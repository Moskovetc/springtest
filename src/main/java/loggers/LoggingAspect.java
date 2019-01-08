package loggers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private Map<Class<?>, Integer> counter;

    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventmethods() {
    }


//    @Before("allLogEventmethods()")
//    public void logBefore(JoinPoint joinPoint) {
//        logger.info("BEFORE : " + joinPoint.getTarget().getClass().getSimpleName()
//                + " " + joinPoint.getSignature().getName());
//    }

    @Around("allLogEventmethods()")
    public Object logAfter(ProceedingJoinPoint pjp) throws Throwable {
        final Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
        final Object[] args = pjp.getArgs();
        final String methodName = pjp.getSignature().getName();

        if (!isUtilMethod(methodName)) {
            log.debug("{}(): {}", methodName, args);
        }

        final Object result = pjp.proceed();

        if (!isUtilMethod(methodName)) {
            log.debug("{}(): result={}", methodName, result);
        }
        return result;
    }

    private boolean isUtilMethod(String name) {
        return name.startsWith("get")
                || name.startsWith("set")
                || name.equals("toString")
                || name.equals("equals")
                || name.equals("hashCode");
    }
}
