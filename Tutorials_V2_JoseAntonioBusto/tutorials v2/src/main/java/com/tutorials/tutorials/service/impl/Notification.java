package com.tutorials.tutorials.service.impl;

// Añadimos la clase Notification que será nuestro objeto POJO
// con el que mandaremos la notificación en el Front

import java.util.Date;

public class Notification {

    private String text;
    private Date time;

    public Notification (String text, Date time) {

        super();
        this.text = text;
        this.time = time;

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
