/*package cs2.adt

/**
 * @author agieselm
 */
class LinkedStack[A] extends Stack[A] {
  private class Node(val data:A, val next:Node)
  private var head:Node = null
  
  def push(elem:A) {
    head = new Node(elem, head)
  }
  def pop():A = {
    assert(head != null)
    val tmp = head.data
    head = head.next
    tmp
  }
  def peek():A = {
   assert(head != null) 
   head.data
   }
  def isEmpty():Boolean = (head == null)
  
}*/