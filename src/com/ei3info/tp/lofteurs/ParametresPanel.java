/**
 * 
 */
package com.ei3info.tp.lofteurs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Le panneau contenant les différents paramètes pour configurer une saison et
 * le bouton pour relancer une nouvelle saison.
 * @author Camille Bouquet
 * @author Antoine Sellam
 */
public class ParametresPanel extends JPanel
{
    /**
     * Generated default serial UID.
     */
    private static final long   serialVersionUID     = 1L;

    /**
     * La zone graphique qui contient ce panneau.
     */
    private ZoneGraphique       zone;

    /**
     * Le nombre de paramètres actuellement ajoutés au panneau.
     */
    private int                 nbrParametres        = 0;

    /**
     * Zone de saisie pour la vitesse qui correspond au temps d'attente en ms
     * entre chaque tour.
     */
    private JFormattedTextField tfVitesse            = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour l'énergie maximale des neuneus.
     */
    private JFormattedTextField tfEnergMaxNeuneus    = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la quantité maximale d'énergie que peur procurer la
     * nourriture.
     */
    private JFormattedTextField tfEnergMaxNourriture = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la quantité d'énergie que prend le fait de se
     * reproduire.
     */
    private JFormattedTextField tfEnergReprod        = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la quantité initiale de nourriture à placer dans le
     * loft.
     */
    private JFormattedTextField tfPNourriture        = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour le nombre initial de neuneus présents dans le loft.
     */
    private JFormattedTextField tfNbrNeuneus         = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la proportion de pizza parmi la nourriture
     * initialement placée dans le loft.
     */
    private JFormattedTextField tfPPizza             = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la proportion de coca parmi la nourriture
     * initialement placée dans le loft.
     */
    private JFormattedTextField tfPCoca              = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la proportion de bière parmi la nourriture
     * initialement placée dans le loft.
     */
    private JFormattedTextField tfPBiere             = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la proportion d'erratique parmi les neuneus placés
     * dans le loft.
     */
    private JFormattedTextField tfErratiques         = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la proportion de voraces parmi les neuneus placés
     * dans le loft.
     */
    private JFormattedTextField tfVoraces            = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la proportion de cannibales parmi les neuneus placés
     * dans le loft.
     */
    private JFormattedTextField tfCannibales         = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Zone de saisie pour la proportion de lapins parmi les neuneus placés dans
     * le loft.
     */
    private JFormattedTextField tfLapins             = new JFormattedTextField(NumberFormat.getIntegerInstance());

    /**
     * Constructeur avec la zone graphique contenant le panneau.
     * @param zone
     *            La zone graphique contenant le panneau.
     */
    public ParametresPanel(ZoneGraphique zone)
    {
        this.zone = zone;
        creerParametresPanel();
    }

    /**
     * Création effective du panneau.
     */
    private void creerParametresPanel()
    {
        this.setLayout(new GridBagLayout());

        // Vitesse
        NumberFormat nbf = NumberFormat.getIntegerInstance();
        nbf.setGroupingUsed(false);
        tfVitesse = new JFormattedTextField(nbf);
        ajouterParametre(tfVitesse, "Vitesse");
        tfVitesse.setText(String.valueOf((int) (Saison.WAITING_TIME)));

        // quantités d'éléments
        JFormattedTextField[] textFieldsQuantite = {tfNbrNeuneus, tfPNourriture};
        String[] titresQuantite = {"Nombre de neuneus", "Quantité de nourriture"};
        ajouterParametre(textFieldsQuantite, titresQuantite);

        tfNbrNeuneus.setText(String.valueOf((int) (Saison.nombreLofteurs)));
        tfPNourriture.setText(String.valueOf(Saison.quantiteNourriture));

        // energies
        JFormattedTextField[] textFieldsEnergie = {tfEnergMaxNeuneus, tfEnergMaxNourriture, tfEnergReprod};
        String[] titresEnergie = {"Energie max. neuneus", "Energie max. nourriture", "Energie reproduction"};
        ajouterParametre(textFieldsEnergie, titresEnergie);

        tfEnergMaxNeuneus.setText(String.valueOf((int) (Neuneu.ENERGIE_MAX)));
        tfEnergMaxNourriture.setText(String.valueOf((int) (Nourriture.ENERGIE_MAX)));
        tfEnergReprod.setText(String.valueOf((int) (Neuneu.ENERGIE_REPRODUCTION)));

        // proportions de nourriture
        JFormattedTextField[] textFieldsNourriture = {tfPPizza, tfPCoca, tfPBiere};
        String[] titresNourriture = {"% Pizza", "% Coca", "% Biere"};
        ajouterParametre(textFieldsNourriture, titresNourriture);

        tfPPizza.setText(String.valueOf((int) (Saison.proportionPizza * 100)));
        tfPCoca.setText(String.valueOf((int) (Saison.proportionCoca * 100)));
        tfPBiere.setText(String.valueOf((int) (Saison.proportionBiere * 100)));

        // proportions de neuneus
        JFormattedTextField[] textFieldsNeuneus = {tfErratiques, tfVoraces, tfCannibales, tfLapins};
        String[] titresNeuneus = {"% Erratiques", "% Voraces", "% Cannibales", "% Lapins"};
        ajouterParametre(textFieldsNeuneus, titresNeuneus);

        tfErratiques.setText(String.valueOf((int) (Saison.proportionErratique * 100)));
        tfVoraces.setText(String.valueOf((int) (Saison.proportionVorace * 100)));
        tfCannibales.setText(String.valueOf((int) (Saison.proportionCannibale * 100)));
        tfLapins.setText(String.valueOf((int) (Saison.proportionLapin * 100)));

        // Bouton relancer la saison
        JButton bRelancer = new JButton("Relancer");
        bRelancer.addActionListener(new RelancerAction());

        // Ajout des différents éléments au panneau de paramètres

        this.add(bRelancer, new GridBagConstraints(0, nbrParametres, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, new Insets(5, 10, 5, 20), 0, 0));
    }

    /**
     * Rajoute un ensemble de paramètres dans une même groupe espacés par
     * rapport aux autres dans le panneau. Un paramètre est composé d'un titre
     * et d'une zone de saisie.
     * @param textFields
     *            Les différentes zones de saisies.
     * @param titres
     *            Les titres correspondants aux zones de saisies.
     */
    private void ajouterParametre(JFormattedTextField[] textFields, String[] titres)
    {
        for (int i = 0; i < textFields.length; i++)
        {
            textFields[i].setColumns(5);
            int insetBas = (i == textFields.length - 1) ? 20 : 5;
            String titre = (titres.length > i) ? titres[i] : "Paramètre";

            this.add(new JLabel(titre), new GridBagConstraints(0, nbrParametres, 1, 1, 0.0, 0.0,
                    GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, insetBas, 10), 0, 0));
            this.add(textFields[i], new GridBagConstraints(1, nbrParametres, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                    GridBagConstraints.NONE, new Insets(5, 10, insetBas, 10), 0, 0));
            nbrParametres++;
        }
    }

    /**
     * Ajoute un simple paramètre au panneau. Un paramètre est composé d'un
     * titre et d'une zone de saisie.
     * @param textField
     *            La zone de saisie.
     * @param titre
     *            Le titre correspondant.
     */
    private void ajouterParametre(JFormattedTextField textField, String titre)
    {
        JFormattedTextField[] textFields = {textField};
        String[] titres = {titre};
        ajouterParametre(textFields, titres);
    }

    /**
     * Action permettant de relancer une saison tout en récupérant les valeurs
     * renseignées dans les différentes zones de saisies correspondants aux
     * paramètres.
     * @author Camille Bouquet
     * @author Antoine Sellam
     */
    private class RelancerAction implements ActionListener
    {
        /**
         * Vérifie que les valeurs d'un ensemble de zones de saisies
         * correspondent bien à des pourcentages dont la somme doit faire 100.
         * @param textFields
         *            Les zones de saisie à vérifier.
         * @param messageErreur
         *            Le message d'erreur à afficher en cas de non conformité.
         * @return true - La somme est bien égale à 100\nfalse - La somme est
         *         différente de 100.
         */
        private boolean verifierPourcentage(JFormattedTextField[] textFields, String messageErreur)
        {
            boolean pourcentageOk = true;
            int total = 0;

            for (int i = 0; i < textFields.length; i++)
            {
                total += new Integer(verifierValeurPourcentage(textFields[i]));
            }

            if (total != 100)
            {
                pourcentageOk = false;
                JOptionPane
                        .showMessageDialog(zone, messageErreur, "Pourcentages incorrects", JOptionPane.ERROR_MESSAGE);
            }

            return pourcentageOk;
        }

        /**
         * Vérifie que la valeur de la zone de saisie est bien conforme à un
         * pourcentage.
         * @param textField
         *            La zone de saisie à vérifier.
         * @return Le contenu après vérification et modification éventuelle du
         *         texte de la zone de saisie.
         */
        private String verifierValeurPourcentage(JFormattedTextField textField)
        {
            Integer valeur = new Integer(textField.getText());
            if (valeur > 100)
            {
                textField.setText("100");
            }

            verifierValeurPositive(textField);

            return textField.getText();
        }

        /**
         * Vérifie que le contenu d'une zone de saisie est bien positif.
         * @param textField
         *            La zone de saisie à vérifier.
         * @return Le contenu après vérification et modification éventuelle du
         *         texte de la zone de saisie.
         */
        private String verifierValeurPositive(JFormattedTextField textField)
        {
            Integer valeur = new Integer(textField.getText());

            if (valeur < 0)
            {
                textField.setText("0");
            }

            return textField.getText();
        }

        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            new JPanel();

            // Vérification de la vitesse
            Integer vitesse = new Integer(tfVitesse.getText());
            if (vitesse <= 0)
            {
                tfVitesse.setText("1");
                vitesse = 1;
            }

            Saison.WAITING_TIME = vitesse;

            // Vérification du nombre de neuneus
            Saison.nombreLofteurs = new Integer(verifierValeurPourcentage(tfNbrNeuneus));

            // Vérification des pourcentages des neuneus
            JFormattedTextField[] tfsPNeuneus = {tfErratiques, tfVoraces, tfCannibales, tfLapins};
            boolean pNeuneus = verifierPourcentage(tfsPNeuneus,
                    "Le total des pourcentages des proportions de neuneus doit être 100");

            if (pNeuneus)
            {
                Saison.proportionErratique = (new Float(tfErratiques.getText())) / 100;
                Saison.proportionVorace = (new Float(tfVoraces.getText())) / 100;
                Saison.proportionCannibale = (new Float(tfCannibales.getText())) / 100;
                Saison.proportionLapin = (new Float(tfLapins.getText())) / 100;
            }

            // Vérficaiton des pourcentages de nourritures
            Saison.quantiteNourriture = new Integer(verifierValeurPositive(tfPNourriture));

            JFormattedTextField[] tfsPNourriture = {tfPPizza, tfPCoca, tfPBiere};
            boolean pNourriture = verifierPourcentage(tfsPNourriture,
                    "Le total des pourcentages des proportions de nourriture doit être 100");

            if (pNourriture)
            {
                Saison.proportionPizza = (new Float(tfPPizza.getText())) / 100;
                Saison.proportionCoca = (new Float(tfPCoca.getText())) / 100;
                Saison.proportionBiere = (new Float(tfPBiere.getText())) / 100;
            }

            // Vérification des énergies
            Neuneu.ENERGIE_MAX = new Integer(verifierValeurPourcentage(tfEnergMaxNeuneus));
            Nourriture.ENERGIE_MAX = new Integer(verifierValeurPourcentage(tfEnergMaxNourriture));
            Neuneu.ENERGIE_REPRODUCTION = new Integer(verifierValeurPourcentage(tfEnergReprod));

            zone.relancerSaison();
        }

    }
}
