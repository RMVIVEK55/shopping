//////package trainingproject.tridentnets.com.shoppingtask;
///////**
//////
//////import android.app.Dialog;
//////import android.content.Context;
//////import android.graphics.Color;
//////import android.graphics.drawable.ColorDrawable;
//////import android.os.Bundle;
//////import android.support.annotation.NonNull;
//////import android.support.annotation.Nullable;
//////import android.support.v4.app.DialogFragment;
//////import android.util.DisplayMetrics;
//////import android.view.LayoutInflater;
//////import android.view.View;
//////import android.view.ViewGroup;
//////import android.view.Window;
//////import android.widget.LinearLayout;
//////import android.widget.TextView;
//////import android.widget.Toast;
//////
//////import com.synchroteam.beans.Conge;
//////import com.synchroteam.beans.UnavailabilityBeans;
//////import com.synchroteam.beans.User;
//////import com.synchroteam.dao.Dao;
//////import com.synchroteam.events.ActivityUpdateEvent;
//////import com.synchroteam.synchroteam.SyncoteamNavigationActivity;
//////import com.synchroteam.synchroteam2.R;
//////import com.synchroteam.utils.ClockInOutUtil;
//////import com.synchroteam.utils.Commons;
//////import com.synchroteam.utils.DaoManager;
//////
//////import java.text.SimpleDateFormat;
//////import java.util.Calendar;
//////import java.util.Enumeration;
//////import java.util.Locale;
//////import java.util.Vector;
//////
//////import de.greenrobot.event.EventBus;
//////
///////**
////// * Created by Trident
////// */
//////
//////public class ClockInOutDialog extends DialogFragment implements View.OnClickListener {
//////    private static final String TAG = ClockInOutDialog.class.getSimpleName();
//////    private TextView txtClockInOut, txtJob, txtDrive, txtActivity;
//////    private LinearLayout layClose;
//////    Context context;
//////    private Dao dataAccessObject;
//////    private User user;
//////    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
//////    private Calendar cal;
//////    private static final String KEY_SHOW_CLOCK_IN_OUT = "clockInOut";
//////    boolean isClockInOut;
//////
//////    public static ClockInOutDialog newInstance(boolean clockInOut) {
//////
//////        ClockInOutDialog fragment = new ClockInOutDialog();
//////        Bundle args = new Bundle();
//////        args.putBoolean(KEY_SHOW_CLOCK_IN_OUT, clockInOut);
//////        fragment.setArguments(args);
//////
//////        return fragment;
//////    }
//////
//////    @Nullable
//////    @Override
//////    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//////
//////        View view = inflater.inflate(R.layout.layout_dialog_clock_in_out, container);
//////
//////        dataAccessObject = DaoManager.getInstance();
//////
//////        user = dataAccessObject.getUser();
//////
//////        getArgumentValues();
//////
//////        initializeUI(view);
//////
//////        updateViewForClockInOut();
//////
//////        return view;
//////    }
//////
//////
//////    @Override
//////    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//////        super.onViewCreated(view, savedInstanceState);
//////
//////        context = getActivity();
//////
//////        setCustomTypeface();
//////
//////    }
//////
//////    private void getArgumentValues() {
//////        isClockInOut = getArguments().getBoolean(KEY_SHOW_CLOCK_IN_OUT);
//////    }
//////
//////    private void updateViewForClockInOut() {
//////        if (isClockInOut) {
//////            txtJob.setVisibility(View.GONE);
//////            txtDrive.setVisibility(View.GONE);
//////            txtActivity.setVisibility(View.GONE);
//////        }else{
//////            txtJob.setVisibility(View.VISIBLE);
//////            txtDrive.setVisibility(View.VISIBLE);
//////            txtActivity.setVisibility(View.VISIBLE);
//////        }
//////    }
//////
//////    private void initializeUI(View view) {
//////
//////        cal = Calendar.getInstance();
//////
//////        txtClockInOut = (TextView) view.findViewById(R.id.txt_clock_in_out);
//////        txtJob = (TextView) view.findViewById(R.id.txt_job);
//////        txtDrive = (TextView) view.findViewById(R.id.txt_drive);
//////        txtActivity = (TextView) view.findViewById(R.id.txt_activity);
//////        layClose = (LinearLayout) view.findViewById(R.id.lay_close);
//////        txtClockInOut.setOnClickListener(this);
//////        txtJob.setOnClickListener(this);
//////        txtDrive.setOnClickListener(this);
//////        txtActivity.setOnClickListener(this);
//////        layClose.setOnClickListener(this);
//////
//////    }
//////
//////    private void settingClockInOutButtonLayout(boolean isClockModeAvailable) {
//////        if (isClockModeAvailable) {
////////            settingDialogHeight(true);
//////            txtClockInOut.setVisibility(View.VISIBLE);
////////            txtDrive.setVisibility(View.VISIBLE);
//////            if (!isClockInOut) {
//////                txtDrive.setVisibility(View.VISIBLE);
//////            }
//////            checkClockModeAndSettingLayout();
//////        } else {
//////            txtClockInOut.setVisibility(View.GONE);
//////            txtDrive.setVisibility(View.GONE);
////////            settingDialogHeight(false);
//////        }
//////    }
//////
//////    @Override
//////    public void onDestroy() {
//////        super.onDestroy();
//////    }
//////
//////    /**
//////     * sets the height of the dialog dynamically based on orientation.
//////     */
//////    @Override
//////    public void onResume() {
//////        super.onResume();
//////        settingClockInOutButtonLayout(dataAccessObject.checkIsClockInAvailable(user.getId()));
//////    }
//////
//////    private void checkClockModeAndSettingLayout() {
//////        // checking Clocked In or Out
//////        Conge vectCongeClockIn = checkClockedIn();
//////        if (vectCongeClockIn != null) {
//////            txtClockInOut.setText(getActivity().getResources().getString(R.string.txt_clock_out));
//////        } else {
//////            txtClockInOut.setText(getActivity().getResources().getString(R.string.txt_clock_in));
//////        }
//////        // update UI in Synchroteam navigation Activity
//////        EventBus.getDefault().post(new ActivityUpdateEvent());
//////    }
//////
//////    private void settingDialogHeight(boolean isclockModeLargeDialog) {
//////        if (isclockModeLargeDialog) {
//////            DisplayMetrics metrics = getResources().getDisplayMetrics();
//////            int screenHeight = metrics.heightPixels;
//////            int screenWidth = metrics.widthPixels;
//////            int dialogHeight = 0, dialogWidth = 0;
////////            dialogHeight = (int) (screenHeight * Double.parseDouble(this.getResources().getString(R.string.clock_in_out_height)));
////////            dialogWidth = (int) (screenWidth * Double.parseDouble(this.getResources().getString(R.string.clock_in_out_width)));
//////            if(isClockInOut){
//////                dialogHeight = (int) (screenHeight * Double.parseDouble(this.getResources().getString(R.string.driving_height)));
//////                dialogWidth = (int) (screenWidth * Double.parseDouble(this.getResources().getString(R.string.driving_width)));
//////            }else{
//////                dialogHeight = (int) (screenHeight * Double.parseDouble(this.getResources().getString(R.string.clock_in_out_height)));
//////                dialogWidth = (int) (screenWidth * Double.parseDouble(this.getResources().getString(R.string.clock_in_out_width)));
//////            }
//////
//////            getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
//////        } else {
//////            DisplayMetrics metrics = getResources().getDisplayMetrics();
//////            int screenHeight = metrics.heightPixels;
//////            int screenWidth = metrics.widthPixels;
//////            int dialogHeight = 0, dialogWidth = 0;
//////            dialogHeight = (int) (screenHeight * Double.parseDouble(this.getResources().getString(R.string.clock_in_out_height_small)));
//////            dialogWidth = (int) (screenWidth * Double.parseDouble(this.getResources().getString(R.string.clock_in_out_width)));
//////            getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
//////        }
//////    }
//////
//////    private void setCustomTypeface() {
//////
//////    }
//////
//////    @NonNull
//////    @Override
//////    public Dialog onCreateDialog(Bundle savedInstanceState) {
//////        Dialog dialog = super.onCreateDialog(savedInstanceState);
//////        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//////        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//////        dialog.setCanceledOnTouchOutside(true);
//////        dialog.setCancelable(false);
//////        return dialog;
//////    }
//////
//////    private void showToastMessage(String message) {
//////        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//////    }
//////
//////
//////    @Override
//////    public void onClick(View view) {
//////        switch (view.getId()) {
//////            case R.id.lay_close:
//////                dismiss();
//////                break;
//////            case R.id.txt_clock_in_out:
//////
//////                Conge vectCongeClockIn = checkClockedIn();
//////                if (vectCongeClockIn != null) {
//////                    // clocked in, So we do clocked out
//////                    if (finishClockedInMode()) {
//////                        checkClockModeAndSettingLayout();
//////                        /** cancel the inactivity alarm */
//////                        ClockInOutUtil.cancelAlarmForTimeOut(getActivity());
////////                        showToastMessage(getActivity().getResources().getString(R.string.txt_clock_out));
//////                    }
//////                } else {
//////                    String currentDateStr = sdf.format(cal.getTime());
//////                    // new clocked in entry to T_CONGE
//////                    UnavailabilityBeans clockInActivity = dataAccessObject.getClockInActivity();
//////                    String uniqueID = dataAccessObject.addUnavailabilityAndReturnID(null, clockInActivity.getUnavailabilityID(), 0, currentDateStr, null, "");
//////                    if (uniqueID != null) {
//////                        checkClockModeAndSettingLayout();
//////                        ClockInOutUtil.saveLastClockedInTime(getActivity());
//////                        ClockInOutUtil.startAlarmForTimeOut(getActivity());
////////                        showToastMessage(getActivity().getResources().getString(R.string.txt_clock_in));
//////                    } else {
//////                        showToastMessage(getActivity().getResources().getString(R.string.msg_error));
//////                    }
//////                }
//////                break;
//////            case R.id.txt_job:
//////                ((SyncoteamNavigationActivity) getActivity()).onClickJobInClockInOutDialog();
//////                dismiss();
//////                break;
//////            case R.id.txt_drive:
//////                DrivingOrActivityListDialog.newInstance(Commons.IS_DRIVING).show(getActivity().getSupportFragmentManager(), "");
//////                dismiss();
//////                break;
//////            case R.id.txt_activity:
//////                DrivingOrActivityListDialog.newInstance(Commons.IS_ACTIVITY).show(getActivity().getSupportFragmentManager(), "");
//////                dismiss();
//////                break;
//////        }
//////    }
//////
//////    public Conge checkClockedIn() {
//////        Conge indisp;
//////        Vector<Conge> vectConge = dataAccessObject.getClockIn();
//////        Enumeration<Conge> enindisp = vectConge.elements();
//////        while (enindisp.hasMoreElements()) {
//////            indisp = enindisp.nextElement();
//////            if (indisp.getDtFin() == null) {
//////                return indisp;
//////            }
//////        }
//////        return null;
//////    }
//////
//////    /**
//////     * Finish clock in to Clocked out
//////     */
//////    private boolean finishClockedInMode() {
//////        boolean clockedOut = false;
//////        Vector<Conge> vectConge = dataAccessObject.getClockIn();
//////        Enumeration<Conge> enindisp = vectConge.elements();
//////        String currentDate = sdf.format(cal.getTime());
//////        while (enindisp.hasMoreElements()) {
//////            Conge indisp = enindisp.nextElement();
//////            if (indisp.getDtFin() == null) {
//////                clockedOut = dataAccessObject.updateClockedOutEndTime(indisp.getIdConge(), currentDate);
//////            }
//////        }
//////        return clockedOut;
//////    }
//////
//////}
//private void settingViewpager() {
//
//        int sizeOfImage = 0;
//        if (eventList.size() > 0) {
//
//        if (eventList.size() > 4) {
//
//        sizeOfImage = 4;
//
//        } else {
//
//        sizeOfImage = eventList.size();
//        }
//
//        for (int i = 0; i <= sizeOfImage; i++) {
//        sliderImagePojo = new SliderImagePojo();
//        sliderImagePojo.setImage(eventList.get(i).getImage());
//        ImagesArray.add(sliderImagePojo);
//        }
//
//        mPager.setAdapter(new SlidingImage_Adapter(getContext(), ImagesArray));
//final float density = getResources().getDisplayMetrics().density;
//        NUM_PAGES = sizeOfImage;
//// Auto start of viewpager
//final Handler handler = new Handler();
//final Runnable Update = new Runnable() {
//public void run() {
//        if (currentPage == NUM_PAGES) {
//        currentPage = 0;
//        }
//        mPager.setCurrentItem(currentPage++, true);
//        }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//@Override
//public void run() {
//
//        handler.post(Update);
//        }
//        }, 10000, 10000);
//        }
//
//
//        }