<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/citas/gestionar-citas-dentista/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="listPagosAutorizados"
	value="{{'','Juan Carlos','2013660005', '04/07/2018', '15:00'}}" />

<s:set var="ttbVisualizar" value="%{getText('tooltipVisualizar')}" />
<s:set var="ttbAdjuntar" value="%{getText('tooltipAdjuntar')}" />
<s:set var="ttbVerCuestionario" value="%{getText('Ver Cuestionario')}" />
<s:set var="ttbRealizarConsulta" value="%{getText('Realizar Consulta')}" />
<s:set var="ttbVerHistorialClinico"
	value="%{getText('Visualizar Historial Clínico')}" />
<s:set var="ttbCancelarCita" value="%{getText('Cancelar Cita')}" />
<s:set var="ttbInasistencia" value="%{getText('Marcar Inasistencia')}" />
<s:set var="ttbConclusion" value="%{getText('Concluir cita')}" />

<s:set var="varIconoVisualizar" value="'&#xE8F4;'" />
<s:set var="varIconoVisualizarCuestionario" value="description" />
<s:set var="varIconoAdjuntar" value="'&#xE5D8;'" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Gestionar Citas Agendadas" />
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
				<table id="tblPagosPorAutorizar" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="Estatus" /></th>
							<th><s:text name="Fecha de la cita" /></th>
							<th><s:text name="Hora de la cita" /></th>
							<th><s:text name="Acciones" /></th>

						</tr>
					</thead>
					<tbody>
						<s:if
							test="%{txLogin == 'coordinador.dentales.matutino@gmail.com'}">
							<s:iterator value="listCitasMatutino" var="citasMatutino">
								<tr>
									<s:if test="%{#citasMatutino.idEstado == 1}">
										<td class="material-icons  md-24 md-sem-yellow">&#xE061;</td>
									</s:if>
									<s:elseif test="%{#citasMatutino.idEstado == 2}">
										<td class="material-icons  md-24 md-sem-green">&#xE061;</td>
									</s:elseif>
									<s:elseif test="%{#citasMatutino.idEstado == 3}">
										<td class="material-icons  md-24 md-sem-rojo">&#xE061;</td>
									</s:elseif>
									<s:else>
										<td class="material-icons  md-24 md-sem-orange">&#xE061;</td>
									</s:else>
									<td><s:property value="%{#citasMatutino.fecha}" /></td>
									<td><s:property value="%{#citasMatutino.horaServicio}" /></td>

									<s:if
										test="%{#citasMatutino.idEstado == 3 || #citasMatutino.idEstado == 4 || #citasMatutino.idEstado == 2}">
										<td><s:property value="'Sin acciones'" /></td>
									</s:if>
									<s:else>
										<td><a
											href="${pageContext.request.contextPath}/citas/gestionar-citas-dentista!marcarInasistencia?idSel=${citasMatutino.id}"
											title="${ttbInasistencia}"> <i
												class="material-icons md-24 md-eld">clear</i>
										</a> <a onclick="myclickDialogCancelacion('${citasMatutino.id}')"
											title="${ttbCancelarCita}"> <i
												class="material-icons md-24 md-eld">cancel</i>
										</a></td>
									</s:else>

								</tr>
							</s:iterator>
						</s:if>
						<s:else>
							<s:iterator value="listCitasVespertino" var="citasVespertino">
								<tr>
									<s:if test="%{#citasVespertino.idEstado == 1}">
										<td class="material-icons  md-24 md-sem-yellow">&#xE061;</td>
									</s:if>
									<s:elseif test="%{#citasVespertino.idEstado == 2}">
										<td class="material-icons  md-24 md-sem-green">&#xE061;</td>
									</s:elseif>
									<s:elseif test="%{#citasVespertino.idEstado == 3}">
										<td class="material-icons  md-24 md-sem-rojo">&#xE061;</td>
									</s:elseif>
									<s:else>
										<td class="material-icons  md-24 md-sem-orange">&#xE061;</td>
									</s:else>
									<td><s:property value="%{#citasVespertino.fecha}" /></td>
									<td><s:property value="%{#citasVespertino.horaServicio}" /></td>

									<s:if
										test="%{#citasVespertino.idEstado == 3 || #citasVespertino.idEstado == 4 || #citasVespertino.idEstado == 2}">
										<td><s:property value="'Sin acciones'" /></td>
									</s:if>
									<s:else>
										<td><a
											href="${pageContext.request.contextPath}/citas/gestionar-citas-dentista!marcarInasistencia?idSel=${citasVespertino.id}"
											title="${ttbInasistencia}"> <i
												class="material-icons md-24 md-eld">clear</i>
										</a> <a
											onclick="myclickDialogCancelacion('${citasVespertino.id}')"
											title="${ttbCancelarCita}"> <i
												class="material-icons md-24 md-eld">cancel</i>
										</a></td>
									</s:else>

								</tr>
							</s:iterator>
						</s:else>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<!-- Diálogo de confirmación para cancelar una cita -->
	<sj:dialog id="cancelacionServicio" modal="true" title="Cancelar cita"
		autoOpen="false" openTopics="showDlgCancelacion"
		closeTopics="closeDlgCancelacion" resizable="false" draggable="false"
		cssClass="hidden">
		<s:url var="urlAction"
			action="gestionar-citas-dentista!cancelarCita?idSel=%{citas.id}"
			includeContext="true" />
		<s:hidden id="hdnUrlAction" value="%{#urlAction}" />
		<s:form id="frmCancelacionCita" action="" theme="simple" method="post">
			<div class="row">
				<div class="col-md-12">
					<s:text
						name="Esta seguro(a) que desea cancelar la cita de su paciente?.">
					</s:text>
				</div>
			</div>
			<!-- Botones de si y no -->
			<div class="row">
				<div class="text-right col-md-12">
					<s:submit cssClass="btn btn-default btn-default-eld" value="Si" />
					<a onclick="closeDlgCancelacion()"
						class="btn btn-default btn-default-eld"><s:text name="No" /></a>
				</div>
			</div>
		</s:form>
	</sj:dialog>


</div>

<div class="text-left">
	<div class="col-md-12">
		<label class="control-label"><i
			class="material-icons  md-24 md-sem-green ">&#xE061;</i></label> <label><s:text
				name="Cita concluida"></s:text></label>
	</div>
	<div class="col-md-12">
		<label class="control-label"><i
			class="material-icons  md-24 md-sem-yellow ">&#xE061;</i></label> <label><s:text
				name="Cita pendiente"></s:text></label>
	</div>
	<div class="col-md-12">
		<label class="control-label"><i
			class="material-icons  md-24 md-sem-orange">&#xE061;</i></label> <label><s:text
				name="Inasistencia"></s:text></label>
	</div>
	<div class="col-md-12">
		<label class="control-label"><i
			class="material-icons  md-24 md-sem-rojo ">&#xE061;</i></label> <label><s:text
				name="Cita cancelada"></s:text></label>
	</div>
</div>

	</html>
</jsp:root>