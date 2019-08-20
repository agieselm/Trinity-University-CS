// Do NOT change this package
package cs2.adt

// Do NOT modify the following trait
trait Deque[A] {
  def append(elem: A)
  def prepend(elem: A)
  def front(): A
  def back(): A
  def peekFront(): A
  def peekBack(): A
  def isEmpty(): Boolean
}

//Make ALL of your changes/additions within the following class, TheDeque
//Note that since TheDeque extends Deque, you MUST provide implementations
//for all of the abstract methods from Deque

//Do NOT change the name of this class
class TheDeque[A] extends Deque[A] {
  
  private class Node(var data: A, var next: Node)
  
  private var head: Node = null
  private var tail: Node = null

  def append(elem: A) = {
    if (head == null) {
      head = new Node(elem, null)
      tail = head
    } else {
      tail.next = new Node(elem, null)
      tail = tail.next
    }
  }

  def prepend(elem: A) = {
    if (tail == null) {
      tail = new Node(elem, null)
      head = tail
    } else {
      head.next = new Node(elem, null)
      head = head.next
    }
  }

  def front(): A ={
    val del = head.data
    head = head.next
    if(head == null) tail = null
    del
  }

  def back(): A = {    
    val del = tail.data
    tail = tail.next
    if(tail == null) head = null
    del
  }

  def peekFront(): A = {
    head.data
  }
  def peekBack(): A = {
    tail.data
  }
  def isEmpty(): Boolean = (head == null) && (tail == null)
}

//The following code is a reasonable tester, but other code will be used to test your work.
//If you wish to add your own testing code, or to modify this tester, you may feel free
object DequeTester {
  def main(args: Array[String]) {
    val d = new TheDeque[Char]()
    //Tests on an empty Deque
    println("initially empty = " + d.isEmpty)

    //Tests on a Deque of length 1 -- all combinations of ap/pre-pend and front/back
    d.append('!')
    println("After appending !, isEmpty = " + d.isEmpty)
    println("Get appended ! from the front = " + d.peekFront + " & " + d.front)
    println("After taking appened ! from the front, isEmpty = " + d.isEmpty)

    d.prepend('#')
    println("After prepending #, isEmpty = " + d.isEmpty)
    println("Get prepended # from the front = " + d.peekFront + " & " + d.front)
    println("After taking prepended # from the front, isEmpty = " + d.isEmpty)

    d.append('@')
    println("After appending @, isEmpty = " + d.isEmpty)
    println("Get appended @ from the back = " + d.peekBack + " & " + d.back)
    println("After taking appened @ from the back, isEmpty = " + d.isEmpty)

    d.prepend('$')
    println("After prepending $, isEmpty = " + d.isEmpty)
    println("Get prepended $ from the back = " + d.peekBack + " & " + d.back)
    println("After taking prepended $ from the back, isEmpty = " + d.isEmpty)

    //Tests on adding many elements to front and back
    for (x <- 'A' to 'Z') {
      if (x % 2 == 0) d.append(x)
      else d.prepend(x)
    }
    println("emtpy after ap/pre-pend = " + d.isEmpty)
    var i = 0
    while (!d.isEmpty) { println("front " + i + " = " + d.peekFront + " & " + d.front); i += 1 }
    println("empty after using front = " + d.isEmpty)

    for (x <- 'A' to 'Z') {
      if (x % 2 == 0) d.append(x)
      else d.prepend(x)
    }
    println("emtpy after ap/pre-pend = " + d.isEmpty)
    var j = 0
    while (!d.isEmpty) { println("back " + j + " = " + d.peekBack + " & " + d.back); j += 1 }
    println("empty after using back = " + d.isEmpty)

  }
}


