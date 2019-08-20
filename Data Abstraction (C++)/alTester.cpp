#include <iostream>
#include "ArrayList.h"
#include <string>
using std::string;

int main() {
  ArrayList<int> list{};

for(int i = 0; i <= 100; i++) {
  list.push_back(i);
}
for(int i = 0; i < list.size(); i++) {
 std::cout << list[i] << "\n";
}

list.insert(100, 25);

for(int i = 0; i < list.size(); i++) {
 std::cout << list[i] << "\n";
}

list.remove(25);

for(int i = 0; i < list.size(); i++) {
 std::cout << list[i] << "\n";
}

list.clear();

for (int i=0; i <=1000000; ++i){
   list.push_back(i);
}

for (int i=0; i <=1000000; ++i){
   list.pop_back();
}

for (int i=0; i <=1000000; ++i){
   list.push_back(i);
}

for (auto it=list.begin(); it!=list.end(); ++it){}

ArrayList<string> alTester {};

for (int i=0; i <= 100; ++i) {
   alTester.push_back("chipoltle");
//   std::cout << list[i] << "\n";
}
return 1;
}


