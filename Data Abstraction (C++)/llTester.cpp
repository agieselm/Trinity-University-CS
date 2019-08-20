#include <iostream> 
#include "LinkedList.h"
using std::string;

int main() {
        LinkedList<int> list {};

for(int i = 0; i <= list.size(); ++i) {
        list.push_back(i);
}

list.insert(++++++ list.begin(), 1234);

for(int i = 0; i<= list.size(); ++i) {
        std::cout << list[i] << "\n";
}



return 0;       
};
