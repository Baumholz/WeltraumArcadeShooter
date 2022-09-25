package Game.Graphic;

import Game.Const.Const;
import Game.Const.Stats;
import Game.Object.*;
import Game.Utility.*;

import javax.swing.*;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Panel extends JPanel implements GraphicSystem, InputSystem, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private boolean newInput = false;
	private int mousePressedX, mousePressedY, mouseMovedX, mouseMovedY, mouseButton;
	private char keyPressed;

	private GraphicsConfiguration graphicsConf = GraphicsEnvironment.getLocalGraphicsEnvironment()
			.getDefaultScreenDevice().getDefaultConfiguration();
	private BufferedImage imageBuffer;
	private Graphics graphics;
	private KeyEvent keyEvent;

	private World world = null;

	private Sounds snd;

	public Panel() {
		this.setSize(Const.WORLDPART_WIDTH, Const.WORLDPART_HEIGHT);
		imageBuffer = graphicsConf.createCompatibleImage(this.getWidth(), this.getHeight());
		graphics = imageBuffer.getGraphics();

		this.setFocusable(true);
		snd = new Sounds();
		this.addKeyListener(this);
	}

	public void clear() {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, Const.WORLDPART_WIDTH, Const.WORLDPART_HEIGHT);
		drawStars();
	}

	public void drawStars() {
		int i = 0;
		Color color = Color.white;
		for (Star star : world.stars) {
			switch (i % 3) {
			case 0:
				color = Color.white;
				break;
			case 1:
				color = Color.yellow;
				break;
			case 2:
				color = Color.orange;
				break;
			}
			int x = (int) (star.x - star.radius - world.worldPartX);
			int y = (int) (star.y - star.radius - world.worldPartY);
			if (i < Const.STAR_COUNT / 4) {
				graphics.setColor(color);
				graphics.drawOval(x, y, 2, 2);
			} else if (i < Const.STAR_COUNT / 2) {
				graphics.setColor(color);
				graphics.drawOval(x, y, 1, 1);
			} else {
				graphics.setColor(color);
				graphics.fillOval(x, y, 3, 3);
			}
			i++;
		}
	}

	public void draw(GameObject obj) {
		if (obj.isLiving) {
			int x = (int) (obj.x - obj.radius - world.worldPartX);
			int y = (int) (obj.y - obj.radius - world.worldPartY);
			graphics.drawImage(obj.image, x, y, null);
		}
	}

	public void redraw() {
		this.getGraphics().drawImage(imageBuffer, 0, 0, this);
	}

	public void mousePressed(MouseEvent evt) {
		// an input Event occurs
		newInput = true;

		mousePressedX = evt.getX();
		mousePressedY = evt.getY();
		mouseButton = evt.getButton();
	}

	public UserInput getUserInput() {
		if (!newInput)
			return null;

		newInput = false;
		return new UserInput(mousePressedX, mousePressedY, mouseMovedX, mouseMovedY, mouseButton, keyPressed, keyEvent);
	}

	// direct the Game.Object.Avatar
	public void command(Avatar avatar, Canon canon, UserInput userInput) {
		int speed = (int) avatar.speed;
		if(speed == 0){
			speed = 200;
		}


		if (userInput.keyPressed == 'w') {
			avatar.speed = speed;
			avatar.alfa = -Math.PI / 2;
			avatar.rotate(0);
			canon.speed = speed;
			canon.alfa = -Math.PI / 2;
		} else if (userInput.keyPressed == 'a') {
			avatar.speed = speed;
			avatar.alfa = Math.PI;
			avatar.rotate(270);
			canon.speed = speed;
			canon.alfa = Math.PI;
		} else if (userInput.keyPressed == 's') {
			avatar.speed = speed;
			avatar.alfa = Math.PI / 2;
			avatar.rotate(180);
			canon.speed = speed;
			canon.alfa = Math.PI / 2;
		} else if (userInput.keyPressed == 'd') {
			avatar.speed = speed;
			avatar.alfa = 2 * Math.PI;
			avatar.rotate(90);
			canon.speed = speed;
			canon.alfa = 2 * Math.PI;
		} else if (userInput.keyEvent.getKeyCode() == KeyEvent.VK_UP) {
			snd.playLaser();
			canon.rotate(0);
			shoot(0, avatar);
			Stats.SHOTS++;
		} else if (userInput.keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
			snd.playLaser();
			canon.rotate(90);
			shoot(90, avatar);
			Stats.SHOTS++;
		} else if (userInput.keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
			snd.playLaser();
			canon.rotate(180);
			shoot(180, avatar);
			Stats.SHOTS++;
		} else if (userInput.keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
			snd.playLaser();
			canon.rotate(270);
			shoot(270, avatar);
			Stats.SHOTS++;
		}
	}

	public void mouseEntered(MouseEvent evt) {
	}

	public void mouseExited(MouseEvent evt) {
	}

	public void mouseClicked(MouseEvent evt) {
	}

	public void mouseReleased(MouseEvent evt) {
	}

	long timeDelta;
	long lastShot = 0;
	long currentShot;

	private void shoot(int alfa, Avatar avatar) {
		currentShot = System.currentTimeMillis();
		timeDelta = currentShot - lastShot;

		if (timeDelta > Const.FIRE_RATE) {
			try {
				Projectile projectile = new Projectile(avatar.x - Const.AVATAR_RADIUS * 0.85,
						avatar.y - Const.AVATAR_RADIUS * 0.85, alfa);
				projectile.rotate(alfa);
				this.world.objectList.add(projectile);
				lastShot = System.currentTimeMillis();
			} catch (Exception e) {
			}
		}
	}
	  public final void draw(TextObject text)
	  {	  
	    graphics.setFont(new Font("", 100, 100));
	    graphics.setColor(Color.DARK_GRAY);
	    graphics.drawString(text.toString(), (int)text.x+1, (int)text.y+1);    
	    graphics.setColor(text.color);
	    graphics.drawString(text.toString(), (int)text.x, (int)text.y);
	  }
	@Override
	public void keyTyped(KeyEvent e) {
		newInput = true;
		keyPressed = e.getKeyChar();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		newInput = true;
		keyPressed = e.getKeyChar();
		keyEvent = e;
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public final void setWorld(World world_) {
		this.world = world_;
	}

}
