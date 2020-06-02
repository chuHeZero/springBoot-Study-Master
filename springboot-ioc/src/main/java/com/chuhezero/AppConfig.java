package com.chuhezero;

import com.chuhezero.bean.MapperScan;
import com.chuhezero.bean.Monkey;
import com.chuhezero.bean.MyImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.chuhezero.bean")
@Import(MyImportBeanDefinitionRegistrar.class)
@MapperScan
public class AppConfig {

    //通过反射 获取到method.invoke 调取此方法，放入单例对象池(singletonObjects)中
    @Bean
    public Monkey monkey(){
        return new Monkey();
    }
}
