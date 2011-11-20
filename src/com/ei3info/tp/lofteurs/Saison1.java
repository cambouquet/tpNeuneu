package com.ei3info.tp.lofteurs;

public class Saison1 extends Saison
{

    public static int     nombreLofteurs      = 10;
    public static int     tailleLoft          = 30;
    public static float   proportionErratique = .75f;
    public static float   proportionVorace    = .25f;
    public static float   proportionCannibale = 0f;
    private Loft          loft;
    private ZoneGraphique zone;

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
        loft.remplissageAleatoire(0.1f);

        for (int i = 0; i < nombreLofteurs; i++)
        {
            double x = Math.random();
            if (x < proportionVorace)
            {
                loft.add(new Vorace(loft,
                        (int) (Math.random() * (tailleLoft - 1)), (int) (Math
                                .random() * (tailleLoft - 1))));
            } else
            {
                x -= proportionVorace;
                if (x < proportionErratique)
                {
                    loft.add(new Erratique(loft,
                            (int) (Math.random() * (tailleLoft - 1)),
                            (int) (Math.random() * (tailleLoft - 1))));
                } else
                {
                    x -= proportionErratique;
                    if (x < proportionCannibale)
                    {
                        loft.add(new Cannibale(loft,
                                (int) (Math.random() * (tailleLoft - 1)),
                                (int) (Math.random() * (tailleLoft - 1))));
                    }
                }
            }
        }

        loft.start();

    }

    @Override
    public void redemarrerSaison()
    {
        loft.arreter();
        while (loft.isAlive())
        {

        }
        demarrerSaison();
    }

}
