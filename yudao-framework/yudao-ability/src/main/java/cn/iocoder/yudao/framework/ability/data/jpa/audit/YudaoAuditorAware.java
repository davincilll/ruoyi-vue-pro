package cn.iocoder.yudao.framework.ability.data.jpa.audit;

import cn.iocoder.yudao.framework.ability.web.web.core.util.WebFrameworkUtils;
import lombok.NonNull;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 自定义审计人员提供者
 * 
 * 实现 Spring Data JPA 的 AuditorAware 接口，用于自动填充 @CreatedBy 和 @LastModifiedBy 注解的字段
 *
 * @author 芋道源码
 */
public class YudaoAuditorAware implements AuditorAware<Long> {

    @Override
    @NonNull
    public Optional<Long> getCurrentAuditor() {
        // 从 WebFrameworkUtils 中获取当前登录用户
        Long userId = WebFrameworkUtils.getLoginUserId();
        
        // 转换为 Optional，如果用户未登录则返回 Empty
        return Optional.ofNullable(userId);
    }
} 