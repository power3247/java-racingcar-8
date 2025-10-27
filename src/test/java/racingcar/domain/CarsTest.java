package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarsTest {

    @Test
    @DisplayName("정상적 케이스")
    void makeLineupTest() {
        String racers = "pobi, crong";

        Cars cars = Cars.makeLineup(racers);

        List<Car> lineup = cars.getLineup();
        assertThat(lineup).hasSize(2);

        assertThat(lineup)
                .map(Car::getName)
                .containsExactly("pobi", "crong"); // 순서까지 확인
    }

    @Test
    @DisplayName("이름 앞뒤의 공백을 제거 확인")
    void makeLineupTrimTest() {
        String racers = "  pobi , crong,honux  ";

        Cars cars = Cars.makeLineup(racers);

        List<Car> lineup = cars.getLineup();
        assertThat(lineup).hasSize(3);
        assertThat(lineup).map(Car::getName)
                .containsExactly("pobi", "crong", "honux");
    }

    @Test
    @DisplayName("입력 문자열이 null인경우")
    void makeLineupNullTest() {
        String racers = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Cars.makeLineup(racers);
        });

        assertThat(exception.getMessage()).contains("이름이 입력되지 않았습니다.");
    }

    @Test
    @DisplayName("입력 문자열이 공백인경우")
    void makeLineupBlankTest() {
        String racers = "";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Cars.makeLineup(racers);
        });

        assertThat(exception.getMessage()).contains("이름이 입력되지 않았습니다.");
    }


    @Test
    @DisplayName("이름이 5자를 초과")
    void makeLineupLengthTest() {
        String racers = "pobi, veryLongName";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Cars.makeLineup(racers);
        });

        assertThat(exception.getMessage()).contains("자동차 이름은 1자 이상 5자 이하여야 합니다.");
    }

    @Test
    @DisplayName("쉼표가 연속으로 빈이름 포함")
    void makeLineupContinuousCommaTest() {
        String racers = "pobi,,crong";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Cars.makeLineup(racers);
        });

        assertThat(exception.getMessage()).contains("자동차 이름은 1자 이상 5자 이하여야 합니다.");
    }

    @Test
    void moveAllCarsTest() {

        NumberGenerator alwaysMoveStub = () -> 5;

        Car pobi = new Car("pobi", alwaysMoveStub);
        Car crong = new Car("crong", alwaysMoveStub);


        Cars cars = new Cars(Arrays.asList(pobi, crong));

        cars.moveAllCars();

        assertThat(pobi.getPosition()).isEqualTo(1);
        assertThat(crong.getPosition()).isEqualTo(1);

    }

    @Test
    void getRoundStatusTest() {
    }
}