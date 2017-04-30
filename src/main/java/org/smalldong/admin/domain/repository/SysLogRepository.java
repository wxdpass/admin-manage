package org.smalldong.admin.domain.repository;

import org.smalldong.admin.domain.modle.SysLog;

import java.util.List;

/**
 * Created by xieqiang on 2016/9/17.
 */
public interface SysLogRepository {

    void add(SysLog sysLog);

    List<SysLog> list();

    void clear();
}
