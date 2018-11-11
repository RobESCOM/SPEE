<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script
		src="${pageContext.request.contextPath}/pages/pagos/gestionar-pagos/js/index-show.js"
		type="text/javascript"></script>
	]]>
</jsp:text>

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Visualizar Comprobante de Pago" />
		</h1>
	</div>
</div>

<s:set var="typeMime" value="application/pdf" />

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<s:set var="archivoPago" value="pagoSiga" ></s:set>
			<s:set var="anio" value="listAnio" ></s:set>
			<s:set var="mes" value="listMes"></s:set>
			<s:set var="archivo" value="%{pagoBs.obtenerArchivoSIGA(#archivoPago)}"></s:set>
			<object data="data:application/pdf;base64, ${archivo}"
				type="application/pdf" width="600" height="500">
				<a download="comprobante"></a>
			</object>
		</div>
	</div>
</div>

<s:set var="mes" value="listAnio"/>
<div class="row">
	<s:set var="area" value="%{pagoBs.obtenerArea(#archivoPago)}"></s:set>
	<div class="col-md-12">
		<a class="btn btn-primary" href="${pageContext.request.contextPath}/pagos/gestionar-archivo-pagos/show?${anio}=&amp;listMes=${mes}"><s:text name="Regresar" /></a>
		<s:if test="#area == 3">
			<a class="btn btn-primary" onclick="myClickDialog('${archivoPago.idPago}')"><s:text name="Agreagar impresiones" /></a>
		</s:if>
		<s:else>
			<a class="btn btn-primary" href="${pageContext.request.contextPath}/pagos/gestionar-pagos?idPago=${archivoPago.idPago}"><s:text name="Efectuar" /></a>
		</s:else>
	</div>
</div>

<sj:dialog id="registroPago" modal="true"
	title="Agregar impresiones" autoOpen="false"
	openTopics="showRegistro" closeTopics="closeRegistro" resizable="false"
	draggable="false" cssClass="hidden">
	
	<s:form id="frmRegistroImpresiones" action="%{pageContext.request.contextPath}/impresiones/control-impresiones!agregarImpresiones" theme="simple" method="post">
		<div class="row">
			<div class="col-md-12">
				<s:hidden name="idSel" value="%{#archivoPago.idPago}"></s:hidden>
				<s:hidden name="clave" value="%{#archivoPago.idCuenta}"></s:hidden>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<s:textfield type="number" id="numeroImpresion" class="form-control" name="numeroImpresion" value="15" min="15"></s:textfield>
			</div>
		</div>
		
		<!-- Botones de si y no -->
		<div class="row">
			<div class="text-right col-md-12">
				<s:submit cssClass="btn btn-default btn-default-eld" value="Si" />
				<a onclick="closeBajaDlg()" class="btn btn-default btn-default-eld"><s:text
						name="No" /></a>
			</div>
		</div>
	</s:form>
</sj:dialog>


<div class="text-left">
	<div class="col-md-12"></div>
</div>
	</html>
</jsp:root>