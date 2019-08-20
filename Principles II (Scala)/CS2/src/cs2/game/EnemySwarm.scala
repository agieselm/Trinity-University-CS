package cs2.game

import scalafx.scene.canvas.GraphicsContext
import scalafx.scene.image.Image
import cs2.util.Vec2
import scala.collection.mutable.Buffer

/** contains the control and logic to present a coordinated set of Enemy objects.	
 *  For now, this class generates a "grid" of enemy objects centered near the 
 *  top of the screen.
 *  
 *  @param nRows - number of rows of enemy objects
 *  @param nCols - number of columns of enemy objects
 */
class EnemySwarm(val nRows:Int, val nCols:Int, enemyPic:Image, bulletPic:Image) extends ShootsBullets {
	
	private val vertStep:Int  = enemyPic.height.value.toInt + 20
	private val horizStep:Int = enemyPic.width.value.toInt + 20
	private val leftOff:Int  = 175
	private val rightOff:Int = leftOff + (nCols-1)*horizStep
	private val topOff:Int   = 100
  
  private var enemies = Buffer.tabulate(nRows * nCols)( i => {
	   new Enemy(enemyPic, new Vec2(leftOff + (i % nCols)*horizStep, 100 + (i / nCols)*vertStep), bulletPic)
	})
	
	def timeStep() {
	  for(i <- 0 until nRows*nCols) {
      if(enemies(i) != null) {
        //If not on one of the horizontal "tracks" OR if at the left or right extent
        if((enemies(i).pos.y.toInt - topOff) / vertStep.toDouble == (enemies(i).pos.y.toInt - topOff) / vertStep) {
          enemies(i).move(new Vec2(0, 1))
        }
        else {
          if(((enemies(i).pos.y.toInt - topOff) / vertStep) % 2 == 0) {
            if(enemies(i).pos.x.toInt == rightOff) {
              enemies(i).move(new Vec2(0,1))
            } else enemies(i).move(new Vec2(1,0))
          } else {
            if(enemies(i).pos.x.toInt == leftOff) {
              enemies(i).move(new Vec2(0,1))
            } else enemies(i).move(new Vec2(-1,0))
          }
        }
      }
    }
	}
  
	/** method to display all Enemy objects contained within this EnemySwarm
	 * 
	 *  @param g - the GraphicsContext to draw into
	 *  @return none/Unit
	 */
	def display(g:GraphicsContext) { enemies.foreach { 
	    enemy => if(enemy != null) enemy.display(g) 
	}}
  
  /** overridden method of ShootsBullets. Creates a single, new bullet instance 
   *  originating from a random enemy in the swarm. (Not a bullet from every 
   *  object, just a single from a random enemy)
   *  
   *  @return Bullet - the newly created Bullet object fired from the swarm
   */
  def shoot():Bullet = { 
    var randEnemy = (math.random * nRows * nCols).toInt
    while(enemies(randEnemy) == null) {
      randEnemy = (math.random * nRows * nCols).toInt
    }
    enemies(randEnemy).shoot
  }
  
  def intersectAndRemoveEnemy(other:Sprite):Boolean = {
    for(i <- 0 until nRows * nCols) {
      if(enemies(i) != null) { 
          if(enemies(i).intersect(other)) {
        enemies(i) = null
        return true
      }
    }}
    return false
  }
  
  def allDead():Boolean = {
    for(i <- 0 until nRows * nCols) {
      if(enemies(i) != null) return false
    }
    return true
  }
  
  def Copy(): EnemySwarm = {
    var enemyswarmbuffer = new EnemySwarm(nRows,nCols, enemyPic, bulletPic)
    enemyswarmbuffer.enemies = Buffer[Enemy]()
    for(i <- 0 until enemies.length) {
      if(enemies(i) !=null) {
        enemyswarmbuffer.enemies += (enemies(i).Copy)
      }else{
        enemyswarmbuffer.enemies += null
      }
    }
    enemyswarmbuffer
  }
  
}