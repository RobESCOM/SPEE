<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/pagos/gestionar-pagos/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="ttbVisualizar" value="%{getText('tooltipVisualizar')}" />
<s:set var="varIconoVisualizar" value="'attach_file'" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Gestionar Pagos" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblPagosEnviados" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Nombre" /></th>
							<th><s:text name="Clave" /></th>
							<th><s:text name="Fecha" /></th>
							<th><s:text name="Concepto" /></th>
							<th><s:text name="Cantidad" /></th>
							<th><s:text name="Comprobante" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listPagos" var="pagAutorizadoBiblioteca">
							<s:set var="alumno"
								value="%{pagoBs.obtenerAlumno(#pagAutorizadoBiblioteca)}"></s:set>
							<tr>
								<td>${alumno}</td>
								<td>${alumno.clave }</td>
								<td><s:date name="%{#pagAutorizadoBiblioteca.fechaEnvio}"
										format="yyyy-MM-dd" /></td>
								<td>${pagAutorizadoBiblioteca.catalogoServicio.descripcion}</td>
								<td>$  ${pagAutorizadoBiblioteca.catalogoServicio.precio}</td>
								<td>
									<a
										href="${pageContext.request.contextPath}/pagos/gestionar-pagos/show?idPago=${pagAutorizadoBiblioteca.id}&amp;idSel=${pagAutorizadoBiblioteca.idUsuario}"
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