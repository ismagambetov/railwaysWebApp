package com.epam.ism.action;

import com.epam.ism.entity.User;
import com.epam.ism.service.LoginService;
import com.epam.ism.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String previousPage = (String) request.getSession().getAttribute("prevPage");

        if (username == null || password == null) {
            return "login-error";
        }

        try {
            LoginService loginService = new LoginService();
            User user = loginService.find(username, password);
            if (user == null) {
                return "login-error";
            }
            request.getSession().setAttribute("user", user);
            //response.sendRedirect(previousPage);

        } catch (ServiceException e) {
            throw new ActionException("Something failed at database level. " + e.getMessage());
        }

        //previousPage.substring(previousPage.lastIndexOf('/'),previousPage.length())
        return previousPage;
    }
}
