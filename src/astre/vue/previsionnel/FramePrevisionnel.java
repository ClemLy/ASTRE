package astre.vue.previsionnel;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import astre.Controleur;

public class FramePrevisionnel extends JFrame
{
	/*-------------*/
	/*--Attributs--*/
	/*-------------*/

	private Controleur ctrl;
	
	private JPanel panelPrevisionnel;

	/*----------------*/
	/*--Constructeur--*/
	/*----------------*/

	/** Constructeur de FramePrevisionnel
	 * @param ctrl le controleur
	 * 
	 */

	public FramePrevisionnel ( Controleur ctrl )
	{
		this.ctrl = ctrl;

		this.setSize     ( 1000, 700 );
		this.setTitle    ( "Prévisionnel"   );

		/* ------------------------- */
		/* Création des composants   */
		/* ------------------------- */

		this.setLayout( new BorderLayout ( ) );

		this.add ( new PanelEnsSemestre  ( this.ctrl ), BorderLayout.CENTER );
		this.add ( new PanelBouton ( this.ctrl ), BorderLayout.SOUTH );


		this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
		this.setVisible               ( true        );
	}
}
