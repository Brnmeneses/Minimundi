package com.br.mimundi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerFabricante, spinnerMarcas, spinnerModelos, spinnerCores;
    private EditText editTextAno;
    private CheckBox cbLoose;
    private RadioGroup radioGroupRaridade;

    private int modo = 1;
    private String editId;
    private String editFabricante;
    private String editMarca;
    private String editModelo;
    private String editCor;
    private String editAno;
    private String editLoose;
    private String editRaridade;

    private Boolean flagOnItemSelectedListener = true;

    public static final String ID = "ID";

    public static final String MODO = "MODO";
    public static final int NOVO = 1;
    public static final int ALTERAR = 2;

    private int retornaArrayModelo(String marca) {

        if (marca.equals(getString(R.string.audi))) {
            return R.array.modelos_audi;
        } else if (marca.equals(getString(R.string.bmw))) {
            return R.array.modelos_bmw;
        } else if (marca.equals(getString(R.string.chevrolet))) {
            return R.array.modelos_chevrolet;
        } else if (marca.equals(getString(R.string.citroen))) {
            return R.array.modelos_citroen;
        } else if (marca.equals(getString(R.string.dodge))) {
            return R.array.modelos_dodge;
        } else if (marca.equals(getString(R.string.ferrari))) {
            return R.array.modelos_ferrari;
        } else if (marca.equals(getString(R.string.fiat))) {
            return R.array.modelos_fiat;
        } else if (marca.equals(getString(R.string.ford))) {
            return R.array.modelos_ford;
        } else if (marca.equals(getString(R.string.gmc))) {
            return R.array.modelos_gmc;
        } else if (marca.equals(getString(R.string.honda))) {
            return R.array.modelos_honda;
        } else if (marca.equals(getString(R.string.hyundai))) {
            return R.array.modelos_hyundai;
        } else if (marca.equals(getString(R.string.jaguar))) {
            return R.array.modelos_jaguar;
        } else if (marca.equals(getString(R.string.kia))) {
            return R.array.modelos_kia;
        } else if (marca.equals(getString(R.string.lamborghini))) {
            return R.array.modelos_lamborghini;
        } else if (marca.equals(getString(R.string.lancia))) {
            return R.array.modelos_lancia;
        } else if (marca.equals(getString(R.string.land_rover))) {
            return R.array.modelos_land_rover;
        } else if (marca.equals(getString(R.string.mazda))) {
            return R.array.modelos_mazda;
        } else if (marca.equals(getString(R.string.mclaren))) {
            return R.array.modelos_mclaren;
        } else if (marca.equals(getString(R.string.mercedes))) {
            return R.array.modelos_mercedes;
        } else if (marca.equals(getString(R.string.mini))) {
            return R.array.modelos_mini;
        } else if (marca.equals(getString(R.string.nissan))) {
            return R.array.modelos_nissan;
        } else if (marca.equals(getString(R.string.peugeot))) {
            return R.array.modelos_peugeot;
        } else if (marca.equals(getString(R.string.plymouth))) {
            return R.array.modelos_plymonth;
        } else if (marca.equals(getString(R.string.porsche))) {
            return R.array.modelos_porsche;
        } else if (marca.equals(getString(R.string.subaru))) {
            return R.array.modelos_subaru;
        } else if (marca.equals(getString(R.string.tesla))) {
            return R.array.modelos_tesla;
        } else if (marca.equals(getString(R.string.toyota))) {
            return R.array.modelos_toyota;
        } else if (marca.equals(getString(R.string.volkswagen))) {
            return R.array.modelos_volskwagen;
        } else if (marca.equals(getString(R.string.volvo))) {
            return R.array.modelos_volvo;
        } else
            return R.array.modelos_na;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFabricante = findViewById(R.id.spinnerFabricantes);
        spinnerMarcas = findViewById(R.id.spinnerMarcas);
        spinnerModelos = findViewById(R.id.spinnerModelos);
        spinnerCores = findViewById(R.id.spinnerCores);
        editTextAno = findViewById(R.id.editTextAno);
        cbLoose = findViewById(R.id.cbLoose);
        radioGroupRaridade = findViewById(R.id.radioGroupRaridade);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {

            modo = bundle.getInt(MODO, ALTERAR);
            setTitle(getString(R.string.alterar_miniatura));

            editId = bundle.getString(ID);

            int id2 = Integer.parseInt(editId);

            MiniaturaDatabase database = MiniaturaDatabase.getDatabase(this);
            Miniatura miniatura = database.miniaturaDAO().queryForId(id2);

            editFabricante = miniatura.getFabricante();
            editMarca = miniatura.getMarca();
            editModelo = miniatura.getModelo();
            editCor = miniatura.getCor();
            editAno = miniatura.getAno();
            editLoose = miniatura.getStringLoose();
            editRaridade = miniatura.getRaridade();

            popularEditFabricante(editFabricante);
            popularEditMarca(editMarca);
            popularEditModelo(editMarca, editModelo);
            popularEditCor(editCor);

            editTextAno.setText(editAno);
            cbLoose.setChecked(editLoose.equals("T") ? true : false);
            switch (editRaridade) {
                case "Raro":
                    radioGroupRaridade.check(R.id.radioButtonRaro);
                    break;
                case "T-Hunted":
                    radioGroupRaridade.check(R.id.radioButtonThunt);
                    break;
                default:
                    radioGroupRaridade.check(R.id.radioButtonComum);
                    break;
            }

        } else {
            setTitle(getString(R.string.nova_miniatura));
        }

        if (modo == NOVO || flagOnItemSelectedListener) {

            spinnerMarcas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String marca = adapterView.getItemAtPosition(i).toString();
                    popularModelos(retornaArrayModelo(marca));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
        flagOnItemSelectedListener = true;
    }

    private void popularEditFabricante(String editFabricante) {
        String[] fabricantesArray = getResources().getStringArray(R.array.fabricantes);
        int index = 0;

        for (int i = 0; i < fabricantesArray.length; i++) {
            if (fabricantesArray[i].equals(editFabricante)) {
                index = i;
            }
        }
        spinnerFabricante.setSelection(index);
    }

    private void popularEditMarca(String editMarca) {
        String[] marcasArray = getResources().getStringArray(R.array.marcas);
        int index = 0;

        for (int i = 0; i < marcasArray.length; i++) {
            if (marcasArray[i].equals(editMarca)) {
                index = i;
            }
        }
        spinnerMarcas.setSelection(index);
    }

    private void popularEditModelo(String editMarca, String editModelo) {

        popularModelos(retornaArrayModelo(editMarca));
        String[] modelosArray = getResources().getStringArray(retornaArrayModelo(editMarca));
        int index = 0;

        for (int i = 0; i < modelosArray.length; i++) {
            if (modelosArray[i].equals(editModelo)) {
                index = i;
            }
        }
        spinnerModelos.setSelection(index);
        flagOnItemSelectedListener = false;
    }

    private void popularEditCor(String editCor) {
        String[] coresArray = getResources().getStringArray(R.array.cores);
        int index = 0;

        for (int i = 0; i < coresArray.length; i++) {
            if (coresArray[i].equals(editCor)) {
                index = i;
            }
        }
        spinnerCores.setSelection(index);
    }

    private void popularModelos(int arrayId) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                arrayId,
                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerModelos.setAdapter(adapter);
    }

    public void salvar() {
        String fabricante = (String) spinnerFabricante.getSelectedItem();
        String marca = (String) spinnerMarcas.getSelectedItem();
        String modelo = (String) spinnerModelos.getSelectedItem();
        String cor = (String) spinnerCores.getSelectedItem();
        String ano = editTextAno.getText().toString().trim();
        String loose = cbLoose.isChecked() ? "T" : "F";
        String raridade = "comum";

        switch (radioGroupRaridade.getCheckedRadioButtonId()) {

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

        MiniaturaDatabase database = MiniaturaDatabase.getDatabase(this);
        Miniatura miniatura = new Miniatura(
                fabricante,
                marca,
                modelo,
                ano,
                cor,
                cbLoose.isChecked(),
                raridade
        );
        if (modo == NOVO) {
            database.miniaturaDAO().insert(miniatura);
        } else {
            miniatura.setId(Integer.parseInt(editId));
            database.miniaturaDAO().update(miniatura);
        }


        setResult(Activity.RESULT_OK);

        finish();
    }

    public void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }

    public void limparCampos() {

        editTextAno.setText(null);

        cbLoose.setChecked(false);
        radioGroupRaridade.clearCheck();

        Toast.makeText(this, R.string.limpar_campos, Toast.LENGTH_LONG).show();

        spinnerFabricante.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_opcoes, menu);
        return true;
    }

    private void mostrarMensagem(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemSalvar:
                salvar();
                mostrarMensagem(getString(R.string.salvar));
                return true;
            case R.id.menuItemLimpar:
                limparCampos();
                mostrarMensagem(getString(R.string.limpar));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}