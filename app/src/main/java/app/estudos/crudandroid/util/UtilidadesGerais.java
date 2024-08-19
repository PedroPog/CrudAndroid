package app.estudos.crudandroid.util;

import java.util.Random;

public class UtilidadesGerais {

    public static String generatePassword(){
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
