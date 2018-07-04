package com.game;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class Enemy4 extends Enemy{
	public Enemy4(int x, int y) {
		super(x, y);
		type = BlockType.Enemy4;
		health = Health.Enemy4Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy4);
		glPopMatrix();
	}
}
