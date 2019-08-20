/** HW0 Part 2 - CSCI1320
 *  Name: Austin Gieselman
 *  Pledge: Austin Gieselman
 */


class Complex (private val a:Double, private val b:Double){
	
override def toString():String = {
   a.toString +" + "+ b.toString + "i"
}

	def real():Double = {this.a}
	def imag():Double = {this.b}
	
	def +(rhs:Complex):Complex = {new Complex (a + rhs.a, b + rhs.b)}
	def -(rhs:Complex):Complex = {new Complex (a - rhs.a, b - rhs.b)}
	
	def *(rhs:Complex):Complex = {new Complex (((a * rhs.a) - (b * rhs.b)) , ((a * rhs.b) + (b * rhs. a)))}
	def /(rhs:Complex):Complex = {
	   val d = (math.pow(rhs.a,2) + math.pow(rhs.b,2))	   
	   new Complex (((a * rhs.a / d) + (b * rhs.b / d)) ,(( b * rhs.a / d) - (a * rhs.b / d)))
	}
	def conjugate():Complex = {new Complex (a,-b)}	
}
	
object hw0 {
	/** Use this main function to create a tester for your Complex number class
	 *  Be sure to test construction of Complex objects, calling each of the methods
	 *  defined above, and the use of "corner case" values
	 */
	def main(args:Array[String]) {

	val x = new Complex (1, 2)
	val y = new Complex (3, 4)

	println(x)
	println(y)
	println(x + y)
	println(x - y)
	println(x * y)
	println(x / y)
	println(x.conjugate)
	println(y.conjugate)	
	}
}

