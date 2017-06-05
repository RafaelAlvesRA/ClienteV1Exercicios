package app.projeto.clientev1exercicios.view;

import android.content.Context;

import java.util.ArrayList;

import app.projeto.clientev1exercicios.model.Cliente;

public interface MainView {

    void mensagem(String mensagem);

    void listarClientes(ArrayList<Cliente> lista);

    Context getContext();
}
