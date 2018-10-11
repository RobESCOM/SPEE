<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/area/gestionar-responsable-area/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="listResponsables"
	value="{{'Angélica Pérez Beltrán','Celex','celexescom@gmail.com','5541698475'},{'Fanny Sosa Adán','Biblioteca','bibliotecaescom@gmail.com','Sin teléfono'}}" />

<s:set var="ttbVisualizar" value="%{getText('Visualizar')}" />
<s:set var="ttbEditar" value="%{getText('Editar')}" />
<s:set var="ttbBaja" value="%{getText('Dar de baja')}" />
<s:set var="ttbReactivar" value="%{getText('Reactivar')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Gestionar Responsables de Areas" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblResponsables" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Nombre" /></th>
							<th><s:text name="Area responsable" /></th>
							<th><s:text name="Correo electronico" /></th>
							<th><s:text name="Numero de contacto" /></th>
							<th><s:text name="Acciones" /></th>

						</tr>
					</thead>
					<tbody>
						<s:iterator value="listResponsables" var="lsResponsable">
							<tr>
								<td>${lsResponsable[0]}</td>
								<td>${lsResponsable[1]}</td>
								<td>${lsResponsable[2]}</td>
								<td>${lsResponsable[3]}</td>
								<td><a
									href="${pageContext.request.contextPath}/area/gestionar-responsable-area/1/edit"
									title="${ttbEditar}"> <i
										class="material-icons md-24 md-eld">edit</i>
								</a> <a
									href="${pageContext.request.contextPath}/area/gestionar-responsable-area/1"
									title="${ttbVisualizar}"> <i
										class="material-icons md-24 md-eld">remove_red_eye </i>
								</a> <a title="${ttbBaja}"> <i
										class="material-icons md-24 md-eld">cancel</i>
								</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="outter-section form-horiontal">
	<div class="col-xs-12 col-md-12 col-md-12 text-center">
		<a class="btn btn-default btn-default-spee"
			href="${pageContext.request.contextPath}/area/gestionar-responsable-area/new">Registrar</a>
	</div>
</div>

	</html>
</jsp:root>