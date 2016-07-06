package com.me.game;

public interface Common {
	public enum Status {UP, DOWN, LEFT, RIGHT}; // di chuyển ngang hoặc dọc
	public final int WIDTH = 800;
	public final int HEIGHT = 600;
	public final int ENEMY_SPEED = 2;
	public final int BOX_SPEED = 2;
	public final int DELAY = 10;
	public final int INIT_BOX_X = 50;
	public final int INIT_BOX_Y = 50;
}
