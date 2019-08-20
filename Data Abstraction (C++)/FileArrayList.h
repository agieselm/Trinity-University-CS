//Austin Gieselman

#include <cstdio>
#include <iostream>
#ifndef FILEARRAYLIST_H_
#define FILEARRAYLIST_H_
using namespace std;

template <typename T>

class FileArrayList {

        FileArrayList(const FileArrayList<T> &that) = delete; //disables copy constructor

        FileArrayList<T> operator = (const FileArrayList<T> &that) = delete; //disables assignment operator

        private:
                FILE* file;// - your private data;
                int length;// - your private helper functions. (maybe file IO w/ index)
                
                static T read(int i, FILE* file) {
                   T tmp;
                   fseek(file, sizeof(int) + sizeof(T)*i, SEEK_SET);
                   fread(&tmp, sizeof(T), 1, file);
                   return tmp;
                }

                void writeFile(const T &t, int i) {
                   fseek (file, sizeof(int) + sizeof(T)*i, SEEK_SET);
                   fwrite(&t, sizeof(T), 1, file);
                }

                void changeSize() {
                   fseek(file, 0, SEEK_SET);
                   fwrite(&length, sizeof(int), 1, file);
                }                

        public:

        typedef T value_type;
              
        class const_iterator {
        
        private:
               FILE* ptr;
               int index;  
            
                public:
                
                const_iterator() {
                index = NULL;
                ptr = NULL;
                }

                const_iterator(int i, FILE *f) {
                index = i;
                ptr = f;
                }

                const_iterator(const const_iterator &i) {
                        ptr = i.ptr; index = i.index;
                        }

                T operator*() {return read(index, ptr);}

                bool operator==(const const_iterator &i) const {
                        return index == i.index;
                        }

                bool operator!=(const const_iterator &i) const {
                        return index != i.index;
                        }

                const_iterator &operator=(const const_iterator &i) {
                        index = i.index;
                        return *this;
                        }

                const_iterator &operator++(){ ++index; return *this; }

                const_iterator &operator--(){ --index; return *this; }

                const_iterator operator++(int) { 
                        const_iterator tmp = *this; index++; return *tmp;
                        }       

                const_iterator operator--(int) {
                        const_iterator tmp = *this; index--; return *tmp;
                        }

                friend class FileArrayList;

                };

                //General Methods
                
        FileArrayList(const std::string &fname) {
           file = fopen (fname.c_str(), "r+b"); //tries to open fname
           if(file == NULL) {
              file = fopen(fname.c_str(), "w+b"); //w+ will amke and write new file
              length = 0;
              changeSize();
              } else {
                 fread(&length, sizeof(int), 1, file);
                 changeSize();  
              }
        }
                
        template<typename I> //the type i will be an iterator.
        FileArrayList(I begin, I end, const std::string &fname) {
           file = fopen(fname.c_str(), "w+b");
           length = 0;
           changeSize();
           for(auto i = begin; i != end; ++i) {
                push_back(*i);
           }       
        } 

        ~FileArrayList() {
           fclose(file);
        }

        void push_back(const T &t) {
           writeFile(t, length);
           length++;
           changeSize();   
        }

        void pop_back() {
           --length;
           changeSize();
        }

        int size() const {
          return length;           
        }

        void clear() {
           int len = length;
           for(int i = 0; i < len; ++i) {
           pop_back();
           }
        }

        const_iterator insert(const_iterator position,  const T &t) {
           for(int i = length-1; i >= position.index; i--) {
              writeFile(read(i, file), i+1);    
           }
           writeFile(t, position.index);
           ++length;
           changeSize();
           return position;
        }

        T operator[](int index) const {
           return read(index, file);
        }

        const_iterator erase(const_iterator position) {
           for(int i = position.index; i < length-1; ++i) {
              writeFile(read(i+1, file), i);
           }
           --length;
           return position;
        }

        void set(const T &value,int index){writeFile(value, index);}

        const_iterator begin() {return const_iterator(0, file);}

        const_iterator begin() const {return const_iterator(0, file);}

        const_iterator end() {return const_iterator(length, file);}

        const_iterator end() const {return const_iterator(length, file);}

        const_iterator cbegin() const { return const_iterator(0, file);}

        const_iterator cend() const {return const_iterator(length, file);}
};
#endif
