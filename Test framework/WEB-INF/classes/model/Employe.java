package model;

import annotation.Url;
import etu1869.framework.Modelview;

public class Employe {

    public Employe(){
        
    }

    @Url(value = "hey.do")
    public Modelview hey(){
        Modelview mv = new Modelview("error.jsp");
        return mv;
    }

    
    
}
