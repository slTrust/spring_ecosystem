package hello;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

// @Aspect 声明它是一个切面
// @Configuration 是跟 spring有关的配置 运行的时候把它拷贝进来
@Aspect
@Configuration
public class CacheAspect {
    Map<String,Object> cache = new HashMap<>();

    @Around("@annotation(hello.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("method is called");

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String methodName = signature.getName();
        Object cacheValue = cache.get(methodName);

        if(cacheValue != null){
            System.out.println("cacheValue");
            return cacheValue;
        }else{
            System.out.println("read realValue");
            Object realValue = joinPoint.proceed();
            cache.put(methodName,realValue);
            return realValue;
        }

    }
}
