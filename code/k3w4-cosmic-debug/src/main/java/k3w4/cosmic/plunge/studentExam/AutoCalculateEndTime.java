package k3w4.cosmic.plunge.studentExam;

import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;


import kd.bos.entity.datamodel.events.PropertyChangedArgs;


import kd.bos.form.plugin.AbstractFormPlugin;
import kd.sdk.plugin.Plugin;

/**
 * 功能：
 * 自动计算考试结束时间插件
 * 根据考试开始时间和考试时长自动计算考试结束时间
 */
public class AutoCalculateEndTime extends AbstractFormPlugin implements Plugin {
    
    // 字段标识
    private static final String FIELD_START_TIME = "k3w4_start_time"; // 考试开始时间
    private static final String FIELD_EXAM_TIME = "k3w4_time"; // 考试时长
    private static final String FIELD_END_TIME = "k3w4_end_time"; // 考试结束时间
    
    /**
     * 监听属性变化事件
     */
    @Override
    public void propertyChanged(PropertyChangedArgs e) {
        super.propertyChanged(e);
        
        // 获取发生变化的属性名
        String propertyName = e.getProperty().getName();
        
        // 判断变化的是否是开始时间或考试时长
        if (FIELD_START_TIME.equals(propertyName) || FIELD_EXAM_TIME.equals(propertyName)) {
            calculateEndTime();
        }
    }
    
    /**
     * 计算考试结束时间
     */
    private void calculateEndTime() {
        // 获取开始时间
        Date startTime = (Date) this.getModel().getValue(FIELD_START_TIME);
        
        // 获取考试时长
        Object examTimeObj = this.getModel().getValue(FIELD_EXAM_TIME);

        if (startTime == null || examTimeObj == null) {
            return;
        }
        
        // 将考试时长转换为整数
        int examTime = 0;
        if (examTimeObj instanceof Number) {
            examTime = ((Number) examTimeObj).intValue();
        }
        
        // 计算结束时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.MINUTE, examTime);
        Date endTime = calendar.getTime();
        
        // 更新结束时间字段
        this.getModel().setValue(FIELD_END_TIME, endTime);
        // 刷新结束时间字段显示
        this.getView().updateView(FIELD_END_TIME);
    }
    
    /**
     * 表单数据绑定后，初始化计算结束时间
     */
    @Override
    public void afterBindData(EventObject e) {
        super.afterBindData(e);
        calculateEndTime();
    }
}
