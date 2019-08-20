package cs2.particles
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/**
 * @author agieselm
 */
class SquareParticle(p:Vec2,v:Vec2) extends Particle(p,v) {
  rad = 6
  
  override def display(g:GraphicsContext) {
    g.fill = col
    g.fillRect(pos.x, pos.y, 2*rad, 2*rad)
  }
  
}