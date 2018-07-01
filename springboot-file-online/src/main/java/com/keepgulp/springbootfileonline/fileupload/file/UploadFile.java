package com.keepgulp.springbootfileonline.fileupload.file;

public class UploadFile {
    private String name;

    private int type;

    private String time;

    private String path;

    public UploadFile(String name, int type, String time, String path) {
        this.name = name;
        this.type = type;
        this.time = time;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
