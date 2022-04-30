package com.eseo.client.servlet;

import com.eseo.client.dto.VilleDto;
import com.eseo.client.service.VilleService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * This servlet leverages the possibility to create a new Ville entity.
 * The GET route serves the form, while the POST route serves the form processing.
 */
@WebServlet(name = "CreateVilleServlet", value = "/ville/create")
public class CreateVilleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("action", "/ville/create");
        request.setAttribute("mode", "Créer la ville");
        request.getRequestDispatcher("/WEB-INF//views/ville/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String message;
        boolean success = false;

        int responseCode = VilleService.create(VilleService.requestToDto(request));

        switch (responseCode){
            case 200:
                message = "La ville a été ajoutée";
                success = true;
                break;
            case 409:
                message = "La ville existe déjà.";
                break;
            case -1:
            case 500:
            default:
                message = "Something went totally wrong.";
                break;
        }

        request.setAttribute("message", message);
        request.setAttribute("success", success);

        response.sendRedirect(getServletContext().getContextPath() + "/ville/create");
    }
}
