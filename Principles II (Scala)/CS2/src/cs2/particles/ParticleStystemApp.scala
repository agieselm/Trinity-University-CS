package cs2.particles
/*
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.canvas._
import scalafx.animation.AnimationTimer
import scalafx.scene._
import scalafx.scene.canvas._
import scalafx.animation.AnimationTimer
import scalafx.scene.paint.Color
import scalafx.scene.input._
import cs2.util.Vec2


/**
 * @author agieselm
 */
object ParticleStystemApp {
 
  val app = new JFXApp {
    stage = new JFXApp.PrimaryStage {
      title = "lelelelleleleelel!"
      scene = new Scene(600,400) {
        val canvas = new Canvas(600,400)
        canvas.graphicsContext2D
        val g = canvas.graphicsContext2D
        
        //val p:Particle p = new Particle(new Vec2(300,50), new Vec2(0,0.05))
        //p.display(g)
        
       var lps: List[ParticleSystem] = List()
        
        canvas.onMouseClicked = (e:MouseEvent) => {
          lps ::= new ParticleSystem(new Vec2(e.x, e.y))
        }
        
        
        
        g.fillOval(200, 100 , 200, 200)
  
        content = canvas
        
        val timer = AnimationTimer (t => {
          g.clearRect(0, 0, 600, 400)
          for(ps <- lps) {
          ps.addParticle
          ps.applyForce(new Vec2(0,0.1))
          p.timeStep
          p.display(g)
          }})  
        timer.start
        
      }
    }
  }
  
  def main (args:Array[String]) {
    app.main(args)
  }
}
* */
