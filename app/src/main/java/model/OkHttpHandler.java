package model;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by alexandre on 19/06/2016.
 */
public class OkHttpHandler extends AsyncTask<Void, Void, String> {
    private final String MARVEL_URL = "http://gateway.marvel.com:80/v1/public/characters/1009610/comics?ts=1&apikey=bb4470a46d0659a43c566ac6056ed48d&hash=479474cf0a28eac9998960da4d96f06b";

    OkHttpClient httpClient = new OkHttpClient();

    @Override
    protected String doInBackground(Void... params) {

        Request.Builder builder = new Request.Builder();
        builder.url(MARVEL_URL);

        Request request = builder.build();

        try {

            Response response = httpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
        }

        return null;
    }
}
