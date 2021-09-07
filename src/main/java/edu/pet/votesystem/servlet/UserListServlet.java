package edu.pet.votesystem.servlet;

import edu.pet.votesystem.model.User;
import edu.pet.votesystem.service.UserService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserListServlet", urlPatterns = "/users")
public class UserListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(ctx);
        UserService service = webCtx.getBean(UserService.class);
        List<User> users = service.findUsers();
        users.forEach(u -> System.out.println(u.getUserId() + ", " + u.getUserName() + ", " + u.getUserRole()));

        getServletContext().getRequestDispatcher("/userList.jsp").forward(req, resp);
    }
}
