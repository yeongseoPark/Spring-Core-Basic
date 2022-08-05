package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// MyLogger의 가짜 프록시 클래스를 만들어두고, Http request와 관계 없이 가짜 프록시 클래스를 다른 빈에 미리 주입
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL; // 빈 생성시점에 requestURL 정보를 알 수 없으므로, 외부에서 setter로 입력받는다
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); // 빈 생성되는 시점에 uuid를 생성해서 저장해둔다 -> 다른 HTTP 요청과 구분
        System.out.println("[" + uuid + "] request scope bean create:  "+ this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:  "+ this);
    }
}
