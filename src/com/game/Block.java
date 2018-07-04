package com.game;

import java.util.ArrayList;

public abstract class Block {
	int x = 0, y = 0;
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static Block[][] coord = new Block[32][22]; 
	public static int BlockSize = 32;
	public static GrassBlock grassblock;
	public static DirtBlock dirtblock;
	public static StoneBlock stoneblock;
	public static AirBlock airblock;
	BlockType type;
	
	public Block(int x, int y){
		this.x = x;
		this.y = y;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public static Block getBlock(int x,int y){
		if(x > 0 && x + 1 < 32 && y - 1 > 0 && y + 1 < 22){
			return coord[x][y];
		}else{
			return null;
		}
	}
	
	public static Block GetBlock(int x,int y){
		if(y - 1 < 0){
			y = 0;
		}
		if(y + 1 > 22){
			y = 21;
		}
		if(x + 1 > 32){
			x = 31;
		}
		return coord[x][y];
	}
		
	public static int getType(int x,int y){
		return coord[x][y].getType();
	}
	public abstract int getType();
	public abstract void draw();
	public abstract boolean Solid();
	public abstract boolean isPathBlock();
}
