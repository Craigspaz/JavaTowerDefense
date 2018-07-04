package com.game;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public abstract class Tower {
	int x;
	int y;
	int radius;
	int type;
	int cost;
	boolean isSelected = false;
	boolean isPlaced = false;
	boolean enemyInRange = false;
	public static boolean isTower1Selected = false;
	int damage;
	boolean lockedOn = false;
	
	public static ArrayList<Tower> towers = new ArrayList<Tower>();
	public static Tower[][] ts = new Tower[32][22];
	public Tower(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public boolean getEnemyInRange(){
		return enemyInRange;
	}
	
	public boolean getlockedOn(){
		return lockedOn;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int radius(){
		return radius;
	}
	
	public static Tower getTower(int x, int y){
		return Tower.ts[x][y];
	}
	
	public void setRadius(int amt){
		radius = amt;
	}
	
	public int getType(){
		return type;
	}
	
	public int cost(){
		return cost;
	}
	
	public void update(){
		int Mousex = Mouse.getX();
     	int Mousey = Display.getHeight() - Mouse.getY() - 1;
     	setX(Mousex);
     	setY(Mousey);
	}
	
	public boolean isPlaced(){
		return isPlaced;
	}
	
	public abstract void draw();
	public abstract void drawLaser(float x, float y);

}
