package pl.jangrot.javasamples.hibernateinheritance.singletable;

import javax.persistence.*;

@Entity
@Table(name = "SHAPE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "TYPE",
        discriminatorType = DiscriminatorType.STRING
)
public class AbstractShape {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
