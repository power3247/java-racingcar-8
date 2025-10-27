package racingcar.view;

import org.junit.jupiter.api.Test;
import racingcar.domain.Cars;

import java.util.List;
import java.util.stream.Collectors;

public class Output implements OutputInterface {
    private static final String MSG_ROUND_RESULT_HEADER = "\n실행 결과";
    private static final String MSG_WINNER_PREFIX = "최종 우승자 : ";
    private static final String POSITION_BAR = "-";
    private static final String WINNER_DELIMITER = ", ";

    @Override
    public void printRoundResultHeader() {
        System.out.println(MSG_ROUND_RESULT_HEADER);
    }

    @Override
    public void printRoundStatus(List<Cars.CarStatus> statuses) {
        for (Cars.CarStatus status : statuses) {
            String name = status.name();
            String positionBars = convertPositionToBars(status.position());
            System.out.printf("%s : %s\n", name, positionBars);
        }
        System.out.println(); // 라운드 구분을 위한 공백
    }

    private String convertPositionToBars(int position) {
        return POSITION_BAR.repeat(position);
    }

    @Override
    public void printWinners(List<String> winnerNames) {
        String winners = winnerNames.stream()
                .collect(Collectors.joining(WINNER_DELIMITER));

        System.out.println(MSG_WINNER_PREFIX + winners);
    }
}
