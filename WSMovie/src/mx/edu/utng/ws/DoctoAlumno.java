package mx.edu.utng.ws;

public class DoctoAlumno {
	private int id;
	private String dateTest;
	private String dateDevolution;
	private String note;
	private String activo;
	private String document;
	public DoctoAlumno(int id, String dateReception, String dateDevolution, String note, String activo, String document) {
		super();
		this.id = id;
		this.dateTest = dateReception;
		this.dateDevolution = dateDevolution;
		this.note = note;
		this.activo = activo;
		this.document = document;
	}
	public DoctoAlumno() {
		this(0,"","","","","");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
public String getDateTest() {
	return dateTest;
}
public void setDateTest(String dateTest) {
	this.dateTest = dateTest;
}
	public String getDateDevolution() {
		return dateDevolution;
	}
	public void setDateDevolution(String dateDevolution) {
		this.dateDevolution = dateDevolution;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	@Override
	public String toString() {
		return "DoctoAlumno [id=" + id + ", dateReception=" + dateTest + ", dateDevolution=" + dateDevolution
				+ ", note=" + note + ", activo=" + activo + ", document=" + document + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
