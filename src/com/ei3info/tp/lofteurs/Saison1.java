package com.ei3info.tp.lofteurs;

/**
 * La première saison de Secrets Neuneu.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class Saison1 extends Saison
{
    /**
     * Démarre une nouvelle saison 1.
     * @param args
     */
    public static void main(String[] args)
    {
        new Saison1().primeTime();
    }

    @Override
    public void primeTime()
    {
        zone = new ZoneGraphique("Secrets Neuneu - Saison 1", this);
        demarrerSaison();
    }

    @Override
    public void demarrerSaison()
    {
        loft = new Loft(zone);
        loft.remplissageAleatoire(quantiteNourriture);

        for (int i = 0; i < nombreLofteurs; i++)
        {
            Neuneu neuneu = loft.creerNouveauNeuneu();

            if (neuneu != null)
            {
                loft.add(neuneu);
            }
        }

        loft.start();
    }

    @Override
    public void redemarrerSaison()
    {
        // On arrete le loft
        loft.arreter();
        try
        {
            loft.join();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        // On remet à 0 les numéros des neuneus
        Erratique.resetNumeros();
        Vorace.resetNumeros();
        Cannibale.resetNumeros();
        Lapin.resetNumeros();
        
        // On lance une nouvelle saison
        demarrerSaison();
    }

}
