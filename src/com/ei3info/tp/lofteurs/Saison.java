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
    public static float   proportionErratique = .45f;
    public static float   proportionVorace    = .25f;
    public static float   proportionCannibale = 0.05f;
    public static float   proportionLapin = 0.1f;
    
    protected Loft          loft;
    protected ZoneGraphique zone;
    
    public abstract void demarrerSaison();
    public abstract void redemarrerSaison();
}
