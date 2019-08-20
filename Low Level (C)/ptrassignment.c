#include<stdioh.h>

int main(void) {

  int x = 99, *p1, *p2;
  
  p1 = &x;
  p2 = p1;

  printf("p1 = %P  p2 = %p\n", p1, p2);
  printf("*p1 = %d *p2 = %d\n", *p1, *p2);
}
  return 0;


