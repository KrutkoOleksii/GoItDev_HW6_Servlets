package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Company;
import ua.goit.model.Developer;
import ua.goit.service.BaseService;
import ua.goit.service.CompanyService;
import ua.goit.service.DeveloperService;

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

    private final Gson gson = new Gson();
    private final BaseService<Long, Developer> developerBaseService;
    private final BaseService<Long, Company> companyBaseService;

    public DeveloperServlet() {
        companyBaseService = new CompanyService();
        developerBaseService = new DeveloperService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = getAction(req);
        String pathInfo = req.getPathInfo();
        String[] split = pathInfo.split("/");
        if (pathInfo==null || "/".equals(pathInfo)) {
            //sendAsJson(resp, repository.findAll());
            req.setAttribute("developers",developerBaseService.readAll(Developer.class));
            req.getRequestDispatcher("/view/developer/developers.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/findDeveloper")) {
            req.getRequestDispatcher("/view/developer/findDeveloper.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/find")) {
            String name = req.getParameter("name");
            Developer developer = developerBaseService.findByName(Developer.class, name).get();
            req.setAttribute("developer", developer);
            req.setAttribute("company", companyBaseService.findById(Company.class,developer.getCompanyId()).get());
            req.getRequestDispatcher("/view/developer/developerDetails.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/addDeveloper")) {
            req.getRequestDispatcher("/view/developer/saveDeveloper.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/deleteDeveloper")) {
            req.getRequestDispatcher("/view/developer/developers.jsp").forward(req,resp);
            return;
        }
        else if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String reqPathInfo = req.getPathInfo();
        Developer developer = developerBaseService.findById(Developer.class,Long.parseLong(reqPathInfo.substring(1))).get();
        req.setAttribute("developer", developer);
        req.setAttribute("company", companyBaseService.findById(Company.class,developer.getCompanyId()).get());
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
        developerBaseService.createEntity(Developer.class,developer);
        req.setAttribute("developer", developer);
        req.setAttribute("company", companyBaseService.findById(Company.class,developer.getCompanyId()).get());
        req.getRequestDispatcher("/view/developer/developerDetails.jsp").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
        Developer developer = gson.fromJson(payload, Developer.class);
        sendAsJson(resp, developerBaseService.updateEntity(Developer.class,developer));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String[] split = req.getPathInfo().split("/");
        developerBaseService.deleteEntity(Developer.class,Long.parseLong(split[1]));
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
