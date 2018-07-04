package com.game;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class Enemy3 extends Enemy{
	public Enemy3(int x, int y) {
		super(x, y);
		type = BlockType.Enemy3;
		health = Health.Enemy3Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy3);
		glPopMatrix();
	}
}
