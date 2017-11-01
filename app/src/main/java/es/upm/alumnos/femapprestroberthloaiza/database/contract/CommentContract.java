package es.upm.alumnos.femapprestroberthloaiza.database.contract;

import android.provider.BaseColumns;

/**
 * Created by Usuario on 27/10/2017.
 */

public class CommentContract {

    private CommentContract() {
    }

    public static class rankingTable implements BaseColumns{
        public final static String TABLE_NAME = "comments";
        public final static String COLUMN_NAME_ID = _ID;
        public final static String COLUMN_NAME_LICORS_ID = "commentId";
        public final static String COLUMN_NAME_COMMENT = "comment";
    }

}
