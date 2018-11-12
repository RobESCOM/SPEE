<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
	<div id="notfound">
		<div class="notfound">
			<h2>Algo ha ocurrido</h2>
			<p>Disculpe las molestias, por favor contacte con el
				administrador</p>
			<a
				href="${pageContext.request.contextPath}/control-acceso/gestionar-bienvenida">Ir
				a Inicio</a>
		</div>
	</div>
</body>
	</html>
</jsp:root>