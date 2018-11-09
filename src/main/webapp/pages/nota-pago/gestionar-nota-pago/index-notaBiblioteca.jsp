<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0"
	xmlns:s="/struts-tags" xmlns:sj="/struts-jquery-tags">
	<jsp:directive.page language="java"
		contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<jsp:text>
	<![CDATA[                 
	<script 
		type="text/javascript" 
		src="${pageContext.request.contextPath}/pages/nota-pago/gestionar-nota-pago/js/index-notaBiblioteca.js">
	</script>
	]]>
</jsp:text>

<div class="row title">
	<div class="col-md-12">
		<h1 class="title">
			<s:text name="Nota de pago" />
		</h1>
	</div>
</div>

<div class="form-section form-horizontal">
	<div class="form-group">
		<div class="row">
			
			<s:form	action="%{#pageContext.request.contextPath}/nota-pago/index-notaBiblioteca"
				method="post" theme="simple">
				
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
						for=""> <s:text name="Num de boleta" />
					</label>
					<div class="col-xs-12 col-sm-4 col-md-4">
						<s:textfield cssClass="form-control campo"
							cssClassError="input-error" name="model.boleta" id="txBoleta" />
						<s:fielderror fieldName="model.boleta" cssClass="error"
							theme="%{#varTheme}" />
					</div>
				</div>
				
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
						for=""> <s:text name="Num de libros" />
					</label>
					<div class="col-xs-12 col-sm-2 col-md-2">
						<s:textfield id="numLibros" type="number" class="form-control" min="1" max="3" step="1"/>
					</div>	
				</div>
				
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
						for=""> <s:text name="Num de adquisicion" />
					</label>
					<div class="col-xs-12 col-sm-2 col-md-2">
						<s:textfield cssClass="form-control campo"
							cssClassError="input-error" name="model.idNumAdquisicion" id="txAdquisicion" />
						<s:fielderror fieldName="model.idNumAdquisicion" cssClass="error"
							theme="%{#varTheme}" />
					</div>	
				</div>
				
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
						for=""> <s:text name="Num de dias" />
					</label>
					<div class="col-xs-12 col-sm-2 col-md-2">
						<s:textfield id="numDias" type="number" class="form-control" name="numDias" value="1" min="1" max="360" step="1"/>
					</div>	
				</div>
				
				<div class="form-group">
					<label
						class="col-xs-12 col-sm-4 col-md-4 control-label label-obligatorio"
						for=""> <s:text name="Importe total" />
					</label>
					<div class="col-xs-12 col-sm-2 col-md-2">
						<div class="input-group">
				            <span class="input-group-addon">$</span>
				            <s:textfield id="concepto" name="concepto" type="text" class="form-control" ></s:textfield>
				            
				        </div>
					</div>
				</div>
				
				<div class="outter-section form-medium text-right">
					<div class="col-xs-12 col-md-12 col-md-12 text-right">
						<s:submit cssClass="btn btn-default-spee" value="Aceptar" />
						<a class="btn btn-default-spee"
							href="${pageContext.request.contextPath}/control-acceso/gestionar-bienvenida">Cancelar</a>
					</div>
				</div>
				
			</s:form>
			
		</div>
	</div>
</div>

</html>
</jsp:root>