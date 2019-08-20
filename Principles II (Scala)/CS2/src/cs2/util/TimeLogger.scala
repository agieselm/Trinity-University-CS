package cs2.util

/**
 * @author agieselm
 */
class TimeLogger {
  private var startTime = System.nanoTime
  def reset() { startTime = System.nanoTime }
  def logTime(msg:String):Double = { 
    val timeTaken = (System.nanoTime - startTime) / 1e9
    println(msg + ": " + timeTaken)
    timeTaken
  }
}