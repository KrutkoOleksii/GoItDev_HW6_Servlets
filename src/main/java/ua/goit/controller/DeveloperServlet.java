package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Company;
import ua.goit.model.Developer;
import ua.goit.repository.BaseRepository;
import ua.goit.repository.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/developer/*")
public class DeveloperServlet extends HttpServlet {

    private final BaseRepository<Long, Developer> repository;
    private final BaseRepository<Long, Company> repositoryCompany;
    private final Gson gson = new Gson();

    public DeveloperServlet() {
        repository = Factory.of(Developer.class);
        repositoryCompany = Factory.of(Company.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        if (pathInfo==null || "/".equals(pathInfo)) {
            //sendAsJson(resp, repository.findAll());
            req.setAttribute("developers",repository.findAll());
            req.getRequestDispatcher("/view/developer/developers.jsp").forward(req,resp);
            return;
        }
        String[] split = pathInfo.split("/");
        if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String reqPathInfo = req.getPathInfo();
        Developer developer = repository.findById(Long.parseLong(reqPathInfo.substring(1))).get();
        req.setAttribute("developer", developer);
        req.setAttribute("company", repositoryCompany.getOne(developer.getCompanyId()));
        req.getRequestDispatcher("/view/developer/developerDetails.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Developer developer = Developer.builder()
                .name(req.getParameter("name"))
                .age(Integer.parseInt(req.getParameter("age")))
                .gender(req.getParameter("gender"))
                .salary(Integer.parseInt(req.getParameter("salary")))
                .companyId(Long.parseLong(req.getParameter("companyId")))
                .build();
        repository.save(developer);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
        Developer developer = gson.fromJson(payload, Developer.class);
        sendAsJson(resp, repository.save(developer));
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
