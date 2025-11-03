package lotto.domain.lotto;

import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WinningLotto 테스트")
class WinningLottoTest {

    @Nested
    @DisplayName("유효한 당첨 로또인 경우")
    class ValidWinning {

        @Test
        @DisplayName("6개 일치하면 1등을 반환한다.")
        void 여섯개_일치하면_일등을_반환한다() {
            Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonus = 7;
            WinningLotto winningLotto = WinningLotto.of(winning, bonus);

            Lotto purchased = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            Rank actual = winningLotto.determineRank(purchased);

            assertEquals(Rank.valueOf(6, false), actual);
        }

        @Test
        @DisplayName("5개와 보너스 일치하면 2등을 반환한다.")
        void 다섯개와_보너스_일치하면_이등을_반환한다() {
            Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int bonus = 7;
            WinningLotto winningLotto = WinningLotto.of(winning, bonus);

            Lotto purchased = new Lotto(List.of(1, 2, 3, 4, 5, 7));
            Rank actual = winningLotto.determineRank(purchased);

            assertEquals(Rank.valueOf(5, true), actual);
        }
    }

    @Nested
    @DisplayName("유효하지 않은 당첨 로또인 경우")
    class InvalidWinning {

        @Test
        @DisplayName("보너스 번호가 범위를 벗어나면 예외를 던진다.")
        void 보너스_번호가_범위를_벗어나면_예외를_던진다() {
            Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));

            IllegalArgumentException exLow = assertThrows(
                    IllegalArgumentException.class,
                    () -> WinningLotto.of(winning, 0)
            );
            assertEquals("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.", exLow.getMessage());

            IllegalArgumentException exHigh = assertThrows(
                    IllegalArgumentException.class,
                    () -> WinningLotto.of(winning, 46)
            );
            assertEquals("[ERROR] 로또 번호는 1부터 45 사이여야 합니다.", exHigh.getMessage());
        }

        @Test
        @DisplayName("보너스 번호가 당첨 숫자에 포함되면 예외를 던진다.")
        void 보너스_번호가_당첨_숫자에_포함되면_예외를_던진다() {
            Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            int duplicateBonus = 3;

            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> WinningLotto.of(winning, duplicateBonus)
            );
            assertEquals("[ERROR] 당첨 숫자 내에 보너스 숫자가 존재합니다!", ex.getMessage());
        }
    }
}
