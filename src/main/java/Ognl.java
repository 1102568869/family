import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/30.
 * Ognl工具类，主要是为了在ognl表达式访问静态方法时可以减少长长的类名称编写<br/>
 * Ognl访问静态方法的表达式为: @class@method(args)<br/>
 * 示例使用:<br/>
 * <p>
 * <pre>
 *     &lt;if test="@Ognl@isNotEmpty(userId)">
 *         and user_id = #{userId}
 *     &lt;/if>
 * </pre>
 */
public class Ognl {
    private Ognl() {
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            return StringUtils.isEmpty((String) o);
        }
        if (o instanceof Collection) {
            return CollectionUtils.isEmpty((Collection) o);
        }
        if (o instanceof Map) {
            return MapUtils.isEmpty((Map) o);
        }
        return false;
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }
}
