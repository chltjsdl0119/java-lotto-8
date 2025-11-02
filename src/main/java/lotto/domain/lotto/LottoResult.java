package lotto.domain.lotto;

import lotto.domain.rank.Rank;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public record LottoResult(
        Map<Rank, Integer> rankResult,
        int purchasePrice
) {
    private static final int LOTTO_PRICE = 1000;

    public static LottoResult of(List<Lotto> lottos, WinningLotto winningLotto) {
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            counts.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            Rank rank = winningLotto.determineRank(lotto);

            counts.put(rank, counts.get(rank) + 1);
        }

        int purchasePrice = lottos.size() * LOTTO_PRICE;

        return new LottoResult(Map.copyOf(counts), purchasePrice);
    }

    public double calculateRate() {
        long totalPrize = calculateTotalPrize();

        if (purchasePrice == 0) {
            return 0.0;
        }

        double rate = ((double) totalPrize / purchasePrice) * 100.0;

        return Math.round(rate * 10.0) / 10.0;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;

        for (Map.Entry<Rank, Integer> entry : rankResult.entrySet()) {
            Rank rank = entry.getKey();
            int count = entry.getValue();
            totalPrize += rank.getPrizeMoney() * count;
        }

        return totalPrize;
    }
}
