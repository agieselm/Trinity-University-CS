#include <iostream>
int helper (int x);

void swap(int *x, int *y) {

   int temp = *x;
   *x = *y;
   *y = temp;
}

void swap2(int& x, int& y) {
   int temp = x;
   x = y;
   y = temp;
}

int main() {

int a = 2;
int b = 3;
//swap(&a, &b);
  swap2(a, b);

 // std::cout << helper(2)<<'\n';
    std::cout<< a<<' '<<b<<'\n';
}
