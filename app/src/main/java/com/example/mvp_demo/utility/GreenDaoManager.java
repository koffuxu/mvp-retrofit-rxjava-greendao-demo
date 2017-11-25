package com.example.mvp_demo.utility;

import com.example.mvp_demo.MyApplication;
import com.example.mvp_demo.model.DaoMaster;
import com.example.mvp_demo.model.DaoSession;
import org.greenrobot.greendao.database.Database;

/**
 * Created by Steven Sun on 2016/10/21.
 * Email:tyhj_sf@163.com
 */

public class GreenDaoManager {
    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    private static final boolean ENCRYPTED = false;
    /**自定义数据库名称*/
    private static final String DB_NAME="myDEMO-db";
    /**单例*/
    private static GreenDaoManager mInstance;
    /**以一定的模式管理Dao类的数据库对象*/
    private DaoMaster mDaoMaster;
    /**管理制定模式下的所有可用Dao对象*/
    private DaoSession mDaoSession;
    private GreenDaoManager() {
        if (mInstance == null) {
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(MyApplication.getContext(), ENCRYPTED ? (DB_NAME+"-encrypted") : DB_NAME, null);
            Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession();
        }
    }

    public static GreenDaoManager getInstance(){
        if (mInstance==null){
            synchronized (GreenDaoManager.class){
                if (mInstance==null){
                    mInstance=new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoMaster getmDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getmDaoSession() {
        return mDaoSession;
    }

}
