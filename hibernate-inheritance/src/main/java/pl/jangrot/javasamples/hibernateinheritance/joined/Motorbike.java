package pl.jangrot.javasamples.hibernateinheritance.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MOTORBIKE")
public class Motorbike extends Vehicle {

    @Column(name = "NUM_OF_STANDS")
    private int numberOfStands;

    public int getNumberOfStands() {
        return numberOfStands;
    }

    public void setNumberOfStands(int numberOfStands) {
        this.numberOfStands = numberOfStands;
    }

    @Override
    public String toString() {
        return "Motorbike [numberOfStands = " + numberOfStands + ", " + super.toString() + "]";
    }
}
