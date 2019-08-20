#include <cstdio>
#include <iostream>
using namespace std;

struct Node {
   char data;
   int prev;
   int next;
};

int main() {
    
   FILE * f = fopen("hi.bin", "r+"); //tries to open file "hi.bin"
   if(f == nullptr) cout << "File DNE" <<endl;
   f = fopen("hi.bin", "w+"); //w+ will make and write to a new file

   Node i { 's', 0 };
   fwrite (&i, sizeof(Node), 1, f);
   
   fseek (f, sizeof(char) + sizeof(int), SEEK_SET);

   //int i = 4;
   //fwrite(&i , sizeof(int), 1, f );
   
   //i = 5;
   //fwrite(&i, sizeof(int), 1, f );

   //fseek (f, sizeof(int), SEEK_SET);
     
   int j;
   fread(&j, sizeof(int), 1, f);
   cout << j << endl;
   fseek(f, sizeof(Node), SEEK_SET);
   Node i2 { 'a', 0, 0 }
   fwrite(&i2, sizeof(Node, 1, f);   
   i.prev = sizeof(Node);
   i.next = sizeof(Node);
   fseek(f, 0, SEEK_SET);
   fwrite(&i, sizeof(Node), 1, f);

};
