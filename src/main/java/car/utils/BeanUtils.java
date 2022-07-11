package car.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BeanUtils {
    private BeanUtils() throws Throwable {
        throw new Throwable(SystemException.CAN_NOT_INSTANCE_ERROR.getMessage());
    }
    public static <T> T copy(Object source, Class<T> clazz){
        if (null == source) {
            return null;
        }
        String jsonString = JSONObject.toJSONString(source);
        return JSONObject.parseObject(jsonString, clazz);
    }

    public static <T> List<T> copyList(Collection<?> sourceList, Class<T> clazz){
        if (CollectionUtils.isEmpty(sourceList)){
            return Collections.emptyList();
        }
        List<T> list = new ArrayList<>(sourceList.size());
        for (Object o : sourceList) {
            list.add(copy(o, clazz));
        }
        return list;
    }
}
