package io.ryspring.tips.springtips.configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ryspring.tips.springtips.configuration.ObjectMapperConfiguration.SpringJacksonPropertiesMissing;
import java.util.Collections;
import java.util.Map;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
@AutoConfigureBefore(JacksonAutoConfiguration.class)
@ConditionalOnMissingBean(ObjectMapper.class)
@Conditional(SpringJacksonPropertiesMissing.class)
public class ObjectMapperConfiguration {
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper()
        .disable(FAIL_ON_UNKNOWN_PROPERTIES)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL);
  }

  static class SpringJacksonPropertiesMissing extends SpringBootCondition {
    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context,
        AnnotatedTypeMetadata metadata) {
      return new ConditionOutcome(hasJackonPropertiesDefined(context),
          "Spring Jackson property based configuration missing");
    }

    private boolean hasJackonPropertiesDefined(ConditionContext context) {
      return Binder.get(context.getEnvironment())
          .bind(ConfigurationPropertyName.of("spring.jackson"),
              Bindable.of(Map.class))
          .orElse(Collections.emptyMap())
          .isEmpty();
    }
  }
}
