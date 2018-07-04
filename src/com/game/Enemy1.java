package com.game;
import static org.lwjgl.opengl.GL11.*;

public class Enemy1 extends Enemy{

	public Enemy1(int x, int y) {
		super(x, y);
		type = BlockType.Enemy1;
		health = Health.Enemy1Health;
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Enemy1);
		glPopMatrix();
	}

}
