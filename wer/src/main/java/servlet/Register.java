package servlet;

import service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "register", urlPatterns = {"/register.do","/register"})
public class Register extends HttpServlet {
    private PostService postService;

    public Register() throws ClassNotFoundException {
        super();
        this.postService = new PostService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("mail")!= "" && request.getParameter("pass")!= "") {
            postService.newUser(request);
            request.setAttribute("postList", this.postService.listPosts(request));
            getServletContext().getRequestDispatcher("/postA.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);        }
    }
}