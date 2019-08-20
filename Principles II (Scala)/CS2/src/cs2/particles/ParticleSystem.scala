package cs2.particles
/*
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.paint.Color

/**
 * @author agieselm
 */
class ParticleSystem(private var origin:Vec2)
  {protected var parts = List[Particle]()
  
  def addParticle() {
        parts ::= new RoundParticle(new Vec2(origin), 
        new Vec2(math.random*-0.5, math.random*-0.5),
        Color.Red, 15)
  }
  def display(g:GraphicsContext) {
    parts.foreach  (x => x.display(g))
}
  def timeStep() {
    for(x <- parts) {
      x.timeStep
     }
    }
  
   def applyForce(acc:Vec2) {
    parts.foreach(x => x.applyForce(acc))
   }
  
  
 }
*/