package org.meltszz;

import java.util.HashMap;

public class Router {
    private final String PAGES_PATH = "public/pages";
    private HashMap<String, String> routes;

    public Router() {
        routes = new HashMap<>();
        routes.put("/", PAGES_PATH + "/" + "home");
    }

    public String getRouteURL(String route) {
        return hasRoute(route) ? routes.get(route) : "";
    }

    public boolean hasRoute(String route) {
        return routes.containsKey(route);
    }
}
