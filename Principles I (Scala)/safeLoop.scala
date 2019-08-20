import io.StdIn._

def safeRead():Double = {
	println("Enter a number greater than zero:")
	var d = readDouble
	while (d <= 0) {
	  println("Sorry, that number was negative")
	  d = readDouble
  }
  d
}

println (safeRead)
