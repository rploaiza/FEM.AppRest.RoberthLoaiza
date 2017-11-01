package es.upm.alumnos.femapprestroberthloaiza.database.contract;

import android.provider.BaseColumns;

/**
 * Created by Usuario on 1/11/2017.
 */

public class ApiKeyContract {

    public ApiKeyContract() {
    }

    public static class tokenTable implements BaseColumns {
        public final static String TABLE_NAME = "token";
        public final static String COLUMN_NAME_ID = _ID;
        public final static String COLUMN_NAME_TOKEN = "apikey";
    }
}
