package cs2.shapes

/**
 * @author agieselm
 */
class Circle(var r:Double) extends Shape() {
  
  override def area:Double = (math.Pi * r * r)
  
  override def perimeter:Double = (2 * (math.Pi) * r)
  
  override def toString:String = ("Circle")
  
}