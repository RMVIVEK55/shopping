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

import java.io.ByteArrayInputStream;

import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
import trainingproject.tridentnets.com.shoppingtask.R;


public class CartCursorAdapter extends CursorAdapter {
    private static final String TAG = CartCursorAdapter.class.getSimpleName();

    private String mPid, mProductName, mProductDesc, mProductPrice, mQuantity;
    private ByteArrayInputStream is;
    private byte[] mSaveImg;

    public CartCursorAdapter(Context context, Cursor c, int f) {
        super(context, c, f);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.row_list_cart_add_fav, parent, false);
    }

    @Override
    public void bindView(View v, final Context context, Cursor cur) {
        TextView txtProductId = v.findViewById(R.id.txt_cart_product_id);
        TextView txtName = v.findViewById(R.id.txt_cart_name);
        mSaveImg = cur.getBlob(cur.getColumnIndex(SqliteDBHelper.CART_IMG));
        TextView txtcost = v.findViewById(R.id.txt_cart_cost);
        ImageView imgSave = v.findViewById(R.id.cart_img);
        TextView txtquantity = v.findViewById(R.id.txt_cart_quantity);
        mProductDesc = cur.getString(cur.getColumnIndex(SqliteDBHelper.CART_DESC));
        mPid = cur.getString(cur.getColumnIndexOrThrow(SqliteDBHelper.PID));
        mQuantity = cur.getString(cur.getColumnIndexOrThrow(SqliteDBHelper.CART_QUANTITY));
        mProductName = cur.getString(cur.getColumnIndexOrThrow(SqliteDBHelper.CART_NAME));
        mProductPrice = cur.getString(cur.getColumnIndexOrThrow(SqliteDBHelper.CART_COST));
        Log.d("current postion cart", cur.getPosition() + "");
        is = new ByteArrayInputStream(mSaveImg);
        txtProductId.setText(mPid);
        txtName.setText(mProductName);
        txtcost.setText(mProductPrice);
        txtquantity.setText("Quantity " + mQuantity);
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        imgSave.setImageBitmap(bitmap);

    }


}
