package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jwt
 * @date 2019-8-20
 * 角色类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private Integer id;
    private String roleName;
    private String roleDesc;
}

