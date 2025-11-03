package lotto.domain.lotto;

import lotto.domain.rank.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LottoResult 테스트")
class LottoResultTest {

    @Nested
    @DisplayName("초기 상태")
    class InitialState {

        @Test
        @DisplayName("아직 당첨 결과가 없으면 모든 등수 카운트는 0이다.")
        void 아직_당첨_결과가_없으면_모든_등수_카운트는_0이다() {
            Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningLotto winningLotto = WinningLotto.of(winning, 7);

            LottoResult result = LottoResult.of(List.of(), winningLotto);

            for (Rank rank : Rank.values()) {
                assertEquals(0, result.rankResult().get(rank));
            }
            assertEquals(0, result.purchasePrice());
        }
    }

    @Nested
    @DisplayName("결과 누적")
    class Accumulation {

        @Test
        @DisplayName("등수를 추가하면 해당 등수 카운트가 증가한다.")
        void 등수를_추가하면_해당_등수_카운트가_증가한다() {
            Lotto winning = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningLotto winningLotto = WinningLotto.of(winning, 7);

            Lotto firstMatch = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            Lotto secondMatch = new Lotto(List.of(1, 2, 3, 4, 5, 7));

            LottoResult result = LottoResult.of(List.of(firstMatch, firstMatch, secondMatch), winningLotto);

            Rank first = Rank.valueOf(6, false);
            Rank second = Rank.valueOf(5, true);

            assertEquals(2, result.rankResult().get(first));
            assertEquals(1, result.rankResult().get(second));
            assertEquals(3 * 1000, result.purchasePrice());
        }
    }
}
