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
class Boss(avatar:Image, initPos:Vec2, bulletPic:Image, speed:Int) extends Sprite(avatar, new Vec2(initPos)) with ShootsBullets {
  
  private val bullSpeed = new Vec2(0, -8)
  private val leftVec = new Vec2(-speed, 0)
  private val rightVec = new Vec2(speed, 0)
  private val upVec = new Vec2(0, -speed)
  private val downVec = new Vec2(0, speed)
  private val bulletSpeed = new Vec2(0, -10)
  private val bulletOffset= new Vec2(0, -avatar.height.value/2)
  
  var hp = 10
  var bossLives = 1
  
  def hit() {
        pos = new Vec2(400, 200)
        hp -= 1
        if(hp == 0) {
          hp = 3
          bossLives -= 1
      }
  }
 
  //boss movement
  def moveLeft() { move(leftVec) }
  
  def moveRight() { move(rightVec) }
  
  def moveUp() { move(upVec) }
  
  def moveDown() { move(downVec) }
  
 
  //bullet function for boss
  def shoot():Bullet = { new Bullet(bulletPic, pos + bulletOffset, bulletSpeed)  }
  
  //respawn boss
  def die() {
    pos = new Vec2(initPos)
    bossLives -= 1
  }
  
  //boss dead
  def isDead():Boolean = (bossLives <= 0 && bossLives >= 0  )
  
  //boss hit boxes
  def intersectAndRemoveBoss (other:Sprite):Boolean = {
    if(pos.x - img.width.value/3 < other.pos.x + other.img.width.value/2 && 
       pos.x + img.width.value/3 > other.pos.x - other.img.width.value/2 &&
       pos.y - img.height.value/3 < other.pos.y + other.img.height.value/2 && 
       pos.y + img.height.value/3 > other.pos.y - other.img.height.value/2) {
      true
    } else {
      false
    }
  
  }
   def Copy:Boss = {
      new Boss (avatar, new Vec2(pos), bulletPic, speed) 
    }
}  
  
