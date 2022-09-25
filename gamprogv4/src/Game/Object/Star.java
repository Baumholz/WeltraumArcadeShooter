package Game.Object;

import Game.Object.GameObject;

import java.io.IOException;

public class Star extends GameObject {

	public Star(double x_, double y_) throws IOException {
		x = x_;
		y = y_;
	}

	public void move(double diffSeconds) {
	}

	@Override
	int type() {
		// TODO Auto-generated method stub
		return 0;
	}
}
