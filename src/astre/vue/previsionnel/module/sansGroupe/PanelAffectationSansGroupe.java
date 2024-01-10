package astre.vue.previsionnel.module.sansGroupe;

import java.util.HashMap;
import java.util.Map;

import astre.Controleur;

import astre.modele.elements.Contrat;
import astre.modele.elements.Heure;
import astre.modele.elements.Intervenant;

import astre.vue.previsionnel.module.*;
import astre.vue.rendus.OperationRenduTableauIntervient;

//FIXME: à complèter pcq c une copie de PanelAffectationAvecGroupe
public class PanelAffectationSansGroupe extends AbstractPanelAffectation
{
	private static final int COL_NB_HEURE    = 4;
	private static final int COL_EQTD        = 5;
	private static final int COL_COMMENTAIRE = 6;

	/*----------------*/
	/*--Constructeur--*/
	/*----------------*/

	public PanelAffectationSansGroupe ( Controleur ctrl, FrameModule frmModule, String[] ensIntituleTypeHeure, String[] ensEntete, Object[] ensTypeDefaut, boolean[] ensModifiable )
	{
		super ( ctrl, frmModule, ensIntituleTypeHeure, ensEntete, ensTypeDefaut, ensModifiable );

		// Permet de gérer les différents rendus des cellulese (Notamment pur nb Semaine et nb Groupe|nb Heure)
		for ( int i = 0; i < this.tableau.getColumnCount ( ); i++ )
			super.tableau.getColumnModel ( ).getColumn ( i ).setCellRenderer ( new OperationRenduTableauIntervient ( ) );

	}

	/* ------------------------- */
	/*          Méthodes         */
	/* ------------------------- */

	@Override
	//TODO: voir pour factoriser la méthode avec celle de PanelAffectationAvecGroupe
	public Map<String, Double> getSommesEQTD ( )
	{
		Map<String, Double> map = new HashMap<> ( );

		// Initialisation des entetes par défaut
		for ( String intitule : super.ensIntituleTypeHeure )
			map.put ( intitule, 0.0 );

		// Calcul des sommes par type d'heures
		for ( int cpt = 0; cpt < this.tableau.getDonnees ( ).length; cpt++ )
		{
			if ( ( char ) this.tableau.getDonnees ( ) [cpt][0] != Controleur.SUPPRIMER )
			{
				String cle = this.tableau.getValueAt ( cpt, 1 ).toString ( );
				Double val = Double.parseDouble ( this.tableau.getDonnees ( ) [cpt][6].toString ( ) );
			
				//dans le cas ou la clé est vide
				if ( ! cle.equals ( "" ) )
					map.put ( cle, map.get ( cle ) + val );
			}
		}

		return map;
	}

	/**
	 * Méthode qui calcul l'équivalent TD pour le tableau
	 */
	@Override
	public void majTotEqtd ( )
	{		
		for ( int ligne = 0; ligne < this.tableau.getDonnees ( ).length; ligne++ )
		{
			if ( ( int ) this.tableau.getDonnees ( ) [ligne][COL_ID_INTERVENANT] != 0 ) 
			{
				Heure       h = this.ctrl.getHeure                          ( this.tableau.getDonnees ( ) [ligne][COL_HEURE]      .toString ( )   );
				Intervenant i = this.ctrl.getIntervenant ( Integer.parseInt ( this.tableau.getDonnees ( ) [ligne][COL_ID_INTERVENANT].toString ( ) ) );
				Contrat     c = i.getContrat ( );
				
				double coefHeure       = h.getCoefTd ( );
				double coefIntervenant = h.getNom ( ).equals ( "TP" ) ? c.getRatioTP ( ) : 1;
				double nbSemaine       = Double.parseDouble ( this.tableau.getDonnees ( ) [ligne][COL_NB_SEMAINE].toString ( ) );
				double nbHeure         = h.getNom ( ).equals ( "HP" ) ? 1 : this.frmModule.getNbHeureSemaine ( h.getNom ( ) );
				double nbGroupe        = Double.parseDouble ( this.tableau.getDonnees ( ) [ligne][COL_NB_GROUPE].toString ( ) );
				
				this.tableau.setValueAt ( coefHeure * coefIntervenant * nbSemaine * nbHeure * nbGroupe, ligne, COL_EQTD - DECALAGE_TABLEAU );
			}
		}
	}

}
