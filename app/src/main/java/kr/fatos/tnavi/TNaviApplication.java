package kr.fatos.tnavi;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;

import biz.fatossdk.fminterface.FMInterface;
import biz.fatossdk.newanavi.ANaviApplication;
import io.fabric.sdk.android.Fabric;

public class TNaviApplication extends ANaviApplication {
    private Context m_Context;
    //==============================================================================================
    @Override
    public void onCreate() {
        m_Context = this;
        FMInterface.initKey(m_Context, "API_KEY");

        super.onCreate();

        //debug모드 일때는 Crashlytics 사용 안함
        Fabric.with(this, new Crashlytics());
    }
    //==============================================================================================
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        m_Context = base;
        MultiDex.install(m_Context);
    }
    //==============================================================================================
}