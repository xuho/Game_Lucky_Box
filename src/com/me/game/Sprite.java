package com.me.game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected boolean visible;
	protected Image image;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	public void loadImage(String fileName) {
		ImageIcon ii = new ImageIcon(fileName);
		image = ii.getImage();
	}
	public void getImageDimention() {
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}
	
	public Rectangle getBound() {
		return new Rectangle(x, y, width, height);
	}
}
