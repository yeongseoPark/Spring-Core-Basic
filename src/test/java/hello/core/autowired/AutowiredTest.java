package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        /* Member는 스프링 빈이 아니다! */

        @Autowired(required = false) // required가 false가 아니면 빈에 Member가 없기에 오류가 난다
        // required가 false이면, 의존관계가 없을 시 메서드 자체가 아예 호출이 되지 않는다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) { // 호출은되지만, 스프링 빈이 없으면 null로 들어온다
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) { // 스프링빈이 없으면 Optional.empty
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
