package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // 이때 prototypeBean1 객체 생성
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2); // 서로 다름
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); // 만들고 그 이후 관리하지 않기에 close() 호출x -> 조회한 클라이언트가 직접 종료 호출해야 함
    }

    /*
    스프링 컨테이너 생성시점에 초기화 메서드가 실행되는 싱글톤 빈과 달리,
    프로토타입 스코프의 빈은 스프링 컨테이너에서 빈을 조회할때 생성되고 초기화 메서드 실행
     */

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
