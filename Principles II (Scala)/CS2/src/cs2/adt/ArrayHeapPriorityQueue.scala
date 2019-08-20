/*package cs2.adt
/*


 * @author agieselm
 */
class ArrayHeapPriorityQueue[A <% Ordered[A] : Manifest] extends Priority Queue[A] {
  
  private var heap:Array[A] = new Array[A] (10)
  private var len:Int = 0
  
  private def left(idx:Int):Int = {
    if(idx <= 0) 0
    else idx * 2
  } 
  
  private def right(idx:Int):Int = {
    if(idx <= 0) 0
    else idx * 2 + 1
  }
  
  private def parent(idx:Int):Int = {
    if(idx <= 0) 0
    else idx / 2
  }
  
  def isEmpty():Boolean = (len == 0)
  
  def peek():A = heap(1)
  
  def add(elem:A) {
    len += 1
    if(len >= heap.length) {
      val tmp = new Array[A] (len * 2)
      for(i <- 0 until len) tmp(i) = heap(i)
      heap = tmp
    }
    var loc = len
    heap(loc) = elem
    heap(len) = elem
    while (loc > 1 && heap(loc) > heap(parent(loc))) {
      heap(loc) = heap(parent(loc))
      heap(parent(loc)) = elem
      loc = parent(loc)
    }
      
  }
  
  def get():A = {
    val ret = heap(1)
    var loc = 1
    heap(loc) = heap(len)
    heap(len) = heap(0)
    len -= 1
    
    var finished = false
    while(left(loc) <= len && !finished) {
    
    val bigKid = if(right(loc) <= len && heap(right(loc)) > heap(left(loc))) right(loc) else left(loc)
    if(heap(bigKid) > heap(loc)) {
      val tmp = heap(loc)
      heap(loc) = heap(bigKid)
      heap(bigKid) = tmp
      loc = bigKid
    } else {
      finished = true
    }
   }  
    ret
 }
  
  
  
}
*/ 

