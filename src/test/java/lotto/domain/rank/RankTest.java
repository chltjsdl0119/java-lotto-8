package lotto.domain.rank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Rank 테스트")
class RankTest {

    @Nested
    @DisplayName("정상 매칭 결과")
    class ValidMapping {

        @Test
        @DisplayName("6개 일치하면 FIRST를 반환한다.")
        void 여섯개_일치하면_FIRST를_반환한다() {
            Rank actual = Rank.valueOf(6, false);
            assertEquals(Rank.FIRST, actual);
            assertEquals(2_000_000_000L, actual.getPrizeMoney());
            assertEquals("6개 일치", actual.getMessage());
        }

        @Test
        @DisplayName("5개와 보너스 일치하면 SECOND를 반환한다.")
        void 다섯개와_보너스_일치하면_SECOND를_반환한다() {
            Rank actual = Rank.valueOf(5, true);
            assertEquals(Rank.SECOND, actual);
            assertEquals(30_000_000L, actual.getPrizeMoney());
            assertEquals("5개 일치, 보너스 볼 일치", actual.getMessage());
        }

        @Test
        @DisplayName("5개만 일치하면 THIRD를 반환한다.")
        void 다섯개만_일치하면_THIRD를_반환한다() {
            Rank actual = Rank.valueOf(5, false);
            assertEquals(Rank.THIRD, actual);
            assertEquals(1_500_000L, actual.getPrizeMoney());
            assertEquals("5개 일치", actual.getMessage());
        }

        @Test
        @DisplayName("4개 일치하면 FOURTH를 반환한다.")
        void 네개_일치하면_FOURTH를_반환한다() {
            Rank actual = Rank.valueOf(4, false);
            assertEquals(Rank.FOURTH, actual);
            assertEquals(50_000L, actual.getPrizeMoney());
            assertEquals("4개 일치", actual.getMessage());
        }

        @Test
        @DisplayName("3개 일치하면 FIFTH를 반환한다.")
        void 세개_일치하면_FIFTH를_반환한다() {
            Rank actual = Rank.valueOf(3, false);
            assertEquals(Rank.FIFTH, actual);
            assertEquals(5_000L, actual.getPrizeMoney());
            assertEquals("3개 일치", actual.getMessage());
        }

        @Test
        @DisplayName("3개 미만이면 MISS를 반환한다.")
        void 세개_미만이면_MISS를_반환한다() {
            assertEquals(Rank.MISS, Rank.valueOf(2, false));
            assertEquals(Rank.MISS, Rank.valueOf(1, false));
            assertEquals(Rank.MISS, Rank.valueOf(0, false));
        }
    }
}
