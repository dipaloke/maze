package com.example.maze.Models;


import com.example.maze.Views.ViewFactory;

public class Model {
    private static Model model;
    private String jwtToken;

    private final ViewFactory viewFactory;

    private Model() {
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance() {
        if(model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void  setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }


}
