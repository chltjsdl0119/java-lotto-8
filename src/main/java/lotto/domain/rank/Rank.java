package lotto.domain.rank;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L, "6개 일치 (2,000,000,000원)", (m, b) -> m == 6),
    SECOND(5, 30_000_000L, "5개 일치, 보너스 볼 일치 (30,000,000원)", (m, b) -> m == 5 && b),
    THIRD(5, 1_500_000L, "5개 일치 (1,500,000원)", (m, b) -> m == 5),
    FOURTH(4, 50_000L, "4개 일치 (50,000원)", (m, b) -> m == 4),
    FIFTH(3, 5_000L, "3개 일치 (5,000원)", (m, b) -> m == 3),
    MISS(0, 0L, "꽝", (m, b) -> m < 3)
    ;

    private final int matchCount;
    private final long prizeMoney;
    private final String message;
    private final RankCondition condition;

    Rank(int matchCount, long prizeMoney, String message, RankCondition condition) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.message = message;
        this.condition = condition;
    }

    public static Rank valueOf(int matchCount, boolean matchBonusNumber) {
        return Arrays.stream(values())
                .filter(rank -> rank.condition.test(matchCount, matchBonusNumber))
                .findFirst()
                .orElse(MISS);
    }

    public long getPrizeMoney() {
        return prizeMoney;
    }
}
