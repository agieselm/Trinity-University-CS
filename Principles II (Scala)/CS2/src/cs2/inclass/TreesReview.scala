package cs2.inclass

/**
 * @author agieselm
 */
class BinarySearchTree[A <% Ordered[A]] {
  /**
   *  I'm using a "smarter" Node class here, rather than the strategy used in the
   *  textbook. In both cases, recursion is used to help out at various points in
   *  dealing with the BST. In the textbook, additional "helper" functions are
   *  included within the main BST methods; here, these "helper" functions often
   *  become methods of the Node class below.
   */
  protected class Node(var data: A, var left: Node, var right: Node) {
    //Locates a particular Node by value and returns it, null if not found 
    def findChild(elem: A): Node = {
      if (!(data < elem) && !(elem < data)) this
      else {
        if (elem < data) if (left != null) left.findChild(elem) else null;
        else if (right != null) right.findChild(elem) else null;
      }
    }

    //Adds an element as a child somewhere below the calling node 
    def insertBelow(elem: A) {
      if (elem < data) if (left == null) left = new Node(elem, null, null) else left.insertBelow(elem);
      else if (right == null) right = new Node(elem, null, null) else right.insertBelow(elem);
    }

    //Locates the maximum child below a particular node (by traversing downward
    //through the tree), and removes it; repairing the damage while traversing
    //back upward. This is helpful with removal (below).
    def passUpMaxChild(): (A, Node) = {
      if (right == null) (data, left)
      else {
        val (d, n) = right.passUpMaxChild;
        right = n;
        (d, this);
      }
    }
    
    def findMax():A = if(right == null) data else right.findMax
    def findMin():A = if(left == null) data else left.findMin
   
    def findMinOnRight():A ={
      if(right == null) data
      else{
        var rover = root.right
          while(rover.left != null){
          rover = rover.left  
        }
        rover.findMinOnRight
      }
    }
    
    def findMaxOnLeft():A = {
      if(left == null) data
      else{
        var rover = root.left
          while(rover.right != null){
          rover = rover.right  
        }
        rover.findMaxOnLeft
      }
    }
    //Attempts to remove an element at a particular position or below it within
    //the overall tree structure
    def removeBelow(elem: A): Node = {
      //If this is the node to be removed 
      if (!(data < elem) && !(elem < data)) {
        //First, see if a child can be directly promoted
        if (left == null) right
        else if (right == null) left
        //If there are two valid children, then start the process of promoting
        //the largest value below the left child -- this is guaranteed to be
        //a new valid value to go at the current location.
        else {
          val (d, n) = left.passUpMaxChild
          data = d
          left = n
          this
        }
      } else {
        //If this isn't the node to be removed, recurse downward
        if (elem < data && left != null) { left = left.removeBelow(elem) }
        else if (right != null) { right = right.removeBelow(elem) }
        this
      }
    }
    override def toString(): String = {
      "(" + data.toString() + " -> " +
        (if (left == null) "()" else left.toString) + "-" +
        (if (right == null) "()" else right.toString) + ")"
    }
  }
  
  /***************************************************
   *  With our "smart" Node, the heart of the BST class becomes much simpler:
   */
  
  protected var root: Node = null
  def isEmpty = root == null

  def contains(elem: A) = root.findChild(elem) != null
  def insert(elem: A) {
    if (root == null) root = new Node(elem, null, null)
    else { root.insertBelow(elem) }
  }
  def remove(elem: A) { root = root.removeBelow(elem) }

  override def toString(): String = if (root == null) "()" else root.toString
  
  //Other "useful" functions
  def peekMax():A = root.findMax
  def peekMin():A = root.findMin
  def peekrightMin():A = root.findMinOnRight
  def peekleftMax():A = root.findMaxOnLeft
  def removeMax():A = {
    val mx = peekMax
    remove(mx)
    mx
  } 
  
  /****************************************************
   * Below are versions of the above code written as though we had "dumb nodes" (DN)
   */
  def containsDN(elem:A):Boolean = {
    var curr = root
    var found = false
    while (!found && curr != null) {
      if(!(elem < curr.data) && !(curr.data < elem)) found = true
      else if(elem < curr.data) curr = curr.left
      else curr = curr.right
    }
    found
  }
  def insertDN(elem:A) {
    if(root == null) root = new Node(elem,null,null)
    else {
      var curr = root
      while (curr != null) {
        //If need to traverse towards the right
        if (curr.data > elem) {
          if(curr.right == null) { curr.right = new Node(elem,null,null); curr = null }
          else curr = curr.right
        } else if (curr.data < elem) { //traverse left
          if(curr.left == null) { curr.left = new Node(elem,null,null); curr = null }
          else curr = curr.left
        }
      }
    }
  }
  def removeDN(elem:A) {
    var target = root.findChild(elem)
    if(target != null) {
      var maxChildValue = target.data
      var curr:Node = target.left
      var parent:Node = target
      while(curr != null) {
        println(curr.data + ": " + root)
        if(curr.right == null) {
          maxChildValue = curr.data
          if(parent == target) parent.left = curr.left 
          else parent.right = curr.left
          curr = null
          parent = null
        } else {
          parent = curr
          curr = curr.right
        }
      }
      target.data = maxChildValue
    }
  }
  
  /*********************************************************
   * Traversals tend to be easy to do recursively, or with
   * the aid of some familiar data structures, we'll do 
   * it both ways for several traversals that print out
   */
  def printPreOrderTraversalRecursive() {
    def processNode(curr:Node) {
      print(curr.data + ", ")
      if(curr.left != null)   processNode(curr.left)
      if(curr.right != null)  processNode(curr.right)
    }
    processNode(root); println()
  }
  def printInOrderTraversalRecursive() {
    def processNode(curr:Node) {
      if(curr.left != null)   processNode(curr.left)
      print(curr.data + ", ")
      if(curr.right != null)  processNode(curr.right)
    }
    processNode(root); println()
  }
  def printPostOrderTraversalRecursive() {
    def processNode(curr:Node) {
      if(curr.left != null)   processNode(curr.left)
      if(curr.right != null)  processNode(curr.right)
      print(curr.data + ", ")
    }
    processNode(root); println()
  }
  
//  def printPreOrderTraversalStack() {
//    val stk = new LinkedStack[Node]
//    stk.push(root)
//    while(!stk.isEmpty) {
//      val curr = stk.pop
//      if(curr != null) {
//        print(curr.data + ", ")
//        stk.push(curr.right)
//        stk.push(curr.left)
//      }
//    }
//    println
//  }
}

/**
 * A simple tester for the BinarySearchTree class
 */
object BSTTester extends App {
  override def main(args: Array[String]) {
    val bst = new BinarySearchTree[Int]
//    bst.insert(10)
//    bst.removeDN(10)
//    println(bst)
//    bst.insert(5)
//    bst.insert(15)
//    println("insert 10,5,15: " + bst)
//    bst.insert(20)
//    bst.insert(13)
//    bst.insert(3)
//    bst.insert(7)
//    bst.insert(14)
//    bst.insert(12)
//    bst.insert(22)
//    println("insert 20,13,3,7,14,12,22: " + bst)
//    bst.removeDN(7)
//    println("remove 7: " + bst)
//    bst.removeDN(4)
//    println("remove 4: " + bst)
//    bst.removeDN(15)
//    println("remove 15: " + bst)
//    bst.removeDN(10)
//    println("remove 10: " + bst)
    bst.insert(10)
    bst.insert(2)
    bst.insert(15)
    bst.insert(4)
    bst.insert(12)
    bst.insert(1)
    bst.insert(17)
    bst.insert(11)
    println(bst)
    println("max: " + bst.peekMax)
    println("min: " + bst.peekMin)
    println("rightMin: " + bst.peekrightMin)
    println("leftmax: " + bst.peekleftMax)
  //  println("test: " + bst)
    
//    bst.printPreOrderTraversalRecursive
//    bst.printInOrderTraversalRecursive
//    bst.printPostOrderTraversalRecursive
//    bst.printPreOrderTraversalStack
    
  }
}