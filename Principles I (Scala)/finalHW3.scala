import scalafx.Includes._
import scalafx.scene.Scene
import scalafx.application.JFXApp
import scalafx.scene.control._
import scalafx.scene.layout._
import scalafx.event.ActionEvent
import scalafx.scene.input.KeyCode
import scalafx.scene.input.KeyCombination
import scalafx.scene.input.KeyCodeCombination
import scalafx.stage.FileChooser
import scalafx.collections.ObservableBuffer
import scalafx.beans.property.StringProperty
import java.io.PrintWriter

case class Action(dir: String, dest: Int)
case class Room(num:Int, name:String, desc:String, numOfLinks:Int, actions: Array[Action])

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Adventure Room Task Manager"
    scene = new Scene(500,500) {

      val menuBar = new MenuBar
      val fileMenu = new Menu("File")

      val openItem = new MenuItem("Open")
      openItem.accelerator = new KeyCodeCombination(KeyCode.O,KeyCombination.ControlDown)
      openItem.onAction = (e:ActionEvent) => { openFile }

      val saveItem = new MenuItem("Save")
      saveItem.accelerator = new KeyCodeCombination(KeyCode.S,KeyCombination.ControlDown)
      saveItem.onAction = (e:ActionEvent) => { saveFile }

      val exitItem = new MenuItem("Exit")
      exitItem.accelerator = new KeyCodeCombination(KeyCode.X,KeyCombination.ControlDown)
      exitItem.onAction = (e:ActionEvent) => { System.exit(0) }

 def openFile:Unit = {
       val chooser = new FileChooser
       val selected = chooser.showOpenDialog(stage)
       if(selected!=null) {
         val src = io.Source.fromFile(selected)
         val lines = src.getLines
         recipes = Array.fill(lines.next.toInt)(Recipe(
              StringProperty(lines.next),
              List.fill(lines.next.toInt)(Ingredient(StringProperty(lines.next),
              StringProperty(lines.next))),
               {
                var dir = ""
                var line = lines.next
                while(line!=".") {
                  dir += (if(dir.isEmpty) "" else "\n")+line
                  line = lines.next
               }
                StringProperty(dir)
               }
        ))
        src.close()
        recipeList.items = ObservableBuffer(recipes.map(_.name.apply):_*)
        recipeList.selectionModel.apply.selectFirst
        bindRecipeFields(recipes.head)
        }
      }

   }
  }
 }

app.main(args)
