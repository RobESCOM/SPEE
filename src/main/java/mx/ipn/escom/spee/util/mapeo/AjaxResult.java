package mx.ipn.escom.spee.util.mapeo;

import java.util.ArrayList;
import java.util.List;

public class AjaxResult {

	private List<Object> response;

	public AjaxResult() {
		this.response = new ArrayList<>();
	}

	public AjaxResult(List<Object> listMessages) {
		super();
	}

	public void addCampo(Object objeto) {
		response.add(objeto);
	}

	public List<Object> getResponse() {
		return response;
	}

	public void setResponse(List<Object> response) {
		this.response = response;
	}

}
