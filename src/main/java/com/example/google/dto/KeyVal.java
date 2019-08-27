package com.example.google.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @author jwt
 * @date 2019-8-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyVal {
    @NotNull
    private Objects key;
    @NotNull
    private Object val;
}
