package com.example.luisfrancisco.sinfilarregional;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spProvincias;
    Spinner spLocalidades;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        
        this.spProvincias = (Spinner)findViewById(R.id.sp_provincia);
        this.spLocalidades = (Spinner)findViewById(R.id.sp_localidad);
        loadSpinnerProvincias();
    }

    private void loadSpinnerProvincias() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.provincias,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spProvincias.setAdapter(adapter);
        spProvincias.setOnItemSelectedListener(this);
        spLocalidades.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId())
        {
            case R.id.sp_provincia:
                TypedArray arrayLocalidades = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
                CharSequence[] localidades = arrayLocalidades.getTextArray(position);
                arrayLocalidades.recycle();

                ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,android.R.id.text1,localidades);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spLocalidades.setAdapter(adapter);
                break;
            case R.id.sp_localidad:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
