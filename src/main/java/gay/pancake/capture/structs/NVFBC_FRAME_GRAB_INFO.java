package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

/**
 * Describes information about a captured frame.
 */
public class NVFBC_FRAME_GRAB_INFO extends Structure {
    public NVFBC_FRAME_GRAB_INFO() {}
    public NVFBC_FRAME_GRAB_INFO(Pointer p) {}

    /**
     * [out] Width of the captured frame.
     */
    public int dwWidth;

    /**
     * [out] Height of the captured frame.
     */
    public int dwHeight;

    /**
     * [out] Size of the frame in bytes.
     */
    public int dwByteSize;

    /**
     * [out] Incremental ID of the current frame.
     * <p>
     * This can be used to identify a frame.
     */
    public int dwCurrentFrame;

    /**
     * [out] Whether the captured frame is a new frame.
     * <p>
     * When using non blocking calls it is possible to capture a frame
     * that was already captured before if the display server did not
     * render a new frame in the meantime.  In that case, this flag
     * will be set to NVFBC_FALSE.
     * <p>
     * When using blocking calls each captured frame will have
     * this flag set to NVFBC_TRUE since the blocking mechanism waits for
     * the display server to render a new frame.
     * <p>
     * Note that this flag does not guarantee that the content of
     * the frame will be different compared to the previous captured frame.
     * <p>
     * In particular, some compositing managers report the entire
     * framebuffer as damaged when an application refreshes its content.
     * <p>
     * Consider a single X screen spanned across physical displays A and B
     * and an NvFBC application tracking display A.  Depending on the
     * compositing manager, it is possible that an application refreshing
     * itself on display B will trigger a frame capture on display A.
     * <p>
     * Workarounds include:
     * <table>
     * <li> - Using separate X screens</li>
     * <li> - Disabling the composite extension</li>
     * <li> - Using a compositing manager that properly reports what regions
     *  are damaged</li>
     * <li> - Using NvFBC's diffmaps to find out if the frame changed</li>
     * </table>
     */
    public int bIsNewFrame; // NVFBC_BOOL

    /**
     * [out] Frame timestamp
     * <p>
     * Time in micro seconds when the display server started rendering the
     * frame.
     * <p>
     * This does not account for when the frame was captured.  If capturing an
     * old frame (e.g., bIsNewFrame is NVFBC_FALSE) the reported timestamp
     * will reflect the time when the old frame was rendered by the display
     * server.
     */
    public long ulTimestampUs;

    /**
     * [out] Number of frames generated since the last capture.
     * <p>
     * This can help applications tell whether they missed frames or there
     * were no frames generated by the server since the last capture.
     */
    public int dwMissedFrames;

    /**
     * [out] Whether the captured frame required post processing.
     * <p>
     * See the 'Post Processing' section.
     */
    public int bRequiredPostProcessing; // NVFBC_BOOL

    /**
     * [out] Whether this frame was obtained via direct capture.
     * <p>
     * See NVFBC_CREATE_CAPTURE_SESSION_PARAMS::bAllowDirectCapture.
     */
    public int bDirectCapture; // NVFBC_BOOL

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwWidth", "dwHeight", "dwByteSize", "dwCurrentFrame", "bIsNewFrame", "ulTimestampUs", "dwMissedFrames", "bRequiredPostProcessing", "bDirectCapture");
    }

}