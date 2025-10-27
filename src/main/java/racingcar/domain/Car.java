package racingcar.domain;

public class Car {
    final private static int DISCRIMINANT_NUMBER = 4;

    final private String name;
    final private NumberGenerator numberGenerator;
    private int position = 0;

    Car(String name, NumberGenerator numberGenerator) {
        this.name = name;
        this.numberGenerator = numberGenerator;
    }

    public void move() {
        if (numberGenerator.generate() >= DISCRIMINANT_NUMBER) {
            this.position++;
        }
    }

    public int getPosition() {
        return position;
    }

    public static Car readyCar(String name) {
        return new Car(name, new CarNumberGenerator());
    }
}
