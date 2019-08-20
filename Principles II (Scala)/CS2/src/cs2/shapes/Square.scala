package cs2.shapes

/**
 * @author agieselm
 */
class Square(var s: Double) extends Shape {
  
  override def area:Double = (s * s)
  
  override def perimeter:Double = (s + s + s + s)
  
  override def toString:String = ("Square")
}