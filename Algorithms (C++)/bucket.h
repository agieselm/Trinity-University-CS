#include <iostream>
#include<vector>
using namespace std;
template<typename T,typename K>

void insertionSort(T begin, T end, K keyFunc){
  auto size = end-begin;
  for(int i = 1; i<size; ++i){
    auto n = begin[i];
    int j = i-1;
    for(; j>=0 && keyFunc(begin[j])>keyFunc(n); --j){
      begin[j+1] = begin[j];
    }
    begin[j+1] = n;
  }
}
template<typename T,typename K>
void bucketSort(T begin, T end, K keyFunc){
  double min = keyFunc(*begin);
  double max = keyFunc(*begin);
  for(auto iter = begin+1; iter!=end; ++iter){
    double key = keyFunc(*iter);
    if(key>max)max = key;
    if(key<min) min = key;
  }
  vector<vector<typename T::value_type>> buckets((end-begin)/40);
  double bucketSize = (buckets.size()-1)/(max-min);
  for(auto iter = begin; iter!=end; ++iter){
    auto val = *iter;
    int bin = bucketSize*(keyFunc(val)-min);
    buckets[bin].push_back(val);
  }
  auto iterator = begin;
  for(auto outer:buckets){
    for(auto inner:outer){
      *iterator = inner;
      ++iterator;
    }
  }
  insertionSort(begin,end,keyFunc);
}
