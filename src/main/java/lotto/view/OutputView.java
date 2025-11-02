package lotto.view;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoResult;
import lotto.domain.rank.Rank;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class OutputView {
    private static final String PURCHASED_LOTTO_COUNT = "%d개를 구매했습니다.";
    private static final String LOTTO_RESULT_HEADER = "당첨 통계\n---";
    private static final String LOTTO_NUMBERS_FORMAT = "[%s]";
    private static final String LOTTO_NUMBERS_DELIMITER = ", ";
    private static final String LOTTO_RESULT_FORMAT = "%s (%,d원) - %d개";

    private OutputView() {
    }

    public static void printPurchasedLottoCount(int count) {
        System.out.println(String.format(PURCHASED_LOTTO_COUNT, count));
    }

    public static void printLottoNumbers(List<Lotto> lottos) {
        lottos.forEach(lotto ->
                System.out.println(formatLottoNumbers(lotto.getNumbers()))
        );
    }

    public static void printResult(LottoResult result) {
        System.out.println(LOTTO_RESULT_HEADER);

        System.out.println(formatResult(result.rankResult()));
    }

    public static void printRate(double rate) {
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }

    public static void printError(String message) {
        System.out.println(message);
    }

    private static String formatLottoNumbers(List<Integer> numbers) {
        String formatted = numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_NUMBERS_DELIMITER));

        return String.format(LOTTO_NUMBERS_FORMAT, formatted);
    }

    private static String formatResult(Map<Rank, Integer> ranks) {
        return ranks.entrySet().stream()
                .filter(entry -> entry.getKey() != Rank.MISS)
                .sorted(Comparator.comparing(entry -> entry.getKey().getPrizeMoney()))
                .map(entry -> String.format(LOTTO_RESULT_FORMAT,
                        entry.getKey().getMessage(),
                        entry.getKey().getPrizeMoney(),
                        entry.getValue()
                ))
                .collect(Collectors.joining("\n"));
    }
}
