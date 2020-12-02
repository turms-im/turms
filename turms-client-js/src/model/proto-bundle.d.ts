import * as $protobuf from "protobufjs";
/** Namespace im. */
export namespace im {

    /** Namespace turms. */
    namespace turms {

        /** Namespace proto. */
        namespace proto {

            /** ContentType enum. */
            enum ContentType {
                PROFILE = 0,
                GROUP_PROFILE = 1,
                ATTACHMENT = 2
            }

            /** DeviceType enum. */
            enum DeviceType {
                DESKTOP = 0,
                BROWSER = 1,
                IOS = 2,
                ANDROID = 3,
                OTHERS = 4,
                UNKNOWN = 5
            }

            /** GroupMemberRole enum. */
            enum GroupMemberRole {
                OWNER = 0,
                MANAGER = 1,
                MEMBER = 2,
                GUEST = 3,
                ANONYMOUS_GUEST = 4
            }

            /** MessageDeliveryStatus enum. */
            enum MessageDeliveryStatus {
                READY = 0,
                SENDING = 1,
                RECEIVED = 2,
                RECALLING = 3,
                RECALLED = 4
            }

            /** ProfileAccessStrategy enum. */
            enum ProfileAccessStrategy {
                ALL = 0,
                ALL_EXCEPT_BLACKLISTED_USERS = 1,
                FRIENDS = 2
            }

            /** RequestStatus enum. */
            enum RequestStatus {
                PENDING = 0,
                ACCEPTED = 1,
                ACCEPTED_WITHOUT_CONFIRM = 2,
                DECLINED = 3,
                IGNORED = 4,
                EXPIRED = 5,
                CANCELED = 6
            }

            /** ResponseAction enum. */
            enum ResponseAction {
                ACCEPT = 0,
                DECLINE = 1,
                IGNORE = 2
            }

            /** UserStatus enum. */
            enum UserStatus {
                AVAILABLE = 0,
                OFFLINE = 1,
                INVISIBLE = 2,
                BUSY = 3,
                DO_NOT_DISTURB = 4,
                AWAY = 5,
                BE_RIGHT_BACK = 6
            }

            /** Properties of an Int64ValuesWithVersion. */
            interface IInt64ValuesWithVersion {

                /** Int64ValuesWithVersion values */
                values?: (string[]|null);

                /** Int64ValuesWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents an Int64ValuesWithVersion. */
            class Int64ValuesWithVersion implements IInt64ValuesWithVersion {

                /**
                 * Constructs a new Int64ValuesWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IInt64ValuesWithVersion);

                /** Int64ValuesWithVersion values. */
                public values: string[];

                /** Int64ValuesWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified Int64ValuesWithVersion message. Does not implicitly {@link im.turms.proto.Int64ValuesWithVersion.verify|verify} messages.
                 * @param message Int64ValuesWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IInt64ValuesWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an Int64ValuesWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns Int64ValuesWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.Int64ValuesWithVersion;
            }

            /** Properties of an Int64Values. */
            interface IInt64Values {

                /** Int64Values values */
                values?: (string[]|null);
            }

            /** Represents an Int64Values. */
            class Int64Values implements IInt64Values {

                /**
                 * Constructs a new Int64Values.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IInt64Values);

                /** Int64Values values. */
                public values: string[];

                /**
                 * Encodes the specified Int64Values message. Does not implicitly {@link im.turms.proto.Int64Values.verify|verify} messages.
                 * @param message Int64Values message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IInt64Values, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an Int64Values message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns Int64Values
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.Int64Values;
            }

            /** Properties of an AudioFile. */
            interface IAudioFile {

                /** AudioFile description */
                description?: (im.turms.proto.AudioFile.IDescription|null);

                /** AudioFile data */
                data?: (google.protobuf.IBytesValue|null);
            }

            /** Represents an AudioFile. */
            class AudioFile implements IAudioFile {

                /**
                 * Constructs a new AudioFile.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IAudioFile);

                /** AudioFile description. */
                public description?: (im.turms.proto.AudioFile.IDescription|null);

                /** AudioFile data. */
                public data?: (google.protobuf.IBytesValue|null);

                /**
                 * Encodes the specified AudioFile message. Does not implicitly {@link im.turms.proto.AudioFile.verify|verify} messages.
                 * @param message AudioFile message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IAudioFile, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an AudioFile message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns AudioFile
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.AudioFile;
            }

            namespace AudioFile {

                /** Properties of a Description. */
                interface IDescription {

                    /** Description url */
                    url?: (string|null);

                    /** Description duration */
                    duration?: (google.protobuf.IInt32Value|null);

                    /** Description size */
                    size?: (google.protobuf.IInt32Value|null);

                    /** Description format */
                    format?: (google.protobuf.IStringValue|null);
                }

                /** Represents a Description. */
                class Description implements IDescription {

                    /**
                     * Constructs a new Description.
                     * @param [properties] Properties to set
                     */
                    constructor(properties?: im.turms.proto.AudioFile.IDescription);

                    /** Description url. */
                    public url: string;

                    /** Description duration. */
                    public duration?: (google.protobuf.IInt32Value|null);

                    /** Description size. */
                    public size?: (google.protobuf.IInt32Value|null);

                    /** Description format. */
                    public format?: (google.protobuf.IStringValue|null);

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.AudioFile.Description.verify|verify} messages.
                     * @param message Description message or plain object to encode
                     * @param [writer] Writer to encode to
                     * @returns Writer
                     */
                    public static encode(message: im.turms.proto.AudioFile.IDescription, writer?: $protobuf.Writer): $protobuf.Writer;

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @param reader Reader or buffer to decode from
                     * @param [length] Message length if known beforehand
                     * @returns Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.AudioFile.Description;
                }
            }

            /** Properties of a File. */
            interface IFile {

                /** File description */
                description?: (im.turms.proto.File.IDescription|null);

                /** File data */
                data?: (google.protobuf.IBytesValue|null);
            }

            /** Represents a File. */
            class File implements IFile {

                /**
                 * Constructs a new File.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IFile);

                /** File description. */
                public description?: (im.turms.proto.File.IDescription|null);

                /** File data. */
                public data?: (google.protobuf.IBytesValue|null);

                /**
                 * Encodes the specified File message. Does not implicitly {@link im.turms.proto.File.verify|verify} messages.
                 * @param message File message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IFile, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a File message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns File
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.File;
            }

            namespace File {

                /** Properties of a Description. */
                interface IDescription {

                    /** Description url */
                    url?: (string|null);

                    /** Description size */
                    size?: (google.protobuf.IInt32Value|null);

                    /** Description format */
                    format?: (google.protobuf.IStringValue|null);
                }

                /** Represents a Description. */
                class Description implements IDescription {

                    /**
                     * Constructs a new Description.
                     * @param [properties] Properties to set
                     */
                    constructor(properties?: im.turms.proto.File.IDescription);

                    /** Description url. */
                    public url: string;

                    /** Description size. */
                    public size?: (google.protobuf.IInt32Value|null);

                    /** Description format. */
                    public format?: (google.protobuf.IStringValue|null);

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.File.Description.verify|verify} messages.
                     * @param message Description message or plain object to encode
                     * @param [writer] Writer to encode to
                     * @returns Writer
                     */
                    public static encode(message: im.turms.proto.File.IDescription, writer?: $protobuf.Writer): $protobuf.Writer;

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @param reader Reader or buffer to decode from
                     * @param [length] Message length if known beforehand
                     * @returns Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.File.Description;
                }
            }

            /** Properties of an ImageFile. */
            interface IImageFile {

                /** ImageFile description */
                description?: (im.turms.proto.ImageFile.IDescription|null);

                /** ImageFile data */
                data?: (google.protobuf.IBytesValue|null);
            }

            /** Represents an ImageFile. */
            class ImageFile implements IImageFile {

                /**
                 * Constructs a new ImageFile.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IImageFile);

                /** ImageFile description. */
                public description?: (im.turms.proto.ImageFile.IDescription|null);

                /** ImageFile data. */
                public data?: (google.protobuf.IBytesValue|null);

                /**
                 * Encodes the specified ImageFile message. Does not implicitly {@link im.turms.proto.ImageFile.verify|verify} messages.
                 * @param message ImageFile message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IImageFile, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an ImageFile message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns ImageFile
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.ImageFile;
            }

            namespace ImageFile {

                /** Properties of a Description. */
                interface IDescription {

                    /** Description url */
                    url?: (string|null);

                    /** Description original */
                    original?: (google.protobuf.IBoolValue|null);

                    /** Description imageSize */
                    imageSize?: (google.protobuf.IInt32Value|null);

                    /** Description fileSize */
                    fileSize?: (google.protobuf.IInt32Value|null);
                }

                /** Represents a Description. */
                class Description implements IDescription {

                    /**
                     * Constructs a new Description.
                     * @param [properties] Properties to set
                     */
                    constructor(properties?: im.turms.proto.ImageFile.IDescription);

                    /** Description url. */
                    public url: string;

                    /** Description original. */
                    public original?: (google.protobuf.IBoolValue|null);

                    /** Description imageSize. */
                    public imageSize?: (google.protobuf.IInt32Value|null);

                    /** Description fileSize. */
                    public fileSize?: (google.protobuf.IInt32Value|null);

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.ImageFile.Description.verify|verify} messages.
                     * @param message Description message or plain object to encode
                     * @param [writer] Writer to encode to
                     * @returns Writer
                     */
                    public static encode(message: im.turms.proto.ImageFile.IDescription, writer?: $protobuf.Writer): $protobuf.Writer;

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @param reader Reader or buffer to decode from
                     * @param [length] Message length if known beforehand
                     * @returns Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.ImageFile.Description;
                }
            }

            /** Properties of a VideoFile. */
            interface IVideoFile {

                /** VideoFile description */
                description?: (im.turms.proto.VideoFile.IDescription|null);

                /** VideoFile data */
                data?: (google.protobuf.IBytesValue|null);
            }

            /** Represents a VideoFile. */
            class VideoFile implements IVideoFile {

                /**
                 * Constructs a new VideoFile.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IVideoFile);

                /** VideoFile description. */
                public description?: (im.turms.proto.VideoFile.IDescription|null);

                /** VideoFile data. */
                public data?: (google.protobuf.IBytesValue|null);

                /**
                 * Encodes the specified VideoFile message. Does not implicitly {@link im.turms.proto.VideoFile.verify|verify} messages.
                 * @param message VideoFile message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IVideoFile, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a VideoFile message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns VideoFile
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.VideoFile;
            }

            namespace VideoFile {

                /** Properties of a Description. */
                interface IDescription {

                    /** Description url */
                    url?: (string|null);

                    /** Description duration */
                    duration?: (google.protobuf.IInt32Value|null);

                    /** Description size */
                    size?: (google.protobuf.IInt32Value|null);

                    /** Description format */
                    format?: (google.protobuf.IStringValue|null);
                }

                /** Represents a Description. */
                class Description implements IDescription {

                    /**
                     * Constructs a new Description.
                     * @param [properties] Properties to set
                     */
                    constructor(properties?: im.turms.proto.VideoFile.IDescription);

                    /** Description url. */
                    public url: string;

                    /** Description duration. */
                    public duration?: (google.protobuf.IInt32Value|null);

                    /** Description size. */
                    public size?: (google.protobuf.IInt32Value|null);

                    /** Description format. */
                    public format?: (google.protobuf.IStringValue|null);

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.VideoFile.Description.verify|verify} messages.
                     * @param message Description message or plain object to encode
                     * @param [writer] Writer to encode to
                     * @returns Writer
                     */
                    public static encode(message: im.turms.proto.VideoFile.IDescription, writer?: $protobuf.Writer): $protobuf.Writer;

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @param reader Reader or buffer to decode from
                     * @param [length] Message length if known beforehand
                     * @returns Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.VideoFile.Description;
                }
            }

            /** Properties of a GroupInvitation. */
            interface IGroupInvitation {

                /** GroupInvitation id */
                id?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation creationDate */
                creationDate?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation content */
                content?: (google.protobuf.IStringValue|null);

                /** GroupInvitation status */
                status?: (im.turms.proto.RequestStatus|null);

                /** GroupInvitation expirationDate */
                expirationDate?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation inviterId */
                inviterId?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation inviteeId */
                inviteeId?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a GroupInvitation. */
            class GroupInvitation implements IGroupInvitation {

                /**
                 * Constructs a new GroupInvitation.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupInvitation);

                /** GroupInvitation id. */
                public id?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation creationDate. */
                public creationDate?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation content. */
                public content?: (google.protobuf.IStringValue|null);

                /** GroupInvitation status. */
                public status: im.turms.proto.RequestStatus;

                /** GroupInvitation expirationDate. */
                public expirationDate?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation inviterId. */
                public inviterId?: (google.protobuf.IInt64Value|null);

                /** GroupInvitation inviteeId. */
                public inviteeId?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified GroupInvitation message. Does not implicitly {@link im.turms.proto.GroupInvitation.verify|verify} messages.
                 * @param message GroupInvitation message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupInvitation, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupInvitation message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupInvitation
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupInvitation;
            }

            /** Properties of a GroupInvitationsWithVersion. */
            interface IGroupInvitationsWithVersion {

                /** GroupInvitationsWithVersion groupInvitations */
                groupInvitations?: (im.turms.proto.IGroupInvitation[]|null);

                /** GroupInvitationsWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a GroupInvitationsWithVersion. */
            class GroupInvitationsWithVersion implements IGroupInvitationsWithVersion {

                /**
                 * Constructs a new GroupInvitationsWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupInvitationsWithVersion);

                /** GroupInvitationsWithVersion groupInvitations. */
                public groupInvitations: im.turms.proto.IGroupInvitation[];

                /** GroupInvitationsWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified GroupInvitationsWithVersion message. Does not implicitly {@link im.turms.proto.GroupInvitationsWithVersion.verify|verify} messages.
                 * @param message GroupInvitationsWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupInvitationsWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupInvitationsWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupInvitationsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupInvitationsWithVersion;
            }

            /** Properties of a GroupJoinQuestion. */
            interface IGroupJoinQuestion {

                /** GroupJoinQuestion id */
                id?: (google.protobuf.IInt64Value|null);

                /** GroupJoinQuestion groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** GroupJoinQuestion question */
                question?: (google.protobuf.IStringValue|null);

                /** GroupJoinQuestion answers */
                answers?: (string[]|null);

                /** GroupJoinQuestion score */
                score?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a GroupJoinQuestion. */
            class GroupJoinQuestion implements IGroupJoinQuestion {

                /**
                 * Constructs a new GroupJoinQuestion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupJoinQuestion);

                /** GroupJoinQuestion id. */
                public id?: (google.protobuf.IInt64Value|null);

                /** GroupJoinQuestion groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** GroupJoinQuestion question. */
                public question?: (google.protobuf.IStringValue|null);

                /** GroupJoinQuestion answers. */
                public answers: string[];

                /** GroupJoinQuestion score. */
                public score?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified GroupJoinQuestion message. Does not implicitly {@link im.turms.proto.GroupJoinQuestion.verify|verify} messages.
                 * @param message GroupJoinQuestion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupJoinQuestion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupJoinQuestion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupJoinQuestion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupJoinQuestion;
            }

            /** Properties of a GroupJoinQuestionsAnswerResult. */
            interface IGroupJoinQuestionsAnswerResult {

                /** GroupJoinQuestionsAnswerResult score */
                score?: (number|null);

                /** GroupJoinQuestionsAnswerResult questionIds */
                questionIds?: (string[]|null);

                /** GroupJoinQuestionsAnswerResult joined */
                joined?: (boolean|null);
            }

            /** Represents a GroupJoinQuestionsAnswerResult. */
            class GroupJoinQuestionsAnswerResult implements IGroupJoinQuestionsAnswerResult {

                /**
                 * Constructs a new GroupJoinQuestionsAnswerResult.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupJoinQuestionsAnswerResult);

                /** GroupJoinQuestionsAnswerResult score. */
                public score: number;

                /** GroupJoinQuestionsAnswerResult questionIds. */
                public questionIds: string[];

                /** GroupJoinQuestionsAnswerResult joined. */
                public joined: boolean;

                /**
                 * Encodes the specified GroupJoinQuestionsAnswerResult message. Does not implicitly {@link im.turms.proto.GroupJoinQuestionsAnswerResult.verify|verify} messages.
                 * @param message GroupJoinQuestionsAnswerResult message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupJoinQuestionsAnswerResult, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupJoinQuestionsAnswerResult message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupJoinQuestionsAnswerResult
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupJoinQuestionsAnswerResult;
            }

            /** Properties of a GroupJoinQuestionsWithVersion. */
            interface IGroupJoinQuestionsWithVersion {

                /** GroupJoinQuestionsWithVersion groupJoinQuestions */
                groupJoinQuestions?: (im.turms.proto.IGroupJoinQuestion[]|null);

                /** GroupJoinQuestionsWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a GroupJoinQuestionsWithVersion. */
            class GroupJoinQuestionsWithVersion implements IGroupJoinQuestionsWithVersion {

                /**
                 * Constructs a new GroupJoinQuestionsWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupJoinQuestionsWithVersion);

                /** GroupJoinQuestionsWithVersion groupJoinQuestions. */
                public groupJoinQuestions: im.turms.proto.IGroupJoinQuestion[];

                /** GroupJoinQuestionsWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified GroupJoinQuestionsWithVersion message. Does not implicitly {@link im.turms.proto.GroupJoinQuestionsWithVersion.verify|verify} messages.
                 * @param message GroupJoinQuestionsWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupJoinQuestionsWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupJoinQuestionsWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupJoinQuestionsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupJoinQuestionsWithVersion;
            }

            /** Properties of a GroupJoinRequest. */
            interface IGroupJoinRequest {

                /** GroupJoinRequest id */
                id?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest creationDate */
                creationDate?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest content */
                content?: (google.protobuf.IStringValue|null);

                /** GroupJoinRequest status */
                status?: (im.turms.proto.RequestStatus|null);

                /** GroupJoinRequest expirationDate */
                expirationDate?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest requesterId */
                requesterId?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest responderId */
                responderId?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a GroupJoinRequest. */
            class GroupJoinRequest implements IGroupJoinRequest {

                /**
                 * Constructs a new GroupJoinRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupJoinRequest);

                /** GroupJoinRequest id. */
                public id?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest creationDate. */
                public creationDate?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest content. */
                public content?: (google.protobuf.IStringValue|null);

                /** GroupJoinRequest status. */
                public status: im.turms.proto.RequestStatus;

                /** GroupJoinRequest expirationDate. */
                public expirationDate?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest requesterId. */
                public requesterId?: (google.protobuf.IInt64Value|null);

                /** GroupJoinRequest responderId. */
                public responderId?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified GroupJoinRequest message. Does not implicitly {@link im.turms.proto.GroupJoinRequest.verify|verify} messages.
                 * @param message GroupJoinRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupJoinRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupJoinRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupJoinRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupJoinRequest;
            }

            /** Properties of a GroupJoinRequestsWithVersion. */
            interface IGroupJoinRequestsWithVersion {

                /** GroupJoinRequestsWithVersion groupJoinRequests */
                groupJoinRequests?: (im.turms.proto.IGroupJoinRequest[]|null);

                /** GroupJoinRequestsWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a GroupJoinRequestsWithVersion. */
            class GroupJoinRequestsWithVersion implements IGroupJoinRequestsWithVersion {

                /**
                 * Constructs a new GroupJoinRequestsWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupJoinRequestsWithVersion);

                /** GroupJoinRequestsWithVersion groupJoinRequests. */
                public groupJoinRequests: im.turms.proto.IGroupJoinRequest[];

                /** GroupJoinRequestsWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified GroupJoinRequestsWithVersion message. Does not implicitly {@link im.turms.proto.GroupJoinRequestsWithVersion.verify|verify} messages.
                 * @param message GroupJoinRequestsWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupJoinRequestsWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupJoinRequestsWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupJoinRequestsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupJoinRequestsWithVersion;
            }

            /** Properties of a GroupMember. */
            interface IGroupMember {

                /** GroupMember groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** GroupMember userId */
                userId?: (google.protobuf.IInt64Value|null);

                /** GroupMember name */
                name?: (google.protobuf.IStringValue|null);

                /** GroupMember role */
                role?: (im.turms.proto.GroupMemberRole|null);

                /** GroupMember joinDate */
                joinDate?: (google.protobuf.IInt64Value|null);

                /** GroupMember muteEndDate */
                muteEndDate?: (google.protobuf.IInt64Value|null);

                /** GroupMember userStatus */
                userStatus?: (im.turms.proto.UserStatus|null);

                /** GroupMember usingDeviceTypes */
                usingDeviceTypes?: (im.turms.proto.DeviceType[]|null);
            }

            /** Represents a GroupMember. */
            class GroupMember implements IGroupMember {

                /**
                 * Constructs a new GroupMember.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupMember);

                /** GroupMember groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** GroupMember userId. */
                public userId?: (google.protobuf.IInt64Value|null);

                /** GroupMember name. */
                public name?: (google.protobuf.IStringValue|null);

                /** GroupMember role. */
                public role: im.turms.proto.GroupMemberRole;

                /** GroupMember joinDate. */
                public joinDate?: (google.protobuf.IInt64Value|null);

                /** GroupMember muteEndDate. */
                public muteEndDate?: (google.protobuf.IInt64Value|null);

                /** GroupMember userStatus. */
                public userStatus: im.turms.proto.UserStatus;

                /** GroupMember usingDeviceTypes. */
                public usingDeviceTypes: im.turms.proto.DeviceType[];

                /**
                 * Encodes the specified GroupMember message. Does not implicitly {@link im.turms.proto.GroupMember.verify|verify} messages.
                 * @param message GroupMember message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupMember, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupMember message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupMember
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupMember;
            }

            /** Properties of a GroupMembersWithVersion. */
            interface IGroupMembersWithVersion {

                /** GroupMembersWithVersion groupMembers */
                groupMembers?: (im.turms.proto.IGroupMember[]|null);

                /** GroupMembersWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a GroupMembersWithVersion. */
            class GroupMembersWithVersion implements IGroupMembersWithVersion {

                /**
                 * Constructs a new GroupMembersWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupMembersWithVersion);

                /** GroupMembersWithVersion groupMembers. */
                public groupMembers: im.turms.proto.IGroupMember[];

                /** GroupMembersWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified GroupMembersWithVersion message. Does not implicitly {@link im.turms.proto.GroupMembersWithVersion.verify|verify} messages.
                 * @param message GroupMembersWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupMembersWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupMembersWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupMembersWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupMembersWithVersion;
            }

            /** Properties of a Group. */
            interface IGroup {

                /** Group id */
                id?: (google.protobuf.IInt64Value|null);

                /** Group typeId */
                typeId?: (google.protobuf.IInt64Value|null);

                /** Group creatorId */
                creatorId?: (google.protobuf.IInt64Value|null);

                /** Group ownerId */
                ownerId?: (google.protobuf.IInt64Value|null);

                /** Group name */
                name?: (google.protobuf.IStringValue|null);

                /** Group intro */
                intro?: (google.protobuf.IStringValue|null);

                /** Group announcement */
                announcement?: (google.protobuf.IStringValue|null);

                /** Group creationDate */
                creationDate?: (google.protobuf.IInt64Value|null);

                /** Group deletionDate */
                deletionDate?: (google.protobuf.IInt64Value|null);

                /** Group muteEndDate */
                muteEndDate?: (google.protobuf.IInt64Value|null);

                /** Group active */
                active?: (google.protobuf.IBoolValue|null);
            }

            /** Represents a Group. */
            class Group implements IGroup {

                /**
                 * Constructs a new Group.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroup);

                /** Group id. */
                public id?: (google.protobuf.IInt64Value|null);

                /** Group typeId. */
                public typeId?: (google.protobuf.IInt64Value|null);

                /** Group creatorId. */
                public creatorId?: (google.protobuf.IInt64Value|null);

                /** Group ownerId. */
                public ownerId?: (google.protobuf.IInt64Value|null);

                /** Group name. */
                public name?: (google.protobuf.IStringValue|null);

                /** Group intro. */
                public intro?: (google.protobuf.IStringValue|null);

                /** Group announcement. */
                public announcement?: (google.protobuf.IStringValue|null);

                /** Group creationDate. */
                public creationDate?: (google.protobuf.IInt64Value|null);

                /** Group deletionDate. */
                public deletionDate?: (google.protobuf.IInt64Value|null);

                /** Group muteEndDate. */
                public muteEndDate?: (google.protobuf.IInt64Value|null);

                /** Group active. */
                public active?: (google.protobuf.IBoolValue|null);

                /**
                 * Encodes the specified Group message. Does not implicitly {@link im.turms.proto.Group.verify|verify} messages.
                 * @param message Group message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroup, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a Group message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns Group
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.Group;
            }

            /** Properties of a GroupsWithVersion. */
            interface IGroupsWithVersion {

                /** GroupsWithVersion groups */
                groups?: (im.turms.proto.IGroup[]|null);

                /** GroupsWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a GroupsWithVersion. */
            class GroupsWithVersion implements IGroupsWithVersion {

                /**
                 * Constructs a new GroupsWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IGroupsWithVersion);

                /** GroupsWithVersion groups. */
                public groups: im.turms.proto.IGroup[];

                /** GroupsWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified GroupsWithVersion message. Does not implicitly {@link im.turms.proto.GroupsWithVersion.verify|verify} messages.
                 * @param message GroupsWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IGroupsWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a GroupsWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns GroupsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.GroupsWithVersion;
            }

            /** Properties of a MessageStatus. */
            interface IMessageStatus {

                /** MessageStatus messageId */
                messageId?: (google.protobuf.IInt64Value|null);

                /** MessageStatus toUserId */
                toUserId?: (google.protobuf.IInt64Value|null);

                /** MessageStatus deliveryStatus */
                deliveryStatus?: (im.turms.proto.MessageDeliveryStatus|null);

                /** MessageStatus receptionDate */
                receptionDate?: (google.protobuf.IInt64Value|null);

                /** MessageStatus readDate */
                readDate?: (google.protobuf.IInt64Value|null);

                /** MessageStatus recallDate */
                recallDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a MessageStatus. */
            class MessageStatus implements IMessageStatus {

                /**
                 * Constructs a new MessageStatus.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IMessageStatus);

                /** MessageStatus messageId. */
                public messageId?: (google.protobuf.IInt64Value|null);

                /** MessageStatus toUserId. */
                public toUserId?: (google.protobuf.IInt64Value|null);

                /** MessageStatus deliveryStatus. */
                public deliveryStatus: im.turms.proto.MessageDeliveryStatus;

                /** MessageStatus receptionDate. */
                public receptionDate?: (google.protobuf.IInt64Value|null);

                /** MessageStatus readDate. */
                public readDate?: (google.protobuf.IInt64Value|null);

                /** MessageStatus recallDate. */
                public recallDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified MessageStatus message. Does not implicitly {@link im.turms.proto.MessageStatus.verify|verify} messages.
                 * @param message MessageStatus message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IMessageStatus, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a MessageStatus message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns MessageStatus
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.MessageStatus;
            }

            /** Properties of a MessageStatuses. */
            interface IMessageStatuses {

                /** MessageStatuses messageStatuses */
                messageStatuses?: (im.turms.proto.IMessageStatus[]|null);
            }

            /** Represents a MessageStatuses. */
            class MessageStatuses implements IMessageStatuses {

                /**
                 * Constructs a new MessageStatuses.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IMessageStatuses);

                /** MessageStatuses messageStatuses. */
                public messageStatuses: im.turms.proto.IMessageStatus[];

                /**
                 * Encodes the specified MessageStatuses message. Does not implicitly {@link im.turms.proto.MessageStatuses.verify|verify} messages.
                 * @param message MessageStatuses message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IMessageStatuses, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a MessageStatuses message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns MessageStatuses
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.MessageStatuses;
            }

            /** Properties of a Message. */
            interface IMessage {

                /** Message id */
                id?: (google.protobuf.IInt64Value|null);

                /** Message deliveryDate */
                deliveryDate?: (google.protobuf.IInt64Value|null);

                /** Message deletionDate */
                deletionDate?: (google.protobuf.IInt64Value|null);

                /** Message text */
                text?: (google.protobuf.IStringValue|null);

                /** Message senderId */
                senderId?: (google.protobuf.IInt64Value|null);

                /** Message groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** Message isSystemMessage */
                isSystemMessage?: (google.protobuf.IBoolValue|null);

                /** Message recipientId */
                recipientId?: (google.protobuf.IInt64Value|null);

                /** Message records */
                records?: (Uint8Array[]|null);
            }

            /** Represents a Message. */
            class Message implements IMessage {

                /**
                 * Constructs a new Message.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IMessage);

                /** Message id. */
                public id?: (google.protobuf.IInt64Value|null);

                /** Message deliveryDate. */
                public deliveryDate?: (google.protobuf.IInt64Value|null);

                /** Message deletionDate. */
                public deletionDate?: (google.protobuf.IInt64Value|null);

                /** Message text. */
                public text?: (google.protobuf.IStringValue|null);

                /** Message senderId. */
                public senderId?: (google.protobuf.IInt64Value|null);

                /** Message groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** Message isSystemMessage. */
                public isSystemMessage?: (google.protobuf.IBoolValue|null);

                /** Message recipientId. */
                public recipientId?: (google.protobuf.IInt64Value|null);

                /** Message records. */
                public records: Uint8Array[];

                /**
                 * Encodes the specified Message message. Does not implicitly {@link im.turms.proto.Message.verify|verify} messages.
                 * @param message Message message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IMessage, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a Message message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns Message
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.Message;
            }

            /** Properties of a MessagesWithTotalList. */
            interface IMessagesWithTotalList {

                /** MessagesWithTotalList messagesWithTotalList */
                messagesWithTotalList?: (im.turms.proto.IMessagesWithTotal[]|null);
            }

            /** Represents a MessagesWithTotalList. */
            class MessagesWithTotalList implements IMessagesWithTotalList {

                /**
                 * Constructs a new MessagesWithTotalList.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IMessagesWithTotalList);

                /** MessagesWithTotalList messagesWithTotalList. */
                public messagesWithTotalList: im.turms.proto.IMessagesWithTotal[];

                /**
                 * Encodes the specified MessagesWithTotalList message. Does not implicitly {@link im.turms.proto.MessagesWithTotalList.verify|verify} messages.
                 * @param message MessagesWithTotalList message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IMessagesWithTotalList, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a MessagesWithTotalList message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns MessagesWithTotalList
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.MessagesWithTotalList;
            }

            /** Properties of a MessagesWithTotal. */
            interface IMessagesWithTotal {

                /** MessagesWithTotal total */
                total?: (number|null);

                /** MessagesWithTotal isGroupMessage */
                isGroupMessage?: (boolean|null);

                /** MessagesWithTotal fromId */
                fromId?: (string|null);

                /** MessagesWithTotal messages */
                messages?: (im.turms.proto.IMessage[]|null);
            }

            /** Represents a MessagesWithTotal. */
            class MessagesWithTotal implements IMessagesWithTotal {

                /**
                 * Constructs a new MessagesWithTotal.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IMessagesWithTotal);

                /** MessagesWithTotal total. */
                public total: number;

                /** MessagesWithTotal isGroupMessage. */
                public isGroupMessage: boolean;

                /** MessagesWithTotal fromId. */
                public fromId: string;

                /** MessagesWithTotal messages. */
                public messages: im.turms.proto.IMessage[];

                /**
                 * Encodes the specified MessagesWithTotal message. Does not implicitly {@link im.turms.proto.MessagesWithTotal.verify|verify} messages.
                 * @param message MessagesWithTotal message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IMessagesWithTotal, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a MessagesWithTotal message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns MessagesWithTotal
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.MessagesWithTotal;
            }

            /** Properties of a Messages. */
            interface IMessages {

                /** Messages messages */
                messages?: (im.turms.proto.IMessage[]|null);
            }

            /** Represents a Messages. */
            class Messages implements IMessages {

                /**
                 * Constructs a new Messages.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IMessages);

                /** Messages messages. */
                public messages: im.turms.proto.IMessage[];

                /**
                 * Encodes the specified Messages message. Does not implicitly {@link im.turms.proto.Messages.verify|verify} messages.
                 * @param message Messages message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IMessages, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a Messages message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns Messages
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.Messages;
            }

            /** Properties of an Acknowledge. */
            interface IAcknowledge {

                /** Acknowledge deliveryDate */
                deliveryDate?: (string|null);

                /** Acknowledge messageId */
                messageId?: (google.protobuf.IInt64Value|null);
            }

            /** Represents an Acknowledge. */
            class Acknowledge implements IAcknowledge {

                /**
                 * Constructs a new Acknowledge.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IAcknowledge);

                /** Acknowledge deliveryDate. */
                public deliveryDate: string;

                /** Acknowledge messageId. */
                public messageId?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified Acknowledge message. Does not implicitly {@link im.turms.proto.Acknowledge.verify|verify} messages.
                 * @param message Acknowledge message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IAcknowledge, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an Acknowledge message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns Acknowledge
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.Acknowledge;
            }

            /** Properties of a Session. */
            interface ISession {

                /** Session sessionId */
                sessionId?: (string|null);
            }

            /** Represents a Session. */
            class Session implements ISession {

                /**
                 * Constructs a new Session.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ISession);

                /** Session sessionId. */
                public sessionId: string;

                /**
                 * Encodes the specified Session message. Does not implicitly {@link im.turms.proto.Session.verify|verify} messages.
                 * @param message Session message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ISession, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a Session message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns Session
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.Session;
            }

            /** Properties of a UserFriendRequest. */
            interface IUserFriendRequest {

                /** UserFriendRequest id */
                id?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest creationDate */
                creationDate?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest content */
                content?: (google.protobuf.IStringValue|null);

                /** UserFriendRequest requestStatus */
                requestStatus?: (im.turms.proto.RequestStatus|null);

                /** UserFriendRequest reason */
                reason?: (google.protobuf.IStringValue|null);

                /** UserFriendRequest expirationDate */
                expirationDate?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest requesterId */
                requesterId?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest recipientId */
                recipientId?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a UserFriendRequest. */
            class UserFriendRequest implements IUserFriendRequest {

                /**
                 * Constructs a new UserFriendRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserFriendRequest);

                /** UserFriendRequest id. */
                public id?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest creationDate. */
                public creationDate?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest content. */
                public content?: (google.protobuf.IStringValue|null);

                /** UserFriendRequest requestStatus. */
                public requestStatus: im.turms.proto.RequestStatus;

                /** UserFriendRequest reason. */
                public reason?: (google.protobuf.IStringValue|null);

                /** UserFriendRequest expirationDate. */
                public expirationDate?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest requesterId. */
                public requesterId?: (google.protobuf.IInt64Value|null);

                /** UserFriendRequest recipientId. */
                public recipientId?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UserFriendRequest message. Does not implicitly {@link im.turms.proto.UserFriendRequest.verify|verify} messages.
                 * @param message UserFriendRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserFriendRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserFriendRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserFriendRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserFriendRequest;
            }

            /** Properties of a UserFriendRequestsWithVersion. */
            interface IUserFriendRequestsWithVersion {

                /** UserFriendRequestsWithVersion userFriendRequests */
                userFriendRequests?: (im.turms.proto.IUserFriendRequest[]|null);

                /** UserFriendRequestsWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a UserFriendRequestsWithVersion. */
            class UserFriendRequestsWithVersion implements IUserFriendRequestsWithVersion {

                /**
                 * Constructs a new UserFriendRequestsWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserFriendRequestsWithVersion);

                /** UserFriendRequestsWithVersion userFriendRequests. */
                public userFriendRequests: im.turms.proto.IUserFriendRequest[];

                /** UserFriendRequestsWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UserFriendRequestsWithVersion message. Does not implicitly {@link im.turms.proto.UserFriendRequestsWithVersion.verify|verify} messages.
                 * @param message UserFriendRequestsWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserFriendRequestsWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserFriendRequestsWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserFriendRequestsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserFriendRequestsWithVersion;
            }

            /** Properties of a UserInfo. */
            interface IUserInfo {

                /** UserInfo id */
                id?: (google.protobuf.IInt64Value|null);

                /** UserInfo name */
                name?: (google.protobuf.IStringValue|null);

                /** UserInfo intro */
                intro?: (google.protobuf.IStringValue|null);

                /** UserInfo registrationDate */
                registrationDate?: (google.protobuf.IInt64Value|null);

                /** UserInfo deletionDate */
                deletionDate?: (google.protobuf.IInt64Value|null);

                /** UserInfo active */
                active?: (google.protobuf.IBoolValue|null);

                /** UserInfo profileAccessStrategy */
                profileAccessStrategy?: (im.turms.proto.ProfileAccessStrategy|null);
            }

            /** Represents a UserInfo. */
            class UserInfo implements IUserInfo {

                /**
                 * Constructs a new UserInfo.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserInfo);

                /** UserInfo id. */
                public id?: (google.protobuf.IInt64Value|null);

                /** UserInfo name. */
                public name?: (google.protobuf.IStringValue|null);

                /** UserInfo intro. */
                public intro?: (google.protobuf.IStringValue|null);

                /** UserInfo registrationDate. */
                public registrationDate?: (google.protobuf.IInt64Value|null);

                /** UserInfo deletionDate. */
                public deletionDate?: (google.protobuf.IInt64Value|null);

                /** UserInfo active. */
                public active?: (google.protobuf.IBoolValue|null);

                /** UserInfo profileAccessStrategy. */
                public profileAccessStrategy: im.turms.proto.ProfileAccessStrategy;

                /**
                 * Encodes the specified UserInfo message. Does not implicitly {@link im.turms.proto.UserInfo.verify|verify} messages.
                 * @param message UserInfo message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserInfo, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserInfo message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserInfo
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserInfo;
            }

            /** Properties of a UserLocation. */
            interface IUserLocation {

                /** UserLocation latitude */
                latitude?: (number|null);

                /** UserLocation longitude */
                longitude?: (number|null);

                /** UserLocation name */
                name?: (google.protobuf.IStringValue|null);

                /** UserLocation address */
                address?: (google.protobuf.IStringValue|null);

                /** UserLocation timestamp */
                timestamp?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a UserLocation. */
            class UserLocation implements IUserLocation {

                /**
                 * Constructs a new UserLocation.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserLocation);

                /** UserLocation latitude. */
                public latitude: number;

                /** UserLocation longitude. */
                public longitude: number;

                /** UserLocation name. */
                public name?: (google.protobuf.IStringValue|null);

                /** UserLocation address. */
                public address?: (google.protobuf.IStringValue|null);

                /** UserLocation timestamp. */
                public timestamp?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UserLocation message. Does not implicitly {@link im.turms.proto.UserLocation.verify|verify} messages.
                 * @param message UserLocation message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserLocation, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserLocation message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserLocation
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserLocation;
            }

            /** Properties of a UserRelationshipGroup. */
            interface IUserRelationshipGroup {

                /** UserRelationshipGroup index */
                index?: (number|null);

                /** UserRelationshipGroup name */
                name?: (string|null);
            }

            /** Represents a UserRelationshipGroup. */
            class UserRelationshipGroup implements IUserRelationshipGroup {

                /**
                 * Constructs a new UserRelationshipGroup.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserRelationshipGroup);

                /** UserRelationshipGroup index. */
                public index: number;

                /** UserRelationshipGroup name. */
                public name: string;

                /**
                 * Encodes the specified UserRelationshipGroup message. Does not implicitly {@link im.turms.proto.UserRelationshipGroup.verify|verify} messages.
                 * @param message UserRelationshipGroup message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserRelationshipGroup, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserRelationshipGroup message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserRelationshipGroup
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserRelationshipGroup;
            }

            /** Properties of a UserRelationshipGroupsWithVersion. */
            interface IUserRelationshipGroupsWithVersion {

                /** UserRelationshipGroupsWithVersion userRelationshipGroups */
                userRelationshipGroups?: (im.turms.proto.IUserRelationshipGroup[]|null);

                /** UserRelationshipGroupsWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a UserRelationshipGroupsWithVersion. */
            class UserRelationshipGroupsWithVersion implements IUserRelationshipGroupsWithVersion {

                /**
                 * Constructs a new UserRelationshipGroupsWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserRelationshipGroupsWithVersion);

                /** UserRelationshipGroupsWithVersion userRelationshipGroups. */
                public userRelationshipGroups: im.turms.proto.IUserRelationshipGroup[];

                /** UserRelationshipGroupsWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UserRelationshipGroupsWithVersion message. Does not implicitly {@link im.turms.proto.UserRelationshipGroupsWithVersion.verify|verify} messages.
                 * @param message UserRelationshipGroupsWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserRelationshipGroupsWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserRelationshipGroupsWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserRelationshipGroupsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserRelationshipGroupsWithVersion;
            }

            /** Properties of a UserRelationship. */
            interface IUserRelationship {

                /** UserRelationship ownerId */
                ownerId?: (google.protobuf.IInt64Value|null);

                /** UserRelationship relatedUserId */
                relatedUserId?: (google.protobuf.IInt64Value|null);

                /** UserRelationship blocked */
                blocked?: (google.protobuf.IBoolValue|null);

                /** UserRelationship groupIndex */
                groupIndex?: (google.protobuf.IInt64Value|null);

                /** UserRelationship establishmentDate */
                establishmentDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a UserRelationship. */
            class UserRelationship implements IUserRelationship {

                /**
                 * Constructs a new UserRelationship.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserRelationship);

                /** UserRelationship ownerId. */
                public ownerId?: (google.protobuf.IInt64Value|null);

                /** UserRelationship relatedUserId. */
                public relatedUserId?: (google.protobuf.IInt64Value|null);

                /** UserRelationship blocked. */
                public blocked?: (google.protobuf.IBoolValue|null);

                /** UserRelationship groupIndex. */
                public groupIndex?: (google.protobuf.IInt64Value|null);

                /** UserRelationship establishmentDate. */
                public establishmentDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UserRelationship message. Does not implicitly {@link im.turms.proto.UserRelationship.verify|verify} messages.
                 * @param message UserRelationship message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserRelationship, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserRelationship message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserRelationship
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserRelationship;
            }

            /** Properties of a UserRelationshipsWithVersion. */
            interface IUserRelationshipsWithVersion {

                /** UserRelationshipsWithVersion userRelationships */
                userRelationships?: (im.turms.proto.IUserRelationship[]|null);

                /** UserRelationshipsWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a UserRelationshipsWithVersion. */
            class UserRelationshipsWithVersion implements IUserRelationshipsWithVersion {

                /**
                 * Constructs a new UserRelationshipsWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserRelationshipsWithVersion);

                /** UserRelationshipsWithVersion userRelationships. */
                public userRelationships: im.turms.proto.IUserRelationship[];

                /** UserRelationshipsWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UserRelationshipsWithVersion message. Does not implicitly {@link im.turms.proto.UserRelationshipsWithVersion.verify|verify} messages.
                 * @param message UserRelationshipsWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserRelationshipsWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserRelationshipsWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserRelationshipsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserRelationshipsWithVersion;
            }

            /** Properties of a UserSessionId. */
            interface IUserSessionId {

                /** UserSessionId userId */
                userId?: (string|null);

                /** UserSessionId deviceType */
                deviceType?: (im.turms.proto.DeviceType|null);
            }

            /** Represents a UserSessionId. */
            class UserSessionId implements IUserSessionId {

                /**
                 * Constructs a new UserSessionId.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserSessionId);

                /** UserSessionId userId. */
                public userId: string;

                /** UserSessionId deviceType. */
                public deviceType: im.turms.proto.DeviceType;

                /**
                 * Encodes the specified UserSessionId message. Does not implicitly {@link im.turms.proto.UserSessionId.verify|verify} messages.
                 * @param message UserSessionId message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserSessionId, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserSessionId message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserSessionId
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserSessionId;
            }

            /** Properties of a UserSessionIds. */
            interface IUserSessionIds {

                /** UserSessionIds userSessionIds */
                userSessionIds?: (im.turms.proto.IUserSessionId[]|null);
            }

            /** Represents a UserSessionIds. */
            class UserSessionIds implements IUserSessionIds {

                /**
                 * Constructs a new UserSessionIds.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserSessionIds);

                /** UserSessionIds userSessionIds. */
                public userSessionIds: im.turms.proto.IUserSessionId[];

                /**
                 * Encodes the specified UserSessionIds message. Does not implicitly {@link im.turms.proto.UserSessionIds.verify|verify} messages.
                 * @param message UserSessionIds message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserSessionIds, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserSessionIds message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserSessionIds
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserSessionIds;
            }

            /** Properties of a UserStatusDetail. */
            interface IUserStatusDetail {

                /** UserStatusDetail userId */
                userId?: (string|null);

                /** UserStatusDetail userStatus */
                userStatus?: (im.turms.proto.UserStatus|null);

                /** UserStatusDetail usingDeviceTypes */
                usingDeviceTypes?: (im.turms.proto.DeviceType[]|null);
            }

            /** Represents a UserStatusDetail. */
            class UserStatusDetail implements IUserStatusDetail {

                /**
                 * Constructs a new UserStatusDetail.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUserStatusDetail);

                /** UserStatusDetail userId. */
                public userId: string;

                /** UserStatusDetail userStatus. */
                public userStatus: im.turms.proto.UserStatus;

                /** UserStatusDetail usingDeviceTypes. */
                public usingDeviceTypes: im.turms.proto.DeviceType[];

                /**
                 * Encodes the specified UserStatusDetail message. Does not implicitly {@link im.turms.proto.UserStatusDetail.verify|verify} messages.
                 * @param message UserStatusDetail message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUserStatusDetail, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UserStatusDetail message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UserStatusDetail
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UserStatusDetail;
            }

            /** Properties of a UsersInfosWithVersion. */
            interface IUsersInfosWithVersion {

                /** UsersInfosWithVersion userInfos */
                userInfos?: (im.turms.proto.IUserInfo[]|null);

                /** UsersInfosWithVersion lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a UsersInfosWithVersion. */
            class UsersInfosWithVersion implements IUsersInfosWithVersion {

                /**
                 * Constructs a new UsersInfosWithVersion.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUsersInfosWithVersion);

                /** UsersInfosWithVersion userInfos. */
                public userInfos: im.turms.proto.IUserInfo[];

                /** UsersInfosWithVersion lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UsersInfosWithVersion message. Does not implicitly {@link im.turms.proto.UsersInfosWithVersion.verify|verify} messages.
                 * @param message UsersInfosWithVersion message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUsersInfosWithVersion, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UsersInfosWithVersion message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UsersInfosWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UsersInfosWithVersion;
            }

            /** Properties of a UsersOnlineStatuses. */
            interface IUsersOnlineStatuses {

                /** UsersOnlineStatuses userStatuses */
                userStatuses?: (im.turms.proto.IUserStatusDetail[]|null);
            }

            /** Represents a UsersOnlineStatuses. */
            class UsersOnlineStatuses implements IUsersOnlineStatuses {

                /**
                 * Constructs a new UsersOnlineStatuses.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUsersOnlineStatuses);

                /** UsersOnlineStatuses userStatuses. */
                public userStatuses: im.turms.proto.IUserStatusDetail[];

                /**
                 * Encodes the specified UsersOnlineStatuses message. Does not implicitly {@link im.turms.proto.UsersOnlineStatuses.verify|verify} messages.
                 * @param message UsersOnlineStatuses message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUsersOnlineStatuses, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a UsersOnlineStatuses message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UsersOnlineStatuses
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UsersOnlineStatuses;
            }

            /** Properties of a TurmsNotification. */
            interface ITurmsNotification {

                /** TurmsNotification requestId */
                requestId?: (google.protobuf.IInt64Value|null);

                /** TurmsNotification code */
                code?: (google.protobuf.IInt32Value|null);

                /** TurmsNotification reason */
                reason?: (google.protobuf.IStringValue|null);

                /** TurmsNotification data */
                data?: (im.turms.proto.TurmsNotification.IData|null);

                /** TurmsNotification relayedRequest */
                relayedRequest?: (im.turms.proto.ITurmsRequest|null);

                /** TurmsNotification requesterId */
                requesterId?: (google.protobuf.IInt64Value|null);

                /** TurmsNotification closeStatus */
                closeStatus?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a TurmsNotification. */
            class TurmsNotification implements ITurmsNotification {

                /**
                 * Constructs a new TurmsNotification.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ITurmsNotification);

                /** TurmsNotification requestId. */
                public requestId?: (google.protobuf.IInt64Value|null);

                /** TurmsNotification code. */
                public code?: (google.protobuf.IInt32Value|null);

                /** TurmsNotification reason. */
                public reason?: (google.protobuf.IStringValue|null);

                /** TurmsNotification data. */
                public data?: (im.turms.proto.TurmsNotification.IData|null);

                /** TurmsNotification relayedRequest. */
                public relayedRequest?: (im.turms.proto.ITurmsRequest|null);

                /** TurmsNotification requesterId. */
                public requesterId?: (google.protobuf.IInt64Value|null);

                /** TurmsNotification closeStatus. */
                public closeStatus?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified TurmsNotification message. Does not implicitly {@link im.turms.proto.TurmsNotification.verify|verify} messages.
                 * @param message TurmsNotification message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ITurmsNotification, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a TurmsNotification message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns TurmsNotification
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.TurmsNotification;
            }

            namespace TurmsNotification {

                /** Properties of a Data. */
                interface IData {

                    /** Data ids */
                    ids?: (im.turms.proto.IInt64Values|null);

                    /** Data idsWithVersion */
                    idsWithVersion?: (im.turms.proto.IInt64ValuesWithVersion|null);

                    /** Data url */
                    url?: (google.protobuf.IStringValue|null);

                    /** Data acknowledge */
                    acknowledge?: (im.turms.proto.IAcknowledge|null);

                    /** Data session */
                    session?: (im.turms.proto.ISession|null);

                    /** Data messages */
                    messages?: (im.turms.proto.IMessages|null);

                    /** Data messageStatuses */
                    messageStatuses?: (im.turms.proto.IMessageStatuses|null);

                    /** Data messagesWithTotalList */
                    messagesWithTotalList?: (im.turms.proto.IMessagesWithTotalList|null);

                    /** Data usersInfosWithVersion */
                    usersInfosWithVersion?: (im.turms.proto.IUsersInfosWithVersion|null);

                    /** Data usersOnlineStatuses */
                    usersOnlineStatuses?: (im.turms.proto.IUsersOnlineStatuses|null);

                    /** Data userFriendRequestsWithVersion */
                    userFriendRequestsWithVersion?: (im.turms.proto.IUserFriendRequestsWithVersion|null);

                    /** Data userRelationshipGroupsWithVersion */
                    userRelationshipGroupsWithVersion?: (im.turms.proto.IUserRelationshipGroupsWithVersion|null);

                    /** Data userRelationshipsWithVersion */
                    userRelationshipsWithVersion?: (im.turms.proto.IUserRelationshipsWithVersion|null);

                    /** Data userSessionIds */
                    userSessionIds?: (im.turms.proto.IUserSessionIds|null);

                    /** Data groupInvitationsWithVersion */
                    groupInvitationsWithVersion?: (im.turms.proto.IGroupInvitationsWithVersion|null);

                    /** Data groupJoinQuestionAnswerResult */
                    groupJoinQuestionAnswerResult?: (im.turms.proto.IGroupJoinQuestionsAnswerResult|null);

                    /** Data groupJoinRequestsWithVersion */
                    groupJoinRequestsWithVersion?: (im.turms.proto.IGroupJoinRequestsWithVersion|null);

                    /** Data groupJoinQuestionsWithVersion */
                    groupJoinQuestionsWithVersion?: (im.turms.proto.IGroupJoinQuestionsWithVersion|null);

                    /** Data groupMembersWithVersion */
                    groupMembersWithVersion?: (im.turms.proto.IGroupMembersWithVersion|null);

                    /** Data groupsWithVersion */
                    groupsWithVersion?: (im.turms.proto.IGroupsWithVersion|null);
                }

                /** Represents a Data. */
                class Data implements IData {

                    /**
                     * Constructs a new Data.
                     * @param [properties] Properties to set
                     */
                    constructor(properties?: im.turms.proto.TurmsNotification.IData);

                    /** Data ids. */
                    public ids?: (im.turms.proto.IInt64Values|null);

                    /** Data idsWithVersion. */
                    public idsWithVersion?: (im.turms.proto.IInt64ValuesWithVersion|null);

                    /** Data url. */
                    public url?: (google.protobuf.IStringValue|null);

                    /** Data acknowledge. */
                    public acknowledge?: (im.turms.proto.IAcknowledge|null);

                    /** Data session. */
                    public session?: (im.turms.proto.ISession|null);

                    /** Data messages. */
                    public messages?: (im.turms.proto.IMessages|null);

                    /** Data messageStatuses. */
                    public messageStatuses?: (im.turms.proto.IMessageStatuses|null);

                    /** Data messagesWithTotalList. */
                    public messagesWithTotalList?: (im.turms.proto.IMessagesWithTotalList|null);

                    /** Data usersInfosWithVersion. */
                    public usersInfosWithVersion?: (im.turms.proto.IUsersInfosWithVersion|null);

                    /** Data usersOnlineStatuses. */
                    public usersOnlineStatuses?: (im.turms.proto.IUsersOnlineStatuses|null);

                    /** Data userFriendRequestsWithVersion. */
                    public userFriendRequestsWithVersion?: (im.turms.proto.IUserFriendRequestsWithVersion|null);

                    /** Data userRelationshipGroupsWithVersion. */
                    public userRelationshipGroupsWithVersion?: (im.turms.proto.IUserRelationshipGroupsWithVersion|null);

                    /** Data userRelationshipsWithVersion. */
                    public userRelationshipsWithVersion?: (im.turms.proto.IUserRelationshipsWithVersion|null);

                    /** Data userSessionIds. */
                    public userSessionIds?: (im.turms.proto.IUserSessionIds|null);

                    /** Data groupInvitationsWithVersion. */
                    public groupInvitationsWithVersion?: (im.turms.proto.IGroupInvitationsWithVersion|null);

                    /** Data groupJoinQuestionAnswerResult. */
                    public groupJoinQuestionAnswerResult?: (im.turms.proto.IGroupJoinQuestionsAnswerResult|null);

                    /** Data groupJoinRequestsWithVersion. */
                    public groupJoinRequestsWithVersion?: (im.turms.proto.IGroupJoinRequestsWithVersion|null);

                    /** Data groupJoinQuestionsWithVersion. */
                    public groupJoinQuestionsWithVersion?: (im.turms.proto.IGroupJoinQuestionsWithVersion|null);

                    /** Data groupMembersWithVersion. */
                    public groupMembersWithVersion?: (im.turms.proto.IGroupMembersWithVersion|null);

                    /** Data groupsWithVersion. */
                    public groupsWithVersion?: (im.turms.proto.IGroupsWithVersion|null);

                    /** Data kind. */
                    public kind?: ("ids"|"idsWithVersion"|"url"|"acknowledge"|"session"|"messages"|"messageStatuses"|"messagesWithTotalList"|"usersInfosWithVersion"|"usersOnlineStatuses"|"userFriendRequestsWithVersion"|"userRelationshipGroupsWithVersion"|"userRelationshipsWithVersion"|"userSessionIds"|"groupInvitationsWithVersion"|"groupJoinQuestionAnswerResult"|"groupJoinRequestsWithVersion"|"groupJoinQuestionsWithVersion"|"groupMembersWithVersion"|"groupsWithVersion");

                    /**
                     * Encodes the specified Data message. Does not implicitly {@link im.turms.proto.TurmsNotification.Data.verify|verify} messages.
                     * @param message Data message or plain object to encode
                     * @param [writer] Writer to encode to
                     * @returns Writer
                     */
                    public static encode(message: im.turms.proto.TurmsNotification.IData, writer?: $protobuf.Writer): $protobuf.Writer;

                    /**
                     * Decodes a Data message from the specified reader or buffer.
                     * @param reader Reader or buffer to decode from
                     * @param [length] Message length if known beforehand
                     * @returns Data
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.TurmsNotification.Data;
                }
            }

            /** Properties of a CreateGroupBlacklistedUserRequest. */
            interface ICreateGroupBlacklistedUserRequest {

                /** CreateGroupBlacklistedUserRequest groupId */
                groupId?: (string|null);

                /** CreateGroupBlacklistedUserRequest userId */
                userId?: (string|null);
            }

            /** Represents a CreateGroupBlacklistedUserRequest. */
            class CreateGroupBlacklistedUserRequest implements ICreateGroupBlacklistedUserRequest {

                /**
                 * Constructs a new CreateGroupBlacklistedUserRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateGroupBlacklistedUserRequest);

                /** CreateGroupBlacklistedUserRequest groupId. */
                public groupId: string;

                /** CreateGroupBlacklistedUserRequest userId. */
                public userId: string;

                /**
                 * Encodes the specified CreateGroupBlacklistedUserRequest message. Does not implicitly {@link im.turms.proto.CreateGroupBlacklistedUserRequest.verify|verify} messages.
                 * @param message CreateGroupBlacklistedUserRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateGroupBlacklistedUserRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateGroupBlacklistedUserRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateGroupBlacklistedUserRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateGroupBlacklistedUserRequest;
            }

            /** Properties of a DeleteGroupBlacklistedUserRequest. */
            interface IDeleteGroupBlacklistedUserRequest {

                /** DeleteGroupBlacklistedUserRequest groupId */
                groupId?: (string|null);

                /** DeleteGroupBlacklistedUserRequest userId */
                userId?: (string|null);
            }

            /** Represents a DeleteGroupBlacklistedUserRequest. */
            class DeleteGroupBlacklistedUserRequest implements IDeleteGroupBlacklistedUserRequest {

                /**
                 * Constructs a new DeleteGroupBlacklistedUserRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteGroupBlacklistedUserRequest);

                /** DeleteGroupBlacklistedUserRequest groupId. */
                public groupId: string;

                /** DeleteGroupBlacklistedUserRequest userId. */
                public userId: string;

                /**
                 * Encodes the specified DeleteGroupBlacklistedUserRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupBlacklistedUserRequest.verify|verify} messages.
                 * @param message DeleteGroupBlacklistedUserRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteGroupBlacklistedUserRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteGroupBlacklistedUserRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteGroupBlacklistedUserRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteGroupBlacklistedUserRequest;
            }

            /** Properties of a QueryGroupBlacklistedUserIdsRequest. */
            interface IQueryGroupBlacklistedUserIdsRequest {

                /** QueryGroupBlacklistedUserIdsRequest groupId */
                groupId?: (string|null);

                /** QueryGroupBlacklistedUserIdsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryGroupBlacklistedUserIdsRequest. */
            class QueryGroupBlacklistedUserIdsRequest implements IQueryGroupBlacklistedUserIdsRequest {

                /**
                 * Constructs a new QueryGroupBlacklistedUserIdsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryGroupBlacklistedUserIdsRequest);

                /** QueryGroupBlacklistedUserIdsRequest groupId. */
                public groupId: string;

                /** QueryGroupBlacklistedUserIdsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryGroupBlacklistedUserIdsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupBlacklistedUserIdsRequest.verify|verify} messages.
                 * @param message QueryGroupBlacklistedUserIdsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryGroupBlacklistedUserIdsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryGroupBlacklistedUserIdsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryGroupBlacklistedUserIdsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryGroupBlacklistedUserIdsRequest;
            }

            /** Properties of a QueryGroupBlacklistedUserInfosRequest. */
            interface IQueryGroupBlacklistedUserInfosRequest {

                /** QueryGroupBlacklistedUserInfosRequest groupId */
                groupId?: (string|null);

                /** QueryGroupBlacklistedUserInfosRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryGroupBlacklistedUserInfosRequest. */
            class QueryGroupBlacklistedUserInfosRequest implements IQueryGroupBlacklistedUserInfosRequest {

                /**
                 * Constructs a new QueryGroupBlacklistedUserInfosRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryGroupBlacklistedUserInfosRequest);

                /** QueryGroupBlacklistedUserInfosRequest groupId. */
                public groupId: string;

                /** QueryGroupBlacklistedUserInfosRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryGroupBlacklistedUserInfosRequest message. Does not implicitly {@link im.turms.proto.QueryGroupBlacklistedUserInfosRequest.verify|verify} messages.
                 * @param message QueryGroupBlacklistedUserInfosRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryGroupBlacklistedUserInfosRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryGroupBlacklistedUserInfosRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryGroupBlacklistedUserInfosRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryGroupBlacklistedUserInfosRequest;
            }

            /** Properties of a CreateGroupRequest. */
            interface ICreateGroupRequest {

                /** CreateGroupRequest name */
                name?: (string|null);

                /** CreateGroupRequest intro */
                intro?: (google.protobuf.IStringValue|null);

                /** CreateGroupRequest announcement */
                announcement?: (google.protobuf.IStringValue|null);

                /** CreateGroupRequest minimumScore */
                minimumScore?: (google.protobuf.IInt32Value|null);

                /** CreateGroupRequest groupTypeId */
                groupTypeId?: (google.protobuf.IInt64Value|null);

                /** CreateGroupRequest muteEndDate */
                muteEndDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a CreateGroupRequest. */
            class CreateGroupRequest implements ICreateGroupRequest {

                /**
                 * Constructs a new CreateGroupRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateGroupRequest);

                /** CreateGroupRequest name. */
                public name: string;

                /** CreateGroupRequest intro. */
                public intro?: (google.protobuf.IStringValue|null);

                /** CreateGroupRequest announcement. */
                public announcement?: (google.protobuf.IStringValue|null);

                /** CreateGroupRequest minimumScore. */
                public minimumScore?: (google.protobuf.IInt32Value|null);

                /** CreateGroupRequest groupTypeId. */
                public groupTypeId?: (google.protobuf.IInt64Value|null);

                /** CreateGroupRequest muteEndDate. */
                public muteEndDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified CreateGroupRequest message. Does not implicitly {@link im.turms.proto.CreateGroupRequest.verify|verify} messages.
                 * @param message CreateGroupRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateGroupRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateGroupRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateGroupRequest;
            }

            /** Properties of a DeleteGroupRequest. */
            interface IDeleteGroupRequest {

                /** DeleteGroupRequest groupId */
                groupId?: (string|null);
            }

            /** Represents a DeleteGroupRequest. */
            class DeleteGroupRequest implements IDeleteGroupRequest {

                /**
                 * Constructs a new DeleteGroupRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteGroupRequest);

                /** DeleteGroupRequest groupId. */
                public groupId: string;

                /**
                 * Encodes the specified DeleteGroupRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupRequest.verify|verify} messages.
                 * @param message DeleteGroupRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteGroupRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteGroupRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteGroupRequest;
            }

            /** Properties of a CheckGroupJoinQuestionsAnswersRequest. */
            interface ICheckGroupJoinQuestionsAnswersRequest {

                /** CheckGroupJoinQuestionsAnswersRequest questionIdAndAnswer */
                questionIdAndAnswer?: ({ [k: string]: string }|null);
            }

            /** Represents a CheckGroupJoinQuestionsAnswersRequest. */
            class CheckGroupJoinQuestionsAnswersRequest implements ICheckGroupJoinQuestionsAnswersRequest {

                /**
                 * Constructs a new CheckGroupJoinQuestionsAnswersRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest);

                /** CheckGroupJoinQuestionsAnswersRequest questionIdAndAnswer. */
                public questionIdAndAnswer: { [k: string]: string };

                /**
                 * Encodes the specified CheckGroupJoinQuestionsAnswersRequest message. Does not implicitly {@link im.turms.proto.CheckGroupJoinQuestionsAnswersRequest.verify|verify} messages.
                 * @param message CheckGroupJoinQuestionsAnswersRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CheckGroupJoinQuestionsAnswersRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CheckGroupJoinQuestionsAnswersRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CheckGroupJoinQuestionsAnswersRequest;
            }

            /** Properties of a CreateGroupInvitationRequest. */
            interface ICreateGroupInvitationRequest {

                /** CreateGroupInvitationRequest groupId */
                groupId?: (string|null);

                /** CreateGroupInvitationRequest inviteeId */
                inviteeId?: (string|null);

                /** CreateGroupInvitationRequest content */
                content?: (string|null);
            }

            /** Represents a CreateGroupInvitationRequest. */
            class CreateGroupInvitationRequest implements ICreateGroupInvitationRequest {

                /**
                 * Constructs a new CreateGroupInvitationRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateGroupInvitationRequest);

                /** CreateGroupInvitationRequest groupId. */
                public groupId: string;

                /** CreateGroupInvitationRequest inviteeId. */
                public inviteeId: string;

                /** CreateGroupInvitationRequest content. */
                public content: string;

                /**
                 * Encodes the specified CreateGroupInvitationRequest message. Does not implicitly {@link im.turms.proto.CreateGroupInvitationRequest.verify|verify} messages.
                 * @param message CreateGroupInvitationRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateGroupInvitationRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateGroupInvitationRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateGroupInvitationRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateGroupInvitationRequest;
            }

            /** Properties of a CreateGroupJoinQuestionRequest. */
            interface ICreateGroupJoinQuestionRequest {

                /** CreateGroupJoinQuestionRequest groupId */
                groupId?: (string|null);

                /** CreateGroupJoinQuestionRequest question */
                question?: (string|null);

                /** CreateGroupJoinQuestionRequest answers */
                answers?: (string[]|null);

                /** CreateGroupJoinQuestionRequest score */
                score?: (number|null);
            }

            /** Represents a CreateGroupJoinQuestionRequest. */
            class CreateGroupJoinQuestionRequest implements ICreateGroupJoinQuestionRequest {

                /**
                 * Constructs a new CreateGroupJoinQuestionRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateGroupJoinQuestionRequest);

                /** CreateGroupJoinQuestionRequest groupId. */
                public groupId: string;

                /** CreateGroupJoinQuestionRequest question. */
                public question: string;

                /** CreateGroupJoinQuestionRequest answers. */
                public answers: string[];

                /** CreateGroupJoinQuestionRequest score. */
                public score: number;

                /**
                 * Encodes the specified CreateGroupJoinQuestionRequest message. Does not implicitly {@link im.turms.proto.CreateGroupJoinQuestionRequest.verify|verify} messages.
                 * @param message CreateGroupJoinQuestionRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateGroupJoinQuestionRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateGroupJoinQuestionRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateGroupJoinQuestionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateGroupJoinQuestionRequest;
            }

            /** Properties of a CreateGroupJoinRequestRequest. */
            interface ICreateGroupJoinRequestRequest {

                /** CreateGroupJoinRequestRequest groupId */
                groupId?: (string|null);

                /** CreateGroupJoinRequestRequest content */
                content?: (string|null);
            }

            /** Represents a CreateGroupJoinRequestRequest. */
            class CreateGroupJoinRequestRequest implements ICreateGroupJoinRequestRequest {

                /**
                 * Constructs a new CreateGroupJoinRequestRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateGroupJoinRequestRequest);

                /** CreateGroupJoinRequestRequest groupId. */
                public groupId: string;

                /** CreateGroupJoinRequestRequest content. */
                public content: string;

                /**
                 * Encodes the specified CreateGroupJoinRequestRequest message. Does not implicitly {@link im.turms.proto.CreateGroupJoinRequestRequest.verify|verify} messages.
                 * @param message CreateGroupJoinRequestRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateGroupJoinRequestRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateGroupJoinRequestRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateGroupJoinRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateGroupJoinRequestRequest;
            }

            /** Properties of a DeleteGroupInvitationRequest. */
            interface IDeleteGroupInvitationRequest {

                /** DeleteGroupInvitationRequest invitationId */
                invitationId?: (string|null);
            }

            /** Represents a DeleteGroupInvitationRequest. */
            class DeleteGroupInvitationRequest implements IDeleteGroupInvitationRequest {

                /**
                 * Constructs a new DeleteGroupInvitationRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteGroupInvitationRequest);

                /** DeleteGroupInvitationRequest invitationId. */
                public invitationId: string;

                /**
                 * Encodes the specified DeleteGroupInvitationRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupInvitationRequest.verify|verify} messages.
                 * @param message DeleteGroupInvitationRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteGroupInvitationRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteGroupInvitationRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteGroupInvitationRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteGroupInvitationRequest;
            }

            /** Properties of a DeleteGroupJoinQuestionRequest. */
            interface IDeleteGroupJoinQuestionRequest {

                /** DeleteGroupJoinQuestionRequest questionId */
                questionId?: (string|null);
            }

            /** Represents a DeleteGroupJoinQuestionRequest. */
            class DeleteGroupJoinQuestionRequest implements IDeleteGroupJoinQuestionRequest {

                /**
                 * Constructs a new DeleteGroupJoinQuestionRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteGroupJoinQuestionRequest);

                /** DeleteGroupJoinQuestionRequest questionId. */
                public questionId: string;

                /**
                 * Encodes the specified DeleteGroupJoinQuestionRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupJoinQuestionRequest.verify|verify} messages.
                 * @param message DeleteGroupJoinQuestionRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteGroupJoinQuestionRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteGroupJoinQuestionRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteGroupJoinQuestionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteGroupJoinQuestionRequest;
            }

            /** Properties of a DeleteGroupJoinRequestRequest. */
            interface IDeleteGroupJoinRequestRequest {

                /** DeleteGroupJoinRequestRequest requestId */
                requestId?: (string|null);
            }

            /** Represents a DeleteGroupJoinRequestRequest. */
            class DeleteGroupJoinRequestRequest implements IDeleteGroupJoinRequestRequest {

                /**
                 * Constructs a new DeleteGroupJoinRequestRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteGroupJoinRequestRequest);

                /** DeleteGroupJoinRequestRequest requestId. */
                public requestId: string;

                /**
                 * Encodes the specified DeleteGroupJoinRequestRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupJoinRequestRequest.verify|verify} messages.
                 * @param message DeleteGroupJoinRequestRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteGroupJoinRequestRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteGroupJoinRequestRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteGroupJoinRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteGroupJoinRequestRequest;
            }

            /** Properties of a QueryGroupInvitationsRequest. */
            interface IQueryGroupInvitationsRequest {

                /** QueryGroupInvitationsRequest groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** QueryGroupInvitationsRequest areSentByMe */
                areSentByMe?: (google.protobuf.IBoolValue|null);

                /** QueryGroupInvitationsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryGroupInvitationsRequest. */
            class QueryGroupInvitationsRequest implements IQueryGroupInvitationsRequest {

                /**
                 * Constructs a new QueryGroupInvitationsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryGroupInvitationsRequest);

                /** QueryGroupInvitationsRequest groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** QueryGroupInvitationsRequest areSentByMe. */
                public areSentByMe?: (google.protobuf.IBoolValue|null);

                /** QueryGroupInvitationsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryGroupInvitationsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupInvitationsRequest.verify|verify} messages.
                 * @param message QueryGroupInvitationsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryGroupInvitationsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryGroupInvitationsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryGroupInvitationsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryGroupInvitationsRequest;
            }

            /** Properties of a QueryGroupJoinQuestionsRequest. */
            interface IQueryGroupJoinQuestionsRequest {

                /** QueryGroupJoinQuestionsRequest groupId */
                groupId?: (string|null);

                /** QueryGroupJoinQuestionsRequest withAnswers */
                withAnswers?: (boolean|null);

                /** QueryGroupJoinQuestionsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryGroupJoinQuestionsRequest. */
            class QueryGroupJoinQuestionsRequest implements IQueryGroupJoinQuestionsRequest {

                /**
                 * Constructs a new QueryGroupJoinQuestionsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryGroupJoinQuestionsRequest);

                /** QueryGroupJoinQuestionsRequest groupId. */
                public groupId: string;

                /** QueryGroupJoinQuestionsRequest withAnswers. */
                public withAnswers: boolean;

                /** QueryGroupJoinQuestionsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryGroupJoinQuestionsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupJoinQuestionsRequest.verify|verify} messages.
                 * @param message QueryGroupJoinQuestionsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryGroupJoinQuestionsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryGroupJoinQuestionsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryGroupJoinQuestionsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryGroupJoinQuestionsRequest;
            }

            /** Properties of a QueryGroupJoinRequestsRequest. */
            interface IQueryGroupJoinRequestsRequest {

                /** QueryGroupJoinRequestsRequest groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** QueryGroupJoinRequestsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryGroupJoinRequestsRequest. */
            class QueryGroupJoinRequestsRequest implements IQueryGroupJoinRequestsRequest {

                /**
                 * Constructs a new QueryGroupJoinRequestsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryGroupJoinRequestsRequest);

                /** QueryGroupJoinRequestsRequest groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** QueryGroupJoinRequestsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryGroupJoinRequestsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupJoinRequestsRequest.verify|verify} messages.
                 * @param message QueryGroupJoinRequestsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryGroupJoinRequestsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryGroupJoinRequestsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryGroupJoinRequestsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryGroupJoinRequestsRequest;
            }

            /** Properties of an UpdateGroupJoinQuestionRequest. */
            interface IUpdateGroupJoinQuestionRequest {

                /** UpdateGroupJoinQuestionRequest questionId */
                questionId?: (string|null);

                /** UpdateGroupJoinQuestionRequest question */
                question?: (google.protobuf.IStringValue|null);

                /** UpdateGroupJoinQuestionRequest answers */
                answers?: (string[]|null);

                /** UpdateGroupJoinQuestionRequest score */
                score?: (google.protobuf.IInt32Value|null);
            }

            /** Represents an UpdateGroupJoinQuestionRequest. */
            class UpdateGroupJoinQuestionRequest implements IUpdateGroupJoinQuestionRequest {

                /**
                 * Constructs a new UpdateGroupJoinQuestionRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateGroupJoinQuestionRequest);

                /** UpdateGroupJoinQuestionRequest questionId. */
                public questionId: string;

                /** UpdateGroupJoinQuestionRequest question. */
                public question?: (google.protobuf.IStringValue|null);

                /** UpdateGroupJoinQuestionRequest answers. */
                public answers: string[];

                /** UpdateGroupJoinQuestionRequest score. */
                public score?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified UpdateGroupJoinQuestionRequest message. Does not implicitly {@link im.turms.proto.UpdateGroupJoinQuestionRequest.verify|verify} messages.
                 * @param message UpdateGroupJoinQuestionRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateGroupJoinQuestionRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateGroupJoinQuestionRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateGroupJoinQuestionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateGroupJoinQuestionRequest;
            }

            /** Properties of a CreateGroupMemberRequest. */
            interface ICreateGroupMemberRequest {

                /** CreateGroupMemberRequest groupId */
                groupId?: (string|null);

                /** CreateGroupMemberRequest userId */
                userId?: (string|null);

                /** CreateGroupMemberRequest name */
                name?: (google.protobuf.IStringValue|null);

                /** CreateGroupMemberRequest role */
                role?: (im.turms.proto.GroupMemberRole|null);

                /** CreateGroupMemberRequest muteEndDate */
                muteEndDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a CreateGroupMemberRequest. */
            class CreateGroupMemberRequest implements ICreateGroupMemberRequest {

                /**
                 * Constructs a new CreateGroupMemberRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateGroupMemberRequest);

                /** CreateGroupMemberRequest groupId. */
                public groupId: string;

                /** CreateGroupMemberRequest userId. */
                public userId: string;

                /** CreateGroupMemberRequest name. */
                public name?: (google.protobuf.IStringValue|null);

                /** CreateGroupMemberRequest role. */
                public role: im.turms.proto.GroupMemberRole;

                /** CreateGroupMemberRequest muteEndDate. */
                public muteEndDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified CreateGroupMemberRequest message. Does not implicitly {@link im.turms.proto.CreateGroupMemberRequest.verify|verify} messages.
                 * @param message CreateGroupMemberRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateGroupMemberRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateGroupMemberRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateGroupMemberRequest;
            }

            /** Properties of a DeleteGroupMemberRequest. */
            interface IDeleteGroupMemberRequest {

                /** DeleteGroupMemberRequest groupId */
                groupId?: (string|null);

                /** DeleteGroupMemberRequest memberId */
                memberId?: (string|null);

                /** DeleteGroupMemberRequest successorId */
                successorId?: (google.protobuf.IInt64Value|null);

                /** DeleteGroupMemberRequest quitAfterTransfer */
                quitAfterTransfer?: (google.protobuf.IBoolValue|null);
            }

            /** Represents a DeleteGroupMemberRequest. */
            class DeleteGroupMemberRequest implements IDeleteGroupMemberRequest {

                /**
                 * Constructs a new DeleteGroupMemberRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteGroupMemberRequest);

                /** DeleteGroupMemberRequest groupId. */
                public groupId: string;

                /** DeleteGroupMemberRequest memberId. */
                public memberId: string;

                /** DeleteGroupMemberRequest successorId. */
                public successorId?: (google.protobuf.IInt64Value|null);

                /** DeleteGroupMemberRequest quitAfterTransfer. */
                public quitAfterTransfer?: (google.protobuf.IBoolValue|null);

                /**
                 * Encodes the specified DeleteGroupMemberRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupMemberRequest.verify|verify} messages.
                 * @param message DeleteGroupMemberRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteGroupMemberRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteGroupMemberRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteGroupMemberRequest;
            }

            /** Properties of a QueryGroupMembersRequest. */
            interface IQueryGroupMembersRequest {

                /** QueryGroupMembersRequest groupId */
                groupId?: (string|null);

                /** QueryGroupMembersRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /** QueryGroupMembersRequest memberIds */
                memberIds?: (string[]|null);

                /** QueryGroupMembersRequest withStatus */
                withStatus?: (google.protobuf.IBoolValue|null);
            }

            /** Represents a QueryGroupMembersRequest. */
            class QueryGroupMembersRequest implements IQueryGroupMembersRequest {

                /**
                 * Constructs a new QueryGroupMembersRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryGroupMembersRequest);

                /** QueryGroupMembersRequest groupId. */
                public groupId: string;

                /** QueryGroupMembersRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /** QueryGroupMembersRequest memberIds. */
                public memberIds: string[];

                /** QueryGroupMembersRequest withStatus. */
                public withStatus?: (google.protobuf.IBoolValue|null);

                /**
                 * Encodes the specified QueryGroupMembersRequest message. Does not implicitly {@link im.turms.proto.QueryGroupMembersRequest.verify|verify} messages.
                 * @param message QueryGroupMembersRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryGroupMembersRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryGroupMembersRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryGroupMembersRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryGroupMembersRequest;
            }

            /** Properties of an UpdateGroupMemberRequest. */
            interface IUpdateGroupMemberRequest {

                /** UpdateGroupMemberRequest groupId */
                groupId?: (string|null);

                /** UpdateGroupMemberRequest memberId */
                memberId?: (string|null);

                /** UpdateGroupMemberRequest name */
                name?: (google.protobuf.IStringValue|null);

                /** UpdateGroupMemberRequest role */
                role?: (im.turms.proto.GroupMemberRole|null);

                /** UpdateGroupMemberRequest muteEndDate */
                muteEndDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents an UpdateGroupMemberRequest. */
            class UpdateGroupMemberRequest implements IUpdateGroupMemberRequest {

                /**
                 * Constructs a new UpdateGroupMemberRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateGroupMemberRequest);

                /** UpdateGroupMemberRequest groupId. */
                public groupId: string;

                /** UpdateGroupMemberRequest memberId. */
                public memberId: string;

                /** UpdateGroupMemberRequest name. */
                public name?: (google.protobuf.IStringValue|null);

                /** UpdateGroupMemberRequest role. */
                public role: im.turms.proto.GroupMemberRole;

                /** UpdateGroupMemberRequest muteEndDate. */
                public muteEndDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UpdateGroupMemberRequest message. Does not implicitly {@link im.turms.proto.UpdateGroupMemberRequest.verify|verify} messages.
                 * @param message UpdateGroupMemberRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateGroupMemberRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateGroupMemberRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateGroupMemberRequest;
            }

            /** Properties of a QueryGroupRequest. */
            interface IQueryGroupRequest {

                /** QueryGroupRequest groupId */
                groupId?: (string|null);

                /** QueryGroupRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryGroupRequest. */
            class QueryGroupRequest implements IQueryGroupRequest {

                /**
                 * Constructs a new QueryGroupRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryGroupRequest);

                /** QueryGroupRequest groupId. */
                public groupId: string;

                /** QueryGroupRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryGroupRequest message. Does not implicitly {@link im.turms.proto.QueryGroupRequest.verify|verify} messages.
                 * @param message QueryGroupRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryGroupRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryGroupRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryGroupRequest;
            }

            /** Properties of a QueryJoinedGroupIdsRequest. */
            interface IQueryJoinedGroupIdsRequest {

                /** QueryJoinedGroupIdsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryJoinedGroupIdsRequest. */
            class QueryJoinedGroupIdsRequest implements IQueryJoinedGroupIdsRequest {

                /**
                 * Constructs a new QueryJoinedGroupIdsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryJoinedGroupIdsRequest);

                /** QueryJoinedGroupIdsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryJoinedGroupIdsRequest message. Does not implicitly {@link im.turms.proto.QueryJoinedGroupIdsRequest.verify|verify} messages.
                 * @param message QueryJoinedGroupIdsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryJoinedGroupIdsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryJoinedGroupIdsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryJoinedGroupIdsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryJoinedGroupIdsRequest;
            }

            /** Properties of a QueryJoinedGroupInfosRequest. */
            interface IQueryJoinedGroupInfosRequest {

                /** QueryJoinedGroupInfosRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryJoinedGroupInfosRequest. */
            class QueryJoinedGroupInfosRequest implements IQueryJoinedGroupInfosRequest {

                /**
                 * Constructs a new QueryJoinedGroupInfosRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryJoinedGroupInfosRequest);

                /** QueryJoinedGroupInfosRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryJoinedGroupInfosRequest message. Does not implicitly {@link im.turms.proto.QueryJoinedGroupInfosRequest.verify|verify} messages.
                 * @param message QueryJoinedGroupInfosRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryJoinedGroupInfosRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryJoinedGroupInfosRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryJoinedGroupInfosRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryJoinedGroupInfosRequest;
            }

            /** Properties of an UpdateGroupRequest. */
            interface IUpdateGroupRequest {

                /** UpdateGroupRequest groupId */
                groupId?: (string|null);

                /** UpdateGroupRequest groupName */
                groupName?: (google.protobuf.IStringValue|null);

                /** UpdateGroupRequest intro */
                intro?: (google.protobuf.IStringValue|null);

                /** UpdateGroupRequest announcement */
                announcement?: (google.protobuf.IStringValue|null);

                /** UpdateGroupRequest minimumScore */
                minimumScore?: (google.protobuf.IInt32Value|null);

                /** UpdateGroupRequest groupTypeId */
                groupTypeId?: (google.protobuf.IInt64Value|null);

                /** UpdateGroupRequest muteEndDate */
                muteEndDate?: (google.protobuf.IInt64Value|null);

                /** UpdateGroupRequest successorId */
                successorId?: (google.protobuf.IInt64Value|null);

                /** UpdateGroupRequest quitAfterTransfer */
                quitAfterTransfer?: (google.protobuf.IBoolValue|null);
            }

            /** Represents an UpdateGroupRequest. */
            class UpdateGroupRequest implements IUpdateGroupRequest {

                /**
                 * Constructs a new UpdateGroupRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateGroupRequest);

                /** UpdateGroupRequest groupId. */
                public groupId: string;

                /** UpdateGroupRequest groupName. */
                public groupName?: (google.protobuf.IStringValue|null);

                /** UpdateGroupRequest intro. */
                public intro?: (google.protobuf.IStringValue|null);

                /** UpdateGroupRequest announcement. */
                public announcement?: (google.protobuf.IStringValue|null);

                /** UpdateGroupRequest minimumScore. */
                public minimumScore?: (google.protobuf.IInt32Value|null);

                /** UpdateGroupRequest groupTypeId. */
                public groupTypeId?: (google.protobuf.IInt64Value|null);

                /** UpdateGroupRequest muteEndDate. */
                public muteEndDate?: (google.protobuf.IInt64Value|null);

                /** UpdateGroupRequest successorId. */
                public successorId?: (google.protobuf.IInt64Value|null);

                /** UpdateGroupRequest quitAfterTransfer. */
                public quitAfterTransfer?: (google.protobuf.IBoolValue|null);

                /**
                 * Encodes the specified UpdateGroupRequest message. Does not implicitly {@link im.turms.proto.UpdateGroupRequest.verify|verify} messages.
                 * @param message UpdateGroupRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateGroupRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateGroupRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateGroupRequest;
            }

            /** Properties of a CreateMessageRequest. */
            interface ICreateMessageRequest {

                /** CreateMessageRequest messageId */
                messageId?: (google.protobuf.IInt64Value|null);

                /** CreateMessageRequest isSystemMessage */
                isSystemMessage?: (google.protobuf.IBoolValue|null);

                /** CreateMessageRequest groupId */
                groupId?: (google.protobuf.IInt64Value|null);

                /** CreateMessageRequest recipientId */
                recipientId?: (google.protobuf.IInt64Value|null);

                /** CreateMessageRequest deliveryDate */
                deliveryDate?: (string|null);

                /** CreateMessageRequest text */
                text?: (google.protobuf.IStringValue|null);

                /** CreateMessageRequest records */
                records?: (Uint8Array[]|null);

                /** CreateMessageRequest burnAfter */
                burnAfter?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a CreateMessageRequest. */
            class CreateMessageRequest implements ICreateMessageRequest {

                /**
                 * Constructs a new CreateMessageRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateMessageRequest);

                /** CreateMessageRequest messageId. */
                public messageId?: (google.protobuf.IInt64Value|null);

                /** CreateMessageRequest isSystemMessage. */
                public isSystemMessage?: (google.protobuf.IBoolValue|null);

                /** CreateMessageRequest groupId. */
                public groupId?: (google.protobuf.IInt64Value|null);

                /** CreateMessageRequest recipientId. */
                public recipientId?: (google.protobuf.IInt64Value|null);

                /** CreateMessageRequest deliveryDate. */
                public deliveryDate: string;

                /** CreateMessageRequest text. */
                public text?: (google.protobuf.IStringValue|null);

                /** CreateMessageRequest records. */
                public records: Uint8Array[];

                /** CreateMessageRequest burnAfter. */
                public burnAfter?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified CreateMessageRequest message. Does not implicitly {@link im.turms.proto.CreateMessageRequest.verify|verify} messages.
                 * @param message CreateMessageRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateMessageRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateMessageRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateMessageRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateMessageRequest;
            }

            /** Properties of a QueryMessageStatusesRequest. */
            interface IQueryMessageStatusesRequest {

                /** QueryMessageStatusesRequest messageId */
                messageId?: (string|null);
            }

            /** Represents a QueryMessageStatusesRequest. */
            class QueryMessageStatusesRequest implements IQueryMessageStatusesRequest {

                /**
                 * Constructs a new QueryMessageStatusesRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryMessageStatusesRequest);

                /** QueryMessageStatusesRequest messageId. */
                public messageId: string;

                /**
                 * Encodes the specified QueryMessageStatusesRequest message. Does not implicitly {@link im.turms.proto.QueryMessageStatusesRequest.verify|verify} messages.
                 * @param message QueryMessageStatusesRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryMessageStatusesRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryMessageStatusesRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryMessageStatusesRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryMessageStatusesRequest;
            }

            /** Properties of a QueryMessagesRequest. */
            interface IQueryMessagesRequest {

                /** QueryMessagesRequest ids */
                ids?: (string[]|null);

                /** QueryMessagesRequest size */
                size?: (google.protobuf.IInt32Value|null);

                /** QueryMessagesRequest areGroupMessages */
                areGroupMessages?: (google.protobuf.IBoolValue|null);

                /** QueryMessagesRequest areSystemMessages */
                areSystemMessages?: (google.protobuf.IBoolValue|null);

                /** QueryMessagesRequest fromId */
                fromId?: (google.protobuf.IInt64Value|null);

                /** QueryMessagesRequest deliveryDateAfter */
                deliveryDateAfter?: (google.protobuf.IInt64Value|null);

                /** QueryMessagesRequest deliveryDateBefore */
                deliveryDateBefore?: (google.protobuf.IInt64Value|null);

                /** QueryMessagesRequest deliveryStatus */
                deliveryStatus?: (im.turms.proto.MessageDeliveryStatus|null);
            }

            /** Represents a QueryMessagesRequest. */
            class QueryMessagesRequest implements IQueryMessagesRequest {

                /**
                 * Constructs a new QueryMessagesRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryMessagesRequest);

                /** QueryMessagesRequest ids. */
                public ids: string[];

                /** QueryMessagesRequest size. */
                public size?: (google.protobuf.IInt32Value|null);

                /** QueryMessagesRequest areGroupMessages. */
                public areGroupMessages?: (google.protobuf.IBoolValue|null);

                /** QueryMessagesRequest areSystemMessages. */
                public areSystemMessages?: (google.protobuf.IBoolValue|null);

                /** QueryMessagesRequest fromId. */
                public fromId?: (google.protobuf.IInt64Value|null);

                /** QueryMessagesRequest deliveryDateAfter. */
                public deliveryDateAfter?: (google.protobuf.IInt64Value|null);

                /** QueryMessagesRequest deliveryDateBefore. */
                public deliveryDateBefore?: (google.protobuf.IInt64Value|null);

                /** QueryMessagesRequest deliveryStatus. */
                public deliveryStatus: im.turms.proto.MessageDeliveryStatus;

                /**
                 * Encodes the specified QueryMessagesRequest message. Does not implicitly {@link im.turms.proto.QueryMessagesRequest.verify|verify} messages.
                 * @param message QueryMessagesRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryMessagesRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryMessagesRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryMessagesRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryMessagesRequest;
            }

            /** Properties of a QueryPendingMessagesWithTotalRequest. */
            interface IQueryPendingMessagesWithTotalRequest {

                /** QueryPendingMessagesWithTotalRequest size */
                size?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a QueryPendingMessagesWithTotalRequest. */
            class QueryPendingMessagesWithTotalRequest implements IQueryPendingMessagesWithTotalRequest {

                /**
                 * Constructs a new QueryPendingMessagesWithTotalRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryPendingMessagesWithTotalRequest);

                /** QueryPendingMessagesWithTotalRequest size. */
                public size?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified QueryPendingMessagesWithTotalRequest message. Does not implicitly {@link im.turms.proto.QueryPendingMessagesWithTotalRequest.verify|verify} messages.
                 * @param message QueryPendingMessagesWithTotalRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryPendingMessagesWithTotalRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryPendingMessagesWithTotalRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryPendingMessagesWithTotalRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryPendingMessagesWithTotalRequest;
            }

            /** Properties of an UpdateMessageRequest. */
            interface IUpdateMessageRequest {

                /** UpdateMessageRequest messageId */
                messageId?: (string|null);

                /** UpdateMessageRequest isSystemMessage */
                isSystemMessage?: (google.protobuf.IBoolValue|null);

                /** UpdateMessageRequest text */
                text?: (google.protobuf.IStringValue|null);

                /** UpdateMessageRequest records */
                records?: (Uint8Array[]|null);

                /** UpdateMessageRequest recallDate */
                recallDate?: (google.protobuf.IInt64Value|null);

                /** UpdateMessageRequest readDate */
                readDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents an UpdateMessageRequest. */
            class UpdateMessageRequest implements IUpdateMessageRequest {

                /**
                 * Constructs a new UpdateMessageRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateMessageRequest);

                /** UpdateMessageRequest messageId. */
                public messageId: string;

                /** UpdateMessageRequest isSystemMessage. */
                public isSystemMessage?: (google.protobuf.IBoolValue|null);

                /** UpdateMessageRequest text. */
                public text?: (google.protobuf.IStringValue|null);

                /** UpdateMessageRequest records. */
                public records: Uint8Array[];

                /** UpdateMessageRequest recallDate. */
                public recallDate?: (google.protobuf.IInt64Value|null);

                /** UpdateMessageRequest readDate. */
                public readDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified UpdateMessageRequest message. Does not implicitly {@link im.turms.proto.UpdateMessageRequest.verify|verify} messages.
                 * @param message UpdateMessageRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateMessageRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateMessageRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateMessageRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateMessageRequest;
            }

            /** Properties of an UpdateTypingStatusRequest. */
            interface IUpdateTypingStatusRequest {

                /** UpdateTypingStatusRequest isGroupMessage */
                isGroupMessage?: (boolean|null);

                /** UpdateTypingStatusRequest toId */
                toId?: (string|null);
            }

            /** Represents an UpdateTypingStatusRequest. */
            class UpdateTypingStatusRequest implements IUpdateTypingStatusRequest {

                /**
                 * Constructs a new UpdateTypingStatusRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateTypingStatusRequest);

                /** UpdateTypingStatusRequest isGroupMessage. */
                public isGroupMessage: boolean;

                /** UpdateTypingStatusRequest toId. */
                public toId: string;

                /**
                 * Encodes the specified UpdateTypingStatusRequest message. Does not implicitly {@link im.turms.proto.UpdateTypingStatusRequest.verify|verify} messages.
                 * @param message UpdateTypingStatusRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateTypingStatusRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateTypingStatusRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateTypingStatusRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateTypingStatusRequest;
            }

            /** Properties of an AckRequest. */
            interface IAckRequest {

                /** AckRequest messageIds */
                messageIds?: (string[]|null);
            }

            /** Represents an AckRequest. */
            class AckRequest implements IAckRequest {

                /**
                 * Constructs a new AckRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IAckRequest);

                /** AckRequest messageIds. */
                public messageIds: string[];

                /**
                 * Encodes the specified AckRequest message. Does not implicitly {@link im.turms.proto.AckRequest.verify|verify} messages.
                 * @param message AckRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IAckRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an AckRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns AckRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.AckRequest;
            }

            /** Properties of a DeleteResourceRequest. */
            interface IDeleteResourceRequest {

                /** DeleteResourceRequest contentType */
                contentType?: (im.turms.proto.ContentType|null);

                /** DeleteResourceRequest keyStr */
                keyStr?: (google.protobuf.IStringValue|null);

                /** DeleteResourceRequest keyNum */
                keyNum?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a DeleteResourceRequest. */
            class DeleteResourceRequest implements IDeleteResourceRequest {

                /**
                 * Constructs a new DeleteResourceRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteResourceRequest);

                /** DeleteResourceRequest contentType. */
                public contentType: im.turms.proto.ContentType;

                /** DeleteResourceRequest keyStr. */
                public keyStr?: (google.protobuf.IStringValue|null);

                /** DeleteResourceRequest keyNum. */
                public keyNum?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified DeleteResourceRequest message. Does not implicitly {@link im.turms.proto.DeleteResourceRequest.verify|verify} messages.
                 * @param message DeleteResourceRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteResourceRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteResourceRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteResourceRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteResourceRequest;
            }

            /** Properties of a QuerySignedGetUrlRequest. */
            interface IQuerySignedGetUrlRequest {

                /** QuerySignedGetUrlRequest contentType */
                contentType?: (im.turms.proto.ContentType|null);

                /** QuerySignedGetUrlRequest keyStr */
                keyStr?: (google.protobuf.IStringValue|null);

                /** QuerySignedGetUrlRequest keyNum */
                keyNum?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QuerySignedGetUrlRequest. */
            class QuerySignedGetUrlRequest implements IQuerySignedGetUrlRequest {

                /**
                 * Constructs a new QuerySignedGetUrlRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQuerySignedGetUrlRequest);

                /** QuerySignedGetUrlRequest contentType. */
                public contentType: im.turms.proto.ContentType;

                /** QuerySignedGetUrlRequest keyStr. */
                public keyStr?: (google.protobuf.IStringValue|null);

                /** QuerySignedGetUrlRequest keyNum. */
                public keyNum?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QuerySignedGetUrlRequest message. Does not implicitly {@link im.turms.proto.QuerySignedGetUrlRequest.verify|verify} messages.
                 * @param message QuerySignedGetUrlRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQuerySignedGetUrlRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QuerySignedGetUrlRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QuerySignedGetUrlRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QuerySignedGetUrlRequest;
            }

            /** Properties of a QuerySignedPutUrlRequest. */
            interface IQuerySignedPutUrlRequest {

                /** QuerySignedPutUrlRequest contentType */
                contentType?: (im.turms.proto.ContentType|null);

                /** QuerySignedPutUrlRequest keyStr */
                keyStr?: (google.protobuf.IStringValue|null);

                /** QuerySignedPutUrlRequest keyNum */
                keyNum?: (google.protobuf.IInt64Value|null);

                /** QuerySignedPutUrlRequest contentLength */
                contentLength?: (string|null);
            }

            /** Represents a QuerySignedPutUrlRequest. */
            class QuerySignedPutUrlRequest implements IQuerySignedPutUrlRequest {

                /**
                 * Constructs a new QuerySignedPutUrlRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQuerySignedPutUrlRequest);

                /** QuerySignedPutUrlRequest contentType. */
                public contentType: im.turms.proto.ContentType;

                /** QuerySignedPutUrlRequest keyStr. */
                public keyStr?: (google.protobuf.IStringValue|null);

                /** QuerySignedPutUrlRequest keyNum. */
                public keyNum?: (google.protobuf.IInt64Value|null);

                /** QuerySignedPutUrlRequest contentLength. */
                public contentLength: string;

                /**
                 * Encodes the specified QuerySignedPutUrlRequest message. Does not implicitly {@link im.turms.proto.QuerySignedPutUrlRequest.verify|verify} messages.
                 * @param message QuerySignedPutUrlRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQuerySignedPutUrlRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QuerySignedPutUrlRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QuerySignedPutUrlRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QuerySignedPutUrlRequest;
            }

            /** Properties of a TurmsRequest. */
            interface ITurmsRequest {

                /** TurmsRequest requestId */
                requestId?: (google.protobuf.IInt64Value|null);

                /** TurmsRequest ackRequest */
                ackRequest?: (im.turms.proto.IAckRequest|null);

                /** TurmsRequest deleteResourceRequest */
                deleteResourceRequest?: (im.turms.proto.IDeleteResourceRequest|null);

                /** TurmsRequest querySignedGetUrlRequest */
                querySignedGetUrlRequest?: (im.turms.proto.IQuerySignedGetUrlRequest|null);

                /** TurmsRequest querySignedPutUrlRequest */
                querySignedPutUrlRequest?: (im.turms.proto.IQuerySignedPutUrlRequest|null);

                /** TurmsRequest createMessageRequest */
                createMessageRequest?: (im.turms.proto.ICreateMessageRequest|null);

                /** TurmsRequest queryMessageStatusesRequest */
                queryMessageStatusesRequest?: (im.turms.proto.IQueryMessageStatusesRequest|null);

                /** TurmsRequest queryMessagesRequest */
                queryMessagesRequest?: (im.turms.proto.IQueryMessagesRequest|null);

                /** TurmsRequest queryPendingMessagesWithTotalRequest */
                queryPendingMessagesWithTotalRequest?: (im.turms.proto.IQueryPendingMessagesWithTotalRequest|null);

                /** TurmsRequest updateMessageRequest */
                updateMessageRequest?: (im.turms.proto.IUpdateMessageRequest|null);

                /** TurmsRequest updateTypingStatusRequest */
                updateTypingStatusRequest?: (im.turms.proto.IUpdateTypingStatusRequest|null);

                /** TurmsRequest createSessionRequest */
                createSessionRequest?: (im.turms.proto.ICreateSessionRequest|null);

                /** TurmsRequest deleteSessionRequest */
                deleteSessionRequest?: (im.turms.proto.IDeleteSessionRequest|null);

                /** TurmsRequest queryUserProfileRequest */
                queryUserProfileRequest?: (im.turms.proto.IQueryUserProfileRequest|null);

                /** TurmsRequest queryUserIdsNearbyRequest */
                queryUserIdsNearbyRequest?: (im.turms.proto.IQueryUserIdsNearbyRequest|null);

                /** TurmsRequest queryUserInfosNearbyRequest */
                queryUserInfosNearbyRequest?: (im.turms.proto.IQueryUserInfosNearbyRequest|null);

                /** TurmsRequest queryUserOnlineStatusesRequest */
                queryUserOnlineStatusesRequest?: (im.turms.proto.IQueryUserOnlineStatusesRequest|null);

                /** TurmsRequest updateUserLocationRequest */
                updateUserLocationRequest?: (im.turms.proto.IUpdateUserLocationRequest|null);

                /** TurmsRequest updateUserOnlineStatusRequest */
                updateUserOnlineStatusRequest?: (im.turms.proto.IUpdateUserOnlineStatusRequest|null);

                /** TurmsRequest updateUserRequest */
                updateUserRequest?: (im.turms.proto.IUpdateUserRequest|null);

                /** TurmsRequest createFriendRequestRequest */
                createFriendRequestRequest?: (im.turms.proto.ICreateFriendRequestRequest|null);

                /** TurmsRequest createRelationshipGroupRequest */
                createRelationshipGroupRequest?: (im.turms.proto.ICreateRelationshipGroupRequest|null);

                /** TurmsRequest createRelationshipRequest */
                createRelationshipRequest?: (im.turms.proto.ICreateRelationshipRequest|null);

                /** TurmsRequest deleteRelationshipGroupRequest */
                deleteRelationshipGroupRequest?: (im.turms.proto.IDeleteRelationshipGroupRequest|null);

                /** TurmsRequest deleteRelationshipRequest */
                deleteRelationshipRequest?: (im.turms.proto.IDeleteRelationshipRequest|null);

                /** TurmsRequest queryFriendRequestsRequest */
                queryFriendRequestsRequest?: (im.turms.proto.IQueryFriendRequestsRequest|null);

                /** TurmsRequest queryRelatedUserIdsRequest */
                queryRelatedUserIdsRequest?: (im.turms.proto.IQueryRelatedUserIdsRequest|null);

                /** TurmsRequest queryRelationshipGroupsRequest */
                queryRelationshipGroupsRequest?: (im.turms.proto.IQueryRelationshipGroupsRequest|null);

                /** TurmsRequest queryRelationshipsRequest */
                queryRelationshipsRequest?: (im.turms.proto.IQueryRelationshipsRequest|null);

                /** TurmsRequest updateFriendRequestRequest */
                updateFriendRequestRequest?: (im.turms.proto.IUpdateFriendRequestRequest|null);

                /** TurmsRequest updateRelationshipGroupRequest */
                updateRelationshipGroupRequest?: (im.turms.proto.IUpdateRelationshipGroupRequest|null);

                /** TurmsRequest updateRelationshipRequest */
                updateRelationshipRequest?: (im.turms.proto.IUpdateRelationshipRequest|null);

                /** TurmsRequest createGroupRequest */
                createGroupRequest?: (im.turms.proto.ICreateGroupRequest|null);

                /** TurmsRequest deleteGroupRequest */
                deleteGroupRequest?: (im.turms.proto.IDeleteGroupRequest|null);

                /** TurmsRequest queryGroupRequest */
                queryGroupRequest?: (im.turms.proto.IQueryGroupRequest|null);

                /** TurmsRequest queryJoinedGroupIdsRequest */
                queryJoinedGroupIdsRequest?: (im.turms.proto.IQueryJoinedGroupIdsRequest|null);

                /** TurmsRequest queryJoinedGroupInfosRequest */
                queryJoinedGroupInfosRequest?: (im.turms.proto.IQueryJoinedGroupInfosRequest|null);

                /** TurmsRequest updateGroupRequest */
                updateGroupRequest?: (im.turms.proto.IUpdateGroupRequest|null);

                /** TurmsRequest createGroupBlacklistedUserRequest */
                createGroupBlacklistedUserRequest?: (im.turms.proto.ICreateGroupBlacklistedUserRequest|null);

                /** TurmsRequest deleteGroupBlacklistedUserRequest */
                deleteGroupBlacklistedUserRequest?: (im.turms.proto.IDeleteGroupBlacklistedUserRequest|null);

                /** TurmsRequest queryGroupBlacklistedUserIdsRequest */
                queryGroupBlacklistedUserIdsRequest?: (im.turms.proto.IQueryGroupBlacklistedUserIdsRequest|null);

                /** TurmsRequest queryGroupBlacklistedUserInfosRequest */
                queryGroupBlacklistedUserInfosRequest?: (im.turms.proto.IQueryGroupBlacklistedUserInfosRequest|null);

                /** TurmsRequest checkGroupJoinQuestionsAnswersRequest */
                checkGroupJoinQuestionsAnswersRequest?: (im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest|null);

                /** TurmsRequest createGroupInvitationRequest */
                createGroupInvitationRequest?: (im.turms.proto.ICreateGroupInvitationRequest|null);

                /** TurmsRequest createGroupJoinRequestRequest */
                createGroupJoinRequestRequest?: (im.turms.proto.ICreateGroupJoinRequestRequest|null);

                /** TurmsRequest createGroupJoinQuestionRequest */
                createGroupJoinQuestionRequest?: (im.turms.proto.ICreateGroupJoinQuestionRequest|null);

                /** TurmsRequest deleteGroupInvitationRequest */
                deleteGroupInvitationRequest?: (im.turms.proto.IDeleteGroupInvitationRequest|null);

                /** TurmsRequest deleteGroupJoinRequestRequest */
                deleteGroupJoinRequestRequest?: (im.turms.proto.IDeleteGroupJoinRequestRequest|null);

                /** TurmsRequest deleteGroupJoinQuestionRequest */
                deleteGroupJoinQuestionRequest?: (im.turms.proto.IDeleteGroupJoinQuestionRequest|null);

                /** TurmsRequest queryGroupInvitationsRequest */
                queryGroupInvitationsRequest?: (im.turms.proto.IQueryGroupInvitationsRequest|null);

                /** TurmsRequest queryGroupJoinRequestsRequest */
                queryGroupJoinRequestsRequest?: (im.turms.proto.IQueryGroupJoinRequestsRequest|null);

                /** TurmsRequest queryGroupJoinQuestionsRequest */
                queryGroupJoinQuestionsRequest?: (im.turms.proto.IQueryGroupJoinQuestionsRequest|null);

                /** TurmsRequest updateGroupJoinQuestionRequest */
                updateGroupJoinQuestionRequest?: (im.turms.proto.IUpdateGroupJoinQuestionRequest|null);

                /** TurmsRequest createGroupMemberRequest */
                createGroupMemberRequest?: (im.turms.proto.ICreateGroupMemberRequest|null);

                /** TurmsRequest deleteGroupMemberRequest */
                deleteGroupMemberRequest?: (im.turms.proto.IDeleteGroupMemberRequest|null);

                /** TurmsRequest queryGroupMembersRequest */
                queryGroupMembersRequest?: (im.turms.proto.IQueryGroupMembersRequest|null);

                /** TurmsRequest updateGroupMemberRequest */
                updateGroupMemberRequest?: (im.turms.proto.IUpdateGroupMemberRequest|null);
            }

            /** Represents a TurmsRequest. */
            class TurmsRequest implements ITurmsRequest {

                /**
                 * Constructs a new TurmsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ITurmsRequest);

                /** TurmsRequest requestId. */
                public requestId?: (google.protobuf.IInt64Value|null);

                /** TurmsRequest ackRequest. */
                public ackRequest?: (im.turms.proto.IAckRequest|null);

                /** TurmsRequest deleteResourceRequest. */
                public deleteResourceRequest?: (im.turms.proto.IDeleteResourceRequest|null);

                /** TurmsRequest querySignedGetUrlRequest. */
                public querySignedGetUrlRequest?: (im.turms.proto.IQuerySignedGetUrlRequest|null);

                /** TurmsRequest querySignedPutUrlRequest. */
                public querySignedPutUrlRequest?: (im.turms.proto.IQuerySignedPutUrlRequest|null);

                /** TurmsRequest createMessageRequest. */
                public createMessageRequest?: (im.turms.proto.ICreateMessageRequest|null);

                /** TurmsRequest queryMessageStatusesRequest. */
                public queryMessageStatusesRequest?: (im.turms.proto.IQueryMessageStatusesRequest|null);

                /** TurmsRequest queryMessagesRequest. */
                public queryMessagesRequest?: (im.turms.proto.IQueryMessagesRequest|null);

                /** TurmsRequest queryPendingMessagesWithTotalRequest. */
                public queryPendingMessagesWithTotalRequest?: (im.turms.proto.IQueryPendingMessagesWithTotalRequest|null);

                /** TurmsRequest updateMessageRequest. */
                public updateMessageRequest?: (im.turms.proto.IUpdateMessageRequest|null);

                /** TurmsRequest updateTypingStatusRequest. */
                public updateTypingStatusRequest?: (im.turms.proto.IUpdateTypingStatusRequest|null);

                /** TurmsRequest createSessionRequest. */
                public createSessionRequest?: (im.turms.proto.ICreateSessionRequest|null);

                /** TurmsRequest deleteSessionRequest. */
                public deleteSessionRequest?: (im.turms.proto.IDeleteSessionRequest|null);

                /** TurmsRequest queryUserProfileRequest. */
                public queryUserProfileRequest?: (im.turms.proto.IQueryUserProfileRequest|null);

                /** TurmsRequest queryUserIdsNearbyRequest. */
                public queryUserIdsNearbyRequest?: (im.turms.proto.IQueryUserIdsNearbyRequest|null);

                /** TurmsRequest queryUserInfosNearbyRequest. */
                public queryUserInfosNearbyRequest?: (im.turms.proto.IQueryUserInfosNearbyRequest|null);

                /** TurmsRequest queryUserOnlineStatusesRequest. */
                public queryUserOnlineStatusesRequest?: (im.turms.proto.IQueryUserOnlineStatusesRequest|null);

                /** TurmsRequest updateUserLocationRequest. */
                public updateUserLocationRequest?: (im.turms.proto.IUpdateUserLocationRequest|null);

                /** TurmsRequest updateUserOnlineStatusRequest. */
                public updateUserOnlineStatusRequest?: (im.turms.proto.IUpdateUserOnlineStatusRequest|null);

                /** TurmsRequest updateUserRequest. */
                public updateUserRequest?: (im.turms.proto.IUpdateUserRequest|null);

                /** TurmsRequest createFriendRequestRequest. */
                public createFriendRequestRequest?: (im.turms.proto.ICreateFriendRequestRequest|null);

                /** TurmsRequest createRelationshipGroupRequest. */
                public createRelationshipGroupRequest?: (im.turms.proto.ICreateRelationshipGroupRequest|null);

                /** TurmsRequest createRelationshipRequest. */
                public createRelationshipRequest?: (im.turms.proto.ICreateRelationshipRequest|null);

                /** TurmsRequest deleteRelationshipGroupRequest. */
                public deleteRelationshipGroupRequest?: (im.turms.proto.IDeleteRelationshipGroupRequest|null);

                /** TurmsRequest deleteRelationshipRequest. */
                public deleteRelationshipRequest?: (im.turms.proto.IDeleteRelationshipRequest|null);

                /** TurmsRequest queryFriendRequestsRequest. */
                public queryFriendRequestsRequest?: (im.turms.proto.IQueryFriendRequestsRequest|null);

                /** TurmsRequest queryRelatedUserIdsRequest. */
                public queryRelatedUserIdsRequest?: (im.turms.proto.IQueryRelatedUserIdsRequest|null);

                /** TurmsRequest queryRelationshipGroupsRequest. */
                public queryRelationshipGroupsRequest?: (im.turms.proto.IQueryRelationshipGroupsRequest|null);

                /** TurmsRequest queryRelationshipsRequest. */
                public queryRelationshipsRequest?: (im.turms.proto.IQueryRelationshipsRequest|null);

                /** TurmsRequest updateFriendRequestRequest. */
                public updateFriendRequestRequest?: (im.turms.proto.IUpdateFriendRequestRequest|null);

                /** TurmsRequest updateRelationshipGroupRequest. */
                public updateRelationshipGroupRequest?: (im.turms.proto.IUpdateRelationshipGroupRequest|null);

                /** TurmsRequest updateRelationshipRequest. */
                public updateRelationshipRequest?: (im.turms.proto.IUpdateRelationshipRequest|null);

                /** TurmsRequest createGroupRequest. */
                public createGroupRequest?: (im.turms.proto.ICreateGroupRequest|null);

                /** TurmsRequest deleteGroupRequest. */
                public deleteGroupRequest?: (im.turms.proto.IDeleteGroupRequest|null);

                /** TurmsRequest queryGroupRequest. */
                public queryGroupRequest?: (im.turms.proto.IQueryGroupRequest|null);

                /** TurmsRequest queryJoinedGroupIdsRequest. */
                public queryJoinedGroupIdsRequest?: (im.turms.proto.IQueryJoinedGroupIdsRequest|null);

                /** TurmsRequest queryJoinedGroupInfosRequest. */
                public queryJoinedGroupInfosRequest?: (im.turms.proto.IQueryJoinedGroupInfosRequest|null);

                /** TurmsRequest updateGroupRequest. */
                public updateGroupRequest?: (im.turms.proto.IUpdateGroupRequest|null);

                /** TurmsRequest createGroupBlacklistedUserRequest. */
                public createGroupBlacklistedUserRequest?: (im.turms.proto.ICreateGroupBlacklistedUserRequest|null);

                /** TurmsRequest deleteGroupBlacklistedUserRequest. */
                public deleteGroupBlacklistedUserRequest?: (im.turms.proto.IDeleteGroupBlacklistedUserRequest|null);

                /** TurmsRequest queryGroupBlacklistedUserIdsRequest. */
                public queryGroupBlacklistedUserIdsRequest?: (im.turms.proto.IQueryGroupBlacklistedUserIdsRequest|null);

                /** TurmsRequest queryGroupBlacklistedUserInfosRequest. */
                public queryGroupBlacklistedUserInfosRequest?: (im.turms.proto.IQueryGroupBlacklistedUserInfosRequest|null);

                /** TurmsRequest checkGroupJoinQuestionsAnswersRequest. */
                public checkGroupJoinQuestionsAnswersRequest?: (im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest|null);

                /** TurmsRequest createGroupInvitationRequest. */
                public createGroupInvitationRequest?: (im.turms.proto.ICreateGroupInvitationRequest|null);

                /** TurmsRequest createGroupJoinRequestRequest. */
                public createGroupJoinRequestRequest?: (im.turms.proto.ICreateGroupJoinRequestRequest|null);

                /** TurmsRequest createGroupJoinQuestionRequest. */
                public createGroupJoinQuestionRequest?: (im.turms.proto.ICreateGroupJoinQuestionRequest|null);

                /** TurmsRequest deleteGroupInvitationRequest. */
                public deleteGroupInvitationRequest?: (im.turms.proto.IDeleteGroupInvitationRequest|null);

                /** TurmsRequest deleteGroupJoinRequestRequest. */
                public deleteGroupJoinRequestRequest?: (im.turms.proto.IDeleteGroupJoinRequestRequest|null);

                /** TurmsRequest deleteGroupJoinQuestionRequest. */
                public deleteGroupJoinQuestionRequest?: (im.turms.proto.IDeleteGroupJoinQuestionRequest|null);

                /** TurmsRequest queryGroupInvitationsRequest. */
                public queryGroupInvitationsRequest?: (im.turms.proto.IQueryGroupInvitationsRequest|null);

                /** TurmsRequest queryGroupJoinRequestsRequest. */
                public queryGroupJoinRequestsRequest?: (im.turms.proto.IQueryGroupJoinRequestsRequest|null);

                /** TurmsRequest queryGroupJoinQuestionsRequest. */
                public queryGroupJoinQuestionsRequest?: (im.turms.proto.IQueryGroupJoinQuestionsRequest|null);

                /** TurmsRequest updateGroupJoinQuestionRequest. */
                public updateGroupJoinQuestionRequest?: (im.turms.proto.IUpdateGroupJoinQuestionRequest|null);

                /** TurmsRequest createGroupMemberRequest. */
                public createGroupMemberRequest?: (im.turms.proto.ICreateGroupMemberRequest|null);

                /** TurmsRequest deleteGroupMemberRequest. */
                public deleteGroupMemberRequest?: (im.turms.proto.IDeleteGroupMemberRequest|null);

                /** TurmsRequest queryGroupMembersRequest. */
                public queryGroupMembersRequest?: (im.turms.proto.IQueryGroupMembersRequest|null);

                /** TurmsRequest updateGroupMemberRequest. */
                public updateGroupMemberRequest?: (im.turms.proto.IUpdateGroupMemberRequest|null);

                /** TurmsRequest kind. */
                public kind?: ("ackRequest"|"deleteResourceRequest"|"querySignedGetUrlRequest"|"querySignedPutUrlRequest"|"createMessageRequest"|"queryMessageStatusesRequest"|"queryMessagesRequest"|"queryPendingMessagesWithTotalRequest"|"updateMessageRequest"|"updateTypingStatusRequest"|"createSessionRequest"|"deleteSessionRequest"|"queryUserProfileRequest"|"queryUserIdsNearbyRequest"|"queryUserInfosNearbyRequest"|"queryUserOnlineStatusesRequest"|"updateUserLocationRequest"|"updateUserOnlineStatusRequest"|"updateUserRequest"|"createFriendRequestRequest"|"createRelationshipGroupRequest"|"createRelationshipRequest"|"deleteRelationshipGroupRequest"|"deleteRelationshipRequest"|"queryFriendRequestsRequest"|"queryRelatedUserIdsRequest"|"queryRelationshipGroupsRequest"|"queryRelationshipsRequest"|"updateFriendRequestRequest"|"updateRelationshipGroupRequest"|"updateRelationshipRequest"|"createGroupRequest"|"deleteGroupRequest"|"queryGroupRequest"|"queryJoinedGroupIdsRequest"|"queryJoinedGroupInfosRequest"|"updateGroupRequest"|"createGroupBlacklistedUserRequest"|"deleteGroupBlacklistedUserRequest"|"queryGroupBlacklistedUserIdsRequest"|"queryGroupBlacklistedUserInfosRequest"|"checkGroupJoinQuestionsAnswersRequest"|"createGroupInvitationRequest"|"createGroupJoinRequestRequest"|"createGroupJoinQuestionRequest"|"deleteGroupInvitationRequest"|"deleteGroupJoinRequestRequest"|"deleteGroupJoinQuestionRequest"|"queryGroupInvitationsRequest"|"queryGroupJoinRequestsRequest"|"queryGroupJoinQuestionsRequest"|"updateGroupJoinQuestionRequest"|"createGroupMemberRequest"|"deleteGroupMemberRequest"|"queryGroupMembersRequest"|"updateGroupMemberRequest");

                /**
                 * Encodes the specified TurmsRequest message. Does not implicitly {@link im.turms.proto.TurmsRequest.verify|verify} messages.
                 * @param message TurmsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ITurmsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a TurmsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns TurmsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.TurmsRequest;
            }

            /** Properties of a CreateSessionRequest. */
            interface ICreateSessionRequest {

                /** CreateSessionRequest userId */
                userId?: (string|null);

                /** CreateSessionRequest password */
                password?: (google.protobuf.IStringValue|null);

                /** CreateSessionRequest userStatus */
                userStatus?: (im.turms.proto.UserStatus|null);

                /** CreateSessionRequest deviceType */
                deviceType?: (im.turms.proto.DeviceType|null);

                /** CreateSessionRequest deviceDetails */
                deviceDetails?: (google.protobuf.IStringValue|null);

                /** CreateSessionRequest location */
                location?: (im.turms.proto.IUserLocation|null);
            }

            /** Represents a CreateSessionRequest. */
            class CreateSessionRequest implements ICreateSessionRequest {

                /**
                 * Constructs a new CreateSessionRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateSessionRequest);

                /** CreateSessionRequest userId. */
                public userId: string;

                /** CreateSessionRequest password. */
                public password?: (google.protobuf.IStringValue|null);

                /** CreateSessionRequest userStatus. */
                public userStatus: im.turms.proto.UserStatus;

                /** CreateSessionRequest deviceType. */
                public deviceType: im.turms.proto.DeviceType;

                /** CreateSessionRequest deviceDetails. */
                public deviceDetails?: (google.protobuf.IStringValue|null);

                /** CreateSessionRequest location. */
                public location?: (im.turms.proto.IUserLocation|null);

                /**
                 * Encodes the specified CreateSessionRequest message. Does not implicitly {@link im.turms.proto.CreateSessionRequest.verify|verify} messages.
                 * @param message CreateSessionRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateSessionRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateSessionRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateSessionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateSessionRequest;
            }

            /** Properties of a DeleteSessionRequest. */
            interface IDeleteSessionRequest {
            }

            /** Represents a DeleteSessionRequest. */
            class DeleteSessionRequest implements IDeleteSessionRequest {

                /**
                 * Constructs a new DeleteSessionRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteSessionRequest);

                /**
                 * Encodes the specified DeleteSessionRequest message. Does not implicitly {@link im.turms.proto.DeleteSessionRequest.verify|verify} messages.
                 * @param message DeleteSessionRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteSessionRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteSessionRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteSessionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteSessionRequest;
            }

            /** Properties of a QueryUserIdsNearbyRequest. */
            interface IQueryUserIdsNearbyRequest {

                /** QueryUserIdsNearbyRequest latitude */
                latitude?: (number|null);

                /** QueryUserIdsNearbyRequest longitude */
                longitude?: (number|null);

                /** QueryUserIdsNearbyRequest distance */
                distance?: (google.protobuf.IFloatValue|null);

                /** QueryUserIdsNearbyRequest maxNumber */
                maxNumber?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a QueryUserIdsNearbyRequest. */
            class QueryUserIdsNearbyRequest implements IQueryUserIdsNearbyRequest {

                /**
                 * Constructs a new QueryUserIdsNearbyRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryUserIdsNearbyRequest);

                /** QueryUserIdsNearbyRequest latitude. */
                public latitude: number;

                /** QueryUserIdsNearbyRequest longitude. */
                public longitude: number;

                /** QueryUserIdsNearbyRequest distance. */
                public distance?: (google.protobuf.IFloatValue|null);

                /** QueryUserIdsNearbyRequest maxNumber. */
                public maxNumber?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified QueryUserIdsNearbyRequest message. Does not implicitly {@link im.turms.proto.QueryUserIdsNearbyRequest.verify|verify} messages.
                 * @param message QueryUserIdsNearbyRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryUserIdsNearbyRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryUserIdsNearbyRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryUserIdsNearbyRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryUserIdsNearbyRequest;
            }

            /** Properties of a QueryUserInfosNearbyRequest. */
            interface IQueryUserInfosNearbyRequest {

                /** QueryUserInfosNearbyRequest latitude */
                latitude?: (number|null);

                /** QueryUserInfosNearbyRequest longitude */
                longitude?: (number|null);

                /** QueryUserInfosNearbyRequest distance */
                distance?: (google.protobuf.IFloatValue|null);

                /** QueryUserInfosNearbyRequest maxNumber */
                maxNumber?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a QueryUserInfosNearbyRequest. */
            class QueryUserInfosNearbyRequest implements IQueryUserInfosNearbyRequest {

                /**
                 * Constructs a new QueryUserInfosNearbyRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryUserInfosNearbyRequest);

                /** QueryUserInfosNearbyRequest latitude. */
                public latitude: number;

                /** QueryUserInfosNearbyRequest longitude. */
                public longitude: number;

                /** QueryUserInfosNearbyRequest distance. */
                public distance?: (google.protobuf.IFloatValue|null);

                /** QueryUserInfosNearbyRequest maxNumber. */
                public maxNumber?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified QueryUserInfosNearbyRequest message. Does not implicitly {@link im.turms.proto.QueryUserInfosNearbyRequest.verify|verify} messages.
                 * @param message QueryUserInfosNearbyRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryUserInfosNearbyRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryUserInfosNearbyRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryUserInfosNearbyRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryUserInfosNearbyRequest;
            }

            /** Properties of a QueryUserOnlineStatusesRequest. */
            interface IQueryUserOnlineStatusesRequest {

                /** QueryUserOnlineStatusesRequest userIds */
                userIds?: (string[]|null);
            }

            /** Represents a QueryUserOnlineStatusesRequest. */
            class QueryUserOnlineStatusesRequest implements IQueryUserOnlineStatusesRequest {

                /**
                 * Constructs a new QueryUserOnlineStatusesRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryUserOnlineStatusesRequest);

                /** QueryUserOnlineStatusesRequest userIds. */
                public userIds: string[];

                /**
                 * Encodes the specified QueryUserOnlineStatusesRequest message. Does not implicitly {@link im.turms.proto.QueryUserOnlineStatusesRequest.verify|verify} messages.
                 * @param message QueryUserOnlineStatusesRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryUserOnlineStatusesRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryUserOnlineStatusesRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryUserOnlineStatusesRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryUserOnlineStatusesRequest;
            }

            /** Properties of a QueryUserProfileRequest. */
            interface IQueryUserProfileRequest {

                /** QueryUserProfileRequest userId */
                userId?: (string|null);

                /** QueryUserProfileRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryUserProfileRequest. */
            class QueryUserProfileRequest implements IQueryUserProfileRequest {

                /**
                 * Constructs a new QueryUserProfileRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryUserProfileRequest);

                /** QueryUserProfileRequest userId. */
                public userId: string;

                /** QueryUserProfileRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryUserProfileRequest message. Does not implicitly {@link im.turms.proto.QueryUserProfileRequest.verify|verify} messages.
                 * @param message QueryUserProfileRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryUserProfileRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryUserProfileRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryUserProfileRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryUserProfileRequest;
            }

            /** Properties of a CreateFriendRequestRequest. */
            interface ICreateFriendRequestRequest {

                /** CreateFriendRequestRequest recipientId */
                recipientId?: (string|null);

                /** CreateFriendRequestRequest content */
                content?: (string|null);
            }

            /** Represents a CreateFriendRequestRequest. */
            class CreateFriendRequestRequest implements ICreateFriendRequestRequest {

                /**
                 * Constructs a new CreateFriendRequestRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateFriendRequestRequest);

                /** CreateFriendRequestRequest recipientId. */
                public recipientId: string;

                /** CreateFriendRequestRequest content. */
                public content: string;

                /**
                 * Encodes the specified CreateFriendRequestRequest message. Does not implicitly {@link im.turms.proto.CreateFriendRequestRequest.verify|verify} messages.
                 * @param message CreateFriendRequestRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateFriendRequestRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateFriendRequestRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateFriendRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateFriendRequestRequest;
            }

            /** Properties of a CreateRelationshipGroupRequest. */
            interface ICreateRelationshipGroupRequest {

                /** CreateRelationshipGroupRequest name */
                name?: (string|null);
            }

            /** Represents a CreateRelationshipGroupRequest. */
            class CreateRelationshipGroupRequest implements ICreateRelationshipGroupRequest {

                /**
                 * Constructs a new CreateRelationshipGroupRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateRelationshipGroupRequest);

                /** CreateRelationshipGroupRequest name. */
                public name: string;

                /**
                 * Encodes the specified CreateRelationshipGroupRequest message. Does not implicitly {@link im.turms.proto.CreateRelationshipGroupRequest.verify|verify} messages.
                 * @param message CreateRelationshipGroupRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateRelationshipGroupRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateRelationshipGroupRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateRelationshipGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateRelationshipGroupRequest;
            }

            /** Properties of a CreateRelationshipRequest. */
            interface ICreateRelationshipRequest {

                /** CreateRelationshipRequest userId */
                userId?: (string|null);

                /** CreateRelationshipRequest blocked */
                blocked?: (boolean|null);

                /** CreateRelationshipRequest groupIndex */
                groupIndex?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a CreateRelationshipRequest. */
            class CreateRelationshipRequest implements ICreateRelationshipRequest {

                /**
                 * Constructs a new CreateRelationshipRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.ICreateRelationshipRequest);

                /** CreateRelationshipRequest userId. */
                public userId: string;

                /** CreateRelationshipRequest blocked. */
                public blocked: boolean;

                /** CreateRelationshipRequest groupIndex. */
                public groupIndex?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified CreateRelationshipRequest message. Does not implicitly {@link im.turms.proto.CreateRelationshipRequest.verify|verify} messages.
                 * @param message CreateRelationshipRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.ICreateRelationshipRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a CreateRelationshipRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns CreateRelationshipRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.CreateRelationshipRequest;
            }

            /** Properties of a DeleteRelationshipGroupMemberRequest. */
            interface IDeleteRelationshipGroupMemberRequest {

                /** DeleteRelationshipGroupMemberRequest userId */
                userId?: (string|null);

                /** DeleteRelationshipGroupMemberRequest groupIndex */
                groupIndex?: (number|null);

                /** DeleteRelationshipGroupMemberRequest targetGroupIndex */
                targetGroupIndex?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a DeleteRelationshipGroupMemberRequest. */
            class DeleteRelationshipGroupMemberRequest implements IDeleteRelationshipGroupMemberRequest {

                /**
                 * Constructs a new DeleteRelationshipGroupMemberRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteRelationshipGroupMemberRequest);

                /** DeleteRelationshipGroupMemberRequest userId. */
                public userId: string;

                /** DeleteRelationshipGroupMemberRequest groupIndex. */
                public groupIndex: number;

                /** DeleteRelationshipGroupMemberRequest targetGroupIndex. */
                public targetGroupIndex?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified DeleteRelationshipGroupMemberRequest message. Does not implicitly {@link im.turms.proto.DeleteRelationshipGroupMemberRequest.verify|verify} messages.
                 * @param message DeleteRelationshipGroupMemberRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteRelationshipGroupMemberRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteRelationshipGroupMemberRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteRelationshipGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteRelationshipGroupMemberRequest;
            }

            /** Properties of a DeleteRelationshipGroupRequest. */
            interface IDeleteRelationshipGroupRequest {

                /** DeleteRelationshipGroupRequest groupIndex */
                groupIndex?: (number|null);

                /** DeleteRelationshipGroupRequest targetGroupIndex */
                targetGroupIndex?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a DeleteRelationshipGroupRequest. */
            class DeleteRelationshipGroupRequest implements IDeleteRelationshipGroupRequest {

                /**
                 * Constructs a new DeleteRelationshipGroupRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteRelationshipGroupRequest);

                /** DeleteRelationshipGroupRequest groupIndex. */
                public groupIndex: number;

                /** DeleteRelationshipGroupRequest targetGroupIndex. */
                public targetGroupIndex?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified DeleteRelationshipGroupRequest message. Does not implicitly {@link im.turms.proto.DeleteRelationshipGroupRequest.verify|verify} messages.
                 * @param message DeleteRelationshipGroupRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteRelationshipGroupRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteRelationshipGroupRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteRelationshipGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteRelationshipGroupRequest;
            }

            /** Properties of a DeleteRelationshipRequest. */
            interface IDeleteRelationshipRequest {

                /** DeleteRelationshipRequest userId */
                userId?: (string|null);

                /** DeleteRelationshipRequest groupIndex */
                groupIndex?: (google.protobuf.IInt32Value|null);

                /** DeleteRelationshipRequest targetGroupIndex */
                targetGroupIndex?: (google.protobuf.IInt32Value|null);
            }

            /** Represents a DeleteRelationshipRequest. */
            class DeleteRelationshipRequest implements IDeleteRelationshipRequest {

                /**
                 * Constructs a new DeleteRelationshipRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IDeleteRelationshipRequest);

                /** DeleteRelationshipRequest userId. */
                public userId: string;

                /** DeleteRelationshipRequest groupIndex. */
                public groupIndex?: (google.protobuf.IInt32Value|null);

                /** DeleteRelationshipRequest targetGroupIndex. */
                public targetGroupIndex?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified DeleteRelationshipRequest message. Does not implicitly {@link im.turms.proto.DeleteRelationshipRequest.verify|verify} messages.
                 * @param message DeleteRelationshipRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IDeleteRelationshipRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a DeleteRelationshipRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns DeleteRelationshipRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.DeleteRelationshipRequest;
            }

            /** Properties of a QueryFriendRequestsRequest. */
            interface IQueryFriendRequestsRequest {

                /** QueryFriendRequestsRequest areSentByMe */
                areSentByMe?: (boolean|null);

                /** QueryFriendRequestsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryFriendRequestsRequest. */
            class QueryFriendRequestsRequest implements IQueryFriendRequestsRequest {

                /**
                 * Constructs a new QueryFriendRequestsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryFriendRequestsRequest);

                /** QueryFriendRequestsRequest areSentByMe. */
                public areSentByMe: boolean;

                /** QueryFriendRequestsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryFriendRequestsRequest message. Does not implicitly {@link im.turms.proto.QueryFriendRequestsRequest.verify|verify} messages.
                 * @param message QueryFriendRequestsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryFriendRequestsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryFriendRequestsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryFriendRequestsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryFriendRequestsRequest;
            }

            /** Properties of a QueryRelatedUserIdsRequest. */
            interface IQueryRelatedUserIdsRequest {

                /** QueryRelatedUserIdsRequest blocked */
                blocked?: (google.protobuf.IBoolValue|null);

                /** QueryRelatedUserIdsRequest groupIndex */
                groupIndex?: (google.protobuf.IInt32Value|null);

                /** QueryRelatedUserIdsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryRelatedUserIdsRequest. */
            class QueryRelatedUserIdsRequest implements IQueryRelatedUserIdsRequest {

                /**
                 * Constructs a new QueryRelatedUserIdsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryRelatedUserIdsRequest);

                /** QueryRelatedUserIdsRequest blocked. */
                public blocked?: (google.protobuf.IBoolValue|null);

                /** QueryRelatedUserIdsRequest groupIndex. */
                public groupIndex?: (google.protobuf.IInt32Value|null);

                /** QueryRelatedUserIdsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryRelatedUserIdsRequest message. Does not implicitly {@link im.turms.proto.QueryRelatedUserIdsRequest.verify|verify} messages.
                 * @param message QueryRelatedUserIdsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryRelatedUserIdsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryRelatedUserIdsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryRelatedUserIdsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryRelatedUserIdsRequest;
            }

            /** Properties of a QueryRelationshipGroupsRequest. */
            interface IQueryRelationshipGroupsRequest {

                /** QueryRelationshipGroupsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryRelationshipGroupsRequest. */
            class QueryRelationshipGroupsRequest implements IQueryRelationshipGroupsRequest {

                /**
                 * Constructs a new QueryRelationshipGroupsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryRelationshipGroupsRequest);

                /** QueryRelationshipGroupsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryRelationshipGroupsRequest message. Does not implicitly {@link im.turms.proto.QueryRelationshipGroupsRequest.verify|verify} messages.
                 * @param message QueryRelationshipGroupsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryRelationshipGroupsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryRelationshipGroupsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryRelationshipGroupsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryRelationshipGroupsRequest;
            }

            /** Properties of a QueryRelationshipsRequest. */
            interface IQueryRelationshipsRequest {

                /** QueryRelationshipsRequest userIds */
                userIds?: (string[]|null);

                /** QueryRelationshipsRequest blocked */
                blocked?: (google.protobuf.IBoolValue|null);

                /** QueryRelationshipsRequest groupIndex */
                groupIndex?: (google.protobuf.IInt32Value|null);

                /** QueryRelationshipsRequest lastUpdatedDate */
                lastUpdatedDate?: (google.protobuf.IInt64Value|null);
            }

            /** Represents a QueryRelationshipsRequest. */
            class QueryRelationshipsRequest implements IQueryRelationshipsRequest {

                /**
                 * Constructs a new QueryRelationshipsRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IQueryRelationshipsRequest);

                /** QueryRelationshipsRequest userIds. */
                public userIds: string[];

                /** QueryRelationshipsRequest blocked. */
                public blocked?: (google.protobuf.IBoolValue|null);

                /** QueryRelationshipsRequest groupIndex. */
                public groupIndex?: (google.protobuf.IInt32Value|null);

                /** QueryRelationshipsRequest lastUpdatedDate. */
                public lastUpdatedDate?: (google.protobuf.IInt64Value|null);

                /**
                 * Encodes the specified QueryRelationshipsRequest message. Does not implicitly {@link im.turms.proto.QueryRelationshipsRequest.verify|verify} messages.
                 * @param message QueryRelationshipsRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IQueryRelationshipsRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes a QueryRelationshipsRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns QueryRelationshipsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.QueryRelationshipsRequest;
            }

            /** Properties of an UpdateFriendRequestRequest. */
            interface IUpdateFriendRequestRequest {

                /** UpdateFriendRequestRequest requestId */
                requestId?: (string|null);

                /** UpdateFriendRequestRequest responseAction */
                responseAction?: (im.turms.proto.ResponseAction|null);

                /** UpdateFriendRequestRequest reason */
                reason?: (google.protobuf.IStringValue|null);
            }

            /** Represents an UpdateFriendRequestRequest. */
            class UpdateFriendRequestRequest implements IUpdateFriendRequestRequest {

                /**
                 * Constructs a new UpdateFriendRequestRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateFriendRequestRequest);

                /** UpdateFriendRequestRequest requestId. */
                public requestId: string;

                /** UpdateFriendRequestRequest responseAction. */
                public responseAction: im.turms.proto.ResponseAction;

                /** UpdateFriendRequestRequest reason. */
                public reason?: (google.protobuf.IStringValue|null);

                /**
                 * Encodes the specified UpdateFriendRequestRequest message. Does not implicitly {@link im.turms.proto.UpdateFriendRequestRequest.verify|verify} messages.
                 * @param message UpdateFriendRequestRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateFriendRequestRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateFriendRequestRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateFriendRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateFriendRequestRequest;
            }

            /** Properties of an UpdateRelationshipGroupRequest. */
            interface IUpdateRelationshipGroupRequest {

                /** UpdateRelationshipGroupRequest groupIndex */
                groupIndex?: (number|null);

                /** UpdateRelationshipGroupRequest newName */
                newName?: (string|null);
            }

            /** Represents an UpdateRelationshipGroupRequest. */
            class UpdateRelationshipGroupRequest implements IUpdateRelationshipGroupRequest {

                /**
                 * Constructs a new UpdateRelationshipGroupRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateRelationshipGroupRequest);

                /** UpdateRelationshipGroupRequest groupIndex. */
                public groupIndex: number;

                /** UpdateRelationshipGroupRequest newName. */
                public newName: string;

                /**
                 * Encodes the specified UpdateRelationshipGroupRequest message. Does not implicitly {@link im.turms.proto.UpdateRelationshipGroupRequest.verify|verify} messages.
                 * @param message UpdateRelationshipGroupRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateRelationshipGroupRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateRelationshipGroupRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateRelationshipGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateRelationshipGroupRequest;
            }

            /** Properties of an UpdateRelationshipRequest. */
            interface IUpdateRelationshipRequest {

                /** UpdateRelationshipRequest userId */
                userId?: (string|null);

                /** UpdateRelationshipRequest blocked */
                blocked?: (google.protobuf.IBoolValue|null);

                /** UpdateRelationshipRequest newGroupIndex */
                newGroupIndex?: (google.protobuf.IInt32Value|null);

                /** UpdateRelationshipRequest deleteGroupIndex */
                deleteGroupIndex?: (google.protobuf.IInt32Value|null);
            }

            /** Represents an UpdateRelationshipRequest. */
            class UpdateRelationshipRequest implements IUpdateRelationshipRequest {

                /**
                 * Constructs a new UpdateRelationshipRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateRelationshipRequest);

                /** UpdateRelationshipRequest userId. */
                public userId: string;

                /** UpdateRelationshipRequest blocked. */
                public blocked?: (google.protobuf.IBoolValue|null);

                /** UpdateRelationshipRequest newGroupIndex. */
                public newGroupIndex?: (google.protobuf.IInt32Value|null);

                /** UpdateRelationshipRequest deleteGroupIndex. */
                public deleteGroupIndex?: (google.protobuf.IInt32Value|null);

                /**
                 * Encodes the specified UpdateRelationshipRequest message. Does not implicitly {@link im.turms.proto.UpdateRelationshipRequest.verify|verify} messages.
                 * @param message UpdateRelationshipRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateRelationshipRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateRelationshipRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateRelationshipRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateRelationshipRequest;
            }

            /** Properties of an UpdateUserLocationRequest. */
            interface IUpdateUserLocationRequest {

                /** UpdateUserLocationRequest latitude */
                latitude?: (number|null);

                /** UpdateUserLocationRequest longitude */
                longitude?: (number|null);

                /** UpdateUserLocationRequest name */
                name?: (google.protobuf.IStringValue|null);

                /** UpdateUserLocationRequest address */
                address?: (google.protobuf.IStringValue|null);
            }

            /** Represents an UpdateUserLocationRequest. */
            class UpdateUserLocationRequest implements IUpdateUserLocationRequest {

                /**
                 * Constructs a new UpdateUserLocationRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateUserLocationRequest);

                /** UpdateUserLocationRequest latitude. */
                public latitude: number;

                /** UpdateUserLocationRequest longitude. */
                public longitude: number;

                /** UpdateUserLocationRequest name. */
                public name?: (google.protobuf.IStringValue|null);

                /** UpdateUserLocationRequest address. */
                public address?: (google.protobuf.IStringValue|null);

                /**
                 * Encodes the specified UpdateUserLocationRequest message. Does not implicitly {@link im.turms.proto.UpdateUserLocationRequest.verify|verify} messages.
                 * @param message UpdateUserLocationRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateUserLocationRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateUserLocationRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateUserLocationRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateUserLocationRequest;
            }

            /** Properties of an UpdateUserOnlineStatusRequest. */
            interface IUpdateUserOnlineStatusRequest {

                /** UpdateUserOnlineStatusRequest userStatus */
                userStatus?: (im.turms.proto.UserStatus|null);

                /** UpdateUserOnlineStatusRequest deviceTypes */
                deviceTypes?: (im.turms.proto.DeviceType[]|null);
            }

            /** Represents an UpdateUserOnlineStatusRequest. */
            class UpdateUserOnlineStatusRequest implements IUpdateUserOnlineStatusRequest {

                /**
                 * Constructs a new UpdateUserOnlineStatusRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateUserOnlineStatusRequest);

                /** UpdateUserOnlineStatusRequest userStatus. */
                public userStatus: im.turms.proto.UserStatus;

                /** UpdateUserOnlineStatusRequest deviceTypes. */
                public deviceTypes: im.turms.proto.DeviceType[];

                /**
                 * Encodes the specified UpdateUserOnlineStatusRequest message. Does not implicitly {@link im.turms.proto.UpdateUserOnlineStatusRequest.verify|verify} messages.
                 * @param message UpdateUserOnlineStatusRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateUserOnlineStatusRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateUserOnlineStatusRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateUserOnlineStatusRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateUserOnlineStatusRequest;
            }

            /** Properties of an UpdateUserRequest. */
            interface IUpdateUserRequest {

                /** UpdateUserRequest password */
                password?: (google.protobuf.IStringValue|null);

                /** UpdateUserRequest name */
                name?: (google.protobuf.IStringValue|null);

                /** UpdateUserRequest intro */
                intro?: (google.protobuf.IStringValue|null);

                /** UpdateUserRequest profileAccessStrategy */
                profileAccessStrategy?: (im.turms.proto.ProfileAccessStrategy|null);
            }

            /** Represents an UpdateUserRequest. */
            class UpdateUserRequest implements IUpdateUserRequest {

                /**
                 * Constructs a new UpdateUserRequest.
                 * @param [properties] Properties to set
                 */
                constructor(properties?: im.turms.proto.IUpdateUserRequest);

                /** UpdateUserRequest password. */
                public password?: (google.protobuf.IStringValue|null);

                /** UpdateUserRequest name. */
                public name?: (google.protobuf.IStringValue|null);

                /** UpdateUserRequest intro. */
                public intro?: (google.protobuf.IStringValue|null);

                /** UpdateUserRequest profileAccessStrategy. */
                public profileAccessStrategy: im.turms.proto.ProfileAccessStrategy;

                /**
                 * Encodes the specified UpdateUserRequest message. Does not implicitly {@link im.turms.proto.UpdateUserRequest.verify|verify} messages.
                 * @param message UpdateUserRequest message or plain object to encode
                 * @param [writer] Writer to encode to
                 * @returns Writer
                 */
                public static encode(message: im.turms.proto.IUpdateUserRequest, writer?: $protobuf.Writer): $protobuf.Writer;

                /**
                 * Decodes an UpdateUserRequest message from the specified reader or buffer.
                 * @param reader Reader or buffer to decode from
                 * @param [length] Message length if known beforehand
                 * @returns UpdateUserRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): im.turms.proto.UpdateUserRequest;
            }
        }
    }
}

/** Namespace google. */
export namespace google {

    /** Namespace protobuf. */
    namespace protobuf {

        /** Properties of a DoubleValue. */
        interface IDoubleValue {

            /** DoubleValue value */
            value?: (number|null);
        }

        /** Represents a DoubleValue. */
        class DoubleValue implements IDoubleValue {

            /**
             * Constructs a new DoubleValue.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IDoubleValue);

            /** DoubleValue value. */
            public value: number;

            /**
             * Encodes the specified DoubleValue message. Does not implicitly {@link google.protobuf.DoubleValue.verify|verify} messages.
             * @param message DoubleValue message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IDoubleValue, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes a DoubleValue message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns DoubleValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.DoubleValue;
        }

        /** Properties of a FloatValue. */
        interface IFloatValue {

            /** FloatValue value */
            value?: (number|null);
        }

        /** Represents a FloatValue. */
        class FloatValue implements IFloatValue {

            /**
             * Constructs a new FloatValue.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IFloatValue);

            /** FloatValue value. */
            public value: number;

            /**
             * Encodes the specified FloatValue message. Does not implicitly {@link google.protobuf.FloatValue.verify|verify} messages.
             * @param message FloatValue message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IFloatValue, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes a FloatValue message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns FloatValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.FloatValue;
        }

        /** Properties of an Int64Value. */
        interface IInt64Value {

            /** Int64Value value */
            value?: (string|null);
        }

        /** Represents an Int64Value. */
        class Int64Value implements IInt64Value {

            /**
             * Constructs a new Int64Value.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IInt64Value);

            /** Int64Value value. */
            public value: string;

            /**
             * Encodes the specified Int64Value message. Does not implicitly {@link google.protobuf.Int64Value.verify|verify} messages.
             * @param message Int64Value message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IInt64Value, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes an Int64Value message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns Int64Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.Int64Value;
        }

        /** Properties of a UInt64Value. */
        interface IUInt64Value {

            /** UInt64Value value */
            value?: (string|null);
        }

        /** Represents a UInt64Value. */
        class UInt64Value implements IUInt64Value {

            /**
             * Constructs a new UInt64Value.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IUInt64Value);

            /** UInt64Value value. */
            public value: string;

            /**
             * Encodes the specified UInt64Value message. Does not implicitly {@link google.protobuf.UInt64Value.verify|verify} messages.
             * @param message UInt64Value message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IUInt64Value, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes a UInt64Value message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns UInt64Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.UInt64Value;
        }

        /** Properties of an Int32Value. */
        interface IInt32Value {

            /** Int32Value value */
            value?: (number|null);
        }

        /** Represents an Int32Value. */
        class Int32Value implements IInt32Value {

            /**
             * Constructs a new Int32Value.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IInt32Value);

            /** Int32Value value. */
            public value: number;

            /**
             * Encodes the specified Int32Value message. Does not implicitly {@link google.protobuf.Int32Value.verify|verify} messages.
             * @param message Int32Value message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IInt32Value, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes an Int32Value message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns Int32Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.Int32Value;
        }

        /** Properties of a UInt32Value. */
        interface IUInt32Value {

            /** UInt32Value value */
            value?: (number|null);
        }

        /** Represents a UInt32Value. */
        class UInt32Value implements IUInt32Value {

            /**
             * Constructs a new UInt32Value.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IUInt32Value);

            /** UInt32Value value. */
            public value: number;

            /**
             * Encodes the specified UInt32Value message. Does not implicitly {@link google.protobuf.UInt32Value.verify|verify} messages.
             * @param message UInt32Value message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IUInt32Value, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes a UInt32Value message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns UInt32Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.UInt32Value;
        }

        /** Properties of a BoolValue. */
        interface IBoolValue {

            /** BoolValue value */
            value?: (boolean|null);
        }

        /** Represents a BoolValue. */
        class BoolValue implements IBoolValue {

            /**
             * Constructs a new BoolValue.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IBoolValue);

            /** BoolValue value. */
            public value: boolean;

            /**
             * Encodes the specified BoolValue message. Does not implicitly {@link google.protobuf.BoolValue.verify|verify} messages.
             * @param message BoolValue message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IBoolValue, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes a BoolValue message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns BoolValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.BoolValue;
        }

        /** Properties of a StringValue. */
        interface IStringValue {

            /** StringValue value */
            value?: (string|null);
        }

        /** Represents a StringValue. */
        class StringValue implements IStringValue {

            /**
             * Constructs a new StringValue.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IStringValue);

            /** StringValue value. */
            public value: string;

            /**
             * Encodes the specified StringValue message. Does not implicitly {@link google.protobuf.StringValue.verify|verify} messages.
             * @param message StringValue message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IStringValue, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes a StringValue message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns StringValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.StringValue;
        }

        /** Properties of a BytesValue. */
        interface IBytesValue {

            /** BytesValue value */
            value?: (Uint8Array|null);
        }

        /** Represents a BytesValue. */
        class BytesValue implements IBytesValue {

            /**
             * Constructs a new BytesValue.
             * @param [properties] Properties to set
             */
            constructor(properties?: google.protobuf.IBytesValue);

            /** BytesValue value. */
            public value: Uint8Array;

            /**
             * Encodes the specified BytesValue message. Does not implicitly {@link google.protobuf.BytesValue.verify|verify} messages.
             * @param message BytesValue message or plain object to encode
             * @param [writer] Writer to encode to
             * @returns Writer
             */
            public static encode(message: google.protobuf.IBytesValue, writer?: $protobuf.Writer): $protobuf.Writer;

            /**
             * Decodes a BytesValue message from the specified reader or buffer.
             * @param reader Reader or buffer to decode from
             * @param [length] Message length if known beforehand
             * @returns BytesValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): google.protobuf.BytesValue;
        }
    }
}
