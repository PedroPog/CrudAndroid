package app.estudos.crudandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.estudos.crudandroid.MainActivity;
import app.estudos.crudandroid.R;
import app.estudos.crudandroid.fragments.FragmentVenda;
import app.estudos.crudandroid.model.ModelCategoria;

public class AdapterCategList extends RecyclerView.Adapter
        <AdapterCategList.RecyclerViewHolder> {
    private ArrayList<ModelCategoria> lista;
    private Context context;
    FragmentVenda fragmentVenda = new FragmentVenda();
    private Animation animation;
    private Intent intent;
    private String numero = "5";

    public AdapterCategList(ArrayList<ModelCategoria> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    public void clear(){lista.clear();}

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        //sistema de imagem
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_sem_imagem,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final ModelCategoria itemposicao = lista.get(position);
        holder.txt_categ.setText(itemposicao.getDescricao());
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor(itemposicao.getCor()));
        drawable.setCornerRadius(10);
        drawable.setStroke(3,Color.parseColor("#000301"));
        holder.txt_categ.setBackground(drawable);

        holder.txt_categ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return lista.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_categ;
        //private ImageView imageView;
        private LinearLayout layout_categ;
        //private TextView descricao;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_categ = itemView.findViewById(R.id.txt_categ);
            layout_categ = itemView.findViewById(R.id.layout_categ);
            /*if(objparam.getHabilitaImagem()==00||objparam.getHabilitaImagem()==10){
                descricao = itemView.findViewById(R.id.tvdescricaoprod);//button SEM IMAGEM
            }else{
                textView = itemView.findViewById(R.id.button1);//button COM IMAGEM
                imageView = itemView.findViewById(R.id.imageView);//button COM IMAGEM
                constraintLayout = itemView.findViewById(R.id.buttonFundo);//button COM IMAGEM
            }*/


        }
    }
}
