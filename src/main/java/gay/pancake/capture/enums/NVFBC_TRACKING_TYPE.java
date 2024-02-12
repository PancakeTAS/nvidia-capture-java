package gay.pancake.capture.enums;

/**
 * Tracking type.
 * <p>
 * NvFBC can track a specific region of the framebuffer to capture.
 * <p>
 * An X screen corresponds to the entire framebuffer.
 * <p>
 * An RandR CRTC is a component of the GPU that reads pixels from a region of
 * the X screen and sends them through a pipeline to an RandR output.
 * A physical monitor can be connected to an RandR output.  Tracking an RandR
 * output captures the region of the X screen that the RandR CRTC is sending to
 * the RandR output.
 */
public interface NVFBC_TRACKING_TYPE {

    /**
     * By default, NvFBC tries to track a connected primary output.  If none is
     * found, then it tries to track the first connected output.  If none is
     * found then it tracks the entire X screen.
     * <p>
     * If the XRandR extension is not available, this option has the same effect
     * as ::NVFBC_TRACKING_SCREEN.
     * <p>
     * This default behavior might be subject to changes in the future.
     */
    int NVFBC_TRACKING_DEFAULT = 0;

    /**
     * Track an RandR output specified by its ID in the appropriate field.
     * <p>
     * The list of connected outputs can be queried via NvFBCGetStatus().
     * This list can also be obtained using e.g., xrandr(1).
     * <p>
     * If the XRandR extension is not available, setting this option returns an
     * error.
     */
    int NVFBC_TRACKING_OUTPUT = 1;

    /**
     * Track the entire X screen.
     */
    int NVFBC_TRACKING_SCREEN = 2;

}
