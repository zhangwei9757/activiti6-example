package com.example.xml.dto;

import lombok.*;

/**
 * <br/>功能: 和xml对应的实体类
 * <br/>版本: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    private String id;
    private String name;
    private String price;
    private String author;
}
