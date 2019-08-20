import io.StdIn._

println ("How old are you?")
val myage = readInt

def parkprice(age:Int):Int = {

	if (age<12){
		20
	}
	else if(age>65){
		15
	}else{
		30
	}
	
}
println(parkprice(myage))

