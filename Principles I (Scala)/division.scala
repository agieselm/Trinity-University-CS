import io.StdIn._

println ("Enter First Integer")
val int1 = readInt

println ("Enter Second Integer")
val int2 = readInt

if(int1==0||int2==0||int1%int2==0||int2%int1==0)
	println("yes")
else
	println("no")

