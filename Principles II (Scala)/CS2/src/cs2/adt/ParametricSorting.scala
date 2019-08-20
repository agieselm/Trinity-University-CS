package cs2.ParametricSorting

/**
 * @author agieselm
 */
/*object ParametricSorting {
  
  def bubbleSort[A <: Ordered[A]](a:Array[A]) {
    for(i <- 0 until a.length) {
    for(j <- 0 until a.length-1) {
      if(a(j + 1) < a(j)) {
      val tmp = a(j)
      a(j) = a(j + 1)
      a(j + 1) = tmp
     }
    }
  }
}
 
    //def bubbleSortCurried[A](a:Array[A])(lt:(A,A)=>Boolean) {
     // for(i <- 0 until a.length-1) {
      for(j <- 0 until a.length-1) {
        if(a(j + 1) < a(j)) {
        val tmp = a(j)
        a(j) = a(j + 1)
        a(j + 1) = tmp

  def main(args:Array[String]) {
    val arr:Array[Double] = Array.fill(10)(math.random)
    println(arr.mkString)
    bubbleSort(arr)
    println(arr.mkString(","))
  
    val b:Array[Char] = Array.fill(10)(scala.util.Random.nextPrintableChar)
    println(b.mkString(","))
    bubbleSort(b)
    println(b.mkString(","))
  }
  
  */