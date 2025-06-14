package cn.iocoder.yudao.module.system.job;

import cn.iocoder.yudao.framework.ability.job.core.handler.JobHandler;
import cn.iocoder.yudao.framework.ability.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.framework.ability.tenant.core.job.TenantJob;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DemoJob implements JobHandler {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    @TenantJob // 标记多租户
    public String execute(String param) {
        System.out.println("当前租户：" + TenantContextHolder.getTenantId());
        List<AdminUserDO> users = adminUserMapper.selectList();
        return "用户数量：" + users.size();
    }

}
