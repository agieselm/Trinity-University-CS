import io.StdIn._

def safeRead():Double = {

	print("Enter a number greater than zero: ")
	var d:Double = try{
	  readDouble
	}catch{
	  case e:NumberFormatException =>{println("invalid"); d =  safeRead}
	}
	if (d<= 0) {
	println ("Sorry, that number was negative")
	d = safeRead
 }
 d
}
