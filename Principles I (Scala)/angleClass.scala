class Angle (private var rad:Double) {
  recenter
  
  def this() = this(0)

  private def recenter() {
   while(rad > 2*math.Pi) rad -= 2*math.Pi
   while(rad < 0) rad += 2*math.Pi
  }
   def radians = rad
   def degrees = rad * 360/ (2 * math.Pi)

   def radians_= (amt:Double) {
     rad = amt
     recenter
  }
   def degrees_= (amt:Double) {
     rad = amt * 2 * math.Pi / 360
     recenter
  }
   override def toString():String = (rad / math.Pi).toString + "pi"

   def + (other:Angle):Angle = new Angle (this.rad + other.rad)
   def +=(other:Angle) { this.rad += other.rad; recenter }

   def unary_-():Angle = { new Angle(-this.rad) }

   def apply ():String = (rad * 180 / math.Pi).toString

   def update(rhs:Double) { this.rad = rhs; recenter }
}

object AngleTester {
   def main(args:Array[String]) {

val a = new Angle(4 * math.Pi)
println(a.radians)
a.radians = math.Pi
println(a.radians)
println(a.degrees)
a.degrees = 45
println(a.radians)
println(a)
val b = new Angle(math.Pi)
val c = a + b
val d = a.+(b)

println(c)
println(c())

c() = math.Pi
println(c)

val e = new Angle ()
println (e)
   
   }
}
