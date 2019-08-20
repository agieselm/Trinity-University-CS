#include <funtional>
#include <unordered_map> 
#include <iostream>
using namespace std;

int hashInt(int x) {
   return x;
}

int main() {

   std::unordered_map<int, double, function<int(int)>> m(20, hashInt);

   std::unordered_map<int, double, function<int(int)>> m(20, [](int x){return x;$


//   HashMap<int, double, function<int(int)>>HashMap(hashInt);
   
   m[1] = 1.0;

   cout<<m[1]<<endl;
   return 0;
}
