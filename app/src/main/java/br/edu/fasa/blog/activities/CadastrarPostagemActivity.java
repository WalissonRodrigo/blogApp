package br.edu.fasa.blog.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.fasa.blog.R;
import br.edu.fasa.blog.models.Postagems;
import br.edu.fasa.blog.network.api.ApiService;
import br.edu.fasa.blog.network.api.RetrofitBuilder;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarPostagemActivity extends AppCompatActivity {

    private ApiService apiService;
    @BindView(R.id.detalhes_postagem_editText_titulo)
    TextView mTitulo;
    @BindView(R.id.detalhes_postagem_editText_descricao)
    TextView mConteudo;
    @BindView(R.id.detalhes_postagem_editText_autor)
    TextView mAutor;
    @BindView(R.id.detalhes_postagem_editText_image1)
    TextView mImage1;
    @BindView(R.id.detalhes_postagem_editText_image2)
    TextView mImage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_postagem);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastrar_postagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Toast.makeText(this, "Esta Postagem será Cadastrada!", Toast.LENGTH_LONG).show();
                postPostagem();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void postPostagem() {
        apiService = RetrofitBuilder.getRetrofit().create(ApiService.class);
        Call<Postagems> callPost = apiService.postPostagem(
                mTitulo.getText().toString(),
                mConteudo.getText().toString(),
                mAutor.getText().toString(),
                mImage1.getText().toString(),
                mImage2.getText().toString()
        );
        callPost.enqueue(new Callback<Postagems>() {
            @Override
            public void onResponse(Call<Postagems> call, Response<Postagems> response) {
                //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<Postagems> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
