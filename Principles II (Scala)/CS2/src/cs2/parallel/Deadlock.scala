package cs2.parallel

import java.lang.Thread

/**
 * @author agieselm
 */
object Deadlock {
  class Lock
    
    
    val lock1 = new Lock
    val lock2 = new Lock
    
    def funcA {
      lock1.synchronized {
        println("A1")
        
        lock2.synchronized {
          println("A2")
        }
      }
    } 
    
    def funcB {
      lock2.synchronized {
        println("B2")  
        
        lock1.synchronized {
          println("B1")
        }
      }
    }
    
    def tryToDeadLock() {
      val athreads = Array.fill(100)(new Thread {
        override def run() { funcA }
      })
      
      val bthreads = Array.fill(100)(new Thread {
        override def run() { funcB  }
      })
      
      for(i <- 0 until 100) {
        athreads(i).start
        bthreads(i).start
      }
    }
    
    def simpleWaitNotify() {
      val handOff:Array[Boolean] = Array.fill(10)(false)
      val threads:Array[Thread] = Array.tabulate(10)(x => new Thread {
        override def run() {
          println("Start Thread" + x)
          for(y <- 1 to 5) lock1.synchronized {
            while (!handOff(x)) { lock1.wait }
            Thread.sleep(scala.util.Random.nextInt(1000))
            println("Thread" + x + ": Iteration" + y)
            handOff(x) = false
            handOff((x + 1)%10) = true
            lock1.notifyAll
            
          }
        }
      })
      threads.foreach { x => x.start }
      Thread.sleep(1000)
      handOff(0) = true
      lock1.synchronized { lock1.notifyAll }
    }
    
    def main(args:Array[String]) {
      //tryToDeadLock
      simpleWaitNotify
    
  }
}