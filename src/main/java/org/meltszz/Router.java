package org.meltszz;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Router {
    private Map<String, String> routes = Map.ofEntries(
          entry("/", "public/pages/home/")
    );

    public String getRouteURL(String route) {
        return hasRoute(route) ? routes.get(route) : "";
    }

    public boolean hasRoute(String route) {
        return routes.containsKey(route);
    }
}
