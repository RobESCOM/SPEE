<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/pagos/gestionar-autorizacion-pagos/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="ttbVisualizar" value="%{getText('tooltipVisualizar')}" />
<s:set var="ttbAdjuntar" value="%{getText('tooltipAdjuntar')}" />
<s:set var="varIconoVisualizar" value="'&#xE8F4;'" />
<s:set var="varIconoAdjuntar" value="'&#xE5D8;'" />

<s:set var="varTheme" value="%{getText('mx.edu.spee.defaulTheme')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Autorizar Pagos" />
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
				<table id="tblPagosPorAutorizar" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Estado" /></th>
							<th><s:text name="Nombre" /></th>
							<th><s:text name="CURP" /></th>
							<th><s:text name="Fecha" /></th>
							<th><s:text name="Concepto" /></th>
							<th><s:text name="Cantidad" /></th>
							<th><s:text name="Acciones" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listArchivoPagosRevision"
							var="pagoPorAutorizar">
							<s:set var="alumno"
								value="%{pagoBs.obtenerAlumno(#pagoPorAutorizar)}"></s:set>
							<tr>
								<td><s:if
										test="%{#pagoPorAutorizar.idEstadoPago eq @mx.ipn.escom.spee.pagos.mapeo.EstadoPago$EstadoPagoEnum@AUTORIZADO.getIdEstatus()}">
										<i class="material-icons  md-24 md-eld-green ">&#xE061;</i>
									</s:if> <s:elseif
										test="%{#pagoPorAutorizar.idEstadoPago eq @mx.ipn.escom.spee.pagos.mapeo.EstadoPago$EstadoPagoEnum@REVISION.getIdEstatus()}">
										<i class="material-icons  md-24 md-sem-yellow ">&#xE061;</i>
									</s:elseif> <s:elseif
										test="%{#pagoPorAutorizar.idEstadoPago eq @mx.ipn.escom.spee.pagos.mapeo.EstadoPago$EstadoPagoEnum@RECHAZADO.getIdEstatus()}">
										<i class="material-icons  md-24 md-sem-rojo ">&#xE061;</i>
									</s:elseif></td>

								<td>${alumno.nombre}</td>
								<td>${alumno.clave }</td>
								<td><s:date name="%{#pagoPorAutorizar.fechaEnvio}"
										format="yyyy-MM-dd" /></td>
								<td>${pagoPorAutorizar.catalogoServicio.descripcion}</td>
								<td>$ ${pagoPorAutorizar.catalogoServicio.precio} MXN</td>
								<td><s:if
										test="%{pagoBs.findSigaAsociado(#pagoPorAutorizar.id) == false}">
										<a
											href="${pageContext.request.contextPath}/pagos/gestionar-autorizacion-pagos/${pagoPorAutorizar.id}"
											title="Visualizar Comprobante"> <i
											class="material-icons md-24">${varIconoVisualizar}</i>
										</a>
									</s:if> <s:else>
										<a href="#" title="Pago gestionado"> <i
											class="material-icons"> done </i>
										</a>
									</s:else></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="row">
	<div>
		<div class="text-right col-md-12">
			<a class="btn btn-primary" onclick="corteCaja()"><s:text
					name="Corte de Caja" /></a>
		</div>
	</div>
</div>

<sj:dialog id="corteCajaId" modal="true" title="Corte de Caja"
	autoOpen="false" openTopics="showDlgCorteCaja"
	closeTopics="closeDlgCorte" resizable="false" draggable="false">
	<s:url var="urlAction" action="gestionar-corte-caja/"
		includeContext="true" />
	<s:hidden id="hdnUrlAction" value="%{#urlAction}" />

	<s:form id="frmCorteCaja" action="" method="post" theme="simple"
		cssClass="form-horizontal">
		<div class="row">
			<div class="col-md-12">
				<s:text name="Esta apunto de realizar el corte de caja" />
			</div>
		</div>

		<div class="row">
			<div class="text-right col-md-12">
				<s:submit value="Confirmar" class="btn btn-primary" />
				<a id="btnDialogCancelar" class="btn btn-primary"
					onclick="closeDlgCorte()"> <s:text name="Cancelar" /></a>
			</div>
		</div>
	</s:form>
</sj:dialog>

<div class="text-left">
	<div class="col-md-12">
		<label class="control-label"><i
			class="material-icons  md-24 md-sem-yellow ">&#xE061;</i></label> <label><s:text
				name="Pagos Por Autorizar"></s:text></label>
	</div>
	<div class="col-md-12">
		<label class="control-label"><i
			class="material-icons  md-24 md-sem-rojo ">&#xE061;</i></label> <label><s:text
				name="Pagos Rechazados"></s:text></label>
	</div>
</div>
	</html>
</jsp:root>