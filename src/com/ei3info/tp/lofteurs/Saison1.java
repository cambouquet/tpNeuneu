package com.ei3info.tp.lofteurs;

public class Saison1 extends Saison
{
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        new Saison1().primeTime();
    }

    public void primeTime()
    {
        zone = new ZoneGraphique("Secrets Neuneu - Saison 1", this);
        demarrerSaison();
    }

    public void demarrerSaison()
    {
        loft = new Loft(tailleLoft, zone);
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
        loft.arreter();
        try
        {
            loft.join();
        } catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Erratique.resetNumeros();
        Vorace.resetNumeros();
        Cannibale.resetNumeros();
        Lapin.resetNumeros();
        demarrerSaison();
    }

}
