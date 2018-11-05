package mx.ipn.escom.spee.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import javax.persistence.Transient;

public class Archivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2778629277334743838L;

	@Transient
	private File fileUpload;

	@Transient
	private String fileUploadFileName;

	@Transient
	private String fileUploadContentType;

	private FileInputStream fileInputStream;

	/**
	 * Especifica si el archivo se encuentra en sessión
	 */
	@Transient
	private Boolean inSession;

	/**
	 * Constructor sin parámetros
	 */
	public Archivo() {
		super();
		this.inSession = false;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	/**
	 * @return the inSession
	 */
	public Boolean getInSession() {
		return inSession;
	}

	/**
	 * @param inSession the inSession to set
	 */
	public void setInSession(Boolean inSession) {
		this.inSession = inSession;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	/**
	 * @param fileInputStream the fileInputStream to set
	 */
	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
}
