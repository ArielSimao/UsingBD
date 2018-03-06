package br.edu.fatecmm.pojo;

public class BeanEditoras {
	private int idEditora;
	private String nmEditora;
	
	public BeanEditoras() {
		idEditora = -1;
		nmEditora = "";
	}

	public int getIdEditora() {
		return idEditora;
	}

	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}

	public String getNmEditora() {
		return nmEditora;
	}

	public void setNmEditora(String nmEditora) {
		this.nmEditora = nmEditora;
	}
}
