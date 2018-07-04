package com.game;

import java.util.ArrayList;
import java.util.Random;

public class Waves {

	static int numofEnemies;
	static Random random = new Random();
	public static int counter = 0;
	public static ArrayList<Enemy> wave = new ArrayList<Enemy>();
	public static int waveNum = 0;
	
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
	
	public static void generateWave1(){
		
		for(int i = 0; i < numofEnemies;i++){
			int temp = random.nextInt();
			Waves.wave.add(new Enemy1(LoadWorld.startpointx,LoadWorld.startpointy));
		}
	}
	
	public static void generateWave1_10(){
		for(int i = 0; i < numofEnemies;i++){
			int temp = random.nextInt();
			if(temp > 70){
				Waves.wave.add(new Enemy1(LoadWorld.startpointx,LoadWorld.startpointy));
			}else{
				Enemy.enemies.add(new Enemy2(LoadWorld.startpointx,LoadWorld.startpointy));
			}
		}
	}
	
	public static void generateWave10_50(){
		for(int i = 0; i < numofEnemies;i++){
			int temp = random.nextInt();
			if(temp > 70){
				Waves.wave.add(new Enemy3(LoadWorld.startpointx,LoadWorld.startpointy));
			}else{
				Enemy.enemies.add(new Enemy4(LoadWorld.startpointx,LoadWorld.startpointy));
			}
		}
	}
	
	public static void generateWave50_70(){
		for(int i = 0; i < numofEnemies;i++){
			int temp = random.nextInt();
			if(temp > 70){
				Waves.wave.add(new Enemy5(LoadWorld.startpointx,LoadWorld.startpointy));
			}else{
				Enemy.enemies.add(new Enemy6(LoadWorld.startpointx,LoadWorld.startpointy));
			}
		}
	}
	public static void generateWave90(){
		for(int i = 0; i < numofEnemies;i++){
			int temp = random.nextInt();
			if(temp > 70){
				Waves.wave.add(new Enemy7(LoadWorld.startpointx,LoadWorld.startpointy));
			}else{
				Enemy.enemies.add(new Enemy8(LoadWorld.startpointx,LoadWorld.startpointy));
			}
		}
	}
}
