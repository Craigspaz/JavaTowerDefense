package com.game;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class Enemy7 extends Enemy{
	public Enemy7(int x, int y) {
		super(x, y);
		type = BlockType.Enemy7;
		health = Health.Enemy7Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy7);
		glPopMatrix();
	}
}
