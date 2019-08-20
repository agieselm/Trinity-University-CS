/*

package cs2.adt/**

import org.junit._
import org.junit.Assert._
 * @author agieselm
 */
abstract class Stack[A] {
  def push(elem:A)
  def pop():A
  def peek:A
  def isEmpty():Boolean
}

class StackTester {
  var s:Stack[Int] = null

  @Before def initStack() {
    s = new ArrayStack[Int]()
  }
  
  @Test def checkIsEmptyAfterConstruction() {
    s = new ArrayStack[Int]()
    assertTrue(s.isEmpty)
  }

  
  @Test def checkIsNotEmptyAfterPush() {
    s.push(7)
    assertTrue(!s.isEmpty)
  }
  
  @Test def checkPushPeek() {
    s.push(7)
    assertTrue(s.peek == 7)
  }
  
  @Test def checkPushPop() {
    s.push(7)
    val tmp = s.pop
    assertTrue(tmp == 7)
    assertTrue(s.isEmpty)
  }
  @Test def checkPushPeekOnFullStack() {
    for(i <- 0 until 100) {
      s.push(i)
    }
    s.push(7)
    assertTrue(s.peek == 7)
    assertTrue(!s.isEmpty)
    s.pop
    for(i <- 0 until 100) {
      s.pop
    }
    assertTrue(s.isEmpty)
  }
} 
*/