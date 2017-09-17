package br.com.viperfish.mpbmamaepagabarato.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by ddark on 16/09/17.
 */

public class ConexaoFirebase {

    private static DatabaseReference databaseReference;

    private static FirebaseAuth autenticacao;

    public static DatabaseReference getConexao() {

        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return databaseReference;
    }

    public static FirebaseAuth getFireBaseAutenticacao() {

        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }

        return autenticacao;
    }

    public static DatabaseReference getNoAnucio(){
       return getConexao().child("anuncios");
    }

    public static boolean isUsuarioLogado() {
        return  autenticacao.getCurrentUser() != null;
    }
}
