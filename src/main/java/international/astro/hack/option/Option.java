package international.astro.hack.option;

public class Option {
    private String name;
    private boolean visible = true;

    public Option(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
