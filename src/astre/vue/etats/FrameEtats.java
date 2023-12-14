package astre.vue.etats;

/** Page de gestion des Etats
  * @author : Matéo Sa
  * @version : 1.0 - 14/12/2023
  * @date : 14/12/2023
  */

import astre.Controleur;
import astre.vue.outils.MenuBarAstre;
import javax.swing.*;

public class FrameEtats extends JFrame
{
	private PanelEtats panel;
	private Controleur        ctrl;
	
	public FrameEtats ( Controleur ctrl )
	{
		this.ctrl  = ctrl;
		this.panel = new PanelEtats ( this.ctrl );
		
		this.setTitle              ( "Etats"        );
		this.setSize               ( 500,250 );
		this.setLocationRelativeTo ( null               );
		
		this.setJMenuBar ( new MenuBarAstre ( this.ctrl, this ) );
		
		this.add ( this.panel );

		this.setVisible   ( true );
	}

}