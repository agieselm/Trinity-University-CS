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

case class Action(name:StringProperty, amount:StringProperty)
case class Room(name:StringProperty, actions:List[Action], directions:StringProperty)

val app = new JFXApp {
  stage = new JFXApp.PrimaryStage {
    title = "Adventure Rooms GUI"
    scene = new Scene(700,700) {
      
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

      fileMenu.items = List(openItem, saveItem, new SeparatorMenuItem, exitItem)

      val roomMenu = new Menu("Room")
      val addItem = new MenuItem("Add")
      addItem.onAction = (e:ActionEvent) => { addRoom }
      val removeItem = new MenuItem("Remove")
      removeItem.onAction = (e:ActionEvent) => { removeRoom }
      roomMenu.items = List(addItem, removeItem)
   
      menuBar.menus = List(fileMenu, roomMenu)

      //Recipe List
      var rooms = Array(Room(StringProperty("Starting Room"), List(Action(StringProperty("Make a Room"), 
        StringProperty("1 Room"))), StringProperty("Description")))

      val roomList = new ListView(rooms.map(_.name.apply))
      var selectedRoom:Option[Room] = None
      roomList.selectionModel.apply.selectedIndex.onChange {
	val index = roomList.selectionModel.apply.selectedIndex.apply
	if(index>=0) bindRoomFields(rooms(index))
      }


      //Ingredients Stuff
      var selectedAct:Option[Action] = None
      val addButton = new Button("Add")
      addButton.onAction = (ae:ActionEvent) => addAction
      val removeButton = new Button("Remove")
      removeButton.onAction = (ae:ActionEvent) => removeAction
      val actionsList = new ListView[String]()
      val actionNameField = new TextField
      val amountField = new TextField
      val actionsGrid = new GridPane
      actionsGrid.children += addButton
      GridPane.setConstraints(addButton,0,0)
      actionsGrid.children += removeButton
      GridPane.setConstraints(removeButton,1,0)
      actionsGrid.children += actionsList
      GridPane.setConstraints(actionsList,0,1,2,3)
      val nameLabel = new Label("Name:")
      actionsGrid.children += nameLabel
      GridPane.setConstraints(nameLabel,3,0)
      val amountLabel = new Label("Links:")
      actionsGrid.children += amountLabel
      GridPane.setConstraints(amountLabel,3,2)
      actionsGrid.children += actionNameField
      GridPane.setConstraints(actionNameField,4,0)
      actionsGrid.children += amountField
      GridPane.setConstraints(amountField,4,2)
      actionsList.selectionModel.apply.selectedItem.onChange {
        val roomIndex = roomList.selectionModel.apply.selectedIndex.apply
        val actionIndex = 
            actionsList.selectionModel.apply.selectedIndex.apply
        if(roomIndex>0 && actionIndex>=0) {
          bindActionFields(rooms(roomIndex).actions(actionIndex))
        }
      }
      actionNameField.text.onChange {
	val newName = actionNameField.text.apply
        val actionIndex = 
	    actionsList.selectionModel.apply.selectedIndex.apply
	if(actionIndex>=0) actionsList.items.apply(actionIndex) =
            newName
       }

       // Directions
       val directionsArea = new TextArea

       val splitPane = new SplitPane
       splitPane.orientation = scalafx.geometry.Orientation.VERTICAL
       splitPane.items += actionsGrid
       splitPane.items += directionsArea
       
       // Top level layout
       val topBorderPane = new BorderPane
       topBorderPane.top = menuBar
       topBorderPane.left = roomList
       topBorderPane.center = splitPane

       root = topBorderPane
  
       def openFile:Unit = {
         val chooser = new FileChooser
         val selected = chooser.showOpenDialog(stage)
         if(selected!=null) {
	   val src = io.Source.fromFile(selected)
	   val lines = src.getLines
	   rooms = Array.fill(lines.next.toInt)(Room(
	      StringProperty(lines.next), 
              List.fill(lines.next.toInt)(Action(StringProperty(lines.next),
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
	roomList.items = ObservableBuffer(rooms.map(_.name.apply):_*)
	roomList.selectionModel.apply.selectFirst
	bindRoomFields(rooms.head)
	}
       }

       def saveFile:Unit = {
	 val chooser = new FileChooser
	 val selected = chooser.showSaveDialog(stage)
	 if(selected!=null) {
	   val pw = new PrintWriter(selected)
	   pw.println(rooms.length)
	   for(r <- rooms) {
	     pw.println(r.name)
	     pw.println(r.actions.length)
	     for(ing <- r.actions) {
	       pw.println(ing.name)
	       pw.println(ing.amount)
	     }
	     pw.println(r.directions)
	     pw.println(".")
	   }
	   pw.close()
	 }
       }
       
       def addRoom:Unit = {
	 val dialog = new TextInputDialog
	 dialog.title = "Room Name"
	 dialog.headerText = "Question?"
	 dialog.contentText = "What is the name of the new Room?"
	 dialog.showAndWait().foreach{ name =>
	   rooms = rooms :+ Room(StringProperty(name), 
	     List(Action(StringProperty("New Room"),
	   	 StringProperty("Link Where?"))), StringProperty("Directions"))
	   roomList.items = ObservableBuffer(rooms.map(_.name.apply):_*)
	   roomList.selectionModel.apply.clearAndSelect(rooms.length-1)
	 }
       }

       def removeRoom:Unit = {
	 if(!roomList.selectionModel.apply.selectedItems.isEmpty) {
	   rooms = 
	       rooms.patch(roomList.selectionModel.apply.selectedIndex.apply,Nil,1)
	   roomList.items= ObservableBuffer(rooms.map(_.name.apply):_*)
	   roomList.selectionModel.apply.clearAndSelect(0)
	 }
       }

       def addAction:Unit = {
	 val roomIndex = roomList.selectionModel.apply.selectedIndex.apply
	 if(roomIndex>=0) {
	   val newAct = Action(StringProperty("Insert name"),StringProperty("Dir"))
	 rooms(roomIndex) = rooms(roomIndex).copy(
	   actions = rooms(roomIndex).actions :+ newAct)
	 actionsList.items.apply += newAct.name.apply
	}
       }

       def removeAction:Unit = {
	 val roomIndex = roomList.selectionModel.apply.selectedIndex.apply
	 val actionIndex = 
	     actionsList.selectionModel.apply.selectedIndex.apply
	 if(roomIndex>=0 && actionIndex>=0) {
	   rooms(roomIndex) = rooms(roomIndex).copy(actions =
	     rooms(roomIndex).actions.patch(actionIndex,Nil,1))
	 bindRoomFields(rooms(roomIndex))
	 }
       }

       def bindRoomFields(r:Room):Unit = {
	 actionsList.items = ObservableBuffer(r.actions.map(_.name.apply):_*)
	 selectedRoom.foreach(_.directions.unbind)
	 directionsArea.text = r.directions.apply
	 selectedRoom = Some(r)
       }

       def bindActionFields(act:Action):Unit = {
	 selectedAct.foreach { si =>
	   si.name.unbind
	   si.amount.unbind
	  }
	  actionNameField.text = act.name.apply
	  act.name <== actionNameField.text
	  amountField.text = act.amount.apply
	  act.amount <== amountField.text
	  rooms.foreach(println)
	  selectedAct = Some(act)
         }
       }
     }
   }

app.main(args)
