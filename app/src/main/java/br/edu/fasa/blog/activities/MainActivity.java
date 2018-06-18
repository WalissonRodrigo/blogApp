package br.edu.fasa.blog.activities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.edu.fasa.blog.models.Postagem;
import br.edu.fasa.blog.models.Postagems;
import br.edu.fasa.blog.network.api.ApiService;
import br.edu.fasa.blog.network.api.RetrofitBuilder;
import br.edu.fasa.blog.utils.Utils;
import br.edu.fasa.blog.views.adapters.PostagemsAdapter;
import br.edu.fasa.blog.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements PostagemsAdapter.PostagemClickListener {

    private String TAG = "MainActivity";
    @BindView(R.id.content_main_recycler_postagem)
    RecyclerView mRecyclerViewPostagems;
    private PostagemsAdapter mAdapter;
    //private Call<Postagems> call;
    private ApiService apiService;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        Log.d(TAG,"onCreate");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        mRecyclerViewPostagems.setLayoutManager(gridLayoutManager);
        mAdapter = new PostagemsAdapter(this);
        mRecyclerViewPostagems.setAdapter(mAdapter);
        //loadPostagems();
        //getPostagems();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create:
                Intent intent = new Intent(this, CadastrarPostagemActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_refresh:
                loadPostagems();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    private void loadPostagems() {

        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Carregando Postagems aguarde...");
        mDialog.setCancelable(true);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);

        mAdapter.reset();

        mDialog.show();

        if (getNetworkAvailability()) {
            getPostagems();
        } else {
            Toast.makeText(getApplicationContext(), "Não há conexão com a internet. Conecte-se a Internet e depois tente abrir o aplicativo.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
        loadPostagems();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }

    @Override
    public void onClick(int position) {
        Postagem selectedPostagem = mAdapter.getSelectedPostagem(position);
        Intent intent = new Intent(MainActivity.this, DetalhesPostagemActivity.class);
        intent.putExtra("Postagems", selectedPostagem);
        startActivity(intent);
    }

    public void getPostagems() {
        apiService = RetrofitBuilder.getRetrofit().create(ApiService.class);
        //call = apiService.getPostagems();
        Call<Postagems> listCall = apiService.getPostagems();
        listCall.enqueue(new Callback<Postagems>() {

            @Override
            public void onResponse(Call<Postagems> call, Response<Postagems> response) {
                if (response.body() != null) {
                    mAdapter.setPostagems(response.body().getResults());
                }
                mDialog.dismiss();
            }

            @Override
            public void onFailure(Call<Postagems> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
    }
}
