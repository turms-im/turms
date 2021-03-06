package im.turms.server.common.property.env.service.env.database;

import com.mongodb.WriteConcern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GroupMongoProperties extends TurmsMongoProperties {
    private WriteConcern groupWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern groupBlockedUserWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern groupInvitationWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern groupJoinQuestionWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern groupJoinRequestWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern groupMemberWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern groupTypeWriteConcern = WriteConcern.MAJORITY;
    private WriteConcern groupVersionWriteConcern = WriteConcern.ACKNOWLEDGED;
}
