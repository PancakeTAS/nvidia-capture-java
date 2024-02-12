package gay.pancake.capture;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import gay.pancake.capture.structs.*;

/**
 * Interface for the NvFBC API.
 *
 * @version 8.1
 */
public interface NvFBC extends Library {
    NvFBC INSTANCE = Native.load("nvidia-fbc", NvFBC.class);

    /**
     * Creates a version number for structure parameters.
     */
    static int NVFBC_STRUCT_VERSION(Structure typeName, int ver) {
        return typeName.size() | (ver << 16) | (NVFBC_VERSION << 24);
    }

    /** NvFBC API major version. */
    int NVFBC_VERSION_MAJOR = 1;
    /** NvFBC API minor version. */
    int NVFBC_VERSION_MINOR = 8;
    /** NvFBC API version. */
    int NVFBC_VERSION = NVFBC_VERSION_MINOR | (NVFBC_VERSION_MAJOR << 8);

    /** Maximum size in bytes of an error string. */
    int NVFBC_ERR_STR_LEN = 512;

    /**
     * Gets the last error message that got recorded for a client.
     * <p>
     * When NvFBC returns an error, it will save an error message that can be
     * queried through this API call.  Only the last message is saved.
     * The message and the return code should give enough information about
     * what went wrong.
     *
     * @param sessionHandle Handle to the NvFBC client.
     * @return A NULL terminated error message, or an empty string. Its maximum length
     * is NVFBC_ERROR_STR_LEN.
     */
    String NvFBCGetLastErrorStr(long sessionHandle);

    /**
     * Allocates a new handle for an NvFBC client.
     * <p>
     * This function allocates a session handle used to identify an FBC client.
     * <p>
     * This function implicitly calls NvFBCBindContext().
     *
     * @param pSessionHandle
     *   Pointer that will hold the allocated session handle.
     * @param pParams
     *   ::NVFBC_CREATE_HANDLE_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_PTR <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_OUT_OF_MEMORY <br>
     *   ::NVFBC_ERR_MAX_CLIENTS <br>
     *   ::NVFBC_ERR_X <br>
     *   ::NVFBC_ERR_GLX <br>
     *   ::NVFBC_ERR_GL
     *
     */
    int NvFBCCreateHandle(Pointer pSessionHandle, NVFBC_CREATE_HANDLE_PARAMS pParams);

    /**
     * Destroys the handle of an NvFBC client.
     * <p>
     * This function uninitializes an FBC client.
     * <p>
     * This function implicitly calls NvFBCReleaseContext().
     * <p>
     * After this fucntion returns, it is not possible to use this session handle
     * for any further API call.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_DESTROY_HANDLE_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCDestroyHandle(long sessionHandle, NVFBC_DESTROY_HANDLE_PARAMS pParams);

    /**
     * Gets the current status of the display driver.
     * <p>
     * This function queries the display driver for various information.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_GET_STATUS_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCGetStatus(long sessionHandle, NVFBC_GET_STATUS_PARAMS pParams);

    /**
     * Binds the FBC context to the calling thread.
     * <p>
     * The NvFBC library internally relies on objects that must be bound to a
     * thread.  Such objects are OpenGL contexts and CUDA contexts.
     * <p>
     * This function binds these objects to the calling thread.
     * <p>
     * The FBC context must be bound to the calling thread for most NvFBC entry
     * points, otherwise ::NVFBC_ERR_CONTEXT is returned.
     * <p>
     * If the FBC context is already bound to a different thread,
     * ::NVFBC_ERR_CONTEXT is returned.  The other thread must release the context
     * first by calling the ReleaseContext() entry point.
     * <p>
     * If the FBC context is already bound to the current thread, this function has
     * no effects.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_DESTROY_CAPTURE_SESSION_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCBindContext(long sessionHandle, NVFBC_BIND_CONTEXT_PARAMS pParams);

    /**
     * Releases the FBC context from the calling thread.
     * <p>
     * If the FBC context is bound to a different thread, ::NVFBC_ERR_CONTEXT is
     * returned.
     * <p>
     * If the FBC context is already released, this functino has no effects.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCReleaseContext(long sessionHandle, NVFBC_RELEASE_CONTEXT_PARAMS pParams);

    /**
     * Creates a capture session for an FBC client.
     * <p>
     * This function starts a capture session of the desired type (system memory,
     * video memory with CUDA interop, or H.264 compressed frames in system memory).
     * <p>
     * Not all types are supported on all systems.  Also, it is possible to use
     * NvFBC without having the CUDA library.  In this case, requesting a capture
     * session of the concerned type will return an error.
     * <p>
     * After this function returns, the display driver will start generating frames
     * that can be captured using the corresponding API call.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_CREATE_CAPTURE_SESSION_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_INVALID_PARAM <br>
     *   ::NVFBC_ERR_OUT_OF_MEMORY <br>
     *   ::NVFBC_ERR_X <br>
     *   ::NVFBC_ERR_GLX <br>
     *   ::NVFBC_ERR_GL <br>
     *   ::NVFBC_ERR_CUDA <br>
     *   ::NVFBC_ERR_MUST_RECREATE <br>
     *   ::NVFBC_ERR_INTERNAL
     */
    int NvFBCCreateCaptureSession(long sessionHandle, NVFBC_CREATE_CAPTURE_SESSION_PARAMS pParams);

    /**
     * Destroys a capture session for an FBC client.
     * <p>
     * This function stops a capture session and frees allocated objects.
     * <p>
     * After this function returns, it is possible to create another capture
     * session using the corresponding API call.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_DESTROY_CAPTURE_SESSION_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCDestroyCaptureSession(long sessionHandle, NVFBC_DESTROY_CAPTURE_SESSION_PARAMS pParams);

    /**
     * Sets up a capture to system memory session.
     * <p>
     * This function configures how the capture to system memory should behave. It
     * can be called anytime and several times after the capture session has been
     * created.  However, it must be called at least once prior to start capturing
     * frames.
     * <p>
     * This function allocates the buffer that will contain the captured frame.
     * The application does not need to free this buffer.  The size of this buffer
     * is returned in the ::NVFBC_FRAME_GRAB_INFO structure.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_TOSYS_SETUP_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_UNSUPPORTED <br>
     *   ::NVFBC_ERR_INVALID_PTR <br>
     *   ::NVFBC_ERR_INVALID_PARAM <br>
     *   ::NVFBC_ERR_OUT_OF_MEMORY <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCToSysSetUp(long sessionHandle, NVFBC_TOSYS_SETUP_PARAMS pParams);

    /**
     * Captures a frame to a buffer in system memory.
     * <p>
     * This function triggers a frame capture to a buffer in system memory that was
     * registered with the ToSysSetUp() API call.
     * <p>
     * Note that it is possible that the resolution of the desktop changes while
     * capturing frames. This should be transparent for the application.
     * <p>
     * When the resolution changes, the capture session is recreated using the same
     * parameters, and necessary buffers are re-allocated. The frame counter is not
     * reset.
     * <p>
     * An application can detect that the resolution changed by comparing the
     * dwByteSize member of the ::NVFBC_FRAME_GRAB_INFO against a previous
     * frame and/or dwWidth and dwHeight.
     * <p>
     * During a change of resolution the capture is paused even in asynchronous
     * mode.
     *
     * @param sessionHandle
     *   FBC session handle.
     * @param pParams
     *   ::NVFBC_TOSYS_GRAB_FRAME_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_INVALID_PTR <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_X <br>
     *   ::NVFBC_ERR_MUST_RECREATE <br>
     *   @see #NvFBCCreateCaptureSession(long, NVFBC_CREATE_CAPTURE_SESSION_PARAMS)  <br>
     *   @see #NvFBCToSysSetUp(long, NVFBC_TOSYS_SETUP_PARAMS) 
     */
    int NvFBCToSysGrabFrame(long sessionHandle, NVFBC_TOSYS_GRAB_FRAME_PARAMS pParams);

    /**
     * Sets up a capture to video memory session.
     * <p>
     * This function configures how the capture to video memory with CUDA interop
     * should behave.  It can be called anytime and several times after the capture
     * session has been created.  However, it must be called at least once prior
     * to start capturing frames.
     *
     * @param sessionHandle
     *   FBC session handle.
     *
     * @param pParams
     *   ::NVFBC_TOCUDA_SETUP_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_UNSUPPORTED <br>
     *   ::NVFBC_ERR_GL <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCToCudaSetUp(long sessionHandle, NVFBC_TOCUDA_SETUP_PARAMS pParams);

    /**
     * Captures a frame to a CUDA device in video memory.
     * <p>
     * This function triggers a frame capture to a CUDA device in video memory.
     * <p>
     * Note about changes of resolution: See {@link #NvFBCToSysGrabFrame(long, NVFBC_TOSYS_GRAB_FRAME_PARAMS)}
     *
     * @param sessionHandle
     *   FBC session handle.
     *
     * @param pParams
     *   ::NVFBC_TOCUDA_GRAB_FRAME_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_INVALID_PTR <br>
     *   ::NVFBC_ERR_CUDA <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_X <br>
     *   ::NVFBC_ERR_MUST_RECREATE <br>
     *   See {@link #NvFBCCreateCaptureSession(long, NVFBC_CREATE_CAPTURE_SESSION_PARAMS)} <br>
     *   See {@link #NvFBCToCudaSetUp(long, NVFBC_TOCUDA_SETUP_PARAMS)}
     */
    int NvFBCToCudaGrabFrame(long sessionHandle, NVFBC_TOCUDA_GRAB_FRAME_PARAMS pParams);

    /**
     * Sets up a capture to OpenGL buffer in video memory session.
     * <p>
     * This function configures how the capture to video memory should behave.
     * It can be called anytime and several times after the capture session has been
     * created.  However, it must be called at least once prior to start capturing
     * frames.
     *
     * @param sessionHandle
     *   FBC session handle.
     *
     * @param pParams
     *   ::NVFBC_TOGL_SETUP_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_UNSUPPORTED <br>
     *   ::NVFBC_ERR_GL <br>
     *   ::NVFBC_ERR_X
     */
    int NvFBCToGLSetUp(long sessionHandle, NVFBC_TOGL_SETUP_PARAMS pParams);

    /**
     * Captures a frame to an OpenGL buffer in video memory.
     * <p>
     * This function triggers a frame capture to a selected resource in video memory.
     * <p>
     * Note about changes of resolution: See {@link #NvFBCToSysGrabFrame(long, NVFBC_TOSYS_GRAB_FRAME_PARAMS)}
     *
     * @param sessionHandle
     *   FBC session handle.
     *
     * @param pParams
     *   ::NVFBC_TOGL_GRAB_FRAME_PARAMS
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_HANDLE <br>
     *   ::NVFBC_ERR_API_VERSION <br>
     *   ::NVFBC_ERR_BAD_REQUEST <br>
     *   ::NVFBC_ERR_CONTEXT <br>
     *   ::NVFBC_ERR_INVALID_PTR <br>
     *   ::NVFBC_ERR_INTERNAL <br>
     *   ::NVFBC_ERR_X <br>
     *   ::NVFBC_ERR_MUST_RECREATE <br>
     *   @see #NvFBCCreateCaptureSession(long, NVFBC_CREATE_CAPTURE_SESSION_PARAMS)  <br>
     *   @see #NvFBCToCudaSetUp(long, NVFBC_TOCUDA_SETUP_PARAMS) 
     */
    int NvFBCToGLGrabFrame(long sessionHandle, NVFBC_TOGL_GRAB_FRAME_PARAMS pParams);

    /**
     * Entry Points to the NvFBC interface.
     * <p>
     * Creates an instance of the NvFBC interface, and populates the
     * pFunctionList with function pointers to the API routines implemented by
     * the NvFBC interface.
     *
     * @return
     *   ::NVFBC_SUCCESS <br>
     *   ::NVFBC_ERR_INVALID_PTR <br>
     *   ::NVFBC_ERR_API_VERSION
     */
    int NvFBCCreateInstance(NVFBC_API_FUNCTION_LIST pFunctionList);

}
