package tech.washmore.family.service.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import tech.washmore.BaseTest;
import tech.washmore.family.FamilyStarter;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/8
 */

public class ApiOuter4Wx extends BaseTest {
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;
    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testOne() throws Exception {
        StringBuffer sb = new StringBuffer("");
        sb.append("const HOST = 'http://localhost:8888';\n");
        sb.append("//const HOST = 'http://washmore.hellofqs.com';\n");
        sb.append("export default {\n");
        Map<RequestMappingInfo, HandlerMethod> map = this.handlerMapping.getHandlerMethods();
        Iterator<?> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<RequestMappingInfo, HandlerMethod> entry = (Map.Entry) iterator.next();
            RequestMappingInfo info = entry.getKey();
            HandlerMethod method = entry.getValue();
            if (method.getBeanType().getPackage().getName().startsWith(FamilyStarter.class.getPackage().getName())) {
                String uri = info.getPatternsCondition().getPatterns().iterator().next();
                String key = uri.replace("/", "_");
                sb.append("    " + key + ": HOST + '" + uri + "',\n");
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("\n}");
        System.out.println(sb.toString());
        String rootpath = applicationContext.getClassLoader().getResource("").getPath();
        String root = rootpath.substring(0, rootpath.lastIndexOf("/family")) + "/family";
        String out = root + "/wx/utils/url.js";
        File outfile = new File(out);
        if (!outfile.exists()) {
            outfile.createNewFile();
        }
        FileWriter writer = new FileWriter(outfile);
        writer.write(sb.toString());
        writer.close();
    }
}
