package cs2.inclass

import java.io._

/**
 * @author agieselm
 */
object Quiz6 {
  
  def parseBinaryAndPrint(filename:String) {
    
    val dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(filename))))
    
  while (dis.available > 0) {  
    val num = dis.readByte.toInt 
    val c = dis.readByte.toChar //dis.readChar
    
    for(i <- 1 to num) { print(c) }
   }
   dis.close  
  }
  
  def geomSum(n:Int) = {
    var sum = 0.0
    for(i <- 0 to n) {
      sum += 1.0/math.pow(2, i)
    }
    sum
  }
  
  import java.util.concurrent._
  
  def geomSumExecutor(n:Int) = {
    val service = java.util.concurrent.Executors.newCachedThreadPool
    val futures:Array[Future[Double]] = Array.tabulate(n + 1) (i => service.submit(new Callable[Double] {
      def call():Double = {
        return 1.0 / math.pow(2, i)
      }
    }))
    futures.map(_.get).sum
  }
  
  
}