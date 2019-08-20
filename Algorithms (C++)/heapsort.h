#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

template<typename T> void maxHeapify(T begin, T end, int i) {

  auto heapSize = end - begin;
  auto currentLargest = i;
  auto left = (i * 2 + 1);
  auto right = ((i * 2) + 2);

  if(left < heapSize && *(begin+(left)) > *(begin + i)) {
    currentLargest = left;
  }

  if(right < heapSize && *(begin + right) > *(begin + currentLargest)) {
    currentLargest = right;
  }

  if(currentLargest != i) {
    swap(*(begin + i), *(begin + currentLargest));
    maxHeapify(begin, end, currentLargest);
    }
}

template<typename T> void buildMaxHeap(T begin, T end) {
  
  auto size = end - begin;
  for(auto i = (size / 2); i >= 0; i--) {
    maxHeapify(begin, end, i);
  }
    
}

template<typename T> void heapsort(T begin, T end) {
  
  auto size = end - begin;
  buildMaxHeap(begin, end);
  for(auto i = size - 1; i >= 0; i--) {
    swap(*begin, *(begin + i));
    maxHeapify(begin, begin + i, 0);
  }
}
