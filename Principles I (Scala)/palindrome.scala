import io.StdIn._

def maxRec (lst:List[Double]):Double = {
	if(lst.length==1) lst(0)
	else
	if(lst.length==0) Double.NaN
	else maxRec(lst.tail) max lst.head
}

val lst = List(3,4,-5.4)

println(maxRec(lst))

def minmax(x:Double*) = {
 if (x.length==0) (Double.NaN,Double.NaN)
 else (x.min,x.max)
}
println(minmax(3,2,1,-3.3))

def maxWhile(lst:List[Double]):Double = {
	
	var best:Double = lst(0)
	var rest = lst
	while(rest.length>0){
		best = max(best, rest.head)
		rest = rest.tail
   }
   best
}
