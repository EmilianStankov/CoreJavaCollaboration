package corejava.furniture;

public class Chair extends Furniture {

    public Chair(Material material, int price) {
        super(material, price);
    }

    @Override
    public String toString() {
        return "chair";
    }

}
