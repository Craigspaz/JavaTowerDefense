package com.game;
import static org.lwjgl.opengl.GL11.*;

public class GrassBlock extends Block{

	public GrassBlock(int x, int y) {
		super(x, y);
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.grassTop);
		glPopMatrix();
	}

	public boolean Solid() {
		return true;
	}

	public boolean isPathBlock() {
		return false;
	}

	public int getType() {
		return BlockType.grass;
	}

}
