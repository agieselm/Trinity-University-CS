package cs2.parallel

/**
 * @author agieselm
 */
object Multithreading {
  
  def countDownGetName():String = {
    val thread = new java.lang.Thread {
      override def run() {
        for(i <- 0 until 5) {
          println(i)
          java.lang.Thread.sleep(1000)
          }
        println("Time's up!")
        sys.exit
        }
      } 
      thread.start    
    
    println("Enter your name:")
    var name = readLine
    thread.stop
    name
  }
  
  def betterCountDownGetName():String = {
    
    var name = "Person who is too good to type in their own name"
    var timesUp = false
    var haveAnswer = false
    
    val thread = new java.lang.Thread {
      override def run() {
        var ctr = 5
        while(!haveAnswer && ctr > 0) {
          println(ctr)
          java.lang.Thread.sleep(1000)
          ctr -= 1
        }
        timesUp = true
        
      }
    }
    thread.start
    
    println("Enter your name:")
    while(!timesUp && !Console.in.ready) {
      java.lang.Thread.sleep(10) 
    }
    if(!timesUp) {
      name = readLine
      haveAnswer = true
    }
    name
  }
  
  def simpleCounterThread() {
    var ctr = 0
    class CountThread extends java.lang.Thread {
      override def run() {
        for(i <- 0 until 100000) ctr += 1
      }
    }
    val threads = Array.fill(10)(new CountThread)
    threads.foreach { x => x.start }
    threads.foreach { x => x.join }
    println(ctr)
    
  }
  
  def main(args:Array[String]) {
    simpleCounterThread
    //val n = betterCountDownGetName
    //println("Hello, " + n)
  }
  
}