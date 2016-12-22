package com.epam.ism.controller;

import com.epam.ism.action.Action;
import com.epam.ism.action.ActionException;
import com.epam.ism.action.ActionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontController")
public class FrontController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        service(request,response);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Action action = ActionFactory.getAction(req);
            String view = action.execute(req, resp);

            if (view.equals(req.getPathInfo().substring(1))) {
                req.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(req,resp);
            } else {
                resp.sendRedirect(view);
            }
        } catch (ActionException e) {
            throw new ServletException("Executing action failed.",e);
        }


    }
}
