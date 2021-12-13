package vendingmachine;

import vendingmachine.controller.VendingMachineController;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.service.VendingMachineService;

public class Application {
    public static void main(String[] args) {
        CoinRepository coinRepository = new CoinRepository();
        ProductRepository productRepository = new ProductRepository();
        VendingMachineService vendingMachineService = new VendingMachineService(coinRepository, productRepository);
        VendingMachineController vendingMachineController = new VendingMachineController(vendingMachineService);
    }
}
