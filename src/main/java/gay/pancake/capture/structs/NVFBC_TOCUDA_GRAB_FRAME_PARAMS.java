package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCToCudaGrabFrame() API call.
 */
public class NVFBC_TOCUDA_GRAB_FRAME_PARAMS extends Structure {
    public NVFBC_TOCUDA_GRAB_FRAME_PARAMS() { super(); }
    public NVFBC_TOCUDA_GRAB_FRAME_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_TOCUDA_GRAB_FRAME_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_TOCUDA_GRAB_FRAME_PARAMS(), 2);

    /**
     * [in] Must be set to NVFBC_TOCUDA_GRAB_FRAME_PARAMS_VER.
     */
    public int dwVersion = NVFBC_TOCUDA_GRAB_FRAME_PARAMS_VER;

    /**
     * [in] Flags defining the behavior of this frame capture.
     */
    public int dwFlags;

    /**
     * [out] Pointer to a ::CUdeviceptr
     * <p>
     * The application does not need to allocate memory for this CUDA device.
     * <p>
     * The application does need to create its own CUDA context to use this
     * CUDA device.
     * <p>
     * This ::CUdeviceptr will be mapped to a segment in video memory containing
     * the frame.  It is not possible to process a CUDA device while capturing
     * a new frame.  If the application wants to do so, it must copy the CUDA
     * device using ::cuMemcpyDtoD or ::cuMemcpyDtoH beforehand.
     */
    public Pointer pCUDADeviceBuffer;

    /**
     * [out] Information about the captured frame.
     * <p>
     * Can be NULL.
     */
    public NVFBC_FRAME_GRAB_INFO pFrameGrabInfo;

    /**
     * [in] Wait timeout in milliseconds.
     * <p>
     * When capturing frames with the NVFBC_TOCUDA_GRAB_FLAGS_NOFLAGS or
     * NVFBC_TOCUDA_GRAB_FLAGS_NOWAIT_IF_NEW_FRAME_READY flags,
     * NvFBC will wait for a new frame or mouse move until the below timer
     * expires.
     * <p>
     * When timing out, the last captured frame will be returned.  Note that as
     * long as the NVFBC_TOCUDA_GRAB_FLAGS_FORCE_REFRESH flag is not set,
     * returning an old frame will incur no performance penalty.
     * <p>
     * NvFBC clients can use the return value of the grab frame operation to
     * find out whether a new frame was captured, or the timer expired.
     * <p>
     * Note that the behavior of blocking calls is to wait for a new frame
     * *after* the call has been made.  When using timeouts, it is possible
     * that NvFBC will return a new frame (e.g., it has never been captured
     * before) even though no new frame was generated after the grab call.
     * <p>
     * For the precise definition of what constitutes a new frame, see
     * ::bIsNewFrame.
     * <p>
     * Set to 0 to disable timeouts.
     */
    public int dwTimeoutMs;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "dwFlags", "pCUDADeviceBuffer", "pFrameGrabInfo", "dwTimeoutMs");
    }

}
