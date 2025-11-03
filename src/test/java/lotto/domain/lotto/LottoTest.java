package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("정상적인 로또 번호 6개로 로또 객체를 생성할 수 있다.")
    @Test
    void 정상적인_로또_번호_6개로_로또_객체를_생성할_수_있다() {
        Lotto lotto = new Lotto(List.of(8, 3, 21, 15, 30, 1));

        assertThat(lotto.getNumbers())
                .containsExactly(1, 3, 8, 15, 21, 30);
    }

    @DisplayName("로또 번호가 범위를 넘어가면 예외가 발생한다.")
    @Test
    void 로또_번호가_범위를_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(-1, -2, -3, -4, -5, -6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("두 로또가 3개의 번호만 일치할 경우 3을 반환한다.")
    @Test
    void 두_로또가_3개의_번호만_일치할_경우_3을_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto otherLotto = new Lotto(List.of(1, 2, 3, 40, 41, 42));

        int count = lotto.countMatchingNumbers(otherLotto);

        assertThat(count).isEqualTo(3);
    }

    @DisplayName("로또 번호에 보너스 번호가 포함되어 있으면 true를 반환한다.")
    @Test
    void 로또_번호에_보너스_번호가_포함되어_있으면_true를_반환한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        assertThat(lotto.containsBonusNumber(3)).isTrue();
        assertThat(lotto.containsBonusNumber(10)).isFalse();
    }
}
