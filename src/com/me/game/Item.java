package com.me.game;

public class Item extends Sprite {

	public Item(int x, int y) {
		super(x, y);
		initItem();
	}

	private void initItem() {
		loadImage("images/item.png");
		getImageDimention();
	}
	
}
