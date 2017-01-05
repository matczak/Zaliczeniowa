package pl.matczakonline.zaliczeniowa.common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import pl.matczakonline.zaliczeniowa.R;


/**
 * Created by michnik on 03.01.2017.
 */


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 3;

    private Dao<Todo, Integer> todoDao = null;
    private Dao<User, Integer> userDao = null;
    private RuntimeExceptionDao<Todo, Integer> todoRuntimeDao = null;
    private RuntimeExceptionDao<User, Integer> userRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Todo.class);
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Todo.class, true);
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<Todo, Integer> getTodoDao() throws SQLException {
        if (todoDao == null) {
            todoDao = getDao(Todo.class);
        }
        return todoDao;
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }

    public RuntimeExceptionDao<Todo, Integer> getTodoRuntimeExceptionDao() {
        if (todoRuntimeDao == null) {
            todoRuntimeDao = getRuntimeExceptionDao(Todo.class);
        }
        return todoRuntimeDao;
    }

    public RuntimeExceptionDao<User, Integer> getUserRuntimeExceptionDao() {
        if (userRuntimeDao == null) {
            userRuntimeDao = getRuntimeExceptionDao(User.class);
        }
        return userRuntimeDao;
    }
}
