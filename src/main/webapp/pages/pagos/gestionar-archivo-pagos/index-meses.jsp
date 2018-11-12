<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/pagos/gestionar-archivo-pagos/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="ttbVisualizar" value="%{getText('tooltipVisualizar')}" />
<s:set var="varIconoVisualizar" value="'&#xE8F4;'" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Pagos mensuales" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblArchivosPagoAnual" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Fecha" /></th>
							<th><s:text name="Cantidad" /></th>
							<th><s:text name="Total"/></th>
							<th><s:text name="Acciones" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator var="pagoAnio" value="listArchivosPago">
							<s:set var="mes" value="listAnio"/>
							<s:set var="cantidad" value="%{pagoBs.obtenerTotalMes(#pagoAnio)}"/>
							<s:if test="#cantidad[1] != 0">
								<tr>
									<td>${cantidad[0]}</td>
									<td>${cantidad[1]}</td>
									<td>$ ${cantidad[2]}</td>
									<td>
										<a
											href="${pageContext.request.contextPath}/pagos/gestionar-archivo-pagos/show?listAnio=${mes}&amp;listMes=${cantidad[0]}"
											title="Ver pagos del mes"> <i
												class="material-icons md-24 md-eld">redo</i>
										</a>
									</td>
								</tr>
							</s:if>
						</s:iterator>
							
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
	</html>
</jsp:root>