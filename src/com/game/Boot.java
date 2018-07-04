package com.game;

import static org.lwjgl.opengl.GL11.GL_BLEND;
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

import java.util.Random;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Boot {
	
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

    public static void main(String[] args) {
        try {
        	DisplayMode display = new DisplayMode(1200,900);
            Display.setDisplayMode(display);
            Display.setTitle("Tower Defense 0.1.0");
            Display.setResizable(false);
            if(Display.wasResized()){
            	display = new DisplayMode(Display.getWidth(),Display.getHeight());
            }
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
        Textures textures = new Textures();
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
        while (!Display.isCloseRequested()) {
            // Render
            glClear(GL_COLOR_BUFFER_BIT);
            glLoadIdentity();
            
            int Mousex = Mouse.getX();
        	int Mousey = Display.getHeight() - Mouse.getY() - 1;

            if(state == State.INTRO){
            	Textures.grass.bind();
            	GFX.drawRect(Display.getWidth(),Display.getHeight(),0,0, Textures.dirt);
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
//            	//Draw Map Grid y
//            	GFX.drawLine(Display.getWidth()/16,0,Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(2 * Display.getWidth()/16,0,2 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(3 * Display.getWidth()/16,0,3 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(4 * Display.getWidth()/16,0,4 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(5 * Display.getWidth()/16,0,5 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(6 * Display.getWidth()/16,0,6 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(7 * Display.getWidth()/16,0,7 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(8 * Display.getWidth()/16,0,8 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(9 * Display.getWidth()/16,0,9 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(10 * Display.getWidth()/16,0,10 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(11 * Display.getWidth()/16,0,11 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(12 * Display.getWidth()/16,0,12 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(13 * Display.getWidth()/16,0,13 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(14 * Display.getWidth()/16,0,14 * Display.getWidth()/16,Display.getHeight());
//            	GFX.drawLine(15 * Display.getWidth()/16,0,15 * Display.getWidth()/16,Display.getHeight());
//            	
//            	//Draw Map Grid y
//            	
//            	GFX.drawLine(0,Display.getHeight()/16,Display.getWidth(),Display.getHeight()/16);
//            	GFX.drawLine(0,2 * Display.getHeight()/16,Display.getWidth(),2 * Display.getHeight()/16);
//            	GFX.drawLine(0,3 * Display.getHeight()/16,Display.getWidth(),3 * Display.getHeight()/16);
//            	GFX.drawLine(0,4 * Display.getHeight()/16,Display.getWidth(),4 * Display.getHeight()/16);
//            	GFX.drawLine(0,5 * Display.getHeight()/16,Display.getWidth(),5 * Display.getHeight()/16);
//            	GFX.drawLine(0,6 * Display.getHeight()/16,Display.getWidth(),6 * Display.getHeight()/16);
//            	GFX.drawLine(0,7 * Display.getHeight()/16,Display.getWidth(),7 * Display.getHeight()/16);
//            	GFX.drawLine(0,8 * Display.getHeight()/16,Display.getWidth(),8 * Display.getHeight()/16);
//            	GFX.drawLine(0,9 * Display.getHeight()/16,Display.getWidth(),9 * Display.getHeight()/16);
//            	GFX.drawLine(0,10 * Display.getHeight()/16,Display.getWidth(),10 * Display.getHeight()/16);
//            	GFX.drawLine(0,11 * Display.getHeight()/16,Display.getWidth(),11 * Display.getHeight()/16);
//            	GFX.drawLine(0,12 * Display.getHeight()/16,Display.getWidth(),12 * Display.getHeight()/16);
//            	GFX.drawLine(0,13 * Display.getHeight()/16,Display.getWidth(),13 * Display.getHeight()/16);
//            	GFX.drawLine(0,14 * Display.getHeight()/16,Display.getWidth(),14 * Display.getHeight()/16);
//            	GFX.drawLine(0,15 * Display.getHeight()/16,Display.getWidth(),15 * Display.getHeight()/16);
            	
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
        		glPopMatrix();
        		
        		
        		if(Mousex > 1050 && Mousex < 1050 + 32 && Mousey > 200 && Mousey < Mousey + 32){
        			if(Mouse.isButtonDown(0)){
        				itemSelected = true;
        				Tower.isTower1Selected = true;
        				Mouse.updateCursor();
        			}
        		}
        		if(itemSelected){
        			glPushMatrix();
            		GFX.drawRect(32, 32, Mousex, Mousey, Textures.Tower1);
            		glPopMatrix();
        		}
        		if(itemSelected){
        			if(Mouse.isButtonDown(1)){
        				if(Mousex/32 >= 32 || Mousey/32 >= 22 || Mousex/32 < 0 || Mousey/32 < 0 || Block.GetBlock(Mousex/32, Mousey/32).getType() != BlockType.grass || Tower.getTower(Mousex/32, Mousey/32) != null){
        					JOptionPane.showMessageDialog(Display.getParent(), "You cannot place a tower there!");
        				}else{
        					if(Tower.isTower1Selected && player.money >= Price.T1_Cost){
        						Tower.towers.add(new Tower1(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						Tower.ts[Mousex/32][Mousey/32] =(new Tower1(Block.GetBlock(Mousex/32, Mousey/32).x,Block.GetBlock(Mousex/32,Mousey/32).y));
        						player.money -= Price.T1_Cost;
        						Tower.isTower1Selected = false;
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
		        		if(counter == 30){
		        			Enemy e = Waves.wave.get(c);
		        			Enemy.enemies.add(e);
		        			System.out.println("Enemy added");
		        			counter = 0;
		        			c++;
		        		}
		        		counter++;
        			}else{
        				System.out.println("Wave Ended!");
        				waveStarted = false;
        				c = 0;
        				counter = 0;
        			}
        		}
        		
        		
        		glPushMatrix();
        		GFX.font2.drawString(Display.getWidth()-175, 0, "Health: " + player.health);
        		glPopMatrix();
        		glPushMatrix();
        		GFX.font2.drawString(Display.getWidth()-175, 30, "Money: " + player.money);
        		glPopMatrix();
        		
        		glPushMatrix();
        		GFX.drawRect(32, 32, Display.getWidth()-175, 60, Textures.Play);
        		glPopMatrix();
        		if(Mousex > Display.getWidth() - 175 && Mousex < Display.getWidth() - 175 + 32 && Mousey > 60 && Mousey < 60 + 32 && !waveStarted){
            		GFX.drawRect(32, 32, Display.getWidth()-175, 60, Textures.Play1);
            		//System.out.println("Mouse over button play");
            		if(Mouse.isButtonDown(0)){
            			new Waves(5);
            			waveStarted = true;
            			Waves.start();
            			counter = 0;
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
        				if(tower.x - 5 * 32 < e.x && tower.x + 5 * 32 > e.x && tower.y - 5 * 32 < e.y && tower.y + 5  * 32 > e.y && !tower.lockedOn){
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
}
