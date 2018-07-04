package com.game;
import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;


public class GFX {
	
	public static TrueTypeFont font2;
	
	
	public static void drawCube(float x,float y,float z,float xx, float yy, float zz){
			//glLoadIdentity();
			glTranslatef(xx,yy,zz);
			glPushMatrix();
			glBegin(GL_QUADS);
				//Front face
				glColor3f(1f,0f,0f);
				glVertex3f(-x,-y,z);
				glVertex3f(-x,y,z);
				glVertex3f(x,y,z);
				glVertex3f(x,-y,z);
				//Backface
				glColor3f(0f,1f,0f);
				glVertex3f(-x,-y,-z);
				glVertex3f(-x,y,-z);
				glVertex3f(x,y,-z);
				glVertex3f(x,-y,-z);
				//Bottumface
				glColor3f(0f,0f,1f);
				glVertex3f(-x,-y,-z);
				glVertex3f(-x,-y,z);
				glVertex3f(-x,y,z);
				glVertex3f(-x,y,-z);
				//Top Face
				glColor3f(1f,0f,1f);
				glVertex3f(x,-y,-z);
				glVertex3f(x,-y,z);
				glVertex3f(x,y,z);
				glVertex3f(x,y,-z);
				
				glColor3f(.75f,.05f,.05f);
				glVertex3f(-x,-y,-z);
				glVertex3f(x,-y,-z);
				glVertex3f(x,-y,z);
				glVertex3f(-x,-y,z);
				
				glColor3f(1f,1f,1f);
				glVertex3f(-x,y,-z);
				glVertex3f(x,y,-z);
				glVertex3f(x,y,z);
				glVertex3f(-x,y,z);
			glEnd();
		glPopMatrix();
	}
		
	public static void drawCube(float x,float y,float z,float xx, float yy, float zz,Texture texture){
			texture.bind();
			//glLoadIdentity();
			glTranslatef(xx,yy,zz);
			glPushMatrix();
			glBegin(GL_QUADS);
				//Front face
				//glColor3f(1f,0f,0f);
				glTexCoord2f(1,1);
				glVertex3f(-x,-y,z);
				glTexCoord2f(1,0);
				glVertex3f(-x,y,z);
				glTexCoord2f(0,0);
				glVertex3f(x,y,z);
				glTexCoord2f(0,1);
				glVertex3f(x,-y,z);
				//Backface
				//glColor3f(0f,1f,0f);
				glTexCoord2f(1,1);
				glVertex3f(-x,-y,-z);
				glTexCoord2f(1,0);
				glVertex3f(-x,y,-z);
				glTexCoord2f(0,0);
				glVertex3f(x,y,-z);
				glTexCoord2f(0,1);
				glVertex3f(x,-y,-z);
				//left
				//glColor3f(0f,0f,1f);
				glTexCoord2f(0,1);
				glVertex3f(-x,-y,-z);
				glTexCoord2f(1,1);
				glVertex3f(-x,-y,z);
				glTexCoord2f(1,0);
				glVertex3f(-x,y,z);
				glTexCoord2f(0,0);
				glVertex3f(-x,y,-z);
				//right
				//glColor3f(1f,0f,1f);
				glTexCoord2f(1,1);
				glVertex3f(x,-y,-z);
				glTexCoord2f(0,1);
				glVertex3f(x,-y,z);
				glTexCoord2f(0,0);
				glVertex3f(x,y,z);
				glTexCoord2f(1,0);
				glVertex3f(x,y,-z);
				
				//Bottom Face
				//glColor3f(.75f,.05f,.05f);
				glTexCoord2f(1,1);
				glVertex3f(-x,-y,-z);
				glTexCoord2f(1,0);
				glVertex3f(x,-y,-z);
				glTexCoord2f(0,0);
				glVertex3f(x,-y,z);
				glTexCoord2f(0,1);
				glVertex3f(-x,-y,z);
				
				//top
				//glColor3f(1f,1f,1f);
				glTexCoord2f(1,1);
				glVertex3f(-x,y,-z);
				glTexCoord2f(1,0);
				glVertex3f(x,y,-z);
				glTexCoord2f(0,0);
				glVertex3f(x,y,z);
				glTexCoord2f(0,1);
				glVertex3f(-x,y,z);
			glEnd();
		glPopMatrix();
	}
	public static void drawGrass(float x,float y,float z,float xx, float yy, float zz,Texture texture,Texture top,Texture bottom){
		texture.bind();
		//glLoadIdentity();
		glTranslatef(xx,yy,zz);
		glPushMatrix();
		glBegin(GL_QUADS);
			//Front face
			//glColor3f(1f,0f,0f);
			glTexCoord2f(1,1);
			glVertex3f(-x,-y,z);
			glTexCoord2f(1,0);
			glVertex3f(-x,y,z);
			glTexCoord2f(0,0);
			glVertex3f(x,y,z);
			glTexCoord2f(0,1);
			glVertex3f(x,-y,z);
			//Backface
			//glColor3f(0f,1f,0f);
			glTexCoord2f(1,1);
			glVertex3f(-x,-y,-z);
			glTexCoord2f(1,0);
			glVertex3f(-x,y,-z);
			glTexCoord2f(0,0);
			glVertex3f(x,y,-z);
			glTexCoord2f(0,1);
			glVertex3f(x,-y,-z);
			//left
			//glColor3f(0f,0f,1f);
			glTexCoord2f(0,1);
			glVertex3f(-x,-y,-z);
			glTexCoord2f(1,1);
			glVertex3f(-x,-y,z);
			glTexCoord2f(1,0);
			glVertex3f(-x,y,z);
			glTexCoord2f(0,0);
			glVertex3f(-x,y,-z);
			//right
			//glColor3f(1f,0f,1f);
			glTexCoord2f(1,1);
			glVertex3f(x,-y,-z);
			glTexCoord2f(0,1);
			glVertex3f(x,-y,z);
			glTexCoord2f(0,0);
			glVertex3f(x,y,z);
			glTexCoord2f(1,0);
			glVertex3f(x,y,-z);
			
//			//Bottom Face
//			//glColor3f(.75f,.05f,.05f);
//			glTexCoord2f(1,1);
//			glVertex3f(-x,-y,-z);
//			glTexCoord2f(1,0);
//			glVertex3f(x,-y,-z);
//			glTexCoord2f(0,0);
//			glVertex3f(x,-y,z);
//			glTexCoord2f(0,1);
//			glVertex3f(-x,-y,z);
			
			//top
			
			//top.bind();
			//glColor3f(1f,1f,1f);
//			glTexCoord2f(1,1);
//			glVertex3f(-x,y,-z);
//			glTexCoord2f(1,0);
//			glVertex3f(x,y,-z);
//			glTexCoord2f(0,0);
//			glVertex3f(x,y,z);
//			glTexCoord2f(0,1);
//			glVertex3f(-x,y,z);
		glEnd();
	glPopMatrix();
	
	
	//glLoadIdentity();
	//glPushMatrix();
	//glBindTexture(GL_TEXTURE_2D, top.getTextureID());
	top.bind();
	glBegin(GL_QUADS);
		//glColor3f(1f,1f,1f);
		glTexCoord2f(1,1);
		glVertex3f(-x,y,-z);
		glTexCoord2f(1,0);
		glVertex3f(x,y,-z);
		glTexCoord2f(0,0);
		glVertex3f(x,y,z);
		glTexCoord2f(0,1);
		glVertex3f(-x,y,z);
	glEnd();
	
	
	//Bottom Face
	//glColor3f(.75f,.05f,.05f);
	bottom.bind();
	glBegin(GL_QUADS);
		glTexCoord2f(1,1);
		glVertex3f(-x,-y,-z);
		glTexCoord2f(1,0);
		glVertex3f(x,-y,-z);
		glTexCoord2f(0,0);
		glVertex3f(x,-y,z);
		glTexCoord2f(0,1);
		glVertex3f(-x,-y,z);
	glEnd();
	//glPopMatrix();
	//glLoadIdentity();
	}

	public static void drawRect(float x, float y, float xx, float yy, Texture texture){
		texture.bind();
		glPushMatrix();
		//glTranslatef(xx,yy,0);
		glBegin(GL_QUADS);
		
		glTexCoord2f(0,0);
		glVertex2f(xx,yy);
		glTexCoord2f(0,1);
		glVertex2f(xx,y + yy);
		glTexCoord2f(1,1);
		glVertex2f(x + xx,y + yy);
		glTexCoord2f(1,0);
		glVertex2f(x + xx,yy);
		
		glEnd();
		glPopMatrix();
	}
	
	public static void drawRect(float x, float y, float xx, float yy){
		glPushMatrix();
		//glTranslatef(xx,yy,0);
		glBegin(GL_QUADS);
		
		glVertex2f(xx,yy);
		glVertex2f(xx,y + yy);
		glVertex2f(x + xx,y + yy);
		glVertex2f(x + xx,yy);
		
		glEnd();
		glPopMatrix();
	}
	
	
	public static void drawTestCube(float x,float y,float z,float xx, float yy, float zz,Texture texture){
		texture.bind();
		//glLoadIdentity();
		//glTranslatef(xx,yy,zz);
		glPushMatrix();
		glBegin(GL_QUADS);
			//Front face
			//glColor3f(1f,0f,0f);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx,-y + yy,z + zz);
			glTexCoord2f(1,0);
			glVertex3f(-x + xx,y + yy,z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx,y + yy,z + zz);
			glTexCoord2f(0,1);
			glVertex3f(x + xx,-y + yy,z + zz);
			//Backface
			//glColor3f(0f,1f,0f);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx,-y + yy ,-z + zz);
			glTexCoord2f(1,0);
			glVertex3f(-x + xx,y + yy ,-z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx ,y + yy ,-z + zz);
			glTexCoord2f(0,1);
			glVertex3f(x + xx,-y + yy,-z + zz);
			//left
			//glColor3f(0f,0f,1f);
			glTexCoord2f(0,1);
			glVertex3f(-x + xx,-y + yy,-z + zz);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx,-y + yy ,z + zz);
			glTexCoord2f(1,0);
			glVertex3f(-x + xx,y + yy ,z + zz);
			glTexCoord2f(0,0);
			glVertex3f(-x + xx,y + yy,-z + zz);
			//right
			//glColor3f(1f,0f,1f);
			glTexCoord2f(1,1);
			glVertex3f(x + xx,-y + yy,-z + zz);
			glTexCoord2f(0,1);
			glVertex3f(x + xx,-y + yy ,z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx,y + yy,z + zz);
			glTexCoord2f(1,0);
			glVertex3f(x + xx,y + yy,-z + zz);
			
			//Bottom Face
			//glColor3f(.75f,.05f,.05f);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx,-y + yy,-z + zz);
			glTexCoord2f(1,0);
			glVertex3f(x + xx,-y + yy ,-z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx,-y + yy,z + zz);
			glTexCoord2f(0,1);
			glVertex3f(-x + xx,-y + yy,z + zz);
			
			//top
			//glColor3f(1f,1f,1f);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx,y + yy,-z + zz);
			glTexCoord2f(1,0);
			glVertex3f(x + xx,y + yy,-z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx,y + yy,z + zz);
			glTexCoord2f(0,1);
			glVertex3f(-x + xx,y + yy,z + zz);
		glEnd();
	glPopMatrix();
}
	
	public static void drawGrassTest(float x,float y,float z,float xx, float yy, float zz,Texture texture,Texture top,Texture bottom){
		texture.bind();
		//glLoadIdentity();
		//glTranslatef(xx,yy,zz);
		glPushMatrix();
		glBegin(GL_QUADS);
			//Front face
			//glColor3f(1f,0f,0f);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx,-y + yy,z + zz);
			glTexCoord2f(1,0);
			glVertex3f(-x + xx,y + yy,z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx,y + yy,z + zz);
			glTexCoord2f(0,1);
			glVertex3f(x + xx,-y + yy,z + zz);
			//Backface
			//glColor3f(0f,1f,0f);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx,-y + yy,-z + zz);
			glTexCoord2f(1,0);
			glVertex3f(-x + xx,y + yy,-z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx,y + yy,-z + zz);
			glTexCoord2f(0,1);
			glVertex3f(x + xx,-y + yy,-z + zz);
			//left
			//glColor3f(0f,0f,1f);
			glTexCoord2f(0,1);
			glVertex3f(-x + xx,-y + yy,-z + zz);
			glTexCoord2f(1,1);
			glVertex3f(-x + xx ,-y + yy,z + zz);
			glTexCoord2f(1,0);
			glVertex3f(-x + xx,y + yy,z +zz);
			glTexCoord2f(0,0);
			glVertex3f(-x + xx,y + yy,-z + zz);
			//right
			//glColor3f(1f,0f,1f);
			glTexCoord2f(1,1);
			glVertex3f(x + xx,-y + yy ,-z + zz);
			glTexCoord2f(0,1);
			glVertex3f(x + xx,-y + yy,z + zz);
			glTexCoord2f(0,0);
			glVertex3f(x + xx,y + yy,z + zz);
			glTexCoord2f(1,0);
			glVertex3f(x + xx,y + yy,-z + zz);
			glEnd();
	glPopMatrix();
	
	
	//glLoadIdentity();
	//glPushMatrix();
	//glBindTexture(GL_TEXTURE_2D, top.getTextureID());
	top.bind();
	glBegin(GL_QUADS);
		//glColor3f(1f,1f,1f);
		glTexCoord2f(1,1);
		glVertex3f(-x + xx,y + yy,-z + zz);
		glTexCoord2f(1,0);
		glVertex3f(x + xx,y + yy,-z + zz);
		glTexCoord2f(0,0);
		glVertex3f(x + xx,y + yy,z + zz);
		glTexCoord2f(0,1);
		glVertex3f(-x + xx,y + yy,z + zz);
	glEnd();
	
	
	//Bottom Face
	//glColor3f(.75f,.05f,.05f);
	bottom.bind();
	glBegin(GL_QUADS);
		glTexCoord2f(1,1);
		glVertex3f(-x + xx,-y + yy,-z + zz);
		glTexCoord2f(1,0);
		glVertex3f(x + xx,-y + yy,-z + zz);
		glTexCoord2f(0,0);
		glVertex3f(x + xx,-y + yy,z + zz);
		glTexCoord2f(0,1);
		glVertex3f(-x + xx,-y + yy,z + zz);
	glEnd();
	//glPopMatrix();
	//glLoadIdentity();
	}

	public static void drawLine(float x,float y,float xx, float yy){
		glPushMatrix();
		glBegin(GL_LINES);
		glVertex2f(x,y);
		glVertex2f(xx,yy);
		glEnd();
		glPopMatrix();
	}
	
	public static void drawLine(float x,float y,float xx, float yy, Texture textures){
		textures.bind();
		glPushMatrix();
		glBegin(GL_LINES);
		glTexCoord2f(0,0);
		glVertex2f(x,y);
		glTexCoord2f(1,1);
		glVertex2f(xx,yy);
		glEnd();
		glPopMatrix();
	}
	
	
	public static void drawString(){
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/Cash Currency.ttf");
	 
			Font awtFont2 = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont2 = awtFont2.deriveFont(24f); // set font size
			font2 = new TrueTypeFont(awtFont2, false);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}