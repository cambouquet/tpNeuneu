package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

public class Cannibale extends Neuneu {

	public Cannibale(Loft loft, int x, int y) {
		super(loft, x, y);
		couleur = Color.RED;
	}

	@Override
	public void dessinerObjet(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	public void seDeplacer() {
		// Recherche de la nourriture la plus proche
		int[] nextF = new int[2];
		nextF = trouverNourriturePlusProche();
		// Recherche du neuneu le plus proche
		int[] nextN = new int[2];
		nextN = trouverNeuneuPlusProche();
		// Détermination de la source la plus proche
		double distF = Math.sqrt(nextF[0]^2 + nextF[1]^2);
		double distN = Math.sqrt(nextN[0]^2 + nextN[1]^2);
		if (distF < distN) {
			nextN[0] = 0; // Il fallait bien mettre quelque chose.
		}
		else {
			nextF = nextN;
		}
		// Pathfinding basique
		int mvHandleX = nextF[0]/Math.abs(nextF[0]);
		int mvHandleY = nextF[1]/Math.abs(nextF[1]);
		// Déplacement
		this.posX = this.posX + mvHandleX;
		this.posY = this.posY + mvHandleY;
	}
}
