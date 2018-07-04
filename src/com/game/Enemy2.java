package com.game;
import static org.lwjgl.opengl.GL11.*;

public class Enemy2 extends Enemy{

	public Enemy2(int x, int y) {
		super(x, y);
		type = BlockType.Enemy2;
		health = Health.Enemy2Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy2);
		glPopMatrix();
	}

}
