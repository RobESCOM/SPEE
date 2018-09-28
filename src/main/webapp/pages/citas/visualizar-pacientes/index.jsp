<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/citas/visualizar-pacientes/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="listPacientes" value="{{'Paquito', 'JELD921029', '20136352589'}}" />

<s:set var="ttbVerHistorialClinico" value="%{getText('Ver Historial Clínico')}" />

<s:set var="varIconoVisualizar" value="'&#xE8F4;'" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Gestionar pacientes" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-sm-12 col-md-12">
				<table id="tblPagosPorAutorizar" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="CU29_THEAD1" /></th>
							<th><s:text name="CU29_THEAD2" /></th>
							<th><s:text name="CU29_THEAD3" /></th>
							<th><s:text name="CU29_THEAD4" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listPacientes" var="historialClinico">
							<tr>
								<td>${historialClinico[0]}</td>
								<td>${historialClinico[1]}</td>
								<td>${historialClinico[2]}</td>
								<td><a
									href="${pageContext.request.contextPath}/citas/visualizar-historial-clinico"
									title="${ttbVerHistorialClinico}"> <i
										class="material-icons md-24 md-eld">history</i>
								</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<div class="text-right">
	<a
		href="${pageContext.request.contextPath}/bienvenida"
		class="btn btn-default btn-default-eld"><s:text
			name="%{getText('mx.com.eld.boton.regresar')}" /></a>
</div>
	</html>
</jsp:root>