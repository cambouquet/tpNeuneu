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
        loft.remplissageAleatoire(0.4f);

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
