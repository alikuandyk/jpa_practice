package org.example.homework68.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    CREATED("создан"),
    PAID("оплачено"),
    IN_DELIVERY("в доставке"),
    DELIVERED("доставлен");

    private final String displayName;
}
