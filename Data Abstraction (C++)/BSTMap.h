#include <iostream> 
#include <cassert>

using namespace std;

template<typename K,typename V>
class BSTMap {
     
    struct Node {
        pair<K, V> data;
        Node* leftChild;
        Node* rightChild;
        Node* parent;

    };

    int length = 0;
    Node* root = NULL;
    
    void destroy(Node* n) {
        if(n != NULL) {
                destroy(n -> leftChild);
                destroy(n -> rightChild);
                delete n;
        }
    }

    Node* treeMin(Node* node) const {
        if(node == NULL) { return NULL; }
        while(node -> leftChild) { node = node -> leftChild; }
        return node;
    }
    
    static Node* treeMax(Node* node) {
        if(node == NULL) { return NULL; }
        while(node -> rightChild) { node = node -> rightChild; }
        return node;
    }
    
    void transplant(Node* old, Node* nw) {
        if(old -> parent == NULL) { root = nw; }
        else if(old == old -> parent -> leftChild) { old -> parent -> leftChild = nw; }
        else { old -> parent -> rightChild = nw; }
        if(nw) nw -> parent = old -> parent;
    }
        
    public:
        typedef K key_type;
        typedef V mapped_type;
        typedef pair<K,V> value_type;

        class const_iterator;

        class iterator {
                 Node* node;
                 Node* root;
        
          public:

            friend class const_iterator;
            friend class BSTMap<K, V>;

           iterator(Node* r, Node* n = NULL):node{n}, root{r} {}
            
           bool operator==(const iterator &i) const { return node == i.node; }

           bool operator!=(const iterator &i) const { return !(*this==i); }

           pair<K,V> &operator*() { assert(node); return node -> data; }
        
           iterator &operator++() {
              assert(node);
              if(node -> rightChild) {
                 node = node -> rightChild;
                 while(node -> leftChild) { node = node -> leftChild; }
              }  else {
                    auto y = node -> parent;
                    while(y && node == y -> rightChild) {
                        node = y;
                        y = y -> parent;
                      }
                      node = y;
                 }
                 return *this;
           }

           iterator &operator--() {
              if(node == NULL) { node = treeMax(root); }
              else if (node -> leftChild) {
                node = node -> leftChild;
                while(node -> rightChild) { node = node -> rightChild; }
              } else {
                  auto y = node -> parent;
                  while(y && node == y -> leftChild) {
                        node = y;
                        y = y -> parent;
                  }
                  node = y;
              }
              return *this;
           }

           iterator operator++(int) {
              iterator tmp(*this);
              ++(*this);
              return tmp;
           }

           iterator operator--(int) {
              iterator tmp(*this);
              --(*this);
              return tmp;
          }
       };

       class const_iterator {
           Node* node;
           Node* root;
       
       public:

           friend class BSTMap<K, V>; //may not need this

           const_iterator(Node* r, Node* n = NULL): node{n}, root{r} {}

           const_iterator(const iterator &iter) {
                node = iter.node;
                root = iter.root;
           }

           bool operator==(const const_iterator &i) const {
               return (node == i.node);
           }

           bool operator!=(const const_iterator &i) const {
               return !(*this==i); 
           }

           const pair<K, V> &operator*() { assert(node); return node -> data; }

           const_iterator &operator++() {
               assert(node);
               if(node -> rightChild) {
                node = node -> rightChild;
                while(node -> leftChild) { node = node -> leftChild; }
               } else {
                        auto y = node -> parent;
                        while(y && node == y -> rightChild) {
                                node = y;
                                y = y -> parent;
                        }
                        node = y;
               }
               return *this;
           }

           const_iterator &operator--() {
               if(node == NULL) { node = treeMax(root); }
               else if (node -> leftChild) {
                node = node -> leftChild;
                while(node -> rightChild) node = node -> rightChild;
               } else {
                        auto y = node -> parent;
                        while(y && node == y -> leftChild) {
                                node = y;
                                y = y -> parent;
                        }
                        node = y;
               } 
               return *this;
           }

           const iterator operator++(int) {
               const_iterator tmp(*this);
               ++(*this);
               return tmp;
           }
           
           const iterator operator--(int) {
              const_iterator tmp(*this);
              --(*this);
              return tmp;
           }
       };

       BSTMap() {}

       ~BSTMap() {
          clear();
       }

       BSTMap(const BSTMap<K, V> &that) {
          for(auto &x:that) insert(x);
       }
       
       BSTMap &operator=(const BSTMap<K, V> &that) {
          clear();
          for(auto& x:that) insert(x);
       }

       bool empty() const { return length == 0 && root == NULL; }

       unsigned size() const { return length; }

       iterator find(const key_type& k) {
         Node* ptr = root;
         while(ptr != NULL) {
                if(k == ptr -> data.first) return iterator (root, ptr);
                if(k < ptr  -> data.first) ptr = ptr -> leftChild;
                else ptr = ptr -> rightChild;
         }
         return end();
       }

       const_iterator find(const key_type& k) const {
         Node* ptr = root;
         while(ptr != NULL) {
                if(k == ptr -> data.first) return const_iterator (root, ptr);
                if(k < ptr  -> data.first) ptr = ptr -> leftChild;
                else ptr = ptr -> rightChild;
         }
         return cend();
       }

       unsigned int count(const key_type& k) const {
         return find(k) != cend();
       }

       std::pair<iterator,bool> insert(const value_type& val) {
           Node* ptr = root;
           Node* parent = NULL;
           while(ptr != NULL) {
             parent = ptr;
             if(val.first == ptr -> data.first) return make_pair(iterator(root, ptr), false);
             if(val.first < ptr -> data.first) { ptr = ptr -> leftChild; }
             else { ptr = ptr -> rightChild;
             }
           }
           auto newNode = new Node { val, NULL, NULL, parent };
           if(parent == NULL) { root = newNode; }
           else if (newNode -> data.first < parent -> data.first) { parent -> leftChild = newNode; }
           else { parent -> rightChild = newNode; }
           length++;
           return make_pair(iterator(root, newNode), true);
       }

       template <class InputIterator>
       void insert(InputIterator first, InputIterator last) {
           for(auto iter = first; iter!=last; ++iter) {
               insert(*iter);
           }
       }

       iterator erase(const_iterator position) {
         Node* pos = position.node;
         Node* rover = (++position).node;
         if (pos -> leftChild == NULL) {
                transplant(pos, pos -> rightChild);
         }
         else if(pos -> rightChild == NULL) {
                transplant(pos, pos -> leftChild);
         }
         else {
                Node* y = treeMin(pos -> rightChild);
                if(y -> parent != pos) {
                        transplant(y, y -> rightChild);
                        y -> rightChild = pos -> rightChild;
                        y -> rightChild -> parent = y;
                }
                transplant(pos, y);
                y -> leftChild = pos -> leftChild;
                y -> leftChild -> parent = y;
        }
        length--;
        delete pos;
        return iterator(root, rover);
       }

       unsigned int erase(const key_type& k) {
           auto it = find(k);
           if(it == end()) return 0;
           else {
             erase(it);
             return 1;
           }  
       }

       void clear() {
           destroy(root);
           root = NULL;
           length = 0;
       }

       mapped_type &operator[](const K &key) {
         auto i = insert(make_pair(key, V {})).first;
         return (*i).second;
       }

       bool operator==(const BSTMap<K,V>& rhs) const {
         for(auto i = rhs.begin(); i != rhs.end(); ++i) {
           auto r = find((*i).first);
           if(r == end() || (*r).second != (*i).second) { return false; }
         }      
         return size() == rhs.size();
       }

       bool operator!=(const BSTMap<K,V>& rhs) const {
         return !(*this == rhs);
       }

       iterator begin() { return iterator(root, treeMin(root)); }

       const_iterator begin() const { return const_iterator(root, treeMin(root)); }

       iterator end() { return iterator(root); }

       const_iterator end() const { return const_iterator(root); }

       const_iterator cbegin() const { return const_iterator(root, treeMin(root)); }

       const_iterator cend() const { return const_iterator(root); }

};         
