package br.edu.fasa.blog.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import br.edu.fasa.blog.models.Postagem;
import br.edu.fasa.blog.R;
import br.edu.fasa.blog.models.Postagems;
import br.edu.fasa.blog.network.api.ApiService;
import br.edu.fasa.blog.network.api.RetrofitBuilder;
import br.edu.fasa.blog.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesPostagemActivity extends AppCompatActivity {

    private Postagem postagem;
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

    @BindView(R.id.detalhes_postagem_imageView_imagem1)
    ImageView mImageView1;
    @BindView(R.id.detalhes_postagem_imageView_imagem2)
    ImageView mImageView2;
    
    private static int mId = 0;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_postagem);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        postagem = (Postagem) intent.getSerializableExtra("Postagems");

        mId = postagem.getId();
        mTitulo.setText(postagem.getTitulo());
        mAutor.setText(postagem.getAutor());
        mConteudo.setText(postagem.getConteudo());

        String url1 =  postagem.getImage_1()!= null ? postagem.getImage_1() :"http://blogfasa.herokuapp.com/adm/img/sem.gif";
        String url2 = postagem.getImage_2()!= null ? postagem.getImage_2() :"http://blogfasa.herokuapp.com/adm/img/sem.gif";
        mImage1.setText(url1);
        mImage2.setText(url2);
        Picasso.get().load(url1).into(mImageView1 );
        Picasso.get().load(url2).into(mImageView2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detalhes_postagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Toast.makeText(this, "Esta Postagem será Deletada!", Toast.LENGTH_LONG).show();
                deletePostagem();
                return true;

            case R.id.action_update:
                Toast.makeText(this, "Esta Postagem será Atualizada!", Toast.LENGTH_LONG).show();
                updatePostagem();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void updatePostagem() {
        apiService = RetrofitBuilder.getRetrofit().create(ApiService.class);
        Call<Postagems> callUpdate = apiService.putPostagem(
            mId,
            mTitulo.getText().toString(),
            mConteudo.getText().toString(),
            mAutor.getText().toString(),
            mImage1.getText().toString(),
            mImage2.getText().toString()
        );
        callUpdate.enqueue(new Callback<Postagems>() {
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

    private void deletePostagem(){
        apiService = RetrofitBuilder.getRetrofit().create(ApiService.class);
        Call<Postagems> callDelete = apiService.deletePostagem(mId);
        callDelete.enqueue(new Callback<Postagems>() {
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
