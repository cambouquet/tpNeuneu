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
 * @author Camille
 */
public class ParametresPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ZoneGraphique       zone;
    private int                 nbrParametres        = 0;

    private JFormattedTextField tfVitesse            = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfEnergMaxNeuneus    = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfEnergMaxNourriture = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfEnergReprod        = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPNourriture        = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfNbrNeuneus         = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPPizza             = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPCoca              = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfPBiere             = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfErratiques         = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfVoraces            = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfCannibales         = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField tfLapins             = new JFormattedTextField(NumberFormat.getIntegerInstance());

    public ParametresPanel(ZoneGraphique zone)
    {
        this.zone = zone;
        creerParametresPanel();
    }

    private void creerParametresPanel()
    {
        this.setLayout(new GridBagLayout());

        // Vitesse
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

    private void ajouterParametre(JFormattedTextField textField, String titre)
    {
        JFormattedTextField[] textFields = {textField};
        String[] titres = {titre};
        ajouterParametre(textFields, titres);
    }

    private class RelancerAction implements ActionListener
    {

        private boolean verifierPourcentageNeuneus(JFormattedTextField[] textFields, String messageErreur)
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
                JOptionPane.showMessageDialog(zone, messageErreur, "Pourcentages incorrects",
                        JOptionPane.ERROR_MESSAGE);
            }

            return pourcentageOk;
        }

        private String verifierValeurPourcentage(JFormattedTextField textFields)
        {
            Integer valeur = new Integer(textFields.getText());
            if (valeur > 100)
            {
                textFields.setText("100");
            }

            verifierValeurPositive(textFields);

            return textFields.getText();
        }

        private String verifierValeurPositive(JFormattedTextField textFields)
        {
            Integer valeur = new Integer(textFields.getText());

            if (valeur < 0)
            {
                textFields.setText("0");
            }

            return textFields.getText();
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
            boolean pNeuneus = verifierPourcentageNeuneus(tfsPNeuneus,
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
            boolean pNourriture = verifierPourcentageNeuneus(tfsPNourriture,
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
