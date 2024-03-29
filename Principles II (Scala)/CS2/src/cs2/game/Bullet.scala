package cs2.game

import scalafx.scene.image.Image	
import cs2.util.Vec2

/** Representation of a bullet/projectile for a simple game based on sprites.
 *  Handles all information regarding a bullet's position, movements, and 
 *  abilities.
 *  
 *  @param pic the image representing the bullet
 *  @param initPos the initial position of the '''center''' of the bullet
 *  @param vel the initial velocity of the bullet
 */
class Bullet(pic:Image, initPos:Vec2, var vel:Vec2) extends Sprite(pic, new Vec2(initPos)) {

  /** advances the position of the Bullet over a single time step
   * 
   *  @return none/Unit
   */
	def timeStep() { pos += vel}
  
  def Copy:Bullet = {
    new Bullet(pic, new Vec2(pos), vel)
  }
  
}