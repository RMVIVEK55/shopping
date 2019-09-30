//package trainingproject.tridentnets.com.shoppingtask.Adapter;
//
//import android.content.Context;
//
//import android.database.Cursor;
//import android.support.v4.widget.CursorAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import android.widget.TextView;
//
//import trainingproject.tridentnets.com.shoppingtask.Database.SqliteDBHelper;
//import trainingproject.tridentnets.com.shoppingtask.R;
//
///**
// * Created by Admin on 11/10/2017.
// */
//
//public class InsertRecodeCursorAdapter extends CursorAdapter {
//    private Context contxt;
//    private TextView txtName, txtDesc, txtAddress, txtMsg, txtChat;
//
//    public InsertRecodeCursorAdapter(Context context, Cursor c) {
//        super(context, c, 0);
//        contxt = context;
//    }
//
//    @Override
//    public View newView(Context context, Cursor cursor, ViewGroup parent) {
//        return LayoutInflater.from(context).inflate(R.layout.fragment_listfragment, null, false);
//    }
//
//    @Override
//    public void bindView(View view, Context context, Cursor cursor) {
//        txtName = (TextView) view.findViewById(R.id.txtview_name);
//        txtAddress = (TextView) view.findViewById(R.id.txtview_address);
//        txtDesc = (TextView) view.findViewById(R.id.txtview_address);
//        txtMsg = (TextView) view.findViewById(R.id.txtview_msg);
//        txtChat = (TextView) view.findViewById(R.id.txtview_chat);
//        TextView tvid = (TextView) view.findViewById(R.id.txt_id);
//        String id = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.ID));
//        String name = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.PERSON_NAME));
//        String address = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.ADDRESS));
//        String desc = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.DESCRIPTION));
//        String msg = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.MESSAGE));
//        String chat = cursor.getString(cursor.getColumnIndexOrThrow(SqliteDBHelper.CHAT));
//        tvid.setText(id.toString());
//        txtName.setText(name.toString());
//        txtAddress.setText(address.toString());
//        txtDesc.setText(desc.toString());
//        txtMsg.setText(msg.toString());
//        txtChat.setText(chat.toString());
//
//    }
//}
