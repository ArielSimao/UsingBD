package br.edu.fatecmm.passagemdeparametros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	private EditText txtUsuario;
	private EditText txtSenha;
	private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtUsuario = (EditText) findViewById(R.id.txtUsuario);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        
        btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it = new Intent(getBaseContext(), Menu.class);
				
				Bundle parametros = new Bundle();
				
				parametros.putString("strUsuario", txtUsuario.getText().toString());
				parametros.putString("strSenha", txtSenha.getText().toString());
				
				it.putExtras(parametros);
				
		        startActivity(it);
			}
		});
    }

}
