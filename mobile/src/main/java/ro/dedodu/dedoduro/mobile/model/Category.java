package ro.dedodu.dedoduro.mobile.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashMap;

import ro.dedodu.dedoduro.mobile.R;

@DatabaseTable(tableName = "category")
public class Category {

    @DatabaseField(id = true)
    private Integer id;

    @DatabaseField
    private String name;

    @DatabaseField
    private String img;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int imageResource() {
        return Image.getImageResource(getImg());
    }

    public int markerResource() {
        return Image.getMarkerResource(getImg());
    }

    private enum Image {
        VIOLENCE("violence", R.drawable.violence, R.drawable.violence_p),
        EDUCATION("education", R.drawable.education, R.drawable.education_p),
        COMPANY("company", R.drawable.company, R.drawable.company_p),
        SERVICE("service", R.drawable.service, R.drawable.service_p),
        TRACK("track", R.drawable.track, R.drawable.track_p),
        STREET("street", R.drawable.street, R.drawable.street_p);

        private String value;
        private int resource;
        private int markerResource;

        private Image(String value, int resource, int markerResource) {
            this.value = value;
            this.resource = resource;
            this.markerResource = markerResource;
        }

        public static int getImageResource(String type) {
            int resourceId = 0;
            for (Image image : values()) {
                if (image.value.equals(type)) {
                    resourceId = image.resource;
                }
            }

            return resourceId;
        }

        public static int getMarkerResource(String type) {
            int resourceId = 0;
            for (Image image : values()) {
                if (image.value.equals(type)) {
                    resourceId = image.markerResource;
                }
            }

            return resourceId;
        }
    }

    public static class Converter {

        public static Category from(HashMap map) {
            Category category = new Category();
            category.setId((Integer) map.get("id"));
            category.setName((String) map.get("name"));
            category.setImg((String) map.get("img"));

            return category;
        }
    }
}
