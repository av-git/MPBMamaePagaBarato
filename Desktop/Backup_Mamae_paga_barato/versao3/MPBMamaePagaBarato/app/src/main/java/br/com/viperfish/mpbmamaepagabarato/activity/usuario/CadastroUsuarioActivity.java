package br.com.viperfish.mpbmamaepagabarato.activity.usuario;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.com.viperfish.mpbmamaepagabarato.R;
import br.com.viperfish.mpbmamaepagabarato.config.ConexaoFirebase;
import br.com.viperfish.mpbmamaepagabarato.modelo.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    TextInputLayout nome;
    TextInputLayout email;
    TextInputLayout senha;
    private Usuario usuario;
    FirebaseAuth autenticacao;

    private static String TAG = "CadastroUsuarioActivity"; // LogCat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (TextInputLayout) findViewById(R.id.edit_cadastro_usuario_nome);
        email = (TextInputLayout) findViewById(R.id.edit_cadastro_usuario_email);
        senha = (TextInputLayout) findViewById(R.id.edit_cadastro_usuario_senha);

    }

    public void salvarCadastroUsuario(View v) {
        Log.i(TAG, "Salvando o usuario...");

        usuario = new Usuario();

        if(todosCamposObrigatoriosPreenchidos()) {

            usuario.setNome(nome.getEditText().getText().toString());
            usuario.setEmail(email.getEditText().getText().toString());
            usuario.setSenha(senha.getEditText().getText().toString());

            cadastrarUsuario();
        }
    }

    private boolean todosCamposObrigatoriosPreenchidos() {

        boolean camposValidos = true;

        if (nome.getEditText().getText().toString().isEmpty()) {
            nome.setError("Campo Obrigatório");
            camposValidos = false;
        }

        if (email.getEditText().getText().toString().isEmpty()) {
            email.setError("Campo Obrigatório");
            camposValidos = false;
        }

        if (senha.getEditText().getText().toString().isEmpty()) {
            senha.setError("Campo Obrigatório");
            camposValidos = false;
        }

        return camposValidos;
    }

    private void cadastrarUsuario() {
        autenticacao = ConexaoFirebase.getFireBaseAutenticacao();

        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).

                addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(CadastroUsuarioActivity.this, "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();

                            //obtem o id gerado pelo firebase. Util para termos nos unicos na nossa base dados
                            usuario.setId(task.getResult().getUser().getUid());
                            usuario.salvar();
                            autenticacao.signOut();
                            finish(); // finaliza a activity

                        } else {

                            String msgErro = "";

                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                msgErro = "Senha fraca. Informe uma senha com Mínimo de 6 caracteres.";
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                msgErro = "E-mail inválido.";
                            } catch (FirebaseAuthUserCollisionException e) {
                                msgErro = "Esse E-mail já esta registrado para esse aplicativo. Tente Logar.";
                            } catch (Exception e) {
                                if (e.getMessage().contains("WEAK_PASSWORD")) {
                                    msgErro = "Senha fraca. Informe uma senha com Mínimo de 6 caracteres.";
                                } else {
                                    msgErro = " Ao cadastrar Usuario. Verifique se todos os dados estão corretos e se o acesso a Internet está disponível. Tente Novamente";
                                }
                            }

                            Toast.makeText(CadastroUsuarioActivity.this, "Erro: " +msgErro, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
