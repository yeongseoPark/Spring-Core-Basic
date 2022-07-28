package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    /* 스프링 컨테이너인 ApplicationContext 인터페이스의 구현체인 AnnotationConfigApplicationContext
    getBeanDefinition함수는 AliasRegistry 인터페이스를 상속받으며, ApplicationContext와는 연관이 없다
    그래서 getBeanDefinition 을 ApplicationContext 타입으로 사용할 수는 없다
    반면 클라이언트인 AnnotationConfigApplicationContext는 계층 구조를 통해 , 여러 인터페이스에 선언된 메서드를 가져와 사용 가능
    한 반면, 하나의 인터페이스로는 전체 메서드를 호출할 수 없다
    https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FrnZsd%2FbtrzyWQIVWJ%2F5kkaiefO9SSkIUl6nfQcuk%2Fimg.png
    */

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // .getBeanDefinitionNames() : 스프링에 등록된 모든 bean이름 조회
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); // getBean() : bean 이름으로 bean 객체를 조회한ㄷ
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

//            ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
//            ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
