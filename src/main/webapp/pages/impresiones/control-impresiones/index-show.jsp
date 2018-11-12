<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/impresiones/control-impresiones/js/index-show.js"></script>
	]]>
</jsp:text>

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Historial de impresiones" />
		</h1>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<label class="col-xs-12 col-sm-4 col-md-4 control-label"
		for=""> <s:text name="Impresiones disponibles" />
		</label> 
		<s:set var="impresiones" value="impresiones" />
		<label class="col-xs-12 col-sm-5 col-md-5" for=""> 
			<s:text	name="Impresiones">${impresiones}</s:text>
		</label>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblImpresiones" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Fecha" /></th>
							<th><s:text name="Impresiones realizadas" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listFhImpresiones" var="fhImpresion">
						<s:set var="dateFormat" value="%{impresionesBs.dateForm(#fhImpresion)}" />
							<tr>
								<td>${dateFormat}</td>
								<td>${fhImpresion.num_impresion}</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-12">
		<s:if test="usuarioSel.perfilActivo.id == 5">
		<a class="btn btn-default-spee"
			href="${pageContext.request.contextPath}/impresiones/control-impresiones">Regresar</a>
		</s:if>
	</div>
</div>

	</html>
</jsp:root>