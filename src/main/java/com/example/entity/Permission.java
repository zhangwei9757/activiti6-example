package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jwt
 * @date 2019-8-20
 * 权限表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission {
    private Integer id;
    /**
     * 权限名称
     */
    private String permName;
    /**
     * 权限标识
     */
    private String permTag;
    /**
     * 请求url
     */
    private String url;
}

