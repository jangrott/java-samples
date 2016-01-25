package pl.jangrot.javasamples.hibernateinheritance.tableperclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MANAGER")
public class Manager extends Employee {

    @Column(name = "NUM_OF_SUBORDINATES")
    private int numberOfsubordinates;

    public void setNumberOfsubordinates(int numberOfsubordinates) {
        this.numberOfsubordinates = numberOfsubordinates;
    }

    public int getNumberOfsubordinates() {
        return numberOfsubordinates;
    }

    @Override
    public String toString() {
        return "Manager [numberOfsubordinates = " + numberOfsubordinates + ", " + super.toString() + "]";
    }
}
