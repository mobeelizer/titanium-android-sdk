package ti.mobeelizer.sdk;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.mobile.android.api.MobeelizerConjunction;

@Kroll.proxy
public class MobeelizerRestrictionConjunctionProxy extends KrollProxy implements withGetMobeelizerCriterion {

    private final MobeelizerConjunction conjunction;

    public MobeelizerRestrictionConjunctionProxy(final MobeelizerConjunction conjunction) {
        this.conjunction = conjunction;
    }

    @Kroll.method
    public MobeelizerRestrictionConjunctionProxy add(final MobeelizerRestrictionProxy restriction) {
        conjunction.add(restriction.getMobeelizerCriterion());
        return this;
    }

    @Override
    public MobeelizerConjunction getMobeelizerCriterion() {
        return conjunction;
    }

}
