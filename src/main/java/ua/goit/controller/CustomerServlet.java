package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Customer;
import ua.goit.repository.BaseRepository;
import ua.goit.repository.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {

    private final BaseRepository<Long, Customer> repository;
    private final Gson gson = new Gson();

    public CustomerServlet() {
        repository = Factory.of(Customer.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        if (pathInfo==null || "/".equals(pathInfo)) {
            //sendAsJson(resp, repository.findAll());
            req.setAttribute("customers",repository.findAll());
            req.getRequestDispatcher("/view/customer/customers.jsp").forward(req,resp);
            return;
        }
        String[] split = pathInfo.split("/");
        if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //sendAsJson(resp, repository.findById(Long.parseLong(split[1])));
        List<Customer> customers = new ArrayList<>();
        customers.add(repository.findById(Long.parseLong(split[1])).get());
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/view/customer/customers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Customer customer = Customer.builder()
                .name(req.getParameter("name"))
                .code(req.getParameter("code"))
                .build();
        repository.save(customer);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
        Customer customer = gson.fromJson(payload, Customer.class);
        sendAsJson(resp, repository.save(customer));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String[] split = req.getPathInfo().split("/");
        repository.deleteById(Long.parseLong(split[1]));
    }

    private void sendAsJson(HttpServletResponse resp, Object payload) throws IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String result = gson.toJson(payload);
        writer.print(result );
        writer.flush();
    }

}
