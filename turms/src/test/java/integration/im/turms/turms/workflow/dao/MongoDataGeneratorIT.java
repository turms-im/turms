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
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class MongoDataGeneratorIT extends BaseIntegrationTest {

    @Test
    void validateSchemas() {
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

        // Build a mongo client
        TurmsMongoProperties mongoProperties = new TurmsMongoProperties(ENV.getMongoUri("turms-test"));
        TurmsMongoClient mongoClient = TurmsMongoClient.of(mongoProperties);

        // init and populate collections
        new MongoDataGenerator(mongoClient,
                mongoClient,
                mongoClient,
                mongoClient,
                mongoClient,
                passwordManager,
                propertiesManager,
                context);

        // Admin
        validateSchema(mongoClient, Admin.class);
        validateSchema(mongoClient, AdminRole.class);

        // Group
        validateSchema(mongoClient, Group.class);
        validateSchema(mongoClient, GroupBlockedUser.class);
        validateSchema(mongoClient, GroupInvitation.class);
        validateSchema(mongoClient, GroupJoinQuestion.class);
        validateSchema(mongoClient, GroupJoinRequest.class);
        validateSchema(mongoClient, GroupMember.class);
        validateSchema(mongoClient, GroupType.class);
        validateSchema(mongoClient, GroupVersion.class);

        // Message
        validateSchema(mongoClient, Message.class);

        // Conversation
        validateSchema(mongoClient, GroupConversation.class);
        validateSchema(mongoClient, PrivateConversation.class);

        // User
        validateSchema(mongoClient, User.class);
        validateSchema(mongoClient, UserFriendRequest.class);
        validateSchema(mongoClient, UserPermissionGroup.class);
        validateSchema(mongoClient, UserRelationship.class);
        validateSchema(mongoClient, UserRelationshipGroup.class);
        validateSchema(mongoClient, UserRelationshipGroupMember.class);
        validateSchema(mongoClient, UserVersion.class);
    }

    private void validateSchema(TurmsMongoClient mongoClient, Class<?> clazz) {
        Duration timeout = Duration.ofSeconds(10);
        String schemaFileName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, clazz.getSimpleName());
        StepVerifier.create(mongoClient.validate(clazz, readJsonSchema(schemaFileName)))
                .as("Entity " + clazz.getSimpleName() + " should have a valid schema")
                .expectNext(true)
                .expectComplete()
                .verify(timeout);
    }

    private String readJsonSchema(String name) {
        String path = "schema/" + name + ".json";
        InputStream resource = MongoDataGeneratorIT.class.getClassLoader().getResourceAsStream(path);
        if (resource == null) {
            throw new IllegalStateException("Cannot find resource " + path);
        }
        try {
            return new String(resource.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}