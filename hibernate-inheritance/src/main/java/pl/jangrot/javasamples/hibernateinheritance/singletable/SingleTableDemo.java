package pl.jangrot.javasamples.hibernateinheritance.singletable;

import org.hibernate.Session;
import pl.jangrot.javasamples.hibernateinheritance.HibernateUtils;

import java.util.List;

public class SingleTableDemo {

    public static void main(String[] args) throws Exception {

        Session session = HibernateUtils.getInstance().getSession();

        session.save(createCircle(0.3));
        session.save(createRectangle(100, 200));
        session.save(createSquare(50));
        session.save(createCircle(0.6));

        List<Shape> shapes = session.createQuery(
                "from pl.jangrot.javasamples.hibernateinheritance.singletable.Shape"
        ).list();

        session.close();

        shapes.forEach(System.out::println);
    }

    private static Square createSquare(double side) {
        Square square = new Square();
        square.setSide(side);
        return square;
    }

    private static Rectangle createRectangle(double height, double width) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        return rectangle;
    }

    private static Circle createCircle(double radius) {
        Circle circle = new Circle();
        circle.setRadius(radius);
        return circle;
    }
}
