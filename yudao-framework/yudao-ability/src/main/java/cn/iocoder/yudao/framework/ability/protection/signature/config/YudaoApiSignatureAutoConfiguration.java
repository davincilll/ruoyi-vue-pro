package cn.iocoder.yudao.framework.ability.protection.signature.config;

import cn.iocoder.yudao.framework.ability.protection.signature.core.aop.ApiSignatureAspect;
import cn.iocoder.yudao.framework.ability.protection.signature.core.redis.ApiSignatureRedisDAO;
import cn.iocoder.yudao.framework.ability.redis.config.YudaoRedisAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * HTTP API 签名的自动配置类
 *
 * @author Zhougang
 */
@AutoConfiguration(after = YudaoRedisAutoConfiguration.class)
public class YudaoApiSignatureAutoConfiguration {

    @Bean
    public ApiSignatureAspect signatureAspect(ApiSignatureRedisDAO signatureRedisDAO) {
        return new ApiSignatureAspect(signatureRedisDAO);
    }

    @Bean
    public ApiSignatureRedisDAO signatureRedisDAO(StringRedisTemplate stringRedisTemplate) {
        return new ApiSignatureRedisDAO(stringRedisTemplate);
    }

}
