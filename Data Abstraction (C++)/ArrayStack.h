#ifndef STACK
#define STACK
#include "Stack.h"


template <typename T>
class ArrayStack:public Stack<T> {
	public:
		int cap;
		int top; //Keeps track of the top element of the stack
		int size; //Keeps track of how many elements are in the stack
		T *data; 
		
		ArrayStack(): data(new T[10]), size(10), cap(10),top(0){};
		ArrayStack(const ArrayStack& copy): data(new T[copy.size]), size(copy.size), cap(copy.cap), top(copy.top){ //Copy Constructor
		for(int i = 0; i < size; ++i) { 
			data[i] = copy.data[i];
		}
		};
		~ArrayStack() { delete[] data; };

	bool isEmpty() const {
	return(top == 0);
	}

	T peek() const {
	return (data[size - 1]);
	}

	void push (const T& t) {
		if(size == cap) {
		T *arr;
		arr = new T[cap*2+1];
		cap = cap*2+1;
		for(int i = 0; i < size; ++i) {
		arr[i] = data[i];	
		}
		delete[] data;
		data = arr;
		};		 
		++top;
		++size;
		data[top] = t;
	}	 

	T pop() {
		T output = data[top];
		top -= 1;
		return output;
	}	
};

#endif
