package com.yonchando.autobot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {
    private Long userId;
    private Long chatId;
    private Long groupId;
    private Integer messageId;
    private String firstName;
    private String lastName;
    private String username;
    private Boolean ignoreMe;
}
