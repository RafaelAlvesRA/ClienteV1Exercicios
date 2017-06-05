package app.projeto.clientev1exercicios.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ClienteDb {
    ClienteDbHelper dbHelper;

    public static final String CLIENTE = "cliente";

    public ClienteDb(Context context){
        dbHelper = new ClienteDbHelper(context);
    }

    public void insereClientes(ArrayList<Cliente> clientes){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(ClienteContract.ClienteEntry.TABLE_NAME,null,null);

        for(Cliente cliente:clientes) {
            ContentValues values = new ContentValues();

            values.put(ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME, cliente.getNome());
            values.put(ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_FONE, cliente.getFone());
            values.put(ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_EMAIL, cliente.getEmail());


            db.insert(ClienteContract.ClienteEntry.TABLE_NAME, null, values);
        }
    }



    public ArrayList<Cliente> selecionaClientes(){
        ArrayList<Cliente> lista = new ArrayList<Cliente>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] colunas = {ClienteContract.ClienteEntry._ID,
                ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME ,
                ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_FONE ,
                ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_EMAIL };

        String ordem = ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME;

        Cursor c = db.query(ClienteContract.ClienteEntry.TABLE_NAME, colunas, null, null, ordem, null, null );

        while(c.moveToNext()){
            int id = c.getInt(c.getColumnIndex(ClienteContract.ClienteEntry._ID));
            String nome = c.getString(c.getColumnIndex(ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME));
            String fone = c.getString(c.getColumnIndex(ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_FONE));
            String email = c.getString(c.getColumnIndex(ClienteContract.ClienteEntry.COLUMN_NAME_CLIENTE_NOME));

            Cliente cliente = new Cliente(id,nome,fone, email);


            lista.add( cliente );
        }

        c.close();

        return lista;
    }


}
