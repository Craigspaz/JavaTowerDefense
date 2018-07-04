package com.game;
import static org.lwjgl.opengl.GL11.*;

public class DirtBlock extends Block{

	public DirtBlock(int x, int y) {
		super(x, y);
	}

	public void draw() {
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.dirt);
		glPopMatrix();
	}

	public boolean Solid() {
		return false;
	}

	public boolean isPathBlock() {
		return true;
	}

	public int getType() {
		return BlockType.dirt;
	}

}
