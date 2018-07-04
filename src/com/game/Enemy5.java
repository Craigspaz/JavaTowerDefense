package com.game;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class Enemy5 extends Enemy{
	public Enemy5(int x, int y) {
		super(x, y);
		type = BlockType.Enemy5;
		health = Health.Enemy5Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy5);
		glPopMatrix();
	}
}
