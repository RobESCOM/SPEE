<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/area/gestionar-area/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="listAreas"
	value="{{'Celex','Cursos extracurriculares de lenguas extranjeras'},{'Biblioteca','Biblioteca de la ESCOM'}}" />

<s:set var="ttbVisualizar" value="%{getText('Visualizar')}" />
<s:set var="ttbEditar" value="%{getText('Editar')}" />
<s:set var="ttbBaja" value="%{getText('Dar de baja')}" />
<s:set var="ttbReactivar" value="%{getText('Reactivar')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Gestionar Areas" />
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

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblAreas" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Nombre" /></th>
							<th><s:text name="Descripcion" /></th>
							<th><s:text name="Acciones" /></th>

						</tr>
					</thead>
					<tbody>
						<s:iterator value="listAreas" var="area">
							<tr>
								<td><s:property value="%{#area.nombreArea}" /></td>
								<td><s:property value="%{#area.descripcion}" /></td>
								<td><s:if test="%{#area.estatus == true}">
										<a
											href="${pageContext.request.contextPath}/area/gestionar-area/${area.id}/edit"
											title="${ttbEditar}"> <i
											class="material-icons md-24 md-eld">edit</i>
										</a>
										<a
											href="${pageContext.request.contextPath}/area/gestionar-area/${area.id}"
											title="${ttbVisualizar}"> <i
											class="material-icons md-24 md-eld">remove_red_eye </i>
										</a>
										<a onclick="myclickDialogBajaArea('${area.id}')"
											title="${ttbBaja}"> <i
											class="material-icons md-24 md-eld">cancel</i>
										</a>
									</s:if> <s:else>
										<a
											href="${pageContext.request.contextPath}/area/gestionar-area/${area.id}"
											title="${ttbVisualizar}"> <i
											class="material-icons md-24 md-eld">remove_red_eye </i>
										</a>
										<a onclick="myclickDialogReactivaArea('${area.id}')"
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

	<!-- Diálogo de confirmación para dar de baja un área -->
	<sj:dialog id="dlgBajaArea" modal="true" title="Dar de baja el área"
		autoOpen="false" openTopics="showDlgBaja" closeTopics="closeDlgBaja"
		resizable="false" draggable="false" cssClass="hidden">
		<s:url var="urlAction"
			action="gestionar-area!bajaArea?idSel=%{area.id}"
			includeContext="true" />
		<s:hidden id="hdnUrlAction" value="%{#urlAction}" />
		<s:form id="frmBajaArea" action="" theme="simple" method="post">
			<div class="row">
				<div class="col-md-12">
					<s:text
						name="Esta seguro que desea dar de baja el area? Esta area junto con el responsable asociado quedaran deshabilitados del sistema.">
					</s:text>
				</div>
			</div>
			<!-- Botones de si y no -->
			<div class="row">
				<div class="text-right col-md-12">
					<s:submit cssClass="btn btn-default btn-default-eld" value="Si" />
					<a onclick="closeBajaDlg()" class="btn btn-default btn-default-eld"><s:text
							name="No" /></a>
				</div>
			</div>
		</s:form>
	</sj:dialog>

	<!-- Diálogo de confirmación para reactivar un área -->
	<sj:dialog id="reactivaArea" modal="true" title="Reactivar un servicio"
		autoOpen="false" openTopics="showDlgReact" closeTopics="closeDlgReact"
		resizable="false" draggable="false" cssClass="hidden">
		<s:url var="urlActionReact"
			action="gestionar-area!reactivarArea?idSel=%{area.id}"
			includeContext="true" />
		<s:hidden id="hdnUrlActionReact" value="%{#urlActionReact}" />
		<s:form id="frmRectivaArea" action="" theme="simple" method="post">
			<div class="row">
				<div class="col-md-12">
					<s:text
						name="Esta seguro que desea reactivar el area? Esta area se habiitara de nuevo en el sistema. Tendra que asignar a un nuevo responsable.">
					</s:text>
				</div>
			</div>
			<!-- Botones de si y no -->
			<div class="row">
				<div class="text-right col-md-12">
					<s:submit cssClass="btn btn-default btn-default-eld" value="Si" />
					<a onclick="closeReactivaDlg()"
						class="btn btn-default btn-default-eld"><s:text name="No" /></a>
				</div>
			</div>
		</s:form>
	</sj:dialog>
</div>
	</html>
</jsp:root>