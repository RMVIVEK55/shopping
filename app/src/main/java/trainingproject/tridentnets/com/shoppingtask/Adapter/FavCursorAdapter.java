package trainingproject.tridentnets.com.shoppingtask.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.ByteArrayInputStream;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
import trainingproject.tridentnets.com.shoppingtask.R;


public class FavCursorAdapter extends CursorAdapter implements View.OnClickListener {
    // private String pid;
    private ToggleButton tbtnFavProduct;
    private Context localContext;
    Cursor localCursor;
    private SqliteDBHelper sqliteDBHelper;

    public FavCursorAdapter(Context context, Cursor c) {
        super(context, c);
        localContext = context;
        localCursor = c;
        sqliteDBHelper = new SqliteDBHelper(context);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return LayoutInflater.from(context).inflate(R.layout.row_list_fav, parent, false);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtFavId = view.findViewById(R.id.txt_fav_id);
        Log.d("cursor position",cursor.getPosition()+"");
        TextView txtProductName = view.findViewById(R.id.txt_fav_name);
        TextView txtProductCost = view.findViewById(R.id.txt_fav_cost);
        tbtnFavProduct = view.findViewById(R.id.tbtn_fav_ic_fav);
        ImageView imgFavProduct = view.findViewById(R.id.img_fav_product_img);
        String favId = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.PID));
        String favPname = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.FAV_NAME));
        String favPcost = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.FAV_COST));
        //  favPquan = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.FAV_QUANTITY));
        boolean isFav = cursor.getInt(cursor.getColumnIndexOrThrow(SqliteDBHelper.FAV_CHK_BOOL)) > 0;
        ByteArrayInputStream fbaip = new ByteArrayInputStream(cursor.getBlob(cursor.getColumnIndexOrThrow(SqliteDBHelper.FAV_IMG)));
        txtFavId.setTag(cursor.getPosition());
        txtFavId.setText(favId);
        txtProductName.setText(favPname);
        txtProductCost.setText(favPcost);
        tbtnFavProduct.setChecked(isFav);
        Bitmap favBitmap = BitmapFactory.decodeStream(fbaip);
        imgFavProduct.setImageBitmap(favBitmap);
        tbtnFavProduct.setTag(favId);
        tbtnFavProduct.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int v = view.getId();

        switch (v) {
            case R.id.tbtn_fav_ic_fav:

                if (tbtnFavProduct.isChecked()) {
                    //     String id=localCursor.getString(localCursor.getColumnIndex(SqliteDBHelper.PID));
                    Log.d("pid clickid id", ":" + " id user" + view.getTag());
                    tbtnFavProduct.setChecked(false);
                    sqliteDBHelper.delFav(view.getTag().toString(), false);
                    swapCursor(sqliteDBHelper.getFavDetails());

                } else {
                    tbtnFavProduct.setChecked(true);
                }
                break;
        }
    }
}
