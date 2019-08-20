#include <iostream>
#include "FileLinkedList.h"
#include <string>
using std::string;

int main() {


template<typename T>
bool simpleTest(T &lst1,const string &secondFile) {
    lst1.clear();
    lst1.push_back(5);
    if(lst1[0]!=5) { cout << "[0]' error after push_back 5, but got "
<<lst1[0]<< endl; return false; }
    lst1.push_back(6);
    if(lst1[1]!=6) { cout << "[1]' error after push_back 5 6, but got
"<<lst1[1] << endl; return false; }
    lst1.push_back(7);
    if(lst1[2]!=7) { cout << "[2]' error after push_back 5 6 7, but
got "<<lst1[2] << endl; return false; }
    lst1.push_back(8);
    lst1.push_back(9);
    if(lst1[0]!=5) { cout << "[0] error after push_back 5 6 7 8 9." <<
endl; return false; }
    if(lst1[1]!=6) { cout << "[1] error after push_back 5 6 7 8 9." <<
endl; return false; }
    if(lst1[2]!=7) { cout << "[2] error after push_back 5 6 7 8 9." <<
endl; return false; }
    if(lst1[3]!=8) { cout << "[3] error after push_back 5 6 7 8 9." <<
endl; return false; }
    if(lst1[4]!=9) { cout << "[4] error after push_back 5 6 7 8 9." <<
endl; return false; }

    int cnt = 5;
    for(auto iter = lst1.begin(); iter!=lst1.end(); ++iter) {
        if(*iter!=cnt) {
            cout << "iter error: *iter should be " << cnt << " but got
" << *iter << endl;
            return false;
        }
        ++cnt;
    }
    cnt = 5;
    for(auto iter = lst1.cbegin(); iter!=lst1.cend(); ++iter) {
        if(*iter!=cnt) {
            cout << "const_iter error " << cnt << " but got " << *iter << endl;
            return false;
        }
        ++cnt;
    }
    cnt = 9;
    for(auto iter = --lst1.end(); iter!=lst1.begin(); --iter) {
        if(*iter!=cnt) {
            cout << "--iter error " << cnt<< " but got " << *iter  << endl;
            return false;
        }
        --cnt;
    }
    lst1.set(99,2);
    if(lst1[2]!=99) {
        cout << "set error." << endl;
        return false;
    }
    T lst2(lst1.begin(),lst1.end(),secondFile);
    lst1.erase(++++lst1.begin());
    if(lst1[0]!=5) { cout << "After erase lst1[0]." << endl; return false; }
    if(lst1[1]!=6) { cout << "After erase lst1[1]." << endl; return false; }
    if(lst1[2]!=8) { cout << "After erase lst1[2]." << endl; return false; }
    if(lst1[3]!=9) { cout << "After erase lst1[3]." << endl; return false; }
    if(lst2[0]!=5) { cout << "After erase lst2[0]." << endl; return false; }
    if(lst2[1]!=6) { cout << "After erase lst2[1]." << endl; return false; }
    if(lst2[2]!=99) { cout << "After erase lst2[2]." << endl; return false; }
    if(lst2[3]!=8) { cout << "After erase lst2[3]." << endl; return false; }
    if(lst2[4]!=9) { cout << "After erase lst2[4]." << endl; return false; }
    lst2.insert(++++++lst2.begin(),98);
    if(lst2[0]!=5) { cout << "After insert lst2[0]." << endl; return false; }
    if(lst2[1]!=6) { cout << "After insert lst2[1]." << endl; return false; }
    if(lst2[2]!=99) { cout << "After insert lst2[2]." << endl; return false; }
    if(lst2[3]!=98) { cout << "After insert lst2[3]." << endl; return false; }
    if(lst2[4]!=8) { cout << "After insert lst2[4]." << endl; return false; }
    if(lst2[5]!=9) { cout << "After insert lst2[5]." << endl; return false; }
    lst2.pop_back();
    if(lst2.size()!=5) { cout << "bad size after pop_back." << endl;
return false; }
    lst2.pop_back();
    if(lst2.size()!=4) { cout << "bad size after pop_back." << endl;
return false; }
    lst2.pop_back();
    if(lst2.size()!=3) { cout << "bad size after pop_back." << endl;
return false; }
    if(lst2[0]!=5) { cout << "After pop_back lst2[0]." << endl; return false; }
    if(lst2[1]!=6) { cout << "After pop_back lst2[1]." << endl; return false; }
    if(lst2[2]!=99) { cout << "After pop_back lst2[2]." << endl; return false; }
    return true;
}

}
