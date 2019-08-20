package cs2.adt

/**
 * @author agieselm
 */
 class ParQueue[A : Manifest] extends Queue[A] {

 class Lock
 val lock1 = new Lock
  
 private var arr:Array[A] = new Array[A](10)
  private var begin = 0
  var len = 0
  
  def length():Int = len
  def size():Int = len
  
  def enqueue(elem:A) {
   lock1.synchronized {
    if(len == arr.length) {
      val tmp = new Array[A](arr.length * 2)
      for(i <- 0 until len) tmp(i) = arr((begin + i) % arr.length)
      arr = tmp
      begin = 0
    }
    arr((begin + len) % arr.length) = elem
    len += 1
  }
 }  
 
  def dequeue():A = {
    lock1.synchronized {
    val retval = arr(begin)
    begin = (begin + 1) % arr.length
    len -= 1
    retval
    } 
   }
  
  override def peek():A = {
    arr(begin)
  }
  
  override def isEmpty():Boolean = {
    len == 0
  }
  
  override def toString():String = {
    var ctr = begin
    var retval = "(" + size.toString + ") ["
    for(i <- 0 until len) {
      retval += arr((begin + i) % arr.length)
      if(i < len-1) retval += ","
    }
    retval + "]"
  }
}
  
  

/* If you wish to test your implementation, you may utilize the stand-alone object 
 * but this portion will not be evaluated/tested during grading.
 */
object ParQueueTester {
  def main(args:Array[String]) {

    var a = new ParQueue[Int]

    val threads = Array.tabulate(10)(i => new Thread {
      override def run() {
        for (i <- 1 to 1000000) a.enqueue(i)
        for (i <- 1 to 1000000) a.dequeue()
        
      }
    })
    for (t <- threads) t.start
    for (t <- threads) t.join
    println(a.len)
  }
  
    
    
  
}