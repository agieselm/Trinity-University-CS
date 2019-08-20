package cs2.adt

/**
 * @author agieselm
 */
abstract class Queue[A] {
  def enqueue(elem:A)
  def dequeue():A
  def peek():A
  def isEmpty():Boolean
}