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
    title = "Recipes"
    scene = new Scene(600,400) {
      
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

      val recipeMenu = new Menu("Recipe")
      val addItem = new MenuItem("Add")
      addItem.onAction = (e:ActionEvent) => { addRecipe }
      val removeItem = new MenuItem("Remove")
      removeItem.onAction = (e:ActionEvent) => { removeRecipe }
      recipeMenu.items = List(addItem, removeItem)
   
      menuBar.menus = List(fileMenu, recipeMenu)

      //Recipe List
      var recipes = Array(Recipe(StringProperty("Pop Tarts"), List(Ingredient(StringProperty("Pop Tart"), 
        StringProperty("1 packet"))), StringProperty("Toast and poptarts ...\nor don't.")))

      val recipeList = new ListView(recipes.map(_.name.apply))
      var selectedRecipe:Option[Recipe] = None
      recipeList.selectionModel.apply.selectedIndex.onChange {
	val index = recipeList.selectionModel.apply.selectedIndex.apply
	if(index>=0) bindRecipeFields(recipes(index))
      }


      //Ingredients Stuff
      var selectedIngr:Option[Ingredient] = None
      val addButton = new Button("Add")
      addButton.onAction = (ae:ActionEvent) => addIngredient
      val removeButton = new Button("Remove")
      removeButton.onAction = (ae:ActionEvent) => removeIngredient
      val ingredientsList = new ListView[String]()
      val ingredientNameField = new TextField
      val amountField = new TextField
      val ingredientsGrid = new GridPane
      ingredientsGrid.children += addButton
      GridPane.setConstraints(addButton,0,0)
      ingredientsGrid.children += removeButton
      GridPane.setConstraints(removeButton,1,0)
      ingredientsGrid.children += ingredientsList
      GridPane.setConstraints(ingredientsList,0,1,2,3)
      val nameLabel = new Label("Name:")
      ingredientsGrid.children += nameLabel
      GridPane.setConstraints(nameLabel,3,0)
      val amountLabel = new Label("Amount:")
      ingredientsGrid.children += amountLabel
      GridPane.setConstraints(amountLabel,3,2)
      ingredientsGrid.children += ingredientNameField
      GridPane.setConstraints(ingredientNameField,4,0)
      ingredientsGrid.children += amountField
      GridPane.setConstraints(amountField,4,2)
      ingredientsList.selectionModel.apply.selectedItem.onChange {
        val recipeIndex = recipeList.selectionModel.apply.selectedIndex.apply
        val ingredientIndex = 
            ingredientsList.selectionModel.apply.selectedIndex.apply
        if(recipeIndex>0 && ingredientIndex>=0) {
          bindIngredientFields(recipes(recipeIndex).ingredients(ingredientIndex))
        }
      }
      ingredientNameField.text.onChange {
	val newName = ingredientNameField.text.apply
        val ingredientIndex = 
	    ingredientsList.selectionModel.apply.selectedIndex.apply
	if(ingredientIndex>=0) ingredientsList.items.apply(ingredientIndex) =
            newName
       }

       // Directions
       val directionsArea = new TextArea

       val splitPane = new SplitPane
       splitPane.orientation = scalafx.geometry.Orientation.VERTICAL
       splitPane.items += ingredientsGrid
       splitPane.items += directionsArea
       
       // Top level layout
       val topBorderPane = new BorderPane
       topBorderPane.top = menuBar
       topBorderPane.left = recipeList
       topBorderPane.center = splitPane

       root = topBorderPane
  
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

       def saveFile:Unit = {
	 val chooser = new FileChooser
	 val selected = chooser.showSaveDialog(stage)
	 if(selected!=null) {
	   val pw = new PrintWriter(selected)
	   pw.println(recipes.length)
	   for(r <- recipes) {
	     pw.println(r.name)
	     pw.println(r.ingredients.length)
	     for(ing <- r.ingredients) {
	       pw.println(ing.name)
	       pw.println(ing.amount)
	     }
	     pw.println(r.directions)
	     pw.println(".")
	   }
	   pw.close()
	 }
       }
       
       def addRecipe:Unit = {
	 val dialog = new TextInputDialog
	 dialog.title = "Recipe Name"
	 dialog.headerText = "Question?"
	 dialog.contentText = "What is the name of the new recipe?"
	 dialog.showAndWait().foreach{ name =>
	   recipes = recipes :+ Recipe(StringProperty(name), 
	     List(Ingredient(StringProperty("ingredient"),
	   	 StringProperty("amount"))), StringProperty("Directions"))
	   recipeList.items = ObservableBuffer(recipes.map(_.name.apply):_*)
	   recipeList.selectionModel.apply.clearAndSelect(recipes.length-1)
	 }
       }

       def removeRecipe:Unit = {
	 if(!recipeList.selectionModel.apply.selectedItems.isEmpty) {
	   recipes = 
	       recipes.patch(recipeList.selectionModel.apply.selectedIndex.apply,Nil,1)
	   recipeList.items= ObservableBuffer(recipes.map(_.name.apply):_*)
	   recipeList.selectionModel.apply.clearAndSelect(0)
	 }
       }

       def addIngredient:Unit = {
	 val recipeIndex = recipeList.selectionModel.apply.selectedIndex.apply
	 if(recipeIndex>=0) {
	   val newIngr = Ingredient(StringProperty("Stuff"),StringProperty("Some"))
	 recipes(recipeIndex) = recipes(recipeIndex).copy(
	   ingredients = recipes(recipeIndex).ingredients :+ newIngr)
	 ingredientsList.items.apply += newIngr.name.apply
	}
       }

       def removeIngredient:Unit = {
	 val recipeIndex = recipeList.selectionModel.apply.selectedIndex.apply
	 val ingredientIndex = 
	     ingredientsList.selectionModel.apply.selectedIndex.apply
	 if(recipeIndex>=0 && ingredientIndex>=0) {
	   recipes(recipeIndex) = recipes(recipeIndex).copy(ingredients =
	     recipes(recipeIndex).ingredients.patch(ingredientIndex,Nil,1))
	 bindRecipeFields(recipes(recipeIndex))
	 }
       }

       def bindRecipeFields(r:Recipe):Unit = {
	 ingredientsList.items = ObservableBuffer(r.ingredients.map(_.name.apply):_*)
	 selectedRecipe.foreach(_.directions.unbind)
	 directionsArea.text = r.directions.apply
	 selectedRecipe = Some(r)
       }

       def bindIngredientFields(ingr:Ingredient):Unit = {
	 selectedIngr.foreach { si =>
	   si.name.unbind
	   si.amount.unbind
	  }
	  ingredientNameField.text = ingr.name.apply
	  ingr.name <== ingredientNameField.text
	  amountField.text = ingr.amount.apply
	  ingr.amount <== amountField.text
	  recipes.foreach(println)
	  selectedIngr = Some(ingr)
         }
       }
     }
   }

app.main(args)
