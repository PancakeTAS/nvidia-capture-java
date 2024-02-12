package gay.pancake.capture.enums;

/**
 * Capture type.
 */
public interface NVFBC_CAPTURE_TYPE  {

    /**
     * Capture frames to a buffer in system memory.
     */
    int NVFBC_CAPTURE_TO_SYS = 0;

    /**
     * Capture frames to a CUDA device in video memory.
     * <p>
     * Specifying this will dlopen() libcuda.so.1 and fail if not available.
     */
    int NVFBC_CAPTURE_SHARED_CUDA = 1;

    /**
     * Capture frames to an OpenGL buffer in video memory.
     */
    int NVFBC_CAPTURE_TO_GL = 3;

}
