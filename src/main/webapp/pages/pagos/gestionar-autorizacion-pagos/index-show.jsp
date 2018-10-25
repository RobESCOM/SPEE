<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	]]>
</jsp:text>

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Visualizar Comprobante de Pago" />
		</h1>
	</div>
</div>


<div class="text-left">
	<label class="col-md-6 text-left control-label"> <s:text
			name="Folio">
		</s:text>
	</label>
	<div class="col-md-6 text-left">
		<s:property value="model.folioOperacion" />
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			<object data="${pageContext.request.contextPath}/files/pago.pdf"
				type="application/pdf" width="600" height="600">
				<p></p>
			</object>

		</div>
	</div>
</div>

<div class="text-right">
	<a
		href="${pageContext.request.contextPath}/pagos/gestionar-autorizacion-pagos!autorizarPago?idSel=${idSel}"
		class="btn btn-primary"><s:text name="Autorizar" /></a> <a
		href="${pageContext.request.contextPath}/pagos/gestionar-autorizacion-pagos!rechazarPago?idSel=${idSel}"
		class="btn btn-primary"><s:text name="Rechazar" /></a><a
		href="${pageContext.request.contextPath}/pagos/gestionar-autorizacion-pagos"
		class="btn btn-primary"><s:text name="Regresar" /> </a>
</div>

<div class="text-left">
	<div class="col-md-12"></div>
</div>
	</html>
</jsp:root>