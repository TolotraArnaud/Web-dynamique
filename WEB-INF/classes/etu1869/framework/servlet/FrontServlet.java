package etu1869.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import helper_classes.*;
import java.util.*;
import etu1869.framework.*;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls; 

    @Override
    public void init() throws ServletException{
        this.MappingUrls = new HashMap<String, Mapping>();
        ServletContext context = getServletContext();
        String absolutePath = context.getRealPath("/WEB-INF/classes");
        File workspace = new File(absolutePath);
        String[] test=workspace.list();
        for (String packageName : test) {
            String packagePath = absolutePath+'\\'+packageName;
            PackageClasse.checkMethod(packagePath,packageName, this.MappingUrls);
        }
    }
    

    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        if (request.getMethod().equals("GET")) {
            out.println("Method GET");
        } else if (request.getMethod().equals("POST")) {
            out.println("Method POST");
        } else {
            // Si la méthode HTTP n'est ni GET ni POST, on renvoie une erreur "Méthode non autorisée"
            out.println("Methode non reconnu");
        }
        processRequest(request, response);
    }
    protected void processRequest (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String url = String.valueOf(req.getRequestURL());
        out.println("URL : "+url);
        out.println("Parameter url : "+Util.getParamURL(url));
        out.println("MAPPING :"+this.MappingUrls.toString());
        HashMap<String, Mapping> test = this.MappingUrls;
        out.println(test.get("test2"));
    }
    
}
