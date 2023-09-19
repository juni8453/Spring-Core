package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 수동 AppConfig 설정 클래스를 Bean 으로 등록하지 않기 위해 삭제하는 것 대신 제외 (컴포넌트 스캔의 범위를 지정해 줄 수 있음)
// @Component 를 찾아 Bean 으로 등록하겠다고 선언하는 어노테이션
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
@Configuration
public class AutoAppConfig {
}


// 예상
// 1. @SpringBootTest 사용하지 않을 때
/*   직접 AutoAppConfig 를 컨테이너에 Bean 으로 등록 -> 테스트 실행(Run Spring) -> @ComponentScan 으로 인해 컴포넌트 스캔 발생
      -> Fix, RateDiscountPolicy 등 @Component 를 찾아 Bean 으로 등록 -> 테스트 로직 수행

   2. @SpringBootTest 사용할 때
     테스트 실행(Run Spring) -> @SpringBootTest 에 의해 컴포넌트 스캔 발생 -> @Component 를 찾아 Bean 등록 -> 테스트 로직 수행
* */


// 결론
// 자동 주입을 사용하면서 테스트 코드 작성할 때 직접 Spring Container 를 띄워 사용하려면 컴포넌트 스캔 과정이 있어야하기 때문에
// 설정 클래스에서 @ComponetScan 을 붙여 컴포넌트 스캔 과정을 유도해야한다. 컴포넌트 스캔이 없다면 Bean 등록이 안되서 다른 Bean 을 사용할 수 없기 때문
// 그냥 테스트 코드에서 @SpringBootTest 등 스프링을 사용하도록 하면 이렇게 안해도 되는 것..