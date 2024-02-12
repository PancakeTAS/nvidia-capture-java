package gay.pancake.capture.enums;

/**
 * Defines error codes.
 *
 * @see gay.pancake.capture.NvFBC#NvFBCGetLastErrorStr(long)
 */
public interface NVFBCSTATUS {

    /**
     * This indicates that the API call returned with no errors.
     */
    int NVFBC_SUCCESS = 0;
    
    /**
     * This indicates that the API version between the client and the library
     * is not compatible.
     */
    int NVFBC_ERR_API_VERSION = 1;
    
    /**
     * An internal error occurred.
     */
    int NVFBC_ERR_INTERNAL = 2;
    
    /**
     * This indicates that one or more of the parameter passed to the API call
     * is invalid.
     */
    int NVFBC_ERR_INVALID_PARAM = 3;
    
    /**
     * This indicates that one or more of the pointers passed to the API call
     * is invalid.
     */
    int NVFBC_ERR_INVALID_PTR = 4;
    
    /**
     * This indicates that the handle passed to the API call to identify the
     * client is invalid.
     */
    int NVFBC_ERR_INVALID_HANDLE = 5;
    
    /**
     * This indicates that the maximum number of threaded clients of the same
     * process has been reached.  The limit is 10 threads per process.
     * There is no limit on the number of process.
     */
    int NVFBC_ERR_MAX_CLIENTS = 6;
    
    /**
     * This indicates that the requested feature is not currently supported
     * by the library.
     */
    int NVFBC_ERR_UNSUPPORTED = 7;
    
    /**
     * This indicates that the API call failed because it was unable to allocate
     * enough memory to perform the requested operation.
     */
    int NVFBC_ERR_OUT_OF_MEMORY = 8;
    
    /**
     * This indicates that the API call was not expected.  This happens when
     * API calls are performed in a wrong order; such as trying to capture
     * a frame prior to creating a new capture session; or trying to set up
     * a capture to video memory although a capture session to system memory
     * was created.
     */
    int NVFBC_ERR_BAD_REQUEST = 9;
    
    /**
     * This indicates an X error; most likely meaning that the X server has
     * been terminated.  When this error is returned; the only resort is to
     * create another FBC handle using NvFBCCreateHandle().
     * <p>
     * The previous handle should still be freed with NvFBCDestroyHandle(); but
     * it might leak resources; in particular X; GLX; and GL resources since
     * it is no longer possible to communicate with an X server to free them
     * through the driver.
     * <p>
     * The best course of action to eliminate this potential leak is to close
     * the OpenGL driver; close the forked process running the capture; or
     * restart the application.
     */
    int NVFBC_ERR_X = 10;
    
    /**
     * This indicates a GLX error.
     */
    int NVFBC_ERR_GLX = 11;
    
    /**
     * This indicates an OpenGL error.
     */
    int NVFBC_ERR_GL = 12;
    
    /**
     * This indicates a CUDA error.
     */
    int NVFBC_ERR_CUDA = 13;
    
    /**
     * This indicates a HW encoder error.
     */
    int NVFBC_ERR_ENCODER = 14;
    
    /**
     * This indicates an NvFBC context error.
     */
    int NVFBC_ERR_CONTEXT = 15;
    
    /**
     * This indicates that the application must recreate the capture session.
     * <p>
     * This error can be returned if a modeset event occurred while capturing
     * frames; and int NVFBC_CREATE_HANDLE_PARAMS::bDisableAutoModesetRecovery
     * was set to int NVFBC_TRUE.
     */
    int NVFBC_ERR_MUST_RECREATE = 16;
    
    /**
     * This indicates a Vulkan error.
     */
    int NVFBC_ERR_VULKAN = 17;

}
