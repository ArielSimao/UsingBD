package br.edu.fatecmm.banco;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecmm.pojo.BeanEditoras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAOEditoras {
	private SQLiteDatabase banco;
	private GerenciaBanco gerenciaBanco;
	
	// Colunas
	public static final String ID_EDITORA = "id_editora";
	public static final String NM_EDITORA = "nm_editora";
	
	public static final String[] todasAsColunas = 
	{
		ID_EDITORA, NM_EDITORA
	};
	
	// Tabela
	public static final String TABELA_EDITORAS = "editoras";
	
	public DAOEditoras(Context contexto)
	{
		gerenciaBanco = new GerenciaBanco(contexto);
	}
	
	public void open() throws SQLException
	{
		banco = gerenciaBanco.getWritableDatabase();
	}
	
	public void close()
	{
		gerenciaBanco.close();
	}
	
	public void insert(BeanEditoras item)
	{
		ContentValues valores = new ContentValues();
		valores.put(NM_EDITORA, item.getNmEditora());
		
		banco.insert(TABELA_EDITORAS, null, valores);
	}
	
	public void update(BeanEditoras item)
	{
		ContentValues valores = new ContentValues();
		
		valores.put(ID_EDITORA, item.getIdEditora());
		valores.put(NM_EDITORA, item.getNmEditora());
		
		banco.update(TABELA_EDITORAS, valores, ID_EDITORA + " = " + item.getIdEditora(), null);
	}
	
	public void delete(BeanEditoras item)
	{
		int id = item.getIdEditora();
		banco.delete(TABELA_EDITORAS, ID_EDITORA + " = " + id, null);
	}
	
	public List<BeanEditoras> selectTodos()
	{
		List<BeanEditoras> itens = new ArrayList<BeanEditoras>();
		Cursor cursor = banco.query(TABELA_EDITORAS, 
			todasAsColunas, null, null, null, null, NM_EDITORA);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			BeanEditoras item = cursorToItem(cursor);
			itens.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return itens;
	}
	
	public BeanEditoras selectUm(BeanEditoras item)
	{
		Cursor cursor = banco.query(
				TABELA_EDITORAS, 
				todasAsColunas, 
				ID_EDITORA + " = " + 
				item.getIdEditora(), null, null, null, null);
		cursor.moveToFirst();
		return cursorToItem(cursor);
	}
	
	private BeanEditoras cursorToItem(Cursor cursor)
	{
		BeanEditoras item = new BeanEditoras();
		
		item.setIdEditora(cursor.getInt(0));
		item.setNmEditora(cursor.getString(1));
		
		return item;
	}

    public static class BeanLivros {
        private int idLivro;
        private String nmLivro;
        private int idAutor;
        private int idEditora;
        private String anoLivro;
        private String dsResenha;

        public BeanLivros()
        {
            idLivro = -1;
            nmLivro = "";
            idAutor = -1;
            idEditora = -1;
            anoLivro = "";
            dsResenha = "";
        }

        public int getIdLivro() {
            return idLivro;
        }

        public void setIdLivro(int idLivro) {
            this.idLivro = idLivro;
        }

        public String getNmLivro() {
            return nmLivro;
        }

        public void setNmLivro(String nmLivro) {
            this.nmLivro = nmLivro;
        }

        public int getIdAutor() {
            return idAutor;
        }

        public void setIdAutor(int idAutor) {
            this.idAutor = idAutor;
        }

        public int getIdEditora() {
            return idEditora;
        }

        public void setIdEditora(int idEditora) {
            this.idEditora = idEditora;
        }

        public String getAnoLivro() {
            return anoLivro;
        }

        public void setAnoLivro(String anoLivro) {
            this.anoLivro = anoLivro;
        }

        public String getDsResenha() {
            return dsResenha;
        }

        public void setDsResenha(String dsResenha) {
            this.dsResenha = dsResenha;
        }
    }
}
