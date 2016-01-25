package pl.jangrot.javasamples.hibernateinheritance.joined;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
public class Car extends Vehicle {

    @Column(name = "NUM_OF_DOORS")
    private int numberOfDoors;

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public String toString() {
        return "Car [numberOfDoors = " + numberOfDoors + ", " + super.toString() + "]";
    }
}
