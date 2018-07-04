package com.game;

import java.util.ArrayList;

public abstract class Enemy {
	
	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static Enemy[][] enemys = new Enemy[32][22];
	public boolean movingUp = false;
	public float speed = 0.005f;
	float x, y;
	boolean destroyed = false;
	int type;
	int health;
	
	public Enemy(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public boolean isDestroyed(){
		return destroyed;
	}
	
	public static Enemy getEnemy(int x, int y){
		return enemys[x][y];
	}
	
	public static Enemy getEnemy(Enemy e){
		return e;
	}
	
	public void setDestroyed(boolean destroyed){
		this.destroyed = destroyed;
	}
	
	public void setX(int amt){
		x = amt;
	}
	
	public int getType(){
		return type;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setY(int amt){
		y = amt;
	}
	
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	
	public abstract void draw();
	public void moveRight(){
		x+=speed;
		movingUp = false;
	}
	public void moveUp(){
		y-=speed;
		movingUp = true;
	}
	public void moveDown(){
		y+=speed;
		movingUp = false;
	}
	public void moveUpFinal(){
		y-=speed * 2;
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		moveUp(2);
		movingUp = false;
	}
	
	public static void removeEnemy(Enemy e){
		Enemy.enemies.remove(e);
	}
	
	public void moveUp(int amt){
		y-=amt;
	}
}
