<%@ page pageEncoding="UTF-8" %>
<%@ page import="metier.Personne" %>
<%@page import="java.util.*"%>
<%@page session="true"%>
<%@page errorPage="erreur.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title> MaPage </title>			     
</head>
<body>
<center>
<h1>ma requete</h1>
<%
// initialisation du compteur global
	Personne global=(Personne)application.getAttribute("global");
	if(global==null){  
		global= new Personne();
		application.setAttribute("global",global);
	}
	global.incrementeI();
	
// initialisation du compteur local
	Personne local=(Personne)session.getAttribute("local");
	
	if(local==null){  
		local= new Personne();
		session.setAttribute("local",local);
	}
	local.incrementeI();
%>
Vous avez accédé <%=local.getI()%> fois à cette page sur les <%=global.getI()%> accès effectués.
</body>
</html>