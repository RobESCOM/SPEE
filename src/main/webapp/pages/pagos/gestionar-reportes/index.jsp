<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/pagos/gestionar-actividad-cajero/js/index.js"></script>
	]]>
</jsp:text>

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Gestionar Reportes" />
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


<fieldset class="form form-horizontal form-medium">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblCajero" class="table table-striped">
					<thead>
						<tr>
							<th data-priority="1"><s:text name="Cantidad" /></th>
							<th data-priority="1"><s:text name="Fecha" /></th>
							<th data-priority="2"><s:text name="Acciones" /></th>

						</tr>
					</thead>
					<tbody>
						<s:iterator value="listCorteCaja" var="corte">
							<tr>
								<td>${corte.total}MXN</td>
								<td>${corte.fechaCorte}</td>
								<td><a
									href="${pageContext.request.contextPath}/pagos/gestionar-reportes!imprimirReporte?idSel=${corte.id}"
									target="_blank" title="Visualizar Reporte"> <i
										class="material-icons md-24">picture_as_pdf </i>
								</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</fieldset>
<div class="row">
	<div class="col-md-12"></div>
</div>
	</html>
</jsp:root>