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
    @DisplayName("현재 Car들의 상태를 record 리스트로 정확히 반환한다")
    void getRoundStatusTest() {
        Car pobi = new Car("pobi", () -> 5);
        Car crong = new Car("crong", () -> 3);
        pobi.move();
        Cars cars = new Cars(Arrays.asList(pobi, crong));

        List<Cars.CarStatus> statuses = cars.getRoundStatus();

        assertThat(statuses).hasSize(2);

        assertThat(statuses)
                .extracting(Cars.CarStatus::name) // .getName() -> .name()
                .containsExactly("pobi", "crong");

        assertThat(statuses)
                .extracting(Cars.CarStatus::position) // .getPosition() -> .position()
                .containsExactly(1, 0);
    }

    @Test
    @DisplayName("단독 우승자")
    void getWinnersSolo() {
        Car pobi = new Car("pobi", () -> 5);  //전진
        Car crong = new Car("crong", () -> 3);//정지
        Car honux = new Car("honux", () -> 5);//전진

        pobi.move();
        pobi.move();  // pobi = 2 (우승)
        honux.move(); // honux = 1
                      // crong = 0

        Cars cars = new Cars(Arrays.asList(pobi, crong, honux));

        List<String> winners = cars.getWinners();

        assertThat(winners).hasSize(1);
        assertThat(winners).containsExactly("pobi");
    }

    @Test
    @DisplayName("공동 우승자")
    void getWinnersTogether() {
        Car pobi = new Car("pobi", () -> 5);
        Car crong = new Car("crong", () -> 3);
        Car honux = new Car("honux", () -> 5);

        pobi.move();  // pobi = 1 (공동 우승)
        // crong = 0
        honux.move(); // honux = 1 (공동 우승)

        Cars cars = new Cars(Arrays.asList(pobi, crong, honux));

        List<String> winners = cars.getWinners();

        // 4. pobi와 honux가 모두 포함되어 있는지 확인
        assertThat(winners).hasSize(2);
        assertThat(winners).containsExactlyInAnyOrder("pobi", "honux");
    }

    @Test
    @DisplayName("모두0점")
    void getWinnersAllZeroes() {
        Car pobi = new Car("pobi", () -> 3);   // pobi = 0 (공동 우승)
        Car crong = new Car("crong", () -> 3); // crong = 0 (공동 우승)

        Cars cars = new Cars(Arrays.asList(pobi, crong));

        List<String> winners = cars.getWinners();

        assertThat(winners).hasSize(2);
        assertThat(winners).containsExactlyInAnyOrder("pobi", "crong");
    }
}