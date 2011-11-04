package com.ei3info.tp.lofteurs;

public class Vorace extends Neuneu {

	public Vorace(Loft loft, int x, int y) {
		// TODO Auto-generated constructor stub
	}

	public void seDeplacer() {
		// Recherche de la nourriture la plus proche
		int[] nextF = new int[2];
		nextF = trouverNourriturePlusProche(this);
		// Pathfinding basique
		int mvHandleX = nextF[0]/Math.abs(nextF[0]);
		int mvHandleY = nextF[1]/Math.abs(nextF[1]);
		// Déplacement
		this.posX = this.posX + mvHandleX;
		this.posY = this.posY + mvHandleY;
	}
}
