package com.practica.eddy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.practica.eddy.myapplication.Beans.PersonaBean;
import com.practica.eddy.myapplication.Dao.PersonaDAO;

public class SecondActivity extends AppCompatActivity {

    LinearLayout llBuscar;
    EditText etBuscar,etDni,etNombre,etApellido,etEdad;
    TextView tvTitulo;
    Button btOperacion,btBuscarSecond,btEliminar;
    Spinner spSexoSecond;

    PersonaDAO personaDAO=new PersonaDAO(SecondActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        etBuscar=(EditText)findViewById(R.id.etBuscarSecond);
        etDni=(EditText)findViewById(R.id.etDni);
        etNombre=(EditText)findViewById(R.id.etNombre);
        etApellido=(EditText)findViewById(R.id.etApellido);
        etEdad=(EditText)findViewById(R.id.etEdad);
        spSexoSecond=(Spinner)findViewById(R.id.spSexoSecond);
        tvTitulo=(TextView)findViewById(R.id.tvTitulo);
        btOperacion=(Button)findViewById(R.id.btOperacion);
        btBuscarSecond=(Button)findViewById(R.id.btBuscarSecond);
        btEliminar=(Button)findViewById(R.id.btEliminar);
        llBuscar=(LinearLayout)findViewById(R.id.llSecond);

        Intent intent=getIntent();
        if(intent.getSerializableExtra("persona")!=null){
            PersonaBean personaBean= (PersonaBean) intent.getSerializableExtra("persona");
            etDni.setText(personaBean.getDni());
            etNombre.setText(personaBean.getNombre());
            etApellido.setText(personaBean.getApellido());
            etEdad.setText(String.valueOf(personaBean.getEdad()));
            tvTitulo.setText("Datos de " +personaBean.getNombre());
            btOperacion.setText("Actualizar");
            etDni.setEnabled(false);
        }
        else {
            btEliminar.setVisibility(View.INVISIBLE);
            llBuscar.setVisibility(View.INVISIBLE);
        }

        btEliminar.setOnClickListener(onClickListenerEliminar);
        btOperacion.setOnClickListener(onClickListenerOperacion);
        btBuscarSecond.setOnClickListener(onClickListenerBuscar);
    }

    private final View.OnClickListener onClickListenerBuscar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PersonaBean personaBeans=personaDAO.personaXdni(etBuscar.getText().toString());
            etDni.setText(personaBeans.getDni());
            etNombre.setText(personaBeans.getNombre());
            etApellido.setText(personaBeans.getApellido());
            etEdad.setText(String.valueOf(personaBeans.getEdad()));
            tvTitulo.setText("Datos de " +personaBeans.getNombre());
        }
    };

    private final View.OnClickListener onClickListenerOperacion=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PersonaBean personaBean=new PersonaBean();
            personaBean.setDni(etDni.getText().toString());
            personaBean.setNombre(etNombre.getText().toString());
            personaBean.setApellido(etApellido.getText().toString());
            personaBean.setEdad(Integer.parseInt(etEdad.getText().toString()));
            personaBean.setSexo(spSexoSecond.getSelectedItem().toString());
            if(etDni.isEnabled()){
                personaDAO.agregar(personaBean);
            }
            else {
                personaDAO.actualizar(personaBean);
            }
            startActivity(new Intent(SecondActivity.this,MainActivity.class));
            finish();
        }
    };

    private final View.OnClickListener onClickListenerEliminar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          personaDAO.eliminar(etDni.getText().toString());
            startActivity(new Intent(SecondActivity.this,MainActivity.class));
            finish();
        }
    };
}
