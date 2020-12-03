package xyz.zzyitj.ils.config;

import org.apache.catalina.Host;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

///**
// * @author intent zzy.main@gmail.com
// * @date 2020/11/28 15:16
// * @since 1.0
// */
//@Component
public class PrintMapperComponent implements ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(PrintMapperComponent.class);
    private boolean isPrint;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!isPrint) {
            isPrint = true;
            printAllMapping(applicationContext);
        }
    }

    private void printAllMapping(ApplicationContext context) {
        RequestMappingHandlerMapping handlerMapping = context.getBean(
                "requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        try {
            Method getMappingRegistryMethod = handlerMapping.getClass().getSuperclass().getSuperclass()
                    .getDeclaredMethod("getMappingRegistry");
            getMappingRegistryMethod.setAccessible(true);
            Object mappingRegistryObject = getMappingRegistryMethod.invoke(handlerMapping);
            Field registryField = mappingRegistryObject.getClass().getDeclaredField("registry");
            registryField.setAccessible(true);
            Map<RequestMappingInfo, Object> registry =
                    (Map<RequestMappingInfo, Object>) registryField.get(mappingRegistryObject);
            String serverUrl = getServerUrl(context);
            registry.forEach((info, o) -> {
                try {
                    Field handlerMethodField = o.getClass().getDeclaredField("handlerMethod");
                    handlerMethodField.setAccessible(true);
                    HandlerMethod handlerMethod = (HandlerMethod) handlerMethodField.get(o);
                    Field directUrlsField;
                    try {
                        directUrlsField = o.getClass().getDeclaredField("directUrls");
                    } catch (NoSuchFieldException e) {
                        directUrlsField = o.getClass().getDeclaredField("directPaths");
                    }
                    directUrlsField.setAccessible(true);
                    List<String> directUrls;
                    try {
                        directUrls = (List<String>) directUrlsField.get(o);
                    } catch (ClassCastException e) {
                        directUrls = ((HashSet<String>) directUrlsField.get(o))
                                .parallelStream()
                                .collect(Collectors.toList());
                    }
                    String requestType = "REQUEST";
                    if (!info.getMethodsCondition().isEmpty()) {
                        requestType = info.getMethodsCondition().getMethods().iterator().next().name();
                    }
                    for (String url : directUrls) {
                        logger.info("{} {} {}{}", handlerMethod, requestType, serverUrl, url);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getServerUrl(ApplicationContext context) {
        AnnotationConfigServletWebServerApplicationContext serverApplicationContext =
                (AnnotationConfigServletWebServerApplicationContext) context;
        String host = ((TomcatWebServer) serverApplicationContext.getWebServer())
                .getTomcat().getServer().getAddress();
        int port = ((TomcatWebServer) serverApplicationContext.getWebServer()).getPort();
        Host tomcatHost = ((TomcatWebServer) serverApplicationContext.getWebServer()).getTomcat().getHost();
        String contextPath = tomcatHost.findChildren()[0].getName();
        return String.format("http://%s:%d%s", host, port, contextPath);
    }
}
