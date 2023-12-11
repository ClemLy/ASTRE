package astre.vue.previsionnel.module;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import astre.Controleur;

public class PanelModule  extends JPanel implements ActionListener
{
	/*-------------*/
	/*--Attributs--*/
	/*-------------*/

	private Controleur ctrl;

	private TextField tfType;
	private TextField tfSemestre;
	private TextField tfCode;
	private TextField tfLibLong;
	private TextField tfLibCourt;

	private JButton btnEnregistrer;
	private JButton btnAnnuler;

	/*----------------*/
	/*--Constructeur--*/
	/*----------------*/
	
	public PanelModule ( Controleur ctrl )
	{
		this.ctrl = ctrl;
		/* ------------------------- */
		/* Création des composants   */
		/* ------------------------- */

		this.tfType	    = new TextField ("", 10);
		this.tfSemestre	= new TextField ("", 2);
		this.tfCode	    = new TextField ("", 5);
		this.tfLibLong	= new TextField ("", 20);
		this.tfLibCourt	= new TextField ("", 10);

		this.add ( new JLabel ( "Type : " ) );
		this.add ( this.tfType     );

		this.add ( new JLabel ( "Semestre : " ) );
		this.add ( this.tfSemestre );

		this.add ( new JLabel ( "Code : "  ) );
		this.add ( this.tfCode     );

		this.add ( new JLabel ( "Libellé long : " ) );
		this.add ( this.tfLibLong  );

		this.add ( new JLabel ( "Libellé court : " ) );
		this.add ( this.tfLibCourt );

		this.btnEnregistrer = new JButton ( "Enregistrer" );
		this.btnAnnuler     = new JButton ( "Annuler"     );

		this.add ( this.btnEnregistrer );
		this.add ( this.btnAnnuler     );

		/* ------------------------- */
		/* Activation des composants */
		/* ------------------------- */

		this.tfType    .setEnabled ( false );
		this.tfSemestre.setEnabled ( false );

		this.tfCode        .addActionListener(this);

		this.btnEnregistrer.addActionListener(this);
		this.btnAnnuler    .addActionListener(this);
	}

	/* ActionListener */
	public void actionPerformed ( ActionEvent e )
	{
		if (e.getSource() == this.tfCode)
		{
			String code = this.tfCode.getText();
	
			if (code.startsWith("R"))
			{
				this.tfType.setText("Ressource");
			}

			if (code.startsWith("S"))
			{
				this.tfType.setText("SAE");
			}

			int valSemestre = Character.getNumericValue(code.charAt(1));

			if (valSemestre >= 1 && valSemestre <= 6)
			{
				this.tfSemestre.setText("S" + valSemestre);
			}
		}

		if ( e.getSource ( ) == this.btnEnregistrer )
		{
			System.out.println("Enregistrer");
		}
	}
}