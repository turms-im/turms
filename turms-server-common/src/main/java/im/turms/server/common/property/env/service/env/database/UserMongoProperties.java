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
public class UserMongoProperties extends TurmsMongoProperties {
    private WriteConcern userWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern userFriendRequestWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern userMaxDailyOnlineUserWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern userPermissionGroupWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern userRelationshipWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern userRelationshipGroupWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern userRelationshipGroupMemberWriteConcern = WriteConcern.ACKNOWLEDGED;
    private WriteConcern userVersionWriteConcern = WriteConcern.ACKNOWLEDGED;
}
