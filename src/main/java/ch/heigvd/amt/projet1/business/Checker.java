package ch.heigvd.amt.projet1.business;
import ch.heigvd.amt.projet1.domain.Authentification;
public class Checker{
    public Boolean login(String login,String password){
        Authentification auth = new Authentification();
        return auth.isSame(login,password);
    }
    public String register(String login,String password){
        Authentification auth = new Authentification();
        if(auth.isExist(login))
            return "this login is already used";
        else
            auth.setAuth(login,password);
            return "your account has been created";
    }
}