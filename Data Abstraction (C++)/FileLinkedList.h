//Austin Gieselman
#include <cstdio>
#include <iostream>
#ifndef FILELINKEDLIST_H_
#define FILELINKEDLIST_H_

template <typename T>
class FileLinkedList {
FileLinkedList(const FileLinkedList<T> &that) = delete;
FileLinkedList<T> operator = (const FileLinkedList<T> &that) = delete;

//offset for iterators
//fseek(file, 2*sizeof(int) + index * (2*sizeof(int)+sizeof(T))+sizeof(int)+sizeof(T), SEEK_SET);
//fread(&index), sizeof(int), 1, file);

   private:
   
   FILE* file;
   int length;
   int firstFree; 
  
   int getNext(int index, FILE* file) const {
      fseek(file, 2*sizeof(int) + index * (2*sizeof(int)+sizeof(T))+sizeof(int)+sizeof(T), SEEK_SET);
      int tmp;
      fread(&tmp, sizeof(int), 1, file);
      return tmp;
   }

   int getPrev(int index, FILE* file) {
      fseek(file, 2*sizeof(int) + index * (2*sizeof(int)+sizeof(T))+sizeof(T), SEEK_SET);
      int tmp;
      fread(&tmp, sizeof(int), 1, file);
      return tmp;
   }

   void writeNext(int index, int next) {
      fseek(file, 2*sizeof(int) + index * (2*sizeof(int)+sizeof(T))+sizeof(int)+sizeof(T), SEEK_SET);
      fwrite(&next, sizeof(int), 1, file);
   }
   
   void writePrev(int index, int prev) {
      fseek(file, 2*sizeof(int) + index * (2*sizeof(int)+sizeof(T))+sizeof(T), SEEK_SET);
      fwrite(&prev, sizeof(int), 1, file);
   }
   
   void writeElems(int index, const T &t) {
      fseek(file, 2*sizeof(int)+index*(2*sizeof(int)+sizeof(T)), SEEK_SET);
      fwrite(&t, sizeof(T), 1, file);
   }

   static T readElems(int index, FILE* file) {
      fseek(file, 2*sizeof(int) + index*(2*sizeof(int) + sizeof(T)), SEEK_SET);
      T tmp;
      fread(&tmp, sizeof(T), 1, file);
      return tmp;
   }

   void changeSize() {
      fseek(file, 0, SEEK_SET);
      fwrite(&length, sizeof(int), 1, file);
      fseek(file, sizeof(int), SEEK_SET);
      fwrite(&firstFree, sizeof(int), 1, file);
   }

   public:

        typedef T value_type;

        class const_iterator {          
        FILE* file;
        int index;

   public:
        const_iterator() {
           index = nullptr;
           file  = nullptr;
        }
        
        const_iterator(int i, FILE *f) {
           index = i;
           file  = f;
        }

        const_iterator(const const_iterator &i) {
           file  = i.file; 
           index = i.index;
        }

        T operator*() {
           return readElems(index, file);
        }

        bool operator==(const const_iterator &i) const {
           return ((index== i.index)&&(file== i.file));
        }

        bool operator!=(const const_iterator &i) const {
           return ((index!= i.index)||(file!= i.file));
        }

        const_iterator &operator=(const const_iterator &i) {
          index = i.index;
          file = i.file;
          return *this;     
        }

        const_iterator &operator++() {
          fseek(file, 2*sizeof(int) + index * (2*sizeof(int)+sizeof(T))+sizeof(int)+sizeof(T), SEEK_SET);
          fread(&index, sizeof(int), 1, file);
          return *this;
        }

        const_iterator &operator--() {
          fseek(file, 2*sizeof(int) + index * (2*sizeof(int)+sizeof(T))+sizeof(T), SEEK_SET);
          fread(&index, sizeof(int), 1, file);
          return *this;
        }

        const_iterator operator++(int) {
          int tmp = index;
          fseek(file, 2*sizeof(int) + index * index *(2*sizeof(int)+sizeof(T))+sizeof(int)+sizeof(T), SEEK_SET);
          fread(&index, sizeof(int), 1, file);
          return const_iterator(tmp, file);
        }

        const_iterator operator--(int) {
          int tmp = index;
          fseek(file, 2*sizeof(int) + index * index *(2*sizeof(int)+sizeof(T))+sizeof(T), SEEK_SET);
          fread(&index, sizeof(int), 1, file);
          return const_iterator(tmp, file);
        }

        friend class FileLinkedList;

   };

   //General Methods

   FileLinkedList(const std::string &fname) {
      file = fopen(fname.c_str(), "r+b");
      if(file==nullptr) {
        file = fopen(fname.c_str(), "w+b");
        length = 0;
        firstFree = -1;
        T tmp;
        writeElems(0, tmp);
        writeNext(0, 0);
        writePrev(0, 0);
        changeSize();
      } else {
        fseek(file, 0, SEEK_SET);
        fread(&length, sizeof(int), 1, file);
        fseek(file, sizeof(int), SEEK_SET);
        fread(&firstFree, sizeof(int), 1, file);
      }
   }

   template<typename I> //the type i will be an iterator

   FileLinkedList(I begin, I end, const std::string &fname) {
      file = fopen(fname.c_str(), "w+b");
      length = 0;
      firstFree = -1;
      changeSize();
      for(auto i = begin; i != end; ++i) {
         push_back(*i);
      }
        changeSize();
   }

   ~FileLinkedList() { //destructor
      fclose(file);
   }

   void push_back(const T &t) {
      insert(end(), t);
   }

   void pop_back() {
      erase(--end());
   }

   int size() const {
      return length;
   }
 
   void clear() {
      length = 0;
      firstFree = -1;
      writeNext(0, 0);
      writePrev(0, 0);
   }

   const_iterator insert(const_iterator position, const T &t) {
      int insertLoc = position.index;
      int index;
      int prevLoc;
      if(firstFree == -1) {
        index = length +1;
      } else {
        index = firstFree;
        firstFree = getNext(firstFree, file);
      }
      writeElems(index, t);
      writePrev(index, getPrev(insertLoc, file));
      writeNext(index, insertLoc);
      prevLoc = getPrev(insertLoc, file);
      writePrev(insertLoc, index);
      writeNext(prevLoc, index);
        
      ++length;
      changeSize();
      return const_iterator(insertLoc, file);
   }

   T operator[](int index) const {
      const_iterator tmp{0, file};
      for (int i = 0; i <= index; i++) {
        ++tmp;
      } 
      return *tmp;
   }

   const_iterator erase(const_iterator position) {
      int eraseLoc = position.index;
      int tmp = getNext(eraseLoc, file);
      writeNext(getPrev(eraseLoc, file), getNext(eraseLoc, file));
      writePrev(getPrev(eraseLoc, file), getPrev(eraseLoc, file));
      writeNext(eraseLoc, firstFree);
      firstFree = eraseLoc;
      
      --length;
      changeSize();
      return const_iterator(tmp, file);
   }

   void set(const T &value, int index) {
     int tmp = 0;
     for(int i = 0; i<= index; ++i) {
        tmp = getNext(tmp, file);
     }
     writeElems(tmp, value);
   }

   void set(const T &value, const_iterator position) {
     writeElems(position.index, value);
   }

      const_iterator begin() {
        return const_iterator (getNext(0, file), file);
      }

      const_iterator begin() const {
        return const_iterator (getNext(0, file), file);
      }

      const_iterator end() {
        return const_iterator(0, file);
      }

      const_iterator end() const {
        return const_iterator(0, file);
      }

      const_iterator cbegin() const {
        return const_iterator(getNext(0, file), file);
      }

      const_iterator cend() const {
        return const_iterator(0, file);
      }
};
#endif
