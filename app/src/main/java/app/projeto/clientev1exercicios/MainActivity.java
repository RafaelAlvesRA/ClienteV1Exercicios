package app.projeto.clientev1exercicios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import app.projeto.clientev1exercicios.model.Cliente;
import app.projeto.clientev1exercicios.model.ClienteDb;
import app.projeto.clientev1exercicios.model.ClienteRequester;
import app.projeto.clientev1exercicios.presenter.MainActivityPresenter;
import app.projeto.clientev1exercicios.presenter.MainPresenter;
import app.projeto.clientev1exercicios.view.MainView;

public class MainActivity extends Activity implements MainView {
    EditText textNome;
    ArrayList<Cliente> lista;
    ClienteRequester requester;
    Intent intent;
    String chave;

    //padrao
    //public static final String SERVIDOR = "http://10.0.2.2:8080";
    //casa
    public static final String SERVIDOR = "http://192.168.0.100:8080";
    //faculdade
    //public static final String SERVIDOR = "http://10.70.9.176:8080";
    public static final String APLICACAO = "/arqdesis_poetas";
    public static final String RECURSO = "/cliente";
    public static final String LISTA = "br.usjt.ftce.desmob.clientev1.lista";

    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textNome = (EditText)findViewById(R.id.buscar_clientes);
        mainPresenter = new MainActivityPresenter(this , new ClienteDb(this));
    }

    public void buscarCliente(View view){
        chave = textNome.getText().toString();
        mainPresenter.buscarClientes(chave);
    }

    @Override
    public void mensagem(String mensagem) {
        Toast toast = Toast.makeText(this,mensagem, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public void listarClientes(ArrayList<Cliente> lista) {
        intent = new Intent(this, ListarClientesActivity.class);
        intent.putExtra(LISTA, lista);
        startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
