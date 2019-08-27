package com.example.enums;

/**
 * Created by jwt on 2019-8-16
 * <p>
 */
public enum JwtExpiration {
    /**
     * 天过期时间
     */
    DAY(24 * 60 * 60L),
    /**
     * 年过期时间
     */
    YEAR(365 * 24 * 60 * 60L);

    private long expiration;

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    JwtExpiration(long expiration) {
        this.expiration = expiration;
    }
}
