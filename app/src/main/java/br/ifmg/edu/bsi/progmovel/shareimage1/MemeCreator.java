package br.ifmg.edu.bsi.progmovel.shareimage1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.DisplayMetrics;

/**
 * Cria um meme com um texto e uma imagem de fundo.
 *
 * Você pode controlar o texto, a cor do texto e a imagem de fundo.
 */
public class MemeCreator {
    private Texto textoHeader;
    private Texto textoFooter;
    private Bitmap fundo;
    private DisplayMetrics displayMetrics;
    private Bitmap meme;
    private boolean dirty; // se true, significa que o meme precisa ser recriado.

    public MemeCreator(String valueHeader, String valueFooter, Bitmap fundo, DisplayMetrics displayMetrics) {
        this.textoFooter = new Texto(valueFooter, Color.WHITE, 64.f);
        this.textoHeader = new Texto(valueHeader, Color.WHITE, 64.f);
        this.fundo = fundo;
        this.displayMetrics = displayMetrics;
        this.meme = criarImagem();
        this.dirty = false;
    }

    public Texto getTextoHeader() {
        return textoHeader;
    }

    public Texto getTextoFooter() {
        return textoFooter;
    }

    public String getTextoFooterValue() {
        return textoFooter.getValue();
    }

    public void setTextoFooter(Texto texto) {
        this.textoFooter = texto;
        dirty = true;
    }

    public int getCorTextoFooter() {
        return this.textoFooter.getColor();
    }

    public void setCorTextoFooter(int corTexto) {
        this.textoFooter.setColor(corTexto);
        dirty = true;
    }

    public String getTextoHeaderValue() {
        return this.textoHeader.getValue();
    }

    public void setTextoHeader(Texto texto) {
        this.textoHeader = texto;
        dirty = true;
    }

    public int getCorTextoHeader() {
        return this.textoHeader.getColor();
    }

    public void setCorTextoHeader(int corTexto) {
        this.textoFooter.setColor(corTexto);
        dirty = true;
    }

    public Bitmap getFundo() {
        return fundo;
    }

    public void setFundo(Bitmap fundo) {
        this.fundo = fundo;
        dirty = true;
    }

    public void rotacionarFundo(float graus) {
        Matrix matrix = new Matrix();
        matrix.postRotate(graus);
        fundo = Bitmap.createBitmap(fundo, 0, 0, fundo.getWidth(), fundo.getHeight(), matrix, true);
        dirty = true;
    }

    public Bitmap getImagem() {
        if (dirty) {
            meme = criarImagem();
            dirty = false;
        }
        return meme;
    }

    public void updateFontSize(float size) {
        if (size > 0) {
            this.textoHeader.setSize(size);
            this.textoFooter.setSize(size);

            this.meme = criarImagem();
            dirty = false;
        }
    }

    protected Bitmap criarImagem() {
        float heightFactor = (float) fundo.getHeight() / fundo.getWidth();
        int width = displayMetrics.widthPixels;
        int height = (int) (width * heightFactor);
        // nao deixa a imagem ocupar mais que 60% da altura da tela.
        if (height > displayMetrics.heightPixels * 0.6) {
            height = (int) (displayMetrics.heightPixels * 0.6);
            width = (int) (height * (1 / heightFactor));
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Bitmap scaledFundo = Bitmap.createScaledBitmap(fundo, width, height, true);
        canvas.drawBitmap(scaledFundo, 0, 0, new Paint());

        Paint paintHeader = new Paint();

        paintHeader.setColor(this.textoHeader.getColor());
        paintHeader.setAntiAlias(true);
        paintHeader.setTextSize(this.textoHeader.getSize());
        paintHeader.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        paintHeader.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(this.textoHeader.getValue(), (width / 2.f), (height * 0.15f), paintHeader);

        Paint paintFooter = new Paint();

        paintFooter.setColor(this.textoFooter.getColor());
        paintFooter.setAntiAlias(true);
        paintFooter.setTextSize(this.textoFooter.getSize());
        paintFooter.setTypeface(Typeface.create("sans-serif-condensed", Typeface.BOLD));
        paintFooter.setTextAlign(Paint.Align.CENTER);
        // desenhar texto em cima

        // desenhar texto embaixo
        canvas.drawText(textoFooter.getValue(), (width / 2.f), (height * 0.9f), paintFooter);
        return bitmap;
    }
}
