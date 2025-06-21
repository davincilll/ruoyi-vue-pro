package cn.iocoder.yudao.framework.ability.data.jpa.tenant;

import cn.iocoder.yudao.framework.ability.tenant.core.context.TenantContextHolder;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * 基于 Hibernate 的多租户标识解析器实现
 * 
 * 从 {@link TenantContextHolder} 中获取租户ID并提供给Hibernate
 * 用于自动填充实体中标注 @TenantId 的字段
 *
 * @author 芋道源码
 */
public class YudaoCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver<String> {

    private static final String DEFAULT_TENANT_ID = "0"; // 默认租户ID，作为未指定租户时的备选值
    
    @Override
    public String resolveCurrentTenantIdentifier() {
        // 从上下文中获取租户ID
        Long tenantId = TenantContextHolder.getTenantId();
        
        // 如果上下文中存在租户ID，则使用它，否则使用默认值
        return tenantId != null ? String.valueOf(tenantId) : DEFAULT_TENANT_ID;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        // 返回false，表示允许在切换租户ID时继续使用现有的Hibernate会话
        // 如果返回true，则会在租户ID变化时抛出异常
        return false;
    }
    boolean isRoot(T tenantId) {
        // TODO:在配置缓存工具里添加一个Root租户配置
        return false;
    }
} 