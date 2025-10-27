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
    public record CarStatus(String name, int position) {}

    public List<CarStatus> getRoundStatus() {
        return lineup.stream()
                .map(car -> new CarStatus(car.getName(), car.getPosition()))
                .collect(Collectors.toList());
    }

    private int getMaxPosition() {
        return lineup.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0); // lineup이 비어있을 경우 0 반환
    }

    public List<String> getWinners() {
        int maxPosition = getMaxPosition();

        return lineup.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

}
