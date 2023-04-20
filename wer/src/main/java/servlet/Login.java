package servlet;

import model.Usuari;
import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.sql.SQLException;


@WebServlet(name = "login", urlPatterns = {"/login.do"})
public class Login extends HttpServlet {
    private PostService postService;

    public Login() throws ClassNotFoundException {
        super();
        this.postService = new PostService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
        String password = req.getParameter("pass");
        String email = req.getParameter("mail");
        if (this.postService.getUser(req, email, password)) {
            req.setAttribute("listPost", this.postService.listPosts(req));
            getServletContext().getRequestDispatcher("/home.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
*/
        if (this.postService.checkUser(req)) {
            req.setAttribute("postList", this.postService.listPosts(req));
            getServletContext().getRequestDispatcher("/postA.jsp").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}




