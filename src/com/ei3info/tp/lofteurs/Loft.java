/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Dimension;
import java.util.LinkedList;

/**
 * @author Camille
 */
public class Loft
{

    private int                         tailleLoft;
    private ZoneGraphique               zone;
    private LinkedList<ObjetDessinable> listeObjets;
    private LoftPanel                   loftPanel;

    public Loft(int tailleLoft, ZoneGraphique zone)
    {
        this.tailleLoft = tailleLoft;
        this.zone = zone;
        this.listeObjets = new LinkedList<ObjetDessinable>();
        this.loftPanel = new LoftPanel(listeObjets);
        loftPanel.setPreferredSize(new Dimension(tailleLoft
                * ObjetPositionnable.tailleX, tailleLoft
                * ObjetPositionnable.tailleY));
        loftPanel.setSize(tailleLoft * ObjetPositionnable.tailleX, tailleLoft
                * ObjetPositionnable.tailleY);
        this.zone.setLoftPanel(loftPanel);
    }

    public void remplissageAleatoire(float f)
    {
        int nbrCaseARemplir = (int) (f * tailleLoft);
        for (int i = 0; i < nbrCaseARemplir; i++)
        {
            this.add(new Pizza((int) (Math.random() * (tailleLoft - 1)),
                    (int) (Math.random() * (tailleLoft - 1))));
        }
    }

    public void add(ObjetDessinable objet)
    {
        listeObjets.add(objet);
        loftPanel.updateListeObjets(listeObjets);
    }

    public void go()
    {
        System.out.println("Bienvenue à tous !");
        System.out.println("Nous sommes heureux de vous présenter la saison 1 de Secrets Neuneus !\n");
        
        int heures = 0;
        while (nombreNeuneusRestants() > 0)
        {
            for (ObjetDessinable objet : listeObjets)
            {
                if (objet instanceof Neuneu)
                {
                    Neuneu neuneu = (Neuneu) objet;

                    neuneu.setEnergie(neuneu.getEnergie() - 1);
                    boolean mort = neuneu.mourir();

                    if (!mort)
                    {
                        neuneu.seDeplacer();
                        neuneu.manger();
                        neuneu.seReproduire();
                        neuneu.mourir();
                    }
                }
            }
            heures++;
        }
        
        System.out.println("Fin de la saison 1 !");
        System.out.println("durée : " + heures + " h\n");
        System.out
                .println("Bientôt la saison 2 : plus d'action, de suspens et d'émotion !!!");
    }

    private int nombreNeuneusRestants()
    {
        int nbrNeuneus = 0;
        for (ObjetDessinable objet : listeObjets)
        {
            if (objet instanceof Neuneu)
            {
                nbrNeuneus++;
            }
        }
        return nbrNeuneus;
    }

    public int getTailleLoft()
    {
        return this.tailleLoft;
    }

}
