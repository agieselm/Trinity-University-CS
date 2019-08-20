package cs2.game

 import scalafx.Includes._	
import scalafx.application.JFXApp
import scalafx.scene.canvas._
import scalafx.scene.Scene
import scalafx.scene.paint.Color
import scalafx.scene.image.Image
import scalafx.animation.AnimationTimer
import scalafx.scene.input._
import cs2.util.Vec2
import scala.collection.mutable.Buffer
import scalafx.scene.text.Font

/**
 * @author agieselm
 */
 class BackgroundSwarm (val nRows:Int, val nCols:Int, enemyPic:Image, bulletPic:Image, bulletSpeed:Int) extends ShootsBullets {
  
  private val vertStep:Int  = enemyPic.height.value.toInt + 20
  private val horizStep:Int = enemyPic.width.value.toInt + 20
  private val leftOff:Int   = 275
  private val rightOff:Int  = leftOff + (nCols-1)*horizStep
  private val topOff:Int    = -900
  
     var backEnemies = Buffer.tabulate(nRows * nCols)( i => {
     new Enemy(enemyPic, new Vec2(leftOff + (i % nCols)*horizStep,-300), bulletPic)
   })
   
 def display(g:GraphicsContext) { backEnemies.foreach { 
      enemy => if(enemy != null) enemy.display(g) 
  }}
  
 def shoot():Bullet = { 
    var randEnemy = (math.random * nRows * nCols).toInt
    while(backEnemies(randEnemy) == null) {
      randEnemy = (math.random * nRows * nCols).toInt
    }
    backEnemies(randEnemy).shoot
  }
  
}
