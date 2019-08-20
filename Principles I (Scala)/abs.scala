import io.StdIn._

println("Enter a number")
val x = readDouble

def abs(x:Double):Double = math.sqrt(x*x)

	if (x>0){
	x
}
	else{
	-x
}
println(abs(x))



