#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <tuple>
#include <climits>

using namespace std;

struct compare {
  bool operator() (tuple<int, int> &a, tuple<int, int> &b) { //struct for comparator that compares neighboring nodes
    return ((get<0>(a)) > (get<0>(b)));
  }
};

tuple<vector<int>,vector<int>> distanceFromHome(const vector<vector<tuple<int,int>>> &nearbyDistances) {

int src = 0; //starting node = 0
vector<int> dist(nearbyDistances.size()); //unknown distance from start
vector<int> prev(nearbyDistances.size());
(dist)[src] = 0; //initialize distance from source as 0
(prev)[src] = -1; //predesessor of source
priority_queue<tuple<int, int>, vector<tuple<int, int>>, compare> Q; //priority queue Q for vertex set
Q.push(make_tuple(dist[src], src)); //push elements onto the queue, starting with the source (travelling pete's house)

for(unsigned int i = 1; i < nearbyDistances.size(); i++) {
   dist[i] = INT_MAX;
   prev[i] = 0;
}

while(!Q.empty()) { //main loop, checks neighbors while there are still nodes to check
  int u = get<1>(Q.top()); //gets current element in the queue you are comparing neighbors of
  Q.pop();

  for(unsigned int i = 0; i < nearbyDistances[u].size(); i++) { //compares the curr element (u) with it's neighbors
    int alt = dist[u] + (get<1>(nearbyDistances[u][i])); //gets next neightbor of u (i)
    if(alt < (dist [get<0>(nearbyDistances[u][i])])) { //compare tentative distance from current node to curr assigned value and assgn smaller one
      dist[get<0>(nearbyDistances[u][i])] = alt;
      prev[get<0>(nearbyDistances[u][i])] = u;
      Q.push(make_tuple(alt, get<0>(nearbyDistances[u][i]))); //add result to the queue
    }
  }
}
  return (make_tuple(dist, prev));
}

// int main() {
//
//   vector<vector<tuple<int,int>>> map1 =
//    {{make_tuple(1,1),make_tuple(2,3)},
//     {make_tuple(2,1),make_tuple(0,5)},
//     {make_tuple(0,3),make_tuple(1,3)}};
//  auto ret1 = distanceFromHome(map1);
//  vector<int> d1 = {0,1,2};
//  vector<int> p1 = {-1,0,1};
//  if(d1!=get<0>(ret1)) {
//    cout << "Distance error 1" << endl;
//    return -1;
//  }
//  if(p1!=get<1>(ret1)) {
//    cout << "Parent error 1" << endl;
//    return -1;
//  }
// }
