#ifndef ARRAYLIST_H_
#define ARRAYLIST_H_    
#include <iostream>

template <typename T> 
class ArrayList {
   private: 
   
      T *container;
      int mySize;
      int currLength;
             
   public:
     
      ArrayList() { container = new T[10]; mySize = 10; currLength = 0; }
      ArrayList(const ArrayList <T>& that): container(new T[that.mySize]), mySize(that.mySize), currLength(that.currLength){
        for(int i = 0; i < mySize; ++i){
         container[i] = that.container[i];
        }
      }
      
      ArrayList &operator=(const ArrayList<T> &a) {
        T* tmp = new T[a.mySize];
        for(int i = 0; i < mySize; ++i){
          tmp[i] = a.container[i];
            
        }
        delete []container;
          container = tmp;
          mySize = a.mySize;
          currLength = a.currLength;
        return *this;
        }
      
      ~ArrayList(){
      delete []container;
      }
      
      void push_back(const T &t) {       //add to the end.
      if(mySize == currLength) { 
        T* tmp = new T[mySize*2];
        for(int i = 0; i < mySize; ++i){
          tmp[i] = container[i];
        }
        delete []container;
          container = tmp;
          mySize *= 2;
          container[currLength] = t;
          currLength ++;  
      }
        else {
          container[currLength] = t;
          currLength ++;
        }
      }  
      
      void pop_back() {                  //remove last element
      if (currLength == 0) {
          
      }
      else {
        currLength -=1;
        }
      }  
          
      int size() const {
        return (currLength);
      }
            
      void clear(){
        currLength = 0;
      }     
        
      void insert(const T &t, int index) {       //insert this element before the given index
        if(mySize == currLength) {
          T* tmp = new T[mySize*2];
        for(int i = 0; i < mySize; ++i){
          tmp[i] = container[i];
        }
        delete []container;
          container = tmp;
          mySize *= 2;

        for(int i = currLength; i > index; --i) {
          container[i] = container[i - 1];
        }
          container[index] = t;
          currLength += 1;
        }

        else {

         for(int i = currLength; i > index; --i) {
          container[i] = container[i - 1];
        }
          container[index] = t;
          currLength += 1;

        }  
      }  
      
      const T &operator[](int index) const {     //get the element at the index.
        return (container[index]);
      }
        
      T &operator [](int index) {                //get the element at the index.
        return (container[index]);
      }  
      
      void remove(int index) {                   //remove the item at the given index.
        for(int i = index; i < currLength; ++i) {
          container[i] = container[i + 1];
        }
        currLength -= 1;
     }   
      
     class iterator {
                T* p;
      public:
                
                iterator(T *l) { p = l; }
                iterator() { p = nullptr; }
                iterator(const iterator &i) {
                  p = i.p;
                }
                T &operator*() { return *p; } 

      bool operator==(const iterator &i) const{ return ( p == i.p ); }

      bool operator!=(const iterator &i) const{ return ( p != i.p ); }

      iterator &operator=(const iterator &i){ return ( p = i.p ); }

      iterator &operator++()    {
                p++;
                return *this;   
                }

      iterator &operator--()    {
                p--;
                return *this;   
                }

      iterator operator ++(int) {
                return p++; 
                }
                
      iterator operator --(int) {
                return p--; 
                }
     };

     class const_iterator {
                T*p;
       public:
                const_iterator(T *l) { p = l; }
                const_iterator() { p = nullptr; }
                const_iterator(const iterator &i) {
                  p = i.p;
                }
                const T &operator*() { return *p; }

      bool operator==(const const_iterator &i) const{ return ( p == i.p ); }

      bool operator!=(const const_iterator &i) const{ return ( p != i.p ); }

      const_iterator &operator=( const iterator &i){ return ( p = i.p ); }
                        
      const_iterator &operator++() {
                p++;
                return *this; 
                }

      const_iterator &operator--() {
                p--;
                return *this; 
                }

      const_iterator operator ++(int) {
                return p++; 
                }

      const_iterator operator --(int) {
                return p--; 
                }
     };

      iterator begin(){ return iterator(&container[0]); }

      const_iterator begin() const { return const_iterator(&container[0]); }

      iterator end(){ return iterator (&container[currLength]); }

      const_iterator end() const { return const_iterator(&container[currLength]); }

      const_iterator cbegin() const { return const_iterator (&container[0]); }

      const_iterator cend() const { return const_iterator(&container[currLength]); }
};
#endif
