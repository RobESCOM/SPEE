<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/servicio/gestionar-servicio/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="ttbVisualizar" value="%{getText('Visualizar')}" />
<s:set var="ttbEditar" value="%{getText('Editar')}" />
<s:set var="ttbBaja" value="%{getText('Dar de baja')}" />
<s:set var="ttbReactivar" value="%{getText('Reactivar')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Gestionar Servicios" />
		</h1>
	</div>
</div>

<!-- Action messages -->
<div class="form-group">
	<div class="col-md-8 col-md-offset-2">
		<s:actionmessage cssClass="alert alert-success" />
		<s:actionerror cssClass="alert alert-danger" />
	</div>
</div>

<div class="text-left">
	<div class="col-md-12"></div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblServicios" class="table table-striped">
					<thead>
						<tr>
							<th data-priority="1"><s:text name="Clave" /></th>
							<th data-priority="2"><s:text name="Tipo de servicio" /></th>
							<th data-priority="2"><s:text name="Descripcion" /></th>
							<th data-priority="1"><s:text name="Costo" /></th>
							<th data-priority="1"><s:text name="Area a la que pertenece" /></th>
							<th data-priority="2"><s:text name="Acciones" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listServicios" var="lstServ">
							<tr>
								<td><s:property value="%{#lstServ.clave}" /></td>
								<td><s:property value="%{#lstServ.tipoServicio}" /></td>
								<td><s:property value="%{#lstServ.descripcion}" /></td>
								<td><s:property value="%{'$ ' + #lstServ.precio + ' MXN'}" /></td>
								<td><s:property value="%{#lstServ.area}" /></td>
								<td><a
									href="${pageContext.request.contextPath}/servicio/gestionar-servicio/${lstServ.id}/edit"
									title="${ttbEditar}"> <i
										class="material-icons md-24 md-eld">edit</i>
								</a> <a
									href="${pageContext.request.contextPath}/servicio/gestionar-servicio/${lstServ.id}"
									title="${ttbVisualizar}"> <i
										class="material-icons md-24 md-eld">remove_red_eye </i>
								</a> <s:if test="%{#lstServ.estatus == true}">
										<a onclick="myclickDialogBaja('${lstServ.id}')"
											title="${ttbBaja}"> <i
											class="material-icons md-24 md-eld">cancel</i>
										</a>
									</s:if> <s:else>
										<a onclick="myclickDialogReact('${lstServ.id}')"
											title="${ttbReactivar}"> <i
											class="material-icons md-24 md-eld">check_circle</i>
										</a>
									</s:else></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Diálogo de confirmación para dar de baja a un servicio -->
	<sj:dialog id="bajaServicio" modal="true"
		title="Dar de baja un servicio" autoOpen="false"
		openTopics="showDlgBaja" closeTopics="closeDlgBaja" resizable="false"
		draggable="false" cssClass="hidden">
		<s:url var="urlAction"
			action="gestionar-servicio!bajaServicio?idSel=%{lstServ.id}"
			includeContext="true" />
		<s:hidden id="hdnUrlAction" value="%{#urlAction}" />
		<s:form id="frmBajaServicio" action="" theme="simple" method="post">
			<div class="row">
				<div class="col-md-12">
					<s:text
						name="Esta seguro que desea dar de baja el servicio? Este servicio quedara deshabilitado del sistema.">
					</s:text>
				</div>
			</div>
			<!-- Botones de si y no -->
			<div class="row">
				<div class="text-right col-md-12">
					<s:submit cssClass="btn btn-default btn-default-eld" value="Si" />
					<a onclick="closeBajaDlgBaja()"
						class="btn btn-default btn-default-eld"><s:text name="No" /></a>
				</div>
			</div>
		</s:form>
	</sj:dialog>
	
	<!-- Diálogo de confirmación para reactivar un servicio -->
	<sj:dialog id="reactivaServicio" modal="true"
		title="Reactivar un servicio" autoOpen="false"
		openTopics="showDlgReact" closeTopics="closeDlgReact" resizable="false"
		draggable="false" cssClass="hidden">
		<s:url var="urlActionReact"
			action="gestionar-servicio!reactivarServicio?idSel=%{lstServ.id}"
			includeContext="true" />
		<s:hidden id="hdnUrlActionReact" value="%{#urlActionReact}" />
		<s:form id="frmRectivaServicio" action="" theme="simple" method="post">
			<div class="row">
				<div class="col-md-12">
					<s:text
						name="Esta seguro que desea reactivar el servicio? Este servicio se habiitara de nuevo en el sistema.">
					</s:text>
				</div>
			</div>
			<!-- Botones de si y no -->
			<div class="row">
				<div class="text-right col-md-12">
					<s:submit cssClass="btn btn-default btn-default-eld" value="Si" />
					<a onclick="closeBajaDlgReact()"
						class="btn btn-default btn-default-eld"><s:text name="No" /></a>
				</div>
			</div>
		</s:form>
	</sj:dialog>
	
	

</div>

<div class="outter-section form-horiontal">
	<div class="col-xs-12 col-md-12 col-md-12 text-center">
		<a class="btn btn-default btn-default-spee"
			href="${pageContext.request.contextPath}/servicio/gestionar-servicio/new">Registrar</a>
	</div>
</div>
<div class="text-left">
	<div class="col-md-12"></div>
</div>

	</html>
</jsp:root>