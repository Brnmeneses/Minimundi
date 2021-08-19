package com.br.mimundi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewMiniaturas;

    private ArrayList<Miniatura> miniaturaListTela = new ArrayList<>();
    private ArrayAdapter<Miniatura> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewMiniaturas = findViewById(R.id.listViewMiniatura);

   /*     listViewMiniaturas.setOnItemClickListener((adapterView, view, position, l) -> {
            Miniatura fabricante = (Miniatura) listViewMiniaturas.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), fabricante.getModelo(), Toast.LENGTH_LONG).show();
        });*/

        registerForContextMenu(listViewMiniaturas);
    }

    private void popularLista(ArrayList<Miniatura> miniaturaList) {
        adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_expandable_list_item_1,
                        miniaturaList);

        adapter.notifyDataSetChanged();

        listViewMiniaturas.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        if ((requestCode == MainActivity.NOVO
        || requestCode == MainActivity.ALTERAR)
                && resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();
            if (bundle != null) {


                String fabricante = bundle.getString(MainActivity.FABRICANTE);
                String marca =  bundle.getString(MainActivity.MARCA);
                String modelo = bundle.getString(MainActivity.MODELO);
                String cor =  bundle.getString(MainActivity.COR);
                String serie =  bundle.getString(MainActivity.SERIE);
                String raridade =  bundle.getString(MainActivity.RARIDADE);


                ArrayList<Miniatura> miniaturaList = new ArrayList<>();

                Miniatura nvMini = new Miniatura(fabricante, marca, modelo, "2021",cor);
                miniaturaList.contains(nvMini);
                miniaturaList.add(nvMini);

                miniaturaListTela.addAll(miniaturaList);

                popularLista(miniaturaListTela);

                Toast.makeText(this,
                        getString(R.string.sucesso),
                        Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    private void mostrarMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    private void mostrarDadosSobre(){
        Intent intent = new Intent(this,
                AutoriaActivity.class);

        startActivity(intent);
    }

    private void mostrarCadastrarNovo(){
        Intent intent = new Intent(this,
                MainActivity.class);

        startActivityForResult(intent,MainActivity.NOVO);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuItemAdicionar:
                mostrarCadastrarNovo();
                return true;
            case R.id.menuItemSobre:
                mostrarDadosSobre();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void alterar(int posicao){
        Miniatura miniatura = miniaturaListTela.get(posicao);

        Intent intent = new Intent(this,
                MainActivity.class);

        intent.putExtra(MainActivity.FABRICANTE, miniatura.getFabricante());
        intent.putExtra(MainActivity.MARCA, miniatura.getMarca());
        intent.putExtra(MainActivity.MODELO, miniatura.getModelo());
        intent.putExtra(MainActivity.COR, miniatura.getCor());

        startActivityForResult(intent, MainActivity.ALTERAR);

    }

    private void excluir(int posicao){
        miniaturaListTela.remove(posicao);
        popularLista(miniaturaListTela);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu,
                                    View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.principal_menu_contexto, menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;

        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.menuItemAlterar:
                alterar(info.position);
                return true;

            case R.id.menuItemExcluir:
                excluir(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}