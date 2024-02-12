package gay.pancake.capture.structs;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.List;

import static gay.pancake.capture.NvFBC.NVFBC_STRUCT_VERSION;

/**
 * Defines parameters for the ::NvFBCToCudaSetUp() API call.
 */
public class NVFBC_TOCUDA_SETUP_PARAMS extends Structure {
    public NVFBC_TOCUDA_SETUP_PARAMS() { super(); }
    public NVFBC_TOCUDA_SETUP_PARAMS(Pointer p) { super(p); }
    public static final int NVFBC_TOCUDA_SETUP_PARAMS_VER = NVFBC_STRUCT_VERSION(new NVFBC_TOCUDA_SETUP_PARAMS(), 1);

    /**
     * [in] Must be set to NVFBC_TOCUDA_SETUP_PARAMS_VER
     */
    public int dwVersion = NVFBC_TOCUDA_SETUP_PARAMS_VER;

    /**
     * [in] Desired buffer format.
     */
    public int eBufferFormat; // NVFBC_BUFFER_FORMAT

    @Override
    protected List<String> getFieldOrder() {
        return List.of("dwVersion", "eBufferFormat");
    }

}
