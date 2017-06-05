package app.projeto.clientev1exercicios.model;

import android.provider.BaseColumns;


public class ClienteContract {
    public ClienteContract(){}

    public static abstract class ClienteEntry implements BaseColumns {
        public static final String TABLE_NAME = "cliente";
        public static final String COLUMN_NAME_CLIENTE_NOME = "nome";
        public static final String COLUMN_NAME_CLIENTE_FONE = "fone";
        public static final String COLUMN_NAME_CLIENTE_EMAIL = "email";
    }


}
