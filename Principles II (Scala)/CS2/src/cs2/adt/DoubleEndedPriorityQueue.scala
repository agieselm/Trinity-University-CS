package cs2.adt

abstract class DoubleEndPriorityQueue[A <% Ordered[A]] {
  def isEmpty():Boolean //Return true if there are no elements in the DEPQ
  def add(elem:A) //Add an element to the DEPQ
  def peekMax():A //Return the largest element in the DEPQ
  def max():A     //Return AND Remove the largest element from the DEPQ
  def peekMin():A //Return the smallest element in the DEPQ
  def min():A     //Return AND Remove the smallest element from the DEPQ
}

class DEPQ[A <% Ordered[A]]() extends DoubleEndPriorityQueue[A]() {
  
  class Node(var data: A, var next: Node) 
  
  var head: Node = null
  var tail: Node = null
  
  def isEmpty():Boolean = 
    (head == null) & (tail == null)
  
  def add(elem:A) = {
    if(head == null) {
      head = new Node(elem, null)
      tail = head
    } else {
      var rover = head
      while(rover.next != null) {
        rover = rover.next
        if(rover.data > rover.next.data) {
          rover = new Node(elem, null)
        } else {
          rover =rover.next
        }
      }      
    }
  }
  
  def peekMax():A = {
    head.data
  }
  
  def max():A = {
    val tmp = head
    head = tmp.next
    tmp.data
  }
    
  
  def peekMin():A = {
    tail.data
  }
  def min():A = {
    val tmp = tail
    if (head != tail) {
    var rover = head
    while(rover.next != tail) {
      rover = rover.next
      
    }
    tail = rover
    } else {
      head = null
      tail = null 
    } 
    tmp.data  
  }
 }
