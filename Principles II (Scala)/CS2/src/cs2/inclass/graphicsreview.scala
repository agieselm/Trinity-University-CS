import scalafx.application.JFXApp
import scalafx.scene._
import scalafx.scene.canvas._
import scalafx.scene.paint._

def drawCircle(g:GraphicsContext, x:Double, y:Double, r:Double) {
   g.strokeOval(x-r,y-r, r*2,r*2)
   if(r>2){ 
   drawCircle(g, x-r, y, r/2)
   drawCircle(g, x+r, y, r/2)
   drawCircle(g, x, y+r, r/2)
   drawCircle(g, x, y-r, r/2)
 }
}
val app = new JFXApp {
   stage = new JFXApp.PrimaryStage {
      title = "Fractal Circles"
      scene = new Scene(600,600) {
	val canvas = new Canvas(600,600)
	val g = canvas.graphicsContext2D
	g.fill = Color.rgb(200, 100, 20)
	
	//g.fillRect(100,100, 300,100)	
	//g.strokeOval(300,300, 300,300)
	drawCircle(g, 300,300, 150)
	content = canvas

      }
   }
}
app.main(args)
