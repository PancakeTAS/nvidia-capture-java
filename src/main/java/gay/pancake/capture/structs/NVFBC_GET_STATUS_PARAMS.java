package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCGetStatus() API call.
 */
public class NVFBC_GET_STATUS_PARAMS extends Structure {
    public NVFBC_GET_STATUS_PARAMS() { super(); }
    public NVFBC_GET_STATUS_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_GET_STATUS_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_GET_STATUS_PARAMS(), 2);
    public static final int NVFBC_OUTPUT_MAX = 5;

    /**
     * [in] Must be set to NVFBC_GET_STATUS_PARAMS_VER
     */
    public int dwVersion = NVFBC_GET_STATUS_PARAMS_VER;

    /**
     * [out] Whether or not framebuffer capture is supported by the graphics
     * driver.
     */
    public int bIsCapturePossible; // NVFBC_BOOL

    /**
     * [out] Whether or not there is already a capture session on this system.
     */
    public int bCurrentlyCapturing; // NVFBC_BOOL

    /**
     * [out] Whether or not it is possible to create a capture session on this
     * system.
     */
    public int bCanCreateNow; // NVFBC_BOOL

    /**
     * [out] Size of the X screen (framebuffer).
     */
    public NVFBC_SIZE screenSize;

    /**
     * [out] Whether the XRandR extension is available.
     * <p>
     * If this extension is not available then it is not possible to have
     * information about RandR outputs.
     */
    public int bXRandRAvailable; // NVFBC_BOOL

    /**
     * [out] Array of outputs connected to the X screen.
     * <p>
     * An application can track a specific output by specifying its ID when
     * creating a capture session.
     * <p>
     * Only if XRandR is available.
     */
    public NVFBC_RANDR_OUTPUT_INFO[] outputs = new NVFBC_RANDR_OUTPUT_INFO[NVFBC_OUTPUT_MAX];

    /**
     * [out] Number of outputs connected to the X screen.
     * <p>
     * This must be used to parse the array of connected outputs.
     * <p>
     * Only if XRandR is available.
     */
    public int dwOutputNum;

    /**
     * [out] Version of the NvFBC library running on this system.
     */
    public int dwNvFBCVersion;

    /**
     * [out] Whether the X server is currently in modeset.
     * <p>
     * When the X server is in modeset, it must give up all its video
     * memory allocations. It is not possible to create a capture
     * session until the modeset is over.
     * <p>
     * Note that VT-switches are considered modesets.
     */
    public int bInModeset;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "bIsCapturePossible", "bCurrentlyCapturing", "bCanCreateNow", "screenSize", "bXRandRAvailable", "outputs", "dwOutputNum", "dwNvFBCVersion", "bInModeset");
    }

}
