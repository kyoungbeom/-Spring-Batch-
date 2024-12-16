package com.practice.settlement.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ServicePolicy {

    A(1L, "practice/services/a", 10),
    B(2L, "practice/services/b", 20),
    C(3L, "practice/services/c", 30),
    D(4L, "practice/services/d", 40),
    E(5L, "practice/services/e", 50),
    F(6L, "practice/services/f", 60),
    G(7L, "practice/services/g", 70),
    H(8L, "practice/services/h", 80),
    I(9L, "practice/services/i", 90),
    J(10L, "practice/services/j", 100),
    K(11L, "practice/services/k", 110),
    L(12L, "practice/services/l", 120),
    M(13L, "practice/services/m", 130),
    N(14L, "practice/services/n", 140),
    O(15L, "practice/services/o", 150),
    P(16L, "practice/services/p", 160),
    Q(17L, "practice/services/q", 170),
    R(18L, "practice/services/r", 180),
    S(19L, "practice/services/s", 190),
    T(20L, "practice/services/t", 200),
    U(21L, "practice/services/u", 210),
    V(22L, "practice/services/v", 220),
    W(23L, "practice/services/w", 230),
    X(24L, "practice/services/x", 240),
    Y(25L, "practice/services/y", 250),
    Z(26L, "practice/services/z", 260);

    private final Long id;
    private final String url;
    private final Integer fee;

    ServicePolicy(Long id, String url, Integer fee) {
        this.id = id;
        this.url = url;
        this.fee = fee;
    }

    public static ServicePolicy findByUrl(String url) {
        return Arrays.stream(values())
                .filter(it -> it.url.equals(url))
                .findFirst()
                .orElseThrow();
    }

    public static ServicePolicy findById(Long id) {
        return Arrays.stream(values())
                .filter(it -> it.id.equals(id))
                .findFirst()
                .orElseThrow();
    }

}