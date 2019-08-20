10/19

File Linked List

--//normal operations
--
--setPrev(getNext(node), getPrev(...));
--setNext(..., firstFree);
--firstFree = ...;
--
--
--//different ways implementing seek to find the offset for the next node
--fseek(f, i, SEEK_SET);
--   i + sizeof(T)
--   i + sizeof(T) + sizeof(int)
--   sizeof(int) *2                 sentinel
--   fseek(f, i*sizeof(Node) + sizeof(int)*2, SEEK_SET);
--
--
--//getNode 
--   if(emmptyList) {
--      write end of file
--      } else {
--        firstFree=getNext(fr.   )
--        }
--
--
--int main() {
--
-- FILE* f = fopen("hi.bin", "r+");
-- if(f==nullptr) cout <<"FILE DNE"<< endl;
-- f fopen("hi.bin", "w+");
--
-- int firstFree = sizeof(Node) + sizeof(int);
-- fwrite(&firstFree, sizeof(int), 1, f);
--
-- Node i {'s', sizeof(int), sizeof(int)};
-- fwrite(&i, sizeof(Node), 1, f);

----- fseek (f, sizeof(char) + sizeof(int), SEEK_SET);
----- int j;
----- fread( &j, sizeof(int), 1, f);
------ cout << j << endl;

--//get first free node
-- fseek (f, 0, SEEK_SET);
-- fread (&firstFree, sizeof(int), 1, f);
-- fseek (f, firstFree, SEEK_SET);
-- Node ffNode;
-- int retval = fread(&Node, sizeof(Node), 1, f);
-- fseek(f, firstFree, SEEK_SET);
--int nodeAddr = firstFree;
-- if(retval == 1) {
--   firstFree = ffNode.next;
-- } else {
--   firstFree = firstFree + sizeof(Node);
-- }

-- ffNode.data = 'a'; //constructing the ffNode-----vvvv
-- ffNode.prev = sizeof(int);
-- ffNode.next = sizeof(int);
-- fwrite(&ffNode, sizeof(Node), 1, f);

----- fread(&Node, sizeof(Node), 1, f);
----- firstFree = ffNode.next;

----- fseek(f, sizeof(Node) , SEEK_SET);
----- Node i2{ 'a', 0, 9};
----- fwrite(&i2, sizeof(Node), 1, f);

--//modify pre next pointers of neighbors

-- i.prev = sizeof = nodeAddr;
-- i.next = sizeof = nodeAddr;
-- fseek (f, sizeof(int), SEEK_SET);
-- fwrite(&i, sizeof(Node), 1, f);

--//delete a node
 
-- int nodep = sizeof(int) + sizeof(Node);
-- Node node;
-- fseek(f, nodep, SEEK_SET);
-- fread(&node, sizeof(Node), 1, f);
-- fseek(f, node.prev+sizeof(char)+sizeof(int), SEEK_SET);
-- fwrite (&node.next, sizeof(int), 1, f);

--//set node.next.prev = node.prev
 
 
--//get free list
 
-- fseek(f, 0, SEEK_SET);
-- fread(&firstFree, sizeof(int), 1, f);
-- fseek (f, nodep+sizeof(char)+sizeof(int), SEEK_SET);
-- fwrite(&firstFree, sizeof(int), 1, f);
-- fseek(f, 0, SEEK_SET);
-- firstFree = nodep;
-- fwrite(&firstFree, sizeof(int), 1, f);


        ***MIDTERM REVIEW***

--constructors and destructors
--try/catch exceptions
--basic concepts about inherited classes
--how to make copy constructors
--templates
--iterators***fek
--implementations of data structures --*how they work, how to insert, erase, etc, etc*--
--also know how to construct a sentinel, why you need one, structure of linked lists, etc
--know how to modify pointers for data structures 
--how to write data structures and save them into binary files and read them
--know the concept of hash tables, be able to trace code of hash tables prolly
--do fizzbuzz in c++ lmao you know it's gonna be on there don't lie to yourself
--
--less than 10 q's total
--coding questions (~3)
--short answer (~4)


--fizzbuzz
#include <iostream>
using namespace std;
int main ()
{
  for(int i = 1; i <= 100; i++)
  {
    if(i % 3 == 0 && i % 5 == 0)
      cout << "FizzBuzz" << endl;
    else
    if(i % 3 == 0)
      cout << "Fizz" << endl;
    else
    if(i % 5 == 0)
      cout << "Buzz" << endl;
    else cout << i << endl;
  }
  return 0;
}



--Stack implementation
#include<iostream>
#include<cstdlib>
using namespace std;
 
/*
 * Node Declaration
 */
struct node
{
    int info;
    struct node *link;    
}*top;
 
/*
 * Class Declaration
 */
class stack_list
{
    public:
        node *push(node *, int);
        node *pop(node *);
        void traverse(node *);
        stack_list()
        {
            top = NULL;
        }               
};
 
/*
 * Main: Contains Menu
 */
int main()
{
    int choice, item;
    stack_list sl;
    while (1)
    {
        cout<<"\n-------------"<<endl;
        cout<<"Operations on Stack"<<endl;
        cout<<"\n-------------"<<endl;
        cout<<"1.Push Element into the Stack"<<endl;
        cout<<"2.Pop Element from the Stack"<<endl;
        cout<<"3.Traverse the Stack"<<endl;
        cout<<"4.Quit"<<endl;
        cout<<"Enter your Choice: ";
        cin>>choice;
        switch(choice)
        {
        case 1:
            cout<<"Enter value to be pushed into the stack: ";
            cin>>item;
            top = sl.push(top, item);
            break;
        case 2:
            top = sl.pop(top);
            break;
        case 3:
            sl.traverse(top);
            break;
        case 4:
            exit(1);
            break;
        default:
            cout<<"Wrong Choice"<<endl;
        }
    }
    return 0;
}
 
/*
 * Push Element into the Stack
 */
node *stack_list::push(node *top, int item)
{
    node *tmp;
    tmp = new (struct node);
    tmp->info = item;
    tmp->link = top;
    top = tmp;
    return top;
}
 
/*
 * Pop Element from the Stack
 */
node *stack_list::pop(node *top)
{
    node *tmp;
    if (top == NULL)
        cout<<"Stack is Empty"<<endl;
    else
    {       
        tmp = top;
        cout<<"Element Popped: "<<tmp->info<<endl;
        top = top->link;
        free(tmp);
    }
    return top;
}
 
/*
 * Traversing the Stack
 */
void stack_list::traverse(node *top)
{       
    node *ptr;
    ptr = top;
    if (top == NULL)
        cout<<"Stack is empty"<<endl;
    else
    {
        cout<<"Stack elements :"<<endl;
        while (ptr != NULL)
        {
            cout<<ptr->info<<endl;
            ptr = ptr->link;
        }
    }
}


--stack(array) Implementation
#include <string>
using namespace std;
 
class Stack {
private:
      int top;
      int capacity;
      int *storage;
public:
      Stack(int capacity) {
            if (capacity <= 0)
                  throw string("Stack's capacity must be positive");
            storage = new int[capacity];
            this->capacity = capacity;
            top = -1;
      }
 
      void push(int value) {
            if (top == capacity)
                  throw string("Stack's underlying storage is overflow");
            top++;
            storage[top] = value;
      }
 
      int peek() {
            if (top == -1)
                  throw string("Stack is empty");
            return storage[top];
      }
 
      void pop() {
            if (top == -1)
                  throw string("Stack is empty");
            top--;
      }
 
      bool isEmpty() {
            return (top == -1);
      }
 
      ~Stack() {
            delete[] storage;
      }
};

--array Queue implementation
#include<iostream.h>
#include<conio.h>
class queue
{
  public:
  int q[5],front,rear,x,result;
  void enq();
  void dque();
  void disp();
  queue()
  {
    front=0;
    rear=0;
  }
};
void queue::enq()
{
  if(rear>=5)
  cout<<"\nQueue overflow!!\n";
  else
  {
    cout<<"\nEnter the number to be inserted: ";
    cin>>x;
    rear++;
    q[rear]=x;
    cout<<"\nNumber pushed in the queue:"<<q[rear];
  }
}
void queue::dque()
{
  if(rear==0)
  cout<<"\nQueue underflow!!\n";
  else
  {
    if(front==rear)
    {
      front=0;
      rear=0;
    }
    else
      front++;
  }
  cout<<"\nDeleted element is:";
  result=q[front];
  cout<<result;
}
void queue::disp()
{
  if(rear==0)
    cout<<"\nQueue underflow!!\n";
  else
    cout<<"\nContents of queue is:";
  for(int i=front+1;i<=rear;i++)
    cout<<q[i]<<"\t";
}
void main()
{
  int c;
  queue qu;
  clrscr();
//  cout<<"\n*****";
//  cout<<"\nQUEUE";
//  cout<<"\n*****";
  do
  {
    cout<<"\n1.Insertion\n2.Deletion\n3.Display\n";
    cout<<"\nEnter your choice:";
    cin>>c;
    switch(c)
    {
      case 1:
    qu.enq();
    break;
      case 2:
    qu.dque();
    break;
      case 3:
    qu.disp();
    break;
      default:
    cout<<"\nInvalid choice!!\n";
    }
  }
  while(c<4);
  getch();
}



--try/catch in c++
To catch exceptions, a portion of code is placed under exception inspection. This is done by enclosing that portion of code in a try-block. When an exceptional circumstance arises within that block, an exception is thrown that transfers the control to the exception handler. If no exception is thrown, the code continues normally and all handlers are ignored.

An exception is thrown by using the throw keyword from inside the try block. Exception handlers are declared with the keyword catch, which must be placed immediately after the try block:

#include <iostream>
using namespace std;

int main () {
  try
  {
    throw 20;
  }
  catch (int e)
  {
    cout << "An exception occurred. Exception Nr. " << e << '\n';
  }
  return 0;
}
// exceptions
#include <iostream>
using namespace std;

int main () {
  try
  {
    throw 20;
  }
  catch (int e)
  {
    cout << "An exception occurred. Exception Nr. " << e << '\n';
  }
  return 0;
}
An exception occurred. Exception Nr. 20
Edit & Run


The code under exception handling is enclosed in a try block. In this example this code simply throws an exception:

 
throw 20;


A throw expression accepts one parameter (in this case the integer value 20), which is passed as an argument to the exception handler.

The exception handler is declared with the catch keyword immediately after the closing brace of the try block. The syntax for catch is similar to a regular function with one parameter. The type of this parameter is very important, since the type of the argument passed by the throw expression is checked against it, and only in the case they match, the exception is caught by that handler.

Multiple handlers (i.e., catch expressions) can be chained; each one with a different parameter type. Only the handler whose argument type matches the type of the exception specified in the throw statement is executed.

If an ellipsis (...) is used as the parameter of catch, that handler will catch any exception no matter what the type of the exception thrown. This can be used as a default handler that catches all exceptions not caught by other handlers:

try {
  // code here
}
catch (int param) { cout << "int exception"; }
catch (char param) { cout << "char exception"; }
catch (...) { cout << "default exception"; }
try {

In this case, the last handler would catch any exception thrown of a type that is neither int nor char.

After an exception has been handled the program, execution resumes after the try-catch block, not after the throw statement!.

It is also possible to nest try-catch blocks within more external try blocks. In these cases, we have the possibility that an internal catch block forwards the exception to its external level. This is done with the expression throw; with no arguments. For example: 

try {
  try {
      // code here
  }
  catch (int n) {
      throw;
  }
}
catch (...) {
  cout << "Exception occurred";
}


--iterator stuff
Iterator: a pointer-like object that can be incremented with ++, dereferenced with *, and compared against another iterator with !=.

Iterators are generated by STL container member functions, such as begin() and end(). Some containers return iterators that support only the above operations, while others return iterators that can move forward and backward, be compared with <, and so on.

The generic algorithms use iterators just as you use pointers in C to get elements from and store elements to various containers. Passing and returning iterators makes the algorithms

more generic, because the algorithms will work for any containers, including ones you invent, as long as you define iterators for them
more efficient (as discussed here)
Some algorithms can work with the minimal iterators, others may require the extra features. So a certain algorithm may require certain containers because only those containers can return the necessary kind of iterators.

An Example

Here's an example call to copy(), an algorithm that we'll use in our examples below:

copy(v.begin(), v.end(), l.begin());
copy() takes three arguments, all iterators:

an iterator pointing to the first location to copy from
an iterator pointing one element past to the last location to copy from
an iterator pointing to the first location to copy into
In this case, v and l are some STL containers and begin() and end() are member functions that return iterators pointing to locations within those containers.

Note 1: begin() returns a location you can dereference. end() does not. Dereferencing the end pointer is an error. The end pointer is only to be used to see when you've reached it.

Note 2: copy() assumes that the destination already has room for the elements being copied. It would be an error to copy into an empty list or vector. However, this limitation is easily overcome with insert operatorso.



--iterators cont.
The Iterator concept describes types that can be used to identify and traverse the elements of a container.
Iterator is the base concept used by other iterator types: InputIterator, OutputIterator, ForwardIterator, BidirectionalIterator, and RandomAccessIterator. Iterators can be thought of as an abstraction of pointers.
Requirements
The type It satisfies Iterator if
The type It satisfies CopyConstructible, and
The type It satisfies CopyAssignable, and
The type It satisfies Destructible, and
lvalues of type It satisfy Swappable, and
Given
r, an lvalue of type It.
The following expressions must be valid and have their specified effects:
Expression      Return Type     Precondition
*r      unspecified     r is dereferenceable (see below)
++r     It&     r is incrementable (the behavior of the expression ++r is defined)
Dereferenceable iterators
Iterators for which the behavior of the expression *i is defined are called dereferenceable.
Iterators are not dereferenceable if
they are past-the-end iterators (including pointers past the end of an array) or before-begin iterators. Such iterators may be dereferenceable in a particular implementation, but the library never assumes that they are.
they are singular iterators, that is, iterators that are not associated with any sequence. A null pointer, as well as a default-constructed pointer (holding an indeterminate value) is singular
they were invalidated by one of the iterator-invalidating operations on the sequence to which they refer.



--constructors c++
A class constructor is a special member function of a class that is executed whenever we create new objects of that class.

A constructor will have exact same name as the class and it does not have any return type at all, not even void. Constructors can be very useful for setting initial values for certain member variables.

Following example explains the concept of constructor:

#include <iostream>
 
using namespace std;
 
class Line
{
   public:
      void setLength( double len );
      double getLength( void );
      Line();  // This is the constructor
 
   private:
      double length;
};
 
// Member functions definitions including constructor
Line::Line(void)
{
    cout << "Object is being created" << endl;
}
 
void Line::setLength( double len )
{
    length = len;
}
 
double Line::getLength( void )
{
    return length;
}
// Main function for the program
int main( )
{
   Line line;
 
   // set line length
   line.setLength(6.0); 
   cout << "Length of line : " << line.getLength() <<endl;
 
   return 0;
}
When the above code is compiled and executed, it produces the following result:

Object is being created
Length of line : 6
Parameterized Constructor:
A default constructor does not have any parameter, but if you need, a constructor can have parameters. This helps you to assign initial value to an object at the time of its creation as shown in the following example:

#include <iostream>
 
using namespace std;
 
class Line
{
   public:
      void setLength( double len );
      double getLength( void );
      Line(double len);  // This is the constructor
 
   private:
      double length;
};
 
// Member functions definitions including constructor
Line::Line( double len)
{
    cout << "Object is being created, length = " << len << endl;
    length = len;
}
 
void Line::setLength( double len )
{
    length = len;
}
 
double Line::getLength( void )
{
    return length;
}
// Main function for the program
int main( )
{
   Line line(10.0);
 
   // get initially set length.
   cout << "Length of line : " << line.getLength() <<endl;
   // set line length again
   line.setLength(6.0); 
   cout << "Length of line : " << line.getLength() <<endl;
 
   return 0;
}

--Destructors c++
A destructor is a special member function of a class that is executed whenever an object of it's class goes out of scope or whenever the delete expression is applied to a pointer to the object of that class.

A destructor will have exact same name as the class prefixed with a tilde (~) and it can neither return a value nor can it take any parameters. Destructor can be very useful for releasing resources before coming out of the program like closing files, releasing memories etc.

Following example explains the concept of destructor:

#include <iostream>
 
using namespace std;
 
class Line
{
   public:
      void setLength( double len );
      double getLength( void );
      Line();   // This is the constructor declaration
      ~Line();  // This is the destructor: declaration
 
   private:
      double length;
};
 
// Member functions definitions including constructor
Line::Line(void)
{
    cout << "Object is being created" << endl;
}
Line::~Line(void)
{
    cout << "Object is being deleted" << endl;
}
 
void Line::setLength( double len )
{
    length = len;
}
 
double Line::getLength( void )
{
    return length;
}
// Main function for the program
int main( )
{
   Line line;
 
   // set line length
   line.setLength(6.0); 
   cout << "Length of line : " << line.getLength() <<endl;
 
   return 0;
}
When the above code is compiled and executed, it produces the following result:

Object is being created
Length of line : 6
Object is being deleted
