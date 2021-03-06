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
		src="${pageContext.request.contextPath}/pages/servicio7gestionar-servicio/js/index-editNew.js"
		type="text/javascript"></script>
	]]>
</jsp:text>
</head>
<body>
	<div class="row title">
		<div class="col-md-12">
			<h1 class="title">
				<s:text name="Editar Informacion de Servicio"></s:text>
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
		action="%{#pageContext.request.contextPath}/servicio/gestionar-servicio/%{idSel}"
		method="post" theme="simple">
		<s:hidden name="_method" value="PUT" />

		<fieldset class="form form-horizontal form-medium">
			<legend class="form-section">
				<s:text name="Informacion del servicio" />
			</legend>
			<s:hidden id="idServicio"  value="%{model.id}"/>
			<s:hidden id="txEstatus" value="%{model.estatus}"/>
			<!-- Clave del servicio -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Clave del servicio" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo"
						cssClassError="input-error" name="model.clave" id="txClave" maxlength="4"/>
					<s:fielderror fieldName="model.clave" cssClass="error" theme="bootstrap" />
				</div>
			</div>
			<!-- Tipo de servicio -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Tipo de servicio" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:select id="slcTipoServicio" class="form-control"
						headerKey="%{model.idTipoServicio}"
						headerValue="%{model.tipoServicio}" list="listTipos"
						listValue="nombre" listKey="id" name="model.idTipoServicio"
						cssErrorClass="field-error" />
					<s:fielderror fieldName="model.idTipoServicio" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
			<!-- Descripción -->
			<div class="form-group">
				<label class="col-xs-12 col-sm-4 col-md-4 control-label" for="">
					<s:text name="Descripcion" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textarea cssClass="form-control campo" name="model.descripcion"
						cssClassError="input-error" id="txDescripcion" maxlength="150"
						rows="5" />
					<s:fielderror fieldName="model.descripcion" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
			<!-- Costo -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio">
					<s:text name="Costo (MXN)" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:textfield cssClass="form-control campo" name="model.precio"
						cssClassError="input-error" id="txCosto" type="number" min="1"
						max="10000" />
					<s:fielderror fieldName="model.precio" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
			<!-- Area a la que perteneces -->
			<div class="form-group">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
					for=""> <s:text name="Area a la que pertenece" />
				</label>
				<div class="col-xs-12 col-sm-6 col-md-6">
					<s:select id="slcArea" class="form-control" headerKey="%{model.idArea}"
						headerValue="%{model.area}" list="listAreas" listValue="nombreArea"
						listKey="id" name="model.idArea" cssErrorClass="field-error" />
					<s:fielderror fieldName="model.idArea" cssClass="error"
						theme="bootstrap" />
				</div>
			</div>
		</fieldset>
		<!-- Botones de Aceptar y Cancelar -->
		<div class="outter-section form-medium text-right">
			<div class="col-xs-12 col-md-12 col-md-12 text-right">
				<s:submit cssClass="btn btn-default-spee" value="Aceptar" />
				<a class="btn btn-default-spee"
					href="${pageContext.request.contextPath}/servicio/gestionar-servicio">Cancelar</a>
			</div>
		</div>
	</s:form>
</body>
	</html>
</jsp:root>