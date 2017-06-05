package app.projeto.clientev1exercicios;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.projeto.clientev1exercicios.model.Cliente;
import app.projeto.clientev1exercicios.presenter.DetalheActivityPresenter;
import app.projeto.clientev1exercicios.presenter.DetalheClientePresenter;
import app.projeto.clientev1exercicios.view.DetalheView;


public class DetalheClienteActivity extends Activity implements DetalheView {
    TextView textViewNome, textViewFone, textViewEmail;
    ImageView imagemCliente;
    Cliente cliente;
    DetalheClientePresenter detalhePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_cliente);
        textViewNome = (TextView) findViewById(R.id.txt_cliente_nome);
        textViewFone = (TextView) findViewById(R.id.txt_cliente_fone);
        textViewEmail = (TextView) findViewById(R.id.txt_cliente_email);
        imagemCliente = (ImageView) findViewById(R.id.cliente_image_view);
        Intent intent = getIntent();
        cliente = (Cliente)intent.getSerializableExtra(ListarClientesActivity.CLIENTE);
        textViewNome.setText(cliente.getNome());
        textViewEmail.setText(cliente.getEmail());
        textViewFone.setText(cliente.getFone());

        detalhePresenter = new DetalheActivityPresenter(this);
        detalhePresenter.carregaImagem(cliente);
    }

    @Override
    public void setImagem(Bitmap imagem) {
        imagemCliente.setImageBitmap(imagem);
    }

    @Override
    public void mensagem(String mensagem) {
        Toast toast = Toast.makeText(this,mensagem, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public Context getContext() {
        return this;
    }

}

