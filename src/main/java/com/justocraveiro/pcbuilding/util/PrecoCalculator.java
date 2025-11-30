package com.justocraveiro.pcbuilding.util;

import com.justocraveiro.pcbuilding.model.Peca;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Objects;

public final class PrecoCalculator {
    private PrecoCalculator() {}

    public static BigDecimal soma(Collection<Peca> pecas) {
        if (pecas == null || pecas.isEmpty()) return BigDecimal.ZERO.setScale(2);
        return pecas.stream()
                .filter(Objects::nonNull)
                .map(Peca::getPreco)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
