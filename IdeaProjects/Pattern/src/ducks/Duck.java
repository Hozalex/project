package ducks;

import ducks.Behavior.FlyBehavior;
import ducks.Behavior.QuackBehavior;

public abstract class Duck {

    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {

    }

    public void performFly() {
        flyBehavior.fly();

    }

    public void performQuack() {
        quackBehavior.quack();

    }

    public void setBehavior(FlyBehavior behavior) {

        this.flyBehavior = behavior;

    }


}
