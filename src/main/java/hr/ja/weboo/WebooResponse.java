package hr.ja.weboo;

import hr.ja.weboo.js.JsCommand;
import lombok.experimental.Delegate;
import spark.Response;

public class WebooResponse {

    @Delegate
    private final Response sparkResponse;

    public WebooResponse(Response sparkResponse) {
        this.sparkResponse = sparkResponse;
    }

}
