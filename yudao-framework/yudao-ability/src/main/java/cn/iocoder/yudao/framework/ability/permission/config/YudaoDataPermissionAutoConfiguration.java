package cn.iocoder.yudao.framework.ability.permission.config;

import cn.iocoder.yudao.framework.ability.data.mybatis.core.util.MyBatisUtils;
import cn.iocoder.yudao.framework.ability.permission.core.aop.DataPermissionAnnotationAdvisor;
import cn.iocoder.yudao.framework.ability.permission.core.db.DataPermissionRuleHandler;
import cn.iocoder.yudao.framework.ability.permission.core.rule.DataPermissionRule;
import cn.iocoder.yudao.framework.ability.permission.core.rule.DataPermissionRuleFactory;
import cn.iocoder.yudao.framework.ability.permission.core.rule.DataPermissionRuleFactoryImpl;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * 数据权限的自动配置类
 *
 * @author 芋道源码
 */
@AutoConfiguration
public class YudaoDataPermissionAutoConfiguration {
    // TODO: 基于JPA重构数据权限模块
    // TODO: 使用JPA的继承规则实现User的继承 实现多方User AdminUser和MemberUser和商家User
    @Bean
    public DataPermissionRuleFactory dataPermissionRuleFactory(List<DataPermissionRule> rules) {
        return new DataPermissionRuleFactoryImpl(rules);
    }

    @Bean
    public DataPermissionRuleHandler dataPermissionRuleHandler(MybatisPlusInterceptor interceptor,
                                                               DataPermissionRuleFactory ruleFactory) {
        // 创建 DataPermissionInterceptor 拦截器
        DataPermissionRuleHandler handler = new DataPermissionRuleHandler(ruleFactory);
        DataPermissionInterceptor inner = new DataPermissionInterceptor(handler);
        // 添加到 interceptor 中
        // 需要加在首个，主要是为了在分页插件前面。这个是 MyBatis Plus 的规定
        MyBatisUtils.addInterceptor(interceptor, inner, 0);
        return handler;
    }

    @Bean
    public DataPermissionAnnotationAdvisor dataPermissionAnnotationAdvisor() {
        return new DataPermissionAnnotationAdvisor();
    }

}
