<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/pagos/gestionar-archivo-pagos/js/index-show.js"></script>
	]]>
</jsp:text>

<s:set var="ttbVisualizar" value="%{getText('Visualizar')}" />

<s:set var="varIconoImprimir" value="'&#xE8AD;'" />
<s:set var="varIconoVisualizar" value="'&#xE415;'" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Lista de pagos" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblArchivoPagoMes" class="table table-striped">
					<s:set var="admin" value="permisos" />
					<thead>
						<tr>
							<th><s:text name="Folio" /></th>
							<th><s:text name="Fecha" /></th>
							<th><s:text name="Concepto" /></th>
							<th><s:text name="Monto" /></th>
							<s:if test="admin">
								<th><s:text name="Area servicio"/></th>
							</s:if>
							<th><s:text name="Acciones" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="pagosDate" var="pagoMes">
						<s:set var="date" value="%{pagoBs.dateForm(#pagoMes)}" />
						<s:set var="money" value="%{pagoBs.moneyForm(#pagoMes)}" />
							<tr>
								<td>${pagoMes.folioOperacion}</td>
								<td>${date}</td>
								<td>${pagoMes.catalogoServicio.descripcion }</td>
								<td>$ ${money}</td>
								<s:if test="admin">
									<td>${pagoMes.catalogoServicio.area.nombreArea}</td>
								</s:if>
								<td>
									<a
										href="${pageContext.request.contextPath}/pagos/gestionar-pagos/show?idPago=${pagoMes.id}&amp;idSel=${pagoMes.idUsuario}"
										title="${ttbverSIGA}"><i
											class="material-icons md-24 md-eld">find_in_page</i>
									</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
	</html>
</jsp:root>