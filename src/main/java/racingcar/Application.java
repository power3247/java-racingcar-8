package racingcar;

import racingcar.controller.Game;
import racingcar.view.Input;
import racingcar.view.InputInterface;
import racingcar.view.Output;
import racingcar.view.OutputInterface;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputInterface input = new Input();
        OutputInterface output = new Output();

        Game game = new Game(input,output);
        game.run();
    }
}
