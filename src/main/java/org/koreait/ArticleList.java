package org.koreait;

public class ArticleList {

    int id;
    String title;
    String body;
    String time;
    String time2;
    String writerName;

    ArticleList(String title, String body, int id, String time, String time2, String writerName) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.time = time;
        this.time2 = time2;
        this.writerName = writerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getWriterName() {
        return writerName;
    }

    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }

}
