package com.epam.ism.filter;

import com.epam.ism.entity.Restriction;
import com.epam.ism.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    private InputStream input;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestedPage = request.getRequestURI();
        String previousPage = request.getHeader("referer");

        if (!requestedPage.contains("secured")) {
            chain.doFilter(req,resp);
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            session.setAttribute("prevPage",previousPage);
            response.sendRedirect("/login.jsp");
            return;
        } else {
            Role role = (Role) session.getAttribute("role");

            Restriction restriction = null;
            try {
                restriction = (Restriction)JAXBContext.newInstance(Restriction.class).
                                                                                createUnmarshaller().unmarshal(input);

                List<String> restrictedPages = new ArrayList<>();
                if (role.equals(Role.ADMINISTRATOR)) {
                    restrictedPages = restriction.getAdmin().getPages();
                } else if (role.equals(Role.CASHIER)) {
                    restrictedPages = restriction.getCashier().getPages();
                } else if (role.equals(Role.PASSENGER)) {
                    restrictedPages = restriction.getPassenger().getPages();
                }
                if (!restrictedPages.isEmpty()) {
                    if (restrictedPages.contains(requestedPage)) {
                        //an access to the requested page is restricted.
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    }
                } else {
                    chain.doFilter(req,resp);
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

    public void init() {
        input = Thread.currentThread().getContextClassLoader().getResourceAsStream("config/role-config.xml");
    }

}
