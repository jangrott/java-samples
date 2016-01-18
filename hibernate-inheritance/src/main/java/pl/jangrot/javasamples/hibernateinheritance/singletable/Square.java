package pl.jangrot.javasamples.hibernateinheritance.singletable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
public class Square extends AbstractShape implements Shape {

    @Column(name = "SIDE")
    private double side;

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public double getCircuit() {
        return 4 * side;
    }

    @Override
    public String toString() {
        return "Square [side = " + side + "]";
    }
}
