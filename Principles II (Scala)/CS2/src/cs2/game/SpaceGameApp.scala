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
import scala.collection.mutable.Stack
import TimeReversal._
import scalafx.scene.control.Button
import scalafx.event.ActionEvent
import javax.sound.sampled.AudioSystem
import java.io._

/**
 * main object that initiates the execution of the game, including construction	
 *  of the window
 */
object SpaceGameApp extends JFXApp {
  //Make the width/height of the window adaptable
  val w: Double = 800.0
  val h: Double = 800.0

  stage = new JFXApp.PrimaryStage {
    title = "The Empire Strikes Back!"
    scene = new Scene {
      val canvas = new Canvas(w, h)
      val g: GraphicsContext = canvas.graphicsContext2D
      content = canvas

      var runGame: Boolean = true
      var runGame2: Boolean = false
      var gameState = new TimeReversal()
      var history = Stack[TimeReversal]()

      //Load images for use in game (do it here so this just happens once)
      //      val imgPlayer:Image      = new Image("file:falconanimated.gif", 140, 140, true, true)
      //      val imgEnemy:Image       = new Image("file:tiefighterfinal.png", 50, 50, true, true)
      //      val imgBullet:Image      = new Image("file:laser.png", 20, 20, true, true)
      //      val imgStart:Image       = new Image("file:starstartscreen.jpg", w, h, false, false)
      //      var imgBoss:Image        = new Image("file:vadertiefighter.jpg", 90, 90, true, true)
      //      var imgUpSwarm:Image     = new Image("file:blankSwarm.png", 70, 70, true, true)
      //      var imgBackBullet1:Image = new Image("file:simpleStar.png", 50, 50, true, true)
      //      var imgBackBullet2:Image = new Image("file:simpleStar.png", 95, 95, true, true)
      //      var imgBackBullet3:Image = new Image("file:simpleStar.png", 135, 135, true, true)
      //      var imgBackBullet4:Image = new Image("file:simpleStar.png", 155, 155, true, true)

      //Background swarms to create parallax effect
      //      var backgroundOne   = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet1, 2)
      //      var backgroundTwo   = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet2, 4)
      //      var backgroundThree = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet3, 6)
      //      var backgroundFour  = new BackgroundSwarm(1, 20, imgUpSwarm, imgBackBullet4, 8)

      //Background swarm construct & implementation
      //      var backSwarm:BackgroundSwarm = null
      //      var backSwarmBullets:Buffer[Bullet] = null

      //Construct the Player and EnemySwarm objects
      //      var player:Player = null
      //      var swarm:EnemySwarm = null
      //      val speed = 7

      //Keep a collection of bullets that have been fired for players & enemies
      //      var enemyBullets:Buffer[Bullet] = null
      //      var playerBullets:Buffer[Bullet] = null

      //Deal with keyboard inputs in the canvas
      var keySet: scala.collection.mutable.HashSet[KeyCode] = null
      canvas.onKeyPressed = (e: KeyEvent) => {
        keySet += e.code
        if (!runGame) {
          if (e.code == KeyCode.N) {
            resetGame
            runGame = true
          }
        }
      }
      canvas.onKeyReleased = (e: KeyEvent) => { keySet -= e.code }

      //Keep a counter to limit player shot rate
      var shotCounter: Int = 10
      var shotCounterp2: Int = 10
      resetGame

      //Reset game function
      def resetGame() {
        gameState.player = new Player(imgPlayer, new Vec2(w / 2, h - 100), imgBullet, imgGreenLaser, 3)
        gameState.player2 = new Player(imgPlayer2, new Vec2(w / 2, h - 100), imgBullet, imgGreenLaser, 3)
        gameState.swarm = new EnemySwarm(5, 10, imgEnemy, imgBullet)
        gameState.boss = new Boss(imgBoss, new Vec2(400, 200), imgBullet, 1)
        // gameState.backgroundOne   = new BackgroundSwarm(20, 20, imgUpSwarm, imgBackBullet1, 2)
        // gameState.backgroundTwo   = new BackgroundSwarm(20, 20, imgUpSwarm, imgBackBullet2, 4)
        // gameState.backgroundThree = new BackgroundSwarm(20, 20, imgUpSwarm, imgBackBullet3, 6)
        // gameState.backgroundFour  = new BackgroundSwarm(20, 20, imgUpSwarm, imgBackBullet4, 8)
        gameState.enemyBullets = Buffer[Bullet]()
        gameState.playerBullets = Buffer[Bullet]()
        // gameState.backSwarmBullets= Buffer[Bullet]()
        gameState.bossBullets = Buffer[Bullet]()
        gameState.p1GreenLaser = Buffer[GreenLaser]()
        gameState.p2GreenLaser = Buffer[GreenLaser]()
        gameState.healthPowerup = Buffer[Bullet]()
        gameState.laserPowerup = Buffer[Bullet]()

        keySet = scala.collection.mutable.HashSet[KeyCode]()
        shotCounter = 10
        gameState.appear = false

      }

      //Intersections between game objects handler
      def dealWithIntersections() {
        //Handle intersections between player bullets and the swarm
        var i = 0
        while (i < gameState.playerBullets.length) {
          if (gameState.swarm.intersectAndRemoveEnemy(gameState.playerBullets(i))) {
            gameState.playerBullets.remove(i)
            gameState.player.score += 100
          } else i += 1
        }

        //Handle intersections between player bullets and the boss
        i = 0
        while (i < gameState.playerBullets.length) {
          if (gameState.boss.intersectAndRemoveBoss(gameState.playerBullets(i))) {
            gameState.playerBullets.remove(i)
            gameState.player.score += 500
            gameState.boss.hit
          } else i += 1
        }

        //Handle intersections between enemy bullets and the player
        i = 0
        while (i < gameState.enemyBullets.length) {
          if (gameState.player.intersect(gameState.enemyBullets(i))) {
            gameState.enemyBullets.remove(i)
            gameState.player.hit
            gameState.player.speedBoost = false
          } else i += 1
        }

        //Handle intersections between boss bullets and the player
        i = 0
        while (i < gameState.bossBullets.length) {
          if (gameState.player.intersect(gameState.bossBullets(i))) {
            gameState.bossBullets.remove(i)
            gameState.player.hit
            gameState.player.speedBoost = false
          } else i += 1
        }

        //Handle intersections between enemy and player bullets
        i = 0
        while (i < gameState.enemyBullets.length) {
          var j = 0
          var hit = false
          while (j < gameState.playerBullets.length) {
            if (gameState.enemyBullets(i).intersect(gameState.playerBullets(j))) {
              gameState.playerBullets.remove(j)
              hit = true
            } else j += 1
          }
          if (hit) {
            gameState.enemyBullets.remove(i)
          } else i += 1
        }
        //Handle intersections between player and enemies
        if (gameState.swarm.intersectAndRemoveEnemy(gameState.player)) {
          gameState.player.hit
          gameState.player.score += 100
        }

        //Handle intersections between laserPowerup and player  
        i = 0
        while (i < gameState.laserPowerup.length) {
          if (gameState.player.intersect(gameState.laserPowerup(i))) {
            gameState.laserPowerup.remove(i)
            gameState.player.laserCounter += 1
            gameState.player.laserOn = "Ready to fire!"
          } else i += 1
        }

        //Handle intersections between GreenLaser and the swarm
        i = 0
        while (i < gameState.p1GreenLaser.length) {
          if (gameState.swarm.intersectAndRemoveEnemy(gameState.p1GreenLaser(i))) {
            gameState.player.score += 1000
          } else i += 1
        }

        //Handle intersections between GreenLaser and the boss
        i = 0
        while (i < gameState.p1GreenLaser.length) {
          if (gameState.boss.intersectAndRemoveBoss(gameState.p1GreenLaser(i))) {
            gameState.p1GreenLaser.remove(i)
            gameState.player.score += 500
            gameState.boss.hit
          } else i += 1
        }

        //Handle intersections between healthPowerup and the player

        i = 0
        while (i < gameState.healthPowerup.length) {
          if (gameState.player.intersect(gameState.healthPowerup(i))) {
            gameState.healthPowerup.remove(i)
            gameState.player.lives += 1
          } else i += 1
        }

        //Handle intersections between speedPowerup and the player

        i = 0
        while (i < gameState.speedPowerup.length) {
          if (gameState.player.intersect(gameState.speedPowerup(i))) {
            gameState.speedPowerup.remove(i)
            gameState.player.speedBoost = true
          } else i += 1
        }
        
        //start Player2 intersections
        
        //Handle intersections between player2 bullets and the swarm
        i = 0
        while (i < gameState.playerBullets.length) {
          if (gameState.swarm.intersectAndRemoveEnemy(gameState.playerBullets(i))) {
            gameState.playerBullets.remove(i)
            gameState.player2.score += 100
          } else i += 1
        }

        //Handle intersections between player2 bullets and the boss
        i = 0
        while (i < gameState.playerBullets.length) {
          if (gameState.boss.intersectAndRemoveBoss(gameState.playerBullets(i))) {
            gameState.playerBullets.remove(i)
            gameState.player2.score += 500
            gameState.boss.hit
          } else i += 1
        }

        //Handle intersections between enemy bullets and the player2
        i = 0
        while (i < gameState.enemyBullets.length) {
          if (gameState.player2.intersect(gameState.enemyBullets(i))) {
            gameState.enemyBullets.remove(i)
            gameState.player2.hit
            gameState.player2.speedBoost = false
          } else i += 1
        }

        //Handle intersections between boss bullets and the player2
        i = 0
        while (i < gameState.bossBullets.length) {
          if (gameState.player2.intersect(gameState.bossBullets(i))) {
            gameState.bossBullets.remove(i)
            gameState.player2.hit
            gameState.player2.speedBoost = false
          } else i += 1
        }

        //Handle intersections between enemy and player2 bullets
        i = 0
        while (i < gameState.enemyBullets.length) {
          var j = 0
          var hit = false
          while (j < gameState.playerBullets.length) {
            if (gameState.enemyBullets(i).intersect(gameState.playerBullets(j))) {
              gameState.playerBullets.remove(j)
              hit = true
            } else j += 1
          }
          if (hit) {
            gameState.enemyBullets.remove(i)
          } else i += 1
        }
        //Handle intersections between player2 and enemies
        if (gameState.swarm.intersectAndRemoveEnemy(gameState.player2)) {
          gameState.player2.hit
          gameState.player2.score += 100
        }

        //Handle intersections between laserPowerup and player2 
        i = 0
        while (i < gameState.laserPowerup.length) {
          if (gameState.player2.intersect(gameState.laserPowerup(i))) {
            gameState.laserPowerup.remove(i)
            gameState.player2.laserCounter += 1
            gameState.player2.laserOn = "Ready to fire!"
          } else i += 1
        }

        //Handle intersections between p2GreenLaser and the swarm
        i = 0
        while (i < gameState.p2GreenLaser.length) {
          if (gameState.swarm.intersectAndRemoveEnemy(gameState.p2GreenLaser(i))) {
            gameState.player2.score += 1000
          } else i += 1
        }

        //Handle intersections between p2GreenLaser and the boss
        i = 0
        while (i < gameState.p2GreenLaser.length) {
          if (gameState.boss.intersectAndRemoveBoss(gameState.p2GreenLaser(i))) {
            gameState.p2GreenLaser.remove(i)
            gameState.player2.score += 500
            gameState.boss.hit
          } else i += 1
        }

        //Handle intersections between healthPowerup and the player

        i = 0
        while (i < gameState.healthPowerup.length) {
          if (gameState.player2.intersect(gameState.healthPowerup(i))) {
            gameState.healthPowerup.remove(i)
            gameState.player2.lives += 1
          } else i += 1
        }

        //Handle intersections between speedPowerup and the player

        i = 0
        while (i < gameState.speedPowerup.length) {
          if (gameState.player2.intersect(gameState.speedPowerup(i))) {
            gameState.speedPowerup.remove(i)
            gameState.player2.speedBoost = true
          } else i += 1
        }

        //Buttons for home menu
//
//        val button1 = new Button("Start Single Player")
//        button1.layoutX = (230)
//        button1.layoutY = (360)
//        val button2 = new Button("Start Two Player")
//        button1.layoutX = (230)
//        button1.layoutY = (500)
//        content = List(canvas, button1, button2)
//        button1.onAction = (e: ActionEvent) => {
//          gameState.player.lives = 5
//          runGame = true
//          runGame2 = false
//          content = canvas
//        }
//        button2.onAction = (e: ActionEvent) => {
//          gameState.player.lives = 5
//          runGame = false
//          runGame2 = true
//          content = canvas
//        }

      }
      //Create and run an AnimationTimer to control game frames/flow
      var oldTime: Long = 0
      val timer = AnimationTimer(t => {
        canvas.requestFocus
        //Use the oldTime variable to limit the timer firing rate
        if (t - oldTime > 1e9 / 60) {
          oldTime = t

          if (!keySet.contains(KeyCode.R)) {

            //If the game is running....
            if (runGame) {
              //Handle all intersections
              dealWithIntersections

              //Respawn the swarm if they are all destroyed
              if (gameState.swarm.allDead) {
                gameState.swarm = new EnemySwarm(5, 10, imgEnemy, imgBullet)
              }

              //Check for dead player
              if (gameState.player.isDead) {
                runGame = false
              }

              //Check for dead boss
              if (gameState.boss.isDead) {
                gameState.boss.moveTo(new Vec2(1000, 1000))
              }

              //Remove bullets that leave the screen
              //gameState.backSwarmBullets.filterNot( b => b.pos.y > h)
              gameState.playerBullets.filterNot(b => b.pos.y < 0)
              gameState.enemyBullets.filterNot(b => b.pos.y > h)
              gameState.p1GreenLaser.filterNot(b => b.pos.y < 0)
              gameState.laserPowerup.filterNot(b => b.pos.y > h)
              gameState.healthPowerup.filterNot(b => b.pos.y > h)
              gameState.speedPowerup.filterNot(b => b.pos.y > h)

              //Update objects based on current key presses

              if (keySet(KeyCode.LEFT)) if (gameState.player.pos.x > 0) { gameState.player.moveLeft; gameState.boss.moveLeft }
              if (keySet(KeyCode.RIGHT)) if (gameState.player.pos.x < w) { gameState.player.moveRight; gameState.boss.moveRight }
              if (keySet(KeyCode.UP)) if (gameState.player.pos.y > 0) { gameState.player.moveUp; gameState.boss.moveUp }
              if (keySet(KeyCode.DOWN)) if (gameState.player.pos.y < h) { gameState.player.moveDown; gameState.boss.moveDown }
              if (keySet(KeyCode.A)) if (gameState.player.pos.x > 0) { gameState.player.moveLeft; gameState.boss.moveLeft }
              if (keySet(KeyCode.D)) if (gameState.player.pos.x < w) { gameState.player.moveRight; gameState.boss.moveRight }
              if (keySet(KeyCode.W)) if (gameState.player.pos.y > 0) { gameState.player.moveUp; gameState.boss.moveUp }
              if (keySet(KeyCode.S)) if (gameState.player.pos.y < h) { gameState.player.moveDown; gameState.boss.moveDown }

              //Update objects if speedBoost == true
              if (gameState.player.speedBoost == true) {
                if (keySet(KeyCode.LEFT)) if (gameState.player.pos.x > 0) { gameState.player.speedMoveLeft }
                if (keySet(KeyCode.RIGHT)) if (gameState.player.pos.x < w) { gameState.player.speedMoveRight }
                if (keySet(KeyCode.UP)) if (gameState.player.pos.y > 0) { gameState.player.speedMoveUp }
                if (keySet(KeyCode.DOWN)) if (gameState.player.pos.y < h) { gameState.player.speedMoveDown }
                if (keySet(KeyCode.A)) if (gameState.player.pos.x > 0) { gameState.player.speedMoveLeft }
                if (keySet(KeyCode.D)) if (gameState.player.pos.x < w) { gameState.player.speedMoveRight }
                if (keySet(KeyCode.W)) if (gameState.player.pos.y > 0) { gameState.player.speedMoveUp }
                if (keySet(KeyCode.S)) if (gameState.player.pos.y < h) { gameState.player.speedMoveDown }
              }

              //Speed at which player can shoot
              shotCounter -= 1

              if (keySet(KeyCode.SPACE)) {
                if (shotCounter <= 0) {
                  gameState.playerBullets += gameState.player.shoot
                  shotCounter = 10

              //p1 shoot sound
              
              var sound = AudioSystem.getAudioInputStream(

              new BufferedInputStream(

              new FileInputStream(

              new File("GalagaSounds/blasterSound.wav"))))

              var clip = AudioSystem.getClip

              clip.open(sound)

              clip.start
                  
                }
              }

              //Laser Properties
              if (gameState.player.laserCounter >= 1) {
                if (keySet(KeyCode.ENTER)) {
                  if (gameState.player.laserCounter >= 0) {
                    gameState.player.laserOn = "Unavailable"
                    gameState.p1GreenLaser += gameState.player.shootGreenLaser
                    gameState.player.laserCounter = 0
                  
              //p1 Laser sound
              
              var sound = AudioSystem.getAudioInputStream(

              new BufferedInputStream(

              new FileInputStream(

              new File("GalagaSounds/forceBeWithYou.wav"))))

              var clip = AudioSystem.getClip

              clip.open(sound)

              clip.start
                  }
                }
              }
  
              //Perform additional updates to game state (eg. animations)

              gameState.swarm.timeStep
              gameState.bossBullets.foreach { x => { x.timeStep } }
              gameState.enemyBullets.foreach { x => { x.timeStep } }
              gameState.playerBullets.foreach { x => { x.timeStep } }
              gameState.p1GreenLaser.foreach { x => { x.timeStep } }
              gameState.laserPowerup.foreach { x => { x.timeStep } }
              gameState.healthPowerup.foreach { x => { x.timeStep } }
              gameState.speedPowerup.foreach { x => { x.timeStep } }

              //gameState.backSwarmBullets.foreach { x => { x.timeStep } }

              if (gameState.appear == true) {
                if (math.random < 0.12) {
                  val bossBullet = gameState.boss.shoot
                  if (bossBullet != null) gameState.bossBullets += bossBullet

                }
              }

              if (math.random < 0.08) {
                val bullet = gameState.swarm.shoot
                if (bullet != null) gameState.enemyBullets += bullet
              }

              if (math.random < 0.008) {
                val greenlaserPowerup = gameState.swarm.shoot
                greenlaserPowerup.img = imglaserPowerup
                if (greenlaserPowerup != null) gameState.laserPowerup += greenlaserPowerup
              }

              if (math.random < 0.008) {
                val addHealthPowerup = gameState.swarm.shoot
                addHealthPowerup.img = imghealthPowerup
                if (addHealthPowerup != null) gameState.healthPowerup += addHealthPowerup
              }

              if (math.random < 0.008) {
                val addSpeedPowerup = gameState.swarm.shoot
                addSpeedPowerup.img = imgspeedPowerup
                if (addSpeedPowerup != null) gameState.speedPowerup += addSpeedPowerup
              }

              //Background swarm shoot
              //            if(math.random < 0.40) {
              //              val bullet = gameState.backgroundOne.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }
              //              
              //            if(math.random < 0.20) {
              //              val bullet = gameState.backgroundTwo.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }
              //            
              //            if(math.random < 0.10) {
              //              val bullet = gameState.backgroundThree.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }
              //            
              //            if(math.random < 0.05) {
              //              val bullet = gameState.backgroundFour.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }

              history.push(gameState.Copy)
              history.length

              //Display all objects
              g.fill = Color.Black
              g.fillRect(0, 0, w, h) //blank out the screen

              if (math.random < 0.01 && !gameState.appear) {
                gameState.appear = true
              }

            }

            if (runGame2) {
              //Handle all intersections
              dealWithIntersections

              //Respawn the swarm if they are all destroyed
              if (gameState.swarm.allDead) {
                gameState.swarm = new EnemySwarm(5, 10, imgEnemy, imgBullet)
              }

              //Check for dead player
             
              if (gameState.player.isDead || gameState.player2.isDead) {
                runGame2 = false
              }

              //Check for dead boss
              if (gameState.boss.isDead) {
                gameState.boss.moveTo(new Vec2(1000, 1000))
              }

              //Remove bullets that leave the screen
              //gameState.backSwarmBullets.filterNot( b => b.pos.y > h)
              gameState.playerBullets.filterNot(b => b.pos.y < 0)
              gameState.enemyBullets.filterNot(b => b.pos.y > h)
              gameState.p1GreenLaser.filterNot(b => b.pos.y < 0)
              gameState.p2GreenLaser.filterNot(b => b.pos.y < 0)
              gameState.laserPowerup.filterNot(b => b.pos.y > h)
              gameState.healthPowerup.filterNot(b => b.pos.y > h)
              gameState.speedPowerup.filterNot(b => b.pos.y > h)

              //Update objects based on current key presses

              if (keySet(KeyCode.LEFT)) if (gameState.player.pos.x > 0) { gameState.player.moveLeft; gameState.boss.moveLeft }
              if (keySet(KeyCode.RIGHT)) if (gameState.player.pos.x < w) { gameState.player.moveRight; gameState.boss.moveRight }
              if (keySet(KeyCode.UP)) if (gameState.player.pos.y > 0) { gameState.player.moveUp; gameState.boss.moveUp }
              if (keySet(KeyCode.DOWN)) if (gameState.player.pos.y < h) { gameState.player.moveDown; gameState.boss.moveDown }
              
              //update p2 movesments on key presses
              
              if (keySet(KeyCode.A)) if (gameState.player2.pos.x > 0) { gameState.player2.moveLeft; gameState.boss.moveLeft }
              if (keySet(KeyCode.D)) if (gameState.player2.pos.x < w) { gameState.player2.moveRight; gameState.boss.moveRight }
              if (keySet(KeyCode.W)) if (gameState.player2.pos.y > 0) { gameState.player2.moveUp; gameState.boss.moveUp }
              if (keySet(KeyCode.S)) if (gameState.player2.pos.y < h) { gameState.player2.moveDown; gameState.boss.moveDown }

              //Update objects if speedBoost == true
              
              if (gameState.player.speedBoost == true) {
                if (keySet(KeyCode.LEFT)) if (gameState.player.pos.x > 0) { gameState.player.speedMoveLeft }
                if (keySet(KeyCode.RIGHT)) if (gameState.player.pos.x < w) { gameState.player.speedMoveRight }
                if (keySet(KeyCode.UP)) if (gameState.player.pos.y > 0) { gameState.player.speedMoveUp }
                if (keySet(KeyCode.DOWN)) if (gameState.player.pos.y < h) { gameState.player.speedMoveDown }
              }
              
              //update p2 is speedBoost == true
              
              if (gameState.player2.speedBoost == true) {
                if (keySet(KeyCode.A)) if (gameState.player2.pos.x > 0) { gameState.player2.speedMoveLeft }
                if (keySet(KeyCode.D)) if (gameState.player2.pos.x < w) { gameState.player2.speedMoveRight }
                if (keySet(KeyCode.W)) if (gameState.player2.pos.y > 0) { gameState.player2.speedMoveUp }
                if (keySet(KeyCode.S)) if (gameState.player2.pos.y < h) { gameState.player2.speedMoveDown }
              }

              //Speed at which player can shoot
              shotCounter -= 1
              shotCounterp2 -= 1

              if (keySet(KeyCode.SPACE)) {
                if (shotCounter <= 0) {
                  gameState.playerBullets += gameState.player.shoot
                  shotCounter = 10
                  
              //p2 shoot sound
              
              var sound = AudioSystem.getAudioInputStream(

              new BufferedInputStream(

              new FileInputStream(

              new File("GalagaSounds/blasterSound.wav"))))

              var clip = AudioSystem.getClip

              clip.open(sound)

              clip.start
                }
              }

              //p2 shoot
              if (keySet(KeyCode.X)) {
                if (shotCounterp2 <= 0) {
                  gameState.playerBullets += gameState.player2.shoot
                  shotCounterp2 = 10
              //p2 blaster sound
              
              var sound = AudioSystem.getAudioInputStream(

              new BufferedInputStream(

              new FileInputStream(

              new File("GalagaSounds/blasterSound.wav"))))

              var clip = AudioSystem.getClip

              clip.open(sound)

              clip.start
                }
              }

              //Laser Properties
              if (gameState.player.laserCounter >= 1) {
                if (keySet(KeyCode.ENTER)) {
                  if (gameState.player.laserCounter >= 0) {
                    gameState.player.laserOn = "Unavailable"
                    gameState.p1GreenLaser += gameState.player.shootGreenLaser
                    gameState.player.laserCounter = 0
              //p1 laser sound
              
              var sound = AudioSystem.getAudioInputStream(

              new BufferedInputStream(

              new FileInputStream(

              new File("GalagaSounds/forceBeWithYou.wav"))))

              var clip = AudioSystem.getClip

              clip.open(sound)

              clip.start
                  }
                }
              }
              //p2 laser
              
              if (gameState.player2.laserCounter >= 1) {
                if (keySet(KeyCode.Z)) {
                  if (gameState.player2.laserCounter >= 0) {
                    gameState.player2.laserOn = "Unavailable"
                    gameState.p2GreenLaser += gameState.player2.shootGreenLaser
                    gameState.player2.laserCounter = 0
              //p2 Laser sound
              
              var sound = AudioSystem.getAudioInputStream(

              new BufferedInputStream(

              new FileInputStream(

              new File("GalagaSounds/forceBeWithYou.wav"))))

              var clip = AudioSystem.getClip

              clip.open(sound)

              clip.start
                  }
                }
              }

              //Perform additional updates to game state (eg. animations)

              gameState.swarm.timeStep
              gameState.bossBullets.foreach { x => { x.timeStep } }
              gameState.enemyBullets.foreach { x => { x.timeStep } }
              gameState.playerBullets.foreach { x => { x.timeStep } }
              gameState.p1GreenLaser.foreach { x => { x.timeStep } }
              gameState.p2GreenLaser.foreach { x => { x.timeStep } }
              gameState.laserPowerup.foreach { x => { x.timeStep } }
              gameState.healthPowerup.foreach { x => { x.timeStep } }
              gameState.speedPowerup.foreach { x => { x.timeStep } }

              //gameState.backSwarmBullets.foreach { x => { x.timeStep } }

              if (gameState.appear == true) {
                if (math.random < 0.12) {
                  val bossBullet = gameState.boss.shoot
                  if (bossBullet != null) gameState.bossBullets += bossBullet

                }
              }

              if (math.random < 0.08) {
                val bullet = gameState.swarm.shoot
                if (bullet != null) gameState.enemyBullets += bullet
              }

              if (math.random < 0.008) {
                val greenlaserPowerup = gameState.swarm.shoot
                greenlaserPowerup.img = imglaserPowerup
                if (greenlaserPowerup != null) gameState.laserPowerup += greenlaserPowerup
              }

              if (math.random < 0.008) {
                val addHealthPowerup = gameState.swarm.shoot
                addHealthPowerup.img = imghealthPowerup
                if (addHealthPowerup != null) gameState.healthPowerup += addHealthPowerup
              }

              if (math.random < 0.008) {
                val addSpeedPowerup = gameState.swarm.shoot
                addSpeedPowerup.img = imgspeedPowerup
                if (addSpeedPowerup != null) gameState.speedPowerup += addSpeedPowerup
              }

              //Background swarm shoot
              //            if(math.random < 0.40) {
              //              val bullet = gameState.backgroundOne.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }
              //              
              //            if(math.random < 0.20) {
              //              val bullet = gameState.backgroundTwo.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }
              //            
              //            if(math.random < 0.10) {
              //              val bullet = gameState.backgroundThree.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }
              //            
              //            if(math.random < 0.05) {
              //              val bullet = gameState.backgroundFour.shoot
              //              if(bullet != null) gameState.backSwarmBullets += bullet
              //              }

              history.push(gameState.Copy)
              history.length

              //Display all objects
              g.fill = Color.Black
              g.fillRect(0, 0, w, h) //blank out the screen

              if (math.random < 0.01 && !gameState.appear) {
                gameState.appear = true
              }

            } else {

              //Show the end game screen
              g.fill = Color.Black
              g.fillRect(0, 0, w, h) //blank out the screen
              g.font = new Font("Times New Roman", 20)
              g.fill = Color.White
              g.fillText("GAME OVER", w / 2 - 50, h / 2)
              g.fillText("Press N to Start New Game", w / 2 - 100, h / 2 + 50)

            }

            //start screen

            if (gameState.player.lives == -5) {

              runGame = false
              g.fill = Color.Black
              g.fillRect(0, 0, w, h)
              g.fillText("Press Enter for single player mode", 400, 600)
              g.fillText("Press M for multiplayer mode", 400, 650)
              g.drawImage(imgStart, 0, 0)
              g.fill = Color.Red
              g.font = new Font("Times New Roman", 100)
              g.drawImage(imgStart, 0, 0)
                        if(keySet(KeyCode.ENTER)) {gameState.player.lives = 3; runGame = true}
                        if(keySet(KeyCode.M)) {gameState.player.lives = 3; gameState.player2.lives = 3; runGame2 = true}
            }
          } else {

            if (!history.isEmpty) {
              gameState = history.top
              history.pop
              gameState.enemyBullets.length
            }
          }

          if (runGame) {
            g.fill = Color.Black
            g.fillRect(0, 0, w, h)
            gameState.player.display(g)
            gameState.swarm.display(g)
            if (gameState.appear == true) gameState.boss.display(g)
            //            gameState.backgroundOne.display(g)
            //            gameState.backgroundTwo.display(g)
            //            gameState.backgroundThree.display(g)
            //            gameState.backgroundFour.display(g)
            gameState.bossBullets.foreach { x => { x.timeStep } }
            //   gameState.backSwarmBullets.foreach { x => { x.display(g) } }
            gameState.enemyBullets.foreach { x => { x.display(g) } }
            gameState.playerBullets.foreach { x => { x.display(g) } }
            gameState.p1GreenLaser.foreach { x => { x.display(g) } }
            gameState.laserPowerup.foreach { x => { x.display(g) } }
            gameState.healthPowerup.foreach { x => { x.display(g) } }
            gameState.speedPowerup.foreach { x => { x.display(g) } }
            g.fill = Color.White
            g.font = new Font("Times New Roman", 20)            
            g.fillText("Laser " + gameState.player.laserOn, 600, 700)            
            g.fillText("Health: " + gameState.player.hp, w - 80, 40)
            g.fillText("Score: " + gameState.player.score, 10, 20)
            g.fillText("Lives: " + gameState.player.lives, w - 70, 20)
          }

          if (runGame2) {
            g.fill = Color.Black
            g.fillRect(0, 0, w, h)
            gameState.player.display(g)
            gameState.player2.display(g)
            gameState.swarm.display(g)
            if (gameState.appear == true) gameState.boss.display(g)
            //            gameState.backgroundOne.display(g)
            //            gameState.backgroundTwo.display(g)
            //            gameState.backgroundThree.display(g)
            //            gameState.backgroundFour.display(g)
            gameState.bossBullets.foreach { x => { x.timeStep } }
            //   gameState.backSwarmBullets.foreach { x => { x.display(g) } }
            gameState.enemyBullets.foreach { x => { x.display(g) } }
            gameState.playerBullets.foreach { x => { x.display(g) } }
            gameState.p1GreenLaser.foreach { x => { x.display(g) } }
            gameState.p2GreenLaser.foreach { x => { x.display(g) } }
            gameState.laserPowerup.foreach { x => { x.display(g) } }
            gameState.healthPowerup.foreach { x => { x.display(g) } }
            gameState.speedPowerup.foreach { x => { x.display(g) } }
            g.fill = Color.White
            g.font = new Font("Times New Roman", 20)
            g.fillText("P1Health: " + gameState.player.hp, w - 130, 40)
            g.fillText("Score: " + gameState.player.score, 10, 20)
            g.fillText("P1Lives: " + gameState.player.lives, w -120, 20)
            g.fillText("Laser " + gameState.player.laserOn, 600, 700)
            g.fillText("Laser " + gameState.player2.laserOn, 600, 750)
            //display for p2 lives and health
            g.fillText("P2Health: " + gameState.player2.hp, w - 130, 80)
            g.fillText("P2Lives: " + gameState.player2.lives, w -120, 100)
          }

        }

      })
      timer.start
      
      if (gameState.player.lives == -5) {

        //start screen sound
              
        var sound = AudioSystem.getAudioInputStream(

        new BufferedInputStream(

        new FileInputStream(
      
        new File("GalagaSounds/mainMenuSound.wav"))))

        var clip = AudioSystem.getClip

        clip.open(sound)

        clip.start
       }
     }
   }
 }