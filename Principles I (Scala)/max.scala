import io.StdIn._

val a = readDouble
val b = readDouble
val c = readDouble

def max(a:Double,b:Double):Double = if (a>b) a else b
def max3(a:Double,b:Double,c:Double):Double = {
	max(max(a,b), c)
}
println (max3(a,b,c))

