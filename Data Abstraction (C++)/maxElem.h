#include <iostream>
#include <string>

template <Typename C>
typename C::value_type max (const C& c) {
  typename C::value_type res= *(c.begin());
  for (auto it=c.begin(); it!=c.end(); ++it) {
    if(*it > res) res = *it
  }
  return res;
}
        
