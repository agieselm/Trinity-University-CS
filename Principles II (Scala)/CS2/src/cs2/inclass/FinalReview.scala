package cs2.inclass

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

import scala.collection.mutable.Buffer

/**
 * @author agieselm
 */
class FinalReview {
  
  //Executor Parallel Sum
  def geomSumExecutor(n:Int) = {
    val service = Executors.newCachedThreadPool
    val futures:Array[Future[Double]] = 
      Array.tabulate(n + 1)(x => service.submit(new Callable[Double] {
        def call():Double = {
          return 1.0 / math.pow(2, x)  
          }
        }))
    futures.map(_.get).par.sum 
    
  }
  
  //Manual Thread parallel sum
  import java.util.concurrent._
  class Lock
  val lock = new Lock

  def geoSumThreads(n: Int) = {
    var sum = 0.0
    val t1 = new Thread {
      override def run() {
        for (i <- 0 until n) {
          val tmp = 1.0 / math.pow(2, i)
          lock.synchronized {
            sum += tmp
          }
        }
      }
    }
    val t2 = new Thread {
      override def run() {
        for (i <- n / 2 to n) {
          val tmp = 1.0 / math.pow(2, i)
          lock.synchronized {
            sum += 1.0 / tmp
          }
        }
      }
    }
    t1.start
    t2.start
    t1.join
    t2.join
    sum
  }

  //Array Tabulate parallel sum
  def geoSumTabulateThreads(n: Int) = {
    var sum = 0.0
    val t1 = Array.tabulate(10)((x: Int) => new Thread {
      override def run() {
        for (i <- x to n by 10) {
          val tmp = 1.0 / math.pow(2, i)
          lock.synchronized {
            sum += tmp
          }
        }
      }
    })
    t1.foreach(_.start)
    t1.foreach(_.join)
    sum
  }
  
  //Read/Write file ex.
  
  def readFromNameFile(filename:String): Buffer[String] = {
    val buf = Buffer[String]()
    try {  
    val dis = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(filename))))
   // val buf = Buffer[String]()   
    while(dis.available > 0) {
      val len = dis.readByte
      var name = ""
      for(i <- 0 until len) {
        name += dis.readByte.toChar
      }
      buf += name
    }
    dis.close
    //buf   
    } catch {
      case ex:FileNotFoundException => { println("file not found") }
      case ex:IOException => { println("IO Exception") }
    }
    buf
  }
  
  //write file ex.
  
  def writeToNameFile(filename:String, names:Buffer[String]) {
  try {  
    val dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filename))))
    for(name <- names) {
      dos.writeByte(name.length)
      dos.writeChars(name)
    }
    dos.close 
    } catch {
      case ex:IOException => println ("oops")
    }
  }
  
  //fizzbuzz ex.
  
  def fizzbuzz() = {
    for(i <- 0 to 100) {
      if(i%3 == 0 && i%5 == 0) println("fizzbuzz")
      else if(i%3 == 0) println("fizz")
      else if(i%5 == 0) println("buzz")
      else println(i)
    }
  }
  
  //priority queue array based
  
  trait PriorityQueue[A] {
  def add(elem:A)
  def get():A
  def peek():A
  def isEmpty():Boolean
}
  
  class arrayPQueue[A : Manifest]  {
    var begin = 0
    var len = 0
    var arr = new Array[A](10)
    
    def add(elem:A) {
      if(len == 0) {
        
      }
    }
  }
  
  
}