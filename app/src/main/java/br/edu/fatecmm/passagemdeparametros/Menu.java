package br.edu.fatecmm.passagemdeparametros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends Activity {

	private String strUsuario;
	private String strSenha;
	private Button btnLivros;
	private Button btnAutores;
	private Button btnEditoras;
	private Button btnMenuVoltar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
		strUsuario = "";
		strSenha = "";
		Intent it = getIntent();
		if(it != null)
		{
			Bundle parametros = it.getExtras();
			if(parametros != null)
			{
				strUsuario = parametros.getString("strUsuario");
				strSenha = parametros.getString("strSenha");
			}
		}
		
		if(!"Ariel".equalsIgnoreCase(strUsuario) || !"abner".equals(strSenha))
		{
			Toast.makeText(getBaseContext(), "Usuário ou senha inválidos", Toast.LENGTH_LONG).show();
			finish();
		}
		
		btnAutores = (Button) findViewById(R.id.btnAutores);
		btnEditoras = (Button) findViewById(R.id.btnEditoras);
		btnLivros = (Button) findViewById(R.id.btnLivros);
		btnMenuVoltar = (Button) findViewById(R.id.btnMenuVoltar);
		
		btnAutores.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getBaseContext(), TodosAutores.class);
				startActivity(it);
			}
		});
		
		btnMenuVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
