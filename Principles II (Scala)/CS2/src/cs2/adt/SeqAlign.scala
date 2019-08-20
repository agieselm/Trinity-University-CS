package dnaalignment

import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.File
import java.io.FileInputStream
import scala.collection.mutable.Buffer
import java.util.concurrent.Executors
import java.util.concurrent.Callable 

/**
 * @author agieselm	
 */

object SeqAlign {
  
  def executorFind(sFile: Buffer[Char], lFile: Buffer[Char], Threads: Int): Array[Int] = {
    
    val service = Executors.newCachedThreadPool()
    val futures = Array.tabulate(Threads)(x => service.submit(new Callable[Int] {
      
      def call(): Int = { if(sFile == lFile.view(x, x + sFile.size)) x else 0}
    }))
    val result = futures.map(_.get).filterNot(_== 0)
    service.shutdown
    result
  }
  
  def main(args: Array[String]) {
    
    var sBuff = Buffer[Char]()
    var sFile = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(args(0)))))
    
    while(sFile.available() > 0)
        sBuff.append(sFile.readByte.toChar)
        sBuff = sBuff.dropRight(1)
              
        
    var lBuff = Buffer[Char]()
    var lFile = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(args(1)))))
    
    while(lFile.available() > 0)
        lBuff.append(lFile.readByte.toChar)
        lBuff = lBuff.dropRight(1)
        
    executorFind(sBuff, lBuff, lBuff.size - sBuff.size + 1).foreach(println)
  }
}