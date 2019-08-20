import io.StdIn._

def fact(n:Int):Int = if (n==1) 1 else n*fact(n-1)

def fact_match (n:Int):Int = {
	println("fact("+n+") called.")
	val r = if(n==1) 1 else n* fact_match(n-1)
	println("fact("+n+") finished with the return value "+r)
	r
}

fact_match(10)


