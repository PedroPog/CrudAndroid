package app.estudos.crudandroid.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import app.estudos.crudandroid.R;
import app.estudos.crudandroid.SplashActivity;
import app.estudos.crudandroid.adapter.AdapterCategList;
import app.estudos.crudandroid.adapter.AdapterProdList;
import app.estudos.crudandroid.database.BdCategoria;
import app.estudos.crudandroid.database.BdProduto;
import app.estudos.crudandroid.model.ModelCategoria;
import app.estudos.crudandroid.model.ModelProduto;

public class FragmentVenda extends Fragment {
    private ViewGroup toolbarContainer,infos_container;
    private Toolbar currentToolbar;
    private RecyclerView grid_itens_venda;
    private RecyclerView grid_itens_venda2;
    private LinearLayout currentLinear;
    private static AdapterCategList categList;
    private static AdapterProdList prodList;
    public static ArrayList<ModelCategoria> listDeItensCateg;
    public static ArrayList<ModelProduto> listDeItensProd;
    private BdCategoria bdCategoria;
    private BdProduto bdProduto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_venda, container, false);
        toolbarContainer = view.findViewById(R.id.toolbar_container);  // Container para adicionar as toolbars
        infos_container = view.findViewById(R.id.infos_container);  // Container para adicionar as infos
        grid_itens_venda = view.findViewById(R.id.grid_itens_venda);
        grid_itens_venda2 = view.findViewById(R.id.grid_itens_venda2);

        // Inicializa com a Toolbar de Orçamento
        loadToolbar(R.layout.toolbar_venda,R.layout.infos_venda);
        if (view.getContext() != null) {
            bdCategoria = new BdCategoria(view.getContext());
            bdProduto = new BdProduto(view.getContext());
        } else {
            Log.e("FragmentVenda", "Contexto não foi carregado corretamente");
        }
        listDeItensCateg = new ArrayList<>();
        listDeItensProd = new ArrayList<>();
        listarCateg();
        //listarProduto(1);
        return view;
    }

    private void listarCateg() {
        Cursor cursor = bdCategoria.selecionarVendCateg();
        if(cursor != null && cursor.moveToFirst()){
            for (int i=0;i<cursor.getCount();i++){
                ModelCategoria categoria = new ModelCategoria();
                cursor.moveToPosition(i);
                categoria.setIdcategoria(cursor.getInt(0));
                categoria.setDescricao(cursor.getString(1));
                categoria.setCor(cursor.getString(2));
                listDeItensCateg.add(categoria);
            }
            cursor.close();
        }
        categList = new AdapterCategList(listDeItensCateg,getContext());
        grid_itens_venda.setAdapter(categList);
        grid_itens_venda.setLayoutManager(new GridLayoutManager(getContext(), 4)); // 2 é o número de colunas
    }
    public void listarProduto(int idcateg){
        try {
            Cursor cursor = bdProduto.selecionarVendProd(idcateg);
            if(cursor != null && cursor.moveToFirst()){
                for (int i=0;i<cursor.getCount();i++){
                    ModelProduto produto = new ModelProduto();
                    cursor.moveToPosition(i);
                    produto.setIdproduto(cursor.getInt(0));
                    produto.setDescricao(cursor.getString(1));
                    produto.setPreco(cursor.getDouble(2));
                    produto.setQuantestoque(cursor.getDouble(3));
                    produto.setCodigoean(cursor.getString(4));
                    produto.setPrecovariavel(Boolean.parseBoolean(cursor.getString(5)));
                    produto.setSigla(cursor.getString(6));
                    produto.setImagem(cursor.getString(7));
                    produto.setCor(cursor.getString(8));
                    produto.setStatus(Boolean.parseBoolean(cursor.getString(9)));
                    listDeItensProd.add(produto);
                }
                cursor.close();
            }
            //prodList.clear();
            grid_itens_venda2.setLayoutManager(new GridLayoutManager(getContext(), 4)); // 2 é o número de colunas
            prodList = new AdapterProdList(listDeItensProd,getContext());
            grid_itens_venda2.setAdapter(prodList);

        }catch (Exception e){
            Log.e("AdapaterProduto","Falha ao inicia: "+e.getLocalizedMessage());
        }
    }

    private void loadToolbar(int toolbarLayoutId,int infosLayoutId) {
        if (getActivity() != null) {
            // Remover a Toolbar atual, se existir
            toolbarContainer.removeAllViews();
            infos_container.removeAllViews();

            // Infla a nova Toolbar e adiciona ao container
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            currentToolbar = (Toolbar) inflater.inflate(toolbarLayoutId, toolbarContainer, false);
            currentLinear = (LinearLayout) inflater.inflate(infosLayoutId, infos_container, false);
            toolbarContainer.addView(currentToolbar);
            infos_container.addView(currentLinear);

            // Configura a nova Toolbar como a ActionBar
            ((SplashActivity) getActivity()).setSupportActionBar(currentToolbar);
            setHasOptionsMenu(true);
            getActivity().invalidateOptionsMenu();
        }
        //setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Infla o menu específico da Toolbar atual
        if (currentToolbar != null) {
            if (currentToolbar.getId() == R.id.toolbar_orcamento) {
                inflater.inflate(R.menu.menu_orcamento, menu);
            } else if (currentToolbar.getId() == R.id.toolbar_venda) {
                inflater.inflate(R.menu.menu_venda, menu);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(currentToolbar.getId() == R.id.toolbar_orcamento){
            if(id == R.id.cancelar_venda){
                Log.d("FragmentOrcamento", "Cancelar Venda clicado");
                return true;
            }
            if(id == R.id.reimprimir_cupom_orc){
                Log.d("FragmentOrcamento", "Reimprimir Cupom clicado");
                return true;
            }
            if(id == R.id.alterar_para_venda){
                Log.d("FragmentOrcamento", "Alterar para Venda clicado");
                loadToolbar(R.layout.toolbar_venda,R.layout.infos_venda);
                return true;
            }
            if(id == R.id.alterar_para_venda_icon){
                Log.d("FragmentOrcamento", "Ícone Alterar para Venda clicado icon");
                loadToolbar(R.layout.toolbar_venda,R.layout.infos_venda);
                return true;
            }
            if(id == R.id.efetivar_orcamento){
                Log.d("FragmentOrcamento", "Efetivar Orçamento clicado");
                return true;
            }
        }
        else if (currentToolbar.getId() == R.id.toolbar_venda){
            if(id == R.id.cancelar_venda_icon){
                Log.d("FragmentVenda", "Cancelar Venda clicado icon");
                return true;
            }
            if(id == R.id.reimprimir_cupom_icon){
                Log.d("FragmentVenda", "Reimprimir Cupom clicado icon");
                return true;
            }
            if(id == R.id.alterar_orçamento_icon){
                Log.d("FragmentVenda", "Alterar para Orcamento clicado icon");
                loadToolbar(R.layout.toolbar_orcamento,R.layout.infos_orcamento);
                return true;
            }
            if(id == R.id.cancelar_venda){
                Log.d("FragmentVenda", "Cancelar Venda clicado");
                return true;
            }
            if(id == R.id.reimprimir_cupom){
                Log.d("FragmentVenda", "Reimprimir Cupom clicado");
                return true;
            }
            if(id == R.id.alterar_orçamento){
                Log.d("FragmentVenda", "Alterar para Orcamento clicado");
                loadToolbar(R.layout.toolbar_orcamento,R.layout.infos_orcamento);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}