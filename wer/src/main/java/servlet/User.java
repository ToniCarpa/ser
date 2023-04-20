package servlet;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)

@WebServlet(name = "user", urlPatterns = {"/user.do"})
public class User extends HttpServlet {
    private PostService postService;

    public User() throws ClassNotFoundException {
        super();
        this.postService = new PostService();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if (this.postService.checkUser(req)) {
            req.setAttribute("listUserPost", this.postService.listUserPost(req));
            getServletContext().getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
            if (req.getParameter("edit") != null) {
                getServletContext().getRequestDispatcher("/jsp/edit.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}