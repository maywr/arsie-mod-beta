package xyz.maywr.arsie.impl.util;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.lwjgl.Sys;
import xyz.maywr.arsie.Arsie;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static xyz.maywr.arsie.Arsie.mc;

public class NiggerBlocker {

    public static boolean check() throws IOException {
        /**
        BufferedReader reader = new BufferedReader(new InputStreamReader(new URL("http://checkip.amazonaws.com/").openStream()));
        String ip = reader.readLine();

        reader.close();

        BufferedReader countryReader = new BufferedReader(new InputStreamReader(new URL("http://ip-api.com/json/" + ip + "?fields=country,proxy").openStream()));
        String answer = countryReader.readLine();
        countryReader.close();
        JSONObject object = new JSONObject(answer);
        boolean proxy = object.getBoolean("proxy");
        if(proxy) {
            JOptionPane.showMessageDialog(null, "you cant run arsie unless ur russian nigga", "пшл нах", JOptionPane.ERROR_MESSAGE);
            return true;
        }

        String country = object.getString("country");
        if(country.equalsIgnoreCase("United States")) {
            if (proxy) {
                JOptionPane.showMessageDialog(null, "you cant run arsie unless ur russian nigga", "пшл нах", JOptionPane.ERROR_MESSAGE);
                return true;
            }
        }
        return false; **/
        
        return true;
    }
}
