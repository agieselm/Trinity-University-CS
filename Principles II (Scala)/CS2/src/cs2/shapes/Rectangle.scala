package cs2.shapes
import cs2.util.Vec2

/**
 * @author agieselm
 */
class Rectangle(var l:Double, var w:Double) extends Shape() {
  
  def this(t:Vec2, b:Vec2) = {
    this(math.abs(b.x - t.x), math.abs(b.y - t.y))
  }
  
  override def area:Double = (l * w)
  
  override def perimeter:Double = (l + l + w + w)
  
  override def toString:String = ("Rectangle")
  
}