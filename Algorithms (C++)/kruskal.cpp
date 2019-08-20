#include <iostream>
#include <algorithm>
#include <set>
#include <vector>
#include <cmath>
#include <tuple>
using namespace std;

struct Edge {
  tuple<double, double> a;
  tuple<double, double> b;
  double weight;
  Edge(tuple<double, double> a, tuple<double, double> b, double weight);
};

double dst(tuple<int, int> first, tuple<int, int> second) {
  auto distance = sqrt(pow(get<0>(first) - get<0>(second),2) + (pow(get<1>(first) - get<1>(second),2)));  //distance between two points on a graph
  return distance;
};

tuple<double,vector<tuple<int,int> > > sidewalkPlan(const vector<tuple<double, double> > &buildingLocations) {

};
