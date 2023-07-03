package com.yonchando.model;

import lombok.Data;

@Data
public class Users {

    private Long userId;
    private Long chatId;
    private Long messageId;
    private String firstName;
    private String lastName;

}
