#include <iostream>
#include <algorithm>


template<typename T>
class DisjointSet {
    public:
        DisjointSet(const T &data):ord(0), data(data), a(this){}

        void unionSets(DisjointSet &ds) {
          DisjointSet* current = findSet();
          DisjointSet* next = ds.findSet();
          if(current -> ord > next -> ord) {
            next -> a = current;
            } else {
                current -> a = next;
                if(current -> ord == next -> ord) {
                  next -> ord += 1;
                }
            }
      
        }
        const T &getMarker() {
          return findSet() -> data;
        }
        
        private:
        
        int ord;
        T data;
        DisjointSet* a;
        
        DisjointSet* findSet() {
          if(a != this) {
            a = a -> findSet();
            return a;
            }
        }
};
