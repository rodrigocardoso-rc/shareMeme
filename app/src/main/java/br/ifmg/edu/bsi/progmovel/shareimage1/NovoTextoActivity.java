package br.ifmg.edu.bsi.progmovel.shareimage1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;

public class NovoTextoActivity extends AppCompatActivity {

    public static String EXTRA_TEXTO_HEADER = "br.ifmg.edu.bsi.progmovel.shareimage1.textoHeader";
    public static String EXTRA_TEXTO_FOOTER = "br.ifmg.edu.bsi.progmovel.shareimage1.textoFooter";

    public static String EXTRA_POSICAO = "br.ifmg.edu.bsi.progmovel.shareimage1.posicao";

    private EditText etTexto;
    private EditText etCor;

    private Texto textHeader;
    private Texto textFooter;
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_texto);

        etTexto = findViewById(R.id.etTexto);
        etCor = findViewById(R.id.etCor);

        Intent intent = getIntent();

        this.textHeader = (Texto) intent.getSerializableExtra(EXTRA_TEXTO_HEADER);
        this.textFooter = (Texto) intent.getSerializableExtra(EXTRA_TEXTO_FOOTER);
        this.position = intent.getStringExtra(EXTRA_POSICAO);

        if (this.position != null && this.position.equals("Header")) {
            etTexto.setText(this.textHeader.getValue());
            etCor.setText(this.textHeader.getColor());
        } else {
            etTexto.setText(this.textFooter.getValue());
            etCor.setText(converterCor(this.textFooter.getColor()));
        }
    }

    private String converterCor(int cor) {
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

    public void enviarNovoTexto(View v) {
        Intent intent = new Intent();

        if (this.position != null && this.position.equals("Header")) {
            this.textHeader.setValue(etTexto.getText().toString());
        } else {
            this.textFooter.setValue(etTexto.getText().toString());
        }

        intent.putExtra(EXTRA_TEXTO_HEADER, textHeader);
        intent.putExtra(EXTRA_TEXTO_FOOTER, textFooter);

        setResult(RESULT_OK, intent);
        finish();
    }
}