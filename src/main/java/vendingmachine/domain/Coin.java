package vendingmachine.domain;

import java.util.Arrays;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin find(int randomCoinValue) {
        return Arrays.stream(Coin.values())
            .filter(coin -> coin.getAmount() == randomCoinValue)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("해당 가치를 가진 동전은 존재하지 않습니다. - 로직에러"));
    }

    public int getAmount() {
        return amount;
    }

    // 추가 기능 구현
}
