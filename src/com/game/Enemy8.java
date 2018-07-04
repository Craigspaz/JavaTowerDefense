package com.game;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class Enemy8 extends Enemy{
	public Enemy8(int x, int y) {
		super(x, y);
		type = BlockType.Enemy8;
		health = Health.Enemy8Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy8);
		glPopMatrix();
	}
}
