package com.example.luan.crudsqlite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Luan on 28/05/2017.
 */

public class InsereDado extends Activity {
    public Button botao;
    public EditText titulo;
    public EditText autor;
    public EditText editora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            /**
             * Metodo para capturar o conteúdo dos EditTexts e converter para String.
             * @param v
             */
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());

                titulo = (EditText)findViewById(R.id.etTitulo);
                autor = (EditText)findViewById((R.id.etAutor));
                editora = (EditText)findViewById(R.id.etEditora);

                String tituloString = titulo.getText().toString();
                String autorString = autor.getText().toString();
                String editoraString = editora.getText().toString();

                String resultado;

                resultado = crud.insereDado(tituloString,autorString,editoraString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
