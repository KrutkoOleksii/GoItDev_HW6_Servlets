package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Customer;
import ua.goit.service.BaseService;
import ua.goit.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {

    //private final BaseRepository<Long, Customer> repository;
    private final BaseService<Long,Customer> customerBaseService= new CustomerService();
    private final Gson gson = new Gson();

    public CustomerServlet() {
        //repository = Factory.of(Customer.class);
        //customerBaseService = new CustomerService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = getAction(req);
        String pathInfo = req.getPathInfo();
        String[] split = pathInfo.split("/");
        if (pathInfo==null || "/".equals(pathInfo)) {
            //sendAsJson(resp, repository.findAll());
            req.setAttribute("customers",customerBaseService.readAll(Customer.class));
            req.getRequestDispatcher("/view/customer/customers.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/findCustomer")) {
            req.getRequestDispatcher("/view/customer/findCustomer.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/find")) {
            String name = req.getParameter("name");
            Customer customer = customerBaseService.findByName(Customer.class, name).get();
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/view/customer/customerDetails.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/addCustomer")) {
            req.getRequestDispatcher("/view/customer/saveCustomer.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/deleteCustomer")) {
            req.getRequestDispatcher("/view/customer/customers.jsp").forward(req,resp);
            return;
        }
        else if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String reqPathInfo = req.getPathInfo();
        Customer customer = customerBaseService.findById(Customer.class, Long.parseLong(reqPathInfo.substring(1))).get();
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/view/customer/ccustomerDetails.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Customer customer = Customer.builder()
                .name(req.getParameter("name"))
                .code(req.getParameter("code"))
                .build();
            customerBaseService.createEntity(Customer.class,customer);
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/view/customer/customerDetails.jsp").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
        Customer customer = gson.fromJson(payload, Customer.class);
        sendAsJson(resp, customerBaseService.updateEntity(Customer.class,customer));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String[] split = req.getPathInfo().split("/");
        customerBaseService.deleteEntity(Customer.class,Long.parseLong(split[1]));
    }

    private void sendAsJson(HttpServletResponse resp, Object payload) throws IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String result = gson.toJson(payload);
        writer.print(result );
        writer.flush();
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestPathWithServletPath = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletPath.length());
    }
}
