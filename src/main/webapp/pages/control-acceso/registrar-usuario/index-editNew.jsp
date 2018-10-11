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
				<s:text name="CU1_TITLE"></s:text>
			</h1>
		</div>
	</div>

	<div class="form-group">
		<div class="col-md-8 col-md-offset-2">
			<s:actionmessage cssClass="alert alert-success" />
			<s:actionerror cssClass="alert alert-danger" />
		</div>
	</div>

	<div class="col-md-12">
		<div class="outter-section form-medium text-left">
			<s:text name="mx.com.spee.label.camposObligatorios" />
		</div>
	</div>

	<s:form
		action="%{#pageContext.request.contextPath}/control-acceso/registrar-usuario"
		method="post" theme="simple">
		<fieldset class="form form-horizontal form-medium">
			<legend class="form-section">
				<s:text name="Datos Personales" />
			</legend>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Perfil" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:select id="alumnoCheckId" class="form-control" headerKey="-1"
						headerValue="Seleccione"
						list="#{'1':'Alumno', '2':'Profesor', '3':'Externo'}"
						name="selectValue" cssErrorClass="field-error" />
					<s:fielderror fieldName="estatusNuevo" cssClass="error" theme="bootstrap" />
				</div>
			</div>

			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Nombre(s)" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo"
						cssClassError="input-error" name="model.nombre" id="txNombre" />
					<s:fielderror fieldName="model.nombre" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL2" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo"
						name="model.primerApellido" cssClassError="input-error"
						id="txUsuario" />
					<s:fielderror fieldName="model.primerApellido" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Segundo Apellido" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo"
						name="model.segundoApellido" cssClassError="input-error"
						id="txSegundoApellido" />
					<s:fielderror fieldName="model.segundoApellido" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio">
					<s:text name="CURP" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="model.curp"
						cssClassError="input-error" id="txCurp" />
					<s:fielderror fieldName="model.curp" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<div id="divBoleta" class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Boleta" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="model.boleta"
						cssClassError="input-error" id="txBoleta" />
					<s:fielderror fieldName="model.boleta" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<div id="divNoEmpleado" class="form-group">
				<div class="col-xs-12 col-sm-4 col-md-4 control-label">
					<label class="label-obligatorio" for=""> <s:text
							name="CU1_LBL6" />
					</label>
				</div>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="model.noEmpleado"
						cssClassError="input-error" />
					<s:fielderror fieldName="model.noEmpleado" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<div class="form-group">
				<label class=" col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL7" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="usuario.login"
						cssClassError="input-error" />
					<s:fielderror fieldName="usuario.login" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<div class="form-group">
				<label class=" col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL8" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="model.celular"
						cssClassError="input-error" />
					<s:fielderror fieldName="model.celular" cssClass="error" theme="bootstrap" />
				</div>
			</div>

			<div class="form-group">
				<label class=" col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Password" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield type="password" cssClass="form-control campo"
						name="usuario.password" cssClassError="input-error" />
					<s:fielderror fieldName="usuario.password" cssClass="error" theme="bootstrap" />
				</div>
			</div>
		</fieldset>


		<div class="outter-section form-medium text-right">
			<div class="col-xs-12 col-md-12 col-md-12 text-right">
				<s:submit cssClass="btn btn-default-spee" value="Aceptar" />
				<a class="btn btn-default-spee"
					href="${pageContext.request.contextPath}/control-acceso/login">Cancelar</a>
			</div>
		</div>
	</s:form>

	<sj:dialog id="dlgDeleteContact" modal="true"
		title="Eliminar Medio de Contacto" autoOpen="false"
		openTopics="showDlgDeleteContact" closeTopics="closeDlgDeleteContact"
		resizable="true" draggable="false">
		<s:url var="urlAction" action="gestionar-contactos/"
			includeContext="true" />
		<s:hidden id="hdnIdContactoDelete" value="%{#urlAction}" />
		<div class="form-horizontal">
			<s:form id="frmDeleteContacto"
				action="%{#pageContext.request.contextPath}/aspirante/gestionar-contactos/%{#hdnIdContactoDelete}"
				method="post">
				<s:hidden name="_method" value="delete"></s:hidden>
				<div class="form-group">
					<div class="col-md-12 col-contact">
						<s:text name="IU1.4-3_MSG_ELIMINAR" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12 text-right">
						<s:submit id="btnDialogDeleteAceptar"
							class="btn btn-default btn-default-eld" title="%{#btnSi}"
							value="%{#btnSi}">
						</s:submit>
						<a id="btnDialogDeleteCancelar"
							class="btn btn-default btn-default-eld"><s:text name="No" /></a>
					</div>
				</div>
			</s:form>
		</div>
	</sj:dialog>
</body>
	</html>
</jsp:root>