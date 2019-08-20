package cs2.game

import scalafx.scene.image.Image
import cs2.util.Vec2

/** The player representation for a simple game based on sprites. Handles all 
 *  information regarding the player's positions, movements, and abilities.
 *  
 *  @param avatar the image representing the player
 *  @param initPos the initial position of the '''center''' of the player
 *  @param bulletPic the image of the bullets fired by this player
 */
class Player(avatar:Image, initPos:Vec2, bulletPic:Image, laserPic:Image, speed:Int) 
                extends Sprite(avatar, new Vec2(initPos)) with ShootsBullets {
 var speedBoost:Boolean = false
  
  //vecs for normal speed
 
   var leftVec = new Vec2(-speed, 0)
   var rightVec = new Vec2(speed, 0)
   var upVec = new Vec2(0, -speed)
   var downVec = new Vec2(0, speed)
 
  //activate normal speed motion
  
  def moveLeft() { move(leftVec) }
  def moveRight() { move(rightVec) }
  def moveUp() { move(upVec) }
  def moveDown() { move(downVec) }
  
  //bullet speeds
  
          val bulletSpeed = new Vec2(0, -10)
          val laserSpeed = new Vec2(0, -10)
  private val bulletOffset= new Vec2(0, -avatar.height.value/2)
  private val laserOffset = new Vec2(0, -avatar.height.value/2)
  
  //vecs for speedPowerup
  
   var speedLeftVec = new Vec2(-speed*2, 0)
   var speedRightVec = new Vec2(speed*2, 0)
   var speedUpVec = new Vec2(0, -speed*2)
   var speedDownVec = new Vec2(0, speed*2)
  
  //activate motion for speed
  
  def speedMoveLeft() { move(speedLeftVec) }
  def speedMoveRight() { move(speedRightVec) }
  def speedMoveUp() { move(speedUpVec) }
  def speedMoveDown() { move(speedDownVec) }
  
  var hp = 3
  var lives = -5
  var score = 0
  var laserCounter = 0
  var laserOn = "Unavailable"
  
  
   def hit() {
        pos = new Vec2(initPos)
        hp -= 1
        if(hp == 0) {
          hp = 3
          lives -= 1
      }
  }
 
  
  /** creates a new Bullet instance beginning from the player, with an 
   *  appropriate velocity
   * 
   *  @return Bullet - the newly created Bullet object that was fired
   */
  def shoot():Bullet = { new Bullet(bulletPic, pos + bulletOffset, bulletSpeed)  }
  def shootGreenLaser():GreenLaser = { new GreenLaser(laserPic, pos + laserOffset, laserSpeed)}
  
  
  
  def die() {
    pos = new Vec2(initPos)
    lives -= 1
    
  }
    
  def Copy:Player = {
      val player = new Player (avatar, new Vec2(pos), bulletPic, laserPic, speed)
      player.lives = this.lives
      player.hp = this.hp
      player.score = this.score
      player
    }
    
  
  def isDead():Boolean = (lives <= 0 && lives >= 0)
 
}
