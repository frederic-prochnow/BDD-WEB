Create table personnes (
login VARCHAR(20) PRIMARY KEY NOT NULL,
mdp VARCHAR(20) NOT NULL,
nom VARCHAR(20) NOT NULL,
prenom VARCHAR(20) NOT NULL,
adresse VARCHAR(20),
email VARCHAR(20),
tel INTEGER,
datenaissance INTEGER);
INSERT INTO personnes (login,mdp,nom,prenom,tel) VALUES ('francois.mitterand','fm','francois','mitterand',0123456789);
INSERT INTO personnes (login,mdp,nom,prenom,tel) VALUES ('francois.holland','fh','francois','holland',0123456789);
INSERT INTO personnes (login,mdp,nom,prenom,tel) VALUES ('martine.aubry','ma','martine','aubry',0123456789);
INSERT INTO personnes (login,mdp,nom,prenom,tel) VALUES ('pierre.caudron','pc','pierre','caudron',0123456789);
INSERT INTO personnes (login,mdp,nom,prenom,tel) VALUES ('manuel.valls','mv','manuel','valls',0123456789);
