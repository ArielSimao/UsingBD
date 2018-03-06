package br.edu.fatecmm.pojo;

public class BeanAutores {
	private int idAutor;
	private String nmAutor;
	private String bioAutor;
	
	public BeanAutores() {
		idAutor = -1;
		nmAutor = "";
		bioAutor = "";
	}

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	public String getNmAutor() {
		return nmAutor;
	}

	public void setNmAutor(String nmAutor) {
		this.nmAutor = nmAutor;
	}

	public String getBioAutor() {
		return bioAutor;
	}

	public void setBioAutor(String bioAutor) {
		this.bioAutor = bioAutor;
	}

	@Override
	public String toString() {
		return nmAutor;
	}
	
}
