package kr.fatos.tnavi;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.google.firebase.analytics.FirebaseAnalytics;

import biz.fatossdk.fminterface.FMInterface;
import biz.fatossdk.newanavi.ANaviApplication;

public class TNaviApplication extends ANaviApplication
{
    private Context m_Context;
    private FirebaseAnalytics mFirebaseAnalytics;

    //==============================================================================================
    @Override
    public void onCreate()
    {
        m_Context = this;

        FMInterface.initKey(m_Context, "tjQAbwvioXOQdeFZD8HVJQjnyLkl5LlWTZbYH5uNDcA");
        super.onCreate();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
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