package com.game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

public class Boot{
	
	private static State state = State.INTRO;
	private static int width,height;
	public static int direction = 0;
	public static int upward = 0;
	public static int downward = 1;
	public static int right = 2;
	public static int mobWalk = 0;
	public static int x = 0,y = 0;
	public static float speed = 0.0005f;
	public static boolean itemSelected = false;
	public static int counter = 0,counter1 = 0;
	public static boolean gameover = false;
	public static boolean waveStarted = false;
	public static int c = 0;
	public static boolean EnemyInSpawn = false;
  
    
    public static void game(){
    	try {
        	DisplayMode display = new DisplayMode(1200,900);
            Display.setDisplayMode(display);
            Display.setTitle("Tower Defense 0.2.2");
            try {
				Display.setIcon(new ByteBuffer[] {
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("res/logo.png")), false, false, null),
				        new ImageIOImageData().imageToByteBuffer(ImageIO.read(new File("res/logo.png")), false, false, null)
				        });
			} catch (IOException e) {
				e.printStackTrace();
			}
            Display.setResizable(false);
            width = Display.getWidth();
            height = Display.getHeight();
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        // Initialization code OpenGL
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        try {
			Mouse.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
        World world = new World();
        //Enemy.enemies.add(new Enemy1(LoadWorld.startpointx,LoadWorld.startpointy));
        BlockType type = new BlockType();
        Player player = new Player();
        GFX.drawString();
        Textures textures = new Textures();
        while (!Display.isCloseRequested()) {
        	if(Display.wasResized()){
        		glViewport(0,0,Display.getWidth(),Display.getHeight());
        	}
            // Render
            glClear(GL_COLOR_BUFFER_BIT);
            glLoadIdentity();
            
            int Mousex = Mouse.getX();
        	int Mousey = Display.getHeight() - Mouse.getY() - 1;

            if(state == State.INTRO){
            	Textures.grass.bind();
            	GFX.drawRect(Display.getWidth(),Display.getHeight(),0,0, Textures.dirt);
            	GFX.font2.drawString(Display.getWidth()/2 - 128, Display.getHeight()/2, "Press Enter to continue!");
            	if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
            		state = State.MAIN_MENU;
            	}
            	
            }else if(state == State.MAIN_MENU){
            	GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 - 64, Textures.Play_First);
            	GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 + 64, Textures.Exit_First);
            	
            	if(Mousex > Display.getWidth()/2 - 256 && Mousex < Display.getWidth()/2 && Mousey > Display.getHeight()/2 - 64 && Mousey < Display.getHeight()/2){
            		GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 - 64, Textures.Play_Second);
            		System.out.println("Mouse over button play");
            		if(Mouse.isButtonDown(0)){
            			System.out.println("Play");
            			state = State.GAME;
            		}
            	}
            	if(Mousex > Display.getWidth()/2 - 256 && Mousex < Display.getWidth()/2 && Mousey > Display.getHeight()/2 + 64 && Mousey > Display.getHeight()/2){
            		GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 + 64, Textures.Exit_Second);
            		System.out.println("Mouse over button exit");
            		if(Mouse.isButtonDown(0)){
            			Display.destroy();
            			System.exit(0);
            		}
            	}
            	
            	//System.out.println(Mousex + " " + Mousey);
            
            }else if(state == State.GAME){            	
            	for(Block block: Block.blocks){
            		block.draw();
            	}
            	
            	for(Enemy e : Enemy.enemies){
            		e.draw();
            		for(Block block : Block.blocks){
            			if(Block.GetBlock((int)(e.x + 32)/32, (int)(e.y)/32).getType() == BlockType.dirt){
            				if(e.movingUp){
            					e.moveUpFinal();
            					//e.moveUp();
            					e.movingUp = false;
            				}else{
            					e.moveRight();
            					//System.out.println("Moving Right!");
            				}
            			}else if(Block.GetBlock((int)(e.x)/32, (int)(e.y + 32)/32).getType() == BlockType.dirt && !e.movingUp){
            				e.moveDown();
            				//System.out.println("Moving Down!");
            			}
            			else if(Block.GetBlock((int)(e.x)/32, (int)(e.y - 32)/32).getType() == BlockType.dirt){
            				e.moveUp();
            				//System.out.println("Moving Up!");
            			}
            			else if(Block.GetBlock((int)(e.x + 32)/32, (int)(e.y)/32).getType() == BlockType.air && !e.isDestroyed()){
            				e.x = Block.GetBlock((int)(e.x)/32,(int)(e.y)/32).x;
            				e.y = Block.GetBlock((int)(e.x)/32,(int)(e.y)/32).y;
            				if(e.getType() == BlockType.Enemy1){
            					player.health--;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				if(e.getType() == BlockType.Enemy2){
            					player.health-=2;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				if(e.getType() == BlockType.Enemy3){
            					player.health-=3;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				if(e.getType() == BlockType.Enemy4){
            					player.health-=4;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				if(e.getType() == BlockType.Enemy5){
            					player.health-=5;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				if(e.getType() == BlockType.Enemy6){
            					player.health-=6;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				if(e.getType() == BlockType.Enemy7){
            					player.health-=7;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				if(e.getType() == BlockType.Enemy8){
            					player.health-=8;
            					if(player.health <= 0){
            						player.health = 0;
            					}
            				}
            				System.out.println(player.health);
            				e.setDestroyed(true);
            				
            				//System.out.println("Done");
            			}
                	}
            	}
            	for(Enemy e: Enemy.enemies){
            		if(e.isDestroyed()){
            			e.removeEnemy(e);
            			break;
            		}
            	}
            	
            	if(player.health <= 0){
            		gameover = true;
            	}
            	
            	if(gameover){
            		System.out.println("Game is over");
            		glPushMatrix();
            		GFX.font2.drawString(Display.getWidth()/2-200, Display.getHeight()/2, "Game Over!");
            		glPopMatrix();
            	}
            	
//            	for(Tower towers: Tower.towers){
//                	towers.draw();
//	
//                	if(Mousex > towers.x && Mousex < towers.x + 32 && Mousey > towers.y && Mousey < towers.y + 32 && !towers.isSelected){
//                		if(Mouse.isButtonDown(0)){
//                			towers.update();
//                			towers.isSelected = true;
//                		}
//                	}
//                }
//            	for(Tower towers: Tower.towers){
//            		if(towers.isSelected){
//                		towers.update();
//                		if(Mouse.isButtonDown(1)){
//                			System.out.println(Mousex + " : " + Mousey);
//                			System.out.println(Mousex/32 + " : " + Mousey/32);
//                			int x = Mousex;
//                			int y = Mousey;
//                			if(Mousex/32 >= 32 || Mousey/32 >= 22 || Mousex/32 < 0 || Mousey/32 < 0){
//                				JOptionPane.showMessageDialog(Display.getParent(), "You cannot place a tower there");
//                			}else{
//	                			towers.setX(Block.GetBlock(Mousex/32, Mousey/32).x);
//	                			towers.setY(Block.GetBlock(Mousex/32, Mousey/32).y);
//	                			towers.isPlaced = true;
//	                			towers.isSelected = false;
//                			}
//                		}
//                	}
//            	}
            	glPushMatrix();
        		GFX.drawRect(32, 32, 1050, 200, Textures.Tower1);
        		GFX.font2.drawString(1050 + 32, 200+30, "10");
        		
        		GFX.drawRect(32, 32, 1050, 200 + 100,Textures.Tower2);
        		GFX.font2.drawString(1050 + 32, 200 + 130, "30");
        		
        		GFX.drawRect(32, 32, 1050, 200 + 200,Textures.Tower3);
        		GFX.font2.drawString(1050 + 32, 200 + 230, "100");
        		
        		GFX.drawRect(32, 32, 1050, 200+300,Textures.Tower4);
        		GFX.font2.drawString(1050 + 32, 200 + 330, "1000");
        		
        		GFX.drawRect(32, 32, 1050, 200 + 430, Textures.Trash);
        		glPopMatrix();
        		
        		
        		if(Mousex > 1050 && Mousex < 1050 + 32 && Mousey > 200 && Mousey < 200 + 32){
        			if(Mouse.isButtonDown(0)){
        				itemSelected = true;
        				Tower.isTower1Selected = true;
        				Mouse.updateCursor();
        				System.out.println("Tower1");
        			}
        		}
        		if(Mousex > 1050 && Mousex < 1050 + 32 && Mousey > 200+100 && Mousey < 300 + 32){
        			if(Mouse.isButtonDown(0)){
        				itemSelected = true;
        				Tower.isTower2Selected = true;
        				Mouse.updateCursor();
        				System.out.println("Tower2");
        			}
        		}
        		if(Mousex > 1050 && Mousex < 1050 + 32 && Mousey > 200 + 200 && Mousey < 200 + 200 + 32){
        			if(Mouse.isButtonDown(0)){
        				itemSelected = true;
        				Tower.isTower3Selected = true;
        				Mouse.updateCursor();
        				System.out.println("Tower3");
        			}
        		}
        		if(Mousex > 1050 && Mousex < 1050 + 32 && Mousey > 200 + 300 && Mousey < 200 + 300 + 32){
        			if(Mouse.isButtonDown(0)){
        				itemSelected = true;
        				Tower.isTower4Selected = true;
        				Mouse.updateCursor();
        				System.out.println("Tower4");
        			}
        		}
        		
        		
        		if(itemSelected && Tower.isTower1Selected){
        			glPushMatrix();
            		GFX.drawRect(32, 32, Mousex, Mousey, Textures.Tower1);
            		glPopMatrix();
        		}
        		if(itemSelected && Tower.isTower2Selected){
        			glPushMatrix();
            		GFX.drawRect(32, 32, Mousex, Mousey, Textures.Tower2);
            		glPopMatrix();
        		}
        		if(itemSelected && Tower.isTower3Selected){
        			glPushMatrix();
            		GFX.drawRect(32, 32, Mousex, Mousey, Textures.Tower3);
            		glPopMatrix();
        		}
        		if(itemSelected && Tower.isTower4Selected){
        			glPushMatrix();
            		GFX.drawRect(32, 32, Mousex, Mousey, Textures.Tower4);
            		glPopMatrix();
        		}
        		if(itemSelected){
        			if(Mouse.isButtonDown(1)){
        				
        				if(Mousex > 1050 && Mousex < 1050 + 32 && Mousey > 200+430 && Mousey < 200+430+32){
        					Tower.isTower1Selected = false;
        					Tower.isTower2Selected = false;
        					Tower.isTower3Selected = false;
        					Tower.isTower4Selected = false;
        					itemSelected = false;
        				}else if(Mousex/32 >= 32 || Mousey/32 >= 22 || Mousex/32 < 0 || Mousey/32 < 0 || Block.GetBlock(Mousex/32, Mousey/32).getType() != BlockType.grass || Tower.getTower(Mousex/32, Mousey/32) != null){
        					JOptionPane.showMessageDialog(Display.getParent(), "You cannot place a tower there!");
        				}else{
        					if(Tower.isTower1Selected && player.money >= Price.T1_Cost){
        						Tower.towers.add(new Tower1(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						Tower.ts[Mousex/32][Mousey/32] =(new Tower1(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						player.money -= Price.T1_Cost;
        						Tower.isTower1Selected = false;
        						itemSelected = false;
        					}else
        					if(Tower.isTower2Selected && player.money >= Price.T2_Cost){
        						Tower.towers.add(new Tower2(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						Tower.ts[Mousex/32][Mousey/32] =(new Tower2(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						player.money -= Price.T2_Cost;
        						Tower.isTower2Selected = false;
        						itemSelected = false;
        					}else
        					if(Tower.isTower3Selected && player.money >= Price.T3_Cost){
        						Tower.towers.add(new Tower3(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						Tower.ts[Mousex/32][Mousey/32] =(new Tower3(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						player.money -= Price.T3_Cost;
        						Tower.isTower3Selected = false;
        						itemSelected = false;
        					}else
        					if(Tower.isTower4Selected && player.money >= Price.T4_Cost){
        						Tower.towers.add(new Tower4(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						Tower.ts[Mousex/32][Mousey/32] =(new Tower4(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						player.money -= Price.T4_Cost;
        						Tower.isTower4Selected = false;
        						itemSelected = false;
        					}
        				}
        			}
        		}
        		for(Tower tower : Tower.towers){
        			tower.draw();
        		}
        		
        		
        		
        		//Test Wave
        		if(waveStarted){
        			int temp = Waves.wave.size();
        			if(c < temp){
		        		if(counter == 30 && !EnemyInSpawn){
		        			Enemy e = Waves.wave.get(c);
		        			Enemy.enemies.add(e);
		        			System.out.println("Enemy added");
		        			counter = 0;
		        			c++;
		        			EnemyInSpawn = true;
		        		}
		        		if(EnemyInSpawn){
		        			Enemy e = Waves.wave.get(c);
	            			if(e.x > 32){
	            				EnemyInSpawn = false;
	            				System.out.println("Enemy add aborted");
	            			}
	            		}
		        		counter++;
        			}else{
        				System.out.println("Wave Ended!");
        				waveStarted = false;
        				c = 0;
        				counter = 0;
        				Waves.wave.clear();
        			}
        		}
        		
        		
        		
        	
        		
        		
        		glPushMatrix();
        		GFX.font2.drawString(Display.getWidth()-175, 0, "Health: " + player.health);
        		glPopMatrix();
        		glPushMatrix();
        		GFX.font2.drawString(Display.getWidth()-175, 30, "Money: " + player.money);
        		glPopMatrix();
        		glPushMatrix();
        		GFX.font2.drawString(Display.getWidth()-175, 90, "Wave: " + Waves.waveNum);
        		glPopMatrix();
        		
        		glPushMatrix();
        		GFX.drawRect(32, 32, Display.getWidth()-175, 60, Textures.Play);
        		glPopMatrix();
        		if(Mousex > Display.getWidth() - 175 && Mousex < Display.getWidth() - 175 + 32 && Mousey > 60 && Mousey < 60 + 32 && !waveStarted){
            		GFX.drawRect(32, 32, Display.getWidth()-175, 60, Textures.Play1);
            		//System.out.println("Mouse over button play");
            		if(Mouse.isButtonDown(0)){
            			if(Waves.waveNum+1 == 1){
            				new Waves(5);
							Waves.generateWave1();
            				waveStarted = true;
                			Waves.waveNum++;
                			counter = 0;
                			c = 0;
            			}else if(Waves.waveNum + 1 > 1 && Waves.waveNum < 10){
            				new Waves(30 * (Waves.waveNum + 1) - 20);
            				waveStarted = true;
                			Waves.waveNum++;
                			Waves.generateWave1_10();
                			counter = 0;
                			c = 0;
            			}else if(Waves.waveNum >= 10 && Waves.waveNum < 50){
            				new Waves(30 * (Waves.waveNum + 1) - 20);
            				waveStarted = true;
                			Waves.waveNum++;
                			Waves.generateWave10_50();
                			counter = 0;
                			c = 0;
            			}else if(Waves.waveNum >= 50 && Waves.waveNum < 70){
            				new Waves(30 * (Waves.waveNum + 1) - 20);
            				waveStarted = true;
                			Waves.waveNum++;
                			Waves.generateWave50_70();
                			counter = 0;
                			c = 0;
            			}else{
            				new Waves(30 * (Waves.waveNum + 1) - 20);
            				waveStarted = true;
                			Waves.waveNum++;
                			Waves.generateWave90();
                			counter = 0;
                			c = 0;
            			}
            			
            			try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
            			System.out.println("New Wave");
            		}
            	}
        		glPushMatrix();
        		GFX.drawRect(32, 32, Display.getWidth()-175 +32, 60, Textures.Pause);
        		glPopMatrix();
        		if(Mousex > Display.getWidth() - 175 +32 && Mousex < Display.getWidth() - 175 + 64 && Mousey > 60 && Mousey < 60 + 32){
            		GFX.drawRect(32, 32, Display.getWidth()-175 +32, 60, Textures.Pause1);
            		//System.out.println("Mouse over button play");
            		if(Mouse.isButtonDown(0)){
            			System.out.println("Pause Game");
            			state = State.INGAME;
            		}
            	}
        		
        		
        		
        		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
        			state = State.INGAME;
        		}
        		
        		for(Enemy e : Enemy.enemies){
        			for(Tower tower : Tower.towers){
        				if(tower.x - tower.radius * 32 < e.x && tower.x + tower.radius * 32 > e.x && tower.y - tower.radius * 32 < e.y && tower.y + tower.radius  * 32 > e.y && !tower.lockedOn){
        					tower.lockedOn = true;
        					tower.drawLaser(e.x, e.y);
        					//System.out.println("Laser!!!!");
        					e.health -= tower.damage;
        					if(e.health <= 0){
        						e.destroyed = true;
        						player.money += 1;
        					}
        					tower.lockedOn = false;
        				}
        			}
        		}
        		
        		for(Tower tower : Tower.towers){
        			if(Mousex > tower.x && Mousex < tower.x + 32 && Mousey > tower.y && Mousey < tower.y + 32){
        				if(Mouse.isButtonDown(0) && !Tower.globalSelected){
        					//System.out.println("Tower Upgrades!");
        					tower.settowerForUpgradeisSelected(true, tower);
        					Tower.globalSelected = true;
        				}else if(Mouse.isButtonDown(0) && Tower.globalSelected){
        					for(Tower t : Tower.towers){
        						t.settowerForUpgradeisSelected(false, t);
        						//System.out.println("Tower Upgrades!");
            					tower.settowerForUpgradeisSelected(true, tower);
            					Tower.globalSelected = true;
            					//System.out.println("Tower2");
        					}
        					
        				}
        			}
        			
        			if(tower.gettowerForUpgradeisSelected() && Tower.globalSelected){
        				tower.towerUpgrades(player,Mousex,Mousey);
        				tower.Range(player,Mousex,Mousey);
        				tower.Damage(player, Mousex, Mousey);
        			}
        			
        		}
        		
            }else if(state == State.INGAME){
            	
            	GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 - 64, Textures.Resume_First);
            	GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 + 64, Textures.Exit_First);
            	
            	if(Mousex > Display.getWidth()/2 - 256 && Mousex < Display.getWidth()/2 && Mousey > Display.getHeight()/2 - 64 && Mousey < Display.getHeight()/2){
            		GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 - 64, Textures.Resume_Second);
            		System.out.println("Mouse over button play");
            		if(Mouse.isButtonDown(0)){
            			System.out.println("Play");
            			state = State.GAME;
            		}
            	}
            	if(Mousex > Display.getWidth()/2 - 256 && Mousex < Display.getWidth()/2 && Mousey > Display.getHeight()/2 + 64 && Mousey > Display.getHeight()/2){
            		GFX.drawRect(256, 64, Display.getWidth()/2 - 256,Display.getHeight()/2 + 64, Textures.Exit_Second);
            		System.out.println("Mouse over button exit ingame");
            		if(Mouse.isButtonDown(0)){
            			
            			Block.blocks.clear();
            			Tower.towers.clear();
            			Enemy.enemies.clear();
            			
            			World world1 = new World();
            			player.health = 100;
            			player.money = 100;
            	        
            	        try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
            			
            			state = State.MAIN_MENU;
            		}
            	}
            	            	
            }

            Display.update();
            Display.sync(60);
            if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
                Display.destroy();
                System.exit(0);
            }
        }
        Display.destroy();
        System.exit(0);
    }
    
    public static void main(String[] args) {
        game();
    }
}
