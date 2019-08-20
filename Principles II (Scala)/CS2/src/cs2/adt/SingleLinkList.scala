/*
package cs2.adt

/**
 * @author agieselm
 */
class SingleLinkList[A] extends List[A]{
  protected class Node(var data:A, var next:Node) {
    override def toString():String = {
      if(next != null) data.toString + "," + next.toString
      else data.toString 
    }
  }
  
  protected var head:Node = null
  
  protected def getNodeByIndex(idx:Int):Node = {
    var rover:Node = head
    for(i <- 0 until idx) {
      rover = rover.next
    }
    rover
  }
  
  def isEmpty():Boolean = (head == null)
  
  def get(idx:Int):A = {
    getNodeByIndex(idx).data
  }
  
  def set(idx:Int, elem:A) {
    getNodeByIndex(idx).data = elem
  }
  
  def insert(idx:Int, elem:A) {
    if(idx == 0) {
      head = new Node(elem, head)
    } else {
    var curr = getNodeByIndex(idx-1)
    curr.next = new Node(elem, curr.next)
    }
  }  
  def remove(idx:Int):A = {
   if(idx == 0) {
    val tmp = head.data
    head = head.next
    tmp
   } else {
    var prior = getNodeByIndex(idx-1)
    val data = prior.next.data
    prior.next = prior.next.next
    data
  }
 }
  def makeString(curr:Node):String = {
    if(curr == null) ""
    else curr.data.toString + "," + makeString(curr.next)
  }

  
  override def toString():String = {
    if(head != null) head.toString
    else ""
    }
 }
*/