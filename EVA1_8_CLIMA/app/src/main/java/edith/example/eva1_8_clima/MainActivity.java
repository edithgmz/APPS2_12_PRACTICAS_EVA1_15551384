package edith.example.eva1_8_clima;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    ListView lstVwCiudades;
    List<Clima> lstCiudades = new ArrayList<Clima>();
    DetalleFragment dfDetalle;
    FragmentManager fmManager;
    FragmentTransaction ftTrans;
    Bundle bDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        fmManager = getSupportFragmentManager();
        dfDetalle = new DetalleFragment();
        //
        bDatos = new Bundle();
        //
        lstVwCiudades = findViewById(R.id.lstVwCiudades);
        lstVwCiudades.setOnItemClickListener(this);
        //
        ConexionClima ccClimaCiudad = new ConexionClima();
        ccClimaCiudad.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        bDatos.putInt("IMG", lstCiudades.get(i).getImagen_clima());
        bDatos.putString("CD", lstCiudades.get(i).getCiudad());
        bDatos.putString("CLIMA", lstCiudades.get(i).getClima());
        bDatos.putDouble("TEMP", lstCiudades.get(i).getTemp());
        bDatos.putString("DESC", lstCiudades.get(i).getDesc_clima());
        //
        dfDetalle.onMainToFrag(bDatos);
        //
        ftTrans = fmManager.beginTransaction();
        ftTrans.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        ftTrans.add(R.id.fmlFrame, dfDetalle);
        ftTrans.addToBackStack(null);
        ftTrans.commit();
    }

    class ConexionClima extends AsyncTask<Void, Void, String> {
        //Conexión, Recibir los datos como cadena en formato JSON
        @Override
        protected String doInBackground(Void... voids) {
            String sUrl = "https://samples.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10&appid=b6907d289e10d714a6e88b30761fae22";
            String sResu = "";
            //Conexión
            try {
                URL url = new URL(sUrl);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
                if (httpCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //Buffered Reader -> necesita InputStreamReader -> necesita InputStream
                    BufferedReader brDatosJSON = new BufferedReader(new InputStreamReader(
                            httpCon.getInputStream()
                    ));
                    sResu = brDatosJSON.readLine();
                }
            } catch (MalformedURLException e) { //URL
                e.printStackTrace();
            } catch (IOException e) { //openConnection
                e.printStackTrace();
            }
            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")) { //Se recibieron datos
                try {
                    JSONObject jsDatos = new JSONObject(s); //JSON completo
                    JSONArray ajsCiudades = jsDatos.getJSONArray("list"); //Arreglo dentro del JSON
                    for (int i = 0; i < ajsCiudades.length(); i++) {
                        Clima cCiudad = new Clima();
                        JSONObject jsActual = ajsCiudades.getJSONObject(i); //Objeto JSON dentro del arreglo
                        //
                        cCiudad.setCiudad(jsActual.getString("name"));
                        //
                        JSONObject jsMain = jsActual.getJSONObject("main");
                        cCiudad.setTemp(jsMain.getDouble("temp"));
                        //
                        JSONArray ajsClimas = jsActual.getJSONArray("weather");
                        JSONObject jsClima = ajsClimas.getJSONObject(0);
                        cCiudad.setClima(jsClima.getString("main"));
                        cCiudad.setDesc_clima(jsClima.getString("description"));
                        int iId = jsClima.getInt("id");
                        if (iId < 300) { //Tormentas
                            cCiudad.setImagen_clima(R.drawable.thunderstorm);
                        } else if (iId < 400) { //Lluvia ligera
                            cCiudad.setImagen_clima(R.drawable.light_rain);
                        } else if (iId < 600) { //Lluvia intensa
                            cCiudad.setImagen_clima(R.drawable.rainy);
                        } else if (iId < 700) { //Nieve
                            cCiudad.setImagen_clima(R.drawable.snow);
                        } else if (iId < 800) { //Fenómeno Atmosférico
                            cCiudad.setImagen_clima(R.drawable.atmospher);
                        } else if (iId < 801) { //Despejado
                            cCiudad.setImagen_clima(R.drawable.sunny);
                        } else if (iId < 900) { //Nublado
                            cCiudad.setImagen_clima(R.drawable.cloudy);
                        } else {
                            cCiudad.setImagen_clima(R.drawable.tornado);
                        }
                        //
                        lstCiudades.add(cCiudad);
                    }
                    lstVwCiudades.setAdapter(new ClimaAdapter(MainActivity.this, R.layout.layout_clima, lstCiudades));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
