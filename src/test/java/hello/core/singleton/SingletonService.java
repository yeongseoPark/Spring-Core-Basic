package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();
    // static으로 클래스 레벨에 하나의 변수가 할당(객체가 아닌)
    // final로 선언했기 때문에 변하지 않는 값 됨

    public static SingletonService getInstance() {
        return instance; // 인스턴스의 참조를 꺼내기 위한 메서드: 항상 같은 인스턴스를 반환
    }
    // 생성의 비용 >>>>>>> 참조의 비용
    private SingletonService() {
        /* private 생성자 때문에 외부에서 new 키워드를 통해 생성하려면 컴파일 오류남 */
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
