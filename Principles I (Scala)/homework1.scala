import io.StdIn._

println ("Enter Starting Number")
val i = readInt
println ("Enter addend")
val a = readInt
println ("Enter Starting Number")
val d = readInt
println ("Enter Multiplier")
val m = readInt

def succ(i:Int):Int = i+1
def pred(i:Int):Int = i-1

def addInt(i:Int,a:Int):Int = {
	if(a==0) i
	else{
	addInt(succ(i),pred(a))
	}
}

def multInt(d:Int,m:Int):Int = {
	if(m==1)d
	else{
	multInt(addInt(d)*(m-1)
	}
}
println(multInt(d,m))

