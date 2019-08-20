#include "BSTMap.h"
using namespace std;

int main() {
    BSTMap<int, int> a {};

    cout << '\n';
        
    cout << "testing initial empty and size on BST a. \n" << endl;

    cout << "result of empty is: " << a.empty() << endl << endl;
     
    cout << "result of size : " << a.size() << endl << endl;

    cout << "testing initial insert on HashMap a. \n" << endl;

    a.insert(make_pair(5, 10));
        
    cout << "test complete over and out. \n" << endl;

    cout << "test empty and size on BST a. \n" << endl;

    cout << "result of empty is: " << a.empty() << endl << endl;

    cout << "result of size : " << a.size() << endl << endl;
    
    cout << "testing insert after root \n" << endl;

    cout << a.insert(make_pair(3, 5));

    cout << "testing second insert after root, will be right child or left child \n" << endl;
 
    cout << "testing next insert, should be right child of left child \n" << endl;

    a.insert(make_pair(4, 8));

    cout << "testing next insert, should be right child of root \n" << endl;

    a.insert(make_pair(6, 12));

    cout << 

return 0;
}
