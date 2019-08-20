package cs2.shapes
import cs2.util.Vec2

/**
 * @author agieselm
 */
abstract class Shape {
   def area():Double
   def perimeter():Double
   def toString():String

}
object ShapeTester {
  def main(args:Array[String]) {
    var shapeList = List[Shape]()
    shapeList ::= new Circle(1.5)
    shapeList ::= new Circle(5)
    shapeList ::= new Rectangle(100,200)
    shapeList ::= new Rectangle(new Vec2(0,0), new Vec2(150,20))
    shapeList ::= new Rectangle(new Vec2(100,100), new Vec2(50,200))
    shapeList ::= new Square(30)
    
    shapeList.foreach { x => println(x + ": " + x.area) }
    shapeList.foreach { x => println(x + ": " + x.perimeter) }
  }
}
