package com.me.game;

import java.awt.EventQueue;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LuckyBox extends JFrame implements Common{

	public LuckyBox() {
		setTitle("My first game");
		add(new MainBoard());
		setSize(Common.WIDTH, Common.HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new LuckyBox().setVisible(true);
			}
		});
	}

}
