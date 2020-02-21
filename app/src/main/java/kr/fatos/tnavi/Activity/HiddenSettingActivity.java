package kr.fatos.tnavi.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import biz.fatossdk.fminterface.FMInterface;
import biz.fatossdk.newanavi.ANaviApplication;
import biz.fatossdk.newanavi.base.AMapBaseActivity;
import kr.fatos.tnavi.R;
import kr.fatos.tnavi.Unit.settingItemDetailList;
import kr.fatos.tnavi.Unit.settingItemListAdapter;

public class HiddenSettingActivity extends AMapBaseActivity {
    public static final int SETTING_SIMUL_GPS = 0;
    public static final int SETTING_SERVER = 1;
    public static final int SETTING_MOBILIZER = 2;

    public static final String TAG = "AMAP";
    private Context m_Context = null;
    private Button m_btnBack, m_btnHome;

    private ANaviApplication m_gApp;

    private ListView m_SettingListView;
    private ArrayList<settingItemDetailList> arSettingDessert = new ArrayList<settingItemDetailList>();
    private settingItemListAdapter settingAdapter = null;
    private TextView m_txtTitle;

    static final int[] SETTING_MENU_NAME = new int[]{R.string.string_simul_gps, R.string.string_server, R.string.string_mobilizer};
    static boolean[] SETTING_MENU_NAME_ENABLE = new boolean[]{true, true, true};
    static final String[] SETTING_MENU_DATA_NAME = new String[]{"Off", "Commercial", "Off"};

    public HiddenSettingActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden_setting);

        m_Context = this;
        m_gApp = (ANaviApplication) m_Context.getApplicationContext();

        arSettingDessert.clear();

        for (int i = 0; i < SETTING_MENU_NAME.length; i++)
        {
            settingItemDetailList settingList = new settingItemDetailList();
            settingList.m_strSettingName = getResources().getString(SETTING_MENU_NAME[i]);
            settingList.m_bEnable = SETTING_MENU_NAME_ENABLE[i];
            settingList.m_strSettingDataName = SETTING_MENU_DATA_NAME[i];

            switch(i)
            {
                case SETTING_SIMUL_GPS :
                {
                    if (m_gApp.getAppSettingInfo().m_bSimulGps)
                    {
                        settingList.m_strSettingDataName = "On";
                    }
                    else
                    {
                        settingList.m_strSettingDataName = "Off";
                    }

                    break;
                }

                case SETTING_SERVER :
                {
                    if (m_gApp.getAppSettingInfo().m_nServer == 0)
                    {
                        settingList.m_strSettingDataName = "Develop";
                    }
                    else if(m_gApp.getAppSettingInfo().m_nServer == 1)
                    {
                        settingList.m_strSettingDataName = "Commercial";
                    }

                    break;
                }

                case SETTING_MOBILIZER :
                {
                    if (m_gApp.getAppSettingInfo().m_bMobilizer)
                    {
                        settingList.m_strSettingDataName = "On";
                    }
                    else
                    {
                        settingList.m_strSettingDataName = "Off";
                    }

                    break;
                }
            }

            arSettingDessert.add(i, settingList);

            if(arSettingDessert != null)
            {
                settingAdapter = new settingItemListAdapter(m_Context, arSettingDessert);
            }

            m_SettingListView = findViewById(R.id.list_hidden_setting);
            m_SettingListView.setAdapter(settingAdapter);

            m_SettingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(arSettingDessert.get(position).m_strSettingName.equals(getResources().getString(R.string.string_simul_gps)))
                    {
                        m_gApp.getAppSettingInfo().m_bSimulGps = !m_gApp.getAppSettingInfo().m_bSimulGps;
                        m_gApp.saveSettingInfo(m_Context, m_gApp.getAppSettingInfo());

                        if (m_gApp.getAppSettingInfo().m_bSimulGps) {
                            arSettingDessert.get(position).m_strSettingDataName = "On";
                        }
                        else {
                            arSettingDessert.get(position).m_strSettingDataName = "Off";
                        }

                        settingAdapter.notifyDataSetChanged();
                    }
                    else if(arSettingDessert.get(position).m_strSettingName.equals(getResources().getString(R.string.string_server)))
                    {
                        if(m_gApp.getAppSettingInfo().m_nServer == 0)
                        {
                            m_gApp.getAppSettingInfo().m_nServer = 1;
                            arSettingDessert.get(position).m_strSettingDataName = "Commercial";
                        }
                        else if(m_gApp.getAppSettingInfo().m_nServer == 1)
                        {
                            m_gApp.getAppSettingInfo().m_nServer = 0;
                            arSettingDessert.get(position).m_strSettingDataName = "Develop";
                        }

                        m_gApp.saveSettingInfo(m_Context, m_gApp.getAppSettingInfo());

                        settingAdapter.notifyDataSetChanged();
                        FMInterface.GetInstance().FM_ResetServerInfo();
                        Toast.makeText(m_Context,"재실행 해야 적용 됩니다.", Toast.LENGTH_LONG).show();
                    }
                    else if(arSettingDessert.get(position).m_strSettingName.equals(getResources().getString(R.string.string_mobilizer)))
                    {
                        m_gApp.getAppSettingInfo().m_bMobilizer = !m_gApp.getAppSettingInfo().m_bMobilizer;
                        m_gApp.saveSettingInfo(m_Context, m_gApp.getAppSettingInfo());

                        if (m_gApp.getAppSettingInfo().m_bMobilizer) {
                            arSettingDessert.get(position).m_strSettingDataName = "On";
                        }
                        else {
                            arSettingDessert.get(position).m_strSettingDataName = "Off";
                        }

                        settingAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        m_txtTitle = findViewById(R.id.poi_search_text_view);

        m_btnBack = findViewById(R.id.setting_search_back_btn);
        m_btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
