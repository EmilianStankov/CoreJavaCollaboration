package corejava.furniture;

public class Main {
    public static void main(String[] args) {
        Chair chair = new Chair(new Concrete(), 500);
        System.out.println(chair.getDescription());
    }
}
