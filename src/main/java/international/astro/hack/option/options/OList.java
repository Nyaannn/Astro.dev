package international.astro.hack.option.options;

import international.astro.hack.option.Option;

import java.util.Arrays;
import java.util.List;

public class OList extends Option {

    private String mode;
    private List<String> modes;
    private int index;

    public OList(String name, String defVal, String... modes) {
        super(name);
        this.modes = Arrays.asList(modes);
        mode = defVal;
        index = this.modes.indexOf(defVal);
    }

    public String getMode(){
        return mode;
    }

    public List<String> getModes() {
        return modes;
    }

    public void setMode(String mode) {
        this.mode = mode;
        index = modes.indexOf(mode);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
        mode = modes.get(index);
    }

    public void cycle(){
        if(index < modes.size() -1){
            index++;
            mode = modes.get(index);
        } else if (index >= modes.size() - 1) {
            index = 0;
            mode = modes.get(0);
        }
    }

    public boolean isMode(String mode){
        return this.mode == mode;
    }
}
