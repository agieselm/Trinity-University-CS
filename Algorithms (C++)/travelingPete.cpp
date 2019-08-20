#include <iostream>
#include <vector>
#include <climits>
#include <tuple>
#include <queue>
using namespace std;

struct comp {
    bool operator()(tuple<int, int> &a, tuple<int, int> &b) {
      return ((get<0>(a)) > (get<0>(b)));
  }
};

tuple<vector<int>, vector<int>> distanceFromHome(const vector<vector<tuple<int, int>>> &nearbyDistances) {
  cout << "1) Made it into the algorithm.\n";
  vector<int> dist (nearbyDistances.size()); // WHAT'S BEING RETURNED
  vector<int> prev (nearbyDistances.size()); // WHAT'S BEING RETURNED

  int source = 0; // Initialize-single-source
  dist[source] = 0;
  prev[source] = -1;

  priority_queue<tuple<int, int>, vector<tuple<int,int>>, comp> Q;
  Q.push(make_tuple(dist[source], source));
  cout << "2) Created priority queue.\n";

  for(unsigned int i = 1; i < nearbyDistances.size(); i++) { //Initialize-single-source
      dist[i] = INT_MAX;
      prev[i] = 0;
      cout << "Size of queue is:" << Q.size() << endl;
  }
  cout << "3) Finished initializing the edges.\n";

  while(!Q.empty()) {
    int u = get<1>(Q.top()); // Extract min
    Q.pop(); // Extract min

    for(unsigned int i = 0; i < nearbyDistances[u].size(); i++) {
      int alt = dist[u] + (get<1>(nearbyDistances[u][i]));
      if(alt < (dist[get<0>(nearbyDistances[u][i])])) {
        dist[get<0>(nearbyDistances[u][i])] = alt;
        prev[get<0>(nearbyDistances[u][i])] = u;
        Q.push(make_tuple(alt, get<0>(nearbyDistances[u][i])));
      }
    }
  }
  cout << "4) Finished algorithm!\n";
  return (make_tuple(dist, prev));
}

int main() {
  cout << "Begin Test.\n";
  vector<vector<tuple<int, int>>> map1 = {
    {make_tuple(1,1), make_tuple(2,3)},
    {make_tuple(2,1), make_tuple(0,5)},
    {make_tuple(0,3), make_tuple(1,3)}
  };
  cout << "Initialized map.\n";
  auto ret = distanceFromHome(map1);
  vector<int> d1 = {0, 1, 2};
  vector<int> p1 = {-1, 0, 1};
  if(d1 != get<0>(ret)) {
    cout << "Distance error 1" << endl;
    cout << "Here's what's being returned:\n";
    for (auto i:get<0>(ret)) {
      cout << i << endl;
    }
  } else {
    cout << "Passed 1\n";
  }
  if(p1 != get<1>(ret)) {
    cout << "Parent error 1" << endl;
  } else {
    cout  << "Passed 2\n";
    for (auto i:get<1>(ret)) {
      cout << i << endl;
    }
  }
}
