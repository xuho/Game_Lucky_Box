package com.me.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MainBoard extends JPanel implements ActionListener, Common {
	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	private Box box;
	private Timer timer;
	private boolean inGame;
	private int point;

	public MainBoard() {
		initBoard();
	}

	private void initBoard() {
		inGame = true;
		point = 0;
		addMouseMotionListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.lightGray);
		setDoubleBuffered(true);
		box = new Box(INIT_BOX_X, INIT_BOX_Y);
		enemies = new ArrayList<Enemy>();
		items = new ArrayList<Item>();
		initEnemy();
		initItem();
		inGame = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void initItem() {
		int initX = (int) (Math.random() * (Common.WIDTH - 50));
		int initY = (int) (Math.random() * (Common.HEIGHT - 50));
		Item item = new Item(initX, initY);
		items.add(item);
	}

	private void initEnemy() {
		int initX = (int) (Math.random() * (Common.WIDTH - 50));
		int initY = (int) (Math.random() * (Common.HEIGHT - 50));
		Enemy enemy = new Enemy(initX, initY);
		enemies.add(enemy);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (inGame) {
			drawObject(g);
		} else {
			drawGameOver(g);
		}
	}

	private void drawGameOver(Graphics g) {
		Font font = new Font("Helvetica", Font.BOLD, 20);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Game over!", 350, Common.HEIGHT / 2);
		g.drawString("Score: " + point, 370, Common.HEIGHT / 2 + 30);
	}

	private void drawObject(Graphics g) {
		// Draw Box
		g.drawImage(box.getImage(), box.getX(), box.getY(), this);
		// Draw Enemies
		for (Enemy enemy : enemies) {
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
		}
		// Draw Item
		for (Item item : items) {
			g.drawImage(item.getImage(), item.getX(), item.getY(), this);
		}

		// Draw Point
		Font font = new Font("Arial", Font.BOLD, 12);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("Your point: " + point, 5, 15);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();
		// Update Object
		updateBox();
		updateEnemy();
		updateItem();
		// Check collision
		checkCollision();
		// Repaint
		repaint();
	}

	private void checkCollision() {
		Rectangle boxBound = box.getBound();
		for (Enemy enemy : enemies) {
			Rectangle enemyBound = enemy.getBound();
			if (boxBound.intersects(enemyBound)) {
				inGame = false;
			}
		}

		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			Rectangle itemBound = item.getBound();
			if (boxBound.intersects(itemBound)) {
				point++;
				initEnemy();
				items.remove(i);
				initItem();
			}
		}
	}

	private void inGame() {
		if (!inGame) {
			timer.stop();
		}
	}

	private void updateBox() {
		box.move();
	}

	private void updateEnemy() {
		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			if (enemy.isVisible()) {
				enemy.move();
			} else {
				enemies.remove(i);
			}
		}
	}

	private void updateItem() {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if (!item.isVisible()) {
				items.remove(i);
			}
		}
	}

	private class TAdapter extends MouseMotionAdapter {
		@Override
		public void mouseMoved(MouseEvent e) {
			box.mouseMoved(e);
		}
	}
}
