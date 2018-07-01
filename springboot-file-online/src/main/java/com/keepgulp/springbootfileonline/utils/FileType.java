package com.keepgulp.springbootfileonline.utils;

public enum FileType {
    /**
     * -1：文件夹
     * 0：其他
     * 1：文本
     * 2：图片
     * 3：音频
     * 4：excel
     * 5：视频
     * 6：压缩
     * 7：word
     * 8：代码
     */
    FOLDER("folder", -1), FILE("file", 0), TEXT("text", 1), IMG("img", 2), music("music", 3), EXCEL("excel", 4), VIDEO("video", 5),
    ARCHIVE("archive", 6), WORD("word", 7) , CODE("code", 8);

    private String type;
    private int index;

    FileType(String type, int index) {
        this.type = type;
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    // 普通方法
    public static String getType(int index) {
        for (FileType c : FileType.values()) {
            if (c.getIndex() == index) {
                return c.type;
            }
        }
        return null;
    }

}
