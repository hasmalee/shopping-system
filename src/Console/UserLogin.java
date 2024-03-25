package Console;

public class UserLogin {
    private String _userName;
    private String _password;

    public void SetUserName(String userName){
        _userName = userName;
    }
    public void SetPassword(String password){
        _password = password;
    }

    public String GetUserName(){
        return _userName;
    }
    public String GetPassword(){
        return _password;
    }
}
