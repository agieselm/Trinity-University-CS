import io.StdIn._
import io.Source
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.control._
import scalafx.event.ActionEvent
import scalafx.scene.layout._

val src=Source.fromFile("/users/mlewis/CSCI1320-F15/SanAntonioTemps.csv").getLines

src.next
src.next

case class TempRecord(day:Int,jd:Int, month:Int,state_id:String, 
  year:Int, prcp:Double, tave:Double,tmax:Double,tmin:Double)

val a2d:Array[Array[String]]=src.map(_.split(",") ).toArray

val tempRecords=for (a<- a2d)yield{  
TempRecord(a(0).toInt,a(1).toInt,a(2).toInt,a(3),a(4).toInt,a(5).toDouble,a(6).toDouble,a(7).toDouble,a(8).toDouble)
}

val app=new JFXApp{
  stage=new JFXApp.PrimaryStage{
    title="Temperature"
    scene=new Scene(600,400){

	val listview=new ListView(tempRecords.map(record=>record.year).distinct )
	
	val lbl= new Label("Max Temperature:")
	val lbl2=new Label("0")
	val pane=new BorderPane
	pane.left=listview
	pane.center=new HBox(lbl,lbl2)
	
	content=pane
	listview.selectionModel.apply.selectedItem.onChange {
	  val year=listview.selectionModel.apply.selectedItem.value
	  lbl2.text=tempRecords.filter(_.year == year).map(_.tmax).max.toString
	}
    }
  }
}
app.main(args)
