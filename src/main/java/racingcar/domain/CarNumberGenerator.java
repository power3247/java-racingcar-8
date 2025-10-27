package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;

public class CarNumberGenerator implements NumberGenerator {
    final private static int START_NUMBER = 1;
    final private static int LAST_NUMBER = 9;

    @Override
    public int generate() {
        return Randoms.pickNumberInRange(START_NUMBER, LAST_NUMBER);
    }
}
