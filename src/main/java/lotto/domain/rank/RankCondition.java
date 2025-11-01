package lotto.domain.rank;

@FunctionalInterface
public interface RankCondition {
    boolean test(int matchCount, boolean matchBonusNumber);
}
