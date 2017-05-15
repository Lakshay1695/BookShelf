package es.esy.raghavwahi.bookshelf;


import android.net.Uri;

public class Util {

    final static String URI = "http://www.raghavwahi.esy.es/bookshelf";

    public  static  final  String PREFS_NAME ="user_Prefs";

    public  static  final  String KEY_NAME ="user_Prefs";
    public  static  final  String KEY_PHONE="user_Prefs";
    public  static  final  String KEY_EMAIL ="user_Prefs";
    public  static  final  String KEY_PASSWORD ="user_Prefs";

    //URL
    public static final String INSERT_USER_PHP = URI+"/insert.php";

    //Request
    public static final int REQUEST_IMAGE_CAPTURE = 51;
    public static final int REQUEST_IMAGE_CROP = 215;

    //URI
    public static Uri PHOTO_URI = null;
}
