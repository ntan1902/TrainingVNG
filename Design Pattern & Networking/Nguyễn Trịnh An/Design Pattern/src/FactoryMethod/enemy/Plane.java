package FactoryMethod.enemy;

public class Plane implements Enemy {
    @Override
    public String showName() {
        return "Plane is attacking";
    }
}
