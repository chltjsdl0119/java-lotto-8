package lotto.domain.lotto;

import lotto.domain.rank.Rank;

public class WinningLotto {
    private final Lotto winngingLotto;
    private final int bonusNumber;

    private WinningLotto(Lotto winngingLotto, int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumber(bonusNumber);
        this.winngingLotto = winngingLotto;
        this.bonusNumber = bonusNumber;
    }

    public static WinningLotto of(Lotto winngingLotto, int bonusNumber) {
        return new WinningLotto(winngingLotto, bonusNumber);
    }

    public Rank determineRank(Lotto lotto) {
        int matchCount = winngingLotto.countMatchingNumbers(lotto);

        boolean matchBonusNumber = lotto.containsBonusNumber(bonusNumber);

        return Rank.valueOf(matchCount, matchBonusNumber);
    }

    private void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.");
        }
    }

    private void validateBonusNumber(int bonusNumber) {
        if (winngingLotto.containsBonusNumber(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 당첨 숫자 내에 보너스 숫자가 존재합니다!");
        }
    }
}
