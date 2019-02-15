package top.huhuiyu.oss.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 控制器切点定义
 * 
 * @author 胡辉煜
 */
public abstract class BaseControllerAop {

  @Pointcut("execution(public * top.huhuiyu.oss.controller..*.*(..))")
  public void controller() {
  }

}
