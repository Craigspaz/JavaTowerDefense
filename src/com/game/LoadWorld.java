package com.game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LoadWorld {
	
	public static int startpointx = 0;
	public static int startpointy = 0;
	
	String path;
	//public static ArrayList<Block> block = new ArrayList<Block>();
	public static int[][] levelPixels = new int[32][22];
	
	public LoadWorld(String path){
		this.path = path;
		loadlevel(path);
		
	}
	
	public void loadlevel(String path){
		FileInputStream file;
		InputStreamReader reader;
		Scanner scanner;
		
		try{
			file = new FileInputStream(path);
			reader = new InputStreamReader(file);
			
			scanner = new Scanner(reader);
			
			int x = 0;
			int y = 0;
			
			while(scanner.hasNext()){
				levelPixels[x][y] = scanner.nextInt();
				
				if(x < 32-1){
					x++;
				}else{
					y++;
					x = 0;
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		gen();
	}	
	
	public void gen(){
		for(int x = 0; x < levelPixels.length;x++){
			for(int y = 0; y < levelPixels[0].length;y++){
				int num = levelPixels[x][y];
				if(num == 0){
					Block.blocks.add(new GrassBlock(x*32,y*32));
					Block.coord[x][y] = new GrassBlock(x*32,y*32);
				}else if(num == 1){
					Block.blocks.add(new DirtBlock(x*32,y*32));
					Block.coord[x][y] = new DirtBlock(x*32,y*32);
				}else if(num == 2){
					Block.blocks.add(new StoneBlock(x*32,y*32));
					Block.coord[x][y] = new StoneBlock(x*32,y*32);
					startpointx = x * 32;
					startpointy = y * 32;
				}else if(num == 3){
					Block.blocks.add(new AirBlock(x*32,y*32));
					Block.coord[x][y] = new AirBlock(x*32,y*32);
				}
			}
		}
	}
}
