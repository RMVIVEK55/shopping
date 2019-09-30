package trainingproject.tridentnets.com.shoppingtask.Loader;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;


public class PersonCursorLoader extends CursorLoader {
    private SqliteDBHelper sqliteDBHelper;

    public PersonCursorLoader(Context context) {
        super(context);
        sqliteDBHelper = new SqliteDBHelper(context);
    }

    @Override
    public Cursor loadInBackground() {
        return sqliteDBHelper.getFavDetails();
    }
}
