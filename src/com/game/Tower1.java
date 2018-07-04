package com.game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class Tower1 extends Tower{
	

	public Tower1(int x, int y) {
		super(x, y);
		radius = 25;
		type = BlockType.Tower1;
		cost = 10;
		damage = Damage.Tower1Damage;
	}
	
	public void draw(){
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Tower1);
		glPopMatrix();
	}

	public void drawLaser(float xx, float yy) {
		glPushMatrix();
		GFX.drawLine(x+16, y+16, xx+16, yy+16,Textures.Laser);
		glPopMatrix();
	}
	
	

}
