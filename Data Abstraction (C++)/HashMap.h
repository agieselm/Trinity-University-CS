#include <iostream>
#include <string>
#include <vector>
#include <list>
#include <forward_list>
#include <cassert>
#include <array>


using namespace std;

template <typename K, typename V, typename Hash>
class HashMap {
        Hash hashFunction;
        vector<list<pair<K,V>>> table; 
        int length;       
        
//Data to store the hash table and the number of elements

public:
   typedef K key_type;
   typedef V mapped_type;
   typedef std::pair<K, V> value_type;
        

   class const_iterator;

   class iterator {
        //NOTE: These might be different depending on how you store your table.

        decltype(table.begin()) mainIter;
        decltype(table.begin()) mainEnd;
        decltype(table[0].begin()) subIter;

   public:

        friend class const_iterator;

   iterator(const decltype(mainIter) mi, const decltype(mainEnd) me):mainIter(mi),mainEnd(me) {
            if(mainIter!=mainEnd) subIter = mainIter->begin();
            while(mainIter!=mainEnd && subIter == mainIter->end()) {
                ++mainIter;
                if(mainIter!=mainEnd) subIter = mainIter->begin();
            }
        }
   iterator(const decltype(mainIter) mi,
                const decltype(mainEnd) me,
                const decltype(subIter) si):
                mainIter(mi),mainEnd(me),subIter(si) {}

        bool operator==(const iterator &i) const { return mainIter==i.mainIter && (mainIter==mainEnd || subIter==i.subIter); }
        bool operator!=(const iterator &i) const { return !(*this==i); }
        std::pair<K,V> &operator*() { return *subIter; }
        iterator &operator++() {
            ++subIter;
            while(mainIter!=mainEnd && subIter==mainIter->end()) {
                ++mainIter;
                if(mainIter!=mainEnd) subIter = mainIter->begin();
            }
            return *this;
        }
        iterator operator++(int) {
            iterator tmp(*this);
            ++(*this);
            return tmp;
        }
    };

    class const_iterator {
    
        decltype(table.cbegin()) mainIter;
        decltype(table.cbegin()) mainEnd;
        decltype(table[0].cbegin()) subIter;
     
     public:
            friend class HashMap;

     const_iterator(const decltype(table.cbegin()) mi,const decltype(table.cbegin()) me):mainIter(mi),mainEnd(me) {
            if(mainIter!=mainEnd) subIter = mainIter->cbegin();
            while(mainIter!=mainEnd && subIter == mainIter->end()) {
                ++mainIter;
                if(mainIter!=mainEnd) subIter = mainIter->begin();
            }
        }

     const_iterator(const decltype(table.cbegin()) mi,
            const decltype(table.cbegin()) me,
            const decltype(table[0].cbegin()) si):
                mainIter(mi),mainEnd(me),subIter(si) {}

     const_iterator(const iterator &i):mainIter(i.mainIter),mainEnd(i.mainEnd),subIter(i.subIter) {}

        bool operator==(const const_iterator &i) const { return mainIter==i.mainIter && (mainIter==mainEnd || subIter==i.subIter); }
        bool operator!=(const const_iterator &i) const { return !(*this==i); }
        const std::pair<K,V> &operator*() const { return *subIter; }
        const_iterator &operator++() {
            ++subIter;
            while(mainIter!=mainEnd && subIter==mainIter->end()) {
                ++mainIter;
                if(mainIter!=mainEnd) subIter = mainIter->begin();
            }
            return *this;
        }
        const_iterator operator++(int) {
            const_iterator tmp(*this);
            ++(*this);
            return tmp;
        }
    };
       
    HashMap(const Hash &hf): hashFunction{hf}, table(1000000), length{0}{}
    //table(vector<vector<pair<K, V>>> (100)), length(0) {}; 
    //HashMap(const HashMap<K,V,Hash> &that); // Only if needed.
    //HashMap &operator=(const HashMap<K,V,Hash> &that); // Only if needed.

    bool empty() const {
       return length == 0;
    }

    unsigned int size() const {
       return length;
    }

    iterator find(const key_type& k) {
      auto bucket = hashFunction(k) % table.size();
      auto mainIt = table.begin() + bucket;
      auto it = mainIt->begin();
      for(;it!=mainIt->end();++it) {
         if(it->first == k) return iterator(mainIt, table.end(), it);
         }
         return end();
      }

    const_iterator find(const key_type& k) const {
      auto bucket = hashFunction(k) % table.size();
      auto mainIt = table.begin() + bucket;
      auto it = mainIt-> cbegin();
      for(;it != mainIt-> cend(); ++it) {
         if(it -> first == k) return const_iterator(mainIt, table.end(), it);
         }
         return cend();
      }
//same but w/ const iterator returned (cend();)
    int count(const key_type& k) const {
       auto h = hashFunction(k) % table.size();
       auto mainIt = table.cbegin()+h;
       auto it = mainIt-> cbegin();
       int cnt = 0;
       for(;it!=mainIt->cend();++it) {
           if(it->first == k) cnt++;
           }
       return cnt;
    }

    std::pair<iterator,bool> insert(const value_type& val) {
       auto bucket = hashFunction(val.first) % table.size();
       auto mainIt = table.begin() + bucket;
       auto it = mainIt -> begin();
       for(;it != mainIt -> end(); ++it) {
          if(it -> first==val.first) {
             return make_pair(iterator(mainIt, table.end(),it), false);
             }
       }
          auto in = mainIt -> insert(mainIt -> end(), val);
          length++;
          return make_pair(iterator(mainIt, table.end(), in), true);
    }
  
    template <class InputIterator>

    void insert(InputIterator first, InputIterator last) {
       for(auto i = first; i != last; ++i) {
          insert(*i);
       }
    }
        
    iterator erase(const_iterator position) {
    
    if(position==cend()){ return end();}
    assert(position.subIter != position.mainIter-> end());
    auto& lst = const_cast<std::list<value_type>&>(*(position.mainIter));
    auto it = lst.erase(position.subIter);
    length--;
    int indice = position.mainIter - table.cbegin();
    if(it==lst.end()) return iterator(table.begin()+indice+1, table.end());
    return iterator(table.begin()+indice, table.end(), it);
    }

    int erase(const key_type& k) {
    
      const_iterator it = find(k) ;
      if(it==cend()) return 0;
      erase(it);
      return 1;
      length--;
    }
    
    void clear() {for(auto& x:table) x.clear(); length = 0;}

    mapped_type &operator[](const K &key) {
    
    iterator it = find(key);
    if( it == end()) {
      auto i = insert(make_pair(key, V()));
      assert(i.second==true);
      return (*(i.first)).second;
      } else {
         assert(length);
         return(*it).second;
         }
    }

    bool operator==(const HashMap<K,V,Hash>& rhs) const {
       for(auto i = begin(); i != end(); i++) {
          auto r = find((*i).first);
          if(r == end() || (*r).second != (*i).second) return false;
          }
            return size() == rhs.size();
    }
             
     //  if(size != rhs.size) return false;
     //  for(auto i = begin(); i != end(); i++) {
     //     auto rightFind = find((*i).first);
     //     if(rightFind == rhs.end()) return false;
     //     if(i->second != rightFind->second) return false;
     //  }
     // return true;
     // }
     
    bool operator!=(const HashMap<K,V,Hash>& rhs) const {
       return !(*this == rhs);
    }

    iterator begin() {return iterator(table.begin(), table.end());}

    const_iterator begin() const {return const_iterator(table.begin(), table.end());}

    iterator end() {return iterator(table.end(), table.begin());}

    const_iterator end() const {return const_iterator(table.end(), table.begin());}

    const_iterator cbegin() const {return const_iterator(table.begin(), table.end());}

    const_iterator cend() const {return const_iterator(table.end(), table.begin());}

 //   private:
    /*
 //   void growTableAndRehash();
 //   HashMap<K,V,Hash> other(hashFunction);
 //   other.table=std::vector<std::list<value_type>>(table.size()*2);
 //   table.swap(other.table);
 //   stdd::swap(sz, other.sz);
 //   insert(other.begin(), other.end());
 //   assert(sz==other.sz);
 //   */
 //   std::vector<std::list<value_type>> oTable((table.size()+1)*2-1);
 //   table.swap(oTable);
 //   auto oSize = length;
 //   length = 0;
 //   ...
 //   ...
 //   ...
};
