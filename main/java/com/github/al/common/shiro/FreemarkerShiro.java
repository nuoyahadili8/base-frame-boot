package com.github.al.common.shiro;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;

/**
 * @Author: chenyi
 * @Description: freemarker版shiro标签
 * @Date: 2018/1/1  20:21
 */

@Component
public class FreemarkerShiro implements InitializingBean {

    @Autowired
    private Configuration configuration;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 加上这句后，可以在页面上使用shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}