package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

  // 싱글톤 빈의 경우, 스프링 컨테이너 생성 시점에 초기화 메서드가 실행됨.
  // 같은 객체이므로 init 1회 실행 후, destroy.
  @Test
  void singletonBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
    SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
    SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
    System.out.println("singletonBean1 = " + singletonBean1);
    System.out.println("singletonBean2 = " + singletonBean2);
    Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);

    ac.close();
  }

  @Scope("singleton")
  static class SingletonBean {
    @PostConstruct
    public void init() {
      System.out.println("SingletonBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("SingletonBean.destroy");
    }
  }
}
