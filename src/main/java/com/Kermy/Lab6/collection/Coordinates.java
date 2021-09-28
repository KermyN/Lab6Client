package com.Kermy.Lab6.collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement
public class Coordinates implements Serializable {
    @XmlElement
    private Integer x; //Поле не может быть null
    @XmlElement
    private long y;

    public Coordinates(){};
    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(Integer x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public Integer getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public long getY() {
        return y;
    }
}
