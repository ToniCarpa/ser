package utils;

public class Constants {
    public static final String DB_USER_CONNECTION = "root";
    public static final String DB_PASS_CONNECTION = "root";
    public static final String DB_DRIVER_CONNECTION = "com.mysql.cj.jdbc.Driver";
    public static final String SCHEMA_NAME = "m06uf4servlets";
    public static final String DB_URL_CONNECTION = "jdbc:mysql://localhost:3306/m06uf4servlets?useUnicode=true&serverTimezone=UTC&useSSL=false";

    // POSTS
    public static final String SQL_INSERT_POST =
             "INSERT into POST (id_usuari, title, message, image, likes, dat) values (?,?,?,?,0,now())";
    public static final String SQL_DELETE_POST =
            "DELETE FROM POST WHERE id = ?;";
    public static final String SQL_SELECT_ALL_POSTS =
            "SELECT * FROM post p INNER JOIN usuari u ON u.id=p.id_usuari ORDER BY dat DESC;";

    public static final String SQL_SELECT_USER_POSTS =
            "SELECT * FROM post WHERE id_usuari = ? ORDER BY dat DESC;";

    // USUARIO
    public static final String SQL_INSERT_USER =
            "INSERT INTO usuari (usuari, password, email, linkedin, gitlab) VALUES (?, ?, ?, ?, ?);";
    public static final String SQL_DELETE_USER =
            "DELETE USUARI WHERE id =?;";
    public static final String SQL_SELECT_USERBYID =
            "SELECT * FROM usuari WHERE id=?;";
    public static final String SQL_SELECT_USERBYNAME =
            "SELECT * FROM usuari WHERE usuari=?;";
    public static final String SQL_SELECT_USER_BYPASSMAIL =
            "SELECT * FROM usuari WHERE email = ? AND password = ?;";;
    public static final String SQL_SELECT_ALLUSERS =
            "SELECT * FROM usuari;";
    public static final String INSERT_LIKE =
            "UPDATE post SET likes =? WHERE id =?;";
    //ERROR

    public static final String ERROR = "Error, NO DATA";


}