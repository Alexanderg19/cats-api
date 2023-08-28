package com.alexcode.cats_app.models;

import io.github.cdimascio.dotenv.Dotenv;

public class CatFav {
    String id;
    String image_id;
    String apiKey = "live_O9nZ3JvBmeW4VlezFGilaPaxEtC2Y4kwz9M1vUARtLzR3bQCGZGD0dT72KEY0Yve";
    public ImageX image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ImageX getImage() {
        return image;
    }

    public void setImage(ImageX image) {
        this.image = image;
    }
}
