package es.upm.alumnos.femapprestroberthloaiza.database.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import es.upm.alumnos.femapprestroberthloaiza.database.contract.ResultContract;
import es.upm.alumnos.femapprestroberthloaiza.database.contract.RankingContract;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.RankingParce;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.ResultParce;

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

        String createTable = "CREATE TABLE " + ResultContract.licorsTable.TABLE_NAME
                + "( " + ResultContract.licorsTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_ID + " INTEGER, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_NAME + " TEXT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_TAGS + " TEXT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_PRICE_IN_CENTS + " INTEGER, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_PRIMARY_CATEGORY + " TEXT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_ORIGIN + " TEXT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_PACKAGE_VOL_MIL + " INTEGER, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_ALCOHOL_CONT + " INTEGER, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_PRODUCER_NAME + " TEXT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_IMAGE_THUMB_URL + " TEXT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_VARIETAL + " TEXT, "
                + ResultContract.licorsTable.COLUMN_NAME_LICORS_STYLE + " TEXT"
                + " );";

        db.execSQL(createTable);

        createTable = "CREATE TABLE " + RankingContract.rankingTable.TABLE_NAME
                + "(" + RankingContract.rankingTable.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RankingContract.rankingTable.COLUMN_NAME_LICORS_ID + " INTEGER, "
                + RankingContract.rankingTable.COLUMN_NAME_RANKING + " INTEGER"
                + " );";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + ResultContract.licorsTable.TABLE_NAME;
        db.execSQL(dropTable);

        dropTable = "DROP TABLE IF EXISTS " + RankingContract.rankingTable.TABLE_NAME;
        db.execSQL(dropTable);
    }

    public long count(String tableName) {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, tableName);
    }

    public long onInsertLicors(int licorsId, String name, String tags, int priceInCents, String primaryCategory,
                               String origin, int packageUnitVolumeInMilliliters, int alcoholContent,
                               String producerName, String imageThumbUrl, String varietal, String style) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_ID, licorsId);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_NAME, name);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_TAGS, tags);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_PRICE_IN_CENTS, priceInCents);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_PRIMARY_CATEGORY, primaryCategory);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_ORIGIN, origin);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_PACKAGE_VOL_MIL, packageUnitVolumeInMilliliters);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_ALCOHOL_CONT, alcoholContent);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_PRODUCER_NAME, producerName);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_IMAGE_THUMB_URL, imageThumbUrl);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_VARIETAL, varietal);
        values.put(ResultContract.licorsTable.COLUMN_NAME_LICORS_STYLE, style);

        return db.insert(ResultContract.licorsTable.TABLE_NAME, null, values);
    }

    public long onInsertRanking(int licorsId, int ranking) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RankingContract.rankingTable.COLUMN_NAME_LICORS_ID, licorsId);
        values.put(RankingContract.rankingTable.COLUMN_NAME_RANKING, ranking);
        return db.insert(RankingContract.rankingTable.TABLE_NAME, null, values);
    }


    public Cursor getCursorIDLicors(int licorsId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String SQLWhere = RankingContract.rankingTable.COLUMN_NAME_LICORS_ID + " = ?";

        return db.query(RankingContract.rankingTable.TABLE_NAME, null,
                SQLWhere, new String[]{Integer.toString(licorsId)}, null, null, null);
    }

    public RankingParce getRankingID(int licorsId) {
        RankingParce rankingParce = null;
        Cursor cursor = this.getCursorIDLicors(licorsId);

        if (cursor.moveToFirst()) {
            rankingParce = new RankingParce(
                    cursor.getInt(cursor.getColumnIndex(RankingContract.rankingTable.COLUMN_NAME_ID)),
                    cursor.getInt(cursor.getColumnIndex(RankingContract.rankingTable.COLUMN_NAME_LICORS_ID)),
                    cursor.getInt(cursor.getColumnIndex(RankingContract.rankingTable.COLUMN_NAME_RANKING))
            );
            cursor.close();
        }
        return rankingParce;
    }

    public Cursor getLicorsByCategoryCursor(String category) {
        String SQLWhere = ResultContract.licorsTable.COLUMN_NAME_LICORS_PRIMARY_CATEGORY + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(ResultContract.licorsTable.TABLE_NAME, null,
                SQLWhere, new String[]{category}, null, null, null);
    }


    public ArrayList<ResultParce> getLicorsByCategory(String category) {

        ArrayList<ResultParce> licors = new ArrayList<>();
        Cursor cursor = this.getLicorsByCategoryCursor(category);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ResultParce licor = new ResultParce(
                        cursor.getInt(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_ID)),
                        cursor.getInt(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_ID)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_NAME)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_TAGS)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_PRICE_IN_CENTS)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_PRIMARY_CATEGORY)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_ORIGIN)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_PACKAGE_VOL_MIL)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_ALCOHOL_CONT)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_PRODUCER_NAME)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_IMAGE_THUMB_URL)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_VARIETAL)),
                        cursor.getString(cursor.getColumnIndex(ResultContract.licorsTable.COLUMN_NAME_LICORS_STYLE))
                );
                licors.add(licor);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return licors;
    }


}
