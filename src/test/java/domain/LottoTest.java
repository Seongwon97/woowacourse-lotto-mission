package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("로또 상금을 올바르게 찾는지 테스트한다.")
public class LottoTest {

    @Test
    @DisplayName("3개 일치시 5등이다.")
    void lotto_calculateRightFifthRank() {
        Integer[] numbersArray = {1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        Integer[] winningNumbersArray = {1, 2, 3, 10, 11, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        WinningNumber winningLotto = new WinningNumber(winningNumbers);
        winningLotto.addBonusNumber(45);

        Prize winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Prize.FIFTH);
    }

    @Test
    @DisplayName("5개 일치와 보너스가 있다면 2등이다.")
    void lotto_calculateRightSecondRank() {
        Integer[] numbersArray = {1, 2, 3, 4, 5, 6};
        List<Integer> numbers = Arrays.asList(numbersArray);
        Lotto lotto = new Lotto(numbers);

        Integer[] winningNumbersArray = {1, 2, 3, 4, 5, 12};
        List<Integer> winningNumbers = Arrays.asList(winningNumbersArray);
        WinningNumber winningLotto = new WinningNumber(winningNumbers);
        winningLotto.addBonusNumber(6);

        Prize winnerPrice = lotto.calculateRank(winningLotto);
        assertThat(winnerPrice).isEqualTo(Prize.SECOND);
    }

}
