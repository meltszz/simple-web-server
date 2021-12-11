package org.meltszz;

import org.meltszz.util.ServerPaths;
import java.io.File;
import java.util.HashMap;

public class Router {
    private HashMap<String, String> routes;
    public Router() {
        routes = new HashMap<>();
    }

    public String getRouteURL(String route) {
        return hasRoute(route) ? routes.get(route) : "";
    }

    public boolean hasRoute(String route) {
        return routes.containsKey(route);
    }

    public void addRoute(String url, String path) {
        routes.put(url, path);
    }

    public void createPageRoutes() {
       File htmlDirectory = new File(ServerPaths.HTML_PATH);
       String[] htmlFilesNames = htmlDirectory.list();
       for (String htmlFileName: htmlFilesNames) {
           String htmlFileNameNoExtension = htmlFileName.split("\\.")[0];
           this.addRoute("/" + htmlFileNameNoExtension, ServerPaths.HTML_PATH + "/" + htmlFileName);
       }
    }

    public void createPublicRoutes() {
       for (String directoryName: ServerPaths.ALL_PUBLIC_PATHS) {
           File directory = new File(directoryName);
           String[] filesNames = directory.list();
           for (String fileName: filesNames) {
               this.addRoute("/public/" + fileName, ServerPaths.HTML_PATH + "/" + fileName);
           }
       }
    }

    public void createHomePageRoute(String htmlFileName) {
        this.addRoute("/", ServerPaths.HTML_PATH + "/" + htmlFileName + ".html");
    }
}
