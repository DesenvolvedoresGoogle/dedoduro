package ro.dedodu.dedoduro.mobile.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import ro.dedodu.dedoduro.mobile.model.Category;

public class CategoryDao extends BaseDaoImpl<Category, Integer> {

    protected CategoryDao(ConnectionSource connectionSource) throws SQLException {
        super(Category.class);
        setConnectionSource(connectionSource);
        initialize();
    }
}
