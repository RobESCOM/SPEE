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
		src="${pageContext.request.contextPath}/pages/pagos/cargar-pago/js/index-editNew.js"
		type="text/javascript"></script>
	]]>
</jsp:text>
</head>
<body>

	<s:set var="varTheme" value="%{getText('mx.edu.spee.defaulTheme')}" />
	<div class="row title">
		<div class="col-md-12">
			<h1 class="title">
				<s:text name="Cargar Comprobante SIG@" />
			</h1>
		</div>
	</div>

	<div class="row form-group">
		<div class="col-md-12">
			<s:actionerror theme="%{varTheme}" />
			<s:actionmessage theme="%{varTheme}" />
		</div>
	</div>


	<s:form id="frmActualizarEstadoPago" enctype="multipart/form-data"
		action="%{#pageContext.request.contextPath}/pagos/gestionar-autorizacion-pagos!cargarSiga"
		theme="simple" method="post">
		<s:hidden name="idSel" value="%{idSel}"></s:hidden>
		<fieldset
			class="form-section form-horizontal form-medium text-justify">
			<label> <s:text
					name="El archivo que proporciona representa un comprobante de pago del subsistema SIG@: " />
			</label>
			<div class="text-left">
				<ul>
					<li>El formato debe ser .pdf o .png</li>
					<li>Debe no mas de no más de 10 MB</li>
					<li>Debe visualizarse claramente</li>
				</ul>
			</div>
			<div class="form-group">
				<label class="col-md-4 control-label label-obligatorio "> <s:text
						name="Comprobante SIG@ "></s:text>
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
		<div class="form-medium text-right">
			<s:submit class="btn btn-default-spee" value="Adjuntar Comprobante"></s:submit>
		</div>
	</s:form>
</body>
<div class="text-left">
	<div class="col-md-12"></div>
</div>
	</html>
</jsp:root>