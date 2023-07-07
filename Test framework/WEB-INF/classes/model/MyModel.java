package model;

import java.util.HashMap;
import etu1869.framework.Modelview;
import annotation.Url;

public class MyModel {
    HashMap<String, String> etu = new HashMap<>();

    public MyModel(){
        etu.put("1869", "Test");
        etu.put("0000", "JeanBas");
        etu.put("0001","Jeanne");
        etu.put("0002", "Rakoto");
    }

    @Url(value = "testJson.do")
    public Modelview getJson(){
        Modelview mv = new Modelview("data.jsp");
        mv.addItem("data", etu);
        mv.setJson(true);
        return mv;
    }

    @Url(value = "ha.do")
    public Modelview testme(){
        Modelview mv = new Modelview("error.jsp");
        return mv;
    }


    public HashMap<String, String> getEtu() {
        return etu;
    }

    public void setEtu(HashMap<String, String> etu) {
        this.etu = etu;
    }

    
}
