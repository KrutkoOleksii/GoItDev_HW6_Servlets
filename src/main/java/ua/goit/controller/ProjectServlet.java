package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Project;
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

@WebServlet("/project/*")
public class ProjectServlet extends HttpServlet {

    private final BaseRepository<Long, Project> repository;
    private final Gson gson = new Gson();

    public ProjectServlet() {
        repository = Factory.of(Project.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String pathInfo = req.getPathInfo();
        if (pathInfo==null || "/".equals(pathInfo)) {
            //sendAsJson(resp, repository.findAll());
            req.setAttribute("projects",repository.findAll());
            req.getRequestDispatcher("/view/projects.jsp").forward(req,resp);
            return;
        }
        String[] split = pathInfo.split("/");
        if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        //sendAsJson(resp, repository.findById(Long.parseLong(split[1])));
        List<Project> projects = new ArrayList<>();
        projects.add(repository.findById(Long.parseLong(split[1])).get());
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/view/projects.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
//        Project project = gson.fromJson(payload, Project.class);
//        sendAsJson(resp, repository.save(project));
        Project project = Project.builder()
                .name(req.getParameter("name"))
                .cost(Integer.parseInt(req.getParameter("cost")))
                .startDate(req.getParameter("startDate"))
                .companyId(Long.parseLong(req.getParameter("companyId")))
                .customerId(Long.parseLong(req.getParameter("customerId")))
                .build();
        repository.save(project);
        req.getRequestDispatcher("/index.html").forward(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
        Project project = gson.fromJson(payload, Project.class);
        sendAsJson(resp, repository.save(project));
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
