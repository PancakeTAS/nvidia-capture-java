package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCCreateCaptureSession() API call.
 */
public class NVFBC_CREATE_CAPTURE_SESSION_PARAMS extends Structure {
    public NVFBC_CREATE_CAPTURE_SESSION_PARAMS() { super(); }
    public NVFBC_CREATE_CAPTURE_SESSION_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_CREATE_CAPTURE_SESSION_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_CREATE_CAPTURE_SESSION_PARAMS(), 6);

    /**
     * [in] Must be set to NVFBC_CREATE_CAPTURE_SESSION_PARAMS_VER
     */
    public int dwVersion = NVFBC_CREATE_CAPTURE_SESSION_PARAMS_VER;

    /**
     * [in] Desired capture type.
     * <p>
     * Note that when specyfing ::NVFBC_CAPTURE_SHARED_CUDA NvFBC will try to
     * dlopen() the corresponding libraries.  This means that NvFBC can run on
     * a system without the CUDA library since it does not link against them.
     */
    public int eCaptureType; // NVFBC_CAPTURE_TYPE

    /**
     * [in] What region of the framebuffer should be tracked.
     */
    public int eTrackingType; // NVFBC_TRACKING_TYPE

    /**
     * [in] ID of the output to track if eTrackingType is set to
     * ::NVFBC_TRACKING_OUTPUT.
     */
    public int dwOutputId;

    /**
     * [in] Crop the tracked region.
     * <p>
     * The coordinates are relative to the tracked region.
     * <p>
     * It can be set to 0 to capture the entire tracked region.
     */
    public NVFBC_BOX captureBox;

    /**
     * [in] Desired size of the captured frame.
     * <p>
     * This parameter allow to scale the captured frame.
     * <p>
     * It can be set to 0 to disable frame resizing.
     */
    public NVFBC_SIZE frameSize;

    /**
     * [in] Whether the mouse cursor should be composited to the frame.
     * <p>
     * Disabling the cursor will not generate new frames when only the cursor
     * is moved.
     */
    public int bWithCursor; // NVFBC_BOOL

    /**
     * [in] Whether NvFBC should not attempt to recover from modesets.
     * <p>
     * NvFBC is able to detect when a modeset event occured and can automatically
     * re-create a capture session with the same settings as before, then resume
     * its frame capture session transparently.
     * <p>
     * This option allows to disable this behavior.  NVFBC_ERR_MUST_RECREATE
     * will be returned in that case.
     * <p>
     * It can be useful in the cases when an application needs to do some work
     * between setting up a capture and grabbing the first frame.
     * <p>
     * For example: an application using the ToGL interface needs to register
     * resources with EncodeAPI prior to encoding frames.
     * <p>
     * Note that during modeset recovery, NvFBC will try to re-create the
     * capture session every second until it succeeds.
     */
    public int bDisableAutoModesetRecovery; // NVFBC_BOOL

    /**
     * [in] Whether NvFBC should round the requested frameSize.
     * <p>
     * When disabled, NvFBC will not attempt to round the requested resolution.
     * <p>
     * However, some pixel formats have resolution requirements.  E.g., YUV/NV
     * formats must have a width being a multiple of 4, and a height being a
     * multiple of 2.  RGB formats don't have such requirements.
     * <p>
     * If the resolution doesn't meet the requirements of the format, then NvFBC
     * will fail at setup time.
     * <p>
     * When enabled, NvFBC will round the requested width to the next multiple
     * of 4 and the requested height to the next multiple of 2.
     * <p>
     * In this case, requesting any resolution will always work with every
     * format.  However, an NvFBC client must be prepared to handle the case
     * where the requested resolution is different than the captured resolution.
     * <p>
     * NVFBC_FRAME_GRAB_INFO::dwWidth and NVFBC_FRAME_GRAB_INFO::dwHeight should
     * always be used for getting information about captured frames.
     */
    public int bRoundFrameSize; // NVFBC_BOOL

    /**
     * [in] Rate in ms at which the display server generates new frames
     * <p>
     * This controls the frequency at which the display server will generate
     * new frames if new content is available.  This effectively controls the
     * capture rate when using blocking calls.
     * <p>
     * Note that lower values will increase the CPU and GPU loads.
     * <p>
     * The default value is 16ms (~ 60 Hz).
     */
    public int dwSamplingRateMs; // NVFBC_BOOL

    /**
     * [in] Enable push model for frame capture
     * <p>
     * When set to NVFBC_TRUE, the display server will generate frames whenever
     * it receives a damage event from applications.
     * <p>
     * Setting this to NVFBC_TRUE will ignore ::dwSamplingRateMs.
     * <p>
     * Using push model with the NVFBC_*_GRAB_FLAGS_NOWAIT_IF_NEW_FRAME_READY
     * capture flag should guarantee the shortest amount of time between an
     * application rendering a frame and an NvFBC client capturing it, provided
     * that the NvFBC client is able to process the frames quickly enough.
     * <p>
     * Note that applications running at high frame rates will increase CPU and
     * GPU loads.
     */
    public int bPushModel; // NVFBC_BOOL

    /**
     * [in] Allow direct capture
     * <p>
     * Direct capture allows NvFBC to attach itself to a fullscreen graphics
     * application. Whenever that application presents a frame, it makes a copy
     * of it directly into a buffer owned by NvFBC thus bypassing the X server.
     * <p>
     * When direct capture is *not* enabled, the NVIDIA X driver generates a
     * frame for NvFBC when it receives a damage event from an application if push
     * model is enabled, or periodically checks if there are any pending damage
     * events otherwise (see NVFBC_CREATE_CAPTURE_SESSION_PARAMS::dwSamplingRateMs).
     * <p>
     * Direct capture is possible under the following conditions:
     * - Direct capture is allowed
     * - Push model is enabled (see NVFBC_CREATE_CAPTURE_SESSION_PARAMS::bPushModel)
     * - The mouse cursor is not composited (see NVFBC_CREATE_CAPTURE_SESSION_PARAMS::bWithCursor)
     * - No viewport transformation is required. This happens when the remote
     *   desktop is e.g. rotated.
     * <p>
     * When direct capture is possible, NvFBC will automatically attach itself
     * to a fullscreen unoccluded application, if such exists.
     * <p>
     * Notes:
     * - This includes compositing desktops such as GNOME (e.g., gnome-shell
     *   is the fullscreen unoccluded application).
     * - There can be only one fullscreen unoccluded application at a time.
     * - The NVIDIA X driver monitors which application qualifies or no
     *   longer qualifies.
     * <p>
     * For example, if a fullscreen application is launched in GNOME, NvFBC will
     * detach from gnome-shell and attach to that application.
     * <p>
     * Attaching and detaching happens automatically from the perspective of an
     * NvFBC client. When detaching from an application, the X driver will
     * transparently resume generating frames for NvFBC.
     * <p>
     * An application can know whether a given frame was obtained through
     * direct capture by checking NVFBC_FRAME_GRAB_INFO::bDirectCapture.
     */
    public int bAllowDirectCapture; // NVFBC_BOOL

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "eCaptureType", "eTrackingType", "dwOutputId", "captureBox", "frameSize", "bWithCursor", "bDisableAutoModesetRecovery", "bRoundFrameSize", "dwSamplingRateMs", "bPushModel", "bAllowDirectCapture");
    }

}
