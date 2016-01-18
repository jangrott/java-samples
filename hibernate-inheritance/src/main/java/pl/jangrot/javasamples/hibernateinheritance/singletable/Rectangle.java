package pl.jangrot.javasamples.hibernateinheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Rectangle extends AbstractShape implements Shape {

    @Column(name = "HEIGHT")
    private double height;

    @Column(name = "WIDTH")
    private double width;

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public double getArea() {
        return height * width;
    }

    @Override
    public double getCircuit() {
        return (2 * height) + (2 * width);
    }

    @Override
    public String toString() {
        return "Rectangle [width = " + width + ", height = " + height + "]";
    }
}
