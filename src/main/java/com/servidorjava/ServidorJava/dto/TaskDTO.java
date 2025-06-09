package com.servidorjava.ServidorJava.dto;

import com.servidorjava.ServidorJava.model.User;

public record TaskDTO(
        String title,
        String description,
        User user
) {

}
