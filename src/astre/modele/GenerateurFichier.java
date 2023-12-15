package astre.modele;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import astre.modele.elements.Intervenant;

/** Page de génération des fichiers
  * @author : Matéo Sa, Lebaron Alizéa
  * @version : 1.0 - 11/12/2023
  * @date : 06/12/2023
  */

public class GenerateurFichier
{
	public static void recapTtInter ( )
	{
        String chemin = "./fichierGenerer/recapTtIntervenant.csv";

        try ( BufferedWriter ecrivain = new BufferedWriter ( new FileWriter ( chemin ) ) )
		{
            // Ecriture de l'entête du CSV
			String entete = "Catégorie, Nom, Prénom, Service dû, Maximum heure, Coefficient TP, S1, S3, S5, sTot, S2, S4, S6, sTot, Total";

            ecrivain.write ( entete );
            ecrivain.newLine ( );
			
			BD bd = BD.getInstance ( ); //A changer ??
			Object[][] elem = bd.getIntervenantsTableau ( );
			for ( int i = 0; i < elem.length; i++ )
			{
				for ( int j=1; j < elem[0].length; j++ )
				{
					ecrivain.write ( elem[i][j].toString ( ) + "," );
				}
            	ecrivain.newLine ( );
			}

            System.out.println ( "Fichier CSV créé avec succès." );
        } catch ( IOException e )
		{
            e.printStackTrace ( );
        }
	}

	public static void GenererHTMLIntervenant (Intervenant inter)
	{
		String chemin = "./fichierGenerer/recapIntervenant" + inter.getNom() + ".html";

		try ( BufferedWriter ecrivain = new BufferedWriter ( new FileWriter ( chemin ) ) )
		{
            // Ecriture de l'entête
			String entete = 
			"<!DOCTYPE html>"                                                                             + 
			"<html lang=\"fr\">"                                                                          + 
			"<head>"                                                                                      + 
				"<meta charset=\"UTF-8\">"                                                                + 
				"<link href=\"style.css\" rel=\"stylesheet\">"                                            + 
				"<title>Intervenant [NOM]</title>"                                                        + 
			"</head>"                                                                                     + 
			"<body>"                                                                                      +
				"<table>"                                                                                 +
					"<tr>"                                                                                +
			  			"<th colspan=\"4\">"+ inter.getPrenom() +  inter.getNom().toUpperCase() + "</th>" +
					"</tr>"                                                                               +
					"<tr>"                                                                                +
						"<td class=\"first\">Numéro de ligne</td>"                                        +
						"<td class=\"first\">Code de la matière et nom</td>"                              +
						"<td class=\"first\">Heure dans la matière</td>"                                  +
					"</tr>"                                                                               ;

            ecrivain.write ( entete );

			
			// Ecriture du corps du tableau
			
			BD bd = BD.getInstance ( );
			Map<Integer, List<Object>> ensDonnes = bd.getHeureIntervenant(inter.getId());

			String corps = "";

			for (Integer key : ensDonnes.keySet())
			{
				corps += 
				    	"<tr>" +
							"<td class=\"first\">"+ key                                                         + "</td>" +
							"<td class=\"first\">"+ ensDonnes.get(key).get(0) + " " + ensDonnes.get(key).get(2) + "</td>" +
							"<td class=\"first\">Heure dans la matière</td>"                                    +
						"</tr>";
			}

			

			//Écriture fermant les balises html

			String footer = 
			"</table>" +
			"</body>"  +
			"</html>"  ;



            System.out.println ( "Fichier CSV créé avec succès." );
        } catch ( IOException e )
		{
            e.printStackTrace ( );
        }



	}
}
