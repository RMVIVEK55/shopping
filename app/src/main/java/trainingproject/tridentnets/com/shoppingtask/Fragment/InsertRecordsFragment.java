//package trainingproject.tridentnets.com.shoppingtask.Fragment;
//
//
//import android.database.Cursor;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.LoaderManager;
//import android.support.v4.content.Loader;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import trainingproject.tridentnets.com.shoppingtask.Adapter.InsertRecodeCursorAdapter;
//import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
//import trainingproject.tridentnets.com.shoppingtask.R;
//import trainingproject.tridentnets.com.shoppingtask.Loader.PersonCursorLoader;
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class InsertRecordsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
//    EditText editName, editAddress, editMsg, editDesc, editChat;
//
//    private Button submit, show;
//    private Cursor c;
//    private ListView listView;
//    private SqliteDBHelper sqldb;
//    private String name, address, msg, desc, chat, getstr;
//    private String[] aname = {"arun", "bala", "ravi", "bala", "ravi"};
//    private String[] aaddress = {"vellore", "chennai", "vellore", "chennai", "arcot"};
//    private String[] amsg = {"1", "2", "3", "4", "5"};
//    private String[] adesc = {"i like IOS", "i like android", "i like IOS", "i like android", "i like android"};
//    private String[] achat = {"1", "2", "3", "4", "5"};
//    InsertRecodeCursorAdapter cursorAdapterClass;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.row_list_data, container, false);
//        listView = (ListView) v.findViewById(R.id.list_view);
////       submit = (Button) v.findViewById(R.id.but_submit);
//        show = (Button) v.findViewById(R.id.showdata);
//        sqldb = new SqliteDBHelper(getActivity());
//
//        getActivity().getSupportLoaderManager().initLoader(1, null, this);
//        new SubmitData(aname, adesc, aaddress, amsg, achat).execute();
//          return v;
//    }
//
//    @Override
//    public Loader onCreateLoader(int id, Bundle args) {
//
//        return new PersonCursorLoader(getActivity());
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        cursorAdapterClass = new InsertRecodeCursorAdapter(getActivity(), data);
//        listView.setAdapter(cursorAdapterClass);
//    }
//
//
//    @Override
//    public void onLoaderReset(Loader loader) {
//    }
//
//    class SubmitData extends AsyncTask<String, Void, Boolean> {
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//        }
//
//        String aname[], adesc[], aaddrss[], amsg[], achat[];
//
//        SubmitData(String[] name, String[] desc, String[] address, String[] msg, String[] chat) {
//            aname = name;
//            adesc = desc;
//            aaddress = address;
//            amsg = msg;
//            achat = chat;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Boolean b) {
//            super.onPostExecute(b);
//            if (b) {
//                Toast.makeText(getActivity(), "Database successfully added", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getActivity(), "can not added row", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        protected Boolean doInBackground(String... p) {
//            boolean b = sqldb.insertData(aname, adesc, aaddress, amsg, achat);
//            return b;
//        }
//    }
//}
