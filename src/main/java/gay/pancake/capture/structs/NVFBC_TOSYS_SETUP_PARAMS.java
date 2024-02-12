package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCToSysSetUp() API call.
 */
public class NVFBC_TOSYS_SETUP_PARAMS extends Structure {
    public NVFBC_TOSYS_SETUP_PARAMS() { super(); }
    public NVFBC_TOSYS_SETUP_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_TOSYS_SETUP_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_TOSYS_SETUP_PARAMS(), 3);

    /**
     * [in] Must be set to NVFBC_TOSYS_SETUP_PARAMS_VER
     */
    public int dwVersion = NVFBC_TOSYS_SETUP_PARAMS_VER;

    /**
     * [in] Desired buffer format.
     */
    public int eBufferFormat; // NVFBC_BUFFER_FORMAT

    /**
     * [out] Pointer to a pointer to a buffer in system memory.
     * <p>
     * This buffer contains the pixel value of the requested format.  Refer to
     * the description of the buffer formats to understand the memory layout.
     * <p>
     * The application does not need to allocate memory for this buffer.  It
     * should not free this buffer either.  This buffer is automatically
     * re-allocated when needed (e.g., when the resolution changes).
     * <p>
     * This buffer is allocated by the NvFBC library to the proper size.  This
     * size is returned in the dwByteSize field of the
     * ::NVFBC_FRAME_GRAB_INFO structure.
     */
    public Pointer ppBuffer;

    /**
     * [in] Whether differential maps should be generated.
     */
    public int bWithDiffMap; // NVFBC_BOOL

    /**
     * [out] Pointer to a pointer to a buffer in system memory.
     * <p>
     * This buffer contains the differential map of two frames.  It must be read
     * as an array of unsigned char.  Each unsigned char is either 0 or
     * non-zero.  0 means that the pixel value at the given location has not
     * changed since the previous captured frame.  Non-zero means that the pixel
     * value has changed.
     * <p>
     * The application does not need to allocate memory for this buffer.  It
     * should not free this buffer either.  This buffer is automatically
     * re-allocated when needed (e.g., when the resolution changes).
     * <p>
     * This buffer is allocated by the NvFBC library to the proper size.  The
     * size of the differential map is returned in ::diffMapSize.
     * <p>
     * This option is not compatible with the ::NVFBC_BUFFER_FORMAT_YUV420P and
     * ::NVFBC_BUFFER_FORMAT_YUV444P buffer formats.
     */
    public Pointer ppDiffMap;

    /**
     * [in] Scaling factor of the differential maps.
     * <p>
     * For example, a scaling factor of 16 means that one pixel of the diffmap
     * will represent 16x16 pixels of the original frames.
     * <p>
     * If any of these 16x16 pixels is different between the current and the
     * previous frame, then the corresponding pixel in the diffmap will be set
     * to non-zero.
     * <p>
     * The default scaling factor is 1.  A dwDiffMapScalingFactor of 0 will be
     * set to 1.
     */
    public int dwDiffMapScalingFactor;

    /**
     * [out] Size of the differential map.
     * <p>
     * Only set if bWithDiffMap is set to NVFBC_TRUE.
     */
    public NVFBC_SIZE diffMapSize;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "eBufferFormat", "ppBuffer", "bWithDiffMap", "ppDiffMap", "dwDiffMapScalingFactor", "diffMapSize");
    }

}
