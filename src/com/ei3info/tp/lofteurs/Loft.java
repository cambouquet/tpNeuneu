/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

/**
 * Le loft.\n Contient les différents éléments à afficher et gère la boucle
 * d'événements.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class Loft extends Thread
{
    /**
     * La fenêtre graphique.
     */
    private ZoneGraphique               zone;

    /**
     * La liste des objets contenus dans le loft. La liste des objets est mise à
     * jour à la fin du tour à partir de la liste des objets détruits et de la
     * liste des objets créés.
     */
    private LinkedList<ObjetDessinable> listeObjets;

    /**
     * La liste des objets détruits pendant un tour de jeu. Les objets de cette
     * liste sont retiré de la liste des objets à la fin du tour puis cette
     * liste est vidée.
     */
    private LinkedList<ObjetDessinable> listeObjetsDetruits;

    /**
     * La liste des objets créés pendant le tour. Les objets de cette liste sont
     * ajouté à la liste des objets à la fin du tour puis cette liste est vidée.
     */
    private LinkedList<ObjetDessinable> listeObjetsCrees;

    /**
     * Le panneau affichant les objets dessinables du loft.
     */
    private LoftPanel                   loftPanel;

    /**
     * Indique si la saison est finie.
     */
    private boolean                     finSaison = false;

    /**
     * Le nombre d'heures qui se sont déroulés depuis le début de la saison. Une
     * heure correspond à un tour de jeu.
     */
    private int                         heures;

    /**
     * Créer un nouveau loft, rempli par la saison.
     * @param zone
     *            La fenêtre graphique du programme.
     */
    public Loft(ZoneGraphique zone)
    {
        this.zone = zone;

        // Initialisation des listes
        this.listeObjets = new LinkedList<ObjetDessinable>();
        this.listeObjetsDetruits = new LinkedList<ObjetDessinable>();
        this.listeObjetsCrees = new LinkedList<ObjetDessinable>();

        // Initialisation du panneau dessinant le loft
        this.loftPanel = new LoftPanel(listeObjets);
        loftPanel.setPreferredSize(new Dimension(Saison.tailleLoft * ObjetPositionnable.tailleX, Saison.tailleLoft
                * ObjetPositionnable.tailleY));
        loftPanel.setSize(Saison.tailleLoft * ObjetPositionnable.tailleX, Saison.tailleLoft
                * ObjetPositionnable.tailleY);
        this.zone.setLoftPanel(loftPanel);
    }

    /**
     * Rempli aléatoirement le loft de nourriture en fonction du nombre de cases
     * à remplir et des proportions de la saison.
     * @param nbrCaseARemplir
     *            Le nombre de cases à remplir de nourriture.
     */
    public void remplissageAleatoire(int nbrCaseARemplir)
    {
        for (int i = 0; i < nbrCaseARemplir; i++)
        {
            double x = Math.random();
            if (x < Saison.proportionPizza)
            {
                // On créé une pizza
                this.add(new Pizza((int) (Math.random() * (Saison.tailleLoft - 1)),
                        (int) (Math.random() * (Saison.tailleLoft - 1))));
            } else
            {
                x -= Saison.proportionPizza;
                if (x < Saison.proportionCoca)
                {
                    // Sinon un Coca
                    this.add(new Coca((int) (Math.random() * (Saison.tailleLoft - 1)),
                            (int) (Math.random() * (Saison.tailleLoft - 1))));
                } else
                {
                    // Sinon une bière
                    this.add(new Biere((int) (Math.random() * (Saison.tailleLoft - 1)),
                            (int) (Math.random() * (Saison.tailleLoft - 1))));
                }
            }
        }
    }

    /**
     * Créé aléatoirement un nouveau neuneu en foncion des proportions de la
     * saison.
     * @return Le neuneu créé.
     */
    public Neuneu creerNouveauNeuneu()
    {
        double x = Math.random();

        Neuneu neuneu = null;
        if (x < Saison.proportionVorace)
        {
            // On créé un nouveau vorace
            neuneu = new Vorace(this, (int) (Math.random() * (Saison.tailleLoft - 1)),
                    (int) (Math.random() * (Saison.tailleLoft - 1)));
        } else
        {
            x -= Saison.proportionVorace;
            if (x < Saison.proportionErratique)
            {
                // Sinon un nouvel erratique
                neuneu = new Erratique(this, (int) (Math.random() * (Saison.tailleLoft - 1)),
                        (int) (Math.random() * (Saison.tailleLoft - 1)));
            } else
            {
                x -= Saison.proportionErratique;
                if (x < Saison.proportionCannibale)
                {
                    // Sinon un nouveau cannibale
                    neuneu = new Cannibale(this, (int) (Math.random() * (Saison.tailleLoft - 1)),
                            (int) (Math.random() * (Saison.tailleLoft - 1)));
                } else
                {
                    // Et sinon un nouveau lapin
                    neuneu = new Lapin(this, (int) (Math.random() * (Saison.tailleLoft - 1)),
                            (int) (Math.random() * (Saison.tailleLoft - 1)));
                }
            }
        }

        return neuneu;
    }

    /**
     * Ajoute un nouvel objet au loft directement. A utiliser en dehors du
     * fonctionnement d'un tour de jeu.
     * @param objet
     *            L'objet à ajouter.
     */
    public void add(ObjetDessinable objet)
    {
        listeObjets.add(objet);
        loftPanel.updateListeObjets(listeObjets);
    }

    /**
     * Calcule le nombre de neuneus présents dans le loft.
     * @return Le nombre de neuneus présents dans le loft.
     */
    private int nombreNeuneusPrésents()
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

    /**
     * Détruire un objet. L'ajoute à la liste des objets détruits.
     * @param objetADetruire
     *            L'objet à détruire
     */
    public void detruireObjet(ObjetDessinable objetADetruire)
    {
        listeObjetsDetruits.add(objetADetruire);
        loftPanel.removeObjet(objetADetruire);
        loftPanel.repaint();
    }

    /**
     * Récuperer la liste des objets actuels dans le loft, sans considérer la
     * liste des objets créés ni detruits.
     * @return La liste des objets présents dans le loft.
     */
    public LinkedList<ObjetDessinable> getListeObjets()
    {
        return this.listeObjets;
    }

    /**
     * Arrête la saison actuelle.
     */
    public void arreter()
    {
        finSaison = true;
        loftPanel.nettoyer();
    }

    @Override
    public void run()
    {
        afficherEvenement("Bienvenue à tous !", Color.RED);
        afficherEvenement("Nous sommes heureux de vous présenter la saison 1 de Secrets Neuneus !\n", Color.RED, true);

        heures = 0;
        while (nombreNeuneusPrésents() > 0 && !finSaison)
        {
            // Début d'un tour de jeu
            zone.setDuree(getDuree());
            long debut = System.currentTimeMillis();

            for (ObjetDessinable objet : listeObjets)
            {
                // On parcourt la liste mais ne considère que les neuneus et
                // objets non détruits
                if (!listeObjetsDetruits.contains(objet) && objet instanceof Neuneu)
                {
                    Neuneu neuneu = (Neuneu) objet;

                    // Le neuneu perd de l'énergie à chaque tour
                    neuneu.setEnergie(neuneu.getEnergie() - 1);

                    // Donc il peut éventuellement mourrir
                    boolean mort = neuneu.mourir();
                    loftPanel.repaint();

                    // S'il est encore en vie
                    if (!mort)
                    {
                        // Il se déplace
                        neuneu.seDeplacer();
                        loftPanel.repaint();

                        // Il mange si possible
                        neuneu.manger();
                        loftPanel.repaint();

                        // Il se reproduit si possible
                        neuneu.seReproduire();
                        loftPanel.repaint();

                        // Mais il peut avoir épuisé toute son énergie et mourir
                        mort &= neuneu.mourir();
                        loftPanel.repaint();
                    }

                    if (mort)
                    {
                        afficherEvenementDuree(neuneu.getNom() + " est mort...", Color.RED, false);
                    }
                }
            }

            // On met à jour la liste des objets en enlevant les objets détruits
            // pendant le tour
            for (ObjetDessinable objetDetruit : listeObjetsDetruits)
            {
                listeObjets.remove(objetDetruit);
            }
            listeObjetsDetruits.clear();

            // On met à jour la liste des objets en ajoutant les objets créés
            // pendant le tour
            for (ObjetDessinable objetCree : listeObjetsCrees)
            {
                listeObjets.add(objetCree);
            }
            listeObjetsCrees.clear();

            // On fait une pause en fonction de la vitesse pour que l'animation
            // soit visible
            long duree = System.currentTimeMillis() - debut;
            duree = (Saison.WAITING_TIME - duree < 0) ? 0 : Saison.WAITING_TIME - duree;
            try
            {
                Loft.sleep(duree);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            heures++;
        }

        afficherEvenement("Fin de la saison 1 !", Color.RED);
        afficherEvenement("durée : " + getDuree(), Color.RED);
        afficherEvenement("Bientôt la saison 2 : plus d'action, de suspens et d'émotion !!!\n", Color.RED);
    }

    /**
     * Crée la description de la durée par rapport au temps passé depuis le
     * début de la saison.
     * @return La description de la durée écoulée.
     */
    public String getDuree()
    {
        String jour = ((heures / 24) > 1) ? " jours" : " jour";

        return new String(heures / 24 + jour + " et " + heures % 24 + "h");
    }

    /**
     * Affiche un événement en indiquant la durée depuis le début de la saison.
     * @param evenement
     *            La description de l'événement.
     */
    public void afficherEvenementDuree(String evenement)
    {
        zone.afficherEvenement(getDuree(), evenement, Color.BLACK, false);
    }

    /**
     * Affiche un événement à l'écran avec une couleur spécifique et
     * éventuellement sauter une ligne en indiquant la durée depuis le début de
     * la saison.
     * @param evenement
     *            La description de l'événement.
     * @param couleur
     *            La couleur du texte à afficher.
     * @param sauterLigne
     *            true - Une ligne sera sautée après l'affichage de
     *            l'événement\n false - Aucune ligne ne sera sautée
     */
    public void afficherEvenementDuree(String evenement, Color couleur, boolean sauterLigne)
    {
        zone.afficherEvenement(getDuree(), evenement, couleur, sauterLigne);
    }

    /**
     * Affiche un événement à l'écran.
     * @param evenement
     *            La description de l'événement.
     */
    public void afficherEvenement(String evenement)
    {
        zone.afficherEvenement(evenement);
    }

    /**
     * Affiche un événement à l'écran en spécifiant une couleur.
     * @param evenement
     *            La description de l'événement.
     * @param couleur
     *            La couleur du texte à afficher.
     */
    public void afficherEvenement(String evenement, Color couleur)
    {
        zone.afficherEvenement(evenement, couleur);
    }

    /**
     * Affiche un événement à l'écran en spécifiant une couleur et si on doit
     * sauter une ligne après.
     * @param evenement
     *            La description de l'événement.
     * @param couleur
     *            La couleur du texte à afficher.
     * @param sauterLigne
     *            true - Une ligne sera sautée après l'affichage de
     *            l'événement\n false - Aucune ligne ne sera sautée
     */
    public void afficherEvenement(String evenement, Color couleur, boolean sauterLigne)
    {
        zone.afficherEvenement(evenement, couleur, sauterLigne);
    }

    /**
     * Ajoute un nouveau neuneu pendant que la saison a démarré.
     * @param neuneucree
     *          Le neuneu à ajouter
     */
    public void addBebeNeuneu(Neuneu neuneucree)
    {
        this.listeObjetsCrees.add(neuneucree);
        this.loftPanel.addBebeNeuneu(neuneucree);
    }
}
