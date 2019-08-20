// team I (agieselm, jnguyen4, ccrossla)

//#include <queue>
//#include <array>
//#include <list>
#include <iostream>
#include <vector>
#include <iterator>
using namespace std;








template<typename T,typename K>
void radixSort(T begin,T end,K keyFunc){
  int max = 0;


  int sz = end - begin;

  for (int i = 0; i < sz; i++) {
    if( keyFunc(*(begin+i)) > max) {
      max = keyFunc(*(begin+i));
    }
  }

  for(int mod = 1; max/mod>0; mod*=10) {
     countingSort(begin, end, keyFunc, mod);
  }
}

/*
template<typename T,typename K>
void radixSort(T begin, T end, K keyFunc) {
  int maxElem = 0;

  int sz = end - begin;
}
*/

template<typename T, typename K>
void countingSort(T begin, T end, K keyFunc, int mod){

  std::vector<typename T::value_type> tmp(end - begin);

  std::vector<int> Countup(10);

  for(int i = 0; i < 10; i++) {
    Countup[i] = 0;
  }


  for(int i = 0; i < (end - begin); i++) {
    int bkt = (keyFunc(*(begin + i))/mod) % 10;
    Countup[bkt]++;
  }
  for(int i = 1; i < 10; i++) {
    Countup[i] += Countup[i - 1];
  }



  for(int i = (end - begin) - 1; i >= 0; i--) {
    int index = (keyFunc(*(begin + i))/mod)%10;
    Countup[index]--;
    tmp[Countup[index]] = *(begin + i);
  }
  for(int i = 0; i < (end - begin); i++) {
    *(begin + i) = tmp[i];
  }
}
