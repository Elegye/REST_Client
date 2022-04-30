package com.eseo.client.servlet;

import com.eseo.client.dto.VilleDto;
import com.eseo.client.service.VilleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "homeServlet", value = "/")
public class HomeServlet extends HttpServlet {

    public static Integer ITEMS_PER_PAGE = 50;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Integer page = Integer.parseInt(request.getParameter("page") == null ? "0" : request.getParameter("page"));

        if(page == null){
            page = 0;
        }

        List<VilleDto> villes = VilleService.getVilles();
        List<VilleDto> pagination = villes.subList(page * ITEMS_PER_PAGE, (page + 1) * ITEMS_PER_PAGE);
        session.setAttribute("villes", villes);
        session.setAttribute("page", page);

        request.setAttribute("villes", villes);
        request.setAttribute("pagination", pagination);
        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        VilleDto from = VilleService.getVille(request.getParameter("fromInseeCode"));
        VilleDto to = VilleService.getVille(request.getParameter("toInseeCode"));
        Integer distance = VilleService.getDistance(from, to);

        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("distance", distance);

        this.getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
    }

}