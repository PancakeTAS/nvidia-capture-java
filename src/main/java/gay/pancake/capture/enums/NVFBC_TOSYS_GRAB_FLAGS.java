package gay.pancake.capture.enums;

/**
 * Defines flags that can be used when capturing to system memory.
 */
public interface NVFBC_TOSYS_GRAB_FLAGS {

    /**
     * Default, capturing waits for a new frame or mouse move.
     * <p>
     * The default behavior of blocking grabs is to wait for a new frame until
     * after the call was made.  But it's possible that there is a frame already
     * ready that the client hasn't seen.
     * See {@link #NVFBC_TOSYS_GRAB_FLAGS_NOWAIT_IF_NEW_FRAME_READY}
     */
    int NVFBC_TOSYS_GRAB_FLAGS_NOFLAGS = 0;

    /**
     * Capturing does not wait for a new frame nor a mouse move.
     * <p>
     * It is therefore possible to capture the same frame multiple times.
     * When this occurs, the dwCurrentFrame parameter of the
     * NVFBC_FRAME_GRAB_INFO structure is not incremented.
     */
    int NVFBC_TOSYS_GRAB_FLAGS_NOWAIT = 1;

    /**
     * Forces the destination buffer to be refreshed even if the frame has not
     * changed since previous capture.
     * <p>
     * By default, if the captured frame is identical to the previous one, NvFBC
     * will omit one copy and not update the destination buffer.
     * <p>
     * Setting that flag will prevent this behavior.  This can be useful e.g.,
     * if the application has modified the buffer in the meantime.
     */
    int NVFBC_TOSYS_GRAB_FLAGS_FORCE_REFRESH = 1 << 1;

    /**
     * Similar to NVFBC_TOSYS_GRAB_FLAGS_NOFLAGS, except that the capture will
     * not wait if there is already a frame available that the client has
     * never seen yet.
     */
    int NVFBC_TOSYS_GRAB_FLAGS_NOWAIT_IF_NEW_FRAME_READY = 1 << 2;

}
