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
		src="${pageContext.request.contextPath}/pages/control-acceso/registrar-usuario/js/index-editNew.js"
		type="text/javascript"></script>
	]]>
</jsp:text>
</head>
<body>
	<div class="row title">
		<div class="col-md-12">
			<h1 class="title">
				<s:text name="Agendar Cita"></s:text>
			</h1>
		</div>
	</div>

	<!-- Action messages -->
	<div class="form-group">
		<div class="col-md-8 col-md-offset-2">
			<s:actionmessage cssClass="alert alert-success" />
			<s:actionerror cssClass="alert alert-danger" />
		</div>
	</div>

	<!-- Campos obligatorios -->
	<div class="col-md-12">
		<div class="outter-section form-medium text-left">
			<s:property value="'Los campos marcados con * son obligatorios.'" />
		</div>
	</div>

	<s:form
		action="%{#pageContext.request.contextPath}/citas/gestionar-citas-dentales"
		method="post" theme="simple">
		<fieldset class="form form-horizontal form-medium">
			<legend class="form-section">
				<s:text name="Datos de la cita" />
			</legend>
			<!-- Fecha de la cita -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-6 col-md-6 control-label label-obligatorio text-xs-left">
					<s:text name="Fecha de la cita" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<sj:datepicker id="id_fhCita" name="fecha"
						displayFormat="dd/MM/yy" cssClass="form-control date-picker"
						showOn="focus" inputAppendIcon="calendar" changeYear="true"
						changeMonth="true" readonly="true" showAnim="fadeIn"
						parentTheme="bootstrap" value="" />
					<s:fielderror fieldName="fecha" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
			<!-- Hora de la cita -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-6 col-md-6 control-label label-obligatorio"
					for=""> <s:text name="Hora de la cita" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:select id="slcHora" class="form-control" headerKey=""
						headerValue="Seleccione" list="listHoras"
						name="model.idHora" listValue="hora" listKey="id"
						cssErrorClass="field-error" />
					<s:fielderror fieldName="model.idHora" cssClass="error" theme="bootstrap" />
				</div>
			</div>
		</fieldset>

		<!-- Botones de Aceptar y Cancelar -->
		<div class="outter-section form-medium text-right">
			<div class="col-xs-12 col-md-12 col-md-12 text-right">
				<s:submit cssClass="btn btn-default-spee" value="Aceptar" />
				<a class="btn btn-default-spee"
					href="${pageContext.request.contextPath}/citas/gestionar-citas-dentales">Cancelar</a>
			</div>
		</div>
	</s:form>
</body>
	</html>
</jsp:root>