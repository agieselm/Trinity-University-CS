package cs2.net

import java.net._
import java.io._
import java.lang.Thread

/**
 * @author agieselm
 */
object ChatServerClient {

  
  def main(args:Array[String]) {
    //args(0) I -- server/client
    //args(1)   -- port number
    //args(2)   -- if client, hostname; if server, nothing
    var sock:Socket = null
    if(args(0) == "server") {
      val serveSock = new ServerSocket(args(1).toInt)
      sock = serveSock.accept
      
    } else if(args(0) == "client") {
      sock = new Socket(args(2), args(1).toInt)
      
    } 
    val writeThread = new Thread {
      override def run() {
        val dos = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream))
        val sis = new DataInputStream(new BufferedInputStream(System.in))
        
        var msg:String = ""
        while(!sock.isClosed && msg != "EXIT\n") {
          msg = ""
          while(!sock.isClosed && sis.available == 0) Thread.sleep(10)
          var c:Char = ' '
          while(!sock.isClosed && sis.available > 0 && c != '\n') {
            val c = sis.readByte.toChar
            msg += c
         }
          println(">>" + msg)
          if(!sock.isClosed) {
          dos.writeChars(msg)
          dos.flush
          }
        }
        sis.close
        dos.close
      }
    }
    writeThread.start
    
    val readThread = new Thread {
      override def run() {
        val dis = new DataInputStream(new BufferedInputStream(sock.getInputStream))
        var msg:String = ""
        while(!sock.isClosed && msg != "EXIT\n") {
          msg = ""
          while(!sock.isClosed && dis.available == 0) Thread.sleep(10)
          var c:Char = ' '
          while(!sock.isClosed && dis.available > 0 && c != '\n') {
            c = dis.readByte.toChar
            msg += c
          }
          println("<<" + msg)
        }
        dis.close
        sock.close
      }
    }
    readThread.start
  }
}