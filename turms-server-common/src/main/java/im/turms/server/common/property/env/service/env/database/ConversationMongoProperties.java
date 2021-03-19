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
public class ConversationMongoProperties extends TurmsMongoProperties {
    private WriteConcern conversationWriteConcern = WriteConcern.ACKNOWLEDGED;
}
