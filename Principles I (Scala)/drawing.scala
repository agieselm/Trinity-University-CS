import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.shape._
import scalafx.scene.text.Text

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Draw";
    scene = new Scene (500,500){
      var lines = List[Polyline]()

      onMousePressed = (e:MouseEvent)=>{
	lines ::= Polyline()
	content +=lines.head
	lines.head.points ++= List(e.x,e.y)
      }
      onMouseDragged = (e:MouseEvent)=>{
	lines.head.points ++= List(e.x,e.y)
      }
      onMouseReleased = (e:MouseEvent)=>{
	lines.head.points ++= (e.x,e.y)
     }
   }
  }
}
