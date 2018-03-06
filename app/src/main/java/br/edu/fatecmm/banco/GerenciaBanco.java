package br.edu.fatecmm.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GerenciaBanco extends SQLiteOpenHelper {
	// dados do banco
	private static final String NOME_BANCO = "db_aula";
	private static final int VERSAO_BANCO = 1;
	
	// tabelas
	private static final String TB_AUTORES = 
			"CREATE TABLE autores ( " + 
			"id_autor  INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"nm_autor  TEXT, " +
			"bio_autor TEXT " +
			");";
	private static final String TB_EDITORAS = 
			"CREATE TABLE editoras ( " +
			"id_editora  INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"nm_editora  TEXT " +
			");";
	private static final String TB_LIVROS = 
			"CREATE TABLE livros ( " +
			"id_livro    INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"nm_livro    TEXT, " +
			"id_autor    INTEGER REFERENCES autores ( id_autor ) ON DELETE CASCADE, " +
			"id_editora  INTEGER REFERENCES editoras ( id_editora ) ON DELETE CASCADE, " +
			"ano_livro   TEXT, " +
			"ds_resenha  TEXT " +
			");";
	private static final String IDX_LIVROS_AUTORES = 
			"CREATE INDEX idx_livros_autores ON livros ( " + 
			"id_autor ASC " +
			");";
	private static final String IDX_LIVROS_EDITORAS = 
			"CREATE INDEX idx_livros_editoras ON livros ( " + 
			"id_editora ASC " +
			");";
	
	public GerenciaBanco(Context context) {
		super(context, NOME_BANCO, null, VERSAO_BANCO);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// criação do banco
		db.execSQL(TB_AUTORES);
		db.execSQL(TB_EDITORAS);
		db.execSQL(TB_LIVROS);
		db.execSQL(IDX_LIVROS_AUTORES);
		db.execSQL(IDX_LIVROS_EDITORAS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Setup inicial do banco. Ignorando upgrade
	}

}
