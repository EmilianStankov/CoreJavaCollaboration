package corejava.furniture;

public class Table extends Furniture {

    public Table(Material material, int price) {
        super(material, price);
    }

    @Override
    public String toString() {
        return "table";
    }

}
