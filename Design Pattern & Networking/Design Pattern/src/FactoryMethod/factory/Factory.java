package FactoryMethod.factory;

public class Factory {
    public static EnemyFactory createFactory(CreateType createType) {
        switch (createType) {
            case RANDOM:
                return new RandomFactory();
            case BOSS:
                return new BossFactory();
            default:
                throw new IllegalArgumentException("This creation is not supported");
        }
    }
}
