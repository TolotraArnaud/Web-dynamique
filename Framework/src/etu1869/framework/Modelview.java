package etu1869.framework;

import java.util.*;

public class Modelview {
    String view;
    HashMap<String, Object> datas;
    HashMap<String, Object> session;
    boolean isJson;

    public Modelview (String view){
        this.setView(view);
        this.setJson(false);
        datas = new HashMap<>();
        session = new HashMap<>();
    }
    public Modelview (){
        this.setJson(false);
        datas = new HashMap<>();
        session = new HashMap<>();
    }

    public void addItem(String key, Object item) {
        datas.put(key, item);
    }
    
    // GETTERS AND SETTERS
    public String getView() {
        return view;
    }
    public void setView(String view) {
        if (!view.contains(".jsp")) {
            view =view+ ".jsp";
        }
        System.out.println(view);
        this.view = view;
    }

    public HashMap<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(HashMap<String, Object> datas) {
        this.datas = datas;
    }

    public void setAttributeSession(String key, Object obj) {
        this.session.put(key, obj);
    }

    public void setAttributeSession(String key, Object obj,HashMap<String, Object> httpsession) {
        httpsession.put(key, obj);
    }

    public HashMap<String, Object> getSession() {
        return this.session;
    }

    public boolean isJson() {
        return isJson;
    }

    public void setJson(boolean isJson) {
        this.isJson = isJson;
    }

    
}
