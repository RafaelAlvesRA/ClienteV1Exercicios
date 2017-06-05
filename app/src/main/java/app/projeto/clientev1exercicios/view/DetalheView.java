package app.projeto.clientev1exercicios.view;

import android.content.Context;
import android.graphics.Bitmap;


public interface DetalheView {
    void setImagem(Bitmap imagem);

    void mensagem(String mensagem);

    Context getContext();
}
