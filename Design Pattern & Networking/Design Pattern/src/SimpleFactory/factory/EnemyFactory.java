package SimpleFactory.factory;

import SimpleFactory.enemy.*;

public class EnemyFactory {
    public static Enemy createEnemy(EnemyType enemyType) {
        switch (enemyType) {
            case PLANE:
                return new Plane();
            case TANK:
                return new Tank();
            case DRAGON:
                return new Dragon();
            default:
                throw new IllegalArgumentException("This enemy name is not supported");
        }
    }
}
