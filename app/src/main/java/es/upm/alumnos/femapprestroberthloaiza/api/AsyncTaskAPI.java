package es.upm.alumnos.femapprestroberthloaiza.api;

import android.os.AsyncTask;
import android.widget.TextView;

import retrofit2.Response;

/**
 * Created by Usuario on 26/10/2017.
 */

public abstract class AsyncTaskAPI<U> extends AsyncTask<TextView, Void, Response> {
    private LicorsActivity licorsActivity;
    private TextView textView;

    public AsyncTaskAPI(LicorsActivity licorsActivity) {
        this.licorsActivity = licorsActivity;
    }

    public LicorsActivity getLicorsActivity() {
        return licorsActivity;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    protected abstract void onPreExecute();

    protected abstract Response<U> doInBackground(TextView... TextViews);

    protected abstract void onPostExecute(Response response);
}
