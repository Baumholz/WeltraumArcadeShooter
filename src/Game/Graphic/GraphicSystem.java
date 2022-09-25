package Game.Graphic;

import Game.Object.GameObject;
import Game.Utility.TextObject;
import Game.Utility.World;

public interface GraphicSystem
{
  // prepare to draw a new Screen
  public void clear();
  
  // draw ONE Game.Object.GameObject on the Screen
  //public void draw(Game.Object.Avatar dot, Game.Object.Canon canon);
  public void draw(GameObject obj);
  // display the completed Screen
  public void draw(TextObject obj);
  public void redraw();
  
  //set world
  void setWorld(World world);
}
