package cs2.adt

/**
 * @author agieselm
 */
class LinkedPriorityQueue[A <% Ordered[A]] extends PriorityQueue[A] {
  private class Node(var data:A, var next:Node)
  private var head:Node = null
  
  def isEmpty():Boolean = (head == null)
  def peek():A = head.data
  def get():A = {
    val tmp = head.data
    head = head.next
    tmp
  }
  
  def add(elem:A) {
    if(head == null || elem > head.data) {
      head = new Node(elem, null)
    } else {
    var rover = head
    while(rover.next != null && elem < rover.next.data) 
      rover = rover.next
      rover.next = new Node(elem, rover.next)
    }
  }
 
  /*
  def add(elem:A) {
    head = new Node(elem, head)
  }
  
  def peek():A = {
    var biggest = head.data
    var rover = head
    while(rover.next != null) {
      rover = rover.next
      if(rover.data > biggest) {
        biggest = rover.data
      }
    }
    biggest
  }
  
  def get():A = {
    var rover = head
    var oneBeforeBig = head
    var biggest = head.data
    while(rover.next != null) {
      if(rover.next.data > biggest) {
        biggest = rover.next.data
        oneBeforeBig = rover
      }
      rover = rover.next
    }
    if(biggest <= head.data && biggest >= head.data) {
      head = head.next 
    } else {
      oneBeforeBig.next = oneBeforeBig.next.next
    }
    biggest
  } */ 
}