package cs2.game

import scalafx.scene.image.Image  
import cs2.util.Vec2

/**
 * @author agieselm
 */
class GreenLaser(pic:Image, initPos:Vec2, var vel:Vec2) extends Sprite(pic, new Vec2(initPos)) {
  def timeStep() { pos += vel}
  
  def Copy:GreenLaser = {
    new GreenLaser(pic, new Vec2(pos), vel)
  }
  
}
  
