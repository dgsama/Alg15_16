package coins;

public class FakeCoins {

	private Coins bagWithFakeCoin;
	private int fakePos;

	public FakeCoins(Coins bag) {
		bagWithFakeCoin = bag;

	}

	public int findFake() {
		divAndCon(0, bagWithFakeCoin.getNumberOfCoins() - 1);
		return fakePos;
	}

	public void divAndCon(int l, int r) {
		int n = r - l + 1;

		if (n <= 3) {
			if (n == 1) {
				if ((r <= (bagWithFakeCoin.getNumberOfCoins() - 2)) && (l > 2)) {
					l = l - 2;
				} else {
					r = r + 2;
				}
			} else if (n == 2) {
				if ((r <= (bagWithFakeCoin.getNumberOfCoins() - 1)) && (l > 1)) {
					l = l - 1;
				} else {
					r = r + 1;
				}
			}
			ScalePosition leftSide = bagWithFakeCoin.weigh(l, l, l + 1, l + 1);
			ScalePosition rightSide = bagWithFakeCoin.weigh(l + 1, l + 1, r, r);

			if (leftSide == ScalePosition.LEFT && rightSide == ScalePosition.RIGHT
					|| leftSide == ScalePosition.RIGHT && rightSide == ScalePosition.LEFT) {
				fakePos = l + 1;
			} else if ((leftSide == ScalePosition.RIGHT && rightSide == ScalePosition.EQUAL)
					|| (leftSide == ScalePosition.LEFT && rightSide == ScalePosition.EQUAL)) {
				fakePos = l;
			} else if ((leftSide == ScalePosition.EQUAL && rightSide == ScalePosition.LEFT)
					|| (leftSide == ScalePosition.EQUAL && rightSide == ScalePosition.RIGHT)) {
				fakePos = r;
			}

		} else {
			int middlePos = (l + r) / 2;
			divAndCon(middlePos + 1, r);
			divAndCon(l, middlePos);
		}
	}
}
