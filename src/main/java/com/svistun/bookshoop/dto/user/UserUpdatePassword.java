package com.svistun.bookshoop.dto.user;

import lombok.Data;

@Data
public class UserUpdatePassword {
    private String oldPassword;
    private String newPassword;
    private String checkNewPassword;
}
