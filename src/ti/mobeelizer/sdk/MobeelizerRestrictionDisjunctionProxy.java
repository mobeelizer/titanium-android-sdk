package ti.mobeelizer.sdk;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.mobile.android.api.MobeelizerDisjunction;

@Kroll.proxy
public class MobeelizerRestrictionDisjunctionProxy extends KrollProxy implements withGetMobeelizerCriterion {

    private final MobeelizerDisjunction disjunction;

    public MobeelizerRestrictionDisjunctionProxy(final MobeelizerDisjunction disjunction) {
        this.disjunction = disjunction;
    }

    @Kroll.method
    public MobeelizerRestrictionDisjunctionProxy add(final MobeelizerRestrictionProxy restriction) {
        disjunction.add(restriction.getMobeelizerCriterion());
        return this;
    }

    @Override
    public MobeelizerDisjunction getMobeelizerCriterion() {
        return disjunction;
    }

}
