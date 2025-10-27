package racingcar.domain;

import java.util.List;

public class Cars {
    private List<Car> lineup;

    Cars(String racers) {
        //Todo: String -> List<Car>, 저장
        makeLineup(racers);
    }

    private void makeLineup(String racers) {
        //TODO: 문자열 -> 스트링 리스트 stringParser()
        List<String> racerList = nameParser(racers);

        //Todo: List<String> -> List<Car>


    }

    //TODO: stringParser 문자열을 콤마기준 파싱 -> N대의 자동체 생성 헬퍼
    private List<String> nameParser(String racers) {
        return null;
    }

    //TODO: 모든 자동차 1회 전진 기능
    public void moveAllCars() {
        //TODO: racerLineup을 순회 해서 각 Car객체의 상태 바꾸기 Car#move() 사용
    }

    public void getRoundStatus() {

    }

}
