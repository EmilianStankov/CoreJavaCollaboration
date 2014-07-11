package corejava.furniture;

public abstract class Furniture {
    protected int price;
    protected Material material;

    public Furniture(Material material, int price) {
        this.price = price;
        this.material = material;
    }

    public String getDescription() {
        return String.format("A %s made of %s.", this.toString(), material.toString());
    }

    @Override
    public abstract String toString();

}