package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InputParser 테스트")
class InputParserTest {

    @Nested
    @DisplayName("정상 파싱")
    class ValidParsing {

        @Test
        @DisplayName("숫자 문자열은 정수로 파싱된다.")
        void 숫자_문자열은_정수로_파싱된다() {
            int actual = InputParser.parseInt("3000");
            assertEquals(3000, actual);
        }

        @Test
        @DisplayName("콤마로 구분된 숫자 문자열은 정수 리스트로 파싱된다.")
        void 콤마로_구분된_숫자_문자열은_정수_리스트로_파싱된다() {
            List<Integer> actual = InputParser.parseWinningNumbers("1,2,3,4,5,6");
            assertEquals(List.of(1, 2, 3, 4, 5, 6), actual);
        }

        @Test
        @DisplayName("공백을 포함한 입력도 트리밍되어 파싱된다.")
        void 공백을_포함한_입력도_트리밍되어_파싱된다() {
            List<Integer> actual = InputParser.parseWinningNumbers(" 1 , 2,  3 ");
            assertEquals(List.of(1, 2, 3), actual);
        }
    }

    @Nested
    @DisplayName("입력 오류 처리")
    class InvalidParsing {

        @Test
        @DisplayName("정수가 아닌 문자열이면 예외를 던진다.")
        void 정수가_아닌_문자열이면_예외를_던진다() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> InputParser.parseInt("abc")
            );
            assertEquals("[ERROR] 구입 금액 형식이 잘못되었습니다.", ex.getMessage());
        }

        @Test
        @DisplayName("당첨 번호에 숫자가 아닌 값이 있으면 예외를 던진다.")
        void 당첨_번호에_숫자가_아닌_값이_있으면_예외를_던진다() {
            IllegalArgumentException ex = assertThrows(
                    IllegalArgumentException.class,
                    () -> InputParser.parseWinningNumbers("1,2,three")
            );
            assertEquals("[ERROR] 당첨 번호는 숫자여야 합니다.", ex.getMessage());
        }
    }
}
