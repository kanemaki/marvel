package br.eti.kanemaki.testejson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.MyAdapterRecycler;
import model.OkHttpHandler;
import model.WebHead;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    // revistas JSONArray
    JSONArray banca = null;
    //private static final String URL = "http://i.annihil.us/u/prod/marvel/i/mg/4/b0/52ded58839768/portrait_uncanny.jpg";
    //private static final String URL1 = "http://i.annihil.us/u/prod/marvel/i/mg/c/40/57584aabce22f/portrait_uncanny.jpg";
    //private static final String URL2 = "http://i.annihil.us/u/prod/marvel/i/mg/2/10/52cee2f4eea1d/portrait_uncanny.jpg";
    //private static final String URL3 = "http://i.annihil.us/u/prod/marvel/i/mg/6/80/575847a14cc4c/portrait_uncanny.jpg";


    private static final String TAG_REVISTA = "data";
    private static final String TAG_RESULTS = "results";
    private static final String TAG_ID = "id";
    private static final String TAG_NUM_REVISTA = "issueNumber";
    private static final String TAG_TITULO = "title";
    private static final String TAG_DESCRI = "description";
    private static final String TAG_DATAS = "dates";
    private static final String TAG_DATA_VENDA = "type";
    private static final String TAG_DATA = "date";
    private static final String TAG_PRECOS = "prices";
    private static final String TAG_PRECO_VENDA = "type";
    private static final String TAG_PRECO = "price";
    private static final String TAG_PAGINAS = "pageCount";
    private static final String TAG_CAPAS = "thumbnail";
    private static final String TAG_CAPAS_PATH = "path";

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.lv_default);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<WebHead> listaSP = new ArrayList<>();

        OkHttpHandler handler = new OkHttpHandler();

        String marvelStringJson = "";
        String marvelString = "";
        try {
            marvelStringJson = handler.execute().get();

            Log.d("Response: ", "> " + marvelStringJson);

            if (marvelStringJson != null){
                try {
                    JSONObject jsonObj = new JSONObject(marvelStringJson);

                    // Getting JSON Array node
                    JSONObject hq = jsonObj.getJSONObject(TAG_REVISTA);
                    banca = hq.getJSONArray(TAG_RESULTS);

                    // looping through All HQS
                    for (int i = 0; i < banca.length(); i++) {
                        JSONObject revista = banca.getJSONObject(i);

                        String id = revista.getString(TAG_ID).toString();
                        String numero = revista.getString(TAG_NUM_REVISTA).toString();
                        String titulo = revista.getString(TAG_TITULO).toString();
                        String desc = revista.getString(TAG_DESCRI).toString();
                        String paginas = revista.getString(TAG_PAGINAS).toString();

                        JSONObject img = revista.getJSONObject(TAG_CAPAS);
                        String image =  img.getString(TAG_CAPAS_PATH).toString();

                        JSONArray data = revista.getJSONArray(TAG_DATAS);
                        JSONObject dt = data.getJSONObject(0);
                        String hq_Data =  dt.getString(TAG_DATA).toString();

                        JSONArray preco = revista.getJSONArray(TAG_PRECOS);
                        JSONObject prc = preco.getJSONObject(0);
                        String hq_Prc =  prc.getString(TAG_PRECO).toString();

                        Log.d("Response: ", " <------------------------------> " + i);
                        Log.d("Response: ", " id > " + id);
                        Log.d("Response: ", " numero > " + numero);
                        Log.d("Response: ", " titulo > " + titulo);
                        Log.d("Response: ", " descrição > " + desc);
                        Log.d("Response: ", " paginas > " + paginas);
                        Log.d("Response: ", " imagens > " + image);
                        Log.d("Response: ", " data > " + hq_Data);
                        Log.d("Response: ", " preço > " + hq_Prc);
                        Log.d("Response: ", " <------------------------------> ");

                        listaSP.add(new WebHead("#" + numero, image + "/portrait_fantastic.jpg"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {

        }


        MyAdapterRecycler adpter = new MyAdapterRecycler(listaSP, this);

        mRecyclerView.setAdapter(adpter);

    }

    private void downloadMarvel() {
        OkHttpHandler handler = new OkHttpHandler();

       String marvelStringJson = "";
        String marvelString = "";
        try {
            marvelStringJson = handler.execute().get();

            Log.d("Response: ", "> " + marvelStringJson);

            if (marvelStringJson != null){
                try {
                    JSONObject jsonObj = new JSONObject(marvelStringJson);

                    // Getting JSON Array node
                    JSONObject hq = jsonObj.getJSONObject(TAG_REVISTA);
                    banca = hq.getJSONArray(TAG_RESULTS);

                    // looping through All HQS
                    for (int i = 0; i < banca.length(); i++) {
                        JSONObject revista = banca.getJSONObject(i);

                            String id = revista.getString(TAG_ID).toString();
                            String numero = revista.getString(TAG_NUM_REVISTA).toString();
                            String titulo = revista.getString(TAG_TITULO).toString();
                            String desc = revista.getString(TAG_DESCRI).toString();
                            String paginas = revista.getString(TAG_PAGINAS).toString();

                            JSONObject img = revista.getJSONObject(TAG_CAPAS);
                            String image =  img.getString(TAG_CAPAS_PATH).toString();

                            JSONArray data = revista.getJSONArray(TAG_DATAS);
                            JSONObject dt = data.getJSONObject(0);
                            String hq_Data =  dt.getString(TAG_DATA).toString();

                            JSONArray preco = revista.getJSONArray(TAG_PRECOS);
                            JSONObject prc = preco.getJSONObject(0);
                            String hq_Prc =  prc.getString(TAG_PRECO).toString();

                            Log.d("Response: ", " <------------------------------> " + i);
                            Log.d("Response: ", " id > " + id);
                            Log.d("Response: ", " numero > " + numero);
                            Log.d("Response: ", " titulo > " + titulo);
                            Log.d("Response: ", " descrição > " + desc);
                            Log.d("Response: ", " paginas > " + paginas);
                            Log.d("Response: ", " imagens > " + image);
                            Log.d("Response: ", " data > " + hq_Data);
                            Log.d("Response: ", " preço > " + hq_Prc);
                            Log.d("Response: ", " <------------------------------> ");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {

        }

    }


}

//  Deve conter:  ○ Miniatura da capa da revista (thumbnail); 
// ○ Número da revista (issueNumber); 
// ○ Copyright ao final da listagem (“Data provided by Marvel. © 2014  Marvel”). 
//  Regras da tela de detalhes da revista:   
//  Utilizar os assets propostos de acordo com o herói escolhido  
//  Exibir as  informações completas da revista selecionada; 
//  Deve conter: 
// ○ Título (title); 
// ○ Descrição (description); 
// ○ Data de publicação (dates  > onsaleDate)  
//  Preço (prices  >  printPrice); 
// ○ Quantidade de páginas (pageCount). 