package hr.ja.weboo;

import lombok.Getter;

public interface IPage {

    default void setTitle(String  title) {
        PageRequestContext.setTitle(title);
    }
}
