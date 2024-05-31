package com.example.springbootpubsub.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class NotificationVO {
    private String accountNumber;
    private String template;
    private String notificationType;
    private List<Field> fields;
}
