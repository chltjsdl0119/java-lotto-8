package lotto.controller;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoMachine;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.WinningLotto;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoGameController {

    public void run() {
        List<Lotto> lottos = buyLottos();

        OutputView.printLottoNumbers(lottos);

        WinningLotto winningLotto = createWinningLotto();

        LottoResult result = LottoResult.of(lottos, winningLotto);

        OutputView.printResult(result);
        OutputView.printRate(result.calculateRate());
    }

    private List<Lotto> buyLottos() {
        while (true) {
            try {
                String rawPrice = InputView.readTotalPrice();

                int totalPrice = InputParser.parseInt(rawPrice);

                List<Lotto> lottos = LottoMachine.buyLottos(totalPrice);

                OutputView.printPurchasedLottoCount(lottos.size());

                return lottos;

            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }

    private WinningLotto createWinningLotto() {
        while (true) {
            try {
                String winningLottoNumber = InputView.readWinningNumbers();

                List<Integer> numbers = InputParser.parseWinningNumbers(winningLottoNumber);
                Lotto winningLotto = new Lotto(numbers);

                String rawBonusNumber = InputView.readBonusNumber();
                int bonusNumber = InputParser.parseInt(rawBonusNumber);

                return WinningLotto.of(winningLotto, bonusNumber);

            } catch (IllegalArgumentException e) {
                OutputView.printError(e.getMessage());
            }
        }
    }
}
