package org.example.homework68.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("user", "Пользователь"),     // 0
    MODER("moder", "Модератор"),      // 1
    ADMIN("admin", "Администратор");  // 2

    private final String serviceName;
    private final String displayName;
}
