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
	<s:set var="tool"
		value="'Nullam rhoncus ex eget lacinia ornare. Morbi dictum ante sed nulla maximus viverra. Morbi eu lorem vitae est condimentum iaculis sit amet vel sapien. Orci varius natoque penatibus et magnis dis parturient montes,
	 		nascetur ridiculus mus. Morbi dolor augue, gravida nec luctus vel, auctor at lectus. '" />
	<s:set var="btnAceptar" value="%{getText('mx.com.spee.boton.aceptar')}" />
	<s:set var="btnCancelar"
		value="%{getText('mx.com.spee.boton.cancelar')}" />

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
				<s:text name="CU1_SUBTITLE" />
			</legend>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL1" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo"
						cssClassError="input-error" name="info.nombre" id="txNombre" />
					<s:fielderror fieldName="info.nombre" cssClass="error" theme="" />
				</div>
			</div>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL2" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="info.primerApellido" 
						cssClassError="input-error" id="txUsuario" />
					<s:fielderror fieldName="info.primerApellido" cssClass="error" theme="" />
				</div>
			</div>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL3" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="info.segundoApellido" 
						cssClassError="input-error" id="txUsuario" />
					<s:fielderror fieldName="" cssClass="error" theme="" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
					<s:text name="CU1_LBL4" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="info.curp" 
						cssClassError="input-error" id="txUsuario" />
					<s:fielderror fieldName="" cssClass="error" theme="" />
				</div>
			</div>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL5" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="info.boleta" 
						cssClassError="input-error" id="txUsuario" />
					<s:fielderror fieldName="" cssClass="error" theme="" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-12 col-sm-4 col-md-4 control-label">
					<i class="material-icons md-15 md-eld" data-toggle="tooltip"
						data-placement="top" title="${tool}"> &#xE887; </i> &#160; <label
						class="label-obligatorio" for=""> <s:text name="CU1_LBL6" />
					</label>
				</div>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="info.noEmpleado" 
						cssClassError="input-error"  />
					<s:fielderror fieldName="" cssClass="error" theme="" />
				</div>
			</div>
			<div class="form-group">
				<label class=" col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL7" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="usuario.login" 
						cssClassError="input-error" />
					<s:fielderror fieldName="" cssClass="error" theme="" />
				</div>
			</div>
			<div class="form-group">
				<label class=" col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="CU1_LBL8" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="info.celular" 
						cssClassError="input-error"/>
					<s:fielderror fieldName="" cssClass="error" theme="" />
				</div>
			</div>
			
			<div class="form-group">
				<label class=" col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Passwor" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo" name="usuario.password" 
						cssClassError="input-error"/>
					<s:fielderror fieldName="" cssClass="error" theme="" />
				</div>
			</div>
		</fieldset>

		<div class="outter-section form-medium text-right">
			<div class="col-xs-12 col-md-12 col-md-12 text-right">
				<s:submit cssClass="btn btn-primary" value="%{btnAceptar}" />
				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/control-acceso/login">${btnCancelar}</a>
			</div>
		</div>

	</s:form>
</body>
	</html>
</jsp:root>