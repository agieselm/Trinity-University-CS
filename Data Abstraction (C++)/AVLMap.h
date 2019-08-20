#include <utility>
#include <cassert>
using std::make_pair;

template<typename K,typename V>
class AVLMap {
    //Define your Node
    struct Node {
        std::pair <K, V> data;
        Node* left;
        Node* right;
        Node* parent;
        int hdiff; 
    };
    //Member data
    Node* root = nullptr;
    int dasSize = 0;
    
    void destroy(Node* node){
        if (node){
                destroy(node->left);
                destroy(node->right);
                delete node;
        }
    }
    Node* minimum(Node* node) const {
        if (node==nullptr)return nullptr;
        while (node->left)node=node->left;
        return node;
    }
    static Node* maximum(Node* node) {
        if (node==nullptr) return nullptr;
        while (node->right)node=node->right;
        return node;
    }
    void transplant(Node* u,Node* v){
        if (u->parent==nullptr) root=v;
        else if (u==u->parent->left)u->parent->left=v;
        else u->parent->right=v;
        if (v) v->parent=u->parent;
    }
    void leftRotation (Node* x) {
       Node* y = x -> right;
       x -> right = y -> left;
       if (y -> left != nullptr) y -> left -> parent = x;
       y -> parent = x -> parent;
       if (x -> parent == nullptr) root = y;
       else if (x == x -> parent -> left) x -> parent -> left = y;
       else x -> parent -> right = y;
       y -> left = x;
       x -> parent = y;
       if (x -> hdiff <= 0) incheight(y);
       else if (y -> hdiff > 0) decheight(y);
       if (y -> hdiff > 0) {
          x -> hdiff -= y -> hdiff + 1;
          } else {
          x -> hdiff -= 1;}
       if (x -> hdiff < 0) y -> hdiff -= -x -> hdiff + 1;
       else y -> hdiff -= 1;
    }
    void rightRotation (Node* x) {
       Node* y = x -> left;
       x -> left = y -> right;
       if (y -> right) y -> right -> parent = x;
       y -> parent = x -> parent;
       if (x -> parent == nullptr) root = y;
       else if (x == x -> parent -> right) x -> parent -> right = y;
       else x -> parent -> left = y;
       y -> right = x;
       x -> parent = y;
       if (x -> hdiff >= 0) incheight(y);
       else if (y -> hdiff < 0) decheight(y);
       if (y -> hdiff < 0) {
          x -> hdiff += -y -> hdiff + 1;
          } else {
          x -> hdiff += 1;}
       if (x -> hdiff > 0) y -> hdiff += x -> hdiff + 1;
       else y -> hdiff += 1;
    }
    void incheight(Node* x) {
       Node* parent = x -> parent;
      if (parent == nullptr) return;
          else {
               if (x == parent -> left) {
                  parent -> hdiff -= 1;
                  if (parent -> hdiff < 0) incheight(parent);
               }
               else {
                  parent -> hdiff += 1;
                  if (parent -> hdiff > 0) incheight(parent);
               }
       }
    }
    void decheight(Node* x) {
       if (x == nullptr) return;
       Node* parent = x -> parent;
       if (parent == nullptr) return;    
          else {
               if (x == parent -> left) {
                  parent -> hdiff += 1;
                  if (parent -> hdiff <= 0) decheight(parent);
               }
               else {
                  parent -> hdiff -= 1;
                  if (parent -> hdiff >= 0) decheight(parent);
               }
       }
    }
    void balance(Node* x) {
       Node* parent = x -> parent;
       if (parent == nullptr) return;
       if (parent -> hdiff <= 0 && x == parent -> right) return;
       if (parent -> hdiff >= 0 && x == parent -> left) return;
       Node* gparent = parent -> parent;
       if (gparent == nullptr) return; 
       if (gparent -> hdiff <= 0 && x == gparent -> right) return;
       if (gparent -> hdiff >= 0 && x == gparent -> left) return;
       if (gparent -> hdiff <= 3 && gparent -> hdiff >= -3) {
          balance(parent);
          return;
       }
       else if (gparent -> hdiff > 3) {
          if (x == parent -> left) rightRotation(parent);
          leftRotation (gparent);
       }
       else {
          if (x == parent -> right) leftRotation(parent);
          rightRotation(gparent);
       }
    }

    public:
    typedef K key_type;
    typedef V mapped_type;
    typedef std::pair<K,V> value_type;

    class const_iterator;   

    class iterator {         
        Node* node;
        Node* root;

    public:
        friend class const_iterator;
        iterator(Node* r,Node* n=nullptr):node{n}, root{r} { }
        bool operator==(const iterator &i) const { return node==i.node;  }
        bool operator!=(const iterator &i) const { return !(*this==i); }
        std::pair<K,V> &operator*() { assert(node); return node->data;}
        iterator &operator++() {
            assert(node);
            if(node->right){
                node=node->right;
                while (node->left) node=node->left;
           }
            else{
                auto y=node->parent;
                while ( y && node==y->right){
                        node=y;
                        y=y->parent;
                }
                node=y;
            }
            return *this;
        }
        iterator &operator--() {
            if(node==nullptr)node= maximum(root);
            else if(node->left){
                node=node->left;
                while(node->right)node=node->right;
            }
            else {
                auto y=node->parent;
                while(y && node==y->left){
                        node=y;
                        y=y->parent;
                }
                node=y;
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
    public:
        Node* node;
        Node* root;
    public:
        friend class AVLMap<K,V>;
        const_iterator(Node* r,Node* n=nullptr):node{n},root(r) {  }
        const_iterator(const iterator &iter):node{iter.node},root{iter.root} {}

        bool operator==(const const_iterator &i) const { return node==i.node; }
        bool operator!=(const const_iterator &i) const {  return node!=i.node;}
        const std::pair<K,V> &operator*() { assert(node); return node->data; }
        const_iterator &operator++() {
            if(node->right){
                node=node->right;
                while (node->left) node=node->left;
            }
            else{
                auto y=node->parent;
                while ( y && node==y->right){
                        node=y;
                        y=y->parent;
                }
                node=y;
            }

            return *this;
        }
        const_iterator &operator--() {
            if(node==nullptr) node=maximum(root);
            else if(node->left){
                node=node->left;
                while(node->right)node=node->right;
            }
            else {
                auto y=node->parent;
                while(y && node==y->left){
                        node=y;
                        y=y->parent;
                }
                node=y;
            }
            return *this;
        }
        const_iterator operator++(int) {
            const_iterator tmp(*this);
            ++(*this);
            return tmp;
        }
        const_iterator operator--(int) {
            const_iterator tmp(*this);
            --(*this);
            return tmp;
        }
    };

        
    AVLMap() {
    }
    ~AVLMap() {
        clear();
    }
    AVLMap(const AVLMap<K,V> &that) {
        for(auto &x:that) insert(x);
    }

    AVLMap &operator=(const AVLMap<K,V> &that) {
        clear();
        for(auto& x:that) insert(x);
    }

    bool empty() const { return size() == 0; }

    unsigned size() const { return dasSize; }

    iterator find(const key_type& k) {
       Node* ptr = root;
        while(ptr!=nullptr){
                if (k== ptr->data.first) return iterator(root,ptr);
                if (k<ptr->data.first) ptr=ptr->left;
                else ptr=ptr->right;
        }
        return end();
    }
    
    const_iterator find(const key_type& k) const {
       Node* ptr = root;
        while(ptr!=nullptr){
                if (k== ptr->data.first) return const_iterator(root,ptr);
                if (k<ptr->data.first) ptr=ptr->left;
                else ptr=ptr->right;
        }
        return cend();
    }
    
    unsigned int count(const key_type& k) const {
       return find(k)!=cend();
    }

    std::pair<iterator,bool> insert(const value_type& val) {
       Node* ptr = root;
       Node* parent = nullptr;
       while(ptr != nullptr) {
           parent = ptr;
           if (val.first == ptr-> data.first) return std::make_pair(iterator(root, ptr), false);
           if (val.first < ptr-> data.first) ptr = ptr -> left;
           else ptr = ptr -> right;
       }
       auto newNode = new Node { val, nullptr, nullptr, parent, 0 };
       if (parent == nullptr) root = newNode;
       else if (newNode-> data.first < parent-> data.first) parent-> left = newNode;
       else parent-> right = newNode;
       dasSize++;
       incheight(newNode);
       balance(newNode);
       return std::make_pair(iterator(root, newNode), true);
    }

    template <class InputIterator>
    void insert(InputIterator first, InputIterator last) {
        for(auto iter = first; iter!=last; ++iter) {
            insert(*iter);
        }
    }

    iterator erase(const_iterator position) {
       Node* z = position.node;
       Node* ret = (++position).node;
       if ( z -> left == nullptr) {
          decheight(z);
          transplant(z, z -> right);
       }
       else if (z -> right == nullptr) {
          decheight(z);
          transplant(z, z -> left);
       }
       else {
               Node* y = minimum(z -> right);
               decheight(y);
               if(y -> parent != z) {
                transplant(y, y -> right);
                y -> right = z -> right;
                y -> right -> parent = y;
            }
               transplant(z, y);
               y -> hdiff = z -> hdiff;
               y -> left = z -> left;
               y -> left -> parent = y;
       }
       dasSize--;
       delete z;
       return iterator(root, ret);
    }
        

    unsigned int erase(const key_type& k){
        auto it=find(k);
        if(it==end())return 0;
        else {
                erase(it);
                return 1;
        }
    }


    void clear() {
       destroy(root);
       root=nullptr;
       dasSize=0;
    }

    mapped_type &operator[](const K &key) {
       auto i=insert(make_pair(key, V{})).first;
       return (*i).second;    
    }

    bool operator==(const AVLMap<K,V>& rhs) const{
        for (auto i=rhs.begin();i!=rhs.end();++i){
                auto r=find((*i).first);
                if(r==end() || (*r).second!=(*i).second)return false;
        }
        return size()==rhs.size();
    }

    bool operator!=(const AVLMap<K,V>& rhs) const{return ! (*this == rhs);}

    iterator begin() { return iterator(root,minimum(root)); }

    const_iterator begin() const { return const_iterator(root,minimum(root)); }

    iterator end() { return iterator(root); }

    const_iterator end() const { return const_iterator(root); }

    const_iterator cbegin() const { return const_iterator(root,minimum(root)); }

    const_iterator cend() const { return const_iterator(root); }

};
