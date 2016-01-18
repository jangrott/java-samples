package pl.jangrot.javasamples.hibernateinheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Circle extends AbstractShape implements Shape {

    @Column(name = "RADIUS")
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getCircuit() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle [radius = " + radius + "]";
    }
}
