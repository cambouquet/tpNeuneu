/**
 * 
 */
package com.ei3info.tp.lofteurs;

/**
 * @author Camille
 *
 */
public abstract class Saison
{
    public static int     nombreLofteurs      = 10;
    public static int     tailleLoft          = 30;
    public static float   proportionErratique = .50f;
    public static float   proportionVorace    = .25f;
    public static float   proportionCannibale = 0.00f;
    public static float   proportionLapin = 0.25f;
    public static float   proportionPizza = 0.50f;
    public static float   proportionCoca = 0.25f;
    public static float   proportionBiere = 0.25f;
    public static float   proportionNourriture = 0.25f;
    
    protected Loft          loft;
    protected ZoneGraphique zone;
    
    public abstract void demarrerSaison();
    public abstract void redemarrerSaison();
}
