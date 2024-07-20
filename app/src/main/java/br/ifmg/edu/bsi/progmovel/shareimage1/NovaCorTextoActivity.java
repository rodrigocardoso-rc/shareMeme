package br.ifmg.edu.bsi.progmovel.shareimage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NovaCorTextoActivity extends AppCompatActivity {

    public static String EXTRA_TEXTO_HEADER = "br.ifmg.edu.bsi.progmovel.shareimage1.textoHeader";
    public static String EXTRA_TEXTO_FOOTER = "br.ifmg.edu.bsi.progmovel.shareimage1.textoFooter";
    public static String EXTRA_POSICAO = "br.ifmg.edu.bsi.progmovel.shareimage1.posicao";

    private EditText etCor;

    private Texto textHeader;
    private Texto textFooter;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_cor_texto);

        etCor = findViewById(R.id.etCor);

        Intent intent = getIntent();

        this.textHeader = (Texto) intent.getSerializableExtra(EXTRA_TEXTO_HEADER);
        this.textFooter = (Texto) intent.getSerializableExtra(EXTRA_TEXTO_FOOTER);
        this.position = intent.getStringExtra(EXTRA_POSICAO);

        if (this.position != null && this.position.equals("Header")) {
            etCor.setText(converterCor(this.textHeader.getColor()));
        } else {
            etCor.setText(converterCor(this.textFooter.getColor()));
        }
    }

    public String converterCor(int cor) {
        switch (cor) {
            case Color.BLACK: return "BLACK";
            case Color.WHITE: return "WHITE";
            case Color.BLUE: return "BLUE";
            case Color.GREEN: return "GREEN";
            case Color.RED: return "RED";
            case Color.YELLOW: return "YELLOW";
        }
        return null;
    }

    public void enviarNovaCor(View v) {
        Intent intent = new Intent();

        if (this.position != null && this.position.equals("Header")) {
            this.textHeader.setColor(Color.parseColor(etCor.getText().toString().toUpperCase()));
        } else {
            this.textFooter.setColor(Color.parseColor(etCor.getText().toString().toUpperCase()));
        }

        intent.putExtra(EXTRA_TEXTO_HEADER, textHeader);
        intent.putExtra(EXTRA_TEXTO_FOOTER, textFooter);

        setResult(RESULT_OK, intent);
        finish();
    }
}