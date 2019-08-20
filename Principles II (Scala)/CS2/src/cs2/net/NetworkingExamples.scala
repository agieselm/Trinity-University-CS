package cs2.net

import java.net._
import java.io._


/**
 * @author agieselm
 */
object NetworkingExamples {

  def simpleServerSocket() {
    val serveSock = new ServerSocket(50001)
    val sock:Socket = serveSock.accept
    val dos = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream))
    for(i <- 1 to 1000) {
      val r = math.random
      println(">>Sending: " + r)
      dos.writeDouble(r)
    }
    dos.flush
    dos.close
    sock.close
    serveSock.close
    println(">>Closed")
  }
  
  def simpleClientSocket() {
    val sock = new Socket("localhost", 50001)
    val dis = new DataInputStream(new BufferedInputStream(sock.getInputStream))
    while(dis.available == 0) java.lang.Thread.sleep(10)
    while(dis.available > 0) {
      println("<<Recieved: " + dis.readDouble)
      
    }
    dis.close
    sock.close
    println("<<Closed")
  }
  
  def main(args:Array[String]) {
    
//    (new Thread { override def run() { simpleServerSocket }}).start
//    Thread.sleep(1000)
//    (new Thread { override def run() { simpleClientSocket }}).start
    
    val url = new URL("https://new.trinity.edu")
    val bis = new BufferedInputStream(url.openStream)
    while(bis.available > 0) {
      print(bis.read.toChar)
    }
    bis.close
  }
  
}