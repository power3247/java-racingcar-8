package racingcar.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CarTest {
    @Test
    void moveTest() {
        NumberGenerator stubGenerator = () -> 4;
        Car car = new Car("pobi", stubGenerator);
        car.move();
        assertThat(car.getPosition()).isEqualTo(1);
    }

    @Test
    void stopTest() {
        NumberGenerator stubGenerator = () -> 3;
        Car car = new Car("pobi", stubGenerator);
        car.move();
        assertThat(car.getPosition()).isEqualTo(0);
    }

    @Test
    void validateRightCarName() {
        String expectedMessage = "자동차 이름은 1자 이상 5자 이하여야 합니다.";

        IllegalArgumentException longNameException = assertThrows(IllegalArgumentException.class, () -> {
            new Car("longName", () -> 4);
        });
        assertThat(longNameException.getMessage()).isEqualTo(expectedMessage);

        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> {
            new Car(null, () -> 4);
        });
        assertThat(nullException.getMessage()).isEqualTo(expectedMessage);

        IllegalArgumentException blankException = assertThrows(IllegalArgumentException.class, () -> {
            new Car("", () -> 4);
        });
        assertThat(blankException.getMessage()).isEqualTo(expectedMessage);
    }
}