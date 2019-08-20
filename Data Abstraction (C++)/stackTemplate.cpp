

template<typename T>
class Stack {
//1 * arg;
//int capacity;
ArrayStack<T>: public Stack<T>
    public:
        virtual ~Stack() {}
        virtual void push(const T &t) = 0;
        virtual T pop() = 0;
        virtual T peek() const = 0;
        virtual bool isEmpty() const = 0;
};


template<typename T>
class Queue {
    public:
//T * arg;
//int capacity;
        virtual ~Queue() {}
        virtual void enqueue(const T &t) = 0;
        virtual T dequeue() = 0;
        virtual T peek() const = 0;
        virtual bool isEmpty() const = 0;

};
