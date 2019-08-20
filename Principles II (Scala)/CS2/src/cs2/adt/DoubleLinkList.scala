/*
package cs2.adt

/**
 * @author agieselm
 */
class DoubleLinkList[A : Manifest] extends List[A] {
  protected class Node(var data:A, var prev:Node, var next:Node)
  protected var end:Node = new Node(new Array[A](1)(0), null, null)
  end.prev = end
  end.next = end
  
  protected def getNodeByIndex(idx:Int):Node = {
    var rover:Node = end
    for(i <- 0 to idx) rover = rover.next
    rover
  }
   
  def get(idx:Int):A = getNodeByIndex(idx).data
  
  def set(idx:Int,elem:A) { 
    getNodeByIndex(idx).data = elem
    }
  
  def insert(idx:Int, elem:A) {
    var oneBefore = getNodeByIndex(idx-1)
    oneBefore.next = new Node(elem, oneBefore, oneBefore.next)
    oneBefore.next.next.prev = oneBefore.next
  }
  
  def remove(idx:Int):A = {
    var oneBefore = getNodeByIndex(idx-1)
    val ret = oneBefore.next.data
    oneBefore.next = oneBefore.next.next
    oneBefore.next.prev = oneBefore
    ret
  }
    
}
*/