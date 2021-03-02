package im.turms.server.common.property.env.service.env.database;

import com.mongodb.WriteConcern;
import lombok.Data;

@Data
public class AdminMongoProperties extends TurmsMongoProperties {
    private WriteConcern adminWriteConcern = WriteConcern.MAJORITY;
    private WriteConcern adminRoleWriteConcern = WriteConcern.MAJORITY;
}
