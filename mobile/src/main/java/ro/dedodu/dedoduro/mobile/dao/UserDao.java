package ro.dedodu.dedoduro.mobile.dao;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import ro.dedodu.dedoduro.mobile.model.User;

public class UserDao extends BaseDaoImpl<User, Integer> {

    protected UserDao(ConnectionSource connectionSource) throws SQLException {
        super(User.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
