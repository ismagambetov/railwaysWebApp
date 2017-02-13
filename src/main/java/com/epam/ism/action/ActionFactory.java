package com.epam.ism.action;

import com.epam.ism.utils.DateTimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static final Map<String,Action> actions = new HashMap<>();

    static {
        actions.put("GET/find-trains", new FindTrainsAction());
        actions.put("GET/show-train", new ShowTrainAction());
        actions.put("GET/booking-page", new BookingPageAction());
        actions.put("POST/login", new LoginAction());

    }

    public static Action getAction(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        String uri = DateTimeUtil.getStrippedRequestUri(requestUri);
        return actions.get(request.getMethod() + uri);
    }

}
