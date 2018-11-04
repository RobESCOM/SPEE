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
<s:set var="ttbBaja" value="%{getText('Agregar Impresiones')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Control impresiones"/>
		</h1>
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
						<s:iterator value="cuentaImpresiones" var="impresiones">
							<s:set var="usuario" value="%{impresionesBs.obtenerUsuario(#impresiones)}"></s:set>
							<tr>
								<td>${usuario.nombre}</td>
								<td>${usuario.curp}</td>
								<td>${impresiones.nu_impresiones}</td>
								<td>
									<a
										href="${pageContext.request.contextPath}/impresiones/control-impresiones/new?idSel=${impresiones.id.idCuenta}"
										title="${ttbReducirImpresiones}"> <i
											class="material-icons md-24 md-eld">scanner</i>
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

<div class="row">
	<div class="col-md-12">
		<a class="btn btn-primary" onclick="myClickDialog()"><s:text name="Registrar Pago" /></a>
	</div>
</div>

<sj:dialog id="registroPago" modal="true" title="Registrar Pago" autoOpen="false"
		openTopics="showRegistro" closeTopics="closeRegistro" resizable="false"
		draggable="false" cssClass="hidden" >
	<s:url var="urlAction"
		action="control-impresiones!agregarImpresiones"
		includeContext="true" />
	<s:hidden id="hdnUrlAction" value="%{#urlAction}" />
	<s:form id="frmRegistroImpresiones" action="" theme="simple"
		method="post">
			<div class="row">
				<div class="col-md-12">
					<s:text name="Usuario:"></s:text>
					<s:select id="tipoUsuario" class="form-control"
						list="#{'1':'Alumno', '2':'Empleado', '3':'Externo'}"
						name="tipoUsuario">
					</s:select>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<s:text name="clave"></s:text>
					<s:textfield name="clave"></s:textfield>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<s:text name="Número Impresiones"></s:text>
					<s:textfield type="number" class="form-control" name="numeroImpresion" min="15" value="15" step="15"></s:textfield>
				</div>
			</div>
		<!-- Botones de si y no -->
		<div class="row">
			<div class="text-right col-md-12">
				<s:submit cssClass="btn btn-default btn-default-eld"
					value="Agregar" />
				<a onclick="closeBajaDlg()"
					class="btn btn-default btn-default-eld"><s:text
						name="Cancelar" /></a>
			</div>
		</div>
		
	</s:form>
	</sj:dialog>

	</html>
</jsp:root>