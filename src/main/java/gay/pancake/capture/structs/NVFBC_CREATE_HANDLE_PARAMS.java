package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the CreateHandle() API call.
 */
public class NVFBC_CREATE_HANDLE_PARAMS extends Structure {
    public NVFBC_CREATE_HANDLE_PARAMS() { super(); }
    public NVFBC_CREATE_HANDLE_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_CREATE_HANDLE_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_CREATE_HANDLE_PARAMS(), 2);

    /**
     * [in] Must be set to NVFBC_CREATE_HANDLE_PARAMS_VER
     */
    public int dwVersion = NVFBC_CREATE_HANDLE_PARAMS_VER;

    /**
     * [in] Application specific private information passed to the NvFBC
     * session.
     */
    public Pointer privateData;

    /**
     * [in] Size of the application specific private information passed to the
     * NvFBC session.
     */
    public int privateDataSize;

    /**
     * [in] Whether NvFBC should not create and manage its own graphics context
     * <p>
     * NvFBC internally uses OpenGL to perfom graphics operations on the
     * captured frames.  By default, NvFBC will create and manage (e.g., make
     * current, detect new threads, etc.) its own OpenGL context.
     * <p>
     * If set to NVFBC_TRUE, NvFBC will use the application's context.  It will
     * be the application's responsibility to make sure that a context is
     * current on the thread calling into the NvFBC API.
     */
    public int bExternallyManagedContext; // NVFBC_BOOL

    /**
     * [in] GLX context
     * <p>
     * GLX context that NvFBC should use internally to create pixmaps and
     * make them current when creating a new capture session.
     * <p>
     * Note: NvFBC expects a context created against a GLX_RGBA_TYPE render
     * type.
     */
    public Pointer glxCtx;

    /**
     * [in] GLX framebuffer configuration
     * <p>
     * Framebuffer configuration that was used to create the GLX context, and
     * that will be used to create pixmaps internally.
     * <p>
     * Note: NvFBC expects a configuration having at least the following
     * attributes:
     *  GLX_DRAWABLE_TYPE, GLX_PIXMAP_BIT
     *  GLX_BIND_TO_TEXTURE_RGBA_EXT, 1
     *  GLX_BIND_TO_TEXTURE_TARGETS_EXT, GLX_TEXTURE_2D_BIT_EXT
     */
    public Pointer glxFBConfig;

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "privateData", "privateDataSize", "bExternallyManagedContext", "glxCtx", "glxFBConfig");
    }

}
