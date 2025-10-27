package racingcar.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private List<Car> lineup;

    public void makeLineup(String racers) {
        validateRacersString(racers);
        List<String> racerList = nameParser(racers);
        this.lineup = lineupParser(racerList);
    }

    private void validateRacersString(String racers) {
        if (racers == null || racers.trim().isEmpty()) {
            throw new IllegalArgumentException("이름이 입력되지 않았습니다.");
        }
    }

    private List<String> nameParser(String racers) {
        return Arrays.stream(racers.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private List<Car> lineupParser(List<String> racerList) {
        return racerList.stream()
                .map(Car::readyCar)
                .collect(Collectors.toList());
    }

    // 변화 상태 확인
    List<Car> getLineup() {
        return java.util.Collections.unmodifiableList(lineup);
    }


    //TODO: 모든 자동차 1회 전진 기능
    public void moveAllCars() {
        //TODO: racerLineup을 순회 해서 각 Car객체의 상태 바꾸기 Car#move() 사용
    }

    //TODO: View를 위해 CarStatus를 반환
    public void getRoundStatus() {

    }

}
