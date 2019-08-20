import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape._
import scalafx.scene.input.MouseEvent
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import scalafx.animation.AnimationTimer
import scala.util.Random

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title="Draw";
    scene=new Scene(500,500){
      var circle = Circle(21, 21, 20)
      val rectangle = Rectangle(200,200,50,50)
      content = List(circle,rectangle)
      onMouseMoved = (e:MouseEvent)=>{ 
	circle.centerX=e.x
	circle.centerY=e.y
      }
      val r=new Random
      val timer:AnimationTimer = AnimationTimer(t=>{
      val deltax= 6.0*(if (rectangle.x.apply == 500)-1 else if (r.nextBoolean)1 else -1)
      val deltay= 6.0*(if (rectangle.y.apply == 500)-1 else if (r.nextBoolean)1 else -1)
	rectangle.x=rectangle.x.apply + deltax
	rectangle.y=rectangle.y.apply + deltay
      
     val clear = Shape.intersect(rectangle, circle) .boundsInLocal.apply.isEmpty
     if (!clear){
	println("You Lose!")
	timer.stop 
      }
      
      })
     timer.start 
    }
  }
}
app.main(args)     
