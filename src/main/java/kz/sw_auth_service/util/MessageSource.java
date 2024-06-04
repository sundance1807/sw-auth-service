package kz.sw_auth_service.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MessageSource {

    SUCCESS_REGISTRATION("Вы успешно зарегистрировались."),
    USER_NOT_FOUND("Пользователь '%s' не найден."),
    USERNAME_ALREADY_EXISTS("Пользователь с юзернеймом '%s' существует."),
    TELEGRAM_LINK_ALREADY_EXISTS("'%s' уже существует.");

    private final String messsage;

    public String getMessage(String... params) {
        return String.format(this.messsage, (Object[]) params);
    }
}
