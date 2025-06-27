package cn.iocoder.yudao.framework.ability.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.Set;

/**
 * 多租户配置
 *
 * @author 芋道源码
 */
@ConfigurationProperties(prefix = "yudao.tenant")
@Data
public class TenantProperties {

    /**
     * 租户是否开启
     */
    private static final Boolean ENABLE_DEFAULT = true;

    /**
     * 是否开启
     */
    // TODO: 伪需求，对于这个系统应当是始终开启租户的，从设计当初 是否使用租户基类就决定了是否开启
    private Boolean enable = ENABLE_DEFAULT;

    /**
     * 需要忽略多租户的请求
     * 默认情况下，每个请求需要带上 tenant-id 的请求头。但是，部分请求是无需带上的，例如说短信回调、支付回调等 Open API！
     */
    // TODO: 新增(No)RequiredLogin 和 (No)RequiredTenant 注解 在容器启动的时候进行扫描并添加到一个集合中供filter中去使用
            // 新增配置项default的行为，如果没有任何注解是采用哪一个，考虑性能优化
    private Set<String> ignoreUrls = Collections.emptySet();

    /**
     * 需要忽略多租户的表
     *
     * 即默认所有表都开启多租户的功能，所以记得添加对应的 tenant_id 字段哟
     */
    private Set<String> ignoreTables = Collections.emptySet();

    /**
     * 需要忽略多租户的 Spring Cache 缓存
     *
     * 即默认所有缓存都开启多租户的功能，所以记得添加对应的 tenant_id 字段哟
     */
    private Set<String> ignoreCaches = Collections.emptySet();

}
