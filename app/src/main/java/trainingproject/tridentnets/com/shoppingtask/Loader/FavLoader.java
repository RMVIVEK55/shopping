package trainingproject.tridentnets.com.shoppingtask.Loader;

import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;


public class FavLoader extends CursorLoader {
    private SqliteDBHelper dbHelper;

    public FavLoader(Context context) {
        super(context);
        dbHelper = new SqliteDBHelper(getContext());

    }

    @Override
    public Cursor loadInBackground() {
        return dbHelper.getFavDetails();
    }
}
