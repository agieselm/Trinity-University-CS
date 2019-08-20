/*
package cs2.adt

/**
 * @author agieselm
 */
class IterableDoubleLinkList[A : Manifest] extends DoubleLinkList[A] {
  
class BidirectionalIterator extends scala.collection.Iterator[A] {
      
    var current = end.next
      def next():A = {
        val tmp:A = current.data
        current = current.next
        tmp
          
      }
      
      def hasNext():Boolean = (current != end)
      def previous():A = {
        val tmp = current.data
        current = current.prev
        tmp
      }
      def hasPrevious():Boolean = (current != end)
  }
      def iterator() = new BidirectionalIterator
}
 */