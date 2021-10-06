package ua.goit.controller;

import com.google.gson.Gson;
import ua.goit.model.Company;
import ua.goit.model.Skill;
import ua.goit.repository.BaseRepository;
import ua.goit.repository.Factory;
import ua.goit.service.BaseService;
import ua.goit.service.SkillService;

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

@WebServlet("/skill/*")
public class SkillServlet extends HttpServlet {

    //private final BaseRepository<Long, Skill> repository;
    private final Gson gson = new Gson();
    private final BaseService<Long, Skill> skillBaseService;

    public SkillServlet() {
        //repository = Factory.of(Skill.class);
        skillBaseService = new SkillService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = getAction(req);
        String pathInfo = req.getPathInfo();
        String[] split = pathInfo.split("/");
        if (pathInfo==null || "/".equals(pathInfo)) {
            req.setAttribute("skills",skillBaseService.readAll(Skill.class));
            req.getRequestDispatcher("/view/skill/skills.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/findSkillById")) {
            req.setAttribute("entity","skill");
            req.getRequestDispatcher("/view/findById.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/findSkill")) {
            req.setAttribute("entity","skill");
            req.getRequestDispatcher("/view/findByName.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/findById")) {
            Long id = Long.parseLong(req.getParameter("id"));
            Skill skill = skillBaseService.findById(Skill.class, id).get();
            req.setAttribute("skill", skill);
            req.getRequestDispatcher("/view/skill/skillDetails.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/find")) {
            String name = req.getParameter("name");
            Skill skill = skillBaseService.findByName(Skill.class, name).get();
            req.setAttribute("skill", skill);
            req.getRequestDispatcher("/view/skill/skillDetails.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/addSkill")) {
            req.getRequestDispatcher("/view/skill/saveSkill.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/updateSkill")) {
            req.getRequestDispatcher("/view/skill/saveSkill.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/deleteSkill")) {
            req.setAttribute("entity","skill");
            req.getRequestDispatcher("/view/deleteById.jsp").forward(req,resp);
            return;
        }
        else if (action.startsWith("/delete")) {
            doDelete(req,resp);
        }
        if (split.length!=2){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
//        List<Skill> skills = new ArrayList<>();
//        skills.add(skillBaseService.findById(Skill.class,Long.parseLong(split[1])).get());
//        req.setAttribute("skills", skills);
//        req.getRequestDispatcher("/view/skill/skills.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = getAction(req);
        if (action.startsWith("/create")) {
            Skill skill = Skill.builder()
                    .name(req.getParameter("name"))
                    .skillLevel(req.getParameter("level"))
                    .build();
            skillBaseService.createEntity(Skill.class, skill);
            req.setAttribute("skills",skillBaseService.readAll(Skill.class));
            req.getRequestDispatcher("/view/skill/skills.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Skill skill = Skill.builder()
                .name(req.getParameter("name"))
                .skillLevel(req.getParameter("level"))
                .build();
        skillBaseService.createEntity(Skill.class, skill);
        req.setAttribute("skills",skillBaseService.readAll(Skill.class));
        req.getRequestDispatcher("/view/skill/skills.jsp").forward(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        skillBaseService.deleteEntity(Skill.class, Long.parseLong(id));
        req.setAttribute("skills",skillBaseService.readAll(Skill.class));
        req.getRequestDispatcher("/view/skill/skills.jsp").forward(req,resp);
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        String requestPathWithServletPath = req.getContextPath() + req.getServletPath();
        return requestURI.substring(requestPathWithServletPath.length());
    }

}
