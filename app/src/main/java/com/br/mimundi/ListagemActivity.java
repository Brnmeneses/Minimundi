package com.br.mimundi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.br.mimundi.utils.UtilsGUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListagemActivity extends AppCompatActivity {

    private static final String ARQUIVO =
            "com.br.sharedpreferences.PREFERENCIAS";
    private static final String PROPS = "PROPS";
    private int opcao = 0;
    private ConstraintLayout layout;

    private ListView listViewMiniaturas;
    private List<Miniatura> miniaturaListTela = new ArrayList<>();
    private List<Miniatura> databaseList = new ArrayList<>();
    private ArrayAdapter<Miniatura> adapter;


    private void lerPreferencias(){
        SharedPreferences shared = getSharedPreferences(
                ARQUIVO,
                Context.MODE_PRIVATE);

        opcao = shared.getInt(PROPS, opcao);
    }

    private void salvarPreferencias(int valor){

        opcao = valor;

        SharedPreferences shared = getSharedPreferences(
                ARQUIVO,
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putInt(PROPS, valor);

        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewMiniaturas = findViewById(R.id.listViewMiniatura);

        registerForContextMenu(listViewMiniaturas);

        lerPreferencias();
        popularLista();

    }

    private void popularLista() {

        MiniaturaDatabase database = MiniaturaDatabase.getDatabase(this);

        databaseList = database.miniaturaDAO().queryAll();

        if (opcao == 1) {
            Collections.sort(databaseList);
        } else {
            Collections.reverse(databaseList);
        }

        adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_expandable_list_item_1,
                        databaseList);

        adapter.notifyDataSetChanged();

        listViewMiniaturas.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK) {

            popularLista();

            Toast.makeText(this,
                    getString(R.string.sucesso),
                    Toast.LENGTH_LONG).show();

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal_opcoes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item;
        item = menu.findItem(R.id.menuItemSort);
        item.setChecked(opcao == 1 ? true : false);
        return true;
    }

    private void mostrarDadosSobre() {
        Intent intent = new Intent(this,
                AutoriaActivity.class);

        startActivity(intent);
    }

    private void mostrarCadastrarNovo() {
        Intent intent = new Intent(this,
                MainActivity.class);

        startActivityForResult(intent, MainActivity.NOVO);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemAdicionar:
                mostrarCadastrarNovo();
                return true;
            case R.id.menuItemSobre:
                mostrarDadosSobre();
                return true;
            case R.id.menuItemSort:
                Boolean chk = item.isChecked();
                salvarPreferencias(chk ? 0 : 1);
                item.setChecked(!chk);
                popularLista();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void alterar(int posicao) {

        Miniatura miniatura = databaseList.get(posicao);

        Intent intent = new Intent(this,
                MainActivity.class);

        intent.putExtra(MainActivity.ID, String.valueOf(miniatura.getId()));

        startActivityForResult(intent, MainActivity.ALTERAR);
    }

    private void excluir(int posicao) {

        String mensagem = getString(R.string.please_confirm);

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:

                                Miniatura miniatura = databaseList.get(posicao);
                                MiniaturaDatabase database = MiniaturaDatabase.getDatabase(ListagemActivity.this);
                                database.miniaturaDAO().delete(miniatura);

                                popularLista();
                                adapter.notifyDataSetChanged();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };

        UtilsGUI.confirmaAcao(this, mensagem, listener);
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

        switch (item.getItemId()) {
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