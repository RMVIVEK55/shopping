package trainingproject.tridentnets.com.shoppingtask.Fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import trainingproject.tridentnets.com.shoppingtask.Adapter.FavCursorAdapter;
import trainingproject.tridentnets.com.shoppingtask.Loader.FavLoader;
import trainingproject.tridentnets.com.shoppingtask.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView list;

    public FavouriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_listview_fav, container, false);
        list =  v.findViewById(R.id.list_fav);
        getActivity().getSupportLoaderManager().initLoader(2, null, this);
        return v;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //return new PersonCursorLoader(getActivity());
        return new FavLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        FavCursorAdapter favCursorAdapter = new FavCursorAdapter(getActivity(), data);
        list.setAdapter(favCursorAdapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getSupportLoaderManager().restartLoader(1,null,this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getSupportLoaderManager().destroyLoader(1);
    }
}
