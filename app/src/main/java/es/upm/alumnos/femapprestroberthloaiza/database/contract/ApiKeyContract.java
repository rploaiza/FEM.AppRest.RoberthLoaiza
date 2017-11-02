package es.upm.alumnos.femapprestroberthloaiza.database.contract;

import android.provider.BaseColumns;

/**
 * Created by Usuario on 1/11/2017.
 */

public class ApiKeyContract {

    public ApiKeyContract() {
    }

    public static class ApiTable implements BaseColumns {
        public final static String TABLE_NAME = "api";
        public final static String COLUMN_NAME_ID = _ID;
        public final static String COLUMN_NAME_API_KEY = "apikey";
    }
}
