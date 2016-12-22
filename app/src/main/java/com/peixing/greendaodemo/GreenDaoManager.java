package com.peixing.greendaodemo;

import com.peixing.greendaodemo.gen.DaoMaster;
import com.peixing.greendaodemo.gen.DaoSession;

/**
 * Created by peixing on 2016/12/22.
 */

public class GreenDaoManager {
    private static GreenDaoManager mInstance;
    private DaoMaster mDaomaster;
    private DaoSession mDaoSession;


    public GreenDaoManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(MyApplication.myApplication, "user.db", null);
            mDaomaster = new DaoMaster(devOpenHelper.getWritableDatabase());
            mDaoSession = mDaomaster.newSession();
        }
    }


    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getmMaster() {
        return mDaomaster;
    }

    public DaoSession getmSession() {
        return mDaoSession;
    }


    public DaoSession getNewSession() {
        mDaoSession = mDaomaster.newSession();
        return mDaoSession;
    }
}
