<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script type="text/javascript" src="${pageContext.request.contextPath}/pages/pagos/gestionar-archivo-pagos/js/index.js"></script>
	]]>
</jsp:text>

<s:set var="listArchivosPagoAnual"
	value="{{'01/02/2018','Celex'},
	 {'01/02/2018', 'Celex'},
	 {'01/02/2018', 'Servicios Dentales'}}" />

<s:set var="ttbVisualizar" value="%{getText('tooltipVisualizar')}" />
<s:set var="varIconoVisualizar" value="'&#xE8F4;'" />

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="CU37_TITLE" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<div class="col-md-12">
				<table id="tblArchivosPagoAnual" class="table table-striped">
					<thead>
						<tr>
							<th><s:text name="CU37_THEAD1" /></th>
							<th><s:text name="CU37_THEAD2" /></th>
							<th><s:text name="CU37_THEAD3" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="listArchivosPagoAnual" var="pago">
							<tr>
								<td>${pago[0]}</td>
								<td>${pago[1]}</td>
								<td><a
									href="${pageContext.request.contextPath}/pagos/gestionar-archivo-pagos/show"
									title="${ttbVisualizar}"> <i
										class="material-icons md-24 md-eld">${varIconoVisualizar}</i>
								</a></td>
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