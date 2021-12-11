package org.meltszz;

import org.meltszz.util.ServerPaths;
import java.io.File;
import java.util.HashMap;

public class Router {
    private HashMap<String, String> routes;
    public Router() {
        routes = new HashMap<>();
    }

    /***
     * Add Route
     * Adds a route to the route map
     * @param url Request URL i.e: /index, /login, etc.
     * @param path Root path of the file that will be served.
     */
    public void addRoute(String url, String path) {
        routes.put(url, path);
    }

    /***
     * Create Page Routes
     * Creates an url for every html page.
     */
    public void createPageRoutes() {
       File htmlDirectory = new File(ServerPaths.HTML_PATH);
       String[] htmlFilesNames = htmlDirectory.list();
       for (String htmlFileName: htmlFilesNames) {
           String htmlFileNameNoExtension = htmlFileName.split("\\.")[0];
           this.addRoute("/" + htmlFileNameNoExtension, ServerPaths.HTML_PATH + "/" + htmlFileName);
       }
    }

    /***
     * Create Public Routes
     * Creates an url for every public resource.
     */
    public void createPublicRoutes() {
       for (String directoryName: ServerPaths.ALL_PUBLIC_PATHS) {
           File directory = new File(directoryName);
           String[] filesNames = directory.list();
           for (String fileName: filesNames) {
               this.addRoute("/public/" + fileName, ServerPaths.HTML_PATH + "/" + fileName);
           }
       }
    }

    /***
     * Create Home Page Route
     * Links a html file to the / route.
     * @param htmlFileName html file name
     */
    public void createHomePageRoute(String htmlFileName) {
        this.addRoute("/", ServerPaths.HTML_PATH + "/" + htmlFileName + ".html");
    }

    public String getRouteURL(String route) {
        return hasRoute(route) ? routes.get(route) : "";
    }

    /***
     * Has Route
     * Checks if routes map has a specific route.
     * @param route url route i.e: /index
     * @return true if has route false if doesn't have the route
     */
    public boolean hasRoute(String route) {
        return routes.containsKey(route);
    }
}
