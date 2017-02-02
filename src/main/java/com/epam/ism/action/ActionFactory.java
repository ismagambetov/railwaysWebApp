package com.epam.ism.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static final Map<String,Action> actions = new HashMap<>();

    static {
        actions.put("POST/find-trains", new FindTrainsAction());
        actions.put("POST/show-train", new ShowTrainAction()); // TODO: 14.01.2017 controller?
        actions.put("GET/show-train", new ShowTrainAction());
        actions.put("POST/booking-page", new BookAction());
        actions.put("POST/login", new LoginAction());

    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }





}
