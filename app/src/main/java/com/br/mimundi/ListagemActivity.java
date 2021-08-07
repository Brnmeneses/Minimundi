package com.br.mimundi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
            Fabricante fabricante = (Fabricante) listViewFabricante.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), fabricante.getNome(), Toast.LENGTH_LONG).show();
        });

        popularList();

    }

    private void popularList() {

        String[] nomes = getResources().getStringArray(R.array.fabricantes);

        ArrayList<Fabricante> fabricantesList = new ArrayList<>();

        for (int i = 0; i < nomes.length; i++) {
            fabricantesList.add(new Fabricante(nomes[i], "EUA"));
        }

        ArrayAdapter<Fabricante> adapter =
                new ArrayAdapter<>(this,
                        android.R.layout.simple_expandable_list_item_1,
                        fabricantesList);

        listViewFabricante.setAdapter(adapter);
    }


}