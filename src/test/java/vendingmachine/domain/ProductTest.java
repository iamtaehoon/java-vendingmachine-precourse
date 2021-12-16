package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void 상품_생성_정상() {
        Product product = new Product("코카콜라", "1000", "10");
    }

    @Test
    void 상품_가격_100원_미만_예외() {
        Assertions.assertThatThrownBy(() -> new Product("코카콜라", "80", "10"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품_이름_공백_예외() {
        Assertions.assertThatThrownBy(() -> new Product(" ", "800", "10"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 상품_이름_10글자_초과_예외() {
        Assertions.assertThatThrownBy(() -> new Product("코카콜라스프라이트음료", "800", "10"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}