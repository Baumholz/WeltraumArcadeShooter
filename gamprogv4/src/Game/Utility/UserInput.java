package Game.Utility;

import java.awt.event.KeyEvent;

public class UserInput
{
  // everything a user can press on keyboard or mouse
  public int  mousePressedX, mousePressedY, 
              mouseMovedX, mouseMovedY, mouseButton;

  public char keyPressed;
  public KeyEvent keyEvent;

  public UserInput(int mpx, int mpy, int mmx, int mmy, int mb, char kp, KeyEvent ke)
  { mousePressedX=mpx;  mousePressedY=mpy; 
    mouseMovedX  =mmx;  mouseMovedY  =mmy;
    mouseButton  =mb;  
    keyPressed   =kp;
    keyEvent     =ke;
  }
}
