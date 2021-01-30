package im.turms.server.common.pojo;

import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import lombok.Data;

@Data
public class ThrowableInfo {

    private final TurmsStatusCode code;
    private final String reason;

    private ThrowableInfo(Throwable throwable) {
        if (throwable instanceof TurmsBusinessException) {
            TurmsBusinessException exception = (TurmsBusinessException) throwable;
            code = exception.getCode();
            reason = exception.getReason();
        } else {
            code = TurmsStatusCode.SERVER_INTERNAL_ERROR;
            reason = throwable.getMessage();
        }
    }

    public static ThrowableInfo get(Throwable throwable) {
        return new ThrowableInfo(throwable);
    }

}