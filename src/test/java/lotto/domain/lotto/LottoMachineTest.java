package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LottoMachine 테스트")
class LottoMachineTest {

    @Nested
    @DisplayName("유효한 구매인 경우")
    class ValidPurchase {

        @Test
        @DisplayName("구매한 금액에 맞는 로또 개수를 반환한다.")
        void 구매한_금액에_맞는_로또_개수를_반환한다() {
            int totalPrice = 5000;
            List<Lotto> lottos = LottoMachine.buyLottos(totalPrice);

            assertEquals(totalPrice / 1000, lottos.size());
        }
    }

    @Nested
    @DisplayName("유효하지 않은 구매인 경우")
    class InvalidPurchase {

        @Test
        @DisplayName("한 장 가격보다 적은 금액이면 예외를 던진다.")
        void 한_장_가격보다_적으면_예외를_던진다() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> LottoMachine.buyLottos(500)
            );
            assertEquals("[ERROR] 로또 구매 금액은 1000원 단위여야 합니다.", ex.getMessage());
        }

        @Test
        @DisplayName("1000원 단위가 아니면 예외를 던진다.")
        void 천원_단위가_아니면_예외를_던진다() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> LottoMachine.buyLottos(1500)
            );
            assertEquals("[ERROR] 로또 구매 금액은 1000원 단위여야 합니다.", ex.getMessage());
        }
    }
}
