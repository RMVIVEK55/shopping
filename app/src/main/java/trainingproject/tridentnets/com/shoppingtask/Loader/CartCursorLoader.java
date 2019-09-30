package trainingproject.tridentnets.com.shoppingtask.Loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;


public class CartCursorLoader extends CursorLoader {
    private SqliteDBHelper dbHelper;

    public CartCursorLoader(Context context) {
        super(context);
        dbHelper = new SqliteDBHelper(getContext());
    }

    @Override
    public Cursor loadInBackground() {
        return dbHelper.getCartDetails();
    }
}
