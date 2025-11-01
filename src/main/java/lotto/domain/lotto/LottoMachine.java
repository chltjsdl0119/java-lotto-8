package lotto.domain.lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.IntStream;

public final class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private LottoMachine() {
    }

    public static List<Lotto> buyLottos(int totalPrice) {
        validateTotalPrice(totalPrice);

        int lottoCount = calculateLottoCount(totalPrice);

        return IntStream.range(0, LOTTO_NUMBER_COUNT)
                .mapToObj(i -> generateLotto())
                .toList();
    }

    private static Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBER_COUNT
        );

        return new Lotto(numbers);
    }

    private static int calculateLottoCount(int totalPrice) {
        return totalPrice / LOTTO_PRICE;
    }

    private static void validateTotalPrice(int totalPrice) {
        if (totalPrice < LOTTO_PRICE || totalPrice % LOTTO_PRICE != 0) {
            String.format("[ERROR] 로또 구매 금액은 %d원 단위여야 합니다.", LOTTO_PRICE);
        }
    }
}
