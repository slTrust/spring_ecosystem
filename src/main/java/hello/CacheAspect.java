package hello;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

// @Aspect 声明它是一个切面
// @Configuration 是跟 spring有关的配置 运行的时候把它拷贝进来
@Aspect
@Configuration
public class CacheAspect {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Around("@annotation(hello.anno.Cache)")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("method is called");

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        String methodName = signature.getName();
        Object cacheValue = redisTemplate.opsForValue().get(methodName);

        if(cacheValue != null){
            System.out.println("cacheValue");
            return cacheValue;
        }else{
            System.out.println("read realValue");
            Object realValue = joinPoint.proceed();
            redisTemplate.opsForValue().set(methodName,realValue);
            return realValue;
        }

    }
}
