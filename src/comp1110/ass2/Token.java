package comp1110.ass2;
/**
This class generates the object Token which gives control to a player to play.
 @author Ganaraj Rao
 */
public class Token {
    private static int token =0;


    public void setToken(int token) {
        this.token = token;
    }

    public static int getToken() {
        return token;
    }
}
