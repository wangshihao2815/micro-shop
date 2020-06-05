package com.leyou.auth.client;

import com.leyou.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author wangshihao
 * @Date 2020/3/27
 * @Since 1.0.0
 */

@FeignClient("user-service")
public interface UserClient extends UserApi {

}
