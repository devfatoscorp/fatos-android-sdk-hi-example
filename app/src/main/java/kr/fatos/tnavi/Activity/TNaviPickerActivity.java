package kr.fatos.tnavi.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

import kr.fatos.tnavi.Code.TNaviActionCode;
import kr.fatos.tnavi.R;
import kr.fatos.tnavi.TNaviMainActivity;

public class TNaviPickerActivity extends Activity {
    TextView textView_Name, textView_Address;

    String strAddress;

    FrameLayout frameLayout;
    RelativeLayout relativeLayout;

    private final static String TAG = "ESENS::" + TNaviPickerActivity.class.getSimpleName();
    double x;
    double y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        float fMargin = getResources().getDimension(R.dimen.marker_popup_margin);

        getWindow().getAttributes().y = Math.round(-fMargin);

        setContentView(R.layout.activity_picker);

        textView_Name = findViewById(R.id.textView_Name);
        textView_Address = findViewById(R.id.textView_Address);

        frameLayout = findViewById(R.id.frameLayout);
        relativeLayout = findViewById(R.id.relativeLayout);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String strAppMode = extras.getString(TNaviActionCode.APP_MODE);

            if(!Objects.equals(strAppMode, TNaviActionCode.APP_MODE_SHOW_MAP)) {
                x = extras.getDouble("XCoord");
                y = extras.getDouble("YCoord");

                String strX = String.format("%.4f", x);
                String strY = String.format("%.4f", y);
                strAddress = extras.getString("Address");

                textView_Name.setText("X : " + strX + ", Y : " + strY);
                textView_Address.setText(strAddress);
            }
        }

        findViewById(R.id.frameLayout).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ( event.getAction() == MotionEvent.ACTION_DOWN )
                    closePopup();

                return false;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//
//        int screenHeight = displayMetrics.heightPixels;
//
//        int nLayoutY = getWindow().getAttributes().y = screenHeight / 2;
//        int nLayoutHeight = frameLayout.getHeight();
//
//        frameLayout.setTranslationY(nLayoutY - nLayoutHeight);
    }

    @Override
    public void setFinishOnTouchOutside(boolean finish) {
        super.setFinishOnTouchOutside(finish);
    }

    private void closePopup()
    {
        ((TNaviMainActivity)TNaviMainActivity.m_Context).setGpsAppMode(TNaviActionCode.GPS_APP_MODE_DEFAULT);
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        closePopup();
    }

    public void onClickRoute(View v) {
        Log.d(TAG,"onClickRoute!");

        Intent intent = new Intent();

        if(strAddress!=null){
            intent.putExtra("strAddr",strAddress);
        }

        ((TNaviMainActivity)TNaviMainActivity.m_Context).setGpsAppMode(TNaviActionCode.GPS_APP_MODE_DEFAULT);

        setResult(RESULT_OK, intent);
        finish();
    }
}
