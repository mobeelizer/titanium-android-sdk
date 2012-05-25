package ti.mobeelizer.sdk;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.java.api.MobeelizerError;

@Kroll.proxy
public class MobeelizerErrorProxy extends KrollProxy {

    private final String code;

    private final String message;

    private final Object[] args;

    public MobeelizerErrorProxy(final MobeelizerError mobeelizerError) {
        code = mobeelizerError.getCode().toString();
        args = mobeelizerError.getArgs().toArray();
        message = mobeelizerError.getMessage();
    }

    @Kroll.getProperty
    public String getCode() {
        return code;
    }

    @Kroll.getProperty
    public String getMessage() {
        return message;
    }

    @Kroll.getProperty
    public Object[] getArgs() {
        return args;
    }

    @Override
    @Kroll.method
    public String toString() {
        return "MobeelizerError: {code=" + code + ", message=" + message + "}";
    }
}
