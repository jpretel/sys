package treetabletest;

public class Lista {
	private int id;
	private String descripcion;
	private int idpadre;
	
	public Lista(int id, String descripcion, int idpadre) {
		this.id = id;
		this.descripcion = descripcion;
		this.idpadre = idpadre;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdpadre() {
		return idpadre;
	}

	public void setIdpadre(int idpadre) {
		this.idpadre = idpadre;
	}
}
