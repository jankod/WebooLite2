package hr.ja.weboo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.qute.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class WebooUtil {

    public static final String this_object_name = "this";

    static {
        Engine engine = Engine.builder()
              .strictRendering(true)
              .addParserHook(p -> p.addContentFilter(s -> {
                  s = StringUtils.replace(s, "${", "{" + this_object_name + ".");
                  return s;
              }))
              //.addSectionHelper(new LoopSectionHelper.Factory())
              .addDefaultSectionHelpers()
              //.addValueResolver(new WidgetResolver())
              .addResultMapper(new QuteWidgetResultMapper())

              .addDefaultValueResolvers()
              .addParserHook(new Qute.IndexedArgumentsParserHook())
              .addResultMapper(new HtmlEscaper(ImmutableList.of("text/html")))
              .addValueResolver(new ReflectionValueResolver())
              .addValueResolver(ValueResolvers.rawResolver())

              // for freemarker style ${}
              .build();

        Qute.setEngine(engine);

        Qute.enableCache();
    }


    public static String escape(String text) {
        return StringEscapeUtils.escapeHtml4(text);
    }


    public static String qute(String template, Object... params) {
        Qute.Fmt fmt = Qute.fmt(template);
        fmt.variant(Variant.forContentType(Variant.TEXT_HTML));
        return fmt.dataArray(params)
              .render();

    }

    public static String qute(String template, Map<String, Object> map) {
        Qute.Fmt fmt = Qute.fmt(template);
        fmt.variant(Variant.forContentType(Variant.TEXT_HTML));
        return fmt.dataMap(map).render();
    }

    /**
     * @param template Use template like this: Hello {1} {2}!
     * @param widgets  array of widgets
     * @return html
     */
    public static String qute(String template, Widget... widgets) {
        return Qute.fmt(template, (Object[]) widgets);
    }


    /**
     * return Qute.Map.of("this", this)
     *
     * @param template
     * @param thisObject
     * @return
     */
    public static String quteThis(String template, Object thisObject) {
        Qute.Fmt fmt = Qute.fmt(template);
        fmt.variant(Variant.forContentType(Variant.TEXT_HTML));

        return fmt.dataMap(Map.of(this_object_name, thisObject)).render();
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //objectMapper.configure(DeserializationFeature.FAIL_, false);
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);


    }

    @SneakyThrows
    public static String toJson(Object o) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
    }

    @SneakyThrows
    public static String toJsonRaw(Object o) {
        return objectMapper.writer().writeValueAsString(o);
    }


    private static ValidatorFactory factory;

    public static <T> Set<ConstraintViolation<T>> validate(T user) {
        if (factory == null)
            factory = Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();
        return validator.validate(user);
    }

    public static <T> T fromJson(String json, Class<T> aClass) throws JsonProcessingException {
        return objectMapper.readValue(json, aClass);
    }


    /**
     * Template {0} {1} ...
     *
     * @param template
     * @param params
     * @return
     */
    public static String format(String template, Object... params) {
        MessageFormat f = new MessageFormat(template);
        return f.format(params);
    }

    private static long handlerIdCounter = 1;

    public static String eventHandlerNewId(Class aClass) {
        return aClass.getSimpleName() + "_" + handlerIdCounter++;
    }

    private static long idCounter = 1;

    public static String wigetNewId(Class<? extends Widget> aClass) {
        // TODO : use UUID maybe
        return aClass.getSimpleName() + "_" + idCounter++;
    }

//    public static String pageNewId(Class<? extends Page> aClass) {
//        return aClass.getSimpleName() + "_" + RandomStringUtils.randomNumeric(10);
//    }


    public static void printStackTraceError(Exception e) {

        StackTraceElement element = e.getStackTrace()[0];
        String fileName = element.getFileName();
        int lineNumber = element.getLineNumber();
        log.debug("Datoteka: " + fileName);
        log.debug("Linija: " + lineNumber);

        String cn = element.getClassName().replace('.', '/') + ".class";
        URL u = WebooUtil.class.getClassLoader().getResource(cn);
        log.debug(u.toString().replace(".class", ".java") + ":" + element.getLineNumber());


    }


    public static String createPageId() {
        return UUID.randomUUID().toString();
    }
}
