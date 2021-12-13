package vendingmachine.service;

import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.utils.CoinGenerator;

public class VendingMachineService {
    private CoinRepository coinRepository;
    private ProductRepository productRepository;

    public VendingMachineService(CoinRepository coinRepository, ProductRepository productRepository) {
        this.coinRepository = coinRepository;
        this.productRepository = productRepository;
    }

    public void makeCoins(Price price) {
        LinkedHashMap<Coin,Integer> coins = CoinGenerator.makeCoins(price);
        coinRepository.putCoins(coins);
        // price에서 int값을 꺼내준다.
        // 해당 값이 0이 될 때까지 동전을 만든다.
        //
    }
}
