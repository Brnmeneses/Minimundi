package com.br.mimundi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewFabricante;

    public static final int CADASTRAR_NOVO = 1;

    private ArrayList<Miniatura> miniaturaListTela = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewFabricante = findViewById(R.id.listViewFabricante);

        listViewFabricante.setOnItemClickListener((adapterView, view, position, l) -> {
            Miniatura fabricante = (Miniatura) listViewFabricante.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), fabricante.getModelo(), Toast.LENGTH_LONG).show();
        });

        //popularList();

    }

    private void popularList(ArrayList<Miniatura> miniaturaList) {

/*        String[] marcas = getResources().getStringArray(R.array.marcas_list);
        String[] modelos = getResources().getStringArray(R.array.modelos_list);
        String[] cores = getResources().getStringArray(R.array.cores_list);
        String[] anos = getResources().getStringArray(R.array.anos_list);


        for (int i = 0; i < marcas.length; i++) {
            miniaturaList.add(new Miniatura(
                    "Hot wheels",
                    marcas[i],
                    modelos[i],
                    anos[i],
                    cores[i]));
        }
*/
        ArrayAdapter<Miniatura> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_expandable_list_item_1,
                        miniaturaList);

        listViewFabricante.setAdapter(adapter);
    }

    public void mostarAutoria(View view){
        Intent intent = new Intent(this,
                AutoriaActivity.class);

        startActivity(intent);
    }

    public void cadastrarNovo(View view){
        Intent intent = new Intent(this,
                MainActivity.class);

        startActivityForResult(intent,
                CADASTRAR_NOVO);

    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        if (requestCode == CADASTRAR_NOVO && resultCode == Activity.RESULT_OK) {

            Bundle bundle = data.getExtras();
            if (bundle != null) {


                String fabricante = bundle.getString(MainActivity.FABRICANTE);
                String marca =  bundle.getString(MainActivity.MARCA);
                String modelo = bundle.getString(MainActivity.MODELO);
                String cor =  bundle.getString(MainActivity.COR);
                String serie =  bundle.getString(MainActivity.SERIE);
                String raridade =  bundle.getString(MainActivity.RARIDADE);


                ArrayList<Miniatura> miniaturaList = new ArrayList<>();

                miniaturaList.add(new Miniatura(fabricante, marca, modelo, "2021",cor));

                miniaturaListTela.addAll(miniaturaList);

                popularList(miniaturaListTela);

                Toast.makeText(this,
                        getString(R.string.sucesso),
                        Toast.LENGTH_LONG).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}