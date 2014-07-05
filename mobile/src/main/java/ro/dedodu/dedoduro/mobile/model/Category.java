package ro.dedodu.dedoduro.mobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashMap;

@DatabaseTable(tableName = "category")
public class Category {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Converter {

        public static Category from(HashMap map) {
            Category category = new Category();
            category.setId((Integer) map.get("id"));
            category.setName((String) map.get("name"));
            category.setDescription((String) map.get("description"));

            return category;
        }
    }
}
