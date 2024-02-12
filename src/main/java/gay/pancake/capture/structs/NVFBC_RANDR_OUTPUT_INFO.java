package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

/**
 * Describes an RandR output.
 * <p>
 * Filling this structure relies on the XRandR extension.  This feature cannot
 * be used if the extension is missing or its version is below the requirements.
 * <p>
 * See Requirements
 */
public class NVFBC_RANDR_OUTPUT_INFO extends Structure {
    public NVFBC_RANDR_OUTPUT_INFO() { super(); }
    public NVFBC_RANDR_OUTPUT_INFO(Pointer p) { super(p); }
    public static final int NVFBC_OUTPUT_NAME_LEN = 128;

    /**
     * Identifier of the RandR output.
     */
    public int dwId;

    /**
     * Name of the RandR output, as reported by tools such as xrandr(1).
     * <p>
     * Example: "DVI-I-0"
     */
    public byte[] name = new byte[NVFBC_OUTPUT_NAME_LEN];

    /**
     * Region of the X screen tracked by the RandR CRTC driving this RandR
     * output.
     */
    public NVFBC_BOX trackedBox;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwId", "name", "trackedBox");
    }

}
