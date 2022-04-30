package com.eseo.client.servlet;

import com.eseo.client.dto.VilleDto;
import com.eseo.client.service.VilleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdateVilleServlet", value = "/ville/update")
public class UpdateVilleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inseeCode = request.getParameter("inseeCode");

        if(inseeCode == null){
            throw new ServletException("Un code insee doit être passé en paramètre.");
        }

        VilleDto dto = VilleService.getVille(inseeCode);

        request.setAttribute("action", "/ville/update");
        request.setAttribute("ville", dto);
        request.setAttribute("mode", "Mettre à jour la ville");

        this.getServletContext().getRequestDispatcher("/WEB-INF/views/ville/form.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message;
        boolean success = false;

        int responseCode = VilleService.update(VilleService.requestToDto(request));

        switch (responseCode){
            case 200:
                message = "La ville a été mise à jour";
                success = true;
                break;
            case -1:
            case 500:
            default:
                message = "Something went totally wrong.";
                break;
        }

        request.setAttribute("message", message);
        request.setAttribute("success", success);

        response.sendRedirect("/");
    }
}
