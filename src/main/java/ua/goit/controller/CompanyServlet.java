package ua.goit.controller;

import ua.goit.model.Company;
import ua.goit.service.BaseService;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company/*")
public class CompanyServlet extends HttpServlet {

    private final BaseService<Long,Company> companyBaseService;

    public CompanyServlet() {
        companyBaseService = new CompanyService();
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
            req.setAttribute("entity","company");
            req.getRequestDispatcher("/view/findByName.jsp").forward(req,resp);
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
        else if (action.startsWith("/updateCompany")) {
            req.getRequestDispatcher("/view/company/saveCompany.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/deleteCompany")) {
            req.setAttribute("entity","company");
            req.getRequestDispatcher("/view/deleteById.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/delete")) {
            doDelete(req,resp);
        }
        else if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
//        String reqPathInfo = req.getPathInfo();
//        Company company = companyBaseService.findById(Company.class, Long.parseLong(reqPathInfo.substring(1))).get();
//        req.setAttribute("company", company);
//        req.getRequestDispatcher("/view/company/companyDetails.jsp").forward(req,resp);
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
        Company company = Company.builder()
                .name(req.getParameter("name"))
                .code(req.getParameter("code"))
                .build();
        companyBaseService.updateEntity(Company.class, company);
        req.getRequestDispatcher("/view/index.jsp").forward(req,resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        companyBaseService.deleteEntity(Company.class, Long.parseLong(id));
        req.setAttribute("companies",companyBaseService.readAll(Company.class));
        req.getRequestDispatcher("/view/company/companies.jsp").forward(req,resp);
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestPathWithServletPath = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletPath.length());
    }

}

