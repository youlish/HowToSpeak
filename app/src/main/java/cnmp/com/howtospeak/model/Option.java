package cnmp.com.howtospeak.model;

/**
 * Created by henry on 12/7/2017.
 */

public class Option {
    private String name;
    private int resIcon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Option() {

    }

    public Option(String name, int resIcon) {

        this.name = name;
        this.resIcon = resIcon;
    }
}
