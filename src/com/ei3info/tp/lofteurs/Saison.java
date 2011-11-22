/**
 * 
 */
package com.ei3info.tp.lofteurs;

/**
 * Représente une saison en général avec toutes les variables de paramétrage
 * d'une saison.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public abstract class Saison
{
    /**
     * Le nombre de lofteurs présents initialement dans le loft.
     */
    public static int       nombreLofteurs      = 10;

    /**
     * Le nombre de case d'un coté du loft qui est carré.
     */
    public static final int tailleLoft          = 30;

    /**
     * La proportion d'erratiques dans le loft.
     */
    public static float     proportionErratique = .25f;

    /**
     * La proportion de voraces dans le loft.
     */
    public static float     proportionVorace    = .25f;

    /**
     * La proportion de cannibales dans le loft.
     */
    public static float     proportionCannibale = 0.25f;

    /**
     * La proportion de lapins dans le loft.
     */
    public static float     proportionLapin     = 0.25f;

    /**
     * La proportion de pizzas dans le loft.
     */
    public static float     proportionPizza     = 0.50f;

    /**
     * La proportion de cocas dans le loft.
     */
    public static float     proportionCoca      = 0.25f;

    /**
     * La proportion de bières dans le loft.
     */
    public static float     proportionBiere     = 0.25f;

    /**
     * La quantité de nourriture initiale dans le loft.
     */
    public static int       quantiteNourriture  = 25;

    /**
     * Le temps d'attente en millisecondes.
     */
    public static int       WAITING_TIME        = 200;

    /**
     * Le loft, qui contient les objets et gère les événements.
     */
    protected Loft          loft;

    /**
     * La zone graphique du programme.
     */
    protected ZoneGraphique zone;
    
    /**
     * Initialise une nouvelle saison
     */
    public abstract void primeTime();

    /**
     * Démarrer une nouvelle saison.
     */
    public abstract void demarrerSaison();

    /**
     * Redémarrer une saison. Remet à zero avant de démarrer une nouvelle
     * saison.
     */
    public abstract void redemarrerSaison();
}
