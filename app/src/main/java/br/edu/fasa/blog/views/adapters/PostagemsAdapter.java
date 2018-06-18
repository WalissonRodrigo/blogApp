package br.edu.fasa.blog.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import br.edu.fasa.blog.models.Postagem;
import br.edu.fasa.blog.R;
import br.edu.fasa.blog.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.squareup.picasso.Picasso;

/**
 * Created by Android on 13/04/2018.
 */

public class PostagemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context context;
    private List<Postagem> postagems = new ArrayList<>();
    private PostagemClickListener mListener;

    public PostagemsAdapter(PostagemClickListener listener)
    {
        this.mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_postagem, parent, false);
        postagemViewHolder holder = new postagemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Postagem postagem = postagems.get(position);

        postagemViewHolder postagemHolder = (postagemViewHolder) holder;
        ((postagemViewHolder) holder).bind(postagem);
    }

    @Override
    public int getItemCount() {
        return postagems != null ? postagems.size() : 0;
    }


    public void addPostagem(Postagem postagem){
        postagems.add(postagem);
        notifyItemInserted(getItemCount());
    }

    public void setPostagems(List<Postagem> postagems){
        this.postagems = new ArrayList<>();
        for(Postagem postagem: postagems){
            addPostagem(postagem);
        }
    }

    public Postagem getSelectedPostagem(int position) {
        return postagems.get(position);

    }

    public void reset() {
        postagems.clear();
        notifyDataSetChanged();
    }

    public class postagemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //private String IMAGE_URL= "prefixo se existir patch para a imagem";
        @BindView(R.id.postagem_image_principal)
        ImageView mImagePoster;
        @BindView(R.id.postagem_text_titulo)
        TextView mTitulo;
        @BindView(R.id.postagem_text_conteudo)
        TextView mConteudo;
        @BindView(R.id.postagem_text_autor)
        TextView mAutor;

        public postagemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(@NonNull final Postagem postagem) {
            String url1 =  postagem.getImage_1()!= null ? postagem.getImage_1() :"http://blogfasa.herokuapp.com/adm/img/sem.gif";

            Picasso.get()
                    .load(url1)
                    .into(mImagePoster);

            mTitulo.setText("Título: "+postagem.getTitulo());
            mConteudo.setText("Descrição: "+postagem.getConteudo());
            String autor = postagem.getAutor() != null ? postagem.getAutor() : "Desconhecido";
            mAutor.setText("Autor: "+autor);
        }

        @OnClick
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition());
        }
    }

    public interface PostagemClickListener {
        void onClick(int position);
    }
}
