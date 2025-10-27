package racingcar.domain;

public class Car {
    final private static int DISCRIMINANT_NUMBER = 4;
    final private static int MAX_NAME_LENGTH = 5;


    final private String name;
    final private NumberGenerator numberGenerator;
    private int position = 0;

    Car(String name, NumberGenerator numberGenerator) {
        validateName(name);
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

    public String getName() {
        return name;
    }

    public static Car readyCar(String name) {
        return new Car(name, new CarNumberGenerator());
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty() || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상 5자 이하여야 합니다.");
        }
    }
}
