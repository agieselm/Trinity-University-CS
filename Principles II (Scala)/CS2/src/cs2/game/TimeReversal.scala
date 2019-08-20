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
class TimeReversal {
  import TimeReversal._
  
  //game buffers 
 // var backSwarmBullets:Buffer[Bullet] = Buffer()
  var enemyBullets:Buffer[Bullet] = Buffer()
  var playerBullets:Buffer[Bullet] = Buffer()
  var bossBullets:Buffer[Bullet] = Buffer()
  var p1GreenLaser:Buffer[GreenLaser] = Buffer() 
  var p2GreenLaser:Buffer[GreenLaser] = Buffer()
  
  
  //Powerup buffers
  var laserPowerup: Buffer[Bullet] = Buffer()
  var healthPowerup: Buffer[Bullet] = Buffer()
  var speedPowerup: Buffer[Bullet] = Buffer()
  
 // var backSwarm:BackgroundSwarm = null
  var player:Player = new Player(imgPlayer, new Vec2(w/2, h-100), imgBullet, imgGreenLaser, 3)
  var player2:Player = new Player(imgPlayer2, new Vec2(w/2, h-100), imgBullet, imgGreenLaser, 3)
  var boss:Boss = new Boss(imgBoss, new Vec2(400, 200), imgBullet, 1)
  var swarm:EnemySwarm = null
  var appear: Boolean = false
  var hp = 3
  var lives = -5
  var score = 0
  var bossLives = 3
  
     // var backgroundOne   = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet1, 2)
     // var backgroundTwo   = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet2, 4)
     // var backgroundThree = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet3, 6)
     // var backgroundFour  = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet4, 8)
  
  def Copy():TimeReversal = {
    var gameState = new TimeReversal()
    gameState.player = this.player.Copy
    gameState.swarm = this.swarm.Copy
    gameState.boss = this.boss.Copy
    gameState.appear = this.appear
    gameState.bossLives = this.bossLives
    gameState.enemyBullets = this.enemyBullets.map(_.Copy)
    gameState.playerBullets = this.playerBullets.map(_.Copy)
    gameState.lives = this.lives
    gameState.hp = this.hp
    gameState.score = this.score
    
    gameState 
    
  }

}


object TimeReversal {
  
      val w:Double = 800.0
      val h:Double = 800.0
  
      val imgPlayer:Image        = new Image("file:GalagaPics/millFalcon.png", 100, 100, true, true)
      val imgPlayer2:Image       = new Image("file:GalagaPics/Xwing.png", 100, 100, true, true)
      val imgEnemy:Image         = new Image("file:GalagaPics/tiefighterfinal.png", 50, 50, true, true)
      val imgBullet:Image        = new Image("file:GalagaPics/laser.png", 20, 20, true, true)
      val imgGreenLaser:Image    = new Image("file:GalagaPics/greenLaser.png", 600, 200, true, true)
      val imgStart:Image         = new Image("file:GalagaPics/starstartscreen.jpg", w, h, false, false)
      val imgBoss:Image          = new Image("file:GalagaPics/vaderBoss.png", 90, 90, true, true)
      val imglaserPowerup:Image  = new Image("file:GalagaPics/laserPowerup.png", 30, 30, true, true) 
      val imghealthPowerup:Image = new Image("file:GalagaPics/healthPowerup.png", 40, 40, true, true)
      val imgspeedPowerup:Image  = new Image("file:GalagaPics/speedPowerup.png", 50, 50, true, true)
      
      var imgUpSwarm:Image     = new Image("file:GalagaPics/blankSwarm.png", 70, 70, true, true)
      var imgBackBullet1:Image = new Image("file:GalagaPics/simpleStar.png", 50, 50, true, true)
      var imgBackBullet2:Image = new Image("file:GalagaPics/simpleStar.png", 95, 95, true, true)
      var imgBackBullet3:Image = new Image("file:GalagaPics/simpleStar.png", 135, 135, true, true)
      var imgBackBullet4:Image = new Image("file:GalagaPics/simpleStar.png", 155, 155, true, true)
}
  