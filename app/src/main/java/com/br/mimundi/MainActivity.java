package com.br.mimundi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFabricante, spinnerMarcas, spinnerModelos, spinnerCores;
    private EditText editTextSerie, editTextAno, editTextNome, editTextObs;
    private CheckBox cbLoose;
    private RadioGroup radioGroupRaridade;

    public static final String FABRICANTE = "FABRICANTE";
    public static final String MARCA = "MARCA";
    public static final String MODELO = "MODELO";
    public static final String COR = "COR";
    public static final String SERIE = "SERIE";
    public static final String RARIDADE = "RARIDADE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFabricante = findViewById(R.id.spinnerFabricantes);
        spinnerMarcas = findViewById(R.id.spinnerMarcas);
        spinnerModelos = findViewById(R.id.spinnerModelos);
        spinnerCores = findViewById(R.id.spinnerCores);
        editTextSerie = findViewById(R.id.editTextSerie);
        editTextAno = findViewById(R.id.editTextAno);
        editTextNome = findViewById(R.id.editTextNome);
        editTextObs = findViewById(R.id.editTextObs);
        cbLoose = findViewById(R.id.cbLoose);
        radioGroupRaridade = findViewById(R.id.radioGroupRaridade);

        spinnerMarcas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String marca = adapterView.getItemAtPosition(i).toString();

                if (marca.equals(getString(R.string.audi))) {
                    popularModelos(R.array.modelos_audi);
                } else if (marca.equals(getString(R.string.bmw))) {
                    popularModelos(R.array.modelos_bmw);
                } else if (marca.equals(getString(R.string.chevrolet))) {
                    popularModelos(R.array.modelos_chevrolet);
                } else if (marca.equals(getString(R.string.citroen))) {
                    popularModelos(R.array.modelos_citroen);
                } else if (marca.equals(getString(R.string.dodge))) {
                    popularModelos(R.array.modelos_dodge);
                } else if (marca.equals(getString(R.string.ferrari))) {
                    popularModelos(R.array.modelos_ferrari);
                } else if (marca.equals(getString(R.string.fiat))) {
                    popularModelos(R.array.modelos_fiat);
                } else if (marca.equals(getString(R.string.ford))) {
                    popularModelos(R.array.modelos_ford);
                } else if (marca.equals(getString(R.string.gmc))) {
                    popularModelos(R.array.modelos_gmc);
                } else if (marca.equals(getString(R.string.honda))) {
                    popularModelos(R.array.modelos_honda);
                } else if (marca.equals(getString(R.string.hyundai))) {
                    popularModelos(R.array.modelos_hyundai);
                } else if (marca.equals(getString(R.string.jaguar))) {
                    popularModelos(R.array.modelos_jaguar);
/*                } else if (marca.equals(getString(R.string.jeep))) {
                    popularModelos(R.array.modelos_jeep);*/
                } else if (marca.equals(getString(R.string.kia))) {
                    popularModelos(R.array.modelos_kia);
                } else if (marca.equals(getString(R.string.lamborghini))) {
                    popularModelos(R.array.modelos_lamborghini);
                } else if (marca.equals(getString(R.string.lancia))) {
                    popularModelos(R.array.modelos_lancia);
                } else if (marca.equals(getString(R.string.land_rover))) {
                    popularModelos(R.array.modelos_land_rover);
                } else if (marca.equals(getString(R.string.mazda))) {
                    popularModelos(R.array.modelos_mazda);
                } else if (marca.equals(getString(R.string.mclaren))) {
                    popularModelos(R.array.modelos_mclaren);
                } else if (marca.equals(getString(R.string.mercedes))) {
                    popularModelos(R.array.modelos_mercedes);
                } else if (marca.equals(getString(R.string.mini))) {
                    popularModelos(R.array.modelos_mini);
/*                } else if (marca.equals(getString(R.string.mitsubishi))) {
                    popularModelos(R.array.modelos_mitsubishi); */
                } else if (marca.equals(getString(R.string.n_a))) {
                    popularModelos(R.array.modelos_na);
                } else if (marca.equals(getString(R.string.nissan))) {
                    popularModelos(R.array.modelos_nissan);
                } else if (marca.equals(getString(R.string.peugeot))) {
                    popularModelos(R.array.modelos_peugeot);
                } else if (marca.equals(getString(R.string.plymouth))) {
                    popularModelos(R.array.modelos_plymonth);
                } else if (marca.equals(getString(R.string.porsche))) {
                    popularModelos(R.array.modelos_porsche);
/*                } else if (marca.equals(getString(R.string.renault))) {
                    popularModelos(R.array.modelos_renault); */
                } else if (marca.equals(getString(R.string.subaru))) {
                    popularModelos(R.array.modelos_subaru);
                } else if (marca.equals(getString(R.string.tesla))) {
                    popularModelos(R.array.modelos_tesla);
                } else if (marca.equals(getString(R.string.toyota))) {
                    popularModelos(R.array.modelos_toyota);
                } else if (marca.equals(getString(R.string.volkswagen))) {
                    popularModelos(R.array.modelos_volskwagen);
                } else if (marca.equals(getString(R.string.volvo))) {
                    popularModelos(R.array.modelos_volvo);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void popularModelos(int arrayId) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                arrayId,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerModelos.setAdapter(adapter);
    }

    public void salvar(View view){

        String mensagem = "SALVO";

        /*
        * *
        * Aqui preciso pegar tudo selecionado
        * Fabricante
        * Marca
        * Modelo
        * Serie
        *       e então criar o objeto Minitarua
        *
        * */

        String fabricante = (String) spinnerFabricante.getSelectedItem();
        String marca = (String) spinnerMarcas.getSelectedItem();
        String modelo = (String) spinnerModelos.getSelectedItem();
        String cor = (String) spinnerCores.getSelectedItem();
        String serie = editTextSerie.getText().toString();
        String raridade = "comum";

        switch (radioGroupRaridade.getCheckedRadioButtonId()){

            case R.id.radioButtonComum:
                raridade = getString(R.string.comum);
                break;
            case R.id.radioButtonRaro:
                raridade = getString(R.string.raro);
                break;
            case R.id.radioButtonThunt:
                raridade = getString(R.string.thunt);
                break;
            default:
                raridade = getString(R.string.comum);

        }


        Intent intent = new Intent();

        intent.putExtra(FABRICANTE, fabricante);
        intent.putExtra(MARCA, marca);
        intent.putExtra(MODELO, modelo);
        intent.putExtra(COR, cor);
        intent.putExtra(SERIE, serie);
        intent.putExtra(RARIDADE, raridade);

        setResult(Activity.RESULT_OK, intent);

        finish();

        //Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    public void limparCampos(View view){

        editTextSerie.setText(null);
        editTextAno.setText(null);
        editTextNome.setText(null);
        editTextObs.setText(null);

        cbLoose.setChecked(false);
        radioGroupRaridade.clearCheck();

        Toast.makeText(this, R.string.limpar_campos, Toast.LENGTH_LONG).show();

        spinnerFabricante.requestFocus();
    }

}