/**
 * The Vec2 class should define a 2-dimensional geometric vector and the operations
 * that can be performed on 2D vectors
 * DO NOT ALTER THE NAMES OF THE CLASS, FIELDS, OR METHODS
 */
class Vec2 (var x:Double, var y:Double) {	
	//Provided toString method simplifies printing, e.g. println(vec.toString) OR println(vec)
	//DO NOT ALTER THIS METHOD -- IT IS USED FOR AUTOMATED GRADING
    override def toString():String = "("+x+","+y+")"
	
	//Methods for addition and subtraction of vectors
	def + (other:Vec2):Vec2 = { new Vec2 (x + other.x , y + other.y) }
	def - (other:Vec2):Vec2 = { new Vec2 (x - other.x , y - other.y) }

	//Methods for multiplication and division of vectors by a scalar (non-vector)
	def * (scalar:Double):Vec2 = { new Vec2 (x * scalar, y * scalar) }
	def / (scalar:Double):Vec2 = { new Vec2 (x / scalar, y / scalar) }
	
	//Methods to determine the length of a vector (magnitude and length should return the same value)
	def magnitude():Double = { math.sqrt(x * this.x + y * this.y) }
	def length():Double = { math.sqrt(x * this.x + y * this.y) }

	//Method to return a new vector that is in the same direction, but length 1
	def normalize():Vec2 = { new Vec2 (x / this.length , y / this.length) }
	
	//Methods to calculate the dot product, and determine the angle between 2 vectors
	def dot(other:Vec2):Double = { x * other.x + y * other.y }
	def angleBetween(other:Vec2):Double = { this.x * other.x + this.y * other.y }

} 
