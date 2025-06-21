package cn.iocoder.yudao.framework.ability.data.jpa.config;

import cn.iocoder.yudao.framework.ability.data.jpa.tenant.YudaoCurrentTenantIdentifierResolver;
import cn.iocoder.yudao.framework.ability.data.jpa.audit.YudaoAuditorAware;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

/**
 * JPA 自动配置类
 * 
 * 主要配置:
 * 1. 租户识别 - 通过 CurrentTenantIdentifierResolver 设置多租户上下文
 * 2. 审计功能 - 通过 AuditorAware 实现填充创建者和更新者
 * 
 * @author 芋道源码
 */
@AutoConfiguration
@ConditionalOnClass({EntityManager.class, JpaRepository.class})
public class YudaoJpaAutoConfiguration {

    /**
     * JPA审计配置，启用JPA审计功能
     */
    @Configuration
    @EnableJpaAuditing
    public static class JpaAuditingConfiguration {
        
        /**
         * 自定义审计人员提供者
         * 用于自动填充 @CreatedBy 和 @LastModifiedBy 注解标记的字段
         * 
         * @return AuditorAware 实现，提供当前用户ID
         */
        @Bean
        public AuditorAware<Long> auditorAware() {
            return new YudaoAuditorAware();
        }
    }
    
    /**
     * 配置Hibernate多租户标识解析器
     * 用于自动填充 @TenantId 注解标记的字段
     * 
     * @return Hibernate属性定制器，添加多租户标识解析器
     */
    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer() {
        return (hibernateProperties) -> {
            // 配置Hibernate使用自定义的租户标识解析器
            hibernateProperties.put(
                    AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER,
                    new YudaoCurrentTenantIdentifierResolver()
            );
        };
    }
} 