package br.edu.fatecmm.banco;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatecmm.pojo.BeanAutores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DAOAutores {
	private SQLiteDatabase banco;
	private GerenciaBanco gerenciaBanco;
	
	// Colunas
	public static final String ID_AUTOR = "id_autor";
	public static final String NM_AUTOR = "nm_autor";
	public static final String BIO_AUTOR = "bio_autor";
	
	public static final String[] todasAsColunas = 
	{
		ID_AUTOR, NM_AUTOR, BIO_AUTOR
	};
	
	// Tabela
	public static final String TABELA_AUTORES = "autores";
	
	public DAOAutores(Context contexto)
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
	
	public void insert(BeanAutores item)
	{
		ContentValues valores = new ContentValues();
		valores.put(NM_AUTOR, item.getNmAutor());
		valores.put(BIO_AUTOR, item.getBioAutor());
		
		banco.insert(TABELA_AUTORES, null, valores);
	}
	
	public void update(BeanAutores item)
	{
		ContentValues valores = new ContentValues();
		
		valores.put(ID_AUTOR, item.getIdAutor());
		valores.put(NM_AUTOR, item.getNmAutor());
		valores.put(BIO_AUTOR, item.getBioAutor());
		
		banco.update(TABELA_AUTORES, valores, ID_AUTOR + " = " + item.getIdAutor(), null);
	}
	
	public void delete(BeanAutores item)
	{
		int id = item.getIdAutor();
		banco.delete(TABELA_AUTORES, ID_AUTOR + " = " + id, null);
	}
	
	public List<BeanAutores> selectTodos()
	{
		List<BeanAutores> itens = new ArrayList<BeanAutores>();
		Cursor cursor = banco.query(TABELA_AUTORES, 
			todasAsColunas, null, null, null, null, NM_AUTOR);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			BeanAutores item = cursorToItem(cursor);
			itens.add(item);
			cursor.moveToNext();
		}
		cursor.close();
		return itens;
	}
	
	public BeanAutores selectUm(BeanAutores item)
	{
		Cursor cursor = banco.query(
				TABELA_AUTORES, 
				todasAsColunas, 
				ID_AUTOR + " = " + item.getIdAutor(),
				null, null, null, null);
		cursor.moveToFirst();
		return cursorToItem(cursor);
	}
	
	private BeanAutores cursorToItem(Cursor cursor)
	{
		BeanAutores item = new BeanAutores();
		
		item.setIdAutor(cursor.getInt(0));
		item.setNmAutor(cursor.getString(1));
		item.setBioAutor(cursor.getString(2));
		
		return item;
	}
}
