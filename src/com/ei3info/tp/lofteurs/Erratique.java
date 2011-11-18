package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Graphics;

public class Erratique extends Neuneu {

	public Erratique(Loft loft, int x, int y) {
		super(loft, x, y);
		couleur = Color.BLUE;
	}

	

	private void seDeplacer(){
		// V�rification bordure en X
		if (this.posX == 0 || this.posX == loft.getTailleLoft() - 1){
			// D�cision sur la direction 
			double mvHandle = Math.random();
			if (mvHandle < 0.5){
				mvHandle = -1;
			}
			else{
				mvHandle = 1;
			}
			// D�placement effectif
			this.posY = this.posY + (int) mvHandle;
		}
		
		// V�rification bordure en Y
				if (this.posY == 0 || this.posY == loft.getTailleLoft() - 1){
					// D�cision sur la direction 
					double mvHandle = Math.random();
					if (mvHandle < 0.5){
						mvHandle = -1;
					}
					else{
						mvHandle = 1;
					}
					// D�placement effectif
					this.posX = this.posX + (int) mvHandle;
				}
		
		// D�placement dans le cas g�n�ral
			
		double mvHandleX = Math.random();
		if (mvHandleX < 0.33){
			mvHandleX = -1;
		}
		else{
			if (mvHandleX < 0.66) {
			mvHandleX = 0;
			}
			else{
				mvHandleX = 1;
			}
		}
		double mvHandleY = Math.random();
		if (mvHandleY < 0.33){
			mvHandleY = -1;
		}
		else{
			if (mvHandleY < 0.66) {
			mvHandleY = 0;
			}
			else{
				mvHandleY = 1;
			}
		}
		this.posX = this.posX + (int) mvHandleX;
		this.posY = this.posY + (int) mvHandleY;
	}
}
