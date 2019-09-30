package trainingproject.tridentnets.com.shoppingtask.Fragment;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import trainingproject.tridentnets.com.shoppingtask.Adapter.CartCursorAdapter;
import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
import trainingproject.tridentnets.com.shoppingtask.Loader.CartCursorLoader;
import trainingproject.tridentnets.com.shoppingtask.R;
import trainingproject.tridentnets.com.shoppingtask.activity.CartActivity;
import trainingproject.tridentnets.com.shoppingtask.utils.Keys;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {

    private ListView cartListView;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recycler_cart_view, container, false);
        cartListView = v.findViewById(R.id.recycle_view_cart);
        cartListView.setOnItemClickListener(this);
        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CartCursorLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        CartCursorAdapter adapterObj = new CartCursorAdapter(getActivity(), data, 0);
//        adapterObj.notifyDataSetChanged();

        cartListView.setAdapter(adapterObj);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("state","onresume");
        getActivity().getSupportLoaderManager().initLoader(1, null, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("state","onpause");
        getActivity().getSupportLoaderManager().destroyLoader(1);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("state","onstop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("state","onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("state","onview");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("state","onDetach");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
        Toast.makeText(getActivity(), pos + "", Toast.LENGTH_SHORT).show();

        Cursor localCursor = (Cursor) adapterView.getItemAtPosition(pos);
        Intent i = new Intent(getActivity(), CartActivity.class);
        i.putExtra(Keys.BUNDEL_FROM_PRODUCT, false);
        i.putExtra(Keys.BUNDEL_PRODUCT_ID, localCursor.getString(localCursor.getColumnIndexOrThrow(SqliteDBHelper.PID)));
        Log.d("Postionvaluesonclick", pos + " id" + localCursor.getString(localCursor.getColumnIndexOrThrow(SqliteDBHelper.PID)));
        i.putExtra(Keys.BUNDEL_PRODUCT_NAME, localCursor.getString(localCursor.getColumnIndexOrThrow(SqliteDBHelper.CART_NAME)));
        i.putExtra(Keys.BUNDEL_PRODUCT_DESC, localCursor.getString(localCursor.getColumnIndex(SqliteDBHelper.CART_DESC)));
        byte[] mSaveImg = localCursor.getBlob(localCursor.getColumnIndex(SqliteDBHelper.CART_IMG));
        i.putExtra(Keys.BUNDEL_PRODUCT_Byte_IMG, mSaveImg);
        i.putExtra(Keys.BUNDEL_PRODUCT_PRICE, localCursor.getString(localCursor.getColumnIndexOrThrow(SqliteDBHelper.CART_COST)));
        int qun = Integer.parseInt(localCursor.getString(localCursor.getColumnIndexOrThrow(SqliteDBHelper.CART_QUANTITY)));
        i.putExtra(Keys.BUNDEL_PRODUCT_QUANTITY, qun);
        startActivityForResult(i, 111);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            if (resultCode == RESULT_OK) {
                getActivity().getSupportLoaderManager().restartLoader(1, null, this);

            }
        }
    }
}
