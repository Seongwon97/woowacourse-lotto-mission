package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PrizeResultTest {

    private PrizeResult prizeResult;

    @BeforeEach
    void init() {
        int inputMoney = 3000;
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lottos.add(new Lotto(Arrays.asList(1, 2, 3, 4, 6, 8)));

        Integer[] winningNumbersArray = {1, 2, 3, 4, 5, 12};
        WinningNumbers winningNumber = new WinningNumbers(Arrays.asList(winningNumbersArray));
        winningNumber.addBonusNumber(6);

        prizeResult = new PrizeResult(inputMoney, lottos, winningNumber);
    }

    @Test
    @DisplayName("올바른 상금이 계산된다.")
    void lottos_makeRightTotalPrize() {
        Map<Rank, Integer> prizeResult = this.prizeResult.getPrizeResult();

        int totalPrize = 0;
        for (Rank rank : prizeResult.keySet()) {
            totalPrize += (prizeResult.get(rank) * rank.getPrize());
        }

        assertThat(totalPrize).isEqualTo(31550000);
    }

    @Test
    @DisplayName("올바른 수익률이 계산된다.")
    void lottos_calculateEarningRate() {
        float earningRate = prizeResult.getEarningRate();

        assertThat(earningRate).isEqualTo(10516.66f);
    }

}