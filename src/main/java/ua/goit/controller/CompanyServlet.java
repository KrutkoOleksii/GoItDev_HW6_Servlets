package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Company;
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

@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {

    private final BaseRepository<Long,Company> repository;
    private final Gson gson = new Gson();

    public CompanyServlet() {
        repository = Factory.of(Company.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        if (pathInfo==null || "/".equals(pathInfo)) {
            req.setAttribute("companies",repository.findAll());
            req.getRequestDispatcher("/view/companies.jsp").forward(req,resp);
            return;
        }
        String[] split = pathInfo.split("/");
        if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //sendAsJson(resp, repository.findById(Long.parseLong(split[1])));
        List<Company> companies = new ArrayList<>();
        companies.add(repository.findById(Long.parseLong(split[1])).get());
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/view/companies.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Company company = Company.builder()
                .name(req.getParameter("name"))
                .code(req.getParameter("code"))
                .build();
        repository.save(company);
        req.getRequestDispatcher("/index.html").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
        Company company = gson.fromJson(payload, Company.class);
        sendAsJson(resp, repository.save(company));
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

