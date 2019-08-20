package cs2.parallel

import java.util.concurrent._
import cs2.util.TimeLogger

/**
 * @author agieselm
 */


object ExecutorExamples {
  
  val log = new TimeLogger

  def simpleExecutorServiceExample(nThreads:Int) {
    val service = Executors.newCachedThreadPool
    val futures = Array.tabulate(nThreads)(x => service.submit(new Callable[Int] {
      def call():Int = {
        var counter = 0
        for(i <- 1 to 1000) counter += 1
        counter
      }
    }))
    println(futures.map(_.get).sum)
    service.shutdown
  }
  
  def factorialExecutor(n:BigInt, nThreads:Int):BigInt = {
    val service = Executors.newCachedThreadPool  
    val futures = Array.tabulate(nThreads)(x => service.submit(new Callable[BigInt] {
      def call():BigInt = (BigInt(x+1) to n by nThreads).product
    }))
    val ret = futures.map(_.get).product
    service.shutdown
    ret
  }
  
  def factRecurse(n:BigInt):BigInt = if(n<=0) 1 else n*factRecurse(n-1)
  def factLoop(n:BigInt):BigInt = {
    var res = BigInt(1)
    for(i <- BigInt(1) to n) res *= i
    res
  }
  def factCollect(n:BigInt):BigInt = (BigInt(1) to n).product
  def factParCollect(n:BigInt):BigInt = (BigInt(1) to n).par.product
  
  /*************************/
  
  def main (args:Array[String]) {
    val amt = 50
    
    simpleExecutorServiceExample(5)
    
    log.reset
    var res:BigInt = factRecurse(amt)
    log.logTime("factRecurse(" + amt + ")")
    if(amt < 500) println(res)

    log.reset
    res = factLoop(amt)
    log.logTime("factLoop(" + amt + ")")
    if(amt < 500) println(res)
    
    log.reset
    res = factCollect(amt)
    log.logTime("factCollect(" + amt + ")")
    if(amt < 500) println(res)
    
    
    var nThreads = 1
    while(nThreads < 512) {
      log.reset
      res = factorialExecutor(amt,nThreads)
      log.logTime("factorialExecutor(" + amt + "," + nThreads + ")")
      if(amt < 500) println(res)
      nThreads *= 2
    }
    
    log.reset
    res = factParCollect(amt)
    log.logTime("factParCollect(" + amt + ") time")
    if(amt < 500) println(res)
  }
}