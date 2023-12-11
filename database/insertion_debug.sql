/*
	@author Alizéa LEBARON
	@version 1.0 - 11/12/2023 
	@date 11/12/2023 
	@description Script d'insertion pour tester notre base'
*/

DELETE FROM Intervenant CASCADE;
DELETE FROM Contrat     CASCADE;

INSERT INTO Contrat (nomContrat, hServiceContrat, hMaxContrat, ratioTP) VALUES
('Enseignant 2nd degrès', 250, 360, 1.0  ),
('Enseignant chercheur' , 25 , 389, 0.66 ),
('Contractuel'          , 85 , 125, 0.66 );

INSERT INTO Intervenant (nomInter, prenom, hService, hMax, Id_Contrat) VALUES
('De la Fontaine', 'Jean'   , 250, 360, 1 ),
('Orwell'        , 'Georges',  25, 389, 2 ),
('Lovecraft'     , 'Howard' ,  85, 125, 2 );

insert into semestre values ( 1, 5, 6, 85, 10);
insert into semestre values ( 2, 5, 6, 66, 20);
insert into semestre values ( 3, 8, 6, 58, 30);
insert into semestre values ( 4, 8, 6, 45, 40);
insert into semestre values ( 5,10, 6, 25, 50);
insert into semestre values ( 6,10, 6, 10, 60);