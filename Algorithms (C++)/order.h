#include <iostream>
#include <vector>
using namespace std;

template<typename T,typename C>

T &findNth(std::vector<T> &data,int index,const C &comp) {
  sort(data.begin(), data.end(), [&comp] (T x, T y) {
  return comp(x, y) < 0;
  });
  return data[index];
}
