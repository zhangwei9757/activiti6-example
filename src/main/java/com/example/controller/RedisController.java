package com.example.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jwt
 * @date 2019年8月19日
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加缓存
     */
    @GetMapping(value = "/add")
    @ApiOperation(value = "添加缓存", tags = {"Redis"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "缓存key", required = true, paramType = "query", dataType = "Object"),
            @ApiImplicitParam(name = "value", value = "缓存value", required = true, paramType = "query", dataType = "Object")
    })
    public void saveRedis(@RequestParam("key") Object key, @RequestParam("value") Object val) {
        stringRedisTemplate.opsForValue().set(key.toString(), val.toString());
    }

    /**
     * 获取缓存
     *
     * @return
     */
    @GetMapping(value = "/get")
    @ApiOperation(value = "获取缓存", tags = {"Redis"})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "缓存key", required = true, paramType = "query", dataType = "Object")
    })
    public String getRedis(@RequestParam("key") Object key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}