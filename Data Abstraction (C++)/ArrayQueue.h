#ifndef QUEUE
#define QUEUE
#include "Queue.h"

template <typename T>
class ArrayQueue : public Queue<T> {
	public: 
		int front;
		int rear;
		int size;
		int cap;
		T *data;

	        ArrayQueue(): data(new T[10]), size(0), cap(10), front(0), rear(0){};
		ArrayQueue(const ArrayQueue& copy): data( new T[copy.size]), size(copy.size), front(copy.front), rear(copy.rear) {
			for(int i = 0; i < size; ++i) {
				data[i] = copy.data[i];
			}
		};
		
		~ArrayQueue() { delete[] data; };

	bool isEmpty() const {
	return(front == rear);
	}	

	T peek() const {
	return(data[front]);
	}
	
	void enqueue(const T& t) {
		if(size == cap) {
		T* arr2;
		arr2 = new T[cap*2+1];
		cap = cap*2;
		for(int i = 0; i < size; ++i) {
		arr2[i] = arr2[(front + i) % size];
		}
		delete[] data;
		data = arr2;
		front = 0;
		}
		data[(front + size) % cap] = t;
		++size;
	}

	T dequeue() {
		T output = data[front];
		front = (front + 1) % cap;
		size--;
		return output;
	}

};

#endif
