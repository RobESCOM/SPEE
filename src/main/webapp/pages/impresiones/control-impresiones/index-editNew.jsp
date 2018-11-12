<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script
	 src="${pageContext.request.contextPath}/pages/impresiones/control-impresiones/js/index-editNew.js"
	 type="text/javascript"></script>
	]]>
</jsp:text>

<s:set var="varTheme" value="%{getText('mx.edu.spee.defaulTheme')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Reducir impresiones" />
		</h1>
	</div>
</div>

<div class="row form-group">
	<div class="col-md-12">
		<s:actionerror theme="%{varTheme}" />
		<s:actionmessage theme="%{varTheme}" />
	</div>
</div>

<div class="form-section form-horizontal" onload="inicio()">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label"
					for=""> <s:text name="Nombre" />
				</label>
				<s:set var="nombre" value="usrInformacion"></s:set>
				<label
					class="col-xs-12 col-sm-5 col-md-5"
					for=""> <s:text name="Nombre">${nombre.nombre}</s:text>
				</label>
			</div>
			<div class="col-md-12">
				<label
					class="col-xs-12 col-sm-4 col-md-4 control-label"
					for=""> <s:text name="Impresiones disponibles en negro" />
				</label>
				<s:set var="impresion" value="usrImpresiones"></s:set>
				<label
					class="col-xs-12 col-sm-5 col-md-5"
					for=""> <s:text name="impresiones">${impresion.nu_impresiones}</s:text>
				</label>
			</div>
			
			<s:form	action="%{#pageContext.request.contextPath}/impresiones/control-impresiones"
				method="post" theme="simple">
				<s:hidden name="idSel"></s:hidden>
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
						for=""> <s:text name="Tipo de impresion" />
					</label>
					<div class="col-xs-12 col-sm-6 col-md-6">
						<s:select id="ImpresionCheckId" class="form-control" headerKey="-1"
							list="#{'1':'Impresion en Negro', '2':'Impresion en color', '3':'Impresion en doble carta', '4':'Impresion en 1/4 plotter', '5':'Impresion en 1/2 plotter', '6':'Impresion en plotter completo'}"
							name="tipoImpresion">	
						</s:select>
					</div>
				</div>
				
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
						for=""> <s:text name="Numero de impresiones" />
					</label>
					<div class="col-xs-12 col-sm-2 col-md-2">
						<s:textfield type="number" id="numeroImpresion" class="form-control" name="numeroImpresion" onclick="valida()" value="0" min="1"/>
					</div>
				</div>
				
				
				<div id="divNumero" class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label"
						for="" id="texto"><s:text name=""/>
					</label>
					<div id="nuevoValor" class="col-xs-12 col-sm-2 col-md-2">
						<s:text name="" />
					</div>
				</div>
				
				<div class="outter-section form-medium text-right">
					<div class="col-xs-12 col-md-12 col-md-12 text-right">
						<s:submit cssClass="btn btn-default-spee" value="Aceptar" />
						<a class="btn btn-default-spee"
							href="${pageContext.request.contextPath}/impresiones/control-impresiones">Cancelar</a>
					</div>
				</div>
				
			</s:form>
			
		</div>
	</div>
</div>

</html>
</jsp:root>