package com.practica.eddy.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.practica.eddy.myapplication.Beans.PersonaBean;
import com.practica.eddy.myapplication.Dao.PersonaDAO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spSexoMain;
    Button btBuscar,btNuevo;
    ListView lvMain;

    PersonaDAO personaDAO=new PersonaDAO(MainActivity.this);

    public void cargarPersona(){
        ArrayList<PersonaBean> listado=personaDAO.listado();
        ArrayAdapter<PersonaBean> adapter=new ArrayAdapter<PersonaBean>(MainActivity.this,android.R.layout.simple_list_item_1,listado);
        lvMain.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spSexoMain=(Spinner)findViewById(R.id.spSexoMain);
        btBuscar=(Button)findViewById(R.id.btBuscar);
        btNuevo=(Button)findViewById(R.id.btNuevo);
        lvMain=(ListView)findViewById(R.id.lvMain);
        cargarPersona();

        btBuscar.setOnClickListener(onClickListenerBuscar);
        btNuevo.setOnClickListener(onClickListenerNuevo);
        lvMain.setOnItemClickListener(onItemClickListener);
    }

    private final View.OnClickListener onClickListenerBuscar=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String sexo=spSexoMain.getSelectedItem().toString();
            ArrayList<PersonaBean> listado;
            if (sexo.equals("Todos")){
                listado=personaDAO.listado();
            }
            else {
                listado=personaDAO.listadoXsexo(sexo);
            }
            ArrayAdapter<PersonaBean> adapter=new ArrayAdapter<PersonaBean>(MainActivity.this,android.R.layout.simple_list_item_1,listado);
            lvMain.setAdapter(adapter);
        }
    };

    private final View.OnClickListener onClickListenerNuevo=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
            finish();
        }
    };

    private final AdapterView.OnItemClickListener onItemClickListener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            PersonaBean objeto= (PersonaBean) lvMain.getItemAtPosition(position);
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            intent.putExtra("persona",objeto);
            finish();
            startActivity(intent);
        }
    };
}
