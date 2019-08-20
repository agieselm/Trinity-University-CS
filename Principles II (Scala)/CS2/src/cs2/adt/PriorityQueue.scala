package cs2.adt

/**
 * @author agieselm
 */
trait PriorityQueue[A] {
  def add(elem:A)
  def get():A
  def peek():A
  def isEmpty():Boolean
}