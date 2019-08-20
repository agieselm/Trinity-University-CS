#include <vector>
#include <iostream>

using namespace std;

void merge(vector<T> &v, vector<T> &help) {

  for(int i = begin; i<= last; i++) {
     help[i] = v[i];
  }
  
  int left = begin;
  int right = middle + 1;
  int storeIndex = begin;
  
  while (left <= middle && right <= last) {
     if(help[left] <= help[right]) {
        v[storeIndex] = help[left];
        left++;
     }  else {
        v[storeIndex] = help[right];
        right++;
     }
     
     storeIndex++;
     
  }
  
  int remainder = middle - left;
  for(int i = 0; i <= remainder; i++) {
     v[storeIndex + i] = help[left + i];
  
  }
}



