package com.stanly.ram;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;


import com.stanly.ram.Activities.MainActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by nizar on 28/06/16.
 */
public class MyApplication  extends Application {



    @Override
    public void onCreate() {

        super.onCreate();


        // The Realm file will be located in Context.getFilesDir() with name "default.realm"
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();

        Realm.setDefaultConfiguration(config);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static MyApplication getApplication(Context context) {
        return (MyApplication) context.getApplicationContext();
    }
    public void setFragment(FragmentActivity fragmentActivity, Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_left, R.anim.slide_out_right)
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment ).commit();
    }

    public void setUpFragment(FragmentActivity fragmentActivity,Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment ).commit();
    }

    public void setFragmentWithBackAnimation(FragmentActivity fragmentActivity, Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment ).commit();
    }

    public void setFragmentWithBackAnimationFromUp(FragmentActivity fragmentActivity, Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom, R.anim.slide_in_bottom, R.anim.slide_out_top)
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment ).commit();
    }
    public void setFragmentWithBackAnimationFromDown(FragmentActivity fragmentActivity, Fragment fm, int layout_id){

        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom)
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment ).commit();
    }
    public void setFragmentWithCustomAnimation(FragmentActivity fragmentActivity, Fragment fm, int layout_id, int[] anims){
        String TAG = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(anims[0], anims[1], anims[2], anims[3])
                .replace(layout_id, fm, TAG).addToBackStack(TAG).commit();
    }
    public void addFragment(FragmentActivity paramFragmentActivity, Fragment paramFragment, int paramInt, String paramString)
    {
        paramFragmentActivity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top, R.anim.slide_in_top, R.anim.slide_out_bottom).add(paramInt, paramFragment, paramString).addToBackStack(paramString).commit();
    }
    public void setFragmentWithoutAnimation(FragmentActivity fragmentActivity, Fragment fm, int layout_id){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment )/*.addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )*/.commit();
    }

    public void refreshFragment(FragmentActivity fragmentActivity,Fragment fm){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction().detach(fm).attach(fm)/*.addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )*/.commit();
    }
    public void setFragmentSharedElements(FragmentActivity fragmentActivity, Fragment fm, int layout_id, View SharedElement, String nameShared){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )
                .addSharedElement(SharedElement, nameShared)
                .commit();
    }
    public void setFragmentMultiSharedElements(FragmentActivity fragmentActivity, Fragment fm, int layout_id, View SharedElement, String nameShared, View SharedElement1, String nameShared1){
        ((MainActivity) fragmentActivity).bodyFragment  = fm.getClass().getSimpleName();

        fragmentActivity.getSupportFragmentManager().beginTransaction()
                .replace(layout_id, fm,  ((MainActivity) fragmentActivity).bodyFragment ).addToBackStack( ((MainActivity) fragmentActivity).bodyFragment )
                .addSharedElement(SharedElement, nameShared)
                .addSharedElement(SharedElement1, nameShared1)
                .commit();
    }
}
   /* private int getFirstTimeRun() {
        SharedPreferences sp = getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = BuildConfig.VERSION_CODE;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        Log.e("currentVersionCode ",""+currentVersionCode);
        Log.e("lastVersionCode ",""+lastVersionCode);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();

        return result;
    }
*/
