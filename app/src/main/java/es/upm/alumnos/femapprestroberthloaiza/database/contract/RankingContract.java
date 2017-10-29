package es.upm.alumnos.femapprestroberthloaiza.database.contract;

import android.provider.BaseColumns;

/**
 * Created by Usuario on 27/10/2017.
 */

public class RankingContract {

    private RankingContract() {
    }

    public static class rankingTable implements BaseColumns{
        public final static String TABLE_NAME = "rankings";
        public final static String COLUMN_NAME_ID = _ID;
        public final static String COLUMN_NAME_LICORS_ID = "licorsId";
        public final static String COLUMN_NAME_RANKING = "ranking";
    }

}
