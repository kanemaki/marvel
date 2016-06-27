package model;

/**
 * Created by alexandre on 26/06/2016.
 */
public class WebHead {
    private String num;
    private String hq;

    public WebHead(String num, String hq) {
        this.num = num;
        this.hq = hq;
    }

    public String getNum() {

        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getHq() {
        return hq;
    }

    public void setHq(String hq) {
        this.hq = hq;
    }
}
