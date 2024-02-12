package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

/**
 * Size used to describe the size of a frame.
 */
public class NVFBC_SIZE extends Structure {
    public NVFBC_SIZE() { super(); }
    public NVFBC_SIZE(Pointer p) { super(p); }

    /**
     * [in] Width.
     */
    public int w;

    /**
     * [in] Height.
     */
    public int h;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("w", "h");
    }

}
