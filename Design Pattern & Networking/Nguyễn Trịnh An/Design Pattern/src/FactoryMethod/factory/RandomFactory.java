package FactoryMethod.factory;

import SimpleFactory.enemy.Enemy;
import SimpleFactory.enemy.Plane;
import SimpleFactory.enemy.Tank;

import java.util.Random;

public class RandomFactory implements EnemyFactory{
    private static final Random RANDOM = new Random();
    @Override
    public Enemy createEnemy() {
        // 0 -> SimpleFactory.enemy.Plane
        // 1 -> SimpleFactory.enemy.Tank
        int choice = RANDOM.nextInt(2);
        switch (choice) {
            case 0:
                return new Plane();
            case 1:
                return new Tank();
            default:
                throw new IllegalStateException();
        }
    }
}
