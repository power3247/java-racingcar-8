package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.view.InputInterface;
import racingcar.view.OutputInterface;

import java.util.List;

public class Game {
    private final InputInterface input;
    private final OutputInterface output;

    public Game(InputInterface input, OutputInterface output) {
        this.input = input;
        this.output = output;
    }

    public void run() {
        Cars cars = setupCars();
        int tryCount = setupTryCount();

        runRounds(cars, tryCount);

        concludeGame(cars);
    }

    private Cars setupCars() {
        String racers = input.readCarNames();
        return Cars.makeLineup(racers);
    }

    private int setupTryCount() {
        int count = input.readTryCount();
        if (count < 0) {
            throw new IllegalArgumentException("시도 횟수는 0 이상이어야 합니다.");
        }
        return count;
    }

    private void runRounds(Cars cars, int tryCount) {
        output.printRoundResultHeader();
        for (int i = 0; i < tryCount; i++) {
            cars.moveAllCars();

            List<Cars.CarStatus> statuses = cars.getRoundStatus();
            output.printRoundStatus(statuses);
        }
    }

    private void concludeGame(Cars cars) {

    }

}
