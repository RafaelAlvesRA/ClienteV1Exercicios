package app.projeto.clientev1exercicios.presenter;

import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.io.IOException;

import app.projeto.clientev1exercicios.MainActivity;
import app.projeto.clientev1exercicios.model.Cliente;
import app.projeto.clientev1exercicios.model.ClienteRequester;
import app.projeto.clientev1exercicios.view.DetalheView;


public class DetalheActivityPresenter implements DetalheClientePresenter {
    ClienteRequester clienteRequester;
    DetalheView detalheView;

    public DetalheActivityPresenter(DetalheView detalheView){
        this.clienteRequester =  new ClienteRequester();
        this.detalheView = detalheView;
    }

    @Override
    public void carregaImagem(Cliente cliente) {
        if(clienteRequester.isConnected(detalheView.getContext())) {
            new DownloadImage().execute(MainActivity.SERVIDOR +
                    MainActivity.APLICACAO + "/img/" + cliente.getImagem() + ".jpg");
        } else {
            detalheView.mensagem("Rede indisponivel, nao foi possivel carregar a imagem");
        }
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                return clienteRequester.getImage(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public void onPostExecute(Bitmap result){
            detalheView.setImagem(result);
        }
    }

}
