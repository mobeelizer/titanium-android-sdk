package ti.mobeelizer.sdk;

import java.util.LinkedList;
import java.util.List;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.java.api.MobeelizerError;
import com.mobeelizer.java.api.MobeelizerErrors;

@Kroll.proxy
public class MobeelizerErrorsProxy extends KrollProxy {

    private final MobeelizerErrors errors;

    public MobeelizerErrorsProxy(final MobeelizerErrors errors) {
        this.errors = errors;
    }

    @Kroll.method
    public boolean isValid() {
        return errors.isValid();
    }

    @Kroll.method
    public MobeelizerErrorProxy[] getErrors() {
        return convertToProxyErrors(errors.getErrors());
    }

    @Kroll.method
    public boolean isFieldValid(final String field) {
        return errors.isFieldValid(field);
    }

    @Kroll.method
    public MobeelizerErrorProxy[] getFieldErrors(final String field) {
        return convertToProxyErrors(errors.getFieldErrors(field));
    }

    private MobeelizerErrorProxy[] convertToProxyErrors(final List<MobeelizerError> errors) {
        List<MobeelizerErrorProxy> result = new LinkedList<MobeelizerErrorProxy>();
        for (MobeelizerError error : errors) {
            result.add(new MobeelizerErrorProxy(error));
        }
        return result.toArray(new MobeelizerErrorProxy[result.size()]);
    }

}
