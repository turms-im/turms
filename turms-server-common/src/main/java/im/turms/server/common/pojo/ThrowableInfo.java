package im.turms.server.common.pojo;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.util.CloseReasonUtil;
import lombok.Getter;

@Getter
public class ThrowableInfo {

    private final TurmsStatusCode code;
    private final String reason;

    private ThrowableInfo(Throwable throwable) {
        if (throwable instanceof TurmsBusinessException) {
            TurmsBusinessException exception = (TurmsBusinessException) throwable;
            code = exception.getCode();
            if (code.isServerError()) {
                reason = CloseReasonUtil.isReturnReasonForServerError()
                        ? exception.getReason()
                        : null;
            } else {
                reason = exception.getReason();
            }
        } else {
            code = TurmsStatusCode.SERVER_INTERNAL_ERROR;
            reason = CloseReasonUtil.isReturnReasonForServerError()
                    ? throwable.getMessage()
                    : null;
        }
    }

    public static ThrowableInfo get(Throwable throwable) {
        return new ThrowableInfo(throwable);
    }

    public TurmsNotification toNotification(long requestId) {
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(Int64Value.newBuilder().setValue(requestId).build())
                .setCode(Int32Value.newBuilder().setValue(getCode().getBusinessCode()).build());
        if (reason != null) {
            builder.setReason(StringValue.newBuilder().setValue(reason).build());
        }
        return builder.build();
    }

}
