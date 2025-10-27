package racingcar.view;

import racingcar.domain.Cars;

import java.util.List;

public interface OutputInterface {
    void printRoundResultHeader();

    void printRoundStatus(List<Cars.CarStatus> statuses);

    void printWinners(List<String> winnerNames);

}
