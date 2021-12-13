package vendingmachine.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;
import vendingmachine.domain.Product;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.util.StringUtils;
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
    }

    public void putProducts(ArrayList<String> everyProductInfo) {
        // 아무 상품도 들어오지 않으면 예외를 터트린다.

        //검증하고 레포지토리로.
        // 1. []로 감싸져있는지 확인하고, 전부 제거해준다.
        // 2. 이름, 가격, 수량을 Product에 넣어본다. -> 이름 중복은 repository에서 테스트. / 물건 가격 -> 금액 조건 + 100원 이상이어야 된다 추가.
        // 3. 이상 없으면 예쁘게 만들어준걸 repository로 옮긴다.
        for (String eachProductInfo : everyProductInfo) {
            eachProductInfo = StringUtils.removeBracket(eachProductInfo);
            Product product = new Product(eachProductInfo);
        }

    }
}
