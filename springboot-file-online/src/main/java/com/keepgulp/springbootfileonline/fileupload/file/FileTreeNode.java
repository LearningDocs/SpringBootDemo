package com.keepgulp.springbootfileonline.fileupload.file;

import java.util.List;

public class FileTreeNode {

    private String text;

    private String type;

    private List<FileTreeNode> children;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FileTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<FileTreeNode> children) {
        this.children = children;
    }
}
