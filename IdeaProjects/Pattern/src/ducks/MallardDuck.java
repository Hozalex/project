package ducks;

import ducks.Behavior.FlyWithWings;
import ducks.Behavior.Quack;

public class MallardDuck extends Duck {

    public MallardDuck() {
        quackBehavior = new Quack();
        flyBehavior = new FlyWithWings();
    }


}
