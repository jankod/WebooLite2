package hr.ja.weboo;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.experimental.Delegate;
import spark.Request;

public class WebooRequest {

    @Delegate
    private final Request sparkRequest;

    public WebooRequest(Request sparkRequest) {
        this.sparkRequest = sparkRequest;
    }

    public <T> T bindJsonTo(Class<T> modelClass) throws JsonProcessingException {
        String json = Context.req().body();
        return WebooUtil.fromJson(json, modelClass);
    }

}
