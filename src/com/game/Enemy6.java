package com.game;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class Enemy6 extends Enemy{
	public Enemy6(int x, int y) {
		super(x, y);
		type = BlockType.Enemy6;
		health = Health.Enemy6Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy6);
		glPopMatrix();
	}
}
