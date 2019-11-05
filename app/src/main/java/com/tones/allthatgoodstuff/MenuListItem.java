package com.tones.allthatgoodstuff;

public class MenuListItem {

    // Declare picture, heading, description and filename variables
    private String ref;
    private String sqPic;
    private String rtPic;
    private String head;
    private String desc;
    private String filename;

    // Constructor for heading and description
    public MenuListItem(String ref, String rtPic, String sqPic, String head, String desc, String filename) {
        this.ref = ref;
        this.rtPic = rtPic;
        this.sqPic = sqPic;
        this.head = head;
        this.desc = desc;
        this.filename = filename;
    }

    // Getter for ref
    public String getRef() { return ref; }

    // Getter for square image
    public String getSqPic() { return sqPic; }

    // Getter for rectangle image
    public String getRtPic() { return rtPic; }

    // Getter for heading
    public String getHead() {
        return head;
    }

    // Getter for description
    public String getDesc() {
        return desc;
    }

    // Getter for filename
    public String getFilename() { return filename; }
}
