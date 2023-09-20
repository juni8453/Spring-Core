package hello.core.web;

import hello.core.common.MyLoger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoServie {

  private final ObjectProvider<MyLoger> myLogerProvider;

  public void logic(String id) {
    MyLoger myLoger = myLogerProvider.getObject();
    myLoger.log("service id = " + id);
  }
}
