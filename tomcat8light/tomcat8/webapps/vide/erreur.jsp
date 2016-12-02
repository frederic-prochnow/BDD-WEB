<%@page isErrorPage="true"%>
<html><head>
<title>
page d'affichage d'erreur
</title>
</head>
<body>
<center>
<h1>E R R O R </h1>
<%=exception.getMessage().toString()%>
</center>
</body>
</html>