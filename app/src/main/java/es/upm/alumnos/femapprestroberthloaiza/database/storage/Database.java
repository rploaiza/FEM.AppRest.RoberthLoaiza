package es.upm.alumnos.femapprestroberthloaiza.database.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import es.upm.alumnos.femapprestroberthloaiza.database.contract.Licor;
import es.upm.alumnos.femapprestroberthloaiza.database.contract.Rankin;

/**
 * Created by Usuario on 27/10/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_FILE = "licorsService.db";
    private static final int VERSION_NUMBER = 1;

    public Database(Context context) {
        super(context, DATABASE_FILE, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + Licor.licorsTable.TABLE_NAME
                + "( " + Licor.licorsTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_ID + " INTEGER, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_NAME + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_TAGS + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_PRICE_IN_CENTS + " INTEGER, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_PRIMARY_CATEGORY + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_ORIGIN + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_PACKAGE + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_PACKAGE_VOL_MIL + " INTEGER, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_ALCOHOL_CONT + " INTEGER, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_PRODUCER_NAME + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_IMAGE_THUMB_URL + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_VARIETAL + " TEXT, "
                + Licor.licorsTable.COLUMN_NAME_LICORS_STYLE + " TEXT, "
                + " );";

        db.execSQL(createTable);

        createTable = "CREATE TABLE " + Rankin.rankingTable.TABLE_NAME
                + "(" + Rankin.rankingTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  " );";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
