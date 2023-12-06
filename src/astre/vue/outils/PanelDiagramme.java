package astre.vue.outils;

// General
import java.util.*;

//IHM
import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

//Graphiques
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.*;
import org.jfree.chart.labels.*;

/**
 * @author Maximeuuu
 * Classe permettant de générer seule un diagramme en fournissant les données et les couleurs
 */
public class PanelDiagramme extends JPanel
{
	private static final Color COULEUR_DEFAUT = Color.GRAY;
	
	private Map donnees;
	
	public PanelDiagramme ( Map donnees, Color couleurMin, Color couleurMax )
	{
		this.donnees = donnees;
		this.setSize( new Dimension ( 800,500 ) );
		
		//Ajout des données
		final DefaultPieDataset pieDataset = new DefaultPieDataset ( );
		
		Iterator iterator = this.donnees.entrySet ( ).iterator ( );
		while ( iterator.hasNext ( ) )
		{
			Map.Entry mapentry = ( Map.Entry ) iterator.next ( );
			pieDataset.setValue ( ( String ) mapentry.getKey ( ), ( Double ) mapentry.getValue ( ) );
		}
		
		final JFreeChart pieChart = ChartFactory.createPieChart ( "Répartitions des heures de l'enseignant X", pieDataset, true, true, true );
		final ChartPanel cPanel = new ChartPanel ( pieChart );
		
		// Ajout des couleurs
		PiePlot plot = ( PiePlot ) pieChart.getPlot ( );
		
		int nbDonnes = donnees.size ( );
		List<Color> degrade = generateGradientColors ( couleurMin, couleurMax, nbDonnes );
		int cptD=0;
		iterator = this.donnees.entrySet ( ).iterator ( );
		while ( iterator.hasNext ( ) )
		{
			Map.Entry mapentry = ( Map.Entry ) iterator.next ( );
			
			if ( cptD == nbDonnes-1 ) { plot.setSectionPaint ( ( String ) mapentry.getKey ( ), COULEUR_DEFAUT ); }
			else
			{
				plot.setSectionPaint ( ( String ) mapentry.getKey ( ), degrade.get ( cptD ) );
			}
			cptD++;
		}

		plot.setShadowPaint          ( null );
		plot.setBackgroundAlpha      ( 0.0f ) ;
		plot.setSimpleLabels         ( true );
		plot.setLabelBackgroundPaint ( null );
		plot.setLabelOutlinePaint    ( null );
		plot.setLabelShadowPaint     ( null );
		plot.setLabelPaint           ( Color.WHITE );
		
		Font font = new Font ( "Arial", Font.BOLD, 14 );
		plot.setLabelFont      ( font );
		plot.setLabelGenerator ( new StandardPieSectionLabelGenerator ( "{1}h" ) );


		this.add ( cPanel, BorderLayout.CENTER );
		this.setVisible ( true );
	}

	public static void main ( String[] args )
	{
		// Donnees
		Map donnees = new LinkedHashMap<String,Double> ( );
		donnees.put ( "TP", 56+56+12+12+0.0 );
		donnees.put ( "TD", 28+28+9+9+19.5 );
		donnees.put ( "CM", 28.0 );
		/*donnees.put ( "A", 28.0 );
		donnees.put ( "B", 28.0 );
		donnees.put ( "C", 28.0 );*/
		donnees.put ( "NA", 20.0 ); //((int)(165.5))-(heuresTP+heuresTD+heuresCM)
		
		// Generation
		JFrame f = new JFrame ( );
		f.setVisible ( true );
		System.out.println ( "Diagramme généré" );
		f.add ( new PanelDiagramme ( donnees, new Color ( 255,99,71 ), new Color ( 255,192,124 ) ) );
		f.pack ( );
	}

	public static List<Color> generateGradientColors ( Color startColor, Color endColor, int steps )
	{
		List<Color> gradientColors = new ArrayList<> ( );

		for ( int i = 0; i < steps; i++ )
		{
			float ratio = ( float ) i / ( float ) ( steps - 1 );
			
			int red   = ( int ) ( startColor.getRed   ( ) + ratio * ( endColor.getRed   ( ) - startColor.getRed   ( ) ) );
			int green = ( int ) ( startColor.getGreen ( ) + ratio * ( endColor.getGreen ( ) - startColor.getGreen ( ) ) );
			int blue  = ( int ) ( startColor.getBlue  ( ) + ratio * ( endColor.getBlue  ( ) - startColor.getBlue  ( ) ) );
			
			Color color = new Color ( red, green, blue );
			gradientColors.add ( color );
		}

		return gradientColors;
	}
}