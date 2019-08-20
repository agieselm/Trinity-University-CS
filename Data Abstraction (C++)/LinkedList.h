//Austin Gieselman

#ifndef LINKEDLIST_H_ 
#define LINKEDLIST_H_
#include <iostream>

template <typename T> 
class LinkedList {
        struct Node { 

          Node(T a, Node* b, Node* c): data(a), Next(b), Prev(c) { }
          T data;
          Node* Next;
          Node* Prev;  
          Node(): Prev(NULL), Next(NULL) {}
        
        };

        Node* sentinel;
        int length;

     public:
          typedef T value_type;
        
          class iterator {
             private: 
                Node* ptr;

             public:
                  iterator(Node *a) { ptr = a; }
                  iterator(){ ptr = nullptr; }
                  iterator(const iterator &i) { ptr = i.ptr; }

                   T &operator*() { return ptr->data; }

                   bool operator ==(const iterator &i) const { 
                        return ptr == i.ptr;
                   }

                   bool operator !=(const iterator &i) const { 
                        return ptr != i.ptr;
                   }

                   iterator &operator = (const iterator &i) { 
                        ptr = i.ptr;
                        return *this;
                   }

                   iterator &operator ++() { 
                      ptr = ptr->Next;
                      return *this;     
                   }

                   iterator &operator --() { 
                      ptr = ptr->Prev;
                      return *this; 
                   }

                   iterator operator ++(int) {
                      ptr = ptr->Next;
                      return *this;
                   }

                   iterator operator --(int) {
                      ptr = ptr->Prev;
                      return *this;
        
                   }
                   friend class const_iterator;
                   friend class LinkedList;
                
            };
            class const_iterator {
              private:
                
                Node* ptr;
        
              public:
                    
                  const_iterator(Node *a) { ptr = a; }
                  const_iterator(){ ptr = nullptr; }
                  const_iterator(const iterator &i) { ptr = i.ptr; }
                     
                     const T &operator*() { 
                        return ptr->data;
                     }

                     bool operator==(const const_iterator &i) const { 
                        return ptr == i.ptr;
                     }
                     bool operator!=(const const_iterator &i) const { 
                        return ptr != i.ptr;
                     }

                     const_iterator &operator=(const const_iterator &i) { 
                        ptr = i.ptr;
                        return ptr;
                     }

                     const_iterator &operator++() { 
                        ptr = ptr->Next;
                        return *this;
                     }

                     const_iterator &operator--() { 
                        ptr = ptr->Prev;
                        return *this;
                     }
                
                     const_iterator operator++(int)  {  
                        ptr = ptr->Next;
                        return *this;
                     } 

                     const_iterator operator --(int) {  
                        ptr = ptr->Prev;
                        return *this;
                     }

            };

       
            LinkedList(): length (0), sentinel(new Node()) {
                sentinel->Next = sentinel;
                sentinel->Prev = sentinel;
            }

            LinkedList(const LinkedList &that):length(0), sentinel(new Node()){
                 sentinel->Next = sentinel; 
                 sentinel->Prev = sentinel; 
                 for(auto i = that.begin(); i != that.end(); ++i) {
                        push_back(*i);
                 }
           }

            LinkedList &operator =(const LinkedList &al) {
                sentinel = new Node();
                sentinel -> Next = sentinel;
                sentinel -> Prev = sentinel;
                length = 0;
                for(auto i = al.begin(); i != al.end(); ++i) {
                        push_back(*i);
                }
                return *this;
            }

            ~LinkedList() {
              
               clear();
               delete sentinel;
            }

            void push_back(const T &t) {
               Node* tmp = new Node(t, sentinel, sentinel->Prev);
               sentinel->Prev->Next = tmp;
               sentinel->Prev = tmp;
               ++length;
            }

            void pop_back() {
               Node* tmp = sentinel->Prev;
               sentinel->Prev = sentinel->Prev->Prev;
               sentinel->Prev->Next = sentinel; 
               delete tmp;
                 --length;
            }

            int size() const { 
               return length; 
            }

            void clear() {
               int l = length;
               for(int i = 1; i < l; ++i) {
                  pop_back();
               }
            }

            iterator insert(iterator position, const T &t) {
             Node* i = new Node(t, position.ptr, position.ptr->Prev);
             (position).ptr->Prev->Next = i;
             (position).ptr->Prev = i;
             length ++;
             return iterator(position.ptr);
            }

            const T &operator[](int index) const{
                Node* rover = sentinel->Next;
                for(int i = 0; i < index; ++i) {
                        rover = rover->Next;
                }
                return rover->data;
            }

            T &operator[](int index) {
                Node* rover = sentinel->Next;
                for(int i = 0; i < index; ++i) {
                        rover = rover->Next;
                }
                return rover->data;
            }
            
            iterator erase(iterator position) {
                Node* tmp = (position).ptr->Next;
                (position).ptr->Prev->Next = (position).ptr->Next;
                (position).ptr->Next->Prev = (position).ptr->Prev;
                delete position.ptr;
                --length;
                return tmp;
            }
                
            iterator begin() { return iterator(sentinel->Next); }

            const_iterator begin() const { return const_iterator(sentinel-> Next); }

            iterator end() { return iterator(sentinel); }

            const_iterator end() const { return iterator(sentinel); }

            const_iterator cbegin() const { return const_iterator(sentinel->Next); }

            const_iterator cend() const { return const_iterator(sentinel); }

};

#endif

