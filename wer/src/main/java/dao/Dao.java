package dao;

import model.Post;
import model.Usuari;
import utils.Constants;
import utils.Errors;
import utils.File;

import javax.servlet.http.Part;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;

import static utils.Constants.DB_DRIVER_CONNECTION;
import static utils.Constants.ERROR;

public class Dao {
    private Connection conn;

    private String url, user, pass;

    public void conect() throws SQLException {
        try {
            Class.forName(DB_DRIVER_CONNECTION);
            url = Constants.DB_URL_CONNECTION;
            user = Constants.DB_USER_CONNECTION;
            pass = Constants.DB_PASS_CONNECTION;
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            System.out.println(Constants.ERROR);
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Error al Cerrar : " + e);
        }
    }

    // --------------------------------------------------USUARIO--------------------------------------------------------

    // INSERT USER
    public void insertUsuario(Usuari usuari) throws SQLException {
        conect();
        try (PreparedStatement ps = conn.prepareStatement(Constants.SQL_INSERT_USER)) {
            ps.setString(1, usuari.getUsuari());
            ps.setString(2, usuari.getPassword());
            ps.setString(3, usuari.getEmail());
            ps.setString(4, usuari.getLinkedin());
            ps.setString(5, usuari.getGitlab());
            ps.execute();
        }
        conn.commit();
        close();
    }

    // DELETE USER
    public void deleteUsuario(Usuari usuario) throws SQLException { //PERFIL
        conect();
        try (PreparedStatement ps = conn.prepareStatement(Constants.SQL_DELETE_USER)) {
            ps.setInt(1, usuario.getId());
            ps.execute();
        }
        conn.commit();
        close();
    }

    // UPDATE USER
    /*
    public void updateUsuario(Usuari usuario) throws SQLException { //PERFIL
        jdbc.conect();
        try (PreparedStatement ps = jdbc.conn.prepareStatement(Constants.SQL_UPDATE_USER)) {
            ps.setString(1, usuario.getUsuari());
            ps.setString(2, usuario.getPassword());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getLinkedin());
            ps.setString(5, usuario.getGitlab());
            ps.execute();
        }
        jdbc.conn.commit();
        jdbc.close();
    }
    */

    //USERBYNAME

    public Usuari getUsuarioByName(String name) throws SQLException { // ID HIDDEN
        conect();
        Usuari u = null;
        try (PreparedStatement ps = conn.prepareStatement(Constants.SQL_SELECT_USERBYNAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    u = new Usuari();
                    u.setId(rs.getInt("id"));
                    u.setUsuari(rs.getString("usuari"));
                    u.setPassword(rs.getString("password"));
                    u.setEmail(rs.getString("email"));
                    u.setLinkedin(rs.getString("linkedin"));
                    u.setGitlab(rs.getString("gitlab"));
                }
            }
        }
        close();
        return u;
    }

    // SELECT USUER BY ID
    public Usuari getUsuarioById(int id) throws SQLException { // ID HIDDEN
        conect();
        Usuari u = null;
        try (PreparedStatement ps = conn.prepareStatement(Constants.SQL_SELECT_USERBYID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    u = new Usuari();
                    u.setId(rs.getInt("id"));
                    u.setUsuari(rs.getString("usuari"));
                    u.setPassword(rs.getString("password"));
                    u.setEmail(rs.getString("email"));
                    u.setLinkedin(rs.getString("linkedin"));
                    u.setGitlab(rs.getString("gitlab"));
                }
            }
        }
        close();
        return u;
    }

    // SELECT USUER BY MAIL & PASWD
    public Usuari getUsuarioByMailPass(String mail, String pass) throws SQLException {
        Usuari u = null;
        conect();
        try (PreparedStatement ps = conn.prepareStatement(Constants.SQL_SELECT_USER_BYPASSMAIL)) {
            ps.setString(1, mail);
            ps.setString(2, pass);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    u = new Usuari(rs.getInt("id"),
                            rs.getString("usuari"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("linkedin"),
                            rs.getString("gitlab"));
                }
            }
        }
        close();
        return u;
    }

    // SELECT ALLUSERS
    public ArrayList<Usuari> allUsuariosList() throws SQLException {
        ArrayList<Usuari> listUsuarios = new ArrayList<>();
        Usuari u = null;
        conect();
        try (PreparedStatement pre = conn.prepareStatement(Constants.SQL_SELECT_ALLUSERS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    listUsuarios.add(new Usuari(rs.getInt("id"),
                            rs.getString("usuari"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("linkedin"),
                            rs.getString("gitlab")));
                }
            }
        }
        close();
        return listUsuarios;
    }


    // --------------------------------------------------POSTS----------------------------------------------------------

    // CREATE/INSERT POST
    public void creaPost(Post post, Part part) throws SQLException {
        conect();

        try (PreparedStatement ps = conn.prepareStatement(Constants.SQL_INSERT_POST)) {
            ps.setInt(1, post.getUsuari().getId());
            ps.setString(2, post.getTitle());
            ps.setBytes(3, File.convertImage(part));
            ps.setString(4, post.getMessage());
            ps.setDate(5, post.getDate());

            ps.execute();
        }
        conn.commit();
        close();
    }

    // DELETE POST
    public void deletePost(int id) throws SQLException {
        conect();
        try (PreparedStatement ps = conn.prepareStatement(Constants.SQL_DELETE_POST)) {
            ps.setInt(1, id);
            ps.execute();
        }
        conn.commit();
        close();
    }


    // SELECT ALL POSTS
    public ArrayList<Post> allPostList() throws SQLException {
        conect();
        ArrayList<Post> listAllPosts = new ArrayList<>();
        try (PreparedStatement pre = conn.prepareStatement(Constants.SQL_SELECT_ALL_POSTS)) {
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post(rs.getInt("id"),
                            getUsuarioById(rs.getInt("id_usuari")),
                            rs.getString("title"),
                            rs.getString("message"),
                            rs.getInt("likes"),
                            Date.valueOf(rs.getString("dat")));

                    if (rs.getBytes("image") != null) {
                        post.setImage(Base64.getEncoder().encodeToString(rs.getBytes("image")));
                    }
                    listAllPosts.add(post);
                }
            }
        }
        close();
        return listAllPosts;
    }

    public ArrayList<Post> allPostListUser(int id) throws SQLException {
        conect();
        ArrayList<Post> listAllUsersPosts = new ArrayList<>();
        try (PreparedStatement pre = conn.prepareStatement(Constants.SQL_SELECT_USER_POSTS)) {
            pre.setInt(1, id);
            try (ResultSet rs = pre.executeQuery()) {
                while (rs.next()) {
                    for (Post p : listAllUsersPosts) {
                        Post post = new Post(rs.getInt("id"),
                                getUsuarioById(rs.getInt("id_usuari")),
                                rs.getString("title"),
                                rs.getString("message"),
                                rs.getInt("likes"),
                                Date.valueOf(rs.getString("dat")));

                        if (rs.getBytes("image") != null) {
                            post.setImage(Base64.getEncoder().encodeToString(rs.getBytes("image")));
                        }
                        listAllUsersPosts.add(post);
                    }
                }
            }
        }
        close();
        return listAllUsersPosts;
    }


    public void likes(int id, int likes) throws SQLException {
        conect();
        try (PreparedStatement ps = conn.prepareStatement(Constants.INSERT_LIKE)) {
            ps.setInt(1, likes + 1);
            ps.setInt(2, id);
            ps.execute();
        }
        conn.commit();
        close();
    }
}