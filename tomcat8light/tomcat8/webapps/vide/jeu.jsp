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
<h3>Le premier arrivée à 10 a gagné !</h3>
<h3>Deux faces identiques je gagne, deux faces differentes vous gagnez !<h3>
<h3>Alors, prêt à relever le défi ??</h3>
Scores de <%=jeu.getPointsHumain()%> à <%=jeu.getPointsOrdi()%>
Vous jouez Pile ou Face ?
</center>
</body>
</html>