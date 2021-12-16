package vendingmachine.service;

import vendingmachine.domain.Money;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;

public class VendingMachineService {
    private CoinRepository coinRepository;
    private ProductRepository productRepository;

    public VendingMachineService(CoinRepository coinRepository, ProductRepository productRepository) {
        this.coinRepository = coinRepository;
        this.productRepository = productRepository;
    }

    public void putMoneyByAdmin(String moneyInput) {
        Money moneyVendingMachineHave = new Money(moneyInput);
        coinRepository.putCoinInVendingMachine(moneyVendingMachineHave);
    }
}
