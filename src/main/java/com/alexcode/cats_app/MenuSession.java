package com.alexcode.cats_app;

import com.alexcode.cats_app.models.Cat;
import com.alexcode.cats_app.services.CatService;

import javax.swing.*;
import java.io.IOException;

public class MenuSession {

    public static void main(String[] args) throws IOException {
        int menuOption = -1;
        String[] buttons = {
                "1. See Cats",
                "2. Ver favoritos",
                "3. Exit"
        };

        do{
            String option = (String) JOptionPane.showInputDialog(null, "Cats Java", "Main Menu",
                    JOptionPane.INFORMATION_MESSAGE, null, buttons, buttons[0]);

            for (int i =0; i < buttons.length; i++) {
                if (option.equals(buttons[i])){
                    menuOption = i;
                }
            }

            switch (menuOption){
                case 0:
                    CatService.seeRandomCats();
                    break;
                case 1:
                    CatService.seeFavourites("live_O9nZ3JvBmeW4VlezFGilaPaxEtC2Y4kwz9M1vUARtLzR3bQCGZGD0dT72KEY0Yve");
                default:
                    break;
            }
        }while(menuOption !=2);
    }

}
