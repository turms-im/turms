package im.turms.common.util;

import im.turms.common.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Map;

public class Validator {
    private Validator() {
    }

    public static void throwIfAnyFalsy(@NotEmpty Object... array) {
        for (Object o : array) {
            if (o == null) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
            } else {
                if (o instanceof String) {
                    if (((String) o).isBlank()) {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
                    }
                } else if (o instanceof Collection && ((Collection<?>) o).isEmpty()) {
                    throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
                }
            }
        }
    }

    public static void throwIfAnyNull(@NotEmpty Object... array) {
        for (Object o : array) {
            if (o == null) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
            }
        }
    }

    public static void throwIfAllFalsy(@NotEmpty Object... array) {
        if (areAllFalsy(array)) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
        }
    }

    public static boolean areAllFalsy(Object... array) {
        for (Object o : array) {
            if (o != null) {
                if (o instanceof String) {
                    if (!((String) o).isBlank()) {
                        return false;
                    }
                } else if (o instanceof Collection) {
                    if (!((Collection<?>) o).isEmpty()) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean areAllNull(Object... array) {
        if (array == null) {
            return true;
        } else {
            for (Object o : array) {
                if (o != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void throwIfEmpty(Object object) {
        if (object instanceof Map) {
            if (((Map<?, ?>) object).isEmpty()) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
            }
        } else if (object instanceof Collection && ((Collection<?>) object).isEmpty()) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS);
        }
    }
}
