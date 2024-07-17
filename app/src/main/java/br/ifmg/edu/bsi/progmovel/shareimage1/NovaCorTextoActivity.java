package br.ifmg.edu.bsi.progmovel.shareimage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovaCorTextoActivity extends AppCompatActivity {

    public static String EXTRA_TEXTO_ATUAL = "br.ifmg.edu.bsi.progmovel.shareimage1.texto_atual";
    public static String EXTRA_COR_ATUAL = "br.ifmg.edu.bsi.progmovel.shareimage1.cor_atual";
    public static String EXTRA_NOVO_TEXTO = "br.ifmg.edu.bsi.progmovel.shareimage1.novo_texto";
    public static String EXTRA_NOVA_COR = "br.ifmg.edu.bsi.progmovel.shareimage1.nova_cor";

    private EditText etCor;
    private EditText etTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_texto);

        etTexto = findViewById(R.id.etTexto);
        etCor = findViewById(R.id.etCor);

        Intent intent = getIntent();
        String textoAtual = intent.getStringExtra(EXTRA_TEXTO_ATUAL);
        String corAtual = intent.getStringExtra(EXTRA_COR_ATUAL);

        etTexto.setText(textoAtual);
        etCor.setText(corAtual);
    }

    public void enviarNovaCor(View v) {
        Intent intent = new Intent();

        String novoTexto = etTexto.getText().toString();
        String novaCor = etCor.getText().toString();

        intent.putExtra(EXTRA_NOVO_TEXTO, novoTexto);
        intent.putExtra(EXTRA_NOVA_COR, novaCor);

        setResult(RESULT_OK, intent);
        finish();
    }
}