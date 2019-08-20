import io.StdIn._

println ("Enter height of truck")
val height = readLine 

println ("Enter height of first bridge")
val b1 = readLine

println ("Enter height of second bridge")
val b2 = readLine

println ("Enter height of third bridge")
val b3 = readLine

if (height<b1 && height<b2 && height<b3){

	println("You may proceed")
}else{
	println("You must detour")
}

