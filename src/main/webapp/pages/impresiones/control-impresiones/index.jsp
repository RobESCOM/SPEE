<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script
	 type="text/javascript" 
	 src="${pageContext.request.contextPath}/pages/impresiones/control-impresiones/js/index.js">
	</script>
	]]>
</jsp:text>

<s:set var="ttbReducirImpresiones" value="%{getText('Reducir Impresión')}" />
<s:set var="ttbHistorial" value="%{getText('Historial de impresiones')}" />
<s:set var="ttbBaja" value="%{getText('Agregar Impresiones')}" />

<s:set var="varTheme" value="%{getText('mx.edu.spee.defaulTheme')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Control impresiones"/>
		</h1>
	</div>
</div>

<div class="row form-group">
	<div class="col-md-12">
		<s:actionerror theme="%{varTheme}" />
		<s:actionmessage theme="%{varTheme}" />
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblImpresiones" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Nombre" /></th>
							<th><s:text name="Clave" /></th>
							<th><s:text name="Impresiones disponibles" /></th>
							<th><s:text name="Acciones"/></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="cuentaImpresiones" var="impresionesRealizadas">
							<s:set var="usuario" value="%{impresionesBs.obtenerUsuario(#impresionesRealizadas)}"></s:set>
							<s:set var="boleta" value="%{impresionesBs.obtenerBoleta(#usuario)}" />
							<tr>
								<td>${usuario.nombre}</td>
								<s:if test="#boleta == 'null'">
									<td>${usuario.clave}</td>
								</s:if>
								<s:else>
									<td>${boleta}</td>
								</s:else>
								
								<td>${impresionesRealizadas.nu_impresiones}</td>
								<td>
									<a
										href="${pageContext.request.contextPath}/impresiones/control-impresiones/new?idSel=${impresionesRealizadas.id.idCuenta}"
										title="${ttbReducirImpresiones}"> <i
											class="material-icons md-24 md-eld">scanner</i>
									</a>
									<a
										href="${pageContext.request.contextPath}/impresiones/control-impresiones/${impresionesRealizadas.id.idCuenta}"
										title="${ttbHistorial}"> <i
											class="material-icons md-24 md-eld">chrome_reader_mode</i>
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