/*package cs2.particles
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/**
 * @author agieselm
 */
class Eraser (val pos:Vec2, val rad:Double){ 
  def display(g:GraphicsContext)
    g.stroke = Color.Black
    g.strokeOval(pos.x-rad, pos.y-rad, 2*rad, 2*rad)

}
*/