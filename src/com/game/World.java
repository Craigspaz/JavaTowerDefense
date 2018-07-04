package com.game;

import java.util.Random;

import org.lwjgl.opengl.Display;

public class World {
	public World(){
		//RandomGen();
		loadWorld();
	}
	
	public void loadWorld(){
		LoadWorld l = new LoadWorld("res/Worlds/World1.world");
	}
	
	public void RandomGen(){
		Random random = new Random();
		int width = Display.getWidth();
		int height = Display.getHeight();
		for(int x = 0; x < width-192;x+=Block.BlockSize){
			for(int y = 0; y < height-192;y+=Block.BlockSize){
				float temp = random.nextFloat();
				if(temp >=0.5f){
					Block.blocks.add(new GrassBlock(x,y));
				}else{
					Block.blocks.add(new DirtBlock(x,y));
				}
			}
		}
	}
	
}
