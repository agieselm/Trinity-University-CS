package cs2.game

import scalafx.scene.image.Image
import scalafx.scene.canvas.GraphicsContext
import cs2.util.Vec2

/** A graphical sprite object used for gaming or other visual displays
 *  
 *  @constructor create a new sprite based on the given image and initial location
 *  @param img the image used to display this sprite
 *  @param pos the initial position of the '''center''' of the sprite in 2D space
 */
abstract class Sprite (var img:Image, var pos:Vec2) {

  /** moves the sprite a relative amount based on a specified vector
   *  
   *  @param direction - an offset that the position of the sprite should be moved by
   *  @return none/Unit
   */
  def move (direction:Vec2) { pos += direction }
  
  /** moves the sprite to a specific location specified by a vector (not a relative movement)
   *  
   *  @param location - the new location for the sprite's position
   *  @return none/Unit
   */
  def moveTo (location:Vec2) { pos = new Vec2(location) }
  
  /** Method to display the sprite at its current location in the specified Graphics2D context
   *  
   *  @param g - a GraphicsContext object capable of drawing the sprite
   *  @return none/Unit
   */
  def display (g:GraphicsContext) {
    g.drawImage(img, pos.x - img.width.value/2, pos.y-img.height.value/2)
  }
  
  def intersect (other:Sprite):Boolean = {
    if(pos.x - img.width.value/3 < other.pos.x + other.img.width.value/2 && 
       pos.x + img.width.value/3 > other.pos.x - other.img.width.value/2 &&
       pos.y - img.height.value/3 < other.pos.y + other.img.height.value/2 && 
       pos.y + img.height.value/3 > other.pos.y - other.img.height.value/2) {
      true
    } else {
      false
    }
       
  }
  
}