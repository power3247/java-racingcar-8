package racingcar.view;
import camp.nextstep.edu.missionutils.Console;

public class Input implements InputInterface {
    private static final String MSG_READ_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String MSG_READ_TRY_COUNT = "시도할 횟수는 몇 회인가요?";

    @Override
    public String readCarNames() {
        System.out.println(MSG_READ_CAR_NAMES);
        return Console.readLine();
    }

    @Override
    public int readTryCount() {
        System.out.println(MSG_READ_TRY_COUNT);
        String input = Console.readLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도할 횟수는 숫자여야 합니다.");
        }
    }
}
