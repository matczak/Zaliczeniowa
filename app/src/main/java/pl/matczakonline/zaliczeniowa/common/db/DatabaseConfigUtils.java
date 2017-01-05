package pl.matczakonline.zaliczeniowa.common.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by michnik on 03.01.2017.
 */

public class DatabaseConfigUtils extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class[] {Todo.class, User.class};

    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt", classes);
    }
}
