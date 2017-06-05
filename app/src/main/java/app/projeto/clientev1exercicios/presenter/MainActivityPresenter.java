package app.projeto.clientev1exercicios.presenter;



import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;

import app.projeto.clientev1exercicios.model.Cliente;
import app.projeto.clientev1exercicios.model.ClienteDb;
import app.projeto.clientev1exercicios.model.ClienteRequester;
import app.projeto.clientev1exercicios.view.MainView;

import static app.projeto.clientev1exercicios.MainActivity.APLICACAO;
import static app.projeto.clientev1exercicios.MainActivity.RECURSO;
import static app.projeto.clientev1exercicios.MainActivity.SERVIDOR;


public class MainActivityPresenter implements MainPresenter {

    private MainView mainView;
    private ClienteRequester clienteRequester;
    ArrayList<Cliente> lista;
    ClienteDb clienteDb;

    public MainActivityPresenter(MainView mainView, ClienteDb clienteDb){
        this.mainView = mainView;
        this.clienteRequester = new ClienteRequester();
        this.clienteDb = clienteDb;
    }

    @Override
    public void buscarClientes( final String chave) {
        if(clienteRequester.isConnected(mainView.getContext())){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lista = clienteRequester.get(SERVIDOR+APLICACAO+RECURSO, chave);
                        clienteDb.insereClientes(lista);
                        mainView.listarClientes(lista);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } else {
            mainView.mensagem("Rede indisponível. Carregando dados do cache ");
            new CarregaClienteBD().execute("");
        }
    }

    private class CarregaClienteBD extends AsyncTask<String, Void, ArrayList<Cliente>> {

        @Override
        protected ArrayList<Cliente> doInBackground(String... strings) {
            return clienteDb.selecionaClientes();
        }

        public void onPostExecute(ArrayList<Cliente> lista){
            mainView.listarClientes(lista);
        }
    }

}
