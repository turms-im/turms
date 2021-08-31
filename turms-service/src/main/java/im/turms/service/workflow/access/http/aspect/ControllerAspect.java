package im.turms.service.workflow.access.http.aspect;

import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author James Chen
 */
@Aspect
@Component
public class ControllerAspect {

    private final Node node;

    public ControllerAspect(Node node) {
        this.node = node;
    }

    @Around("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        if (!isValidDeleteRequest(point)) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_FILTER_FOR_DELETE_OPERATION));
        }
        return point.proceed();
    }

    private boolean isValidDeleteRequest(ProceedingJoinPoint point) {
        if (node.getSharedProperties().getService().getAdminApi().isAllowDeletingWithoutFilter()) {
            return true;
        }
        Object[] args = point.getArgs();
        return !Validator.areAllFalsy(args);
    }
}