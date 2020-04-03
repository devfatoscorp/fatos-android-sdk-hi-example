package kr.fatos.tnavi;

import android.content.Context;

import androidx.multidex.MultiDex;

import biz.fatossdk.fminterface.FMInterface;
import biz.fatossdk.newanavi.ANaviApplication;

public class TNaviApplication extends ANaviApplication
{
    private Context m_Context;

    //==============================================================================================
    @Override
    public void onCreate()
    {
        m_Context = this;
        //fatostest - 소스 배포시 주석 제거
        //        "1fff532753643a73f364286b8f362eaa" //tmap
        //        "edfdfggfdhhfdhfdhhh4286b8f362eaa" //cdg
        //        "k2354326sdadfasdfczvxcvfdhgfdhsd" //onemap
        //        "3246fdsgfdg32523dsgavgfdsadg4356" // 해양
        FMInterface.initKey(m_Context, "edfdfggfdhhfdhfdhhh4286b8f362eaa");

        super.onCreate();

        //debug모드 일때는 Crashlytics 사용 안함 - 패브릭
        //        Fabric.with(this, new Crashlytics());
    }

    //==============================================================================================
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);

        m_Context = base;
        MultiDex.install(m_Context);
    }
    //==============================================================================================
}