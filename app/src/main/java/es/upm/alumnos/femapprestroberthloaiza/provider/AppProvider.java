package es.upm.alumnos.femapprestroberthloaiza.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.ResultParce;
import es.upm.alumnos.femapprestroberthloaiza.database.parcelable.ApiKeyParce;
import es.upm.alumnos.femapprestroberthloaiza.database.storage.Database;

/**
 * Created by Usuario on 29/10/2017.
 */

public class AppProvider extends ContentProvider {

    private static final String CLASE = AppProvider.class.getPackage().getName();
    private static final String LICORS_ENTITY = "licors";
    private static final String COMMENT_ENTITY = "comments";
    private static final int ID_LICORS = 1;
    private static final int ID_COMMENT = 2;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(CLASE, LICORS_ENTITY + "/*", ID_LICORS);
        uriMatcher.addURI(CLASE, COMMENT_ENTITY + "/#", ID_COMMENT);
    }

    private APIController apiController;
    private Database databaseStorage;

    @Override
    public boolean onCreate() {
        this.apiController = new APIController();
        this.databaseStorage = new Database(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case ID_LICORS:
                cursor = this.getLicors(uri.getLastPathSegment());
                break;
            case ID_COMMENT:
                cursor = this.getLicorsID(Integer.parseInt(uri.getLastPathSegment()));
                break;
        }
        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case ID_LICORS:
                return "vnd.android.cursor.dir/vnd.miw." + LICORS_ENTITY;
            case ID_COMMENT:
                return "vnd.android.cursor.item/vnd.miw." + COMMENT_ENTITY; //una sola entrada
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long IdComment = -1;

        switch (uriMatcher.match(uri)) {
            case ID_LICORS:
                break;
            case ID_COMMENT:
                if (values.containsKey("comment")) {
                    IdComment = this.databaseStorage.onInsertComment(Integer.parseInt(uri.getLastPathSegment()), (String) values.get("comment"));
                }
                break;
        }

        return ContentUris.withAppendedId(uri, IdComment);
    }

    private Cursor getLicors(String nameProduct) {
        Cursor cursor;
        ArrayList<ResultParce> result = this.databaseStorage.getLicorsByProducerName(nameProduct);

        if (result.size() == 0) {
            ArrayList<ResultParce> licorsAPI = this.getLicorsAPI(this.databaseStorage.getAPIKey(), nameProduct);
            this.onInsertLicors(licorsAPI);
        }

        cursor = this.databaseStorage.getCursorLicorsByProducerName(nameProduct);
        return cursor;
    }

    private ArrayList<ResultParce> getLicorsAPI(ApiKeyParce api_key, String nameProduct) {
        return this.apiController.getLicors(api_key, nameProduct);
    }

    private void onInsertLicors(ArrayList<ResultParce> results) {
        for (ResultParce result : results) {
            this.databaseStorage.onInsertLicors(result.getLicorsId(), result.getName(),
                    result.getTags(), result.getPriceInCents(), result.getPrimaryCategory(),
                    result.getOrigin(), result.getPackageUnitVolumeInMilliliters(),
                    result.getAlcoholContent(), result.getProducerName(), result.getImageThumbUrl(),
                    result.getVarietal(), result.getStyle());
        }
    }

    private Cursor getLicorsID(int ID_LICORS) {
        Cursor cursor = null;

        if (ID_LICORS > 0)
            cursor = this.databaseStorage.getCursorIDLicors(ID_LICORS);

        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
