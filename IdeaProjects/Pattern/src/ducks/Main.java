package ducks;

import ducks.Behavior.FlyLikeRocket;

public class Main {

    public static void main(String[] args) {

        Duck malard = new MallardDuck();
        malard.performFly();
        malard.performQuack();
        malard.setBehavior(new FlyLikeRocket());
        malard.performFly();

    }
}
