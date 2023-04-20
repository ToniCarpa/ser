package service;

import dao.Dao;
import model.Post;
import model.Usuari;
import utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostService {
    private Dao dao;

    public PostService() {
        this.dao = new Dao();
    }


    // --------------------------------------------------LOGIN-------------------------------------------------------

    /*
    public Boolean getUser(HttpServletRequest req, String mail, String pass) {
        try {
            Usuari u = dao.getUsuarioByMailPass(mail, pass);
            if (u != null) {
                req.getSession().setAttribute("id", u.getId() + "");
                req.setAttribute("postList", dao.allPostList());
                return true;
            }
        } catch (SQLException e) {
            req.setAttribute("error", Constants.ERROR);
        }
        return false;
    }
    */

    //----------------------------------------
    public boolean checkUser(HttpServletRequest request) {
        String password = request.getParameter("pass");
        String email = request.getParameter("mail"); //.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        try {
            Usuari u = existUser(email, password);
            if (u != null) request.getSession().setAttribute("id", u.getId() + "");
            request.setAttribute("postList", dao.allPostList());
            return true;
        } catch (SQLException e) {
            request.setAttribute("error", Constants.ERROR);
        }
        return false;
    }

    public Usuari existUser(String mail, String pass) throws SQLException {
        if (dao.getUsuarioByMailPass(mail, pass) == null) throw new SQLException();
        System.out.println("Usuario no encontrado");
        return dao.getUsuarioByMailPass(mail, pass);
    }

    // --------------------------------------------------REGSITER-------------------------------------------------------

    public Usuari newUser(HttpServletRequest request) {
        HttpSession respuesta = request.getSession(true);
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String password = request.getParameter("pass");
            String email = request.getParameter("mail");
            String linkdn = request.getParameter("link");
            String gitlab = request.getParameter("git");

            Usuari t = this.dao.getUsuarioByName(name);

            if (t != null) {
                respuesta.setAttribute("El nombre ya està cogido", respuesta);
                throw new SQLException();
            }

            Usuari u = new Usuari(name, password, email, linkdn, gitlab);
            request.getSession().setAttribute("id", u.getId());

            dao.insertUsuario(u);
            return u;
        } catch (NumberFormatException | SQLException ex) {
            request.setAttribute("error", Constants.ERROR);
        }
        return null;
    }

    // --------------------------------------------------HOME-----------------------------------------------------------


    // ALL POSTS
    public ArrayList<Post> listPosts(HttpServletRequest request) {
        try {
            ArrayList<Post> listPost = dao.allPostList();
            request.setAttribute("postList", listPost);
            return listPost;
        } catch (SQLException e) {
            request.setAttribute("error", Constants.ERROR);
        }
        return null;
    }

    // LIKES
    public boolean sumLikes(HttpServletRequest request) {
        try {
            int id = (int) request.getSession().getAttribute("id");
            request.setAttribute("id", id);
            request.setAttribute("postList", dao.allPostList());
            ArrayList<Post> postlist = this.dao.allPostList();
            for (Post o : postlist) {
                dao.likes(o.getId(), o.getLikes());
            }
            return true;
        } catch (SQLException ex) {
            request.setAttribute("error", Constants.ERROR);
        }
        return false;
    }
    // COMMENTS

    // --------------------------------------------------PROFILE--------------------------------------------------------

    // USER POSTLIST
    public ArrayList<Post> listUserPost(HttpServletRequest request) {
        try {
            int id = (int) request.getAttribute("id");

            ArrayList<Post> listUserPost = this.dao.allPostListUser(id);
            request.setAttribute("listUserPost", listUserPost);
            return listUserPost;
        } catch (SQLException e) {
            request.setAttribute("error", Constants.ERROR);
        }
        return null;
    }


    // --------------------------------------------------EDIT-----------------------------------------------------------

    //NEW POST
    public ArrayList<Post> newPost(HttpServletRequest request) {
        String title = new String(request.getParameter("title").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String message = new String(request.getParameter("message").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Part part = request.getPart("");
            dao.creaPost(new Post(dao.getUsuarioById(id), title, message, new Date(System.currentTimeMillis())), part);

            request.getSession().setAttribute("id", id);
            ArrayList<Post> listPost = this.dao.allPostListUser(id);
            request.setAttribute("listPost", listPost);
            return listPost;
        } catch (SQLException e) {
            request.setAttribute("error", Constants.ERROR);
        } catch (ServletException | IOException e) {
            request.setAttribute("error", Constants.ERROR);
        }
        return null;
    }

    // DELETE POST
    public boolean deletePost(HttpServletRequest request) {
        HttpSession respuesta = request.getSession(true);
        try {
            int id = (int) request.getSession().getAttribute("id");
            request.getSession().setAttribute("id", id);
            request.setAttribute("posts", dao.allPostListUser(id));

            ArrayList<Post> postUserList = this.dao.allPostListUser(id);
            for (Post post : postUserList) {
                this.dao.deletePost(post.getId());
            }
            respuesta.setAttribute("Post Borrado", respuesta);
            return true;

        } catch (SQLException e) {
            respuesta.setAttribute("ERROR no BORRADO", respuesta);
        }
        return false;
    }

    // EDIT POST

}
