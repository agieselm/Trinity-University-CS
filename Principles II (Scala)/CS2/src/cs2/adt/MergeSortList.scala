/*
package cs2.algorithms

/**
 * @author agieselm
 */
object MergeSortList {
  type SLL[A] = cs2.adt.SingleLinkList[A]
  
  
  def mergeLists[A <% Ordered [A]](left:SLL[A], right:SLL[A]):SLL[A] = {
    val result = new SLL[A]()
    while(!left.isEmpty && !right.isEmpty) {
    if(left.get(0) < right.get(0)) {
      result.insert(0, left.remove(0))
    } else {
      result.insert(0, right.remove(0))
      }
    }
    while(!left.isEmpty) { result.insert(0, left.remove(0)) }
    while(!right.isEmpty){ result.insert(0, right.remove(0))}
    val rev = new SLL[A]
    while(!result.isEmpty) { rev.insert(0, result.remove(0)) }
    rev
  }
  
  def splitList[A](lst:SLL[A]): (SLL[A], SLL[A]) = {
    val one = new SLL[A]()
    val two = new SLL[A]()
    var addToOne = true
    while(!lst.isEmpty) {
      if(addToOne) one.insert(0, lst.remove(0))
      else two.insert(0, lst.remove(0))
      addToOne = !addToOne
    }
    (one, two)
  }
  
  def mergeSort[A <% Ordered [A]](lst:SLL[A]):SLL[A] = {
    var halves = splitList(lst)
    if(halves._2.isEmpty) halves._1
    else {
      mergeLists(mergeSort(halves._1), mergeSort(halves._2))
    }
  }
}
*/