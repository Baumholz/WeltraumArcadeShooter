package Game.Utility;

import java.awt.Color;


public class TextObject
{
  protected static World world;
  

  public int     x;
    public int y;
  public Color   color;
  String text;
  
  public TextObject(int x_, int y_, String _text)
  { x=x_; y=y_; color=Color.RED; text = _text;
  }
  
  public String toString() {
	  
	  
	return text;
	  
  }
  
  protected static void setWorld(World w){world=w;}
}
