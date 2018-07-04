package com.game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Tower4 extends Tower{
	

	public Tower4(int x, int y) {
		super(x, y);
		radius = 5;
		type = BlockType.Tower4;
		cost = 1000;
		damage = Damage.Tower4Damage;
	}
	
	public void draw(){
		glPushMatrix();
		GFX.drawRect(32, 32, x, y, Textures.Tower4);
		glPopMatrix();
	}

	public void drawLaser(float xx, float yy) {
		glPushMatrix();
		GFX.drawLine(x+16, y+16, xx+16, yy+16,Textures.Laser);
		glPopMatrix();
	}
	
	public void towerUpgrades(Player player,int Mousex, int Mousey){
		glPushMatrix();
		GFX.drawRect(256, 128, 32, Display.getHeight()-6*32, Textures.RangeUpgrade);
		glPopMatrix();
		glPushMatrix();
		GFX.drawRect(256, 128, 32+520, Display.getHeight()-6*32, Textures.DamageUpgrade);
		glPopMatrix();
	}
	
	public void Range(Player player,int Mousex, int Mousey){
		if(player.money >= Price.T4_Range_Upgrade){
			if(Mouse.isButtonDown(0)){
				if(Mousex > 32 && Mousex < 32 + 256 && Mousey > Display.getHeight()-6*32 && Mousey < Display.getHeight()-6*32+256){
					if(radius < 10){
						radius++;
						player.money-=Price.T4_Range_Upgrade;
					}
					
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public void Damage(Player player,int Mousex, int Mousey){
		if(player.money >= Price.T4_Damage_Upgrade){
			if(Mouse.isButtonDown(0)){
				if(Mousex > 32+520 && Mousex < 32 + 520 + 256 && Mousey > Display.getHeight()-6*32 && Mousey < Display.getHeight()-6*32 + 256){
					if(Damage.Tower4Damage < 20){
						Damage.Tower4Damage++;
						player.money-=Price.T4_Damage_Upgrade;
					}
					
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
