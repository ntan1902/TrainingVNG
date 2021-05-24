package FactoryMethod;

import FactoryMethod.factory.CreateType;
import FactoryMethod.factory.EnemyFactory;
import FactoryMethod.factory.Factory;
import SimpleFactory.enemy.Enemy;

public class Main {
    public static void main(String[] args) {
        // ==== Level 1: Defeat low-level enemies (SimpleFactory.enemy.Plane, SimpleFactory.enemy.Tank) ====.
        // In this level, Game will create random 5 enemies for Player to defeat.
        EnemyFactory randomFactory = Factory.createFactory(CreateType.RANDOM);
        for (int i = 0; i < 5; i++) {
            Enemy enemy = randomFactory.createEnemy();
            System.out.println(enemy.showName());
        }
        // ==== Finish level 1 ====.

        // ==== Level 2: Defeat boss SimpleFactory.enemy (SimpleFactory.enemy.Dragon) ====.
        // In this level, Game will create 1 SimpleFactory.enemy for Player to defeat.
        EnemyFactory bossFactory = Factory.createFactory(CreateType.BOSS);
        Enemy boss = bossFactory.createEnemy();
        System.out.println(boss.showName());
        // ===== Finish level 2 ====.

    }
}
