/*package cs2.adt

/**
 * @author agieselm
 */
class ArrayQueue[A : Manifest] extends Queue[A] {
  var begin = 0
  var length = 0
  var arr = new Array[A](10)
  
  def enqueue(elem:A) {
    arr((begin + length) % arr.length) = elem
    length += 1
  }
  
  def dequeue():A = {
    val tmp = arr(begin)
    begin = (begin + 1) % arr.length
    tmp
  }
  
  def peek():A = arr(begin)
  
  def isEmpty():Boolean = (length == 0)
  
}*/