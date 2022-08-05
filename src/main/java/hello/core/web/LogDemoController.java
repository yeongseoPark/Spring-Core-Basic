package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
//    private final ObjectProvider<MyLogger> myLoggerProvider; // MyLogger에 대한 DL
    private final MyLogger myLogger; // MyLogger에 대한 DL

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) { // HttpServeltRequest를 통해 요청URL받는다
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject(); // Http요청이 와서 MyLogger가 살아있는 상태에서 요청

        System.out.println("myLogger = " + myLogger.getClass()); // 가짜 myLogger가 들어있는 것을 확인가능
        myLogger.setRequestURL(requestURL); // requestURL 값을 myLogger에 저장

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
