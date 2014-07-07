package ro.dedodu.dedoduro.mobile.dao;

import android.content.Context;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.lang.reflect.InvocationTargetException;

public class DaoFactory<T extends BaseDaoImpl> {

    public T create(Context context, Class<T> clazz) {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            return clazz.getDeclaredConstructor(ConnectionSource.class).newInstance(databaseHelper.getConnectionSource());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
