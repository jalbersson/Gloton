package onsite.gloton.com.co.gloton.entity;

/**
 * Created by admin on 4/06/17.
 */

public class Gallery {
    private String name;
    private int imageSource;

    public Gallery(String name, int imageSource) {
        this.name = name;
        this.imageSource = imageSource;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
}
