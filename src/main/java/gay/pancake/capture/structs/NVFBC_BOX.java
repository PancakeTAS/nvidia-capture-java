package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

/**
 * Box used to describe an area of the tracked region to capture.
 * <p>
 * The coordinates are relative to the tracked region.
 * <p>
 * E.g., if the size of the X screen is 3520x1200 and the tracked RandR output
 * scans a region of 1600x1200+1920+0, then setting a capture box of
 * 800x600+100+50 effectively captures a region of 800x600+2020+50 relative to
 * the X screen.
 */
public class NVFBC_BOX extends Structure {
    public NVFBC_BOX() { super(); }
    public NVFBC_BOX(Pointer p) { super(p); }

    /**
     * [in] X offset of the box.
     */
    public int x;

    /**
     * [in] Y offset of the box.
     */
    public int y;

    /**
     * [in] Width of the box.
     */
    public int w;

    /**
     * [in] Height of the box.
     */
    public int h;

    protected List<String> getFieldOrder() {
        return List.of("x", "y", "w", "h");
    }

}
