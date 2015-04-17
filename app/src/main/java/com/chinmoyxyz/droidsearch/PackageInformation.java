package com.chinmoyxyz.droidsearch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chinmoy on 16/4/15.
 */
public class PackageInformation{

    private Context mContext;

    public  PackageInformation(Context context){
        mContext=context;
    }






    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    public ArrayList<InfoObject> getInstalledApps(boolean getSysPackages) {
        ArrayList<InfoObject> res = new ArrayList<InfoObject>();
        List<PackageInfo> packs = mContext.getPackageManager().getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue ;
            }
            InfoObject newInfo = new InfoObject();
            newInfo.setAppname(p.applicationInfo.loadLabel(mContext.getPackageManager()).toString());
            newInfo.setPname(p.packageName);
            newInfo.setVersionName(p.versionName);
            newInfo.setVersionCode(p.versionCode);
            newInfo.setIcon(p.applicationInfo.loadIcon(mContext.getPackageManager()));
            newInfo.setIntent(mContext.getPackageManager().getLaunchIntentForPackage(p.packageName));
            res.add(newInfo);
        }
        return res;
    }


}