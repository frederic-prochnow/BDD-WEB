<%@ page pageEncoding="UTF-8" %>
<%@ page import="metier.JeuPileOuFace" %>
<%@page import="java.util.*"%>
<%@page session="true"%>
<%@page errorPage="erreur.jsp"%>
<html>
<head>
<title>MON JEU</title>
</head>
<body>
<center>
<%
	JeuPileOuFace jeu = new JeuPileOuFace();
	jeu = (JeuPileOuFace)session.getAttribute("jeu");
	jeu.init();
%>
<h1>Pile ou face</h1>
<h3>Le premier arriv�e � 10 a gagn� !</h3>
<h3>Deux faces identiques je gagne, deux faces differentes vous gagnez !<h3>
<h3>Alors, pr�t � relever le d�fi ??</h3>
Scores de <%=jeu.getPointsHumain()%> � <%=jeu.getPointsOrdi()%>
Vous jouez Pile ou Face ?
</center>
</body>
</html>