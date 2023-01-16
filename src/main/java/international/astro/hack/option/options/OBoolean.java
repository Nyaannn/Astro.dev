package international.astro.hack.option.options;

import international.astro.hack.option.Option;

public class OBoolean extends Option {
    private boolean enabled;

    public OBoolean(String name, boolean defVal){
        super(name);
        enabled = defVal;
    }

    public void toggle(){
        enabled = !enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
