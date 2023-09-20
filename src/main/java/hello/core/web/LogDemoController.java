package hello.core.web;

import hello.core.common.MyLoger;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

  private final LogDemoServie logDemoServie;
  private final ObjectProvider<MyLoger> myLogerProvider;

  @GetMapping("/log")
  @ResponseBody
  public String logDemo(HttpServletRequest request) {
    String requestUrl = request.getRequestURI();
    MyLoger myLoger = myLogerProvider.getObject();
    myLoger.setRequestUrl(requestUrl);

    myLoger.log("controller test");
    logDemoServie.logic("Test ID");

    return "OK";
  }
}
