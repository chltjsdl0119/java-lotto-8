package lotto.util;

import java.util.Arrays;
import java.util.List;

public final class InputParser {
    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액 형식이 잘못되었습니다.");
        }
    }

    public static List<Integer> parseWinningNumbers(String input) {
        try {
            return Arrays.stream(input.split(DELIMITER))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
        }
    }
}
