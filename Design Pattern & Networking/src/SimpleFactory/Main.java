package SimpleFactory;

import SimpleFactory.enemy.Enemy;
import SimpleFactory.enemy.EnemyType;
import SimpleFactory.factory.EnemyFactory;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // ==== Level 1: Defeat low-level enemies (SimpleFactory.enemy.Plane, SimpleFactory.enemy.Tank) ====.
        // In this level, Game will create random 5 enemies for Player to defeat.

        // Generate random 5 low-level enemies
        // 0 -> SimpleFactory.enemy.Plane
        // 1 -> SimpleFactory.enemy.Tank
        for (int i = 0; i < 5; i++) {
            Enemy enemy = createRandomEnemies();
            System.out.println(enemy.showName());
        }
        // ==== Finish level 1 ====.

        // ==== Level 2: Defeat boss SimpleFactory.enemy (SimpleFactory.enemy.Dragon) ====.
        // In this level, Game will create 1 SimpleFactory.enemy for Player to defeat.
        Enemy boss = createBoss();
        System.out.println(boss.showName());
        // ===== Finish level 2 ====.
    }

    private static Enemy createBoss() {
        return EnemyFactory.createEnemy(EnemyType.DRAGON);
    }

    private static Enemy createRandomEnemies() {
        Random random = new Random();
        int choice = random.nextInt(2);
        switch (choice) {
            case 0:
                return EnemyFactory.createEnemy(EnemyType.PLANE);
            case 1:
                return EnemyFactory.createEnemy(EnemyType.TANK);
            default:
                throw new IllegalStateException();
        }
    }
}
