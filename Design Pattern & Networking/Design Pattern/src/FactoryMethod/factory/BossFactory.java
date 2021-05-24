package FactoryMethod.factory;

import SimpleFactory.enemy.Dragon;
import SimpleFactory.enemy.Enemy;
import SimpleFactory.enemy.EnemyType;

public class BossFactory implements EnemyFactory{
    @Override
    public Enemy createEnemy() {
        return new Dragon();
    }
}
