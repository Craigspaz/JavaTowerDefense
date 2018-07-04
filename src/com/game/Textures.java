package com.game;

import static com.game.LoadTexture.loadTexture;

import org.newdawn.slick.opengl.Texture;

public class Textures {
	public static Texture air,dirt,grass,grassTop,stone,Exit_First,Exit_Second,Play_First,Play_Second, Resume_First,Resume_Second,Laser,Highlight,Play,Play1,Pause,Pause1;
	public  static Texture Enemy1, Enemy2;
	public static Texture Tower1;
	
	public Textures(){
		air = loadTexture("air");
		dirt = loadTexture("dirt");
		grass = loadTexture("grass");
		grassTop = loadTexture("grassTop");
		stone = loadTexture("stone");
		Exit_First = loadTexture("Buttons/Exit_First");
		Play_First = loadTexture("Buttons/Play_First");
		Exit_Second = loadTexture("Buttons/Exit_Second");
		Play_Second = loadTexture("Buttons/Play_Second");
		Resume_First = loadTexture("Buttons/Resume_First");
		Resume_Second = loadTexture("Buttons/Resume_Second");
		Laser = loadTexture("Laser");
		Highlight = loadTexture("Highlighted Area");
		Play = loadTexture("Buttons/Play");
		Play1 = loadTexture("Buttons/Play1");
		Pause = loadTexture("Buttons/Pause");
		Pause1 = loadTexture("Buttons/Pause1");
		
		Enemy1 = loadTexture("Enemy/Enemy1");
		Enemy2 = loadTexture("Enemy/Enemy2");
		
		Tower1 = loadTexture("Tower/Tower1");
	}
}
