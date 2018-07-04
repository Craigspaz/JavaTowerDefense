package com.game;

import java.util.ArrayList;
import java.util.Random;

public class Waves {

	static int numofEnemies;
	static Random random = new Random();
	public static int counter = 0;
	public static ArrayList<Enemy> wave = new ArrayList<Enemy>();
	
	public Waves(int numofEnemies){
		this.numofEnemies = numofEnemies;
		generateWave();
	}
	
	public static int getNumofEnemies(){
		return numofEnemies;
	}
	
	public static void start(){
		generateWave();
	}
	
	public static void generateWave(){
		for(int i = 0; i < numofEnemies;i++){
			int temp = random.nextInt();
			if(temp > 70){
				Waves.wave.add(new Enemy1(LoadWorld.startpointx,LoadWorld.startpointy));
			}else{
				Enemy.enemies.add(new Enemy2(LoadWorld.startpointx,LoadWorld.startpointy));
			}
		}
	}
}
