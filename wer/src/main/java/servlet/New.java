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
@WebServlet(name = "edit", urlPatterns = {"/edit.do", "/edit"})
public class New extends HttpServlet {
    private PostService postService;

    public New() throws SQLException, ClassNotFoundException {
        super();
        this.postService = new PostService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (this.postService.checkUser(req)) {
            if (req.getParameter("new") != null) {
                postService.newPost(req);
                req.setAttribute("postList", this.postService.listPosts(req));
                getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
            }
        } else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }


}

