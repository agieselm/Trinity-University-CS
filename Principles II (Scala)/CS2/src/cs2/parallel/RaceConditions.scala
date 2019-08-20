/*package cs2.parallel

/**
 * @author agieselm
 */
import java.lang.Thread

class Account(private var bal:Int = 0) {
  def balance = bal
  def deposit(amt:Int)  {
    assert(amt>0)
    //bal += amt
    val oldBal = balance
    val newBal = oldBal + amt
    bal = newBal
  }
  def withdraw(amt:Int)  {
    assert(amt>0 && amt<=bal)
    //bal -= amt
    val oldBal = balance
    val newBal = oldBal - amt
    bal = newBal
  }
}

object RaceConditionAccounts {
  
  def depositWithdrawlThreads() {
    val acct = new Account(5000)
    for(i <- 0 until 10000) acct.deposit(1)
    for(i <- 0 until 10000) acct.withdraw(1)
    println(acct.balance)
    
    val threads = Array.tabulate(10)(i => new Thread {
      override def run() {
        for(i <- 1 to 10000) acct.deposit(1)
        for(i <- 1 to 10000) acct.withdraw(1)
      }
    })
    for(t <- threads) t.start
    for(t <- threads) t.join
    println(acct.balance)
  }
  
  def simpleRaceConditionExample(mx:Int) {
    var counter = 0
    val threads:Array[Thread] = Array.fill(10)(new Thread {
      override def run() {
        for(i <- 1 to mx/10) { counter += 1 }
      }
    })
    threads.foreach(_.start)
    threads.foreach(_.join)
    println(counter)
  }
  
  def simpleSynchronizedExample(mx:Int) {
    var counter = 0
    val threads:Array[Thread] = Array.fill(10)(new Thread {
      override def run() {
        for(i <- 1 to mx/10) RaceConditionAccounts.synchronized { counter += 1 }
      }
    })
    threads.foreach(_.start)
    threads.foreach(_.join)
    println(counter)
  }
  
  def simpleCustomThreadExample(mx:Int) {
    class CounterThread extends Thread {
      var counter:Int = 0
      override def run() {
        for(i <- 1 to mx/10) counter += 1
      }
    }
    val threads = Array.fill(10)(new CounterThread)
    threads.foreach(_.start)
    threads.foreach(_.join)
    val total = threads.map(x => x.counter).sum
    println(total)
  }
  
  def main(args:Array[String]) {
    depositWithdrawlThreads
    
    var time = System.nanoTime
    simpleRaceConditionExample(1000000)
    println("Unsync'd time = " + ((System.nanoTime - time)/1e9))
    
    time = System.nanoTime
    simpleSynchronizedExample(1000000)
    println("Sync'd time = " + ((System.nanoTime - time)/1e9))
    
    time = System.nanoTime
    simpleCustomThreadExample(1000000)
    println("Custom time = " + ((System.nanoTime - time)/1e9))
  }
}
*/