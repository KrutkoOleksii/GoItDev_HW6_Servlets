package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Company;
import ua.goit.service.BaseService;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {

    //private final BaseRepository<Long,Company> repository;
    private final BaseService<Long,Company> companyBaseService= new CompanyService();
    private final Gson gson = new Gson();

    public CompanyServlet() {
        //repository = Factory.of(Company.class);
        //companyBaseService = new CompanyService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = getAction(req);
        String pathInfo = req.getPathInfo();
        String[] split = pathInfo.split("/");
        if (pathInfo==null || "/".equals(pathInfo)) {
            req.setAttribute("companies",companyBaseService.readAll(Company.class));
            req.getRequestDispatcher("/view/company/companies.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/findCompany")) {
            req.getRequestDispatcher("/view/company/findCompany.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/find")) {
            String name = req.getParameter("name");
            Company company = companyBaseService.findByName(Company.class, name).get();
            req.setAttribute("company", company);
            req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/addCompany")) {
            req.getRequestDispatcher("/view/company/saveCompany.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/deleteCompany")) {
            req.getRequestDispatcher("/view/company/companies.jsp").forward(req,resp);
            return;
        }
        else if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String reqPathInfo = req.getPathInfo();
        Company company = companyBaseService.findById(Company.class, Long.parseLong(reqPathInfo.substring(1))).get();
        req.setAttribute("company", company);
        req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = getAction(req);
        if (action.startsWith("/create")) {
            Company company = Company.builder()
                    .name(req.getParameter("name"))
                    .code(req.getParameter("code"))
                    .build();
            companyBaseService.createEntity(Company.class, company);
            req.setAttribute("company", company);
            req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String payload = req.getReader().lines().collect(Collectors.joining("\n"));
//        Company company = gson.fromJson(payload, Company.class);
//        sendAsJson(resp, repository.save(company));
        //req.setAttribute("companyId",1L);
        Company company = Company.builder()
                .name(req.getParameter("name"))
                .code(req.getParameter("code"))
                .build();
        companyBaseService.updateEntity(Company.class, company);
        req.getRequestDispatcher("/view/index.jsp").forward(req,resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String[] split = req.getPathInfo().split("/");
        companyBaseService.deleteEntity(Company.class, Long.parseLong(split[1]));
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

