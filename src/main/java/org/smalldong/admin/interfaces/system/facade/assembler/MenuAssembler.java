package org.smalldong.admin.interfaces.system.facade.assembler;

import org.smalldong.admin.domain.modle.Menu;
import org.smalldong.admin.infrastructure.BeanUtil;
import org.smalldong.admin.interfaces.system.facade.commondobject.MenuCreateCommand;
import org.smalldong.admin.interfaces.system.facade.commondobject.MenuUpdateCommond;

/**
 * Created by xieqiang on 2016/10/30.
 */
public class MenuAssembler {

    public static Menu updateCommendToDomain(String id, MenuUpdateCommond updateCommond) {
        Menu menu=new Menu();
      BeanUtil.copeProperties(updateCommond,menu);
        menu.setId(id);
        return menu;
    }

    public static Menu createCommendToDomain(MenuCreateCommand creteCommand){
        Menu menu=new Menu();
        BeanUtil.copeProperties(creteCommand,menu);
        return menu;
    }
}
