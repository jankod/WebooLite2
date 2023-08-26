package hr.ja.weboo;

import lombok.Getter;
import lombok.experimental.Delegate;
import spark.Request;

public class WebooRequest {

    @Delegate
    private final Request sparkRequest;

    public WebooRequest(Request sparkRequest) {
        this.sparkRequest = sparkRequest;
    }
}
