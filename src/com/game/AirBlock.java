package com.game;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

public class AirBlock extends Block{

	public AirBlock(int x, int y) {
		super(x, y);
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.air);
		glPopMatrix();
	}

	public boolean Solid() {
		return false;
	}

	public boolean isPathBlock() {
		return true;
	}
	
	public int getType(){
		return BlockType.air;
	}

}
