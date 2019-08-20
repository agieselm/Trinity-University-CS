package cs2.particles
import cs2.util.Vec2
import scalafx.scene.paint.Color
import scalafx.scene.canvas.GraphicsContext

/**
 * @author agieselm
 */

abstract class Particle (protected var pos:Vec2, protected var vel:Vec2, 
                protected var col:Color, protected var rad:Double) {
  
  def this(p:Vec2, v:Vec2) = this(p,v, Color.Red, 8)
  
  def display(g:GraphicsContext) 
 
  
  def timeStep() {
    pos += vel
  }
 
  def applyForce(acc:Vec2) {
    vel += acc
  }
}