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
				<s:text name="Editar Responsable de Area"></s:text>
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
		action="%{#pageContext.request.contextPath}/area/gestionar-responsable-area/1"
		method="post" theme="simple">
		<fieldset class="form form-horizontal form-medium">
			<legend class="form-section">
				<s:text name="Informacion del responsable de area" />
			</legend>
			<!-- Nombres -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Nombre(s)" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo"
						cssClassError="input-error" name="'Roberto'" id="txNombre" />
					<s:fielderror fieldName="" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<!-- Primer apellido -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Primer Apellido" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo" name="'Mendoza'"
						cssClassError="input-error" id="txPrimerApellido" />
					<s:fielderror fieldName="" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<!-- Segundo apellido -->
			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
					<s:text name="Segundo Apellido" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo" name="'Saavedra'"
						cssClassError="input-error" id="txSegundoApellido" />
					<s:fielderror fieldName="" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<!-- Número de empleado -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio">
					<s:text name="Numero de empleado" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo" name="'1624568'"
						cssClassError="input-error" id="txNoEmpleado" />
					<s:fielderror fieldName="" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<!-- Área -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Area" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:select id="slcArea" class="form-control" headerKey="-1"
						headerValue="Celex"
						list="#{'1':'Celex', '2':'Biblioteca', '3':'Fotocopiado', '4':'Servicios Dentales'}"
						name="selectValue" cssErrorClass="field-error" />
					<s:fielderror fieldName="" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
			<!-- Fecha de alta -->
			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio" for=""><s:text
						name="Fecha de alta" /></label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<sj:datepicker id="dpALta" cssClass="form-control date-picker" name="'11/10/2018'"
						showOn="focus" inputAppendIcon="calendar" changeYear="true"
						changeMonth="true" readonly="true" showAnim="fadeIn"
						parentTheme="bootstrap" />
				</div>
			</div>
			<!-- Correo electrónico -->
			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Correo electronico" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo" name="'isc.robertomendoza@gmail.com'"
						cssClassError="input-error" />
					<s:fielderror fieldName="" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
			<!-- Número telefónico -->
			<div class="form-group">
				<label class=" col-sm-4 col-md-4 control-label"
					for=""> <s:text name="Numero telefonico" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo" name=""
						cssClassError="input-error" />
					<s:fielderror fieldName="" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
		</fieldset>
		<!-- Botones de Aceptar y Cancelar -->
		<div class="outter-section form-medium text-right">
			<div class="col-xs-12 col-md-12 col-md-12 text-right">
				<s:submit cssClass="btn btn-default-spee" value="Aceptar" />
				<a class="btn btn-default-spee"
					href="${pageContext.request.contextPath}/area/gestionar-responsable-area">Cancelar</a>
			</div>
		</div>
	</s:form>
</body>
	</html>
</jsp:root>