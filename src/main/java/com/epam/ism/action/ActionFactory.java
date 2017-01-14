package com.epam.ism.action;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    public static final Map<String,Action> actions = new HashMap<>();

    static {
        actions.put("POST/find-trains", new FindTrainsAction());
        actions.put("GET/check-train", new CheckTrainAction()); // TODO: 14.01.2017 controller?

        actions.put("POST/delete-station", new DeleteStationAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }





}
