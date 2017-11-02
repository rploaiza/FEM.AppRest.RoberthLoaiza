package es.upm.alumnos.femapprestroberthloaiza.database;

import android.os.AsyncTask;
import android.widget.ListView;

import retrofit2.Response;

/**
 * Created by Usuario on 27/10/2017.
 */

public abstract class DataBaseAsyncTask<U> extends AsyncTask<ListView, Void, Response> {
    private DataBaseActivity dataBaseActivity;

    public DataBaseAsyncTask(DataBaseActivity dataBaseActivity) {
        this.dataBaseActivity = dataBaseActivity;
    }

    public DataBaseActivity getDataBaseActivity() {
        return dataBaseActivity;
    }


    protected abstract void onPreExecute();

    protected abstract Response<U> doInBackground(ListView... ListViews);

    protected abstract void onPostExecute(Response response);
}