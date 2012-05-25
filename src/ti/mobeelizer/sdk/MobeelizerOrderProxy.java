package ti.mobeelizer.sdk;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.mobile.android.api.MobeelizerOrder;
import com.mobeelizer.mobile.android.api.MobeelizerOrders;

@Kroll.proxy
public class MobeelizerOrderProxy extends KrollProxy {

    private MobeelizerOrder order;

    public MobeelizerOrderProxy() {
    }

    public MobeelizerOrderProxy(final MobeelizerOrder order) {
        this.order = order;
    }

    @Kroll.method
    public MobeelizerOrderProxy asc(final String field) {
        return new MobeelizerOrderProxy(MobeelizerOrders.asc(field));
    }

    @Kroll.method
    public MobeelizerOrderProxy desc(final String field) {
        return new MobeelizerOrderProxy(MobeelizerOrders.desc(field));
    }

    public MobeelizerOrder getMobeelizerOrder() {
        if (order == null) {
            throw new IllegalStateException("Order not defined");
        }
        return order;
    }
}
