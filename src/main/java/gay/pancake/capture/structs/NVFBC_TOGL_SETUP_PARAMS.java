package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCToGLSetUp() API call.
 */
public class NVFBC_TOGL_SETUP_PARAMS extends Structure {
    public NVFBC_TOGL_SETUP_PARAMS() { super(); }
    public NVFBC_TOGL_SETUP_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_TOGL_SETUP_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_TOGL_SETUP_PARAMS(), 2);
    public static final int NVFBC_TOGL_TEXTURES_MAX = 2;

    /**
     * [in] Must be set to NVFBC_TOGL_SETUP_PARAMS_VER
     */
    public int dwVersion = NVFBC_TOGL_SETUP_PARAMS_VER;

    /**
     * [in] Desired buffer format.
     */
    public int eBufferFormat; // NVFBC_BUFFER_FORMAT

    /**
     * [in] Whether differential maps should be generated.
     */
    public int bWithDiffMap; // NVFBC_BOOL

    /**
     * [out] Pointer to a pointer to a buffer in system memory.
     * <p>
     * @see NVFBC_TOSYS_SETUP_PARAMS#ppDiffMap
     */
    public Pointer ppDiffMap;

    /**
     * [in] Scaling factor of the differential maps.
     * <p>
     * @see NVFBC_TOSYS_SETUP_PARAMS#dwDiffMapScalingFactor
     */
    public int dwDiffMapScalingFactor;

    /**
     * [out] List of GL textures that will store the captured frames.
     * <p>
     * This array is 0 terminated.  The number of textures varies depending on
     * the capture settings (such as whether diffmaps are enabled).
     * <p>
     * An application wishing to interop with, for example, EncodeAPI will need
     * to register these textures prior to start encoding frames.
     * <p>
     * After each frame capture, the texture holding the current frame will be
     * returned in NVFBC_TOGL_GRAB_FRAME_PARAMS::dwTexture.
     */
    public int[] dwTextures = new int[NVFBC_TOGL_TEXTURES_MAX];

    /**
     * [out] GL target to which the texture should be bound.
     */
    public int dwTexTarget;

    /**
     * [out] GL format of the textures.
     */
    public int dwTexFormat;

    /**
     * [out] GL type of the textures.
     */
    public int dwTexType;
    
    /**
     * [out] Size of the differential map.
     * <p>
     * Only set if bWithDiffMap is set to NVFBC_TRUE.
     */
    public NVFBC_SIZE diffMapSize;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "eBufferFormat", "bWithDiffMap", "ppDiffMap", "dwDiffMapScalingFactor", "dwTextures", "dwTexTarget", "dwTexFormat", "dwTexType", "diffMapSize");
    }

}
