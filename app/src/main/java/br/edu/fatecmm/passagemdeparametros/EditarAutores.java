package br.edu.fatecmm.passagemdeparametros;

import br.edu.fatecmm.banco.DAOAutores;
import br.edu.fatecmm.pojo.BeanAutores;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditarAutores extends Activity {

	private EditText txtEditarAutoresNome;
	private EditText txtEditarAutoresBio;
	private Button btnEditarAutoresGravar;
	private Button btnEditarAutoresVoltar;
	private Button btnEditarAutoresExcluir;
	private boolean novo;
	private BeanAutores bean;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_autores);
		novo = true;
		
		// amarrando componentes
		txtEditarAutoresNome = (EditText) findViewById(R.id.txtEditarAutoresNome);
		txtEditarAutoresBio = (EditText) findViewById(R.id.txtEditarAutoresBio);
		btnEditarAutoresExcluir = (Button) findViewById(R.id.btnEditarAutoresExcluir);
		btnEditarAutoresGravar = (Button) findViewById(R.id.btnEditarAutoresGravar);
		btnEditarAutoresVoltar = (Button) findViewById(R.id.btnEditarAutoresVoltar);
		
		btnEditarAutoresExcluir.setEnabled(false);
		
		Intent it = getIntent();
		Bundle parametros = it.getExtras();
		if(parametros != null)
		{
			novo = false;
			btnEditarAutoresExcluir.setEnabled(true);
			DAOAutores dao = new DAOAutores(getBaseContext());
			bean = new BeanAutores();
			bean.setIdAutor(parametros.getInt("idAutor"));
			
			dao.open();
			bean = dao.selectUm(bean);
			dao.close();
			
			txtEditarAutoresNome.setText(bean.getNmAutor());
			txtEditarAutoresBio.setText(bean.getBioAutor());
		}
		
		btnEditarAutoresVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getBaseContext(), TodosAutores.class);
				startActivity(it);
				finish();
			}
		});
		
		btnEditarAutoresExcluir.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DAOAutores dao = new DAOAutores(getBaseContext());
				dao.open();
				dao.delete(bean);
				dao.close();
				
				Intent it = new Intent(getBaseContext(), TodosAutores.class);
				startActivity(it);
				finish();
			}
		});
		
		btnEditarAutoresGravar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(novo)
					inserir();
				else
					alterar();
				Intent it = new Intent(getBaseContext(), TodosAutores.class);
				startActivity(it);
				finish();
			}
		});
	}
	
	private void inserir()
	{
		BeanAutores bean = new BeanAutores();
		DAOAutores dao = new DAOAutores(getBaseContext());
		
		bean.setNmAutor(txtEditarAutoresNome.getText().toString());
		bean.setBioAutor(txtEditarAutoresBio.getText().toString());
		
		dao.open();
		dao.insert(bean);
		dao.close();
	}
	
	private void alterar()
	{
		DAOAutores dao = new DAOAutores(getBaseContext());
		bean.setNmAutor(txtEditarAutoresNome.getText().toString());
		bean.setBioAutor(txtEditarAutoresBio.getText().toString());
		
		dao.open();
		dao.update(bean);
		dao.close();
	}

	@Override
	public void onBackPressed()
	{
		Intent it = new Intent(getBaseContext(), TodosAutores.class);
		startActivity(it);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_autores, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
