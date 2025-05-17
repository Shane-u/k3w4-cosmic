package k3w4.cosmic.plunge.studentCourses;

import kd.bos.base.AbstractBasePlugIn;
import kd.bos.context.RequestContext;
import kd.sdk.plugin.Plugin;

import java.util.EventObject;

/**
 * 基础资料插件
 */
public class ShowMessage extends AbstractBasePlugIn implements Plugin {

    public void afterCreateNewData(EventObject e){
        super.afterCreateNewData(e);
        // 获取当前操作者的姓名
        RequestContext rc  = RequestContext.get();
        String nowUser = rc.getUserName();

        // 展示消息
        this.getView().showMessage("你好" + nowUser + "欢迎来到学生信息管理页面");
    }


}