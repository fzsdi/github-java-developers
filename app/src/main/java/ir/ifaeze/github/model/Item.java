package ir.ifaeze.github.model;

import com.google.gson.annotations.SerializedName;

public class Item {
    private String login;
    @SerializedName("avatar_url")
    private String avatar;
    @SerializedName("html_url")
    private String url;

    public Item(String login, String avatar, String url) {
        this.login = login;
        this.avatar = avatar;
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getUrl() {
        return url;
    }
}

