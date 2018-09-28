<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/citas/visualizar-historial-clinico/js/index-show.js"></script>
	]]>
</jsp:text>

<s:set var="listHistorialClinico" value="{{'Agendada', '04/07/2018'},{'Agendada', '04/07/2018'}}" />
<s:set var="ttbVisualizar" value="%{getText('tooltipVisualizar')}" />
<s:set var="varIconoVisualizar" value="'&#xE8F4;'" />
<s:set var="ttbVerCuestionario" value="%{getText('Ver Cuestionario')}" />
<s:set var="ttbVisualizarConsulta" value="%{getText('Visualizar Consulta')}" />
<s:set var="ttbVerNotaPago" value="%{getText('Ver Nota de Pago')}" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Visualizar Historial Clinico" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			
			<div class="form-group">
				<label
					class="col-sm-2 col-md-2 control-label"
					for=""> <s:text name="Nombre" />
				</label>
				<div class="col-sm-3 col-md-3">
					<s:textfield cssClass="form-control campo" disabled="true" id="txUsuario" />
				</div>
			</div>
			
			<div class="form-group">
				<label
					class="col-sm-2 col-md-2 control-label"
					for=""> <s:text name="Edad" />
				</label>
				<div class="col-sm-1 col-md-1">
					<s:textfield cssClass="form-control campo" disabled="true" id="txUsuario" />
				</div>
			</div>
			
			<div class="form-group">
				<label
					class="col-sm-2 col-md-2 control-label"
					for=""> <s:text name="Boleta, Curp, Núm. Empleado" />
				</label>
				<div class="col-sm-3 col-md-3">
					<s:textfield cssClass="form-control campo" disabled="true" id="txUsuario" />
				</div>
			</div>
		
			<div class="col-md-12">
				<table id="tblHistorialClinico" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Estatus" /></th>
							<th><s:text name="Fecha" /></th>
							<th><s:text name="Acciones" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listHistorialClinico" var="historial">
							<tr>
								<td>${historial[0]}</td>
								<td>${historial[1]}</td>
								<td>
								<a
									href="${pageContext.request.contextPath}/consulta/visualizar-cuestionario/1"
									title="${ttbVerCuestionario}"> <i
										class="material-icons md-24 md-eld">description</i>
								</a>
								<a
									href="${pageContext.request.contextPath}/consulta/gestionar-consulta/1"
									title="${ttbVisualizarConsulta}"> <i
										class="material-icons md-24 md-eld">chrome_reader_mode</i>
								</a>
								<a
									href="${pageContext.request.contextPath}/pagos/gestionar-notas-pago/1"
									title="${ttbVerNotaPago}"> <i
										class="material-icons md-24 md-eld">pageview</i>
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
<div class="text-right">
	<a
		href="${pageContext.request.contextPath}/citas/gestionar-citas-dentales/new"
		class="btn btn-default btn-default-eld"><s:text
			name="%{getText('mx.com.eld.boton.regresar')}" /></a>
</div>
	</html>
</jsp:root>