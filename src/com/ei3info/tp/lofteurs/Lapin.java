package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

public class Lapin extends Neuneu{
	public Lapin(Loft loft, int x, int y) {
		super(loft, x, y);
		couleur = Color.PINK;
	}

	public void seDeplacer() {
		// Le principe du lapin est qu'il chercheà se reproduire avant de chercher à manger. 
		// S'il n'a pas suffisamment d'énergie pour se déplacer et se reproduire, il cherchera d'abord à manger. 
		
		// Recherche du neuneu le plus proche
		int[] nextN = new int[2];
		nextN = trouverNeuneuPlusProche();
		int energN = nextN[0] + nextN [1] + 2 +2;  // On suppute à ce stade que la reproduction nécessite 2 unités d'énergie, et on met 2 unités d'énergie en réserve pour ne pas se faire exclure.
		if (this.energie < energN) {
			// COMPORTEMENT VORACE
			// Recherche de la nourriture la plus proche
			int[] nextF = new int[2];
			nextF = trouverNourriturePlusProche();
			// Pathfinding basique
			int mvHandleX = nextF[0]/Math.abs(nextF[0]);
			int mvHandleY = nextF[1]/Math.abs(nextF[1]);
			// Déplacement
			this.posX = this.posX + mvHandleX;
			this.posY = this.posY + mvHandleY;
		}
		else {
			// COMPORTEMENT CANNIBALE (twist!)
			nextN = trouverNeuneuPlusProche();
			// Pathfinding basique
			int mvHandleX = nextN[0]/Math.abs(nextN[0]);
			int mvHandleY = nextN[1]/Math.abs(nextN[1]);
			// Déplacement
			this.posX = this.posX + mvHandleX;
			this.posY = this.posY + mvHandleY;
		}
		
	}
}
