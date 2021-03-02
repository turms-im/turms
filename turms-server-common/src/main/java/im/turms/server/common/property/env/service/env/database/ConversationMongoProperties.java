package im.turms.server.common.property.env.service.env.database;

import com.mongodb.WriteConcern;
import lombok.Data;

@Data
public class ConversationMongoProperties extends TurmsMongoProperties {
    private WriteConcern conversationWriteConcern = WriteConcern.ACKNOWLEDGED;
}
