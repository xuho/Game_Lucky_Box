package com.me.game;

import java.awt.event.MouseEvent;

public class Box extends Sprite implements Common {
	private int dx;
	private int dy;

	public Box(int x, int y) {
		super(x, y);
		initBox();
	}

	private void initBox() {
		loadImage("images/box.png");
		getImageDimention();
	}
	
	public void move() {
		x = dx;
		y = dy;
	}
	
	public void mouseMoved(MouseEvent e) {
		dx = e.getX();
		dy = e.getY();
	}
}
