package edu.pet.votesystem.servlet;

import edu.pet.votesystem.model.Restaurant;
import edu.pet.votesystem.service.RestaurantService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RestaurantListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(ctx);
        RestaurantService service = webCtx.getBean(RestaurantService.class);
        List<Restaurant> restaurants = service.findRestaurants();

        req.setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        req.setAttribute("restaurants", restaurants);

        restaurants.forEach(r -> System.out.println(r.getRestaurantId() + r.getRestaurantName() + r.getDishes().size()));

        // getServletContext().getRequestDispatcher("restaurantList.jsp").forward(req, resp);
        getServletContext().getRequestDispatcher("restaurantListJstl.jsp").forward(req, resp);
    }
}
