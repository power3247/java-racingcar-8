package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private List<Car> lineup;

    Cars(List<Car> lineup) {
        this.lineup = lineup;
    }

    public static Cars makeLineup(String racers) {
        validateRacersString(racers);
        List<String> racerList = nameParser(racers);
        List<Car> carList = lineupParser(racerList);
        return new Cars(carList);
    }

    private static void validateRacersString(String racers) {
        if (racers == null || racers.trim().isEmpty()) {
            throw new IllegalArgumentException("이름이 입력되지 않았습니다.");
        }
    }

    private static List<String> nameParser(String racers) {
        return Arrays.stream(racers.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static List<Car> lineupParser(List<String> racerList) {
        return racerList.stream()
                .map(Car::readyCar)
                .collect(Collectors.toList());
    }

    // 변화 상태 확인
    List<Car> getLineup() {
        return java.util.Collections.unmodifiableList(lineup);
    }

    public void moveAllCars() {
        lineup.forEach(Car::move);
    }

    //TODO: View를 위해 CarStatus를 반환
    public void getRoundStatus() {

    }

}
