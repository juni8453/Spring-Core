package hello.core.web;

import hello.core.common.MyLoger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoServie {

  private final MyLoger myLoger;

  public void logic(String id) {
    myLoger.log("service id = " + id);
  }
}
