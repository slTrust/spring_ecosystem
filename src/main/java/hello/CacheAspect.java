package hello;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

// @Aspect 声明它是一个切面
// @Configuration 是跟 spring有关的配置 运行的时候把它拷贝进来
@Aspect
@Configuration
public class CacheAspect {
    @Around("@annotation(hello.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("method is called");
        return joinPoint.proceed();
    }
}
