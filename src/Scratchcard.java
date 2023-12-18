import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Scratchcard {
	private int cardNumber;
	private Set<Integer> winningNumbers;
	private int numWinners;

	public Scratchcard(int cardNumber, Set<Integer> winningNumbers, int numWinners) {
		this.cardNumber = cardNumber;
		this.winningNumbers = winningNumbers;
		this.numWinners = numWinners;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Set<Integer> getWinningNumbers() {
		return winningNumbers;
	}

	public void setWinningNumbers(Set<Integer> winningNumbers) {
		this.winningNumbers = winningNumbers;
	}

	public int getNumWinners() {
		return numWinners;
	}

	public void setNumWinners(int numWinners) {
		this.numWinners = numWinners;
	}

	@Override
	public String toString() {
		return "Scratchcard{" + "cardNumber=" + cardNumber +
				", winningNumbers=" + winningNumbers +
				", numWinners=" + numWinners +
				'}';
	}
}
