package com.ei3info.tp.lofteurs;

import java.awt.Color;

public class Coca extends Nourriture {

	protected Coca(int x, int y) {
		super(x, y);
		System.out.println("Coca placé en " + posX + ":" + posY);
		couleur = Color.BLACK;
	}

    @Override
    public String getDescriptionConsommation()
    {
        return new String("Hmm du Coca-Cola™ !");
    }
}
