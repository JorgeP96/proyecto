package com.ingenieria.proyecto;

public class Estatus {
    private boolean status;
    private String mensaje;

    public Estatus() {
    }

    public Estatus(boolean status, String mensaje) {
        this.status = status;
        this.mensaje = mensaje;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
