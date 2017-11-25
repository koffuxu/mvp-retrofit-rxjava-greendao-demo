package app/java/com.example.mvp_demo.model.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.mvp_demo.model.entity.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MUsername = new Property(1, String.class, "mUsername", false, "M_USERNAME");
        public final static Property MUserPassword = new Property(2, String.class, "mUserPassword", false, "M_USER_PASSWORD");
        public final static Property MEmailAddress = new Property(3, String.class, "mEmailAddress", false, "M_EMAIL_ADDRESS");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"M_USERNAME\" TEXT NOT NULL ," + // 1: mUsername
                "\"M_USER_PASSWORD\" TEXT," + // 2: mUserPassword
                "\"M_EMAIL_ADDRESS\" TEXT);"); // 3: mEmailAddress
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getMUsername());
 
        String mUserPassword = entity.getMUserPassword();
        if (mUserPassword != null) {
            stmt.bindString(3, mUserPassword);
        }
 
        String mEmailAddress = entity.getMEmailAddress();
        if (mEmailAddress != null) {
            stmt.bindString(4, mEmailAddress);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getMUsername());
 
        String mUserPassword = entity.getMUserPassword();
        if (mUserPassword != null) {
            stmt.bindString(3, mUserPassword);
        }
 
        String mEmailAddress = entity.getMEmailAddress();
        if (mEmailAddress != null) {
            stmt.bindString(4, mEmailAddress);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // mUsername
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // mUserPassword
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // mEmailAddress
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMUsername(cursor.getString(offset + 1));
        entity.setMUserPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMEmailAddress(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
