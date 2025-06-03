package web.config;

// Импортируем необходимые классы и аннотации для конфигурации Spring MVC и Thymeleaf
import org.springframework.context.ApplicationContext; // Контекст приложения Spring
import org.springframework.context.annotation.Bean; // Для определения бинов
import org.springframework.context.annotation.ComponentScan; // Для сканирования компонентов
import org.springframework.context.annotation.Configuration; // Обозначает класс конфигурации Spring
import org.springframework.web.servlet.config.annotation.EnableWebMvc; // Включает поддержку MVC
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry; // Регистрация резолверов представлений
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer; // Интерфейс для настройки MVC

// Импорты для Thymeleaf
import org.thymeleaf.spring5.SpringTemplateEngine; // Основной движок шаблонов Thymeleaf
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver; // Разрешатель шаблонов Thymeleaf
import org.thymeleaf.spring5.view.ThymeleafViewResolver; // Вью-резолвер для Thymeleaf

// Объявляем класс как конфигурацию Spring
@Configuration
// Включаем поддержку Spring MVC
@EnableWebMvc
// Указываем пакет для сканирования на компоненты
@ComponentScan("web")
public class WebConfig implements WebMvcConfigurer {

    // Внедряем ApplicationContext через конструктор для использования в настройках шаблонов
    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Создаем и настраиваем бин для разрешения шаблонов Thymeleaf.
     * Указываем путь к папке с HTML-шаблонами.
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        // Передаем ApplicationContext для поиска ресурсов
        templateResolver.setApplicationContext(applicationContext);
        // Указываем папку, где хранятся шаблоны (относительно веб-корня)
        templateResolver.setPrefix("/WEB-INF/pages/");
        // Указываем расширение файлов шаблонов
        templateResolver.setSuffix(".html");
        //устанавливает кодировку символов для шаблонов
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    /**
     * Создаем и настраиваем движок шаблонов Thymeleaf.
     * Он использует ранее созданный resolver.
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        // Устанавливаем разрешатель шаблонов
        templateEngine.setTemplateResolver(templateResolver());

        // Включаем поддержку Expression Language (EL) в Spring EL Compiler для повышения производительности
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    /**
     * Настраиваем ViewResolver, который будет связывать имена представлений с шаблонами Thymeleaf.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        // Устанавливаем движок шаблонов, который будет использоваться при рендеринге страниц
        resolver.setTemplateEngine(templateEngine());
        // Регистрируем этот резолвер в системе MVC
        registry.viewResolver(resolver);
        // Устанавливает кодировку символов для шаблонов (UTF-8)
        resolver.setCharacterEncoding("UTF-8");
        // Устанавливает тип содержимого и кодировку для HTTP-ответа (HTML, UTF-8)
        resolver.setContentType("text/html; charset=UTF-8");
    }
}