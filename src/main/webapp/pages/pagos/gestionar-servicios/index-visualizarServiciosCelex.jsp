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
	value="{{'Idioma inglés Alumno', '$480 MXN', 'Pago inscripción'},
	 {'Reinscripción', '$580 MXN', 'Pago reinscripción idioma inglés'}}" />

<s:set var="ttbBancomer" value="%{getText('tooltipBancomer')}" />
<s:set var="ttbAdjuntar" value="%{getText('tooltipAdjuntar')}" />

<s:set var="varIconoBancomer" value="'&#xE8A1;'" />
<s:set var="varIconoAdjuntar" value="'&#xE5D8;'" />


<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="CU91_TITLE" />
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
							<th><s:text name="CU91_THEAD1" /></th>
							<th><s:text name="CU91_THEAD2" /></th>
							<th><s:text name="CU91_THEAD3" /></th>
							<th><s:text name="CU91_THEAD4" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listPagosAutorizados" var="pagoAutorizado">
							<tr>
								<td>${pagoAutorizado[0]}</td>
								<td>${pagoAutorizado[1]}</td>
								<td>${pagoAutorizado[2]}</td>
								<td><a
									href="${pageContext.request.contextPath}/pagos/gestionar-pagos/new"
									title="${ttbAdjuntar}"> <i
										class="material-icons md-24 md-eld">${varIconoAdjuntar}</i>
								</a><a href="#" title="${ttbBancomer}"> <i
										class="material-icons md-24 md-eld">${varIconoBancomer}</i>
								</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="col-md-12 text-right">
	<a href="${pageContext.request.contextPath}/pagos/consultar-servicios"
		class="btn btn-primary"><s:text
			name="Regresar" /></a>
</div>
	</html>
</jsp:root>