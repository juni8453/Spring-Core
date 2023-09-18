package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 수동 AppConfig 설정 클래스를 Bean 으로 등록하지 않기 위해 삭제하는 것 대신 제외 (컴포넌트 스캔의 범위를 지정해 줄 수 있음)
// @Component 를 찾아 Bean 으로 등록하겠다고 선언하는 어노테이
@ComponentScan(
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
@Configuration
public class AutoAppConfig {
}
