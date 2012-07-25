package ti.mobeelizer.sdk;

import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;

import com.mobeelizer.mobile.android.api.MobeelizerCriterion;
import com.mobeelizer.mobile.android.api.MobeelizerRestrictions;
import com.mobeelizer.mobile.android.api.MobeelizerRestrictions.MatchMode;

@Kroll.proxy
public class MobeelizerRestrictionProxy extends KrollProxy implements withGetMobeelizerCriterion {

    private MobeelizerCriterion criterion;

    @Kroll.constant
    public static final String MATCH_MODE_ANYWHERE = "ANYWHERE";

    @Kroll.constant
    public static final String MATCH_MODE_END = "END";

    @Kroll.constant
    public static final String MATCH_MODE_EXACT = "EXACT";

    @Kroll.constant
    public static final String MATCH_MODE_START = "START";

    public MobeelizerRestrictionProxy() {
    }

    public MobeelizerRestrictionProxy(final MobeelizerCriterion order) {
        this.criterion = order;
    }

    @Kroll.method
    public MobeelizerRestrictionProxy createAnd(final Object[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("createAnd method requires at least one argument");
        }
        MobeelizerRestrictionConjunctionProxy conjunction = conjunction();
        for (Object arg : args) {
            if (!(arg instanceof MobeelizerRestrictionProxy)) {
                throw new IllegalArgumentException("createAnd method arguments must be MobeelizerRestriction");
            }
            conjunction.add((MobeelizerRestrictionProxy) arg);
        }
        return new MobeelizerRestrictionProxy(conjunction.getMobeelizerCriterion());
    }

    @Kroll.method
    public MobeelizerRestrictionProxy createOr(final Object[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("createOr method requires at least one argument");
        }
        MobeelizerRestrictionDisjunctionProxy disjunction = disjunction();
        for (Object arg : args) {
            if (!(arg instanceof MobeelizerRestrictionProxy)) {
                throw new IllegalArgumentException("createOr method arguments must be MobeelizerRestriction");
            }
            disjunction.add((MobeelizerRestrictionProxy) arg);
        }
        return new MobeelizerRestrictionProxy(disjunction.getMobeelizerCriterion());
    }

    @Kroll.method
    public MobeelizerRestrictionProxy createNot(final MobeelizerRestrictionProxy r1) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.not(r1.getMobeelizerCriterion()));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy guidEq(final String guid) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.guidEq(guid));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy ownerEq(final String owner) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.ownerEq(owner));
    }
    
    @Kroll.method
    public MobeelizerRestrictionProxy groupEq(final String group) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.groupEq(group));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy guidNe(final String guid) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.guidNe(guid));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy ownerNe(final String owner) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.ownerNe(owner));
    }
    
    @Kroll.method
    public MobeelizerRestrictionProxy groupNe(final String group) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.groupNe(group));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy isConflicted() {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.isConflicted());
    }

    @Kroll.method
    public MobeelizerRestrictionProxy isNotConflicted() {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.isNotConflicted());
    }

    @Kroll.method
    public MobeelizerRestrictionProxy allEq(final KrollDict dict) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.allEq(dict));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy like(final String field, final String value,
            @Kroll.argument(optional = true) final String mode) {
        if (mode == null) {
            return new MobeelizerRestrictionProxy(MobeelizerRestrictions.like(field, value));
        }
        MatchMode mobeelizerMode = null;
        if (mode.equals(MATCH_MODE_ANYWHERE)) {
            mobeelizerMode = MatchMode.ANYWHERE;
        } else if (mode.equals(MATCH_MODE_END)) {
            mobeelizerMode = MatchMode.END;
        } else if (mode.equals(MATCH_MODE_EXACT)) {
            mobeelizerMode = MatchMode.EXACT;
        } else if (mode.equals(MATCH_MODE_START)) {
            mobeelizerMode = MatchMode.START;
        } else {
            throw new IllegalArgumentException("Unknown maych mode: " + mode);
        }
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.like(field, value, mobeelizerMode));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy le(final String field, final Object value) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.le(field, value));

    }

    @Kroll.method
    public MobeelizerRestrictionProxy lt(final String field, final Object value) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.lt(field, value));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy ge(final String field, final Object value) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.ge(field, value));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy gt(final String field, final Object value) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.gt(field, value));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy ne(final String field, final Object value) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.ne(field, value));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy eq(final String field, final Object value) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.eq(field, value));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy leField(final String field, final String otherField) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.leField(field, otherField));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy ltField(final String field, final String otherField) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.ltField(field, otherField));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy geField(final String field, final String otherField) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.geField(field, otherField));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy gtField(final String field, final String otherField) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.gtField(field, otherField));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy neField(final String field, final String otherField) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.neField(field, otherField));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy eqField(final String field, final String otherField) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.eqField(field, otherField));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy between(final String field, final Object lo, final Object hi) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.between(field, lo, hi));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy inArray(final String field, final Object[] args) {
        if (args.length != 1 || !(args[0] instanceof Object[])) {
            throw new IllegalArgumentException("Wrong array in 'inArray' method");
        }
        Object[] values = (Object[]) args[0];
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.in(field, values));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy belongsTo(final String field, final String model, final String guid) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.belongsTo(field, model, guid));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy belongsToObject(final String field, final MobeelizerEntityProxy entity) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.belongsTo(field, entity.getModel(), entity.getGuid()));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy isNotNull(final String field) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.isNotNull(field));
    }

    @Kroll.method
    public MobeelizerRestrictionProxy isNull(final String field) {
        return new MobeelizerRestrictionProxy(MobeelizerRestrictions.isNull(field));
    }

    @Kroll.method
    public MobeelizerRestrictionDisjunctionProxy disjunction() {
        return new MobeelizerRestrictionDisjunctionProxy(MobeelizerRestrictions.disjunction());
    }

    @Kroll.method
    public MobeelizerRestrictionConjunctionProxy conjunction() {
        return new MobeelizerRestrictionConjunctionProxy(MobeelizerRestrictions.conjunction());
    }

    @Override
    public MobeelizerCriterion getMobeelizerCriterion() {
        if (criterion == null) {
            throw new IllegalStateException("Restriction not defined");
        }
        return criterion;
    }
}
