
/*
package cs2.adt

/**
 * @author agieselm
 */
class IterableSingleLinkList[A] extends SingleLinkList[A] {
  def iterator():scala.collection.Iterator[A] = {
    new scala.collection.Iterator[A] {
      
      var current = head
      def next():A = {
        val tmp = current.data
        current = current.next
        tmp
          
      }
      
      def hasNext():Boolean = (current != null)
    }
  }
}

object ISLLTester {
  def main(args:Array[String]) {
    val sll = new IterableSingleLinkList[Int]
    for(i <- 0 until 30) sll.insert(i, i)
    
    val it = sll.iterator
    while(it.hasNext) {
      println(it.next)
    }
    val it2 = sll.iterator
    it2.foreach { x => println(x) }
  }
  
}
*/