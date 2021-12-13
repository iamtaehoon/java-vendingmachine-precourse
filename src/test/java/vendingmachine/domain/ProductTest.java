package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static vendingmachine.Constant.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

    @ParameterizedTest
    @ValueSource(strings = {"물,100,1", "콜라,100,0", "스프라이트,1000,10", "트코콜라,1000,100", "콜라,10000,13", "사,1000,84",
        "미란다,500,99"})
    @DisplayName("이름이 1~5글자, 가격 10으로 나눠떨어지고 양수, 수량 0 이상 100 이하인 경우 정상적으로 기능을 실행한다.")
    void 상품_정상_로직(String value) {
        Product product = new Product(value);
    }

    // 이름 조건 틀린 경우
    @Test
    @DisplayName("상품 이름에 공백이 있을 경우 예외를 발생시킨다.")
    void 상품_이름_공백_예외() {
        assertThatThrownBy(() -> new Product(",1000,10")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("입력된 정보에 공백이 있습니다.");
    }

    @Test
    @DisplayName("상품 이름이 다섯글자를 초과한 경우 예외를 발생시킨다.")
    void 상품_이름_다섯글자_초과_예외() {
        assertThatThrownBy(() -> new Product("미에로화이바,1000,10")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("상품의 이름 중 다섯 글자를 초과하는 이름이 있습니다.");
    }

    // 가격 조건 틀린 경우
    @Test
    @DisplayName("상품 가격에 공백이 있을 경우 예외를 발생시킨다.")
    void 상품_가격_공백_예외() {
        assertThatThrownBy(() -> new Product("콜라,,10")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("입력된 정보에 공백이 있습니다.");
    }

    @Test
    @DisplayName("상품 가격이 숫자가 아닌 경우 예외를 발생시킨다.")
    void 상품_가격_숫자_아님_예외() {
        assertThatThrownBy(() ->  new Product("콜라,a,10")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("0 이상의 숫자를 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라,101,1", "콜라,101,0", "스프라이트,1024,10", "트코콜라,6089,100", "콜라,10003,13", "사,1008,84"})
    @DisplayName("상품의 가격이 동전 최소단위로 나누어떨어지지 않으면 예외를 발생시킨다.")
    void 가격_10으로_나누어떨어지지_않음_예외(String input) {
        assertThatThrownBy(() -> new Product(input)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(String.format("금액은 %d로 나누어 떨어져야 합니다.", COIN_MIN_VALUE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라,0,1", "콜라,-1,0", "스프라이트,-2,10", "트코콜라,-100,100", "콜라,-9487,13", "사,-1461,84"})
    @DisplayName("상품 가격이 0이거나, 음수면 예외를 발생시킨다.")
    void 가격_0보다_작거나_같음_예외(String input) {
        assertThatThrownBy(() ->  new Product(input)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라,90,1", "콜라,80,0", "스프라이트,30,10", "트코콜라,10,100"})
    @DisplayName("상품 가격이 100원보다 작으면 예외를 발생시킨다.")
    void 가격_100원_미만_오류(String input) {
        assertThatThrownBy(() ->  new Product(input)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("상품의 가격이 100원 미만인 값이 있습니다.");
    }

    // 수량 조건 틀린 경우
    @Test
    @DisplayName("상품 수량에 공백이 있을 경우 예외를 발생시킨다.")
    void 상품_수량_공백_예외() {
        assertThatThrownBy(() -> new Product("콜라,1000,")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("입력된 정보에 공백이 있습니다.");
    }

    @Test
    @DisplayName("상품 수량이 숫자가 아닌 경우 예외를 발생시킨다.")
    void 상품_가격_수량_아님_예외() {
        assertThatThrownBy(() -> new Product("콜라,1000,1bs")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("0 이상의 숫자를 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라,100,-1", "스프라이트,1000,-10", "트코콜라,1000,-100", "콜라,10000,-13", "사,1000,-84"})
    @DisplayName("상품 수량이 0보다 작으면 예외를 발생시킨다.")
    void 수량_0보다_작으면_예외(String input) {
        assertThatThrownBy(() -> new Product(input)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("0 이상의 숫자를 입력해 주세요.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"콜라,100,101", "콜라,100,231", "스프라이트,1000,101", "트코콜라,1000,998", "콜라,10000,5125"})
    @DisplayName("상품 수량이 100개를 초과하면 예외를 발생시킨다.")
    void 수량_100개_초과_예외(String input) {
        assertThatThrownBy(() -> new Product(input)).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("상품의 수량은 100개를 넘을 수 없습니다.");
    }
}