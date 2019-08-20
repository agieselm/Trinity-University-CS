import io.StdIn._

println("Enter x-coordinate of circle 1")
val x1 = readDouble

println("Enter y-coordinate of circle 1")
val y1 = readDouble

println("Enter radius of circle 1")
val r1 = readDouble

println("Enter x-coordinate of circle 2")
val x2 = readDouble

println("Enter y-coordinate of circle 2")
val y2 = readDouble

println("Enter radius of circle 2")
val r2 = readDouble

val distance = math.sqrt(math.pow(x2-x1,2) + math.pow(y2-y1,2))

if (distance>r1+r2){
	println("No Overlap!")
}else{
	println("They Touch!")
}
