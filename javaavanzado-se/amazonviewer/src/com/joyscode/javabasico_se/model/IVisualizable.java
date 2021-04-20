package com.joyscode.javabasico_se.model;

import java.util.Date;

/**
 * Interfaz para definir los metodos de visualizacion
 *
 * @author jscastaneda
 * @since 31/08/2018
 */
public interface IVisualizable {

    public Date startToSee(Date dateI);
    public void stopToSee(Date dateI, Date dateF);
}
