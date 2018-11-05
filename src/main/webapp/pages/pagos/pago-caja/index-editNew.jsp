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
		src="${pageContext.request.contextPath}/pages/pagos/pago-caja/js/index-editNew.js"
		type="text/javascript"></script>
	]]>
</jsp:text>
</head>
<body>
	<s:set var="varTheme" value="%{getText('mx.edu.spee.defaulTheme')}" />

	<div class="row title">
		<div class="col-md-12">
			<h1 class="title">
				<s:text name="Pago Caja"></s:text>
			</h1>
		</div>
	</div>

	<div class="row form-group">
		<div class="col-md-8 col-md-offset-2">
			<s:actionerror theme="%{varTheme}" />
			<s:actionmessage theme="%{varTheme}" />
		</div>
	</div>

	<div class="col-md-12">
		<div class="outter-section form-medium text-left">
			<s:text name="Campos Obligatorios" />
		</div>
	</div>

	<s:form
		action="%{#pageContext.request.contextPath}/pagos/pago-caja/"
		method="post" theme="simple">
		<fieldset class="form form-horizontal form-medium">
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Servicio" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:select id="alumnoCheckId" class="form-control" headerKey="-1"
						headerValue="Seleccione" list="listServicios" listKey="id"
						listValueKey="descripcion" name="model.idCatalogoServicio"
						cssErrorClass="field-error" />
					<s:fielderror fieldName="model.idPerfil" cssClass="error"
						theme="%{#varTheme}" />
				</div>
			</div>
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Correo" />
				</label>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<s:textfield cssClass="form-control campo"
						cssClassError="input-error" name="correo" id="txCorreo" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label label-obligatorio "> <s:text
						name="Ticket Caja"></s:text>
				</label>
				<div class="col-md-8">
					<div class="input-group">
						<input type="text" class="form-control" id="fileName"
							readonly="true" /> <span class="input-group-btn" id="loadImage">
							<i class="material-icons md-24 md-eld">file_upload</i>
						</span>
					</div>
					<s:fielderror fieldName="archivo.fileUpload" cssClass="error"
						theme="bootstrap" />
				</div>
				<div class="col-xs-12 col-sm-8 col-md-8">
					<input type='file' id="fileUpload" name="archivo.fileUpload"
						class="hide" accept=".pdf,png,jpeg" value="" />
				</div>
			</div>
		</fieldset>

		<div class="outter-section form-medium text-right">
			<div class="col-xs-12 col-md-12 col-md-12 text-right">
				<s:submit cssClass="btn btn-default-spee" value="Aceptar" />
			</div>
		</div>
	</s:form>
</body>
	</html>
</jsp:root>