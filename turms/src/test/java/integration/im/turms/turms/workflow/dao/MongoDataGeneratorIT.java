package integration.im.turms.turms.workflow.dao;

import com.google.common.base.CaseFormat;
import im.turms.server.common.context.ApplicationContext;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.ServiceProperties;
import im.turms.server.common.property.env.service.env.MockProperties;
import im.turms.server.common.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.testing.BaseIntegrationTest;
import im.turms.server.common.util.CollectorUtil;
import im.turms.turms.workflow.dao.MongoDataGenerator;
import im.turms.turms.workflow.dao.domain.admin.Admin;
import im.turms.turms.workflow.dao.domain.admin.AdminRole;
import im.turms.turms.workflow.dao.domain.conversation.GroupConversation;
import im.turms.turms.workflow.dao.domain.conversation.PrivateConversation;
import im.turms.turms.workflow.dao.domain.group.Group;
import im.turms.turms.workflow.dao.domain.group.GroupBlockedUser;
import im.turms.turms.workflow.dao.domain.group.GroupInvitation;
import im.turms.turms.workflow.dao.domain.group.GroupJoinQuestion;
import im.turms.turms.workflow.dao.domain.group.GroupJoinRequest;
import im.turms.turms.workflow.dao.domain.group.GroupMember;
import im.turms.turms.workflow.dao.domain.group.GroupType;
import im.turms.turms.workflow.dao.domain.group.GroupVersion;
import im.turms.turms.workflow.dao.domain.message.Message;
import im.turms.turms.workflow.dao.domain.user.UserFriendRequest;
import im.turms.turms.workflow.dao.domain.user.UserPermissionGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationship;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroupMember;
import im.turms.turms.workflow.dao.domain.user.UserVersion;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class MongoDataGeneratorIT extends BaseIntegrationTest {

    static final TurmsMongoClient MONGO_CLIENT;
    static final List<Class<?>> MODEL_CLASSES = List.of(
            // Admin
            Admin.class,
            AdminRole.class,
            // Group
            Group.class,
            GroupBlockedUser.class,
            GroupInvitation.class,
            GroupJoinQuestion.class,
            GroupJoinRequest.class,
            GroupMember.class,
            GroupType.class,
            GroupVersion.class,
            // Message
            Message.class,
            // Conversation
            GroupConversation.class,
            PrivateConversation.class,
            // User
            User.class,
            UserFriendRequest.class,
            UserPermissionGroup.class,
            UserRelationship.class,
            UserRelationshipGroup.class,
            UserRelationshipGroupMember.class,
            UserVersion.class);

    static {
        TurmsMongoProperties mongoProperties = new TurmsMongoProperties(ENV.getMongoUri("turms-test"));
        MONGO_CLIENT = TurmsMongoClient.of(mongoProperties);
        MONGO_CLIENT.registerEntitiesByClasses(MODEL_CLASSES);

        // Mock
        PasswordManager passwordManager = mock(PasswordManager.class);
        when(passwordManager.encodeAdminPassword(anyString())).thenReturn("123");
        when(passwordManager.encodeUserPassword(anyString())).thenReturn("123");

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .service(new ServiceProperties().toBuilder()
                        .mock(new MockProperties().toBuilder()
                                .enabled(true)
                                .build())
                        .build())
                .build());

        ApplicationContext context = mock(ApplicationContext.class);
        when(context.isProduction()).thenReturn(false);

        // init and populate collections
        new MongoDataGenerator(MONGO_CLIENT,
                MONGO_CLIENT,
                MONGO_CLIENT,
                MONGO_CLIENT,
                MONGO_CLIENT,
                passwordManager,
                propertiesManager,
                context);
    }

    @Test
    void validateSchemas() {
        for (Class<?> modelClass : MODEL_CLASSES) {
            String schemaFileName = getFileName(modelClass);
            String jsonSchema = readText("schema/" + schemaFileName + ".json");
            StepVerifier.create(MONGO_CLIENT.validate(modelClass, jsonSchema))
                    .expectNext(true)
                    .as("Entity " + modelClass.getSimpleName() + " should have a valid schema")
                    .expectComplete()
                    .verify(DEFAULT_IO_TIMEOUT);
        }
    }

    @Test
    void validateIndexes() {
        for (Class<?> modelClass : MODEL_CLASSES) {
            StepVerifier.create(MONGO_CLIENT.listIndexes(modelClass).collect(CollectorUtil.toList(8)))
                    .assertNext(indexes -> {
                        String schemaFileName = getFileName(modelClass);
                        String json = readText("index/" + schemaFileName + ".json");
                        List<Document> expectedIndexes = (List<Document>) Document.parse("{\"json\":" + json + "}").get("json");
                        assertThat(indexes)
                                .as("Entity " + modelClass.getSimpleName() + " should have the expected size")
                                .hasSize(expectedIndexes.size());
                        expectedIndexes.forEach(expectedIndex -> {
                            Object expectedIndexName = expectedIndex.get("name");
                            Optional<Document> found = indexes.stream()
                                    .filter(document -> document.get("name").equals(expectedIndexName))
                                    .findFirst();
                            assertThat(found)
                                    .as("Cannot find the index " + expectedIndexName)
                                    .isPresent();
                            Document index = found.get();
                            for (Map.Entry<String, Object> entry : expectedIndex.entrySet()) {
                                assertThat(index.get(entry.getKey())).isEqualTo(entry.getValue());
                            }
                        });
                    })
                    .as("Entity " + modelClass.getSimpleName() + " should have valid index models")
                    .expectComplete()
                    .verify(DEFAULT_IO_TIMEOUT);
        }
    }

    private String getFileName(Class<?> modelClass) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, modelClass.getSimpleName());
    }

}