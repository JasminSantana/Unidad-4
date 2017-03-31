package mx.edu.utng.wsmovie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Alumno on 30/03/2017.
 */

public class DoctoAlumnoWS extends AppCompatActivity implements View.OnClickListener{
    private EditText etTest;
    private EditText etDevolution;
    private EditText etNote;
    private EditText etActivo;
    private EditText etDocument;

    private Button btnSave;
    private Button btnList;
    private DoctoAlumno docto=null;
    final String NAMESPACE =
            "http://ws.utng.edu.mx";
    final SoapSerializationEnvelope envelope =
            new SoapSerializationEnvelope(SoapEnvelope.VER11);
    static String URL =
            "http://172.16.12.204:8080/WSMovie/services/DoctoAlumnoWS";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docto_alumno);
        etTest = (EditText)findViewById(R.id.et_test);
        initComponents();
    }
    private void initComponents(){

        etDevolution = (EditText)findViewById(R.id.et_devolution);
        etNote= (EditText)findViewById(R.id.et_note);
        etActivo= (EditText)findViewById(R.id.et_activo);
        etDocument= (EditText)findViewById(R.id.et_document);

        btnSave = (Button) findViewById(R.id.btn_save_docto);
        btnList = (Button)findViewById(R.id.btn_list_docto);
        btnSave.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== btnSave.getId()){
            try {
                //modificar viene de ListDocto
                if (getIntent().getExtras().getString("accion")
                        .equals("modificar")) {
                    TaskWSUpdate tarea = new TaskWSUpdate();
                    tarea.execute();
                    //limpuiar las cajas de texto
                    clear();
                }

            } catch (Exception e) {
                //Cuando no se haya mandado una accion por defecto es insertar.
                TaskWSInsert tarea = new TaskWSInsert();
                tarea.execute();
                //limpuiar las cajas de texto
                clear();
            }
        }
        if (btnList.getId() == v.getId()) {
            startActivity(new Intent(DoctoAlumnoWS.this, ListDoctoAlumno.class));
        }
    }
    private class TaskWSInsert extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            //eclipse
            final String METHOD_NAME = "addDocto";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            docto = new DoctoAlumno();
            docto.setProperty(0, 0);


            getData();
            Log.i("Info","" +docto);

            PropertyInfo info = new PropertyInfo();
            info.setName("docto");
            info.setValue(docto);
            info.setType(docto.getClass());
            request.addProperty(info);
            envelope.setOutputSoapObject(request);
            ////////////************/
            envelope.addMapping(NAMESPACE, "DoctoAlumno", DoctoAlumno.class);

            /* Para serializar flotantes y otros tipos no cadenas o enteros*/
            MarshalFloat mf = new MarshalFloat();
            mf.register(envelope);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive response =
                        (SoapPrimitive) envelope.getResponse();
                String res = response.toString();
                if (!res.equals("1")) {
                    result = false;
                }

            } catch (Exception e) {
                Log.e("Error ", e.getMessage());
                result = false;
            }


            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {

            if(result){
                Toast.makeText(getApplicationContext(),
                        "Registro exitoso.",
                        Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),
                        "Error al insertar.",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
    private class TaskWSUpdate extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
///de eclipse
            final String METHOD_NAME = "updateDocto";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            docto = new DoctoAlumno();
            docto.setProperty(0, getIntent().getExtras().getString("valor0"));
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("docto");
            info.setValue(docto);
            info.setType(docto.getClass());

            request.addProperty(info);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
/*************/
            envelope.addMapping(NAMESPACE, "DoctoAlumno", docto.getClass());

            MarshalFloat mf = new MarshalFloat();
            mf.register(envelope);

            HttpTransportSE transporte = new HttpTransportSE(URL);

            try {
                transporte.call(SOAP_ACTION,envelope);
                SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                String res = resultado_xml.toString();

                if (!res.equals("1")) {
                    result = false;
                }

            } catch (HttpResponseException e) {
                Log.e("Error HTTP", e.toString());
            } catch (IOException e) {
                Log.e("Error IO", e.toString());
            } catch (XmlPullParserException e) {
                Log.e("Error XmlPullParser", e.toString());
            }


            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), "Actualizado",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error al actualizar",
                        Toast.LENGTH_SHORT).show();

            }
        }
    } private void getData() {
        docto.setProperty(1, etTest.getText().toString());
        Log.i("Caja2", etTest.getText().toString());


        docto.setProperty(2, etDevolution.getText().toString());
        docto.setProperty(3, etNote.getText().toString());
        docto.setProperty(4, etActivo.getText().toString());
        docto.setProperty(5, etDocument.getText().toString());


    }//

    @Override
    protected void onResume() {
        super.onResume();
        Bundle datosRegreso = this.getIntent().getExtras();
        try {

            etTest.setText(datosRegreso.getString("valor1"));
            etDevolution.setText(datosRegreso.getString("valor2"));
            etNote.setText(datosRegreso.getString("valor3"));
            etActivo.setText(datosRegreso.getString("valor4"));
            etDocument.setText(datosRegreso.getString("valor5"));

        } catch (Exception e) {
            Log.e("Error al Recargar", e.toString());
        }
    }

    private void clear(){
        etTest.setText("");
        etDevolution.setText("");
        etNote.setText("");
        etActivo.setText("");
        etDocument.setText("");
    }

}
