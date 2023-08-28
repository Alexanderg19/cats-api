package com.alexcode.cats_app.services;

import com.alexcode.cats_app.models.Cat;
import com.alexcode.cats_app.models.CatFav;
import com.google.gson.Gson;
import com.squareup.okhttp.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class CatService {

    private static String BASE_URL = "https://api.thecatapi.com/v1/";
    private static String SEARCH_ENDPOINT = BASE_URL+"images/search";
    private static String FAVORITE_ENDPOINT = BASE_URL+"favourites";
    private static String favoriteMenu = "Opciones: \n"
            + " 1. ver otra imagen \n"
            + " 2. Eliminar Favorito \n"
            + " 3. Volver \n";
    private static String randomCatsMenu = "Opciones: \n"
            + " 1. Ver otra imagen \n"
            + " 2. Favorito \n"
            + " 3. Volver \n";

    public static void seeRandomCats() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(SEARCH_ENDPOINT)
                .get()
                .build();
        Response response = client.newCall(request).execute();

        String resJson = response.body().string();

        resJson = resJson.substring(1, resJson.length());
        resJson = resJson.substring(0, resJson.length()-1);

        Gson gson = new Gson();
        Cat cats = gson.fromJson(resJson, Cat.class);

        Image image = null;
        try{
            URL url = new URL(cats.getUrl());
            image = ImageIO.read(url);

            ImageIcon catBackground = new ImageIcon(image);

            if (catBackground.getIconWidth() > 800){
                Image background = catBackground.getImage();
                Image updateImage = background.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                catBackground = new ImageIcon(updateImage);
            }

            String[] buttons = {"Ver otra imagen", "Favorito", "Volver"};
            String idCat = cats.getId();
            String option = (String) JOptionPane.showInputDialog(null, randomCatsMenu, idCat, JOptionPane.INFORMATION_MESSAGE, catBackground, buttons, buttons[0]);
            int checkOption = -1;

            for (int i =0; i < buttons.length; i++) {
                if (option.equals(buttons[i])){
                    checkOption = i;
                }
            }

            switch (checkOption) {
                case 0:
                    seeRandomCats();
                    break;
                case 1:
                    markCatsAsFavorite(cats);
                    break;
                default:
                    break;
            }

        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void markCatsAsFavorite( Cat cat) {

        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\""+ cat.getId() +"\"\r\n}");
            Request request = new Request.Builder()
                    .url(FAVORITE_ENDPOINT)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", cat.getApiKey())
                    .build();
            Response response = client.newCall(request).execute();
        }catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void seeFavourites(String apikey) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(FAVORITE_ENDPOINT)
                .get()
                .addHeader("x-api-key", apikey)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        String resJson = response.body().string();

        Gson gson = new Gson();
        CatFav[] catsArray = gson.fromJson(resJson, CatFav[].class);

        if (catsArray.length > 0) {
            int min = 1;
            int max = catsArray.length;
            int random = (int) (Math.random() * ((max - min) + 1)) + min;
            int index = random - 1;

            CatFav catFavorite = catsArray[index];

            Image image = null;
            try{
                URL url = new URL(catFavorite.image.getUrl());
                image = ImageIO.read(url);

                ImageIcon catBackground = new ImageIcon(image);

                if (catBackground.getIconWidth() > 800){
                    Image background = catBackground.getImage();
                    Image updateImage = background.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                    catBackground = new ImageIcon(updateImage);
                }

                String[] buttons = {"Ver otra imagen", "Eliminar Favorito", "Volver"};
                String idCatFav = catFavorite.getId();
                String option = (String) JOptionPane.showInputDialog(null, favoriteMenu, idCatFav, JOptionPane.INFORMATION_MESSAGE, catBackground, buttons, buttons[0]);
                int checkOption = -1;

                for (int i =0; i < buttons.length; i++) {
                    if (option.equals(buttons[i])){
                        checkOption = i;
                    }
                }

                switch (checkOption) {
                    case 0:
                        seeFavourites(apikey);
                        break;
                    case 1:
                        deleteFavourite(catFavorite);
                        break;
                    default:
                        break;
                }

            }catch (IOException e){
                System.out.println(e);
            }

        }
    }

    public static void deleteFavourite(CatFav catFav) throws IOException {

        try{
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");
            Request request = new Request.Builder()
                    .url(FAVORITE_ENDPOINT +catFav.getId()+ "")
                    .method("DELETE", body)
                    .addHeader("x-api-key", catFav.getApiKey())
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if(!response.isSuccessful()) {
                response.body().close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
