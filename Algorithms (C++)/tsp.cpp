#include <iostream>
#include <algorithm>
#include <vector>
#include <unordered_map>

using namespace std;

int calculate(int key, int city, const vector<vector<int>> &connects, vector<unordered_map<int,int>> &bucket) {
  auto find = bucket[city].find(key);
  if(find != bucket[city].end()) {
    return find -> second;
  }
  else {
    int spath = 100000000;
    int shift = key& ~(1<<city);
    for(int i = 1; i < connects.size(); ++i) {
      if(i != city && key & (1<<i)) {
        spath = min(spath, calculate(shift, i, connects, bucket) + connects[i][city]);
      }
    }
    bucket[city][shift] = spath;
    return spath;
  }
}

int shortestCycle(const vector<vector<int>> &connects) {
  int N = connects.size();
  vector<unordered_map<int, int>> bucket(N);
  for(int i = 1; i < N; ++i) bucket[i][1<<i] = connects[0][i];
  int shift = 0;
  for(int i = 1; i < N; ++i) shift|= 1<<i;
  int spath = 1000000000;
  for(int i = 1; i< N; ++i) spath = min(spath, calculate(shift, i, connects, bucket) + connects[i][0]);
  return spath;
}


