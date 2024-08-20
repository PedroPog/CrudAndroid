package app.estudos.crudandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.estudos.crudandroid.R;
import app.estudos.crudandroid.fragments.FragmentVenda;
import app.estudos.crudandroid.model.ModelCategoria;
import app.estudos.crudandroid.model.ModelProduto;

public class AdapterProdList extends RecyclerView.Adapter
        <AdapterProdList.RecyclerViewHolder> {
    private ArrayList<ModelProduto> lista;
    private Context context;
    FragmentVenda fragmentVenda = new FragmentVenda();
    private Animation animation;
    private Intent intent;
    private String numero = "5";

    public AdapterProdList(ArrayList<ModelProduto> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    public void clear(){lista.clear();}

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        //sistema de imagem
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_sem_imagem,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        final ModelProduto itemposicao = lista.get(position);
        holder.txt_prod.setText(itemposicao.getDescricao());
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#fffff0"));
        drawable.setCornerRadius(10);
        drawable.setStroke(6,Color.parseColor(itemposicao.getCor()));
        holder.txt_prod.setBackground(drawable);
        holder.txt_prod.setOnClickListener(new View.OnClickListener() {
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

        private TextView txt_prod;
        //private ImageView imageView;
        private LinearLayout layout_prod;
        //private TextView descricao;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_prod = itemView.findViewById(R.id.txt_prod);
            layout_prod = itemView.findViewById(R.id.layout_prod);
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
