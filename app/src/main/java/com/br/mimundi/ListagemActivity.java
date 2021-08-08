package com.br.mimundi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewFabricante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewFabricante = findViewById(R.id.listViewFabricante);

        listViewFabricante.setOnItemClickListener((adapterView, view, position, l) -> {
            Miniatura fabricante = (Miniatura) listViewFabricante.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), fabricante.getModelo(), Toast.LENGTH_LONG).show();
        });

        popularList();

    }

    private void popularList() {

        String[] marcas = getResources().getStringArray(R.array.marcas_list);
        String[] modelos = getResources().getStringArray(R.array.modelos_list);
        String[] cores = getResources().getStringArray(R.array.cores_list);
        String[] anos = getResources().getStringArray(R.array.anos_list);

        ArrayList<Miniatura> miniaturaList = new ArrayList<>();

        for (int i = 0; i < marcas.length; i++) {
            miniaturaList.add(new Miniatura(
                    "Hot wheels",
                    marcas[i],
                    modelos[i],
                    anos[i],
                    cores[i]));
        }

        ArrayAdapter<Miniatura> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_expandable_list_item_1,
                        miniaturaList);

        listViewFabricante.setAdapter(adapter);
    }


}