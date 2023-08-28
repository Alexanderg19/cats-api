package com.alexcode.cats_app.models;

import io.github.cdimascio.dotenv.Dotenv;

public class Cat {
    String id;
    String url;
    String apiKey = "live_O9nZ3JvBmeW4VlezFGilaPaxEtC2Y4kwz9M1vUARtLzR3bQCGZGD0dT72KEY0Yve";
    String image;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
