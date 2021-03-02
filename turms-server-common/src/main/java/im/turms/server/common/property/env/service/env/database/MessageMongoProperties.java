package im.turms.server.common.property.env.service.env.database;

import com.mongodb.WriteConcern;
import lombok.Data;

@Data
public class MessageMongoProperties extends TurmsMongoProperties {
    private WriteConcern messageWriteConcern = WriteConcern.ACKNOWLEDGED;
}
