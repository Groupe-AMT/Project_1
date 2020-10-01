package ch.heigvd.amt.projet1.domain;

import javax.imageio.spi.ServiceRegistry;
import java.util.HashMap;
import java.util.Map;

public class Authentification {
    private static Map<String,String> auth = new HashMap<>();
    static {
        auth.put("laure","dinateur");
        auth.put("pierre","kiroul");
        auth.put("jean","nemar");
    }
    public boolean isExist(String login){
        return auth.containsKey(login);
    }
    public boolean isSame(String login, String password) {
        if(!auth.containsKey(login))
        return false;
        if (auth.get(login).equals(password))
            return true;
        return false;
    }

    public boolean setAuth(String login,String password) {
        if(auth.containsKey(login))
            return false;
        auth.put(login,password);
        return true;
    }
}
