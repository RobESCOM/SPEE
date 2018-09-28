<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/pagos/gestionar-pagos-autorizados/js/index-editNew.js"></script>
	]]>
</jsp:text>

<s:set var="listPagosAutorizados"
	value="{{'Amalgama', '$480 MXN', 'Colocación de amalgama'},
	 {'Limpieza dental', '$580 MXN', 'Limpieza dental'}}" />

<s:set var="ttbVisualizar" value="%{getText('tooltipVisualizar')}" />
<s:set var="ttbArchivar" value="%{getText('tooltipArchivar')}" />

<s:set var="varIconoVisualizar" value="'&#xE8F4;'" />
<s:set var="varIconoArchivar" value="'&#xE149;'" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="CU92_TITLE" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblPagosAutorizados" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="CU92_THEAD1" /></th>
							<th><s:text name="CU92_THEAD2" /></th>
							<th><s:text name="CU92_THEAD3" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listPagosAutorizados" var="pagoAutorizado">
							<tr>
								<td>${pagoAutorizado[0]}</td>
								<td>${pagoAutorizado[1]}</td>
								<td>${pagoAutorizado[2]}</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="col-md-12 text-right">
	<a
		href="${pageContext.request.contextPath}/citas/gestionar-citas-dentales/new"
		class="btn btn-primary"><s:text
			name="Agendar Cita" /></a>
			<a
		href="${pageContext.request.contextPath}/pagos/consultar-servicios"
		class="btn btn-primary"><s:text
			name="Regresar" /></a>
</div>
	</html>
</jsp:root>