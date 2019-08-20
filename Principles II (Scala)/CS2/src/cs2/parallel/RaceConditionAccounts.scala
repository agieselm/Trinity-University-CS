package cs2.parallel

class Account(var bal:Int) {
  
  def deposit(amt:Int) {
    assert(amt > 0)
    bal += amt
    val oldBal = bal
    val newBal = oldBal + amt
    bal = newBal
  }
  
  def withdraw(amt:Int) {
    assert(amt > 0 && amt <= bal)
    val oldBal = bal
    val newBal = oldBal - amt
    bal = newBal
  }
}

/**
 * @author agieselm
 */
object RaceConditionAccounts {
  
  class Lock
  val lock = new Lock
  
  def depositWithdrawThreads() {
    val acct = new Account(5000)
    for(i <- 0 until 10000) acct.deposit(1)
    for(i <- 0 until 10000) acct.withdraw(1)
    println(acct.bal)
    
    val threads:Array[java.lang.Thread] = 
      Array.tabulate(10)(i => {
        new java.lang.Thread {
          override def run() {
            for(i <- 0 until 10000) lock.synchronized { acct.deposit(1) }
            for(i <- 0 until 10000) lock.synchronized { acct.withdraw(1)}
          }
        }
      })
    for(t <- threads) t.start
    for(t <- threads) t.join
    println(acct.bal)
  }
  
  def main(args:Array[String]) {
    depositWithdrawThreads
  }
}