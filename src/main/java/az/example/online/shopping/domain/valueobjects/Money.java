package az.example.online.shopping.domain.valueobjects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Getter
public class Money {
    private final BigDecimal amount;


    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

}
