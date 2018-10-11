<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:text>
	<![CDATA[
	<script
		src="${pageContext.request.contextPath}/pages/area/gestionar-responsable-area/js/index-editNew.js"
		type="text/javascript"></script>
	]]>
</jsp:text>
</head>
<body>
	<div class="row title">
		<div class="col-md-12">
			<h1 class="title">
				<s:text name="Visualizar Responsable de Area"></s:text>
			</h1>
		</div>
	</div>

	<fieldset class="form form-horizontal form-medium">
		<legend class="form-section">
			<s:text name="Informacion del responsable de area" />
		</legend>
		<!-- Nombres -->
		<div class="form-group">
			<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
				<s:text name="Nombre(s)" />
			</label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'Roberto'" />
			</div>
		</div>
		<!-- Primer apellido -->
		<div class="form-group">
			<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
				<s:text name="Primer Apellido" />
			</label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'Mendoza'" />
			</div>
		</div>
		<!-- Segundo apellido -->
		<div class="form-group">
			<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
				<s:text name="Segundo Apellido" />
			</label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'Saavedra'" />
			</div>
		</div>
		<!-- Número de empleado -->
		<div class="form-group">
			<label class="col-xs-12 col-sm-4 col-md-4 control-label"> <s:text
					name="Numero de empleado" />
			</label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'1624568'" />
			</div>
		</div>
		<!-- Área -->
		<div class="form-group">
			<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
				<s:text name="Area" />
			</label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'Celex'" />
			</div>
		</div>
		<!-- Fecha de alta -->
		<div class="form-group">
			<label class="col-xs-12 col-sm-4 col-md-4 control-label" for=""><s:text
					name="Fecha de alta" /></label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'11/10/2018'" />
			</div>
		</div>
		<!-- Correo electrónico -->
		<div class="form-group">
			<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
				<s:text name="Correo electronico" />
			</label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'isc.robertomendoza@gmail.com'" />
			</div>
		</div>
		<!-- Número telefónico -->
		<div class="form-group">
			<label class=" col-sm-4 col-md-4 control-label" for=""> <s:text
					name="Numero telefonico" />
			</label>
			<div class="col-xs-12 col-sm-6 col-md-6 text-justify">
				<s:property value="'Sin telefono'" />
			</div>
		</div>
	</fieldset>
	<!-- Botones de Aceptar y Cancelar -->
	<div class="outter-section form-medium text-right">
		<div class="col-xs-12 col-md-12 col-md-12 text-right">
			<a class="btn btn-default-spee"
				href="${pageContext.request.contextPath}/area/gestionar-responsable-area">Regresar</a>
		</div>
	</div>
</body>
	</html>
</jsp:root>