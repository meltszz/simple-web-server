package org.meltszz;

import java.util.HashMap;

public class Router {
    private final String PAGES_PATH = "src/main/resources/public/pages";
    private final String IMG_PATH = "src/main/resources/public/public/img";
    private HashMap<String, String> routes;

    public Router() {
        routes = new HashMap<>();
        routes.put("/", PAGES_PATH + "/" + "home");
        routes.put("/favicon", IMG_PATH + "/" + "favicon.ico");

        routes.put("/public/pages/home/style.css", PAGES_PATH + "/" + "style.css");
        routes.put("/public/pages/home/style.js", PAGES_PATH + "/" + "script.js");
    }

    public String getRouteURL(String route) {
        return hasRoute(route) ? routes.get(route) : "";
    }

    public boolean hasRoute(String route) {
        return routes.containsKey(route);
    }
}
