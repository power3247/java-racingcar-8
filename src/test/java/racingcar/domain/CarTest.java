package racingcar.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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
}