package com.epam.ism.filter;

import com.epam.ism.action.Action;
import com.epam.ism.action.ActionException;
import com.epam.ism.action.ActionFactory;
import com.epam.ism.entity.Restriction;
import com.epam.ism.entity.Role;
import com.epam.ism.entity.User;
import com.epam.ism.utils.DateTimeUtil;
import com.epam.ism.utils.XmlManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "SecurityFilter")
public class SecurityFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestedUri = request.getRequestURI();
        String previousPage = request.getHeader("referer");

        if (!requestedUri.contains("secured")) {
            chain.doFilter(req,resp);
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("prevPage",previousPage);
            response.sendRedirect("/login.jsp");
            return;
        } else {
            Role role = user.getRole();


            try {
                String pathToXml = "../config/role-config.xml";
                XmlManager xmlManager = XmlManager.getInstance();
                Restriction restriction = (Restriction) xmlManager.getParsedObject(pathToXml,Restriction.class);

                List<String> restrictedActions = restriction.getRestrictedActions(role);

                if (restrictedActions != null) {
                    String strippedUri = DateTimeUtil.getStrippedRequestUri(requestedUri);

                    if (restrictedActions.contains(strippedUri.replaceAll("/",""))) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                } else {
                    Action action = ActionFactory.getAction(request);
                    String view = action.execute(request,response);
                    req.getRequestDispatcher("/WEB-INF/secured/"+view+".jsp").forward(request,response);
                    return;
                }
            } catch (JAXBException | ActionException e) {
                throw new FilterException(e.getMessage());
            }
        }

        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
