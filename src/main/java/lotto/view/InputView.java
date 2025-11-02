package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public final class InputView {
    private static final String INPUT_TOTAL_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER = "당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";

    private InputView() {
    }

    public static String readTotalPrice() {
        System.out.println(INPUT_TOTAL_PRICE);

        return Console.readLine();
    }

    public static String readWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBER);

        return Console.readLine();
    }

    public static String readBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);

        return Console.readLine();
    }
}
