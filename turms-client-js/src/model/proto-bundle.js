/*eslint-disable block-scoped-var, id-length, no-control-regex, no-magic-numbers, no-prototype-builtins, no-redeclare, no-shadow, no-var, sort-vars*/
"use strict";

var $protobuf = require("protobufjs/minimal");

// Common aliases
var $Reader = $protobuf.Reader, $Writer = $protobuf.Writer, $util = $protobuf.util;

// Exported root namespace
var $root = $protobuf.roots["default"] || ($protobuf.roots["default"] = {});

$root.im = (function() {

    /**
     * Namespace im.
     * @exports im
     * @namespace
     */
    var im = {};

    im.turms = (function() {

        /**
         * Namespace turms.
         * @memberof im
         * @namespace
         */
        var turms = {};

        turms.proto = (function() {

            /**
             * Namespace proto.
             * @memberof im.turms
             * @namespace
             */
            var proto = {};

            /**
             * ContentType enum.
             * @name im.turms.proto.ContentType
             * @enum {number}
             * @property {number} PROFILE=0 PROFILE value
             * @property {number} GROUP_PROFILE=1 GROUP_PROFILE value
             * @property {number} ATTACHMENT=2 ATTACHMENT value
             */
            proto.ContentType = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "PROFILE"] = 0;
                values[valuesById[1] = "GROUP_PROFILE"] = 1;
                values[valuesById[2] = "ATTACHMENT"] = 2;
                return values;
            })();

            /**
             * DeviceType enum.
             * @name im.turms.proto.DeviceType
             * @enum {number}
             * @property {number} DESKTOP=0 DESKTOP value
             * @property {number} BROWSER=1 BROWSER value
             * @property {number} IOS=2 IOS value
             * @property {number} ANDROID=3 ANDROID value
             * @property {number} OTHERS=4 OTHERS value
             * @property {number} UNKNOWN=5 UNKNOWN value
             */
            proto.DeviceType = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "DESKTOP"] = 0;
                values[valuesById[1] = "BROWSER"] = 1;
                values[valuesById[2] = "IOS"] = 2;
                values[valuesById[3] = "ANDROID"] = 3;
                values[valuesById[4] = "OTHERS"] = 4;
                values[valuesById[5] = "UNKNOWN"] = 5;
                return values;
            })();

            /**
             * GroupMemberRole enum.
             * @name im.turms.proto.GroupMemberRole
             * @enum {number}
             * @property {number} OWNER=0 OWNER value
             * @property {number} MANAGER=1 MANAGER value
             * @property {number} MEMBER=2 MEMBER value
             * @property {number} GUEST=3 GUEST value
             * @property {number} ANONYMOUS_GUEST=4 ANONYMOUS_GUEST value
             */
            proto.GroupMemberRole = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "OWNER"] = 0;
                values[valuesById[1] = "MANAGER"] = 1;
                values[valuesById[2] = "MEMBER"] = 2;
                values[valuesById[3] = "GUEST"] = 3;
                values[valuesById[4] = "ANONYMOUS_GUEST"] = 4;
                return values;
            })();

            /**
             * MessageDeliveryStatus enum.
             * @name im.turms.proto.MessageDeliveryStatus
             * @enum {number}
             * @property {number} READY=0 READY value
             * @property {number} SENDING=1 SENDING value
             * @property {number} RECEIVED=2 RECEIVED value
             * @property {number} RECALLING=3 RECALLING value
             * @property {number} RECALLED=4 RECALLED value
             */
            proto.MessageDeliveryStatus = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "READY"] = 0;
                values[valuesById[1] = "SENDING"] = 1;
                values[valuesById[2] = "RECEIVED"] = 2;
                values[valuesById[3] = "RECALLING"] = 3;
                values[valuesById[4] = "RECALLED"] = 4;
                return values;
            })();

            /**
             * ProfileAccessStrategy enum.
             * @name im.turms.proto.ProfileAccessStrategy
             * @enum {number}
             * @property {number} ALL=0 ALL value
             * @property {number} ALL_EXCEPT_BLACKLISTED_USERS=1 ALL_EXCEPT_BLACKLISTED_USERS value
             * @property {number} FRIENDS=2 FRIENDS value
             */
            proto.ProfileAccessStrategy = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "ALL"] = 0;
                values[valuesById[1] = "ALL_EXCEPT_BLACKLISTED_USERS"] = 1;
                values[valuesById[2] = "FRIENDS"] = 2;
                return values;
            })();

            /**
             * RequestStatus enum.
             * @name im.turms.proto.RequestStatus
             * @enum {number}
             * @property {number} PENDING=0 PENDING value
             * @property {number} ACCEPTED=1 ACCEPTED value
             * @property {number} ACCEPTED_WITHOUT_CONFIRM=2 ACCEPTED_WITHOUT_CONFIRM value
             * @property {number} DECLINED=3 DECLINED value
             * @property {number} IGNORED=4 IGNORED value
             * @property {number} EXPIRED=5 EXPIRED value
             * @property {number} CANCELED=6 CANCELED value
             */
            proto.RequestStatus = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "PENDING"] = 0;
                values[valuesById[1] = "ACCEPTED"] = 1;
                values[valuesById[2] = "ACCEPTED_WITHOUT_CONFIRM"] = 2;
                values[valuesById[3] = "DECLINED"] = 3;
                values[valuesById[4] = "IGNORED"] = 4;
                values[valuesById[5] = "EXPIRED"] = 5;
                values[valuesById[6] = "CANCELED"] = 6;
                return values;
            })();

            /**
             * ResponseAction enum.
             * @name im.turms.proto.ResponseAction
             * @enum {number}
             * @property {number} ACCEPT=0 ACCEPT value
             * @property {number} DECLINE=1 DECLINE value
             * @property {number} IGNORE=2 IGNORE value
             */
            proto.ResponseAction = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "ACCEPT"] = 0;
                values[valuesById[1] = "DECLINE"] = 1;
                values[valuesById[2] = "IGNORE"] = 2;
                return values;
            })();

            /**
             * UserStatus enum.
             * @name im.turms.proto.UserStatus
             * @enum {number}
             * @property {number} AVAILABLE=0 AVAILABLE value
             * @property {number} OFFLINE=1 OFFLINE value
             * @property {number} INVISIBLE=2 INVISIBLE value
             * @property {number} BUSY=3 BUSY value
             * @property {number} DO_NOT_DISTURB=4 DO_NOT_DISTURB value
             * @property {number} AWAY=5 AWAY value
             * @property {number} BE_RIGHT_BACK=6 BE_RIGHT_BACK value
             */
            proto.UserStatus = (function() {
                var valuesById = {}, values = Object.create(valuesById);
                values[valuesById[0] = "AVAILABLE"] = 0;
                values[valuesById[1] = "OFFLINE"] = 1;
                values[valuesById[2] = "INVISIBLE"] = 2;
                values[valuesById[3] = "BUSY"] = 3;
                values[valuesById[4] = "DO_NOT_DISTURB"] = 4;
                values[valuesById[5] = "AWAY"] = 5;
                values[valuesById[6] = "BE_RIGHT_BACK"] = 6;
                return values;
            })();

            proto.Int64ValuesWithVersion = (function() {

                /**
                 * Properties of an Int64ValuesWithVersion.
                 * @memberof im.turms.proto
                 * @interface IInt64ValuesWithVersion
                 * @property {Array.<string>|null} [values] Int64ValuesWithVersion values
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] Int64ValuesWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new Int64ValuesWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents an Int64ValuesWithVersion.
                 * @implements IInt64ValuesWithVersion
                 * @constructor
                 * @param {im.turms.proto.IInt64ValuesWithVersion=} [properties] Properties to set
                 */
                function Int64ValuesWithVersion(properties) {
                    this.values = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Int64ValuesWithVersion values.
                 * @member {Array.<string>} values
                 * @memberof im.turms.proto.Int64ValuesWithVersion
                 * @instance
                 */
                Int64ValuesWithVersion.prototype.values = $util.emptyArray;

                /**
                 * Int64ValuesWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.Int64ValuesWithVersion
                 * @instance
                 */
                Int64ValuesWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified Int64ValuesWithVersion message. Does not implicitly {@link im.turms.proto.Int64ValuesWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.Int64ValuesWithVersion
                 * @static
                 * @param {im.turms.proto.IInt64ValuesWithVersion} message Int64ValuesWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                Int64ValuesWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.values != null && message.values.length) {
                        writer.uint32(/* id 1, wireType 2 =*/10).fork();
                        for (var i = 0; i < message.values.length; ++i)
                            writer.int64(message.values[i]);
                        writer.ldelim();
                    }
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an Int64ValuesWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.Int64ValuesWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.Int64ValuesWithVersion} Int64ValuesWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                Int64ValuesWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.Int64ValuesWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.values && message.values.length))
                                message.values = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.values.push(reader.int64().toString());
                            } else
                                message.values.push(reader.int64().toString());
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return Int64ValuesWithVersion;
            })();

            proto.Int64Values = (function() {

                /**
                 * Properties of an Int64Values.
                 * @memberof im.turms.proto
                 * @interface IInt64Values
                 * @property {Array.<string>|null} [values] Int64Values values
                 */

                /**
                 * Constructs a new Int64Values.
                 * @memberof im.turms.proto
                 * @classdesc Represents an Int64Values.
                 * @implements IInt64Values
                 * @constructor
                 * @param {im.turms.proto.IInt64Values=} [properties] Properties to set
                 */
                function Int64Values(properties) {
                    this.values = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Int64Values values.
                 * @member {Array.<string>} values
                 * @memberof im.turms.proto.Int64Values
                 * @instance
                 */
                Int64Values.prototype.values = $util.emptyArray;

                /**
                 * Encodes the specified Int64Values message. Does not implicitly {@link im.turms.proto.Int64Values.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.Int64Values
                 * @static
                 * @param {im.turms.proto.IInt64Values} message Int64Values message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                Int64Values.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.values != null && message.values.length) {
                        writer.uint32(/* id 1, wireType 2 =*/10).fork();
                        for (var i = 0; i < message.values.length; ++i)
                            writer.int64(message.values[i]);
                        writer.ldelim();
                    }
                    return writer;
                };

                /**
                 * Decodes an Int64Values message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.Int64Values
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.Int64Values} Int64Values
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                Int64Values.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.Int64Values();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.values && message.values.length))
                                message.values = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.values.push(reader.int64().toString());
                            } else
                                message.values.push(reader.int64().toString());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return Int64Values;
            })();

            proto.AudioFile = (function() {

                /**
                 * Properties of an AudioFile.
                 * @memberof im.turms.proto
                 * @interface IAudioFile
                 * @property {im.turms.proto.AudioFile.IDescription|null} [description] AudioFile description
                 * @property {google.protobuf.IBytesValue|null} [data] AudioFile data
                 */

                /**
                 * Constructs a new AudioFile.
                 * @memberof im.turms.proto
                 * @classdesc Represents an AudioFile.
                 * @implements IAudioFile
                 * @constructor
                 * @param {im.turms.proto.IAudioFile=} [properties] Properties to set
                 */
                function AudioFile(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * AudioFile description.
                 * @member {im.turms.proto.AudioFile.IDescription|null|undefined} description
                 * @memberof im.turms.proto.AudioFile
                 * @instance
                 */
                AudioFile.prototype.description = null;

                /**
                 * AudioFile data.
                 * @member {google.protobuf.IBytesValue|null|undefined} data
                 * @memberof im.turms.proto.AudioFile
                 * @instance
                 */
                AudioFile.prototype.data = null;

                /**
                 * Encodes the specified AudioFile message. Does not implicitly {@link im.turms.proto.AudioFile.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.AudioFile
                 * @static
                 * @param {im.turms.proto.IAudioFile} message AudioFile message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                AudioFile.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.description != null && Object.hasOwnProperty.call(message, "description"))
                        $root.im.turms.proto.AudioFile.Description.encode(message.description, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.data != null && Object.hasOwnProperty.call(message, "data"))
                        $root.google.protobuf.BytesValue.encode(message.data, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an AudioFile message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.AudioFile
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.AudioFile} AudioFile
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                AudioFile.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.AudioFile();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.description = $root.im.turms.proto.AudioFile.Description.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.data = $root.google.protobuf.BytesValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                AudioFile.Description = (function() {

                    /**
                     * Properties of a Description.
                     * @memberof im.turms.proto.AudioFile
                     * @interface IDescription
                     * @property {string|null} [url] Description url
                     * @property {google.protobuf.IInt32Value|null} [duration] Description duration
                     * @property {google.protobuf.IInt32Value|null} [size] Description size
                     * @property {google.protobuf.IStringValue|null} [format] Description format
                     */

                    /**
                     * Constructs a new Description.
                     * @memberof im.turms.proto.AudioFile
                     * @classdesc Represents a Description.
                     * @implements IDescription
                     * @constructor
                     * @param {im.turms.proto.AudioFile.IDescription=} [properties] Properties to set
                     */
                    function Description(properties) {
                        if (properties)
                            for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                                if (properties[keys[i]] != null)
                                    this[keys[i]] = properties[keys[i]];
                    }

                    /**
                     * Description url.
                     * @member {string} url
                     * @memberof im.turms.proto.AudioFile.Description
                     * @instance
                     */
                    Description.prototype.url = "";

                    /**
                     * Description duration.
                     * @member {google.protobuf.IInt32Value|null|undefined} duration
                     * @memberof im.turms.proto.AudioFile.Description
                     * @instance
                     */
                    Description.prototype.duration = null;

                    /**
                     * Description size.
                     * @member {google.protobuf.IInt32Value|null|undefined} size
                     * @memberof im.turms.proto.AudioFile.Description
                     * @instance
                     */
                    Description.prototype.size = null;

                    /**
                     * Description format.
                     * @member {google.protobuf.IStringValue|null|undefined} format
                     * @memberof im.turms.proto.AudioFile.Description
                     * @instance
                     */
                    Description.prototype.format = null;

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.AudioFile.Description.verify|verify} messages.
                     * @function encode
                     * @memberof im.turms.proto.AudioFile.Description
                     * @static
                     * @param {im.turms.proto.AudioFile.IDescription} message Description message or plain object to encode
                     * @param {$protobuf.Writer} [writer] Writer to encode to
                     * @returns {$protobuf.Writer} Writer
                     */
                    Description.encode = function encode(message, writer) {
                        if (!writer)
                            writer = $Writer.create();
                        if (message.url != null && Object.hasOwnProperty.call(message, "url"))
                            writer.uint32(/* id 1, wireType 2 =*/10).string(message.url);
                        if (message.duration != null && Object.hasOwnProperty.call(message, "duration"))
                            $root.google.protobuf.Int32Value.encode(message.duration, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                        if (message.size != null && Object.hasOwnProperty.call(message, "size"))
                            $root.google.protobuf.Int32Value.encode(message.size, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                        if (message.format != null && Object.hasOwnProperty.call(message, "format"))
                            $root.google.protobuf.StringValue.encode(message.format, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                        return writer;
                    };

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @function decode
                     * @memberof im.turms.proto.AudioFile.Description
                     * @static
                     * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                     * @param {number} [length] Message length if known beforehand
                     * @returns {im.turms.proto.AudioFile.Description} Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    Description.decode = function decode(reader, length) {
                        if (!(reader instanceof $Reader))
                            reader = $Reader.create(reader);
                        var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.AudioFile.Description();
                        while (reader.pos < end) {
                            var tag = reader.uint32();
                            switch (tag >>> 3) {
                            case 1:
                                message.url = reader.string();
                                break;
                            case 2:
                                message.duration = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                                break;
                            case 3:
                                message.size = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                                break;
                            case 4:
                                message.format = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                                break;
                            default:
                                reader.skipType(tag & 7);
                                break;
                            }
                        }
                        return message;
                    };

                    return Description;
                })();

                return AudioFile;
            })();

            proto.File = (function() {

                /**
                 * Properties of a File.
                 * @memberof im.turms.proto
                 * @interface IFile
                 * @property {im.turms.proto.File.IDescription|null} [description] File description
                 * @property {google.protobuf.IBytesValue|null} [data] File data
                 */

                /**
                 * Constructs a new File.
                 * @memberof im.turms.proto
                 * @classdesc Represents a File.
                 * @implements IFile
                 * @constructor
                 * @param {im.turms.proto.IFile=} [properties] Properties to set
                 */
                function File(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * File description.
                 * @member {im.turms.proto.File.IDescription|null|undefined} description
                 * @memberof im.turms.proto.File
                 * @instance
                 */
                File.prototype.description = null;

                /**
                 * File data.
                 * @member {google.protobuf.IBytesValue|null|undefined} data
                 * @memberof im.turms.proto.File
                 * @instance
                 */
                File.prototype.data = null;

                /**
                 * Encodes the specified File message. Does not implicitly {@link im.turms.proto.File.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.File
                 * @static
                 * @param {im.turms.proto.IFile} message File message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                File.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.description != null && Object.hasOwnProperty.call(message, "description"))
                        $root.im.turms.proto.File.Description.encode(message.description, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.data != null && Object.hasOwnProperty.call(message, "data"))
                        $root.google.protobuf.BytesValue.encode(message.data, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a File message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.File
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.File} File
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                File.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.File();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.description = $root.im.turms.proto.File.Description.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.data = $root.google.protobuf.BytesValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                File.Description = (function() {

                    /**
                     * Properties of a Description.
                     * @memberof im.turms.proto.File
                     * @interface IDescription
                     * @property {string|null} [url] Description url
                     * @property {google.protobuf.IInt32Value|null} [size] Description size
                     * @property {google.protobuf.IStringValue|null} [format] Description format
                     */

                    /**
                     * Constructs a new Description.
                     * @memberof im.turms.proto.File
                     * @classdesc Represents a Description.
                     * @implements IDescription
                     * @constructor
                     * @param {im.turms.proto.File.IDescription=} [properties] Properties to set
                     */
                    function Description(properties) {
                        if (properties)
                            for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                                if (properties[keys[i]] != null)
                                    this[keys[i]] = properties[keys[i]];
                    }

                    /**
                     * Description url.
                     * @member {string} url
                     * @memberof im.turms.proto.File.Description
                     * @instance
                     */
                    Description.prototype.url = "";

                    /**
                     * Description size.
                     * @member {google.protobuf.IInt32Value|null|undefined} size
                     * @memberof im.turms.proto.File.Description
                     * @instance
                     */
                    Description.prototype.size = null;

                    /**
                     * Description format.
                     * @member {google.protobuf.IStringValue|null|undefined} format
                     * @memberof im.turms.proto.File.Description
                     * @instance
                     */
                    Description.prototype.format = null;

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.File.Description.verify|verify} messages.
                     * @function encode
                     * @memberof im.turms.proto.File.Description
                     * @static
                     * @param {im.turms.proto.File.IDescription} message Description message or plain object to encode
                     * @param {$protobuf.Writer} [writer] Writer to encode to
                     * @returns {$protobuf.Writer} Writer
                     */
                    Description.encode = function encode(message, writer) {
                        if (!writer)
                            writer = $Writer.create();
                        if (message.url != null && Object.hasOwnProperty.call(message, "url"))
                            writer.uint32(/* id 1, wireType 2 =*/10).string(message.url);
                        if (message.size != null && Object.hasOwnProperty.call(message, "size"))
                            $root.google.protobuf.Int32Value.encode(message.size, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                        if (message.format != null && Object.hasOwnProperty.call(message, "format"))
                            $root.google.protobuf.StringValue.encode(message.format, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                        return writer;
                    };

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @function decode
                     * @memberof im.turms.proto.File.Description
                     * @static
                     * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                     * @param {number} [length] Message length if known beforehand
                     * @returns {im.turms.proto.File.Description} Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    Description.decode = function decode(reader, length) {
                        if (!(reader instanceof $Reader))
                            reader = $Reader.create(reader);
                        var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.File.Description();
                        while (reader.pos < end) {
                            var tag = reader.uint32();
                            switch (tag >>> 3) {
                            case 1:
                                message.url = reader.string();
                                break;
                            case 2:
                                message.size = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                                break;
                            case 3:
                                message.format = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                                break;
                            default:
                                reader.skipType(tag & 7);
                                break;
                            }
                        }
                        return message;
                    };

                    return Description;
                })();

                return File;
            })();

            proto.ImageFile = (function() {

                /**
                 * Properties of an ImageFile.
                 * @memberof im.turms.proto
                 * @interface IImageFile
                 * @property {im.turms.proto.ImageFile.IDescription|null} [description] ImageFile description
                 * @property {google.protobuf.IBytesValue|null} [data] ImageFile data
                 */

                /**
                 * Constructs a new ImageFile.
                 * @memberof im.turms.proto
                 * @classdesc Represents an ImageFile.
                 * @implements IImageFile
                 * @constructor
                 * @param {im.turms.proto.IImageFile=} [properties] Properties to set
                 */
                function ImageFile(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * ImageFile description.
                 * @member {im.turms.proto.ImageFile.IDescription|null|undefined} description
                 * @memberof im.turms.proto.ImageFile
                 * @instance
                 */
                ImageFile.prototype.description = null;

                /**
                 * ImageFile data.
                 * @member {google.protobuf.IBytesValue|null|undefined} data
                 * @memberof im.turms.proto.ImageFile
                 * @instance
                 */
                ImageFile.prototype.data = null;

                /**
                 * Encodes the specified ImageFile message. Does not implicitly {@link im.turms.proto.ImageFile.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.ImageFile
                 * @static
                 * @param {im.turms.proto.IImageFile} message ImageFile message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                ImageFile.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.description != null && Object.hasOwnProperty.call(message, "description"))
                        $root.im.turms.proto.ImageFile.Description.encode(message.description, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.data != null && Object.hasOwnProperty.call(message, "data"))
                        $root.google.protobuf.BytesValue.encode(message.data, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an ImageFile message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.ImageFile
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.ImageFile} ImageFile
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                ImageFile.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.ImageFile();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.description = $root.im.turms.proto.ImageFile.Description.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.data = $root.google.protobuf.BytesValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                ImageFile.Description = (function() {

                    /**
                     * Properties of a Description.
                     * @memberof im.turms.proto.ImageFile
                     * @interface IDescription
                     * @property {string|null} [url] Description url
                     * @property {google.protobuf.IBoolValue|null} [original] Description original
                     * @property {google.protobuf.IInt32Value|null} [imageSize] Description imageSize
                     * @property {google.protobuf.IInt32Value|null} [fileSize] Description fileSize
                     */

                    /**
                     * Constructs a new Description.
                     * @memberof im.turms.proto.ImageFile
                     * @classdesc Represents a Description.
                     * @implements IDescription
                     * @constructor
                     * @param {im.turms.proto.ImageFile.IDescription=} [properties] Properties to set
                     */
                    function Description(properties) {
                        if (properties)
                            for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                                if (properties[keys[i]] != null)
                                    this[keys[i]] = properties[keys[i]];
                    }

                    /**
                     * Description url.
                     * @member {string} url
                     * @memberof im.turms.proto.ImageFile.Description
                     * @instance
                     */
                    Description.prototype.url = "";

                    /**
                     * Description original.
                     * @member {google.protobuf.IBoolValue|null|undefined} original
                     * @memberof im.turms.proto.ImageFile.Description
                     * @instance
                     */
                    Description.prototype.original = null;

                    /**
                     * Description imageSize.
                     * @member {google.protobuf.IInt32Value|null|undefined} imageSize
                     * @memberof im.turms.proto.ImageFile.Description
                     * @instance
                     */
                    Description.prototype.imageSize = null;

                    /**
                     * Description fileSize.
                     * @member {google.protobuf.IInt32Value|null|undefined} fileSize
                     * @memberof im.turms.proto.ImageFile.Description
                     * @instance
                     */
                    Description.prototype.fileSize = null;

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.ImageFile.Description.verify|verify} messages.
                     * @function encode
                     * @memberof im.turms.proto.ImageFile.Description
                     * @static
                     * @param {im.turms.proto.ImageFile.IDescription} message Description message or plain object to encode
                     * @param {$protobuf.Writer} [writer] Writer to encode to
                     * @returns {$protobuf.Writer} Writer
                     */
                    Description.encode = function encode(message, writer) {
                        if (!writer)
                            writer = $Writer.create();
                        if (message.url != null && Object.hasOwnProperty.call(message, "url"))
                            writer.uint32(/* id 1, wireType 2 =*/10).string(message.url);
                        if (message.original != null && Object.hasOwnProperty.call(message, "original"))
                            $root.google.protobuf.BoolValue.encode(message.original, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                        if (message.imageSize != null && Object.hasOwnProperty.call(message, "imageSize"))
                            $root.google.protobuf.Int32Value.encode(message.imageSize, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                        if (message.fileSize != null && Object.hasOwnProperty.call(message, "fileSize"))
                            $root.google.protobuf.Int32Value.encode(message.fileSize, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                        return writer;
                    };

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @function decode
                     * @memberof im.turms.proto.ImageFile.Description
                     * @static
                     * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                     * @param {number} [length] Message length if known beforehand
                     * @returns {im.turms.proto.ImageFile.Description} Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    Description.decode = function decode(reader, length) {
                        if (!(reader instanceof $Reader))
                            reader = $Reader.create(reader);
                        var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.ImageFile.Description();
                        while (reader.pos < end) {
                            var tag = reader.uint32();
                            switch (tag >>> 3) {
                            case 1:
                                message.url = reader.string();
                                break;
                            case 2:
                                message.original = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                                break;
                            case 3:
                                message.imageSize = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                                break;
                            case 4:
                                message.fileSize = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                                break;
                            default:
                                reader.skipType(tag & 7);
                                break;
                            }
                        }
                        return message;
                    };

                    return Description;
                })();

                return ImageFile;
            })();

            proto.VideoFile = (function() {

                /**
                 * Properties of a VideoFile.
                 * @memberof im.turms.proto
                 * @interface IVideoFile
                 * @property {im.turms.proto.VideoFile.IDescription|null} [description] VideoFile description
                 * @property {google.protobuf.IBytesValue|null} [data] VideoFile data
                 */

                /**
                 * Constructs a new VideoFile.
                 * @memberof im.turms.proto
                 * @classdesc Represents a VideoFile.
                 * @implements IVideoFile
                 * @constructor
                 * @param {im.turms.proto.IVideoFile=} [properties] Properties to set
                 */
                function VideoFile(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * VideoFile description.
                 * @member {im.turms.proto.VideoFile.IDescription|null|undefined} description
                 * @memberof im.turms.proto.VideoFile
                 * @instance
                 */
                VideoFile.prototype.description = null;

                /**
                 * VideoFile data.
                 * @member {google.protobuf.IBytesValue|null|undefined} data
                 * @memberof im.turms.proto.VideoFile
                 * @instance
                 */
                VideoFile.prototype.data = null;

                /**
                 * Encodes the specified VideoFile message. Does not implicitly {@link im.turms.proto.VideoFile.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.VideoFile
                 * @static
                 * @param {im.turms.proto.IVideoFile} message VideoFile message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                VideoFile.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.description != null && Object.hasOwnProperty.call(message, "description"))
                        $root.im.turms.proto.VideoFile.Description.encode(message.description, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.data != null && Object.hasOwnProperty.call(message, "data"))
                        $root.google.protobuf.BytesValue.encode(message.data, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a VideoFile message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.VideoFile
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.VideoFile} VideoFile
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                VideoFile.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.VideoFile();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.description = $root.im.turms.proto.VideoFile.Description.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.data = $root.google.protobuf.BytesValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                VideoFile.Description = (function() {

                    /**
                     * Properties of a Description.
                     * @memberof im.turms.proto.VideoFile
                     * @interface IDescription
                     * @property {string|null} [url] Description url
                     * @property {google.protobuf.IInt32Value|null} [duration] Description duration
                     * @property {google.protobuf.IInt32Value|null} [size] Description size
                     * @property {google.protobuf.IStringValue|null} [format] Description format
                     */

                    /**
                     * Constructs a new Description.
                     * @memberof im.turms.proto.VideoFile
                     * @classdesc Represents a Description.
                     * @implements IDescription
                     * @constructor
                     * @param {im.turms.proto.VideoFile.IDescription=} [properties] Properties to set
                     */
                    function Description(properties) {
                        if (properties)
                            for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                                if (properties[keys[i]] != null)
                                    this[keys[i]] = properties[keys[i]];
                    }

                    /**
                     * Description url.
                     * @member {string} url
                     * @memberof im.turms.proto.VideoFile.Description
                     * @instance
                     */
                    Description.prototype.url = "";

                    /**
                     * Description duration.
                     * @member {google.protobuf.IInt32Value|null|undefined} duration
                     * @memberof im.turms.proto.VideoFile.Description
                     * @instance
                     */
                    Description.prototype.duration = null;

                    /**
                     * Description size.
                     * @member {google.protobuf.IInt32Value|null|undefined} size
                     * @memberof im.turms.proto.VideoFile.Description
                     * @instance
                     */
                    Description.prototype.size = null;

                    /**
                     * Description format.
                     * @member {google.protobuf.IStringValue|null|undefined} format
                     * @memberof im.turms.proto.VideoFile.Description
                     * @instance
                     */
                    Description.prototype.format = null;

                    /**
                     * Encodes the specified Description message. Does not implicitly {@link im.turms.proto.VideoFile.Description.verify|verify} messages.
                     * @function encode
                     * @memberof im.turms.proto.VideoFile.Description
                     * @static
                     * @param {im.turms.proto.VideoFile.IDescription} message Description message or plain object to encode
                     * @param {$protobuf.Writer} [writer] Writer to encode to
                     * @returns {$protobuf.Writer} Writer
                     */
                    Description.encode = function encode(message, writer) {
                        if (!writer)
                            writer = $Writer.create();
                        if (message.url != null && Object.hasOwnProperty.call(message, "url"))
                            writer.uint32(/* id 1, wireType 2 =*/10).string(message.url);
                        if (message.duration != null && Object.hasOwnProperty.call(message, "duration"))
                            $root.google.protobuf.Int32Value.encode(message.duration, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                        if (message.size != null && Object.hasOwnProperty.call(message, "size"))
                            $root.google.protobuf.Int32Value.encode(message.size, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                        if (message.format != null && Object.hasOwnProperty.call(message, "format"))
                            $root.google.protobuf.StringValue.encode(message.format, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                        return writer;
                    };

                    /**
                     * Decodes a Description message from the specified reader or buffer.
                     * @function decode
                     * @memberof im.turms.proto.VideoFile.Description
                     * @static
                     * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                     * @param {number} [length] Message length if known beforehand
                     * @returns {im.turms.proto.VideoFile.Description} Description
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    Description.decode = function decode(reader, length) {
                        if (!(reader instanceof $Reader))
                            reader = $Reader.create(reader);
                        var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.VideoFile.Description();
                        while (reader.pos < end) {
                            var tag = reader.uint32();
                            switch (tag >>> 3) {
                            case 1:
                                message.url = reader.string();
                                break;
                            case 2:
                                message.duration = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                                break;
                            case 3:
                                message.size = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                                break;
                            case 4:
                                message.format = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                                break;
                            default:
                                reader.skipType(tag & 7);
                                break;
                            }
                        }
                        return message;
                    };

                    return Description;
                })();

                return VideoFile;
            })();

            proto.GroupInvitation = (function() {

                /**
                 * Properties of a GroupInvitation.
                 * @memberof im.turms.proto
                 * @interface IGroupInvitation
                 * @property {google.protobuf.IInt64Value|null} [id] GroupInvitation id
                 * @property {google.protobuf.IInt64Value|null} [creationDate] GroupInvitation creationDate
                 * @property {google.protobuf.IStringValue|null} [content] GroupInvitation content
                 * @property {im.turms.proto.RequestStatus|null} [status] GroupInvitation status
                 * @property {google.protobuf.IInt64Value|null} [expirationDate] GroupInvitation expirationDate
                 * @property {google.protobuf.IInt64Value|null} [groupId] GroupInvitation groupId
                 * @property {google.protobuf.IInt64Value|null} [inviterId] GroupInvitation inviterId
                 * @property {google.protobuf.IInt64Value|null} [inviteeId] GroupInvitation inviteeId
                 */

                /**
                 * Constructs a new GroupInvitation.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupInvitation.
                 * @implements IGroupInvitation
                 * @constructor
                 * @param {im.turms.proto.IGroupInvitation=} [properties] Properties to set
                 */
                function GroupInvitation(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupInvitation id.
                 * @member {google.protobuf.IInt64Value|null|undefined} id
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.id = null;

                /**
                 * GroupInvitation creationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} creationDate
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.creationDate = null;

                /**
                 * GroupInvitation content.
                 * @member {google.protobuf.IStringValue|null|undefined} content
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.content = null;

                /**
                 * GroupInvitation status.
                 * @member {im.turms.proto.RequestStatus} status
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.status = 0;

                /**
                 * GroupInvitation expirationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} expirationDate
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.expirationDate = null;

                /**
                 * GroupInvitation groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.groupId = null;

                /**
                 * GroupInvitation inviterId.
                 * @member {google.protobuf.IInt64Value|null|undefined} inviterId
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.inviterId = null;

                /**
                 * GroupInvitation inviteeId.
                 * @member {google.protobuf.IInt64Value|null|undefined} inviteeId
                 * @memberof im.turms.proto.GroupInvitation
                 * @instance
                 */
                GroupInvitation.prototype.inviteeId = null;

                /**
                 * Encodes the specified GroupInvitation message. Does not implicitly {@link im.turms.proto.GroupInvitation.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupInvitation
                 * @static
                 * @param {im.turms.proto.IGroupInvitation} message GroupInvitation message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupInvitation.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.id != null && Object.hasOwnProperty.call(message, "id"))
                        $root.google.protobuf.Int64Value.encode(message.id, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.creationDate != null && Object.hasOwnProperty.call(message, "creationDate"))
                        $root.google.protobuf.Int64Value.encode(message.creationDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.content != null && Object.hasOwnProperty.call(message, "content"))
                        $root.google.protobuf.StringValue.encode(message.content, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.status != null && Object.hasOwnProperty.call(message, "status"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.status);
                    if (message.expirationDate != null && Object.hasOwnProperty.call(message, "expirationDate"))
                        $root.google.protobuf.Int64Value.encode(message.expirationDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.inviterId != null && Object.hasOwnProperty.call(message, "inviterId"))
                        $root.google.protobuf.Int64Value.encode(message.inviterId, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.inviteeId != null && Object.hasOwnProperty.call(message, "inviteeId"))
                        $root.google.protobuf.Int64Value.encode(message.inviteeId, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupInvitation message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupInvitation
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupInvitation} GroupInvitation
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupInvitation.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupInvitation();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.id = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.creationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.content = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.status = reader.int32();
                            break;
                        case 5:
                            message.expirationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.inviterId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.inviteeId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupInvitation;
            })();

            proto.GroupInvitationsWithVersion = (function() {

                /**
                 * Properties of a GroupInvitationsWithVersion.
                 * @memberof im.turms.proto
                 * @interface IGroupInvitationsWithVersion
                 * @property {Array.<im.turms.proto.IGroupInvitation>|null} [groupInvitations] GroupInvitationsWithVersion groupInvitations
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] GroupInvitationsWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new GroupInvitationsWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupInvitationsWithVersion.
                 * @implements IGroupInvitationsWithVersion
                 * @constructor
                 * @param {im.turms.proto.IGroupInvitationsWithVersion=} [properties] Properties to set
                 */
                function GroupInvitationsWithVersion(properties) {
                    this.groupInvitations = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupInvitationsWithVersion groupInvitations.
                 * @member {Array.<im.turms.proto.IGroupInvitation>} groupInvitations
                 * @memberof im.turms.proto.GroupInvitationsWithVersion
                 * @instance
                 */
                GroupInvitationsWithVersion.prototype.groupInvitations = $util.emptyArray;

                /**
                 * GroupInvitationsWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.GroupInvitationsWithVersion
                 * @instance
                 */
                GroupInvitationsWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified GroupInvitationsWithVersion message. Does not implicitly {@link im.turms.proto.GroupInvitationsWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupInvitationsWithVersion
                 * @static
                 * @param {im.turms.proto.IGroupInvitationsWithVersion} message GroupInvitationsWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupInvitationsWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupInvitations != null && message.groupInvitations.length)
                        for (var i = 0; i < message.groupInvitations.length; ++i)
                            $root.im.turms.proto.GroupInvitation.encode(message.groupInvitations[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupInvitationsWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupInvitationsWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupInvitationsWithVersion} GroupInvitationsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupInvitationsWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupInvitationsWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.groupInvitations && message.groupInvitations.length))
                                message.groupInvitations = [];
                            message.groupInvitations.push($root.im.turms.proto.GroupInvitation.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupInvitationsWithVersion;
            })();

            proto.GroupJoinQuestion = (function() {

                /**
                 * Properties of a GroupJoinQuestion.
                 * @memberof im.turms.proto
                 * @interface IGroupJoinQuestion
                 * @property {google.protobuf.IInt64Value|null} [id] GroupJoinQuestion id
                 * @property {google.protobuf.IInt64Value|null} [groupId] GroupJoinQuestion groupId
                 * @property {google.protobuf.IStringValue|null} [question] GroupJoinQuestion question
                 * @property {Array.<string>|null} [answers] GroupJoinQuestion answers
                 * @property {google.protobuf.IInt32Value|null} [score] GroupJoinQuestion score
                 */

                /**
                 * Constructs a new GroupJoinQuestion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupJoinQuestion.
                 * @implements IGroupJoinQuestion
                 * @constructor
                 * @param {im.turms.proto.IGroupJoinQuestion=} [properties] Properties to set
                 */
                function GroupJoinQuestion(properties) {
                    this.answers = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupJoinQuestion id.
                 * @member {google.protobuf.IInt64Value|null|undefined} id
                 * @memberof im.turms.proto.GroupJoinQuestion
                 * @instance
                 */
                GroupJoinQuestion.prototype.id = null;

                /**
                 * GroupJoinQuestion groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.GroupJoinQuestion
                 * @instance
                 */
                GroupJoinQuestion.prototype.groupId = null;

                /**
                 * GroupJoinQuestion question.
                 * @member {google.protobuf.IStringValue|null|undefined} question
                 * @memberof im.turms.proto.GroupJoinQuestion
                 * @instance
                 */
                GroupJoinQuestion.prototype.question = null;

                /**
                 * GroupJoinQuestion answers.
                 * @member {Array.<string>} answers
                 * @memberof im.turms.proto.GroupJoinQuestion
                 * @instance
                 */
                GroupJoinQuestion.prototype.answers = $util.emptyArray;

                /**
                 * GroupJoinQuestion score.
                 * @member {google.protobuf.IInt32Value|null|undefined} score
                 * @memberof im.turms.proto.GroupJoinQuestion
                 * @instance
                 */
                GroupJoinQuestion.prototype.score = null;

                /**
                 * Encodes the specified GroupJoinQuestion message. Does not implicitly {@link im.turms.proto.GroupJoinQuestion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupJoinQuestion
                 * @static
                 * @param {im.turms.proto.IGroupJoinQuestion} message GroupJoinQuestion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupJoinQuestion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.id != null && Object.hasOwnProperty.call(message, "id"))
                        $root.google.protobuf.Int64Value.encode(message.id, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.question != null && Object.hasOwnProperty.call(message, "question"))
                        $root.google.protobuf.StringValue.encode(message.question, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.answers != null && message.answers.length)
                        for (var i = 0; i < message.answers.length; ++i)
                            writer.uint32(/* id 4, wireType 2 =*/34).string(message.answers[i]);
                    if (message.score != null && Object.hasOwnProperty.call(message, "score"))
                        $root.google.protobuf.Int32Value.encode(message.score, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupJoinQuestion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupJoinQuestion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupJoinQuestion} GroupJoinQuestion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupJoinQuestion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupJoinQuestion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.id = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.question = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            if (!(message.answers && message.answers.length))
                                message.answers = [];
                            message.answers.push(reader.string());
                            break;
                        case 5:
                            message.score = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupJoinQuestion;
            })();

            proto.GroupJoinQuestionsAnswerResult = (function() {

                /**
                 * Properties of a GroupJoinQuestionsAnswerResult.
                 * @memberof im.turms.proto
                 * @interface IGroupJoinQuestionsAnswerResult
                 * @property {number|null} [score] GroupJoinQuestionsAnswerResult score
                 * @property {Array.<string>|null} [questionIds] GroupJoinQuestionsAnswerResult questionIds
                 * @property {boolean|null} [joined] GroupJoinQuestionsAnswerResult joined
                 */

                /**
                 * Constructs a new GroupJoinQuestionsAnswerResult.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupJoinQuestionsAnswerResult.
                 * @implements IGroupJoinQuestionsAnswerResult
                 * @constructor
                 * @param {im.turms.proto.IGroupJoinQuestionsAnswerResult=} [properties] Properties to set
                 */
                function GroupJoinQuestionsAnswerResult(properties) {
                    this.questionIds = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupJoinQuestionsAnswerResult score.
                 * @member {number} score
                 * @memberof im.turms.proto.GroupJoinQuestionsAnswerResult
                 * @instance
                 */
                GroupJoinQuestionsAnswerResult.prototype.score = 0;

                /**
                 * GroupJoinQuestionsAnswerResult questionIds.
                 * @member {Array.<string>} questionIds
                 * @memberof im.turms.proto.GroupJoinQuestionsAnswerResult
                 * @instance
                 */
                GroupJoinQuestionsAnswerResult.prototype.questionIds = $util.emptyArray;

                /**
                 * GroupJoinQuestionsAnswerResult joined.
                 * @member {boolean} joined
                 * @memberof im.turms.proto.GroupJoinQuestionsAnswerResult
                 * @instance
                 */
                GroupJoinQuestionsAnswerResult.prototype.joined = false;

                /**
                 * Encodes the specified GroupJoinQuestionsAnswerResult message. Does not implicitly {@link im.turms.proto.GroupJoinQuestionsAnswerResult.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupJoinQuestionsAnswerResult
                 * @static
                 * @param {im.turms.proto.IGroupJoinQuestionsAnswerResult} message GroupJoinQuestionsAnswerResult message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupJoinQuestionsAnswerResult.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.score != null && Object.hasOwnProperty.call(message, "score"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.score);
                    if (message.questionIds != null && message.questionIds.length) {
                        writer.uint32(/* id 2, wireType 2 =*/18).fork();
                        for (var i = 0; i < message.questionIds.length; ++i)
                            writer.int64(message.questionIds[i]);
                        writer.ldelim();
                    }
                    if (message.joined != null && Object.hasOwnProperty.call(message, "joined"))
                        writer.uint32(/* id 3, wireType 0 =*/24).bool(message.joined);
                    return writer;
                };

                /**
                 * Decodes a GroupJoinQuestionsAnswerResult message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupJoinQuestionsAnswerResult
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupJoinQuestionsAnswerResult} GroupJoinQuestionsAnswerResult
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupJoinQuestionsAnswerResult.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupJoinQuestionsAnswerResult();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.score = reader.int32();
                            break;
                        case 2:
                            if (!(message.questionIds && message.questionIds.length))
                                message.questionIds = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.questionIds.push(reader.int64().toString());
                            } else
                                message.questionIds.push(reader.int64().toString());
                            break;
                        case 3:
                            message.joined = reader.bool();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupJoinQuestionsAnswerResult;
            })();

            proto.GroupJoinQuestionsWithVersion = (function() {

                /**
                 * Properties of a GroupJoinQuestionsWithVersion.
                 * @memberof im.turms.proto
                 * @interface IGroupJoinQuestionsWithVersion
                 * @property {Array.<im.turms.proto.IGroupJoinQuestion>|null} [groupJoinQuestions] GroupJoinQuestionsWithVersion groupJoinQuestions
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] GroupJoinQuestionsWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new GroupJoinQuestionsWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupJoinQuestionsWithVersion.
                 * @implements IGroupJoinQuestionsWithVersion
                 * @constructor
                 * @param {im.turms.proto.IGroupJoinQuestionsWithVersion=} [properties] Properties to set
                 */
                function GroupJoinQuestionsWithVersion(properties) {
                    this.groupJoinQuestions = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupJoinQuestionsWithVersion groupJoinQuestions.
                 * @member {Array.<im.turms.proto.IGroupJoinQuestion>} groupJoinQuestions
                 * @memberof im.turms.proto.GroupJoinQuestionsWithVersion
                 * @instance
                 */
                GroupJoinQuestionsWithVersion.prototype.groupJoinQuestions = $util.emptyArray;

                /**
                 * GroupJoinQuestionsWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.GroupJoinQuestionsWithVersion
                 * @instance
                 */
                GroupJoinQuestionsWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified GroupJoinQuestionsWithVersion message. Does not implicitly {@link im.turms.proto.GroupJoinQuestionsWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupJoinQuestionsWithVersion
                 * @static
                 * @param {im.turms.proto.IGroupJoinQuestionsWithVersion} message GroupJoinQuestionsWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupJoinQuestionsWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupJoinQuestions != null && message.groupJoinQuestions.length)
                        for (var i = 0; i < message.groupJoinQuestions.length; ++i)
                            $root.im.turms.proto.GroupJoinQuestion.encode(message.groupJoinQuestions[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupJoinQuestionsWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupJoinQuestionsWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupJoinQuestionsWithVersion} GroupJoinQuestionsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupJoinQuestionsWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupJoinQuestionsWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.groupJoinQuestions && message.groupJoinQuestions.length))
                                message.groupJoinQuestions = [];
                            message.groupJoinQuestions.push($root.im.turms.proto.GroupJoinQuestion.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupJoinQuestionsWithVersion;
            })();

            proto.GroupJoinRequest = (function() {

                /**
                 * Properties of a GroupJoinRequest.
                 * @memberof im.turms.proto
                 * @interface IGroupJoinRequest
                 * @property {google.protobuf.IInt64Value|null} [id] GroupJoinRequest id
                 * @property {google.protobuf.IInt64Value|null} [creationDate] GroupJoinRequest creationDate
                 * @property {google.protobuf.IStringValue|null} [content] GroupJoinRequest content
                 * @property {im.turms.proto.RequestStatus|null} [status] GroupJoinRequest status
                 * @property {google.protobuf.IInt64Value|null} [expirationDate] GroupJoinRequest expirationDate
                 * @property {google.protobuf.IInt64Value|null} [groupId] GroupJoinRequest groupId
                 * @property {google.protobuf.IInt64Value|null} [requesterId] GroupJoinRequest requesterId
                 * @property {google.protobuf.IInt64Value|null} [responderId] GroupJoinRequest responderId
                 */

                /**
                 * Constructs a new GroupJoinRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupJoinRequest.
                 * @implements IGroupJoinRequest
                 * @constructor
                 * @param {im.turms.proto.IGroupJoinRequest=} [properties] Properties to set
                 */
                function GroupJoinRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupJoinRequest id.
                 * @member {google.protobuf.IInt64Value|null|undefined} id
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.id = null;

                /**
                 * GroupJoinRequest creationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} creationDate
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.creationDate = null;

                /**
                 * GroupJoinRequest content.
                 * @member {google.protobuf.IStringValue|null|undefined} content
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.content = null;

                /**
                 * GroupJoinRequest status.
                 * @member {im.turms.proto.RequestStatus} status
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.status = 0;

                /**
                 * GroupJoinRequest expirationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} expirationDate
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.expirationDate = null;

                /**
                 * GroupJoinRequest groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.groupId = null;

                /**
                 * GroupJoinRequest requesterId.
                 * @member {google.protobuf.IInt64Value|null|undefined} requesterId
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.requesterId = null;

                /**
                 * GroupJoinRequest responderId.
                 * @member {google.protobuf.IInt64Value|null|undefined} responderId
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @instance
                 */
                GroupJoinRequest.prototype.responderId = null;

                /**
                 * Encodes the specified GroupJoinRequest message. Does not implicitly {@link im.turms.proto.GroupJoinRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @static
                 * @param {im.turms.proto.IGroupJoinRequest} message GroupJoinRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupJoinRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.id != null && Object.hasOwnProperty.call(message, "id"))
                        $root.google.protobuf.Int64Value.encode(message.id, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.creationDate != null && Object.hasOwnProperty.call(message, "creationDate"))
                        $root.google.protobuf.Int64Value.encode(message.creationDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.content != null && Object.hasOwnProperty.call(message, "content"))
                        $root.google.protobuf.StringValue.encode(message.content, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.status != null && Object.hasOwnProperty.call(message, "status"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.status);
                    if (message.expirationDate != null && Object.hasOwnProperty.call(message, "expirationDate"))
                        $root.google.protobuf.Int64Value.encode(message.expirationDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.requesterId != null && Object.hasOwnProperty.call(message, "requesterId"))
                        $root.google.protobuf.Int64Value.encode(message.requesterId, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.responderId != null && Object.hasOwnProperty.call(message, "responderId"))
                        $root.google.protobuf.Int64Value.encode(message.responderId, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupJoinRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupJoinRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupJoinRequest} GroupJoinRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupJoinRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupJoinRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.id = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.creationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.content = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.status = reader.int32();
                            break;
                        case 5:
                            message.expirationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.requesterId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.responderId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupJoinRequest;
            })();

            proto.GroupJoinRequestsWithVersion = (function() {

                /**
                 * Properties of a GroupJoinRequestsWithVersion.
                 * @memberof im.turms.proto
                 * @interface IGroupJoinRequestsWithVersion
                 * @property {Array.<im.turms.proto.IGroupJoinRequest>|null} [groupJoinRequests] GroupJoinRequestsWithVersion groupJoinRequests
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] GroupJoinRequestsWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new GroupJoinRequestsWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupJoinRequestsWithVersion.
                 * @implements IGroupJoinRequestsWithVersion
                 * @constructor
                 * @param {im.turms.proto.IGroupJoinRequestsWithVersion=} [properties] Properties to set
                 */
                function GroupJoinRequestsWithVersion(properties) {
                    this.groupJoinRequests = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupJoinRequestsWithVersion groupJoinRequests.
                 * @member {Array.<im.turms.proto.IGroupJoinRequest>} groupJoinRequests
                 * @memberof im.turms.proto.GroupJoinRequestsWithVersion
                 * @instance
                 */
                GroupJoinRequestsWithVersion.prototype.groupJoinRequests = $util.emptyArray;

                /**
                 * GroupJoinRequestsWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.GroupJoinRequestsWithVersion
                 * @instance
                 */
                GroupJoinRequestsWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified GroupJoinRequestsWithVersion message. Does not implicitly {@link im.turms.proto.GroupJoinRequestsWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupJoinRequestsWithVersion
                 * @static
                 * @param {im.turms.proto.IGroupJoinRequestsWithVersion} message GroupJoinRequestsWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupJoinRequestsWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupJoinRequests != null && message.groupJoinRequests.length)
                        for (var i = 0; i < message.groupJoinRequests.length; ++i)
                            $root.im.turms.proto.GroupJoinRequest.encode(message.groupJoinRequests[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupJoinRequestsWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupJoinRequestsWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupJoinRequestsWithVersion} GroupJoinRequestsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupJoinRequestsWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupJoinRequestsWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.groupJoinRequests && message.groupJoinRequests.length))
                                message.groupJoinRequests = [];
                            message.groupJoinRequests.push($root.im.turms.proto.GroupJoinRequest.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupJoinRequestsWithVersion;
            })();

            proto.GroupMember = (function() {

                /**
                 * Properties of a GroupMember.
                 * @memberof im.turms.proto
                 * @interface IGroupMember
                 * @property {google.protobuf.IInt64Value|null} [groupId] GroupMember groupId
                 * @property {google.protobuf.IInt64Value|null} [userId] GroupMember userId
                 * @property {google.protobuf.IStringValue|null} [name] GroupMember name
                 * @property {im.turms.proto.GroupMemberRole|null} [role] GroupMember role
                 * @property {google.protobuf.IInt64Value|null} [joinDate] GroupMember joinDate
                 * @property {google.protobuf.IInt64Value|null} [muteEndDate] GroupMember muteEndDate
                 * @property {im.turms.proto.UserStatus|null} [userStatus] GroupMember userStatus
                 * @property {Array.<im.turms.proto.DeviceType>|null} [usingDeviceTypes] GroupMember usingDeviceTypes
                 */

                /**
                 * Constructs a new GroupMember.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupMember.
                 * @implements IGroupMember
                 * @constructor
                 * @param {im.turms.proto.IGroupMember=} [properties] Properties to set
                 */
                function GroupMember(properties) {
                    this.usingDeviceTypes = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupMember groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.groupId = null;

                /**
                 * GroupMember userId.
                 * @member {google.protobuf.IInt64Value|null|undefined} userId
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.userId = null;

                /**
                 * GroupMember name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.name = null;

                /**
                 * GroupMember role.
                 * @member {im.turms.proto.GroupMemberRole} role
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.role = 0;

                /**
                 * GroupMember joinDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} joinDate
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.joinDate = null;

                /**
                 * GroupMember muteEndDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} muteEndDate
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.muteEndDate = null;

                /**
                 * GroupMember userStatus.
                 * @member {im.turms.proto.UserStatus} userStatus
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.userStatus = 0;

                /**
                 * GroupMember usingDeviceTypes.
                 * @member {Array.<im.turms.proto.DeviceType>} usingDeviceTypes
                 * @memberof im.turms.proto.GroupMember
                 * @instance
                 */
                GroupMember.prototype.usingDeviceTypes = $util.emptyArray;

                /**
                 * Encodes the specified GroupMember message. Does not implicitly {@link im.turms.proto.GroupMember.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupMember
                 * @static
                 * @param {im.turms.proto.IGroupMember} message GroupMember message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupMember.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        $root.google.protobuf.Int64Value.encode(message.userId, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.role != null && Object.hasOwnProperty.call(message, "role"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.role);
                    if (message.joinDate != null && Object.hasOwnProperty.call(message, "joinDate"))
                        $root.google.protobuf.Int64Value.encode(message.joinDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.muteEndDate != null && Object.hasOwnProperty.call(message, "muteEndDate"))
                        $root.google.protobuf.Int64Value.encode(message.muteEndDate, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.userStatus != null && Object.hasOwnProperty.call(message, "userStatus"))
                        writer.uint32(/* id 7, wireType 0 =*/56).int32(message.userStatus);
                    if (message.usingDeviceTypes != null && message.usingDeviceTypes.length) {
                        writer.uint32(/* id 8, wireType 2 =*/66).fork();
                        for (var i = 0; i < message.usingDeviceTypes.length; ++i)
                            writer.int32(message.usingDeviceTypes[i]);
                        writer.ldelim();
                    }
                    return writer;
                };

                /**
                 * Decodes a GroupMember message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupMember
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupMember} GroupMember
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupMember.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupMember();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.userId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.role = reader.int32();
                            break;
                        case 5:
                            message.joinDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.muteEndDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.userStatus = reader.int32();
                            break;
                        case 8:
                            if (!(message.usingDeviceTypes && message.usingDeviceTypes.length))
                                message.usingDeviceTypes = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.usingDeviceTypes.push(reader.int32());
                            } else
                                message.usingDeviceTypes.push(reader.int32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupMember;
            })();

            proto.GroupMembersWithVersion = (function() {

                /**
                 * Properties of a GroupMembersWithVersion.
                 * @memberof im.turms.proto
                 * @interface IGroupMembersWithVersion
                 * @property {Array.<im.turms.proto.IGroupMember>|null} [groupMembers] GroupMembersWithVersion groupMembers
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] GroupMembersWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new GroupMembersWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupMembersWithVersion.
                 * @implements IGroupMembersWithVersion
                 * @constructor
                 * @param {im.turms.proto.IGroupMembersWithVersion=} [properties] Properties to set
                 */
                function GroupMembersWithVersion(properties) {
                    this.groupMembers = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupMembersWithVersion groupMembers.
                 * @member {Array.<im.turms.proto.IGroupMember>} groupMembers
                 * @memberof im.turms.proto.GroupMembersWithVersion
                 * @instance
                 */
                GroupMembersWithVersion.prototype.groupMembers = $util.emptyArray;

                /**
                 * GroupMembersWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.GroupMembersWithVersion
                 * @instance
                 */
                GroupMembersWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified GroupMembersWithVersion message. Does not implicitly {@link im.turms.proto.GroupMembersWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupMembersWithVersion
                 * @static
                 * @param {im.turms.proto.IGroupMembersWithVersion} message GroupMembersWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupMembersWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupMembers != null && message.groupMembers.length)
                        for (var i = 0; i < message.groupMembers.length; ++i)
                            $root.im.turms.proto.GroupMember.encode(message.groupMembers[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupMembersWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupMembersWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupMembersWithVersion} GroupMembersWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupMembersWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupMembersWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.groupMembers && message.groupMembers.length))
                                message.groupMembers = [];
                            message.groupMembers.push($root.im.turms.proto.GroupMember.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupMembersWithVersion;
            })();

            proto.Group = (function() {

                /**
                 * Properties of a Group.
                 * @memberof im.turms.proto
                 * @interface IGroup
                 * @property {google.protobuf.IInt64Value|null} [id] Group id
                 * @property {google.protobuf.IInt64Value|null} [typeId] Group typeId
                 * @property {google.protobuf.IInt64Value|null} [creatorId] Group creatorId
                 * @property {google.protobuf.IInt64Value|null} [ownerId] Group ownerId
                 * @property {google.protobuf.IStringValue|null} [name] Group name
                 * @property {google.protobuf.IStringValue|null} [intro] Group intro
                 * @property {google.protobuf.IStringValue|null} [announcement] Group announcement
                 * @property {google.protobuf.IInt64Value|null} [creationDate] Group creationDate
                 * @property {google.protobuf.IInt64Value|null} [deletionDate] Group deletionDate
                 * @property {google.protobuf.IInt64Value|null} [muteEndDate] Group muteEndDate
                 * @property {google.protobuf.IBoolValue|null} [active] Group active
                 */

                /**
                 * Constructs a new Group.
                 * @memberof im.turms.proto
                 * @classdesc Represents a Group.
                 * @implements IGroup
                 * @constructor
                 * @param {im.turms.proto.IGroup=} [properties] Properties to set
                 */
                function Group(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Group id.
                 * @member {google.protobuf.IInt64Value|null|undefined} id
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.id = null;

                /**
                 * Group typeId.
                 * @member {google.protobuf.IInt64Value|null|undefined} typeId
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.typeId = null;

                /**
                 * Group creatorId.
                 * @member {google.protobuf.IInt64Value|null|undefined} creatorId
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.creatorId = null;

                /**
                 * Group ownerId.
                 * @member {google.protobuf.IInt64Value|null|undefined} ownerId
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.ownerId = null;

                /**
                 * Group name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.name = null;

                /**
                 * Group intro.
                 * @member {google.protobuf.IStringValue|null|undefined} intro
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.intro = null;

                /**
                 * Group announcement.
                 * @member {google.protobuf.IStringValue|null|undefined} announcement
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.announcement = null;

                /**
                 * Group creationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} creationDate
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.creationDate = null;

                /**
                 * Group deletionDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} deletionDate
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.deletionDate = null;

                /**
                 * Group muteEndDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} muteEndDate
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.muteEndDate = null;

                /**
                 * Group active.
                 * @member {google.protobuf.IBoolValue|null|undefined} active
                 * @memberof im.turms.proto.Group
                 * @instance
                 */
                Group.prototype.active = null;

                /**
                 * Encodes the specified Group message. Does not implicitly {@link im.turms.proto.Group.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.Group
                 * @static
                 * @param {im.turms.proto.IGroup} message Group message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                Group.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.id != null && Object.hasOwnProperty.call(message, "id"))
                        $root.google.protobuf.Int64Value.encode(message.id, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.typeId != null && Object.hasOwnProperty.call(message, "typeId"))
                        $root.google.protobuf.Int64Value.encode(message.typeId, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.creatorId != null && Object.hasOwnProperty.call(message, "creatorId"))
                        $root.google.protobuf.Int64Value.encode(message.creatorId, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.ownerId != null && Object.hasOwnProperty.call(message, "ownerId"))
                        $root.google.protobuf.Int64Value.encode(message.ownerId, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.intro != null && Object.hasOwnProperty.call(message, "intro"))
                        $root.google.protobuf.StringValue.encode(message.intro, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.announcement != null && Object.hasOwnProperty.call(message, "announcement"))
                        $root.google.protobuf.StringValue.encode(message.announcement, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.creationDate != null && Object.hasOwnProperty.call(message, "creationDate"))
                        $root.google.protobuf.Int64Value.encode(message.creationDate, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    if (message.deletionDate != null && Object.hasOwnProperty.call(message, "deletionDate"))
                        $root.google.protobuf.Int64Value.encode(message.deletionDate, writer.uint32(/* id 9, wireType 2 =*/74).fork()).ldelim();
                    if (message.muteEndDate != null && Object.hasOwnProperty.call(message, "muteEndDate"))
                        $root.google.protobuf.Int64Value.encode(message.muteEndDate, writer.uint32(/* id 10, wireType 2 =*/82).fork()).ldelim();
                    if (message.active != null && Object.hasOwnProperty.call(message, "active"))
                        $root.google.protobuf.BoolValue.encode(message.active, writer.uint32(/* id 11, wireType 2 =*/90).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a Group message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.Group
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.Group} Group
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                Group.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.Group();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.id = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.typeId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.creatorId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.ownerId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.intro = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.announcement = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.creationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 9:
                            message.deletionDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 10:
                            message.muteEndDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 11:
                            message.active = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return Group;
            })();

            proto.GroupsWithVersion = (function() {

                /**
                 * Properties of a GroupsWithVersion.
                 * @memberof im.turms.proto
                 * @interface IGroupsWithVersion
                 * @property {Array.<im.turms.proto.IGroup>|null} [groups] GroupsWithVersion groups
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] GroupsWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new GroupsWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a GroupsWithVersion.
                 * @implements IGroupsWithVersion
                 * @constructor
                 * @param {im.turms.proto.IGroupsWithVersion=} [properties] Properties to set
                 */
                function GroupsWithVersion(properties) {
                    this.groups = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * GroupsWithVersion groups.
                 * @member {Array.<im.turms.proto.IGroup>} groups
                 * @memberof im.turms.proto.GroupsWithVersion
                 * @instance
                 */
                GroupsWithVersion.prototype.groups = $util.emptyArray;

                /**
                 * GroupsWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.GroupsWithVersion
                 * @instance
                 */
                GroupsWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified GroupsWithVersion message. Does not implicitly {@link im.turms.proto.GroupsWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.GroupsWithVersion
                 * @static
                 * @param {im.turms.proto.IGroupsWithVersion} message GroupsWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                GroupsWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groups != null && message.groups.length)
                        for (var i = 0; i < message.groups.length; ++i)
                            $root.im.turms.proto.Group.encode(message.groups[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a GroupsWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.GroupsWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.GroupsWithVersion} GroupsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                GroupsWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.GroupsWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.groups && message.groups.length))
                                message.groups = [];
                            message.groups.push($root.im.turms.proto.Group.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return GroupsWithVersion;
            })();

            proto.MessageStatus = (function() {

                /**
                 * Properties of a MessageStatus.
                 * @memberof im.turms.proto
                 * @interface IMessageStatus
                 * @property {google.protobuf.IInt64Value|null} [messageId] MessageStatus messageId
                 * @property {google.protobuf.IInt64Value|null} [toUserId] MessageStatus toUserId
                 * @property {im.turms.proto.MessageDeliveryStatus|null} [deliveryStatus] MessageStatus deliveryStatus
                 * @property {google.protobuf.IInt64Value|null} [receptionDate] MessageStatus receptionDate
                 * @property {google.protobuf.IInt64Value|null} [readDate] MessageStatus readDate
                 * @property {google.protobuf.IInt64Value|null} [recallDate] MessageStatus recallDate
                 */

                /**
                 * Constructs a new MessageStatus.
                 * @memberof im.turms.proto
                 * @classdesc Represents a MessageStatus.
                 * @implements IMessageStatus
                 * @constructor
                 * @param {im.turms.proto.IMessageStatus=} [properties] Properties to set
                 */
                function MessageStatus(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * MessageStatus messageId.
                 * @member {google.protobuf.IInt64Value|null|undefined} messageId
                 * @memberof im.turms.proto.MessageStatus
                 * @instance
                 */
                MessageStatus.prototype.messageId = null;

                /**
                 * MessageStatus toUserId.
                 * @member {google.protobuf.IInt64Value|null|undefined} toUserId
                 * @memberof im.turms.proto.MessageStatus
                 * @instance
                 */
                MessageStatus.prototype.toUserId = null;

                /**
                 * MessageStatus deliveryStatus.
                 * @member {im.turms.proto.MessageDeliveryStatus} deliveryStatus
                 * @memberof im.turms.proto.MessageStatus
                 * @instance
                 */
                MessageStatus.prototype.deliveryStatus = 0;

                /**
                 * MessageStatus receptionDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} receptionDate
                 * @memberof im.turms.proto.MessageStatus
                 * @instance
                 */
                MessageStatus.prototype.receptionDate = null;

                /**
                 * MessageStatus readDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} readDate
                 * @memberof im.turms.proto.MessageStatus
                 * @instance
                 */
                MessageStatus.prototype.readDate = null;

                /**
                 * MessageStatus recallDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} recallDate
                 * @memberof im.turms.proto.MessageStatus
                 * @instance
                 */
                MessageStatus.prototype.recallDate = null;

                /**
                 * Encodes the specified MessageStatus message. Does not implicitly {@link im.turms.proto.MessageStatus.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.MessageStatus
                 * @static
                 * @param {im.turms.proto.IMessageStatus} message MessageStatus message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                MessageStatus.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messageId != null && Object.hasOwnProperty.call(message, "messageId"))
                        $root.google.protobuf.Int64Value.encode(message.messageId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.toUserId != null && Object.hasOwnProperty.call(message, "toUserId"))
                        $root.google.protobuf.Int64Value.encode(message.toUserId, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.deliveryStatus != null && Object.hasOwnProperty.call(message, "deliveryStatus"))
                        writer.uint32(/* id 3, wireType 0 =*/24).int32(message.deliveryStatus);
                    if (message.receptionDate != null && Object.hasOwnProperty.call(message, "receptionDate"))
                        $root.google.protobuf.Int64Value.encode(message.receptionDate, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.readDate != null && Object.hasOwnProperty.call(message, "readDate"))
                        $root.google.protobuf.Int64Value.encode(message.readDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.recallDate != null && Object.hasOwnProperty.call(message, "recallDate"))
                        $root.google.protobuf.Int64Value.encode(message.recallDate, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a MessageStatus message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.MessageStatus
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.MessageStatus} MessageStatus
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                MessageStatus.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.MessageStatus();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.messageId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.toUserId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.deliveryStatus = reader.int32();
                            break;
                        case 4:
                            message.receptionDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.readDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.recallDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return MessageStatus;
            })();

            proto.MessageStatuses = (function() {

                /**
                 * Properties of a MessageStatuses.
                 * @memberof im.turms.proto
                 * @interface IMessageStatuses
                 * @property {Array.<im.turms.proto.IMessageStatus>|null} [messageStatuses] MessageStatuses messageStatuses
                 */

                /**
                 * Constructs a new MessageStatuses.
                 * @memberof im.turms.proto
                 * @classdesc Represents a MessageStatuses.
                 * @implements IMessageStatuses
                 * @constructor
                 * @param {im.turms.proto.IMessageStatuses=} [properties] Properties to set
                 */
                function MessageStatuses(properties) {
                    this.messageStatuses = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * MessageStatuses messageStatuses.
                 * @member {Array.<im.turms.proto.IMessageStatus>} messageStatuses
                 * @memberof im.turms.proto.MessageStatuses
                 * @instance
                 */
                MessageStatuses.prototype.messageStatuses = $util.emptyArray;

                /**
                 * Encodes the specified MessageStatuses message. Does not implicitly {@link im.turms.proto.MessageStatuses.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.MessageStatuses
                 * @static
                 * @param {im.turms.proto.IMessageStatuses} message MessageStatuses message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                MessageStatuses.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messageStatuses != null && message.messageStatuses.length)
                        for (var i = 0; i < message.messageStatuses.length; ++i)
                            $root.im.turms.proto.MessageStatus.encode(message.messageStatuses[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a MessageStatuses message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.MessageStatuses
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.MessageStatuses} MessageStatuses
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                MessageStatuses.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.MessageStatuses();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.messageStatuses && message.messageStatuses.length))
                                message.messageStatuses = [];
                            message.messageStatuses.push($root.im.turms.proto.MessageStatus.decode(reader, reader.uint32()));
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return MessageStatuses;
            })();

            proto.Message = (function() {

                /**
                 * Properties of a Message.
                 * @memberof im.turms.proto
                 * @interface IMessage
                 * @property {google.protobuf.IInt64Value|null} [id] Message id
                 * @property {google.protobuf.IInt64Value|null} [deliveryDate] Message deliveryDate
                 * @property {google.protobuf.IInt64Value|null} [deletionDate] Message deletionDate
                 * @property {google.protobuf.IStringValue|null} [text] Message text
                 * @property {google.protobuf.IInt64Value|null} [senderId] Message senderId
                 * @property {google.protobuf.IInt64Value|null} [groupId] Message groupId
                 * @property {google.protobuf.IBoolValue|null} [isSystemMessage] Message isSystemMessage
                 * @property {google.protobuf.IInt64Value|null} [recipientId] Message recipientId
                 * @property {Array.<Uint8Array>|null} [records] Message records
                 */

                /**
                 * Constructs a new Message.
                 * @memberof im.turms.proto
                 * @classdesc Represents a Message.
                 * @implements IMessage
                 * @constructor
                 * @param {im.turms.proto.IMessage=} [properties] Properties to set
                 */
                function Message(properties) {
                    this.records = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Message id.
                 * @member {google.protobuf.IInt64Value|null|undefined} id
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.id = null;

                /**
                 * Message deliveryDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} deliveryDate
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.deliveryDate = null;

                /**
                 * Message deletionDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} deletionDate
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.deletionDate = null;

                /**
                 * Message text.
                 * @member {google.protobuf.IStringValue|null|undefined} text
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.text = null;

                /**
                 * Message senderId.
                 * @member {google.protobuf.IInt64Value|null|undefined} senderId
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.senderId = null;

                /**
                 * Message groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.groupId = null;

                /**
                 * Message isSystemMessage.
                 * @member {google.protobuf.IBoolValue|null|undefined} isSystemMessage
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.isSystemMessage = null;

                /**
                 * Message recipientId.
                 * @member {google.protobuf.IInt64Value|null|undefined} recipientId
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.recipientId = null;

                /**
                 * Message records.
                 * @member {Array.<Uint8Array>} records
                 * @memberof im.turms.proto.Message
                 * @instance
                 */
                Message.prototype.records = $util.emptyArray;

                /**
                 * Encodes the specified Message message. Does not implicitly {@link im.turms.proto.Message.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.Message
                 * @static
                 * @param {im.turms.proto.IMessage} message Message message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                Message.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.id != null && Object.hasOwnProperty.call(message, "id"))
                        $root.google.protobuf.Int64Value.encode(message.id, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.deliveryDate != null && Object.hasOwnProperty.call(message, "deliveryDate"))
                        $root.google.protobuf.Int64Value.encode(message.deliveryDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.deletionDate != null && Object.hasOwnProperty.call(message, "deletionDate"))
                        $root.google.protobuf.Int64Value.encode(message.deletionDate, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.text != null && Object.hasOwnProperty.call(message, "text"))
                        $root.google.protobuf.StringValue.encode(message.text, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.senderId != null && Object.hasOwnProperty.call(message, "senderId"))
                        $root.google.protobuf.Int64Value.encode(message.senderId, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.isSystemMessage != null && Object.hasOwnProperty.call(message, "isSystemMessage"))
                        $root.google.protobuf.BoolValue.encode(message.isSystemMessage, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.recipientId != null && Object.hasOwnProperty.call(message, "recipientId"))
                        $root.google.protobuf.Int64Value.encode(message.recipientId, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    if (message.records != null && message.records.length)
                        for (var i = 0; i < message.records.length; ++i)
                            writer.uint32(/* id 9, wireType 2 =*/74).bytes(message.records[i]);
                    return writer;
                };

                /**
                 * Decodes a Message message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.Message
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.Message} Message
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                Message.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.Message();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.id = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.deliveryDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.deletionDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.text = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.senderId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.isSystemMessage = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.recipientId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 9:
                            if (!(message.records && message.records.length))
                                message.records = [];
                            message.records.push(reader.bytes());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return Message;
            })();

            proto.MessagesWithTotalList = (function() {

                /**
                 * Properties of a MessagesWithTotalList.
                 * @memberof im.turms.proto
                 * @interface IMessagesWithTotalList
                 * @property {Array.<im.turms.proto.IMessagesWithTotal>|null} [messagesWithTotalList] MessagesWithTotalList messagesWithTotalList
                 */

                /**
                 * Constructs a new MessagesWithTotalList.
                 * @memberof im.turms.proto
                 * @classdesc Represents a MessagesWithTotalList.
                 * @implements IMessagesWithTotalList
                 * @constructor
                 * @param {im.turms.proto.IMessagesWithTotalList=} [properties] Properties to set
                 */
                function MessagesWithTotalList(properties) {
                    this.messagesWithTotalList = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * MessagesWithTotalList messagesWithTotalList.
                 * @member {Array.<im.turms.proto.IMessagesWithTotal>} messagesWithTotalList
                 * @memberof im.turms.proto.MessagesWithTotalList
                 * @instance
                 */
                MessagesWithTotalList.prototype.messagesWithTotalList = $util.emptyArray;

                /**
                 * Encodes the specified MessagesWithTotalList message. Does not implicitly {@link im.turms.proto.MessagesWithTotalList.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.MessagesWithTotalList
                 * @static
                 * @param {im.turms.proto.IMessagesWithTotalList} message MessagesWithTotalList message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                MessagesWithTotalList.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messagesWithTotalList != null && message.messagesWithTotalList.length)
                        for (var i = 0; i < message.messagesWithTotalList.length; ++i)
                            $root.im.turms.proto.MessagesWithTotal.encode(message.messagesWithTotalList[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a MessagesWithTotalList message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.MessagesWithTotalList
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.MessagesWithTotalList} MessagesWithTotalList
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                MessagesWithTotalList.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.MessagesWithTotalList();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.messagesWithTotalList && message.messagesWithTotalList.length))
                                message.messagesWithTotalList = [];
                            message.messagesWithTotalList.push($root.im.turms.proto.MessagesWithTotal.decode(reader, reader.uint32()));
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return MessagesWithTotalList;
            })();

            proto.MessagesWithTotal = (function() {

                /**
                 * Properties of a MessagesWithTotal.
                 * @memberof im.turms.proto
                 * @interface IMessagesWithTotal
                 * @property {number|null} [total] MessagesWithTotal total
                 * @property {boolean|null} [isGroupMessage] MessagesWithTotal isGroupMessage
                 * @property {string|null} [fromId] MessagesWithTotal fromId
                 * @property {Array.<im.turms.proto.IMessage>|null} [messages] MessagesWithTotal messages
                 */

                /**
                 * Constructs a new MessagesWithTotal.
                 * @memberof im.turms.proto
                 * @classdesc Represents a MessagesWithTotal.
                 * @implements IMessagesWithTotal
                 * @constructor
                 * @param {im.turms.proto.IMessagesWithTotal=} [properties] Properties to set
                 */
                function MessagesWithTotal(properties) {
                    this.messages = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * MessagesWithTotal total.
                 * @member {number} total
                 * @memberof im.turms.proto.MessagesWithTotal
                 * @instance
                 */
                MessagesWithTotal.prototype.total = 0;

                /**
                 * MessagesWithTotal isGroupMessage.
                 * @member {boolean} isGroupMessage
                 * @memberof im.turms.proto.MessagesWithTotal
                 * @instance
                 */
                MessagesWithTotal.prototype.isGroupMessage = false;

                /**
                 * MessagesWithTotal fromId.
                 * @member {string} fromId
                 * @memberof im.turms.proto.MessagesWithTotal
                 * @instance
                 */
                MessagesWithTotal.prototype.fromId = "0";

                /**
                 * MessagesWithTotal messages.
                 * @member {Array.<im.turms.proto.IMessage>} messages
                 * @memberof im.turms.proto.MessagesWithTotal
                 * @instance
                 */
                MessagesWithTotal.prototype.messages = $util.emptyArray;

                /**
                 * Encodes the specified MessagesWithTotal message. Does not implicitly {@link im.turms.proto.MessagesWithTotal.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.MessagesWithTotal
                 * @static
                 * @param {im.turms.proto.IMessagesWithTotal} message MessagesWithTotal message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                MessagesWithTotal.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.total != null && Object.hasOwnProperty.call(message, "total"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.total);
                    if (message.isGroupMessage != null && Object.hasOwnProperty.call(message, "isGroupMessage"))
                        writer.uint32(/* id 2, wireType 0 =*/16).bool(message.isGroupMessage);
                    if (message.fromId != null && Object.hasOwnProperty.call(message, "fromId"))
                        writer.uint32(/* id 3, wireType 0 =*/24).int64(message.fromId);
                    if (message.messages != null && message.messages.length)
                        for (var i = 0; i < message.messages.length; ++i)
                            $root.im.turms.proto.Message.encode(message.messages[i], writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a MessagesWithTotal message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.MessagesWithTotal
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.MessagesWithTotal} MessagesWithTotal
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                MessagesWithTotal.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.MessagesWithTotal();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.total = reader.int32();
                            break;
                        case 2:
                            message.isGroupMessage = reader.bool();
                            break;
                        case 3:
                            message.fromId = reader.int64().toString();
                            break;
                        case 4:
                            if (!(message.messages && message.messages.length))
                                message.messages = [];
                            message.messages.push($root.im.turms.proto.Message.decode(reader, reader.uint32()));
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return MessagesWithTotal;
            })();

            proto.Messages = (function() {

                /**
                 * Properties of a Messages.
                 * @memberof im.turms.proto
                 * @interface IMessages
                 * @property {Array.<im.turms.proto.IMessage>|null} [messages] Messages messages
                 */

                /**
                 * Constructs a new Messages.
                 * @memberof im.turms.proto
                 * @classdesc Represents a Messages.
                 * @implements IMessages
                 * @constructor
                 * @param {im.turms.proto.IMessages=} [properties] Properties to set
                 */
                function Messages(properties) {
                    this.messages = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Messages messages.
                 * @member {Array.<im.turms.proto.IMessage>} messages
                 * @memberof im.turms.proto.Messages
                 * @instance
                 */
                Messages.prototype.messages = $util.emptyArray;

                /**
                 * Encodes the specified Messages message. Does not implicitly {@link im.turms.proto.Messages.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.Messages
                 * @static
                 * @param {im.turms.proto.IMessages} message Messages message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                Messages.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messages != null && message.messages.length)
                        for (var i = 0; i < message.messages.length; ++i)
                            $root.im.turms.proto.Message.encode(message.messages[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a Messages message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.Messages
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.Messages} Messages
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                Messages.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.Messages();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.messages && message.messages.length))
                                message.messages = [];
                            message.messages.push($root.im.turms.proto.Message.decode(reader, reader.uint32()));
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return Messages;
            })();

            proto.Acknowledge = (function() {

                /**
                 * Properties of an Acknowledge.
                 * @memberof im.turms.proto
                 * @interface IAcknowledge
                 * @property {string|null} [deliveryDate] Acknowledge deliveryDate
                 * @property {google.protobuf.IInt64Value|null} [messageId] Acknowledge messageId
                 */

                /**
                 * Constructs a new Acknowledge.
                 * @memberof im.turms.proto
                 * @classdesc Represents an Acknowledge.
                 * @implements IAcknowledge
                 * @constructor
                 * @param {im.turms.proto.IAcknowledge=} [properties] Properties to set
                 */
                function Acknowledge(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Acknowledge deliveryDate.
                 * @member {string} deliveryDate
                 * @memberof im.turms.proto.Acknowledge
                 * @instance
                 */
                Acknowledge.prototype.deliveryDate = "0";

                /**
                 * Acknowledge messageId.
                 * @member {google.protobuf.IInt64Value|null|undefined} messageId
                 * @memberof im.turms.proto.Acknowledge
                 * @instance
                 */
                Acknowledge.prototype.messageId = null;

                /**
                 * Encodes the specified Acknowledge message. Does not implicitly {@link im.turms.proto.Acknowledge.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.Acknowledge
                 * @static
                 * @param {im.turms.proto.IAcknowledge} message Acknowledge message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                Acknowledge.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.deliveryDate != null && Object.hasOwnProperty.call(message, "deliveryDate"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.deliveryDate);
                    if (message.messageId != null && Object.hasOwnProperty.call(message, "messageId"))
                        $root.google.protobuf.Int64Value.encode(message.messageId, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an Acknowledge message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.Acknowledge
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.Acknowledge} Acknowledge
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                Acknowledge.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.Acknowledge();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.deliveryDate = reader.int64().toString();
                            break;
                        case 2:
                            message.messageId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return Acknowledge;
            })();

            proto.Session = (function() {

                /**
                 * Properties of a Session.
                 * @memberof im.turms.proto
                 * @interface ISession
                 * @property {string|null} [sessionId] Session sessionId
                 */

                /**
                 * Constructs a new Session.
                 * @memberof im.turms.proto
                 * @classdesc Represents a Session.
                 * @implements ISession
                 * @constructor
                 * @param {im.turms.proto.ISession=} [properties] Properties to set
                 */
                function Session(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Session sessionId.
                 * @member {string} sessionId
                 * @memberof im.turms.proto.Session
                 * @instance
                 */
                Session.prototype.sessionId = "";

                /**
                 * Encodes the specified Session message. Does not implicitly {@link im.turms.proto.Session.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.Session
                 * @static
                 * @param {im.turms.proto.ISession} message Session message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                Session.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.sessionId != null && Object.hasOwnProperty.call(message, "sessionId"))
                        writer.uint32(/* id 1, wireType 2 =*/10).string(message.sessionId);
                    return writer;
                };

                /**
                 * Decodes a Session message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.Session
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.Session} Session
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                Session.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.Session();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.sessionId = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return Session;
            })();

            proto.UserFriendRequest = (function() {

                /**
                 * Properties of a UserFriendRequest.
                 * @memberof im.turms.proto
                 * @interface IUserFriendRequest
                 * @property {google.protobuf.IInt64Value|null} [id] UserFriendRequest id
                 * @property {google.protobuf.IInt64Value|null} [creationDate] UserFriendRequest creationDate
                 * @property {google.protobuf.IStringValue|null} [content] UserFriendRequest content
                 * @property {im.turms.proto.RequestStatus|null} [requestStatus] UserFriendRequest requestStatus
                 * @property {google.protobuf.IStringValue|null} [reason] UserFriendRequest reason
                 * @property {google.protobuf.IInt64Value|null} [expirationDate] UserFriendRequest expirationDate
                 * @property {google.protobuf.IInt64Value|null} [requesterId] UserFriendRequest requesterId
                 * @property {google.protobuf.IInt64Value|null} [recipientId] UserFriendRequest recipientId
                 */

                /**
                 * Constructs a new UserFriendRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserFriendRequest.
                 * @implements IUserFriendRequest
                 * @constructor
                 * @param {im.turms.proto.IUserFriendRequest=} [properties] Properties to set
                 */
                function UserFriendRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserFriendRequest id.
                 * @member {google.protobuf.IInt64Value|null|undefined} id
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.id = null;

                /**
                 * UserFriendRequest creationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} creationDate
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.creationDate = null;

                /**
                 * UserFriendRequest content.
                 * @member {google.protobuf.IStringValue|null|undefined} content
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.content = null;

                /**
                 * UserFriendRequest requestStatus.
                 * @member {im.turms.proto.RequestStatus} requestStatus
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.requestStatus = 0;

                /**
                 * UserFriendRequest reason.
                 * @member {google.protobuf.IStringValue|null|undefined} reason
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.reason = null;

                /**
                 * UserFriendRequest expirationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} expirationDate
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.expirationDate = null;

                /**
                 * UserFriendRequest requesterId.
                 * @member {google.protobuf.IInt64Value|null|undefined} requesterId
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.requesterId = null;

                /**
                 * UserFriendRequest recipientId.
                 * @member {google.protobuf.IInt64Value|null|undefined} recipientId
                 * @memberof im.turms.proto.UserFriendRequest
                 * @instance
                 */
                UserFriendRequest.prototype.recipientId = null;

                /**
                 * Encodes the specified UserFriendRequest message. Does not implicitly {@link im.turms.proto.UserFriendRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserFriendRequest
                 * @static
                 * @param {im.turms.proto.IUserFriendRequest} message UserFriendRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserFriendRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.id != null && Object.hasOwnProperty.call(message, "id"))
                        $root.google.protobuf.Int64Value.encode(message.id, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.creationDate != null && Object.hasOwnProperty.call(message, "creationDate"))
                        $root.google.protobuf.Int64Value.encode(message.creationDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.content != null && Object.hasOwnProperty.call(message, "content"))
                        $root.google.protobuf.StringValue.encode(message.content, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.requestStatus != null && Object.hasOwnProperty.call(message, "requestStatus"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.requestStatus);
                    if (message.reason != null && Object.hasOwnProperty.call(message, "reason"))
                        $root.google.protobuf.StringValue.encode(message.reason, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.expirationDate != null && Object.hasOwnProperty.call(message, "expirationDate"))
                        $root.google.protobuf.Int64Value.encode(message.expirationDate, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.requesterId != null && Object.hasOwnProperty.call(message, "requesterId"))
                        $root.google.protobuf.Int64Value.encode(message.requesterId, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.recipientId != null && Object.hasOwnProperty.call(message, "recipientId"))
                        $root.google.protobuf.Int64Value.encode(message.recipientId, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UserFriendRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserFriendRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserFriendRequest} UserFriendRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserFriendRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserFriendRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.id = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.creationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.content = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.requestStatus = reader.int32();
                            break;
                        case 5:
                            message.reason = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.expirationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.requesterId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.recipientId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserFriendRequest;
            })();

            proto.UserFriendRequestsWithVersion = (function() {

                /**
                 * Properties of a UserFriendRequestsWithVersion.
                 * @memberof im.turms.proto
                 * @interface IUserFriendRequestsWithVersion
                 * @property {Array.<im.turms.proto.IUserFriendRequest>|null} [userFriendRequests] UserFriendRequestsWithVersion userFriendRequests
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] UserFriendRequestsWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new UserFriendRequestsWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserFriendRequestsWithVersion.
                 * @implements IUserFriendRequestsWithVersion
                 * @constructor
                 * @param {im.turms.proto.IUserFriendRequestsWithVersion=} [properties] Properties to set
                 */
                function UserFriendRequestsWithVersion(properties) {
                    this.userFriendRequests = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserFriendRequestsWithVersion userFriendRequests.
                 * @member {Array.<im.turms.proto.IUserFriendRequest>} userFriendRequests
                 * @memberof im.turms.proto.UserFriendRequestsWithVersion
                 * @instance
                 */
                UserFriendRequestsWithVersion.prototype.userFriendRequests = $util.emptyArray;

                /**
                 * UserFriendRequestsWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.UserFriendRequestsWithVersion
                 * @instance
                 */
                UserFriendRequestsWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified UserFriendRequestsWithVersion message. Does not implicitly {@link im.turms.proto.UserFriendRequestsWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserFriendRequestsWithVersion
                 * @static
                 * @param {im.turms.proto.IUserFriendRequestsWithVersion} message UserFriendRequestsWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserFriendRequestsWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userFriendRequests != null && message.userFriendRequests.length)
                        for (var i = 0; i < message.userFriendRequests.length; ++i)
                            $root.im.turms.proto.UserFriendRequest.encode(message.userFriendRequests[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UserFriendRequestsWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserFriendRequestsWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserFriendRequestsWithVersion} UserFriendRequestsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserFriendRequestsWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserFriendRequestsWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userFriendRequests && message.userFriendRequests.length))
                                message.userFriendRequests = [];
                            message.userFriendRequests.push($root.im.turms.proto.UserFriendRequest.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserFriendRequestsWithVersion;
            })();

            proto.UserInfo = (function() {

                /**
                 * Properties of a UserInfo.
                 * @memberof im.turms.proto
                 * @interface IUserInfo
                 * @property {google.protobuf.IInt64Value|null} [id] UserInfo id
                 * @property {google.protobuf.IStringValue|null} [name] UserInfo name
                 * @property {google.protobuf.IStringValue|null} [intro] UserInfo intro
                 * @property {google.protobuf.IInt64Value|null} [registrationDate] UserInfo registrationDate
                 * @property {google.protobuf.IInt64Value|null} [deletionDate] UserInfo deletionDate
                 * @property {google.protobuf.IBoolValue|null} [active] UserInfo active
                 * @property {im.turms.proto.ProfileAccessStrategy|null} [profileAccessStrategy] UserInfo profileAccessStrategy
                 */

                /**
                 * Constructs a new UserInfo.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserInfo.
                 * @implements IUserInfo
                 * @constructor
                 * @param {im.turms.proto.IUserInfo=} [properties] Properties to set
                 */
                function UserInfo(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserInfo id.
                 * @member {google.protobuf.IInt64Value|null|undefined} id
                 * @memberof im.turms.proto.UserInfo
                 * @instance
                 */
                UserInfo.prototype.id = null;

                /**
                 * UserInfo name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.UserInfo
                 * @instance
                 */
                UserInfo.prototype.name = null;

                /**
                 * UserInfo intro.
                 * @member {google.protobuf.IStringValue|null|undefined} intro
                 * @memberof im.turms.proto.UserInfo
                 * @instance
                 */
                UserInfo.prototype.intro = null;

                /**
                 * UserInfo registrationDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} registrationDate
                 * @memberof im.turms.proto.UserInfo
                 * @instance
                 */
                UserInfo.prototype.registrationDate = null;

                /**
                 * UserInfo deletionDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} deletionDate
                 * @memberof im.turms.proto.UserInfo
                 * @instance
                 */
                UserInfo.prototype.deletionDate = null;

                /**
                 * UserInfo active.
                 * @member {google.protobuf.IBoolValue|null|undefined} active
                 * @memberof im.turms.proto.UserInfo
                 * @instance
                 */
                UserInfo.prototype.active = null;

                /**
                 * UserInfo profileAccessStrategy.
                 * @member {im.turms.proto.ProfileAccessStrategy} profileAccessStrategy
                 * @memberof im.turms.proto.UserInfo
                 * @instance
                 */
                UserInfo.prototype.profileAccessStrategy = 0;

                /**
                 * Encodes the specified UserInfo message. Does not implicitly {@link im.turms.proto.UserInfo.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserInfo
                 * @static
                 * @param {im.turms.proto.IUserInfo} message UserInfo message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserInfo.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.id != null && Object.hasOwnProperty.call(message, "id"))
                        $root.google.protobuf.Int64Value.encode(message.id, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.intro != null && Object.hasOwnProperty.call(message, "intro"))
                        $root.google.protobuf.StringValue.encode(message.intro, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.registrationDate != null && Object.hasOwnProperty.call(message, "registrationDate"))
                        $root.google.protobuf.Int64Value.encode(message.registrationDate, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.deletionDate != null && Object.hasOwnProperty.call(message, "deletionDate"))
                        $root.google.protobuf.Int64Value.encode(message.deletionDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.active != null && Object.hasOwnProperty.call(message, "active"))
                        $root.google.protobuf.BoolValue.encode(message.active, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.profileAccessStrategy != null && Object.hasOwnProperty.call(message, "profileAccessStrategy"))
                        writer.uint32(/* id 7, wireType 0 =*/56).int32(message.profileAccessStrategy);
                    return writer;
                };

                /**
                 * Decodes a UserInfo message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserInfo
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserInfo} UserInfo
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserInfo.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserInfo();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.id = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.intro = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.registrationDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.deletionDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.active = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.profileAccessStrategy = reader.int32();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserInfo;
            })();

            proto.UserLocation = (function() {

                /**
                 * Properties of a UserLocation.
                 * @memberof im.turms.proto
                 * @interface IUserLocation
                 * @property {number|null} [latitude] UserLocation latitude
                 * @property {number|null} [longitude] UserLocation longitude
                 * @property {google.protobuf.IStringValue|null} [name] UserLocation name
                 * @property {google.protobuf.IStringValue|null} [address] UserLocation address
                 * @property {google.protobuf.IInt64Value|null} [timestamp] UserLocation timestamp
                 */

                /**
                 * Constructs a new UserLocation.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserLocation.
                 * @implements IUserLocation
                 * @constructor
                 * @param {im.turms.proto.IUserLocation=} [properties] Properties to set
                 */
                function UserLocation(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserLocation latitude.
                 * @member {number} latitude
                 * @memberof im.turms.proto.UserLocation
                 * @instance
                 */
                UserLocation.prototype.latitude = 0;

                /**
                 * UserLocation longitude.
                 * @member {number} longitude
                 * @memberof im.turms.proto.UserLocation
                 * @instance
                 */
                UserLocation.prototype.longitude = 0;

                /**
                 * UserLocation name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.UserLocation
                 * @instance
                 */
                UserLocation.prototype.name = null;

                /**
                 * UserLocation address.
                 * @member {google.protobuf.IStringValue|null|undefined} address
                 * @memberof im.turms.proto.UserLocation
                 * @instance
                 */
                UserLocation.prototype.address = null;

                /**
                 * UserLocation timestamp.
                 * @member {google.protobuf.IInt64Value|null|undefined} timestamp
                 * @memberof im.turms.proto.UserLocation
                 * @instance
                 */
                UserLocation.prototype.timestamp = null;

                /**
                 * Encodes the specified UserLocation message. Does not implicitly {@link im.turms.proto.UserLocation.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserLocation
                 * @static
                 * @param {im.turms.proto.IUserLocation} message UserLocation message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserLocation.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.latitude != null && Object.hasOwnProperty.call(message, "latitude"))
                        writer.uint32(/* id 1, wireType 5 =*/13).float(message.latitude);
                    if (message.longitude != null && Object.hasOwnProperty.call(message, "longitude"))
                        writer.uint32(/* id 2, wireType 5 =*/21).float(message.longitude);
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.address != null && Object.hasOwnProperty.call(message, "address"))
                        $root.google.protobuf.StringValue.encode(message.address, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.timestamp != null && Object.hasOwnProperty.call(message, "timestamp"))
                        $root.google.protobuf.Int64Value.encode(message.timestamp, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UserLocation message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserLocation
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserLocation} UserLocation
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserLocation.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserLocation();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.latitude = reader.float();
                            break;
                        case 2:
                            message.longitude = reader.float();
                            break;
                        case 3:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.address = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.timestamp = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserLocation;
            })();

            proto.UserRelationshipGroup = (function() {

                /**
                 * Properties of a UserRelationshipGroup.
                 * @memberof im.turms.proto
                 * @interface IUserRelationshipGroup
                 * @property {number|null} [index] UserRelationshipGroup index
                 * @property {string|null} [name] UserRelationshipGroup name
                 */

                /**
                 * Constructs a new UserRelationshipGroup.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserRelationshipGroup.
                 * @implements IUserRelationshipGroup
                 * @constructor
                 * @param {im.turms.proto.IUserRelationshipGroup=} [properties] Properties to set
                 */
                function UserRelationshipGroup(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserRelationshipGroup index.
                 * @member {number} index
                 * @memberof im.turms.proto.UserRelationshipGroup
                 * @instance
                 */
                UserRelationshipGroup.prototype.index = 0;

                /**
                 * UserRelationshipGroup name.
                 * @member {string} name
                 * @memberof im.turms.proto.UserRelationshipGroup
                 * @instance
                 */
                UserRelationshipGroup.prototype.name = "";

                /**
                 * Encodes the specified UserRelationshipGroup message. Does not implicitly {@link im.turms.proto.UserRelationshipGroup.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserRelationshipGroup
                 * @static
                 * @param {im.turms.proto.IUserRelationshipGroup} message UserRelationshipGroup message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserRelationshipGroup.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.index != null && Object.hasOwnProperty.call(message, "index"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.index);
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        writer.uint32(/* id 2, wireType 2 =*/18).string(message.name);
                    return writer;
                };

                /**
                 * Decodes a UserRelationshipGroup message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserRelationshipGroup
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserRelationshipGroup} UserRelationshipGroup
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserRelationshipGroup.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserRelationshipGroup();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.index = reader.int32();
                            break;
                        case 2:
                            message.name = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserRelationshipGroup;
            })();

            proto.UserRelationshipGroupsWithVersion = (function() {

                /**
                 * Properties of a UserRelationshipGroupsWithVersion.
                 * @memberof im.turms.proto
                 * @interface IUserRelationshipGroupsWithVersion
                 * @property {Array.<im.turms.proto.IUserRelationshipGroup>|null} [userRelationshipGroups] UserRelationshipGroupsWithVersion userRelationshipGroups
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] UserRelationshipGroupsWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new UserRelationshipGroupsWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserRelationshipGroupsWithVersion.
                 * @implements IUserRelationshipGroupsWithVersion
                 * @constructor
                 * @param {im.turms.proto.IUserRelationshipGroupsWithVersion=} [properties] Properties to set
                 */
                function UserRelationshipGroupsWithVersion(properties) {
                    this.userRelationshipGroups = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserRelationshipGroupsWithVersion userRelationshipGroups.
                 * @member {Array.<im.turms.proto.IUserRelationshipGroup>} userRelationshipGroups
                 * @memberof im.turms.proto.UserRelationshipGroupsWithVersion
                 * @instance
                 */
                UserRelationshipGroupsWithVersion.prototype.userRelationshipGroups = $util.emptyArray;

                /**
                 * UserRelationshipGroupsWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.UserRelationshipGroupsWithVersion
                 * @instance
                 */
                UserRelationshipGroupsWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified UserRelationshipGroupsWithVersion message. Does not implicitly {@link im.turms.proto.UserRelationshipGroupsWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserRelationshipGroupsWithVersion
                 * @static
                 * @param {im.turms.proto.IUserRelationshipGroupsWithVersion} message UserRelationshipGroupsWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserRelationshipGroupsWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userRelationshipGroups != null && message.userRelationshipGroups.length)
                        for (var i = 0; i < message.userRelationshipGroups.length; ++i)
                            $root.im.turms.proto.UserRelationshipGroup.encode(message.userRelationshipGroups[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UserRelationshipGroupsWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserRelationshipGroupsWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserRelationshipGroupsWithVersion} UserRelationshipGroupsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserRelationshipGroupsWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserRelationshipGroupsWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userRelationshipGroups && message.userRelationshipGroups.length))
                                message.userRelationshipGroups = [];
                            message.userRelationshipGroups.push($root.im.turms.proto.UserRelationshipGroup.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserRelationshipGroupsWithVersion;
            })();

            proto.UserRelationship = (function() {

                /**
                 * Properties of a UserRelationship.
                 * @memberof im.turms.proto
                 * @interface IUserRelationship
                 * @property {google.protobuf.IInt64Value|null} [ownerId] UserRelationship ownerId
                 * @property {google.protobuf.IInt64Value|null} [relatedUserId] UserRelationship relatedUserId
                 * @property {google.protobuf.IBoolValue|null} [blocked] UserRelationship blocked
                 * @property {google.protobuf.IInt64Value|null} [groupIndex] UserRelationship groupIndex
                 * @property {google.protobuf.IInt64Value|null} [establishmentDate] UserRelationship establishmentDate
                 */

                /**
                 * Constructs a new UserRelationship.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserRelationship.
                 * @implements IUserRelationship
                 * @constructor
                 * @param {im.turms.proto.IUserRelationship=} [properties] Properties to set
                 */
                function UserRelationship(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserRelationship ownerId.
                 * @member {google.protobuf.IInt64Value|null|undefined} ownerId
                 * @memberof im.turms.proto.UserRelationship
                 * @instance
                 */
                UserRelationship.prototype.ownerId = null;

                /**
                 * UserRelationship relatedUserId.
                 * @member {google.protobuf.IInt64Value|null|undefined} relatedUserId
                 * @memberof im.turms.proto.UserRelationship
                 * @instance
                 */
                UserRelationship.prototype.relatedUserId = null;

                /**
                 * UserRelationship blocked.
                 * @member {google.protobuf.IBoolValue|null|undefined} blocked
                 * @memberof im.turms.proto.UserRelationship
                 * @instance
                 */
                UserRelationship.prototype.blocked = null;

                /**
                 * UserRelationship groupIndex.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupIndex
                 * @memberof im.turms.proto.UserRelationship
                 * @instance
                 */
                UserRelationship.prototype.groupIndex = null;

                /**
                 * UserRelationship establishmentDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} establishmentDate
                 * @memberof im.turms.proto.UserRelationship
                 * @instance
                 */
                UserRelationship.prototype.establishmentDate = null;

                /**
                 * Encodes the specified UserRelationship message. Does not implicitly {@link im.turms.proto.UserRelationship.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserRelationship
                 * @static
                 * @param {im.turms.proto.IUserRelationship} message UserRelationship message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserRelationship.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.ownerId != null && Object.hasOwnProperty.call(message, "ownerId"))
                        $root.google.protobuf.Int64Value.encode(message.ownerId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.relatedUserId != null && Object.hasOwnProperty.call(message, "relatedUserId"))
                        $root.google.protobuf.Int64Value.encode(message.relatedUserId, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.blocked != null && Object.hasOwnProperty.call(message, "blocked"))
                        $root.google.protobuf.BoolValue.encode(message.blocked, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        $root.google.protobuf.Int64Value.encode(message.groupIndex, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.establishmentDate != null && Object.hasOwnProperty.call(message, "establishmentDate"))
                        $root.google.protobuf.Int64Value.encode(message.establishmentDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UserRelationship message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserRelationship
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserRelationship} UserRelationship
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserRelationship.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserRelationship();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.ownerId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.relatedUserId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.blocked = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.groupIndex = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.establishmentDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserRelationship;
            })();

            proto.UserRelationshipsWithVersion = (function() {

                /**
                 * Properties of a UserRelationshipsWithVersion.
                 * @memberof im.turms.proto
                 * @interface IUserRelationshipsWithVersion
                 * @property {Array.<im.turms.proto.IUserRelationship>|null} [userRelationships] UserRelationshipsWithVersion userRelationships
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] UserRelationshipsWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new UserRelationshipsWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserRelationshipsWithVersion.
                 * @implements IUserRelationshipsWithVersion
                 * @constructor
                 * @param {im.turms.proto.IUserRelationshipsWithVersion=} [properties] Properties to set
                 */
                function UserRelationshipsWithVersion(properties) {
                    this.userRelationships = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserRelationshipsWithVersion userRelationships.
                 * @member {Array.<im.turms.proto.IUserRelationship>} userRelationships
                 * @memberof im.turms.proto.UserRelationshipsWithVersion
                 * @instance
                 */
                UserRelationshipsWithVersion.prototype.userRelationships = $util.emptyArray;

                /**
                 * UserRelationshipsWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.UserRelationshipsWithVersion
                 * @instance
                 */
                UserRelationshipsWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified UserRelationshipsWithVersion message. Does not implicitly {@link im.turms.proto.UserRelationshipsWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserRelationshipsWithVersion
                 * @static
                 * @param {im.turms.proto.IUserRelationshipsWithVersion} message UserRelationshipsWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserRelationshipsWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userRelationships != null && message.userRelationships.length)
                        for (var i = 0; i < message.userRelationships.length; ++i)
                            $root.im.turms.proto.UserRelationship.encode(message.userRelationships[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UserRelationshipsWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserRelationshipsWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserRelationshipsWithVersion} UserRelationshipsWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserRelationshipsWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserRelationshipsWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userRelationships && message.userRelationships.length))
                                message.userRelationships = [];
                            message.userRelationships.push($root.im.turms.proto.UserRelationship.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserRelationshipsWithVersion;
            })();

            proto.UserSessionId = (function() {

                /**
                 * Properties of a UserSessionId.
                 * @memberof im.turms.proto
                 * @interface IUserSessionId
                 * @property {string|null} [userId] UserSessionId userId
                 * @property {im.turms.proto.DeviceType|null} [deviceType] UserSessionId deviceType
                 */

                /**
                 * Constructs a new UserSessionId.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserSessionId.
                 * @implements IUserSessionId
                 * @constructor
                 * @param {im.turms.proto.IUserSessionId=} [properties] Properties to set
                 */
                function UserSessionId(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserSessionId userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.UserSessionId
                 * @instance
                 */
                UserSessionId.prototype.userId = "0";

                /**
                 * UserSessionId deviceType.
                 * @member {im.turms.proto.DeviceType} deviceType
                 * @memberof im.turms.proto.UserSessionId
                 * @instance
                 */
                UserSessionId.prototype.deviceType = 0;

                /**
                 * Encodes the specified UserSessionId message. Does not implicitly {@link im.turms.proto.UserSessionId.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserSessionId
                 * @static
                 * @param {im.turms.proto.IUserSessionId} message UserSessionId message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserSessionId.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.deviceType != null && Object.hasOwnProperty.call(message, "deviceType"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int32(message.deviceType);
                    return writer;
                };

                /**
                 * Decodes a UserSessionId message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserSessionId
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserSessionId} UserSessionId
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserSessionId.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserSessionId();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.deviceType = reader.int32();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserSessionId;
            })();

            proto.UserSessionIds = (function() {

                /**
                 * Properties of a UserSessionIds.
                 * @memberof im.turms.proto
                 * @interface IUserSessionIds
                 * @property {Array.<im.turms.proto.IUserSessionId>|null} [userSessionIds] UserSessionIds userSessionIds
                 */

                /**
                 * Constructs a new UserSessionIds.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserSessionIds.
                 * @implements IUserSessionIds
                 * @constructor
                 * @param {im.turms.proto.IUserSessionIds=} [properties] Properties to set
                 */
                function UserSessionIds(properties) {
                    this.userSessionIds = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserSessionIds userSessionIds.
                 * @member {Array.<im.turms.proto.IUserSessionId>} userSessionIds
                 * @memberof im.turms.proto.UserSessionIds
                 * @instance
                 */
                UserSessionIds.prototype.userSessionIds = $util.emptyArray;

                /**
                 * Encodes the specified UserSessionIds message. Does not implicitly {@link im.turms.proto.UserSessionIds.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserSessionIds
                 * @static
                 * @param {im.turms.proto.IUserSessionIds} message UserSessionIds message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserSessionIds.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userSessionIds != null && message.userSessionIds.length)
                        for (var i = 0; i < message.userSessionIds.length; ++i)
                            $root.im.turms.proto.UserSessionId.encode(message.userSessionIds[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UserSessionIds message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserSessionIds
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserSessionIds} UserSessionIds
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserSessionIds.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserSessionIds();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userSessionIds && message.userSessionIds.length))
                                message.userSessionIds = [];
                            message.userSessionIds.push($root.im.turms.proto.UserSessionId.decode(reader, reader.uint32()));
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserSessionIds;
            })();

            proto.UserStatusDetail = (function() {

                /**
                 * Properties of a UserStatusDetail.
                 * @memberof im.turms.proto
                 * @interface IUserStatusDetail
                 * @property {string|null} [userId] UserStatusDetail userId
                 * @property {im.turms.proto.UserStatus|null} [userStatus] UserStatusDetail userStatus
                 * @property {Array.<im.turms.proto.DeviceType>|null} [usingDeviceTypes] UserStatusDetail usingDeviceTypes
                 */

                /**
                 * Constructs a new UserStatusDetail.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UserStatusDetail.
                 * @implements IUserStatusDetail
                 * @constructor
                 * @param {im.turms.proto.IUserStatusDetail=} [properties] Properties to set
                 */
                function UserStatusDetail(properties) {
                    this.usingDeviceTypes = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UserStatusDetail userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.UserStatusDetail
                 * @instance
                 */
                UserStatusDetail.prototype.userId = "0";

                /**
                 * UserStatusDetail userStatus.
                 * @member {im.turms.proto.UserStatus} userStatus
                 * @memberof im.turms.proto.UserStatusDetail
                 * @instance
                 */
                UserStatusDetail.prototype.userStatus = 0;

                /**
                 * UserStatusDetail usingDeviceTypes.
                 * @member {Array.<im.turms.proto.DeviceType>} usingDeviceTypes
                 * @memberof im.turms.proto.UserStatusDetail
                 * @instance
                 */
                UserStatusDetail.prototype.usingDeviceTypes = $util.emptyArray;

                /**
                 * Encodes the specified UserStatusDetail message. Does not implicitly {@link im.turms.proto.UserStatusDetail.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UserStatusDetail
                 * @static
                 * @param {im.turms.proto.IUserStatusDetail} message UserStatusDetail message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UserStatusDetail.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.userStatus != null && Object.hasOwnProperty.call(message, "userStatus"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int32(message.userStatus);
                    if (message.usingDeviceTypes != null && message.usingDeviceTypes.length) {
                        writer.uint32(/* id 3, wireType 2 =*/26).fork();
                        for (var i = 0; i < message.usingDeviceTypes.length; ++i)
                            writer.int32(message.usingDeviceTypes[i]);
                        writer.ldelim();
                    }
                    return writer;
                };

                /**
                 * Decodes a UserStatusDetail message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UserStatusDetail
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UserStatusDetail} UserStatusDetail
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UserStatusDetail.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UserStatusDetail();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.userStatus = reader.int32();
                            break;
                        case 3:
                            if (!(message.usingDeviceTypes && message.usingDeviceTypes.length))
                                message.usingDeviceTypes = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.usingDeviceTypes.push(reader.int32());
                            } else
                                message.usingDeviceTypes.push(reader.int32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UserStatusDetail;
            })();

            proto.UsersInfosWithVersion = (function() {

                /**
                 * Properties of a UsersInfosWithVersion.
                 * @memberof im.turms.proto
                 * @interface IUsersInfosWithVersion
                 * @property {Array.<im.turms.proto.IUserInfo>|null} [userInfos] UsersInfosWithVersion userInfos
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] UsersInfosWithVersion lastUpdatedDate
                 */

                /**
                 * Constructs a new UsersInfosWithVersion.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UsersInfosWithVersion.
                 * @implements IUsersInfosWithVersion
                 * @constructor
                 * @param {im.turms.proto.IUsersInfosWithVersion=} [properties] Properties to set
                 */
                function UsersInfosWithVersion(properties) {
                    this.userInfos = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UsersInfosWithVersion userInfos.
                 * @member {Array.<im.turms.proto.IUserInfo>} userInfos
                 * @memberof im.turms.proto.UsersInfosWithVersion
                 * @instance
                 */
                UsersInfosWithVersion.prototype.userInfos = $util.emptyArray;

                /**
                 * UsersInfosWithVersion lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.UsersInfosWithVersion
                 * @instance
                 */
                UsersInfosWithVersion.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified UsersInfosWithVersion message. Does not implicitly {@link im.turms.proto.UsersInfosWithVersion.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UsersInfosWithVersion
                 * @static
                 * @param {im.turms.proto.IUsersInfosWithVersion} message UsersInfosWithVersion message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UsersInfosWithVersion.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userInfos != null && message.userInfos.length)
                        for (var i = 0; i < message.userInfos.length; ++i)
                            $root.im.turms.proto.UserInfo.encode(message.userInfos[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UsersInfosWithVersion message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UsersInfosWithVersion
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UsersInfosWithVersion} UsersInfosWithVersion
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UsersInfosWithVersion.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UsersInfosWithVersion();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userInfos && message.userInfos.length))
                                message.userInfos = [];
                            message.userInfos.push($root.im.turms.proto.UserInfo.decode(reader, reader.uint32()));
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UsersInfosWithVersion;
            })();

            proto.UsersOnlineStatuses = (function() {

                /**
                 * Properties of a UsersOnlineStatuses.
                 * @memberof im.turms.proto
                 * @interface IUsersOnlineStatuses
                 * @property {Array.<im.turms.proto.IUserStatusDetail>|null} [userStatuses] UsersOnlineStatuses userStatuses
                 */

                /**
                 * Constructs a new UsersOnlineStatuses.
                 * @memberof im.turms.proto
                 * @classdesc Represents a UsersOnlineStatuses.
                 * @implements IUsersOnlineStatuses
                 * @constructor
                 * @param {im.turms.proto.IUsersOnlineStatuses=} [properties] Properties to set
                 */
                function UsersOnlineStatuses(properties) {
                    this.userStatuses = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UsersOnlineStatuses userStatuses.
                 * @member {Array.<im.turms.proto.IUserStatusDetail>} userStatuses
                 * @memberof im.turms.proto.UsersOnlineStatuses
                 * @instance
                 */
                UsersOnlineStatuses.prototype.userStatuses = $util.emptyArray;

                /**
                 * Encodes the specified UsersOnlineStatuses message. Does not implicitly {@link im.turms.proto.UsersOnlineStatuses.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UsersOnlineStatuses
                 * @static
                 * @param {im.turms.proto.IUsersOnlineStatuses} message UsersOnlineStatuses message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UsersOnlineStatuses.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userStatuses != null && message.userStatuses.length)
                        for (var i = 0; i < message.userStatuses.length; ++i)
                            $root.im.turms.proto.UserStatusDetail.encode(message.userStatuses[i], writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a UsersOnlineStatuses message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UsersOnlineStatuses
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UsersOnlineStatuses} UsersOnlineStatuses
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UsersOnlineStatuses.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UsersOnlineStatuses();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userStatuses && message.userStatuses.length))
                                message.userStatuses = [];
                            message.userStatuses.push($root.im.turms.proto.UserStatusDetail.decode(reader, reader.uint32()));
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UsersOnlineStatuses;
            })();

            proto.TurmsNotification = (function() {

                /**
                 * Properties of a TurmsNotification.
                 * @memberof im.turms.proto
                 * @interface ITurmsNotification
                 * @property {google.protobuf.IInt64Value|null} [requestId] TurmsNotification requestId
                 * @property {google.protobuf.IInt32Value|null} [code] TurmsNotification code
                 * @property {google.protobuf.IStringValue|null} [reason] TurmsNotification reason
                 * @property {im.turms.proto.TurmsNotification.IData|null} [data] TurmsNotification data
                 * @property {im.turms.proto.ITurmsRequest|null} [relayedRequest] TurmsNotification relayedRequest
                 * @property {google.protobuf.IInt64Value|null} [requesterId] TurmsNotification requesterId
                 * @property {google.protobuf.IInt32Value|null} [closeStatus] TurmsNotification closeStatus
                 */

                /**
                 * Constructs a new TurmsNotification.
                 * @memberof im.turms.proto
                 * @classdesc Represents a TurmsNotification.
                 * @implements ITurmsNotification
                 * @constructor
                 * @param {im.turms.proto.ITurmsNotification=} [properties] Properties to set
                 */
                function TurmsNotification(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * TurmsNotification requestId.
                 * @member {google.protobuf.IInt64Value|null|undefined} requestId
                 * @memberof im.turms.proto.TurmsNotification
                 * @instance
                 */
                TurmsNotification.prototype.requestId = null;

                /**
                 * TurmsNotification code.
                 * @member {google.protobuf.IInt32Value|null|undefined} code
                 * @memberof im.turms.proto.TurmsNotification
                 * @instance
                 */
                TurmsNotification.prototype.code = null;

                /**
                 * TurmsNotification reason.
                 * @member {google.protobuf.IStringValue|null|undefined} reason
                 * @memberof im.turms.proto.TurmsNotification
                 * @instance
                 */
                TurmsNotification.prototype.reason = null;

                /**
                 * TurmsNotification data.
                 * @member {im.turms.proto.TurmsNotification.IData|null|undefined} data
                 * @memberof im.turms.proto.TurmsNotification
                 * @instance
                 */
                TurmsNotification.prototype.data = null;

                /**
                 * TurmsNotification relayedRequest.
                 * @member {im.turms.proto.ITurmsRequest|null|undefined} relayedRequest
                 * @memberof im.turms.proto.TurmsNotification
                 * @instance
                 */
                TurmsNotification.prototype.relayedRequest = null;

                /**
                 * TurmsNotification requesterId.
                 * @member {google.protobuf.IInt64Value|null|undefined} requesterId
                 * @memberof im.turms.proto.TurmsNotification
                 * @instance
                 */
                TurmsNotification.prototype.requesterId = null;

                /**
                 * TurmsNotification closeStatus.
                 * @member {google.protobuf.IInt32Value|null|undefined} closeStatus
                 * @memberof im.turms.proto.TurmsNotification
                 * @instance
                 */
                TurmsNotification.prototype.closeStatus = null;

                /**
                 * Encodes the specified TurmsNotification message. Does not implicitly {@link im.turms.proto.TurmsNotification.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.TurmsNotification
                 * @static
                 * @param {im.turms.proto.ITurmsNotification} message TurmsNotification message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                TurmsNotification.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.requestId != null && Object.hasOwnProperty.call(message, "requestId"))
                        $root.google.protobuf.Int64Value.encode(message.requestId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.code != null && Object.hasOwnProperty.call(message, "code"))
                        $root.google.protobuf.Int32Value.encode(message.code, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.reason != null && Object.hasOwnProperty.call(message, "reason"))
                        $root.google.protobuf.StringValue.encode(message.reason, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.data != null && Object.hasOwnProperty.call(message, "data"))
                        $root.im.turms.proto.TurmsNotification.Data.encode(message.data, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.relayedRequest != null && Object.hasOwnProperty.call(message, "relayedRequest"))
                        $root.im.turms.proto.TurmsRequest.encode(message.relayedRequest, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.requesterId != null && Object.hasOwnProperty.call(message, "requesterId"))
                        $root.google.protobuf.Int64Value.encode(message.requesterId, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.closeStatus != null && Object.hasOwnProperty.call(message, "closeStatus"))
                        $root.google.protobuf.Int32Value.encode(message.closeStatus, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a TurmsNotification message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.TurmsNotification
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.TurmsNotification} TurmsNotification
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                TurmsNotification.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.TurmsNotification();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.requestId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.code = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.reason = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.data = $root.im.turms.proto.TurmsNotification.Data.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.relayedRequest = $root.im.turms.proto.TurmsRequest.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.requesterId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.closeStatus = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                TurmsNotification.Data = (function() {

                    /**
                     * Properties of a Data.
                     * @memberof im.turms.proto.TurmsNotification
                     * @interface IData
                     * @property {im.turms.proto.IInt64Values|null} [ids] Data ids
                     * @property {im.turms.proto.IInt64ValuesWithVersion|null} [idsWithVersion] Data idsWithVersion
                     * @property {google.protobuf.IStringValue|null} [url] Data url
                     * @property {im.turms.proto.IAcknowledge|null} [acknowledge] Data acknowledge
                     * @property {im.turms.proto.ISession|null} [session] Data session
                     * @property {im.turms.proto.IMessages|null} [messages] Data messages
                     * @property {im.turms.proto.IMessageStatuses|null} [messageStatuses] Data messageStatuses
                     * @property {im.turms.proto.IMessagesWithTotalList|null} [messagesWithTotalList] Data messagesWithTotalList
                     * @property {im.turms.proto.IUsersInfosWithVersion|null} [usersInfosWithVersion] Data usersInfosWithVersion
                     * @property {im.turms.proto.IUsersOnlineStatuses|null} [usersOnlineStatuses] Data usersOnlineStatuses
                     * @property {im.turms.proto.IUserFriendRequestsWithVersion|null} [userFriendRequestsWithVersion] Data userFriendRequestsWithVersion
                     * @property {im.turms.proto.IUserRelationshipGroupsWithVersion|null} [userRelationshipGroupsWithVersion] Data userRelationshipGroupsWithVersion
                     * @property {im.turms.proto.IUserRelationshipsWithVersion|null} [userRelationshipsWithVersion] Data userRelationshipsWithVersion
                     * @property {im.turms.proto.IUserSessionIds|null} [userSessionIds] Data userSessionIds
                     * @property {im.turms.proto.IGroupInvitationsWithVersion|null} [groupInvitationsWithVersion] Data groupInvitationsWithVersion
                     * @property {im.turms.proto.IGroupJoinQuestionsAnswerResult|null} [groupJoinQuestionAnswerResult] Data groupJoinQuestionAnswerResult
                     * @property {im.turms.proto.IGroupJoinRequestsWithVersion|null} [groupJoinRequestsWithVersion] Data groupJoinRequestsWithVersion
                     * @property {im.turms.proto.IGroupJoinQuestionsWithVersion|null} [groupJoinQuestionsWithVersion] Data groupJoinQuestionsWithVersion
                     * @property {im.turms.proto.IGroupMembersWithVersion|null} [groupMembersWithVersion] Data groupMembersWithVersion
                     * @property {im.turms.proto.IGroupsWithVersion|null} [groupsWithVersion] Data groupsWithVersion
                     */

                    /**
                     * Constructs a new Data.
                     * @memberof im.turms.proto.TurmsNotification
                     * @classdesc Represents a Data.
                     * @implements IData
                     * @constructor
                     * @param {im.turms.proto.TurmsNotification.IData=} [properties] Properties to set
                     */
                    function Data(properties) {
                        if (properties)
                            for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                                if (properties[keys[i]] != null)
                                    this[keys[i]] = properties[keys[i]];
                    }

                    /**
                     * Data ids.
                     * @member {im.turms.proto.IInt64Values|null|undefined} ids
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.ids = null;

                    /**
                     * Data idsWithVersion.
                     * @member {im.turms.proto.IInt64ValuesWithVersion|null|undefined} idsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.idsWithVersion = null;

                    /**
                     * Data url.
                     * @member {google.protobuf.IStringValue|null|undefined} url
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.url = null;

                    /**
                     * Data acknowledge.
                     * @member {im.turms.proto.IAcknowledge|null|undefined} acknowledge
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.acknowledge = null;

                    /**
                     * Data session.
                     * @member {im.turms.proto.ISession|null|undefined} session
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.session = null;

                    /**
                     * Data messages.
                     * @member {im.turms.proto.IMessages|null|undefined} messages
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.messages = null;

                    /**
                     * Data messageStatuses.
                     * @member {im.turms.proto.IMessageStatuses|null|undefined} messageStatuses
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.messageStatuses = null;

                    /**
                     * Data messagesWithTotalList.
                     * @member {im.turms.proto.IMessagesWithTotalList|null|undefined} messagesWithTotalList
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.messagesWithTotalList = null;

                    /**
                     * Data usersInfosWithVersion.
                     * @member {im.turms.proto.IUsersInfosWithVersion|null|undefined} usersInfosWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.usersInfosWithVersion = null;

                    /**
                     * Data usersOnlineStatuses.
                     * @member {im.turms.proto.IUsersOnlineStatuses|null|undefined} usersOnlineStatuses
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.usersOnlineStatuses = null;

                    /**
                     * Data userFriendRequestsWithVersion.
                     * @member {im.turms.proto.IUserFriendRequestsWithVersion|null|undefined} userFriendRequestsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.userFriendRequestsWithVersion = null;

                    /**
                     * Data userRelationshipGroupsWithVersion.
                     * @member {im.turms.proto.IUserRelationshipGroupsWithVersion|null|undefined} userRelationshipGroupsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.userRelationshipGroupsWithVersion = null;

                    /**
                     * Data userRelationshipsWithVersion.
                     * @member {im.turms.proto.IUserRelationshipsWithVersion|null|undefined} userRelationshipsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.userRelationshipsWithVersion = null;

                    /**
                     * Data userSessionIds.
                     * @member {im.turms.proto.IUserSessionIds|null|undefined} userSessionIds
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.userSessionIds = null;

                    /**
                     * Data groupInvitationsWithVersion.
                     * @member {im.turms.proto.IGroupInvitationsWithVersion|null|undefined} groupInvitationsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.groupInvitationsWithVersion = null;

                    /**
                     * Data groupJoinQuestionAnswerResult.
                     * @member {im.turms.proto.IGroupJoinQuestionsAnswerResult|null|undefined} groupJoinQuestionAnswerResult
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.groupJoinQuestionAnswerResult = null;

                    /**
                     * Data groupJoinRequestsWithVersion.
                     * @member {im.turms.proto.IGroupJoinRequestsWithVersion|null|undefined} groupJoinRequestsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.groupJoinRequestsWithVersion = null;

                    /**
                     * Data groupJoinQuestionsWithVersion.
                     * @member {im.turms.proto.IGroupJoinQuestionsWithVersion|null|undefined} groupJoinQuestionsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.groupJoinQuestionsWithVersion = null;

                    /**
                     * Data groupMembersWithVersion.
                     * @member {im.turms.proto.IGroupMembersWithVersion|null|undefined} groupMembersWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.groupMembersWithVersion = null;

                    /**
                     * Data groupsWithVersion.
                     * @member {im.turms.proto.IGroupsWithVersion|null|undefined} groupsWithVersion
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Data.prototype.groupsWithVersion = null;

                    // OneOf field names bound to virtual getters and setters
                    var $oneOfFields;

                    /**
                     * Data kind.
                     * @member {"ids"|"idsWithVersion"|"url"|"acknowledge"|"session"|"messages"|"messageStatuses"|"messagesWithTotalList"|"usersInfosWithVersion"|"usersOnlineStatuses"|"userFriendRequestsWithVersion"|"userRelationshipGroupsWithVersion"|"userRelationshipsWithVersion"|"userSessionIds"|"groupInvitationsWithVersion"|"groupJoinQuestionAnswerResult"|"groupJoinRequestsWithVersion"|"groupJoinQuestionsWithVersion"|"groupMembersWithVersion"|"groupsWithVersion"|undefined} kind
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @instance
                     */
                    Object.defineProperty(Data.prototype, "kind", {
                        get: $util.oneOfGetter($oneOfFields = ["ids", "idsWithVersion", "url", "acknowledge", "session", "messages", "messageStatuses", "messagesWithTotalList", "usersInfosWithVersion", "usersOnlineStatuses", "userFriendRequestsWithVersion", "userRelationshipGroupsWithVersion", "userRelationshipsWithVersion", "userSessionIds", "groupInvitationsWithVersion", "groupJoinQuestionAnswerResult", "groupJoinRequestsWithVersion", "groupJoinQuestionsWithVersion", "groupMembersWithVersion", "groupsWithVersion"]),
                        set: $util.oneOfSetter($oneOfFields)
                    });

                    /**
                     * Encodes the specified Data message. Does not implicitly {@link im.turms.proto.TurmsNotification.Data.verify|verify} messages.
                     * @function encode
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @static
                     * @param {im.turms.proto.TurmsNotification.IData} message Data message or plain object to encode
                     * @param {$protobuf.Writer} [writer] Writer to encode to
                     * @returns {$protobuf.Writer} Writer
                     */
                    Data.encode = function encode(message, writer) {
                        if (!writer)
                            writer = $Writer.create();
                        if (message.ids != null && Object.hasOwnProperty.call(message, "ids"))
                            $root.im.turms.proto.Int64Values.encode(message.ids, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                        if (message.idsWithVersion != null && Object.hasOwnProperty.call(message, "idsWithVersion"))
                            $root.im.turms.proto.Int64ValuesWithVersion.encode(message.idsWithVersion, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                        if (message.url != null && Object.hasOwnProperty.call(message, "url"))
                            $root.google.protobuf.StringValue.encode(message.url, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                        if (message.acknowledge != null && Object.hasOwnProperty.call(message, "acknowledge"))
                            $root.im.turms.proto.Acknowledge.encode(message.acknowledge, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                        if (message.session != null && Object.hasOwnProperty.call(message, "session"))
                            $root.im.turms.proto.Session.encode(message.session, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                        if (message.messages != null && Object.hasOwnProperty.call(message, "messages"))
                            $root.im.turms.proto.Messages.encode(message.messages, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                        if (message.messageStatuses != null && Object.hasOwnProperty.call(message, "messageStatuses"))
                            $root.im.turms.proto.MessageStatuses.encode(message.messageStatuses, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                        if (message.messagesWithTotalList != null && Object.hasOwnProperty.call(message, "messagesWithTotalList"))
                            $root.im.turms.proto.MessagesWithTotalList.encode(message.messagesWithTotalList, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                        if (message.usersInfosWithVersion != null && Object.hasOwnProperty.call(message, "usersInfosWithVersion"))
                            $root.im.turms.proto.UsersInfosWithVersion.encode(message.usersInfosWithVersion, writer.uint32(/* id 9, wireType 2 =*/74).fork()).ldelim();
                        if (message.usersOnlineStatuses != null && Object.hasOwnProperty.call(message, "usersOnlineStatuses"))
                            $root.im.turms.proto.UsersOnlineStatuses.encode(message.usersOnlineStatuses, writer.uint32(/* id 10, wireType 2 =*/82).fork()).ldelim();
                        if (message.userFriendRequestsWithVersion != null && Object.hasOwnProperty.call(message, "userFriendRequestsWithVersion"))
                            $root.im.turms.proto.UserFriendRequestsWithVersion.encode(message.userFriendRequestsWithVersion, writer.uint32(/* id 11, wireType 2 =*/90).fork()).ldelim();
                        if (message.userRelationshipGroupsWithVersion != null && Object.hasOwnProperty.call(message, "userRelationshipGroupsWithVersion"))
                            $root.im.turms.proto.UserRelationshipGroupsWithVersion.encode(message.userRelationshipGroupsWithVersion, writer.uint32(/* id 12, wireType 2 =*/98).fork()).ldelim();
                        if (message.userRelationshipsWithVersion != null && Object.hasOwnProperty.call(message, "userRelationshipsWithVersion"))
                            $root.im.turms.proto.UserRelationshipsWithVersion.encode(message.userRelationshipsWithVersion, writer.uint32(/* id 13, wireType 2 =*/106).fork()).ldelim();
                        if (message.userSessionIds != null && Object.hasOwnProperty.call(message, "userSessionIds"))
                            $root.im.turms.proto.UserSessionIds.encode(message.userSessionIds, writer.uint32(/* id 14, wireType 2 =*/114).fork()).ldelim();
                        if (message.groupInvitationsWithVersion != null && Object.hasOwnProperty.call(message, "groupInvitationsWithVersion"))
                            $root.im.turms.proto.GroupInvitationsWithVersion.encode(message.groupInvitationsWithVersion, writer.uint32(/* id 15, wireType 2 =*/122).fork()).ldelim();
                        if (message.groupJoinQuestionAnswerResult != null && Object.hasOwnProperty.call(message, "groupJoinQuestionAnswerResult"))
                            $root.im.turms.proto.GroupJoinQuestionsAnswerResult.encode(message.groupJoinQuestionAnswerResult, writer.uint32(/* id 16, wireType 2 =*/130).fork()).ldelim();
                        if (message.groupJoinRequestsWithVersion != null && Object.hasOwnProperty.call(message, "groupJoinRequestsWithVersion"))
                            $root.im.turms.proto.GroupJoinRequestsWithVersion.encode(message.groupJoinRequestsWithVersion, writer.uint32(/* id 17, wireType 2 =*/138).fork()).ldelim();
                        if (message.groupJoinQuestionsWithVersion != null && Object.hasOwnProperty.call(message, "groupJoinQuestionsWithVersion"))
                            $root.im.turms.proto.GroupJoinQuestionsWithVersion.encode(message.groupJoinQuestionsWithVersion, writer.uint32(/* id 18, wireType 2 =*/146).fork()).ldelim();
                        if (message.groupMembersWithVersion != null && Object.hasOwnProperty.call(message, "groupMembersWithVersion"))
                            $root.im.turms.proto.GroupMembersWithVersion.encode(message.groupMembersWithVersion, writer.uint32(/* id 19, wireType 2 =*/154).fork()).ldelim();
                        if (message.groupsWithVersion != null && Object.hasOwnProperty.call(message, "groupsWithVersion"))
                            $root.im.turms.proto.GroupsWithVersion.encode(message.groupsWithVersion, writer.uint32(/* id 20, wireType 2 =*/162).fork()).ldelim();
                        return writer;
                    };

                    /**
                     * Decodes a Data message from the specified reader or buffer.
                     * @function decode
                     * @memberof im.turms.proto.TurmsNotification.Data
                     * @static
                     * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                     * @param {number} [length] Message length if known beforehand
                     * @returns {im.turms.proto.TurmsNotification.Data} Data
                     * @throws {Error} If the payload is not a reader or valid buffer
                     * @throws {$protobuf.util.ProtocolError} If required fields are missing
                     */
                    Data.decode = function decode(reader, length) {
                        if (!(reader instanceof $Reader))
                            reader = $Reader.create(reader);
                        var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.TurmsNotification.Data();
                        while (reader.pos < end) {
                            var tag = reader.uint32();
                            switch (tag >>> 3) {
                            case 1:
                                message.ids = $root.im.turms.proto.Int64Values.decode(reader, reader.uint32());
                                break;
                            case 2:
                                message.idsWithVersion = $root.im.turms.proto.Int64ValuesWithVersion.decode(reader, reader.uint32());
                                break;
                            case 3:
                                message.url = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                                break;
                            case 4:
                                message.acknowledge = $root.im.turms.proto.Acknowledge.decode(reader, reader.uint32());
                                break;
                            case 5:
                                message.session = $root.im.turms.proto.Session.decode(reader, reader.uint32());
                                break;
                            case 6:
                                message.messages = $root.im.turms.proto.Messages.decode(reader, reader.uint32());
                                break;
                            case 7:
                                message.messageStatuses = $root.im.turms.proto.MessageStatuses.decode(reader, reader.uint32());
                                break;
                            case 8:
                                message.messagesWithTotalList = $root.im.turms.proto.MessagesWithTotalList.decode(reader, reader.uint32());
                                break;
                            case 9:
                                message.usersInfosWithVersion = $root.im.turms.proto.UsersInfosWithVersion.decode(reader, reader.uint32());
                                break;
                            case 10:
                                message.usersOnlineStatuses = $root.im.turms.proto.UsersOnlineStatuses.decode(reader, reader.uint32());
                                break;
                            case 11:
                                message.userFriendRequestsWithVersion = $root.im.turms.proto.UserFriendRequestsWithVersion.decode(reader, reader.uint32());
                                break;
                            case 12:
                                message.userRelationshipGroupsWithVersion = $root.im.turms.proto.UserRelationshipGroupsWithVersion.decode(reader, reader.uint32());
                                break;
                            case 13:
                                message.userRelationshipsWithVersion = $root.im.turms.proto.UserRelationshipsWithVersion.decode(reader, reader.uint32());
                                break;
                            case 14:
                                message.userSessionIds = $root.im.turms.proto.UserSessionIds.decode(reader, reader.uint32());
                                break;
                            case 15:
                                message.groupInvitationsWithVersion = $root.im.turms.proto.GroupInvitationsWithVersion.decode(reader, reader.uint32());
                                break;
                            case 16:
                                message.groupJoinQuestionAnswerResult = $root.im.turms.proto.GroupJoinQuestionsAnswerResult.decode(reader, reader.uint32());
                                break;
                            case 17:
                                message.groupJoinRequestsWithVersion = $root.im.turms.proto.GroupJoinRequestsWithVersion.decode(reader, reader.uint32());
                                break;
                            case 18:
                                message.groupJoinQuestionsWithVersion = $root.im.turms.proto.GroupJoinQuestionsWithVersion.decode(reader, reader.uint32());
                                break;
                            case 19:
                                message.groupMembersWithVersion = $root.im.turms.proto.GroupMembersWithVersion.decode(reader, reader.uint32());
                                break;
                            case 20:
                                message.groupsWithVersion = $root.im.turms.proto.GroupsWithVersion.decode(reader, reader.uint32());
                                break;
                            default:
                                reader.skipType(tag & 7);
                                break;
                            }
                        }
                        return message;
                    };

                    return Data;
                })();

                return TurmsNotification;
            })();

            proto.CreateGroupBlacklistedUserRequest = (function() {

                /**
                 * Properties of a CreateGroupBlacklistedUserRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateGroupBlacklistedUserRequest
                 * @property {string|null} [groupId] CreateGroupBlacklistedUserRequest groupId
                 * @property {string|null} [userId] CreateGroupBlacklistedUserRequest userId
                 */

                /**
                 * Constructs a new CreateGroupBlacklistedUserRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateGroupBlacklistedUserRequest.
                 * @implements ICreateGroupBlacklistedUserRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateGroupBlacklistedUserRequest=} [properties] Properties to set
                 */
                function CreateGroupBlacklistedUserRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateGroupBlacklistedUserRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.CreateGroupBlacklistedUserRequest
                 * @instance
                 */
                CreateGroupBlacklistedUserRequest.prototype.groupId = "0";

                /**
                 * CreateGroupBlacklistedUserRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.CreateGroupBlacklistedUserRequest
                 * @instance
                 */
                CreateGroupBlacklistedUserRequest.prototype.userId = "0";

                /**
                 * Encodes the specified CreateGroupBlacklistedUserRequest message. Does not implicitly {@link im.turms.proto.CreateGroupBlacklistedUserRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateGroupBlacklistedUserRequest
                 * @static
                 * @param {im.turms.proto.ICreateGroupBlacklistedUserRequest} message CreateGroupBlacklistedUserRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateGroupBlacklistedUserRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int64(message.userId);
                    return writer;
                };

                /**
                 * Decodes a CreateGroupBlacklistedUserRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateGroupBlacklistedUserRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateGroupBlacklistedUserRequest} CreateGroupBlacklistedUserRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateGroupBlacklistedUserRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateGroupBlacklistedUserRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.userId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateGroupBlacklistedUserRequest;
            })();

            proto.DeleteGroupBlacklistedUserRequest = (function() {

                /**
                 * Properties of a DeleteGroupBlacklistedUserRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteGroupBlacklistedUserRequest
                 * @property {string|null} [groupId] DeleteGroupBlacklistedUserRequest groupId
                 * @property {string|null} [userId] DeleteGroupBlacklistedUserRequest userId
                 */

                /**
                 * Constructs a new DeleteGroupBlacklistedUserRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteGroupBlacklistedUserRequest.
                 * @implements IDeleteGroupBlacklistedUserRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteGroupBlacklistedUserRequest=} [properties] Properties to set
                 */
                function DeleteGroupBlacklistedUserRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteGroupBlacklistedUserRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.DeleteGroupBlacklistedUserRequest
                 * @instance
                 */
                DeleteGroupBlacklistedUserRequest.prototype.groupId = "0";

                /**
                 * DeleteGroupBlacklistedUserRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.DeleteGroupBlacklistedUserRequest
                 * @instance
                 */
                DeleteGroupBlacklistedUserRequest.prototype.userId = "0";

                /**
                 * Encodes the specified DeleteGroupBlacklistedUserRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupBlacklistedUserRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteGroupBlacklistedUserRequest
                 * @static
                 * @param {im.turms.proto.IDeleteGroupBlacklistedUserRequest} message DeleteGroupBlacklistedUserRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteGroupBlacklistedUserRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int64(message.userId);
                    return writer;
                };

                /**
                 * Decodes a DeleteGroupBlacklistedUserRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteGroupBlacklistedUserRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteGroupBlacklistedUserRequest} DeleteGroupBlacklistedUserRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteGroupBlacklistedUserRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteGroupBlacklistedUserRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.userId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteGroupBlacklistedUserRequest;
            })();

            proto.QueryGroupBlacklistedUserIdsRequest = (function() {

                /**
                 * Properties of a QueryGroupBlacklistedUserIdsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryGroupBlacklistedUserIdsRequest
                 * @property {string|null} [groupId] QueryGroupBlacklistedUserIdsRequest groupId
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryGroupBlacklistedUserIdsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryGroupBlacklistedUserIdsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryGroupBlacklistedUserIdsRequest.
                 * @implements IQueryGroupBlacklistedUserIdsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryGroupBlacklistedUserIdsRequest=} [properties] Properties to set
                 */
                function QueryGroupBlacklistedUserIdsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryGroupBlacklistedUserIdsRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserIdsRequest
                 * @instance
                 */
                QueryGroupBlacklistedUserIdsRequest.prototype.groupId = "0";

                /**
                 * QueryGroupBlacklistedUserIdsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserIdsRequest
                 * @instance
                 */
                QueryGroupBlacklistedUserIdsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryGroupBlacklistedUserIdsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupBlacklistedUserIdsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserIdsRequest
                 * @static
                 * @param {im.turms.proto.IQueryGroupBlacklistedUserIdsRequest} message QueryGroupBlacklistedUserIdsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryGroupBlacklistedUserIdsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryGroupBlacklistedUserIdsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserIdsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryGroupBlacklistedUserIdsRequest} QueryGroupBlacklistedUserIdsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryGroupBlacklistedUserIdsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryGroupBlacklistedUserIdsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryGroupBlacklistedUserIdsRequest;
            })();

            proto.QueryGroupBlacklistedUserInfosRequest = (function() {

                /**
                 * Properties of a QueryGroupBlacklistedUserInfosRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryGroupBlacklistedUserInfosRequest
                 * @property {string|null} [groupId] QueryGroupBlacklistedUserInfosRequest groupId
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryGroupBlacklistedUserInfosRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryGroupBlacklistedUserInfosRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryGroupBlacklistedUserInfosRequest.
                 * @implements IQueryGroupBlacklistedUserInfosRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryGroupBlacklistedUserInfosRequest=} [properties] Properties to set
                 */
                function QueryGroupBlacklistedUserInfosRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryGroupBlacklistedUserInfosRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserInfosRequest
                 * @instance
                 */
                QueryGroupBlacklistedUserInfosRequest.prototype.groupId = "0";

                /**
                 * QueryGroupBlacklistedUserInfosRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserInfosRequest
                 * @instance
                 */
                QueryGroupBlacklistedUserInfosRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryGroupBlacklistedUserInfosRequest message. Does not implicitly {@link im.turms.proto.QueryGroupBlacklistedUserInfosRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserInfosRequest
                 * @static
                 * @param {im.turms.proto.IQueryGroupBlacklistedUserInfosRequest} message QueryGroupBlacklistedUserInfosRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryGroupBlacklistedUserInfosRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryGroupBlacklistedUserInfosRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryGroupBlacklistedUserInfosRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryGroupBlacklistedUserInfosRequest} QueryGroupBlacklistedUserInfosRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryGroupBlacklistedUserInfosRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryGroupBlacklistedUserInfosRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryGroupBlacklistedUserInfosRequest;
            })();

            proto.CreateGroupRequest = (function() {

                /**
                 * Properties of a CreateGroupRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateGroupRequest
                 * @property {string|null} [name] CreateGroupRequest name
                 * @property {google.protobuf.IStringValue|null} [intro] CreateGroupRequest intro
                 * @property {google.protobuf.IStringValue|null} [announcement] CreateGroupRequest announcement
                 * @property {google.protobuf.IInt32Value|null} [minimumScore] CreateGroupRequest minimumScore
                 * @property {google.protobuf.IInt64Value|null} [groupTypeId] CreateGroupRequest groupTypeId
                 * @property {google.protobuf.IInt64Value|null} [muteEndDate] CreateGroupRequest muteEndDate
                 */

                /**
                 * Constructs a new CreateGroupRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateGroupRequest.
                 * @implements ICreateGroupRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateGroupRequest=} [properties] Properties to set
                 */
                function CreateGroupRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateGroupRequest name.
                 * @member {string} name
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @instance
                 */
                CreateGroupRequest.prototype.name = "";

                /**
                 * CreateGroupRequest intro.
                 * @member {google.protobuf.IStringValue|null|undefined} intro
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @instance
                 */
                CreateGroupRequest.prototype.intro = null;

                /**
                 * CreateGroupRequest announcement.
                 * @member {google.protobuf.IStringValue|null|undefined} announcement
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @instance
                 */
                CreateGroupRequest.prototype.announcement = null;

                /**
                 * CreateGroupRequest minimumScore.
                 * @member {google.protobuf.IInt32Value|null|undefined} minimumScore
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @instance
                 */
                CreateGroupRequest.prototype.minimumScore = null;

                /**
                 * CreateGroupRequest groupTypeId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupTypeId
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @instance
                 */
                CreateGroupRequest.prototype.groupTypeId = null;

                /**
                 * CreateGroupRequest muteEndDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} muteEndDate
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @instance
                 */
                CreateGroupRequest.prototype.muteEndDate = null;

                /**
                 * Encodes the specified CreateGroupRequest message. Does not implicitly {@link im.turms.proto.CreateGroupRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @static
                 * @param {im.turms.proto.ICreateGroupRequest} message CreateGroupRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateGroupRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        writer.uint32(/* id 1, wireType 2 =*/10).string(message.name);
                    if (message.intro != null && Object.hasOwnProperty.call(message, "intro"))
                        $root.google.protobuf.StringValue.encode(message.intro, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.announcement != null && Object.hasOwnProperty.call(message, "announcement"))
                        $root.google.protobuf.StringValue.encode(message.announcement, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.minimumScore != null && Object.hasOwnProperty.call(message, "minimumScore"))
                        $root.google.protobuf.Int32Value.encode(message.minimumScore, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.groupTypeId != null && Object.hasOwnProperty.call(message, "groupTypeId"))
                        $root.google.protobuf.Int64Value.encode(message.groupTypeId, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.muteEndDate != null && Object.hasOwnProperty.call(message, "muteEndDate"))
                        $root.google.protobuf.Int64Value.encode(message.muteEndDate, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a CreateGroupRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateGroupRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateGroupRequest} CreateGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateGroupRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateGroupRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.name = reader.string();
                            break;
                        case 2:
                            message.intro = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.announcement = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.minimumScore = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.groupTypeId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.muteEndDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateGroupRequest;
            })();

            proto.DeleteGroupRequest = (function() {

                /**
                 * Properties of a DeleteGroupRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteGroupRequest
                 * @property {string|null} [groupId] DeleteGroupRequest groupId
                 */

                /**
                 * Constructs a new DeleteGroupRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteGroupRequest.
                 * @implements IDeleteGroupRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteGroupRequest=} [properties] Properties to set
                 */
                function DeleteGroupRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteGroupRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.DeleteGroupRequest
                 * @instance
                 */
                DeleteGroupRequest.prototype.groupId = "0";

                /**
                 * Encodes the specified DeleteGroupRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteGroupRequest
                 * @static
                 * @param {im.turms.proto.IDeleteGroupRequest} message DeleteGroupRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteGroupRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    return writer;
                };

                /**
                 * Decodes a DeleteGroupRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteGroupRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteGroupRequest} DeleteGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteGroupRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteGroupRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteGroupRequest;
            })();

            proto.CheckGroupJoinQuestionsAnswersRequest = (function() {

                /**
                 * Properties of a CheckGroupJoinQuestionsAnswersRequest.
                 * @memberof im.turms.proto
                 * @interface ICheckGroupJoinQuestionsAnswersRequest
                 * @property {Object.<string,string>|null} [questionIdAndAnswer] CheckGroupJoinQuestionsAnswersRequest questionIdAndAnswer
                 */

                /**
                 * Constructs a new CheckGroupJoinQuestionsAnswersRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CheckGroupJoinQuestionsAnswersRequest.
                 * @implements ICheckGroupJoinQuestionsAnswersRequest
                 * @constructor
                 * @param {im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest=} [properties] Properties to set
                 */
                function CheckGroupJoinQuestionsAnswersRequest(properties) {
                    this.questionIdAndAnswer = {};
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CheckGroupJoinQuestionsAnswersRequest questionIdAndAnswer.
                 * @member {Object.<string,string>} questionIdAndAnswer
                 * @memberof im.turms.proto.CheckGroupJoinQuestionsAnswersRequest
                 * @instance
                 */
                CheckGroupJoinQuestionsAnswersRequest.prototype.questionIdAndAnswer = $util.emptyObject;

                /**
                 * Encodes the specified CheckGroupJoinQuestionsAnswersRequest message. Does not implicitly {@link im.turms.proto.CheckGroupJoinQuestionsAnswersRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CheckGroupJoinQuestionsAnswersRequest
                 * @static
                 * @param {im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest} message CheckGroupJoinQuestionsAnswersRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CheckGroupJoinQuestionsAnswersRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.questionIdAndAnswer != null && Object.hasOwnProperty.call(message, "questionIdAndAnswer"))
                        for (var keys = Object.keys(message.questionIdAndAnswer), i = 0; i < keys.length; ++i)
                            writer.uint32(/* id 1, wireType 2 =*/10).fork().uint32(/* id 1, wireType 0 =*/8).int64(keys[i]).uint32(/* id 2, wireType 2 =*/18).string(message.questionIdAndAnswer[keys[i]]).ldelim();
                    return writer;
                };

                /**
                 * Decodes a CheckGroupJoinQuestionsAnswersRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CheckGroupJoinQuestionsAnswersRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CheckGroupJoinQuestionsAnswersRequest} CheckGroupJoinQuestionsAnswersRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CheckGroupJoinQuestionsAnswersRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest(), key;
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            reader.skip().pos++;
                            if (message.questionIdAndAnswer === $util.emptyObject)
                                message.questionIdAndAnswer = {};
                            key = reader.int64();
                            reader.pos++;
                            message.questionIdAndAnswer[typeof key === "object" ? $util.longToHash(key) : key] = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CheckGroupJoinQuestionsAnswersRequest;
            })();

            proto.CreateGroupInvitationRequest = (function() {

                /**
                 * Properties of a CreateGroupInvitationRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateGroupInvitationRequest
                 * @property {string|null} [groupId] CreateGroupInvitationRequest groupId
                 * @property {string|null} [inviteeId] CreateGroupInvitationRequest inviteeId
                 * @property {string|null} [content] CreateGroupInvitationRequest content
                 */

                /**
                 * Constructs a new CreateGroupInvitationRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateGroupInvitationRequest.
                 * @implements ICreateGroupInvitationRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateGroupInvitationRequest=} [properties] Properties to set
                 */
                function CreateGroupInvitationRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateGroupInvitationRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.CreateGroupInvitationRequest
                 * @instance
                 */
                CreateGroupInvitationRequest.prototype.groupId = "0";

                /**
                 * CreateGroupInvitationRequest inviteeId.
                 * @member {string} inviteeId
                 * @memberof im.turms.proto.CreateGroupInvitationRequest
                 * @instance
                 */
                CreateGroupInvitationRequest.prototype.inviteeId = "0";

                /**
                 * CreateGroupInvitationRequest content.
                 * @member {string} content
                 * @memberof im.turms.proto.CreateGroupInvitationRequest
                 * @instance
                 */
                CreateGroupInvitationRequest.prototype.content = "";

                /**
                 * Encodes the specified CreateGroupInvitationRequest message. Does not implicitly {@link im.turms.proto.CreateGroupInvitationRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateGroupInvitationRequest
                 * @static
                 * @param {im.turms.proto.ICreateGroupInvitationRequest} message CreateGroupInvitationRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateGroupInvitationRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.inviteeId != null && Object.hasOwnProperty.call(message, "inviteeId"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int64(message.inviteeId);
                    if (message.content != null && Object.hasOwnProperty.call(message, "content"))
                        writer.uint32(/* id 3, wireType 2 =*/26).string(message.content);
                    return writer;
                };

                /**
                 * Decodes a CreateGroupInvitationRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateGroupInvitationRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateGroupInvitationRequest} CreateGroupInvitationRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateGroupInvitationRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateGroupInvitationRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.inviteeId = reader.int64().toString();
                            break;
                        case 3:
                            message.content = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateGroupInvitationRequest;
            })();

            proto.CreateGroupJoinQuestionRequest = (function() {

                /**
                 * Properties of a CreateGroupJoinQuestionRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateGroupJoinQuestionRequest
                 * @property {string|null} [groupId] CreateGroupJoinQuestionRequest groupId
                 * @property {string|null} [question] CreateGroupJoinQuestionRequest question
                 * @property {Array.<string>|null} [answers] CreateGroupJoinQuestionRequest answers
                 * @property {number|null} [score] CreateGroupJoinQuestionRequest score
                 */

                /**
                 * Constructs a new CreateGroupJoinQuestionRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateGroupJoinQuestionRequest.
                 * @implements ICreateGroupJoinQuestionRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateGroupJoinQuestionRequest=} [properties] Properties to set
                 */
                function CreateGroupJoinQuestionRequest(properties) {
                    this.answers = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateGroupJoinQuestionRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.CreateGroupJoinQuestionRequest
                 * @instance
                 */
                CreateGroupJoinQuestionRequest.prototype.groupId = "0";

                /**
                 * CreateGroupJoinQuestionRequest question.
                 * @member {string} question
                 * @memberof im.turms.proto.CreateGroupJoinQuestionRequest
                 * @instance
                 */
                CreateGroupJoinQuestionRequest.prototype.question = "";

                /**
                 * CreateGroupJoinQuestionRequest answers.
                 * @member {Array.<string>} answers
                 * @memberof im.turms.proto.CreateGroupJoinQuestionRequest
                 * @instance
                 */
                CreateGroupJoinQuestionRequest.prototype.answers = $util.emptyArray;

                /**
                 * CreateGroupJoinQuestionRequest score.
                 * @member {number} score
                 * @memberof im.turms.proto.CreateGroupJoinQuestionRequest
                 * @instance
                 */
                CreateGroupJoinQuestionRequest.prototype.score = 0;

                /**
                 * Encodes the specified CreateGroupJoinQuestionRequest message. Does not implicitly {@link im.turms.proto.CreateGroupJoinQuestionRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateGroupJoinQuestionRequest
                 * @static
                 * @param {im.turms.proto.ICreateGroupJoinQuestionRequest} message CreateGroupJoinQuestionRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateGroupJoinQuestionRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.question != null && Object.hasOwnProperty.call(message, "question"))
                        writer.uint32(/* id 2, wireType 2 =*/18).string(message.question);
                    if (message.answers != null && message.answers.length)
                        for (var i = 0; i < message.answers.length; ++i)
                            writer.uint32(/* id 3, wireType 2 =*/26).string(message.answers[i]);
                    if (message.score != null && Object.hasOwnProperty.call(message, "score"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.score);
                    return writer;
                };

                /**
                 * Decodes a CreateGroupJoinQuestionRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateGroupJoinQuestionRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateGroupJoinQuestionRequest} CreateGroupJoinQuestionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateGroupJoinQuestionRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateGroupJoinQuestionRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.question = reader.string();
                            break;
                        case 3:
                            if (!(message.answers && message.answers.length))
                                message.answers = [];
                            message.answers.push(reader.string());
                            break;
                        case 4:
                            message.score = reader.int32();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateGroupJoinQuestionRequest;
            })();

            proto.CreateGroupJoinRequestRequest = (function() {

                /**
                 * Properties of a CreateGroupJoinRequestRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateGroupJoinRequestRequest
                 * @property {string|null} [groupId] CreateGroupJoinRequestRequest groupId
                 * @property {string|null} [content] CreateGroupJoinRequestRequest content
                 */

                /**
                 * Constructs a new CreateGroupJoinRequestRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateGroupJoinRequestRequest.
                 * @implements ICreateGroupJoinRequestRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateGroupJoinRequestRequest=} [properties] Properties to set
                 */
                function CreateGroupJoinRequestRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateGroupJoinRequestRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.CreateGroupJoinRequestRequest
                 * @instance
                 */
                CreateGroupJoinRequestRequest.prototype.groupId = "0";

                /**
                 * CreateGroupJoinRequestRequest content.
                 * @member {string} content
                 * @memberof im.turms.proto.CreateGroupJoinRequestRequest
                 * @instance
                 */
                CreateGroupJoinRequestRequest.prototype.content = "";

                /**
                 * Encodes the specified CreateGroupJoinRequestRequest message. Does not implicitly {@link im.turms.proto.CreateGroupJoinRequestRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateGroupJoinRequestRequest
                 * @static
                 * @param {im.turms.proto.ICreateGroupJoinRequestRequest} message CreateGroupJoinRequestRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateGroupJoinRequestRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.content != null && Object.hasOwnProperty.call(message, "content"))
                        writer.uint32(/* id 2, wireType 2 =*/18).string(message.content);
                    return writer;
                };

                /**
                 * Decodes a CreateGroupJoinRequestRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateGroupJoinRequestRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateGroupJoinRequestRequest} CreateGroupJoinRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateGroupJoinRequestRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateGroupJoinRequestRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.content = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateGroupJoinRequestRequest;
            })();

            proto.DeleteGroupInvitationRequest = (function() {

                /**
                 * Properties of a DeleteGroupInvitationRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteGroupInvitationRequest
                 * @property {string|null} [invitationId] DeleteGroupInvitationRequest invitationId
                 */

                /**
                 * Constructs a new DeleteGroupInvitationRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteGroupInvitationRequest.
                 * @implements IDeleteGroupInvitationRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteGroupInvitationRequest=} [properties] Properties to set
                 */
                function DeleteGroupInvitationRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteGroupInvitationRequest invitationId.
                 * @member {string} invitationId
                 * @memberof im.turms.proto.DeleteGroupInvitationRequest
                 * @instance
                 */
                DeleteGroupInvitationRequest.prototype.invitationId = "0";

                /**
                 * Encodes the specified DeleteGroupInvitationRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupInvitationRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteGroupInvitationRequest
                 * @static
                 * @param {im.turms.proto.IDeleteGroupInvitationRequest} message DeleteGroupInvitationRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteGroupInvitationRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.invitationId != null && Object.hasOwnProperty.call(message, "invitationId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.invitationId);
                    return writer;
                };

                /**
                 * Decodes a DeleteGroupInvitationRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteGroupInvitationRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteGroupInvitationRequest} DeleteGroupInvitationRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteGroupInvitationRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteGroupInvitationRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.invitationId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteGroupInvitationRequest;
            })();

            proto.DeleteGroupJoinQuestionRequest = (function() {

                /**
                 * Properties of a DeleteGroupJoinQuestionRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteGroupJoinQuestionRequest
                 * @property {string|null} [questionId] DeleteGroupJoinQuestionRequest questionId
                 */

                /**
                 * Constructs a new DeleteGroupJoinQuestionRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteGroupJoinQuestionRequest.
                 * @implements IDeleteGroupJoinQuestionRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteGroupJoinQuestionRequest=} [properties] Properties to set
                 */
                function DeleteGroupJoinQuestionRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteGroupJoinQuestionRequest questionId.
                 * @member {string} questionId
                 * @memberof im.turms.proto.DeleteGroupJoinQuestionRequest
                 * @instance
                 */
                DeleteGroupJoinQuestionRequest.prototype.questionId = "0";

                /**
                 * Encodes the specified DeleteGroupJoinQuestionRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupJoinQuestionRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteGroupJoinQuestionRequest
                 * @static
                 * @param {im.turms.proto.IDeleteGroupJoinQuestionRequest} message DeleteGroupJoinQuestionRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteGroupJoinQuestionRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.questionId != null && Object.hasOwnProperty.call(message, "questionId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.questionId);
                    return writer;
                };

                /**
                 * Decodes a DeleteGroupJoinQuestionRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteGroupJoinQuestionRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteGroupJoinQuestionRequest} DeleteGroupJoinQuestionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteGroupJoinQuestionRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteGroupJoinQuestionRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.questionId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteGroupJoinQuestionRequest;
            })();

            proto.DeleteGroupJoinRequestRequest = (function() {

                /**
                 * Properties of a DeleteGroupJoinRequestRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteGroupJoinRequestRequest
                 * @property {string|null} [requestId] DeleteGroupJoinRequestRequest requestId
                 */

                /**
                 * Constructs a new DeleteGroupJoinRequestRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteGroupJoinRequestRequest.
                 * @implements IDeleteGroupJoinRequestRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteGroupJoinRequestRequest=} [properties] Properties to set
                 */
                function DeleteGroupJoinRequestRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteGroupJoinRequestRequest requestId.
                 * @member {string} requestId
                 * @memberof im.turms.proto.DeleteGroupJoinRequestRequest
                 * @instance
                 */
                DeleteGroupJoinRequestRequest.prototype.requestId = "0";

                /**
                 * Encodes the specified DeleteGroupJoinRequestRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupJoinRequestRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteGroupJoinRequestRequest
                 * @static
                 * @param {im.turms.proto.IDeleteGroupJoinRequestRequest} message DeleteGroupJoinRequestRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteGroupJoinRequestRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.requestId != null && Object.hasOwnProperty.call(message, "requestId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.requestId);
                    return writer;
                };

                /**
                 * Decodes a DeleteGroupJoinRequestRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteGroupJoinRequestRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteGroupJoinRequestRequest} DeleteGroupJoinRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteGroupJoinRequestRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteGroupJoinRequestRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.requestId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteGroupJoinRequestRequest;
            })();

            proto.QueryGroupInvitationsRequest = (function() {

                /**
                 * Properties of a QueryGroupInvitationsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryGroupInvitationsRequest
                 * @property {google.protobuf.IInt64Value|null} [groupId] QueryGroupInvitationsRequest groupId
                 * @property {google.protobuf.IBoolValue|null} [areSentByMe] QueryGroupInvitationsRequest areSentByMe
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryGroupInvitationsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryGroupInvitationsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryGroupInvitationsRequest.
                 * @implements IQueryGroupInvitationsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryGroupInvitationsRequest=} [properties] Properties to set
                 */
                function QueryGroupInvitationsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryGroupInvitationsRequest groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.QueryGroupInvitationsRequest
                 * @instance
                 */
                QueryGroupInvitationsRequest.prototype.groupId = null;

                /**
                 * QueryGroupInvitationsRequest areSentByMe.
                 * @member {google.protobuf.IBoolValue|null|undefined} areSentByMe
                 * @memberof im.turms.proto.QueryGroupInvitationsRequest
                 * @instance
                 */
                QueryGroupInvitationsRequest.prototype.areSentByMe = null;

                /**
                 * QueryGroupInvitationsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryGroupInvitationsRequest
                 * @instance
                 */
                QueryGroupInvitationsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryGroupInvitationsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupInvitationsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryGroupInvitationsRequest
                 * @static
                 * @param {im.turms.proto.IQueryGroupInvitationsRequest} message QueryGroupInvitationsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryGroupInvitationsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.areSentByMe != null && Object.hasOwnProperty.call(message, "areSentByMe"))
                        $root.google.protobuf.BoolValue.encode(message.areSentByMe, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryGroupInvitationsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryGroupInvitationsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryGroupInvitationsRequest} QueryGroupInvitationsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryGroupInvitationsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryGroupInvitationsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.areSentByMe = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryGroupInvitationsRequest;
            })();

            proto.QueryGroupJoinQuestionsRequest = (function() {

                /**
                 * Properties of a QueryGroupJoinQuestionsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryGroupJoinQuestionsRequest
                 * @property {string|null} [groupId] QueryGroupJoinQuestionsRequest groupId
                 * @property {boolean|null} [withAnswers] QueryGroupJoinQuestionsRequest withAnswers
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryGroupJoinQuestionsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryGroupJoinQuestionsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryGroupJoinQuestionsRequest.
                 * @implements IQueryGroupJoinQuestionsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryGroupJoinQuestionsRequest=} [properties] Properties to set
                 */
                function QueryGroupJoinQuestionsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryGroupJoinQuestionsRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.QueryGroupJoinQuestionsRequest
                 * @instance
                 */
                QueryGroupJoinQuestionsRequest.prototype.groupId = "0";

                /**
                 * QueryGroupJoinQuestionsRequest withAnswers.
                 * @member {boolean} withAnswers
                 * @memberof im.turms.proto.QueryGroupJoinQuestionsRequest
                 * @instance
                 */
                QueryGroupJoinQuestionsRequest.prototype.withAnswers = false;

                /**
                 * QueryGroupJoinQuestionsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryGroupJoinQuestionsRequest
                 * @instance
                 */
                QueryGroupJoinQuestionsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryGroupJoinQuestionsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupJoinQuestionsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryGroupJoinQuestionsRequest
                 * @static
                 * @param {im.turms.proto.IQueryGroupJoinQuestionsRequest} message QueryGroupJoinQuestionsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryGroupJoinQuestionsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.withAnswers != null && Object.hasOwnProperty.call(message, "withAnswers"))
                        writer.uint32(/* id 2, wireType 0 =*/16).bool(message.withAnswers);
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryGroupJoinQuestionsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryGroupJoinQuestionsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryGroupJoinQuestionsRequest} QueryGroupJoinQuestionsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryGroupJoinQuestionsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryGroupJoinQuestionsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.withAnswers = reader.bool();
                            break;
                        case 3:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryGroupJoinQuestionsRequest;
            })();

            proto.QueryGroupJoinRequestsRequest = (function() {

                /**
                 * Properties of a QueryGroupJoinRequestsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryGroupJoinRequestsRequest
                 * @property {google.protobuf.IInt64Value|null} [groupId] QueryGroupJoinRequestsRequest groupId
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryGroupJoinRequestsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryGroupJoinRequestsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryGroupJoinRequestsRequest.
                 * @implements IQueryGroupJoinRequestsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryGroupJoinRequestsRequest=} [properties] Properties to set
                 */
                function QueryGroupJoinRequestsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryGroupJoinRequestsRequest groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.QueryGroupJoinRequestsRequest
                 * @instance
                 */
                QueryGroupJoinRequestsRequest.prototype.groupId = null;

                /**
                 * QueryGroupJoinRequestsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryGroupJoinRequestsRequest
                 * @instance
                 */
                QueryGroupJoinRequestsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryGroupJoinRequestsRequest message. Does not implicitly {@link im.turms.proto.QueryGroupJoinRequestsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryGroupJoinRequestsRequest
                 * @static
                 * @param {im.turms.proto.IQueryGroupJoinRequestsRequest} message QueryGroupJoinRequestsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryGroupJoinRequestsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryGroupJoinRequestsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryGroupJoinRequestsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryGroupJoinRequestsRequest} QueryGroupJoinRequestsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryGroupJoinRequestsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryGroupJoinRequestsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryGroupJoinRequestsRequest;
            })();

            proto.UpdateGroupJoinQuestionRequest = (function() {

                /**
                 * Properties of an UpdateGroupJoinQuestionRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateGroupJoinQuestionRequest
                 * @property {string|null} [questionId] UpdateGroupJoinQuestionRequest questionId
                 * @property {google.protobuf.IStringValue|null} [question] UpdateGroupJoinQuestionRequest question
                 * @property {Array.<string>|null} [answers] UpdateGroupJoinQuestionRequest answers
                 * @property {google.protobuf.IInt32Value|null} [score] UpdateGroupJoinQuestionRequest score
                 */

                /**
                 * Constructs a new UpdateGroupJoinQuestionRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateGroupJoinQuestionRequest.
                 * @implements IUpdateGroupJoinQuestionRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateGroupJoinQuestionRequest=} [properties] Properties to set
                 */
                function UpdateGroupJoinQuestionRequest(properties) {
                    this.answers = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateGroupJoinQuestionRequest questionId.
                 * @member {string} questionId
                 * @memberof im.turms.proto.UpdateGroupJoinQuestionRequest
                 * @instance
                 */
                UpdateGroupJoinQuestionRequest.prototype.questionId = "0";

                /**
                 * UpdateGroupJoinQuestionRequest question.
                 * @member {google.protobuf.IStringValue|null|undefined} question
                 * @memberof im.turms.proto.UpdateGroupJoinQuestionRequest
                 * @instance
                 */
                UpdateGroupJoinQuestionRequest.prototype.question = null;

                /**
                 * UpdateGroupJoinQuestionRequest answers.
                 * @member {Array.<string>} answers
                 * @memberof im.turms.proto.UpdateGroupJoinQuestionRequest
                 * @instance
                 */
                UpdateGroupJoinQuestionRequest.prototype.answers = $util.emptyArray;

                /**
                 * UpdateGroupJoinQuestionRequest score.
                 * @member {google.protobuf.IInt32Value|null|undefined} score
                 * @memberof im.turms.proto.UpdateGroupJoinQuestionRequest
                 * @instance
                 */
                UpdateGroupJoinQuestionRequest.prototype.score = null;

                /**
                 * Encodes the specified UpdateGroupJoinQuestionRequest message. Does not implicitly {@link im.turms.proto.UpdateGroupJoinQuestionRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateGroupJoinQuestionRequest
                 * @static
                 * @param {im.turms.proto.IUpdateGroupJoinQuestionRequest} message UpdateGroupJoinQuestionRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateGroupJoinQuestionRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.questionId != null && Object.hasOwnProperty.call(message, "questionId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.questionId);
                    if (message.question != null && Object.hasOwnProperty.call(message, "question"))
                        $root.google.protobuf.StringValue.encode(message.question, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.answers != null && message.answers.length)
                        for (var i = 0; i < message.answers.length; ++i)
                            writer.uint32(/* id 3, wireType 2 =*/26).string(message.answers[i]);
                    if (message.score != null && Object.hasOwnProperty.call(message, "score"))
                        $root.google.protobuf.Int32Value.encode(message.score, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an UpdateGroupJoinQuestionRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateGroupJoinQuestionRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateGroupJoinQuestionRequest} UpdateGroupJoinQuestionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateGroupJoinQuestionRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateGroupJoinQuestionRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.questionId = reader.int64().toString();
                            break;
                        case 2:
                            message.question = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            if (!(message.answers && message.answers.length))
                                message.answers = [];
                            message.answers.push(reader.string());
                            break;
                        case 4:
                            message.score = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateGroupJoinQuestionRequest;
            })();

            proto.CreateGroupMemberRequest = (function() {

                /**
                 * Properties of a CreateGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateGroupMemberRequest
                 * @property {string|null} [groupId] CreateGroupMemberRequest groupId
                 * @property {string|null} [userId] CreateGroupMemberRequest userId
                 * @property {google.protobuf.IStringValue|null} [name] CreateGroupMemberRequest name
                 * @property {im.turms.proto.GroupMemberRole|null} [role] CreateGroupMemberRequest role
                 * @property {google.protobuf.IInt64Value|null} [muteEndDate] CreateGroupMemberRequest muteEndDate
                 */

                /**
                 * Constructs a new CreateGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateGroupMemberRequest.
                 * @implements ICreateGroupMemberRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateGroupMemberRequest=} [properties] Properties to set
                 */
                function CreateGroupMemberRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateGroupMemberRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.CreateGroupMemberRequest
                 * @instance
                 */
                CreateGroupMemberRequest.prototype.groupId = "0";

                /**
                 * CreateGroupMemberRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.CreateGroupMemberRequest
                 * @instance
                 */
                CreateGroupMemberRequest.prototype.userId = "0";

                /**
                 * CreateGroupMemberRequest name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.CreateGroupMemberRequest
                 * @instance
                 */
                CreateGroupMemberRequest.prototype.name = null;

                /**
                 * CreateGroupMemberRequest role.
                 * @member {im.turms.proto.GroupMemberRole} role
                 * @memberof im.turms.proto.CreateGroupMemberRequest
                 * @instance
                 */
                CreateGroupMemberRequest.prototype.role = 0;

                /**
                 * CreateGroupMemberRequest muteEndDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} muteEndDate
                 * @memberof im.turms.proto.CreateGroupMemberRequest
                 * @instance
                 */
                CreateGroupMemberRequest.prototype.muteEndDate = null;

                /**
                 * Encodes the specified CreateGroupMemberRequest message. Does not implicitly {@link im.turms.proto.CreateGroupMemberRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateGroupMemberRequest
                 * @static
                 * @param {im.turms.proto.ICreateGroupMemberRequest} message CreateGroupMemberRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateGroupMemberRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int64(message.userId);
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.role != null && Object.hasOwnProperty.call(message, "role"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.role);
                    if (message.muteEndDate != null && Object.hasOwnProperty.call(message, "muteEndDate"))
                        $root.google.protobuf.Int64Value.encode(message.muteEndDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a CreateGroupMemberRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateGroupMemberRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateGroupMemberRequest} CreateGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateGroupMemberRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateGroupMemberRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.userId = reader.int64().toString();
                            break;
                        case 3:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.role = reader.int32();
                            break;
                        case 5:
                            message.muteEndDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateGroupMemberRequest;
            })();

            proto.DeleteGroupMemberRequest = (function() {

                /**
                 * Properties of a DeleteGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteGroupMemberRequest
                 * @property {string|null} [groupId] DeleteGroupMemberRequest groupId
                 * @property {string|null} [memberId] DeleteGroupMemberRequest memberId
                 * @property {google.protobuf.IInt64Value|null} [successorId] DeleteGroupMemberRequest successorId
                 * @property {google.protobuf.IBoolValue|null} [quitAfterTransfer] DeleteGroupMemberRequest quitAfterTransfer
                 */

                /**
                 * Constructs a new DeleteGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteGroupMemberRequest.
                 * @implements IDeleteGroupMemberRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteGroupMemberRequest=} [properties] Properties to set
                 */
                function DeleteGroupMemberRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteGroupMemberRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.DeleteGroupMemberRequest
                 * @instance
                 */
                DeleteGroupMemberRequest.prototype.groupId = "0";

                /**
                 * DeleteGroupMemberRequest memberId.
                 * @member {string} memberId
                 * @memberof im.turms.proto.DeleteGroupMemberRequest
                 * @instance
                 */
                DeleteGroupMemberRequest.prototype.memberId = "0";

                /**
                 * DeleteGroupMemberRequest successorId.
                 * @member {google.protobuf.IInt64Value|null|undefined} successorId
                 * @memberof im.turms.proto.DeleteGroupMemberRequest
                 * @instance
                 */
                DeleteGroupMemberRequest.prototype.successorId = null;

                /**
                 * DeleteGroupMemberRequest quitAfterTransfer.
                 * @member {google.protobuf.IBoolValue|null|undefined} quitAfterTransfer
                 * @memberof im.turms.proto.DeleteGroupMemberRequest
                 * @instance
                 */
                DeleteGroupMemberRequest.prototype.quitAfterTransfer = null;

                /**
                 * Encodes the specified DeleteGroupMemberRequest message. Does not implicitly {@link im.turms.proto.DeleteGroupMemberRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteGroupMemberRequest
                 * @static
                 * @param {im.turms.proto.IDeleteGroupMemberRequest} message DeleteGroupMemberRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteGroupMemberRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.memberId != null && Object.hasOwnProperty.call(message, "memberId"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int64(message.memberId);
                    if (message.successorId != null && Object.hasOwnProperty.call(message, "successorId"))
                        $root.google.protobuf.Int64Value.encode(message.successorId, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.quitAfterTransfer != null && Object.hasOwnProperty.call(message, "quitAfterTransfer"))
                        $root.google.protobuf.BoolValue.encode(message.quitAfterTransfer, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a DeleteGroupMemberRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteGroupMemberRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteGroupMemberRequest} DeleteGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteGroupMemberRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteGroupMemberRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.memberId = reader.int64().toString();
                            break;
                        case 3:
                            message.successorId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.quitAfterTransfer = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteGroupMemberRequest;
            })();

            proto.QueryGroupMembersRequest = (function() {

                /**
                 * Properties of a QueryGroupMembersRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryGroupMembersRequest
                 * @property {string|null} [groupId] QueryGroupMembersRequest groupId
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryGroupMembersRequest lastUpdatedDate
                 * @property {Array.<string>|null} [memberIds] QueryGroupMembersRequest memberIds
                 * @property {google.protobuf.IBoolValue|null} [withStatus] QueryGroupMembersRequest withStatus
                 */

                /**
                 * Constructs a new QueryGroupMembersRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryGroupMembersRequest.
                 * @implements IQueryGroupMembersRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryGroupMembersRequest=} [properties] Properties to set
                 */
                function QueryGroupMembersRequest(properties) {
                    this.memberIds = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryGroupMembersRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.QueryGroupMembersRequest
                 * @instance
                 */
                QueryGroupMembersRequest.prototype.groupId = "0";

                /**
                 * QueryGroupMembersRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryGroupMembersRequest
                 * @instance
                 */
                QueryGroupMembersRequest.prototype.lastUpdatedDate = null;

                /**
                 * QueryGroupMembersRequest memberIds.
                 * @member {Array.<string>} memberIds
                 * @memberof im.turms.proto.QueryGroupMembersRequest
                 * @instance
                 */
                QueryGroupMembersRequest.prototype.memberIds = $util.emptyArray;

                /**
                 * QueryGroupMembersRequest withStatus.
                 * @member {google.protobuf.IBoolValue|null|undefined} withStatus
                 * @memberof im.turms.proto.QueryGroupMembersRequest
                 * @instance
                 */
                QueryGroupMembersRequest.prototype.withStatus = null;

                /**
                 * Encodes the specified QueryGroupMembersRequest message. Does not implicitly {@link im.turms.proto.QueryGroupMembersRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryGroupMembersRequest
                 * @static
                 * @param {im.turms.proto.IQueryGroupMembersRequest} message QueryGroupMembersRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryGroupMembersRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.memberIds != null && message.memberIds.length) {
                        writer.uint32(/* id 3, wireType 2 =*/26).fork();
                        for (var i = 0; i < message.memberIds.length; ++i)
                            writer.int64(message.memberIds[i]);
                        writer.ldelim();
                    }
                    if (message.withStatus != null && Object.hasOwnProperty.call(message, "withStatus"))
                        $root.google.protobuf.BoolValue.encode(message.withStatus, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryGroupMembersRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryGroupMembersRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryGroupMembersRequest} QueryGroupMembersRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryGroupMembersRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryGroupMembersRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            if (!(message.memberIds && message.memberIds.length))
                                message.memberIds = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.memberIds.push(reader.int64().toString());
                            } else
                                message.memberIds.push(reader.int64().toString());
                            break;
                        case 4:
                            message.withStatus = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryGroupMembersRequest;
            })();

            proto.UpdateGroupMemberRequest = (function() {

                /**
                 * Properties of an UpdateGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateGroupMemberRequest
                 * @property {string|null} [groupId] UpdateGroupMemberRequest groupId
                 * @property {string|null} [memberId] UpdateGroupMemberRequest memberId
                 * @property {google.protobuf.IStringValue|null} [name] UpdateGroupMemberRequest name
                 * @property {im.turms.proto.GroupMemberRole|null} [role] UpdateGroupMemberRequest role
                 * @property {google.protobuf.IInt64Value|null} [muteEndDate] UpdateGroupMemberRequest muteEndDate
                 */

                /**
                 * Constructs a new UpdateGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateGroupMemberRequest.
                 * @implements IUpdateGroupMemberRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateGroupMemberRequest=} [properties] Properties to set
                 */
                function UpdateGroupMemberRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateGroupMemberRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.UpdateGroupMemberRequest
                 * @instance
                 */
                UpdateGroupMemberRequest.prototype.groupId = "0";

                /**
                 * UpdateGroupMemberRequest memberId.
                 * @member {string} memberId
                 * @memberof im.turms.proto.UpdateGroupMemberRequest
                 * @instance
                 */
                UpdateGroupMemberRequest.prototype.memberId = "0";

                /**
                 * UpdateGroupMemberRequest name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.UpdateGroupMemberRequest
                 * @instance
                 */
                UpdateGroupMemberRequest.prototype.name = null;

                /**
                 * UpdateGroupMemberRequest role.
                 * @member {im.turms.proto.GroupMemberRole} role
                 * @memberof im.turms.proto.UpdateGroupMemberRequest
                 * @instance
                 */
                UpdateGroupMemberRequest.prototype.role = 0;

                /**
                 * UpdateGroupMemberRequest muteEndDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} muteEndDate
                 * @memberof im.turms.proto.UpdateGroupMemberRequest
                 * @instance
                 */
                UpdateGroupMemberRequest.prototype.muteEndDate = null;

                /**
                 * Encodes the specified UpdateGroupMemberRequest message. Does not implicitly {@link im.turms.proto.UpdateGroupMemberRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateGroupMemberRequest
                 * @static
                 * @param {im.turms.proto.IUpdateGroupMemberRequest} message UpdateGroupMemberRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateGroupMemberRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.memberId != null && Object.hasOwnProperty.call(message, "memberId"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int64(message.memberId);
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.role != null && Object.hasOwnProperty.call(message, "role"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.role);
                    if (message.muteEndDate != null && Object.hasOwnProperty.call(message, "muteEndDate"))
                        $root.google.protobuf.Int64Value.encode(message.muteEndDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an UpdateGroupMemberRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateGroupMemberRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateGroupMemberRequest} UpdateGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateGroupMemberRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateGroupMemberRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.memberId = reader.int64().toString();
                            break;
                        case 3:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.role = reader.int32();
                            break;
                        case 5:
                            message.muteEndDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateGroupMemberRequest;
            })();

            proto.QueryGroupRequest = (function() {

                /**
                 * Properties of a QueryGroupRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryGroupRequest
                 * @property {string|null} [groupId] QueryGroupRequest groupId
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryGroupRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryGroupRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryGroupRequest.
                 * @implements IQueryGroupRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryGroupRequest=} [properties] Properties to set
                 */
                function QueryGroupRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryGroupRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.QueryGroupRequest
                 * @instance
                 */
                QueryGroupRequest.prototype.groupId = "0";

                /**
                 * QueryGroupRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryGroupRequest
                 * @instance
                 */
                QueryGroupRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryGroupRequest message. Does not implicitly {@link im.turms.proto.QueryGroupRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryGroupRequest
                 * @static
                 * @param {im.turms.proto.IQueryGroupRequest} message QueryGroupRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryGroupRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryGroupRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryGroupRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryGroupRequest} QueryGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryGroupRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryGroupRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryGroupRequest;
            })();

            proto.QueryJoinedGroupIdsRequest = (function() {

                /**
                 * Properties of a QueryJoinedGroupIdsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryJoinedGroupIdsRequest
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryJoinedGroupIdsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryJoinedGroupIdsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryJoinedGroupIdsRequest.
                 * @implements IQueryJoinedGroupIdsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryJoinedGroupIdsRequest=} [properties] Properties to set
                 */
                function QueryJoinedGroupIdsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryJoinedGroupIdsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryJoinedGroupIdsRequest
                 * @instance
                 */
                QueryJoinedGroupIdsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryJoinedGroupIdsRequest message. Does not implicitly {@link im.turms.proto.QueryJoinedGroupIdsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryJoinedGroupIdsRequest
                 * @static
                 * @param {im.turms.proto.IQueryJoinedGroupIdsRequest} message QueryJoinedGroupIdsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryJoinedGroupIdsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryJoinedGroupIdsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryJoinedGroupIdsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryJoinedGroupIdsRequest} QueryJoinedGroupIdsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryJoinedGroupIdsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryJoinedGroupIdsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryJoinedGroupIdsRequest;
            })();

            proto.QueryJoinedGroupInfosRequest = (function() {

                /**
                 * Properties of a QueryJoinedGroupInfosRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryJoinedGroupInfosRequest
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryJoinedGroupInfosRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryJoinedGroupInfosRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryJoinedGroupInfosRequest.
                 * @implements IQueryJoinedGroupInfosRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryJoinedGroupInfosRequest=} [properties] Properties to set
                 */
                function QueryJoinedGroupInfosRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryJoinedGroupInfosRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryJoinedGroupInfosRequest
                 * @instance
                 */
                QueryJoinedGroupInfosRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryJoinedGroupInfosRequest message. Does not implicitly {@link im.turms.proto.QueryJoinedGroupInfosRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryJoinedGroupInfosRequest
                 * @static
                 * @param {im.turms.proto.IQueryJoinedGroupInfosRequest} message QueryJoinedGroupInfosRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryJoinedGroupInfosRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryJoinedGroupInfosRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryJoinedGroupInfosRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryJoinedGroupInfosRequest} QueryJoinedGroupInfosRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryJoinedGroupInfosRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryJoinedGroupInfosRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryJoinedGroupInfosRequest;
            })();

            proto.UpdateGroupRequest = (function() {

                /**
                 * Properties of an UpdateGroupRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateGroupRequest
                 * @property {string|null} [groupId] UpdateGroupRequest groupId
                 * @property {google.protobuf.IStringValue|null} [groupName] UpdateGroupRequest groupName
                 * @property {google.protobuf.IStringValue|null} [intro] UpdateGroupRequest intro
                 * @property {google.protobuf.IStringValue|null} [announcement] UpdateGroupRequest announcement
                 * @property {google.protobuf.IInt32Value|null} [minimumScore] UpdateGroupRequest minimumScore
                 * @property {google.protobuf.IInt64Value|null} [groupTypeId] UpdateGroupRequest groupTypeId
                 * @property {google.protobuf.IInt64Value|null} [muteEndDate] UpdateGroupRequest muteEndDate
                 * @property {google.protobuf.IInt64Value|null} [successorId] UpdateGroupRequest successorId
                 * @property {google.protobuf.IBoolValue|null} [quitAfterTransfer] UpdateGroupRequest quitAfterTransfer
                 */

                /**
                 * Constructs a new UpdateGroupRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateGroupRequest.
                 * @implements IUpdateGroupRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateGroupRequest=} [properties] Properties to set
                 */
                function UpdateGroupRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateGroupRequest groupId.
                 * @member {string} groupId
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.groupId = "0";

                /**
                 * UpdateGroupRequest groupName.
                 * @member {google.protobuf.IStringValue|null|undefined} groupName
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.groupName = null;

                /**
                 * UpdateGroupRequest intro.
                 * @member {google.protobuf.IStringValue|null|undefined} intro
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.intro = null;

                /**
                 * UpdateGroupRequest announcement.
                 * @member {google.protobuf.IStringValue|null|undefined} announcement
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.announcement = null;

                /**
                 * UpdateGroupRequest minimumScore.
                 * @member {google.protobuf.IInt32Value|null|undefined} minimumScore
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.minimumScore = null;

                /**
                 * UpdateGroupRequest groupTypeId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupTypeId
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.groupTypeId = null;

                /**
                 * UpdateGroupRequest muteEndDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} muteEndDate
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.muteEndDate = null;

                /**
                 * UpdateGroupRequest successorId.
                 * @member {google.protobuf.IInt64Value|null|undefined} successorId
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.successorId = null;

                /**
                 * UpdateGroupRequest quitAfterTransfer.
                 * @member {google.protobuf.IBoolValue|null|undefined} quitAfterTransfer
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @instance
                 */
                UpdateGroupRequest.prototype.quitAfterTransfer = null;

                /**
                 * Encodes the specified UpdateGroupRequest message. Does not implicitly {@link im.turms.proto.UpdateGroupRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @static
                 * @param {im.turms.proto.IUpdateGroupRequest} message UpdateGroupRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateGroupRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.groupId);
                    if (message.groupName != null && Object.hasOwnProperty.call(message, "groupName"))
                        $root.google.protobuf.StringValue.encode(message.groupName, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.intro != null && Object.hasOwnProperty.call(message, "intro"))
                        $root.google.protobuf.StringValue.encode(message.intro, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.announcement != null && Object.hasOwnProperty.call(message, "announcement"))
                        $root.google.protobuf.StringValue.encode(message.announcement, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.minimumScore != null && Object.hasOwnProperty.call(message, "minimumScore"))
                        $root.google.protobuf.Int32Value.encode(message.minimumScore, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.groupTypeId != null && Object.hasOwnProperty.call(message, "groupTypeId"))
                        $root.google.protobuf.Int64Value.encode(message.groupTypeId, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.muteEndDate != null && Object.hasOwnProperty.call(message, "muteEndDate"))
                        $root.google.protobuf.Int64Value.encode(message.muteEndDate, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.successorId != null && Object.hasOwnProperty.call(message, "successorId"))
                        $root.google.protobuf.Int64Value.encode(message.successorId, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    if (message.quitAfterTransfer != null && Object.hasOwnProperty.call(message, "quitAfterTransfer"))
                        $root.google.protobuf.BoolValue.encode(message.quitAfterTransfer, writer.uint32(/* id 9, wireType 2 =*/74).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an UpdateGroupRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateGroupRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateGroupRequest} UpdateGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateGroupRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateGroupRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupId = reader.int64().toString();
                            break;
                        case 2:
                            message.groupName = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.intro = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.announcement = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.minimumScore = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.groupTypeId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.muteEndDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.successorId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 9:
                            message.quitAfterTransfer = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateGroupRequest;
            })();

            proto.CreateMessageRequest = (function() {

                /**
                 * Properties of a CreateMessageRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateMessageRequest
                 * @property {google.protobuf.IInt64Value|null} [messageId] CreateMessageRequest messageId
                 * @property {google.protobuf.IBoolValue|null} [isSystemMessage] CreateMessageRequest isSystemMessage
                 * @property {google.protobuf.IInt64Value|null} [groupId] CreateMessageRequest groupId
                 * @property {google.protobuf.IInt64Value|null} [recipientId] CreateMessageRequest recipientId
                 * @property {string|null} [deliveryDate] CreateMessageRequest deliveryDate
                 * @property {google.protobuf.IStringValue|null} [text] CreateMessageRequest text
                 * @property {Array.<Uint8Array>|null} [records] CreateMessageRequest records
                 * @property {google.protobuf.IInt32Value|null} [burnAfter] CreateMessageRequest burnAfter
                 */

                /**
                 * Constructs a new CreateMessageRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateMessageRequest.
                 * @implements ICreateMessageRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateMessageRequest=} [properties] Properties to set
                 */
                function CreateMessageRequest(properties) {
                    this.records = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateMessageRequest messageId.
                 * @member {google.protobuf.IInt64Value|null|undefined} messageId
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.messageId = null;

                /**
                 * CreateMessageRequest isSystemMessage.
                 * @member {google.protobuf.IBoolValue|null|undefined} isSystemMessage
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.isSystemMessage = null;

                /**
                 * CreateMessageRequest groupId.
                 * @member {google.protobuf.IInt64Value|null|undefined} groupId
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.groupId = null;

                /**
                 * CreateMessageRequest recipientId.
                 * @member {google.protobuf.IInt64Value|null|undefined} recipientId
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.recipientId = null;

                /**
                 * CreateMessageRequest deliveryDate.
                 * @member {string} deliveryDate
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.deliveryDate = "0";

                /**
                 * CreateMessageRequest text.
                 * @member {google.protobuf.IStringValue|null|undefined} text
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.text = null;

                /**
                 * CreateMessageRequest records.
                 * @member {Array.<Uint8Array>} records
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.records = $util.emptyArray;

                /**
                 * CreateMessageRequest burnAfter.
                 * @member {google.protobuf.IInt32Value|null|undefined} burnAfter
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @instance
                 */
                CreateMessageRequest.prototype.burnAfter = null;

                /**
                 * Encodes the specified CreateMessageRequest message. Does not implicitly {@link im.turms.proto.CreateMessageRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @static
                 * @param {im.turms.proto.ICreateMessageRequest} message CreateMessageRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateMessageRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messageId != null && Object.hasOwnProperty.call(message, "messageId"))
                        $root.google.protobuf.Int64Value.encode(message.messageId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.isSystemMessage != null && Object.hasOwnProperty.call(message, "isSystemMessage"))
                        $root.google.protobuf.BoolValue.encode(message.isSystemMessage, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.groupId != null && Object.hasOwnProperty.call(message, "groupId"))
                        $root.google.protobuf.Int64Value.encode(message.groupId, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.recipientId != null && Object.hasOwnProperty.call(message, "recipientId"))
                        $root.google.protobuf.Int64Value.encode(message.recipientId, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.deliveryDate != null && Object.hasOwnProperty.call(message, "deliveryDate"))
                        writer.uint32(/* id 5, wireType 0 =*/40).int64(message.deliveryDate);
                    if (message.text != null && Object.hasOwnProperty.call(message, "text"))
                        $root.google.protobuf.StringValue.encode(message.text, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.records != null && message.records.length)
                        for (var i = 0; i < message.records.length; ++i)
                            writer.uint32(/* id 7, wireType 2 =*/58).bytes(message.records[i]);
                    if (message.burnAfter != null && Object.hasOwnProperty.call(message, "burnAfter"))
                        $root.google.protobuf.Int32Value.encode(message.burnAfter, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a CreateMessageRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateMessageRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateMessageRequest} CreateMessageRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateMessageRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateMessageRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.messageId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.isSystemMessage = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.groupId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.recipientId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.deliveryDate = reader.int64().toString();
                            break;
                        case 6:
                            message.text = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 7:
                            if (!(message.records && message.records.length))
                                message.records = [];
                            message.records.push(reader.bytes());
                            break;
                        case 8:
                            message.burnAfter = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateMessageRequest;
            })();

            proto.QueryMessageStatusesRequest = (function() {

                /**
                 * Properties of a QueryMessageStatusesRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryMessageStatusesRequest
                 * @property {string|null} [messageId] QueryMessageStatusesRequest messageId
                 */

                /**
                 * Constructs a new QueryMessageStatusesRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryMessageStatusesRequest.
                 * @implements IQueryMessageStatusesRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryMessageStatusesRequest=} [properties] Properties to set
                 */
                function QueryMessageStatusesRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryMessageStatusesRequest messageId.
                 * @member {string} messageId
                 * @memberof im.turms.proto.QueryMessageStatusesRequest
                 * @instance
                 */
                QueryMessageStatusesRequest.prototype.messageId = "0";

                /**
                 * Encodes the specified QueryMessageStatusesRequest message. Does not implicitly {@link im.turms.proto.QueryMessageStatusesRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryMessageStatusesRequest
                 * @static
                 * @param {im.turms.proto.IQueryMessageStatusesRequest} message QueryMessageStatusesRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryMessageStatusesRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messageId != null && Object.hasOwnProperty.call(message, "messageId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.messageId);
                    return writer;
                };

                /**
                 * Decodes a QueryMessageStatusesRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryMessageStatusesRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryMessageStatusesRequest} QueryMessageStatusesRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryMessageStatusesRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryMessageStatusesRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.messageId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryMessageStatusesRequest;
            })();

            proto.QueryMessagesRequest = (function() {

                /**
                 * Properties of a QueryMessagesRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryMessagesRequest
                 * @property {Array.<string>|null} [ids] QueryMessagesRequest ids
                 * @property {google.protobuf.IInt32Value|null} [size] QueryMessagesRequest size
                 * @property {google.protobuf.IBoolValue|null} [areGroupMessages] QueryMessagesRequest areGroupMessages
                 * @property {google.protobuf.IBoolValue|null} [areSystemMessages] QueryMessagesRequest areSystemMessages
                 * @property {google.protobuf.IInt64Value|null} [fromId] QueryMessagesRequest fromId
                 * @property {google.protobuf.IInt64Value|null} [deliveryDateAfter] QueryMessagesRequest deliveryDateAfter
                 * @property {google.protobuf.IInt64Value|null} [deliveryDateBefore] QueryMessagesRequest deliveryDateBefore
                 * @property {im.turms.proto.MessageDeliveryStatus|null} [deliveryStatus] QueryMessagesRequest deliveryStatus
                 */

                /**
                 * Constructs a new QueryMessagesRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryMessagesRequest.
                 * @implements IQueryMessagesRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryMessagesRequest=} [properties] Properties to set
                 */
                function QueryMessagesRequest(properties) {
                    this.ids = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryMessagesRequest ids.
                 * @member {Array.<string>} ids
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.ids = $util.emptyArray;

                /**
                 * QueryMessagesRequest size.
                 * @member {google.protobuf.IInt32Value|null|undefined} size
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.size = null;

                /**
                 * QueryMessagesRequest areGroupMessages.
                 * @member {google.protobuf.IBoolValue|null|undefined} areGroupMessages
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.areGroupMessages = null;

                /**
                 * QueryMessagesRequest areSystemMessages.
                 * @member {google.protobuf.IBoolValue|null|undefined} areSystemMessages
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.areSystemMessages = null;

                /**
                 * QueryMessagesRequest fromId.
                 * @member {google.protobuf.IInt64Value|null|undefined} fromId
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.fromId = null;

                /**
                 * QueryMessagesRequest deliveryDateAfter.
                 * @member {google.protobuf.IInt64Value|null|undefined} deliveryDateAfter
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.deliveryDateAfter = null;

                /**
                 * QueryMessagesRequest deliveryDateBefore.
                 * @member {google.protobuf.IInt64Value|null|undefined} deliveryDateBefore
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.deliveryDateBefore = null;

                /**
                 * QueryMessagesRequest deliveryStatus.
                 * @member {im.turms.proto.MessageDeliveryStatus} deliveryStatus
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @instance
                 */
                QueryMessagesRequest.prototype.deliveryStatus = 0;

                /**
                 * Encodes the specified QueryMessagesRequest message. Does not implicitly {@link im.turms.proto.QueryMessagesRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @static
                 * @param {im.turms.proto.IQueryMessagesRequest} message QueryMessagesRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryMessagesRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.ids != null && message.ids.length) {
                        writer.uint32(/* id 1, wireType 2 =*/10).fork();
                        for (var i = 0; i < message.ids.length; ++i)
                            writer.int64(message.ids[i]);
                        writer.ldelim();
                    }
                    if (message.size != null && Object.hasOwnProperty.call(message, "size"))
                        $root.google.protobuf.Int32Value.encode(message.size, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.areGroupMessages != null && Object.hasOwnProperty.call(message, "areGroupMessages"))
                        $root.google.protobuf.BoolValue.encode(message.areGroupMessages, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.areSystemMessages != null && Object.hasOwnProperty.call(message, "areSystemMessages"))
                        $root.google.protobuf.BoolValue.encode(message.areSystemMessages, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.fromId != null && Object.hasOwnProperty.call(message, "fromId"))
                        $root.google.protobuf.Int64Value.encode(message.fromId, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.deliveryDateAfter != null && Object.hasOwnProperty.call(message, "deliveryDateAfter"))
                        $root.google.protobuf.Int64Value.encode(message.deliveryDateAfter, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.deliveryDateBefore != null && Object.hasOwnProperty.call(message, "deliveryDateBefore"))
                        $root.google.protobuf.Int64Value.encode(message.deliveryDateBefore, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.deliveryStatus != null && Object.hasOwnProperty.call(message, "deliveryStatus"))
                        writer.uint32(/* id 8, wireType 0 =*/64).int32(message.deliveryStatus);
                    return writer;
                };

                /**
                 * Decodes a QueryMessagesRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryMessagesRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryMessagesRequest} QueryMessagesRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryMessagesRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryMessagesRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.ids && message.ids.length))
                                message.ids = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.ids.push(reader.int64().toString());
                            } else
                                message.ids.push(reader.int64().toString());
                            break;
                        case 2:
                            message.size = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.areGroupMessages = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.areSystemMessages = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.fromId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.deliveryDateAfter = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.deliveryDateBefore = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.deliveryStatus = reader.int32();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryMessagesRequest;
            })();

            proto.QueryPendingMessagesWithTotalRequest = (function() {

                /**
                 * Properties of a QueryPendingMessagesWithTotalRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryPendingMessagesWithTotalRequest
                 * @property {google.protobuf.IInt32Value|null} [size] QueryPendingMessagesWithTotalRequest size
                 */

                /**
                 * Constructs a new QueryPendingMessagesWithTotalRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryPendingMessagesWithTotalRequest.
                 * @implements IQueryPendingMessagesWithTotalRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryPendingMessagesWithTotalRequest=} [properties] Properties to set
                 */
                function QueryPendingMessagesWithTotalRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryPendingMessagesWithTotalRequest size.
                 * @member {google.protobuf.IInt32Value|null|undefined} size
                 * @memberof im.turms.proto.QueryPendingMessagesWithTotalRequest
                 * @instance
                 */
                QueryPendingMessagesWithTotalRequest.prototype.size = null;

                /**
                 * Encodes the specified QueryPendingMessagesWithTotalRequest message. Does not implicitly {@link im.turms.proto.QueryPendingMessagesWithTotalRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryPendingMessagesWithTotalRequest
                 * @static
                 * @param {im.turms.proto.IQueryPendingMessagesWithTotalRequest} message QueryPendingMessagesWithTotalRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryPendingMessagesWithTotalRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.size != null && Object.hasOwnProperty.call(message, "size"))
                        $root.google.protobuf.Int32Value.encode(message.size, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryPendingMessagesWithTotalRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryPendingMessagesWithTotalRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryPendingMessagesWithTotalRequest} QueryPendingMessagesWithTotalRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryPendingMessagesWithTotalRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryPendingMessagesWithTotalRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.size = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryPendingMessagesWithTotalRequest;
            })();

            proto.UpdateMessageRequest = (function() {

                /**
                 * Properties of an UpdateMessageRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateMessageRequest
                 * @property {string|null} [messageId] UpdateMessageRequest messageId
                 * @property {google.protobuf.IBoolValue|null} [isSystemMessage] UpdateMessageRequest isSystemMessage
                 * @property {google.protobuf.IStringValue|null} [text] UpdateMessageRequest text
                 * @property {Array.<Uint8Array>|null} [records] UpdateMessageRequest records
                 * @property {google.protobuf.IInt64Value|null} [recallDate] UpdateMessageRequest recallDate
                 * @property {google.protobuf.IInt64Value|null} [readDate] UpdateMessageRequest readDate
                 */

                /**
                 * Constructs a new UpdateMessageRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateMessageRequest.
                 * @implements IUpdateMessageRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateMessageRequest=} [properties] Properties to set
                 */
                function UpdateMessageRequest(properties) {
                    this.records = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateMessageRequest messageId.
                 * @member {string} messageId
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @instance
                 */
                UpdateMessageRequest.prototype.messageId = "0";

                /**
                 * UpdateMessageRequest isSystemMessage.
                 * @member {google.protobuf.IBoolValue|null|undefined} isSystemMessage
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @instance
                 */
                UpdateMessageRequest.prototype.isSystemMessage = null;

                /**
                 * UpdateMessageRequest text.
                 * @member {google.protobuf.IStringValue|null|undefined} text
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @instance
                 */
                UpdateMessageRequest.prototype.text = null;

                /**
                 * UpdateMessageRequest records.
                 * @member {Array.<Uint8Array>} records
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @instance
                 */
                UpdateMessageRequest.prototype.records = $util.emptyArray;

                /**
                 * UpdateMessageRequest recallDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} recallDate
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @instance
                 */
                UpdateMessageRequest.prototype.recallDate = null;

                /**
                 * UpdateMessageRequest readDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} readDate
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @instance
                 */
                UpdateMessageRequest.prototype.readDate = null;

                /**
                 * Encodes the specified UpdateMessageRequest message. Does not implicitly {@link im.turms.proto.UpdateMessageRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @static
                 * @param {im.turms.proto.IUpdateMessageRequest} message UpdateMessageRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateMessageRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messageId != null && Object.hasOwnProperty.call(message, "messageId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.messageId);
                    if (message.isSystemMessage != null && Object.hasOwnProperty.call(message, "isSystemMessage"))
                        $root.google.protobuf.BoolValue.encode(message.isSystemMessage, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.text != null && Object.hasOwnProperty.call(message, "text"))
                        $root.google.protobuf.StringValue.encode(message.text, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.records != null && message.records.length)
                        for (var i = 0; i < message.records.length; ++i)
                            writer.uint32(/* id 4, wireType 2 =*/34).bytes(message.records[i]);
                    if (message.recallDate != null && Object.hasOwnProperty.call(message, "recallDate"))
                        $root.google.protobuf.Int64Value.encode(message.recallDate, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.readDate != null && Object.hasOwnProperty.call(message, "readDate"))
                        $root.google.protobuf.Int64Value.encode(message.readDate, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an UpdateMessageRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateMessageRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateMessageRequest} UpdateMessageRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateMessageRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateMessageRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.messageId = reader.int64().toString();
                            break;
                        case 2:
                            message.isSystemMessage = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.text = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            if (!(message.records && message.records.length))
                                message.records = [];
                            message.records.push(reader.bytes());
                            break;
                        case 5:
                            message.recallDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.readDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateMessageRequest;
            })();

            proto.UpdateTypingStatusRequest = (function() {

                /**
                 * Properties of an UpdateTypingStatusRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateTypingStatusRequest
                 * @property {boolean|null} [isGroupMessage] UpdateTypingStatusRequest isGroupMessage
                 * @property {string|null} [toId] UpdateTypingStatusRequest toId
                 */

                /**
                 * Constructs a new UpdateTypingStatusRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateTypingStatusRequest.
                 * @implements IUpdateTypingStatusRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateTypingStatusRequest=} [properties] Properties to set
                 */
                function UpdateTypingStatusRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateTypingStatusRequest isGroupMessage.
                 * @member {boolean} isGroupMessage
                 * @memberof im.turms.proto.UpdateTypingStatusRequest
                 * @instance
                 */
                UpdateTypingStatusRequest.prototype.isGroupMessage = false;

                /**
                 * UpdateTypingStatusRequest toId.
                 * @member {string} toId
                 * @memberof im.turms.proto.UpdateTypingStatusRequest
                 * @instance
                 */
                UpdateTypingStatusRequest.prototype.toId = "0";

                /**
                 * Encodes the specified UpdateTypingStatusRequest message. Does not implicitly {@link im.turms.proto.UpdateTypingStatusRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateTypingStatusRequest
                 * @static
                 * @param {im.turms.proto.IUpdateTypingStatusRequest} message UpdateTypingStatusRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateTypingStatusRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.isGroupMessage != null && Object.hasOwnProperty.call(message, "isGroupMessage"))
                        writer.uint32(/* id 1, wireType 0 =*/8).bool(message.isGroupMessage);
                    if (message.toId != null && Object.hasOwnProperty.call(message, "toId"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int64(message.toId);
                    return writer;
                };

                /**
                 * Decodes an UpdateTypingStatusRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateTypingStatusRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateTypingStatusRequest} UpdateTypingStatusRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateTypingStatusRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateTypingStatusRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.isGroupMessage = reader.bool();
                            break;
                        case 2:
                            message.toId = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateTypingStatusRequest;
            })();

            proto.AckRequest = (function() {

                /**
                 * Properties of an AckRequest.
                 * @memberof im.turms.proto
                 * @interface IAckRequest
                 * @property {Array.<string>|null} [messageIds] AckRequest messageIds
                 */

                /**
                 * Constructs a new AckRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an AckRequest.
                 * @implements IAckRequest
                 * @constructor
                 * @param {im.turms.proto.IAckRequest=} [properties] Properties to set
                 */
                function AckRequest(properties) {
                    this.messageIds = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * AckRequest messageIds.
                 * @member {Array.<string>} messageIds
                 * @memberof im.turms.proto.AckRequest
                 * @instance
                 */
                AckRequest.prototype.messageIds = $util.emptyArray;

                /**
                 * Encodes the specified AckRequest message. Does not implicitly {@link im.turms.proto.AckRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.AckRequest
                 * @static
                 * @param {im.turms.proto.IAckRequest} message AckRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                AckRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.messageIds != null && message.messageIds.length) {
                        writer.uint32(/* id 1, wireType 2 =*/10).fork();
                        for (var i = 0; i < message.messageIds.length; ++i)
                            writer.int64(message.messageIds[i]);
                        writer.ldelim();
                    }
                    return writer;
                };

                /**
                 * Decodes an AckRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.AckRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.AckRequest} AckRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                AckRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.AckRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.messageIds && message.messageIds.length))
                                message.messageIds = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.messageIds.push(reader.int64().toString());
                            } else
                                message.messageIds.push(reader.int64().toString());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return AckRequest;
            })();

            proto.DeleteResourceRequest = (function() {

                /**
                 * Properties of a DeleteResourceRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteResourceRequest
                 * @property {im.turms.proto.ContentType|null} [contentType] DeleteResourceRequest contentType
                 * @property {google.protobuf.IStringValue|null} [keyStr] DeleteResourceRequest keyStr
                 * @property {google.protobuf.IInt64Value|null} [keyNum] DeleteResourceRequest keyNum
                 */

                /**
                 * Constructs a new DeleteResourceRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteResourceRequest.
                 * @implements IDeleteResourceRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteResourceRequest=} [properties] Properties to set
                 */
                function DeleteResourceRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteResourceRequest contentType.
                 * @member {im.turms.proto.ContentType} contentType
                 * @memberof im.turms.proto.DeleteResourceRequest
                 * @instance
                 */
                DeleteResourceRequest.prototype.contentType = 0;

                /**
                 * DeleteResourceRequest keyStr.
                 * @member {google.protobuf.IStringValue|null|undefined} keyStr
                 * @memberof im.turms.proto.DeleteResourceRequest
                 * @instance
                 */
                DeleteResourceRequest.prototype.keyStr = null;

                /**
                 * DeleteResourceRequest keyNum.
                 * @member {google.protobuf.IInt64Value|null|undefined} keyNum
                 * @memberof im.turms.proto.DeleteResourceRequest
                 * @instance
                 */
                DeleteResourceRequest.prototype.keyNum = null;

                /**
                 * Encodes the specified DeleteResourceRequest message. Does not implicitly {@link im.turms.proto.DeleteResourceRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteResourceRequest
                 * @static
                 * @param {im.turms.proto.IDeleteResourceRequest} message DeleteResourceRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteResourceRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.contentType != null && Object.hasOwnProperty.call(message, "contentType"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.contentType);
                    if (message.keyStr != null && Object.hasOwnProperty.call(message, "keyStr"))
                        $root.google.protobuf.StringValue.encode(message.keyStr, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.keyNum != null && Object.hasOwnProperty.call(message, "keyNum"))
                        $root.google.protobuf.Int64Value.encode(message.keyNum, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a DeleteResourceRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteResourceRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteResourceRequest} DeleteResourceRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteResourceRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteResourceRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.contentType = reader.int32();
                            break;
                        case 2:
                            message.keyStr = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.keyNum = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteResourceRequest;
            })();

            proto.QuerySignedGetUrlRequest = (function() {

                /**
                 * Properties of a QuerySignedGetUrlRequest.
                 * @memberof im.turms.proto
                 * @interface IQuerySignedGetUrlRequest
                 * @property {im.turms.proto.ContentType|null} [contentType] QuerySignedGetUrlRequest contentType
                 * @property {google.protobuf.IStringValue|null} [keyStr] QuerySignedGetUrlRequest keyStr
                 * @property {google.protobuf.IInt64Value|null} [keyNum] QuerySignedGetUrlRequest keyNum
                 */

                /**
                 * Constructs a new QuerySignedGetUrlRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QuerySignedGetUrlRequest.
                 * @implements IQuerySignedGetUrlRequest
                 * @constructor
                 * @param {im.turms.proto.IQuerySignedGetUrlRequest=} [properties] Properties to set
                 */
                function QuerySignedGetUrlRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QuerySignedGetUrlRequest contentType.
                 * @member {im.turms.proto.ContentType} contentType
                 * @memberof im.turms.proto.QuerySignedGetUrlRequest
                 * @instance
                 */
                QuerySignedGetUrlRequest.prototype.contentType = 0;

                /**
                 * QuerySignedGetUrlRequest keyStr.
                 * @member {google.protobuf.IStringValue|null|undefined} keyStr
                 * @memberof im.turms.proto.QuerySignedGetUrlRequest
                 * @instance
                 */
                QuerySignedGetUrlRequest.prototype.keyStr = null;

                /**
                 * QuerySignedGetUrlRequest keyNum.
                 * @member {google.protobuf.IInt64Value|null|undefined} keyNum
                 * @memberof im.turms.proto.QuerySignedGetUrlRequest
                 * @instance
                 */
                QuerySignedGetUrlRequest.prototype.keyNum = null;

                /**
                 * Encodes the specified QuerySignedGetUrlRequest message. Does not implicitly {@link im.turms.proto.QuerySignedGetUrlRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QuerySignedGetUrlRequest
                 * @static
                 * @param {im.turms.proto.IQuerySignedGetUrlRequest} message QuerySignedGetUrlRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QuerySignedGetUrlRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.contentType != null && Object.hasOwnProperty.call(message, "contentType"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.contentType);
                    if (message.keyStr != null && Object.hasOwnProperty.call(message, "keyStr"))
                        $root.google.protobuf.StringValue.encode(message.keyStr, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.keyNum != null && Object.hasOwnProperty.call(message, "keyNum"))
                        $root.google.protobuf.Int64Value.encode(message.keyNum, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QuerySignedGetUrlRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QuerySignedGetUrlRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QuerySignedGetUrlRequest} QuerySignedGetUrlRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QuerySignedGetUrlRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QuerySignedGetUrlRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.contentType = reader.int32();
                            break;
                        case 2:
                            message.keyStr = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.keyNum = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QuerySignedGetUrlRequest;
            })();

            proto.QuerySignedPutUrlRequest = (function() {

                /**
                 * Properties of a QuerySignedPutUrlRequest.
                 * @memberof im.turms.proto
                 * @interface IQuerySignedPutUrlRequest
                 * @property {im.turms.proto.ContentType|null} [contentType] QuerySignedPutUrlRequest contentType
                 * @property {google.protobuf.IStringValue|null} [keyStr] QuerySignedPutUrlRequest keyStr
                 * @property {google.protobuf.IInt64Value|null} [keyNum] QuerySignedPutUrlRequest keyNum
                 * @property {string|null} [contentLength] QuerySignedPutUrlRequest contentLength
                 */

                /**
                 * Constructs a new QuerySignedPutUrlRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QuerySignedPutUrlRequest.
                 * @implements IQuerySignedPutUrlRequest
                 * @constructor
                 * @param {im.turms.proto.IQuerySignedPutUrlRequest=} [properties] Properties to set
                 */
                function QuerySignedPutUrlRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QuerySignedPutUrlRequest contentType.
                 * @member {im.turms.proto.ContentType} contentType
                 * @memberof im.turms.proto.QuerySignedPutUrlRequest
                 * @instance
                 */
                QuerySignedPutUrlRequest.prototype.contentType = 0;

                /**
                 * QuerySignedPutUrlRequest keyStr.
                 * @member {google.protobuf.IStringValue|null|undefined} keyStr
                 * @memberof im.turms.proto.QuerySignedPutUrlRequest
                 * @instance
                 */
                QuerySignedPutUrlRequest.prototype.keyStr = null;

                /**
                 * QuerySignedPutUrlRequest keyNum.
                 * @member {google.protobuf.IInt64Value|null|undefined} keyNum
                 * @memberof im.turms.proto.QuerySignedPutUrlRequest
                 * @instance
                 */
                QuerySignedPutUrlRequest.prototype.keyNum = null;

                /**
                 * QuerySignedPutUrlRequest contentLength.
                 * @member {string} contentLength
                 * @memberof im.turms.proto.QuerySignedPutUrlRequest
                 * @instance
                 */
                QuerySignedPutUrlRequest.prototype.contentLength = "0";

                /**
                 * Encodes the specified QuerySignedPutUrlRequest message. Does not implicitly {@link im.turms.proto.QuerySignedPutUrlRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QuerySignedPutUrlRequest
                 * @static
                 * @param {im.turms.proto.IQuerySignedPutUrlRequest} message QuerySignedPutUrlRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QuerySignedPutUrlRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.contentType != null && Object.hasOwnProperty.call(message, "contentType"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.contentType);
                    if (message.keyStr != null && Object.hasOwnProperty.call(message, "keyStr"))
                        $root.google.protobuf.StringValue.encode(message.keyStr, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.keyNum != null && Object.hasOwnProperty.call(message, "keyNum"))
                        $root.google.protobuf.Int64Value.encode(message.keyNum, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.contentLength != null && Object.hasOwnProperty.call(message, "contentLength"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int64(message.contentLength);
                    return writer;
                };

                /**
                 * Decodes a QuerySignedPutUrlRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QuerySignedPutUrlRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QuerySignedPutUrlRequest} QuerySignedPutUrlRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QuerySignedPutUrlRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QuerySignedPutUrlRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.contentType = reader.int32();
                            break;
                        case 2:
                            message.keyStr = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.keyNum = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.contentLength = reader.int64().toString();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QuerySignedPutUrlRequest;
            })();

            proto.TurmsRequest = (function() {

                /**
                 * Properties of a TurmsRequest.
                 * @memberof im.turms.proto
                 * @interface ITurmsRequest
                 * @property {google.protobuf.IInt64Value|null} [requestId] TurmsRequest requestId
                 * @property {im.turms.proto.IAckRequest|null} [ackRequest] TurmsRequest ackRequest
                 * @property {im.turms.proto.IDeleteResourceRequest|null} [deleteResourceRequest] TurmsRequest deleteResourceRequest
                 * @property {im.turms.proto.IQuerySignedGetUrlRequest|null} [querySignedGetUrlRequest] TurmsRequest querySignedGetUrlRequest
                 * @property {im.turms.proto.IQuerySignedPutUrlRequest|null} [querySignedPutUrlRequest] TurmsRequest querySignedPutUrlRequest
                 * @property {im.turms.proto.ICreateMessageRequest|null} [createMessageRequest] TurmsRequest createMessageRequest
                 * @property {im.turms.proto.IQueryMessageStatusesRequest|null} [queryMessageStatusesRequest] TurmsRequest queryMessageStatusesRequest
                 * @property {im.turms.proto.IQueryMessagesRequest|null} [queryMessagesRequest] TurmsRequest queryMessagesRequest
                 * @property {im.turms.proto.IQueryPendingMessagesWithTotalRequest|null} [queryPendingMessagesWithTotalRequest] TurmsRequest queryPendingMessagesWithTotalRequest
                 * @property {im.turms.proto.IUpdateMessageRequest|null} [updateMessageRequest] TurmsRequest updateMessageRequest
                 * @property {im.turms.proto.IUpdateTypingStatusRequest|null} [updateTypingStatusRequest] TurmsRequest updateTypingStatusRequest
                 * @property {im.turms.proto.ICreateSessionRequest|null} [createSessionRequest] TurmsRequest createSessionRequest
                 * @property {im.turms.proto.IDeleteSessionRequest|null} [deleteSessionRequest] TurmsRequest deleteSessionRequest
                 * @property {im.turms.proto.IQueryUserProfileRequest|null} [queryUserProfileRequest] TurmsRequest queryUserProfileRequest
                 * @property {im.turms.proto.IQueryUserIdsNearbyRequest|null} [queryUserIdsNearbyRequest] TurmsRequest queryUserIdsNearbyRequest
                 * @property {im.turms.proto.IQueryUserInfosNearbyRequest|null} [queryUserInfosNearbyRequest] TurmsRequest queryUserInfosNearbyRequest
                 * @property {im.turms.proto.IQueryUserOnlineStatusesRequest|null} [queryUserOnlineStatusesRequest] TurmsRequest queryUserOnlineStatusesRequest
                 * @property {im.turms.proto.IUpdateUserLocationRequest|null} [updateUserLocationRequest] TurmsRequest updateUserLocationRequest
                 * @property {im.turms.proto.IUpdateUserOnlineStatusRequest|null} [updateUserOnlineStatusRequest] TurmsRequest updateUserOnlineStatusRequest
                 * @property {im.turms.proto.IUpdateUserRequest|null} [updateUserRequest] TurmsRequest updateUserRequest
                 * @property {im.turms.proto.ICreateFriendRequestRequest|null} [createFriendRequestRequest] TurmsRequest createFriendRequestRequest
                 * @property {im.turms.proto.ICreateRelationshipGroupRequest|null} [createRelationshipGroupRequest] TurmsRequest createRelationshipGroupRequest
                 * @property {im.turms.proto.ICreateRelationshipRequest|null} [createRelationshipRequest] TurmsRequest createRelationshipRequest
                 * @property {im.turms.proto.IDeleteRelationshipGroupRequest|null} [deleteRelationshipGroupRequest] TurmsRequest deleteRelationshipGroupRequest
                 * @property {im.turms.proto.IDeleteRelationshipRequest|null} [deleteRelationshipRequest] TurmsRequest deleteRelationshipRequest
                 * @property {im.turms.proto.IQueryFriendRequestsRequest|null} [queryFriendRequestsRequest] TurmsRequest queryFriendRequestsRequest
                 * @property {im.turms.proto.IQueryRelatedUserIdsRequest|null} [queryRelatedUserIdsRequest] TurmsRequest queryRelatedUserIdsRequest
                 * @property {im.turms.proto.IQueryRelationshipGroupsRequest|null} [queryRelationshipGroupsRequest] TurmsRequest queryRelationshipGroupsRequest
                 * @property {im.turms.proto.IQueryRelationshipsRequest|null} [queryRelationshipsRequest] TurmsRequest queryRelationshipsRequest
                 * @property {im.turms.proto.IUpdateFriendRequestRequest|null} [updateFriendRequestRequest] TurmsRequest updateFriendRequestRequest
                 * @property {im.turms.proto.IUpdateRelationshipGroupRequest|null} [updateRelationshipGroupRequest] TurmsRequest updateRelationshipGroupRequest
                 * @property {im.turms.proto.IUpdateRelationshipRequest|null} [updateRelationshipRequest] TurmsRequest updateRelationshipRequest
                 * @property {im.turms.proto.ICreateGroupRequest|null} [createGroupRequest] TurmsRequest createGroupRequest
                 * @property {im.turms.proto.IDeleteGroupRequest|null} [deleteGroupRequest] TurmsRequest deleteGroupRequest
                 * @property {im.turms.proto.IQueryGroupRequest|null} [queryGroupRequest] TurmsRequest queryGroupRequest
                 * @property {im.turms.proto.IQueryJoinedGroupIdsRequest|null} [queryJoinedGroupIdsRequest] TurmsRequest queryJoinedGroupIdsRequest
                 * @property {im.turms.proto.IQueryJoinedGroupInfosRequest|null} [queryJoinedGroupInfosRequest] TurmsRequest queryJoinedGroupInfosRequest
                 * @property {im.turms.proto.IUpdateGroupRequest|null} [updateGroupRequest] TurmsRequest updateGroupRequest
                 * @property {im.turms.proto.ICreateGroupBlacklistedUserRequest|null} [createGroupBlacklistedUserRequest] TurmsRequest createGroupBlacklistedUserRequest
                 * @property {im.turms.proto.IDeleteGroupBlacklistedUserRequest|null} [deleteGroupBlacklistedUserRequest] TurmsRequest deleteGroupBlacklistedUserRequest
                 * @property {im.turms.proto.IQueryGroupBlacklistedUserIdsRequest|null} [queryGroupBlacklistedUserIdsRequest] TurmsRequest queryGroupBlacklistedUserIdsRequest
                 * @property {im.turms.proto.IQueryGroupBlacklistedUserInfosRequest|null} [queryGroupBlacklistedUserInfosRequest] TurmsRequest queryGroupBlacklistedUserInfosRequest
                 * @property {im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest|null} [checkGroupJoinQuestionsAnswersRequest] TurmsRequest checkGroupJoinQuestionsAnswersRequest
                 * @property {im.turms.proto.ICreateGroupInvitationRequest|null} [createGroupInvitationRequest] TurmsRequest createGroupInvitationRequest
                 * @property {im.turms.proto.ICreateGroupJoinRequestRequest|null} [createGroupJoinRequestRequest] TurmsRequest createGroupJoinRequestRequest
                 * @property {im.turms.proto.ICreateGroupJoinQuestionRequest|null} [createGroupJoinQuestionRequest] TurmsRequest createGroupJoinQuestionRequest
                 * @property {im.turms.proto.IDeleteGroupInvitationRequest|null} [deleteGroupInvitationRequest] TurmsRequest deleteGroupInvitationRequest
                 * @property {im.turms.proto.IDeleteGroupJoinRequestRequest|null} [deleteGroupJoinRequestRequest] TurmsRequest deleteGroupJoinRequestRequest
                 * @property {im.turms.proto.IDeleteGroupJoinQuestionRequest|null} [deleteGroupJoinQuestionRequest] TurmsRequest deleteGroupJoinQuestionRequest
                 * @property {im.turms.proto.IQueryGroupInvitationsRequest|null} [queryGroupInvitationsRequest] TurmsRequest queryGroupInvitationsRequest
                 * @property {im.turms.proto.IQueryGroupJoinRequestsRequest|null} [queryGroupJoinRequestsRequest] TurmsRequest queryGroupJoinRequestsRequest
                 * @property {im.turms.proto.IQueryGroupJoinQuestionsRequest|null} [queryGroupJoinQuestionsRequest] TurmsRequest queryGroupJoinQuestionsRequest
                 * @property {im.turms.proto.IUpdateGroupJoinQuestionRequest|null} [updateGroupJoinQuestionRequest] TurmsRequest updateGroupJoinQuestionRequest
                 * @property {im.turms.proto.ICreateGroupMemberRequest|null} [createGroupMemberRequest] TurmsRequest createGroupMemberRequest
                 * @property {im.turms.proto.IDeleteGroupMemberRequest|null} [deleteGroupMemberRequest] TurmsRequest deleteGroupMemberRequest
                 * @property {im.turms.proto.IQueryGroupMembersRequest|null} [queryGroupMembersRequest] TurmsRequest queryGroupMembersRequest
                 * @property {im.turms.proto.IUpdateGroupMemberRequest|null} [updateGroupMemberRequest] TurmsRequest updateGroupMemberRequest
                 */

                /**
                 * Constructs a new TurmsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a TurmsRequest.
                 * @implements ITurmsRequest
                 * @constructor
                 * @param {im.turms.proto.ITurmsRequest=} [properties] Properties to set
                 */
                function TurmsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * TurmsRequest requestId.
                 * @member {google.protobuf.IInt64Value|null|undefined} requestId
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.requestId = null;

                /**
                 * TurmsRequest ackRequest.
                 * @member {im.turms.proto.IAckRequest|null|undefined} ackRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.ackRequest = null;

                /**
                 * TurmsRequest deleteResourceRequest.
                 * @member {im.turms.proto.IDeleteResourceRequest|null|undefined} deleteResourceRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteResourceRequest = null;

                /**
                 * TurmsRequest querySignedGetUrlRequest.
                 * @member {im.turms.proto.IQuerySignedGetUrlRequest|null|undefined} querySignedGetUrlRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.querySignedGetUrlRequest = null;

                /**
                 * TurmsRequest querySignedPutUrlRequest.
                 * @member {im.turms.proto.IQuerySignedPutUrlRequest|null|undefined} querySignedPutUrlRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.querySignedPutUrlRequest = null;

                /**
                 * TurmsRequest createMessageRequest.
                 * @member {im.turms.proto.ICreateMessageRequest|null|undefined} createMessageRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createMessageRequest = null;

                /**
                 * TurmsRequest queryMessageStatusesRequest.
                 * @member {im.turms.proto.IQueryMessageStatusesRequest|null|undefined} queryMessageStatusesRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryMessageStatusesRequest = null;

                /**
                 * TurmsRequest queryMessagesRequest.
                 * @member {im.turms.proto.IQueryMessagesRequest|null|undefined} queryMessagesRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryMessagesRequest = null;

                /**
                 * TurmsRequest queryPendingMessagesWithTotalRequest.
                 * @member {im.turms.proto.IQueryPendingMessagesWithTotalRequest|null|undefined} queryPendingMessagesWithTotalRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryPendingMessagesWithTotalRequest = null;

                /**
                 * TurmsRequest updateMessageRequest.
                 * @member {im.turms.proto.IUpdateMessageRequest|null|undefined} updateMessageRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateMessageRequest = null;

                /**
                 * TurmsRequest updateTypingStatusRequest.
                 * @member {im.turms.proto.IUpdateTypingStatusRequest|null|undefined} updateTypingStatusRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateTypingStatusRequest = null;

                /**
                 * TurmsRequest createSessionRequest.
                 * @member {im.turms.proto.ICreateSessionRequest|null|undefined} createSessionRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createSessionRequest = null;

                /**
                 * TurmsRequest deleteSessionRequest.
                 * @member {im.turms.proto.IDeleteSessionRequest|null|undefined} deleteSessionRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteSessionRequest = null;

                /**
                 * TurmsRequest queryUserProfileRequest.
                 * @member {im.turms.proto.IQueryUserProfileRequest|null|undefined} queryUserProfileRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryUserProfileRequest = null;

                /**
                 * TurmsRequest queryUserIdsNearbyRequest.
                 * @member {im.turms.proto.IQueryUserIdsNearbyRequest|null|undefined} queryUserIdsNearbyRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryUserIdsNearbyRequest = null;

                /**
                 * TurmsRequest queryUserInfosNearbyRequest.
                 * @member {im.turms.proto.IQueryUserInfosNearbyRequest|null|undefined} queryUserInfosNearbyRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryUserInfosNearbyRequest = null;

                /**
                 * TurmsRequest queryUserOnlineStatusesRequest.
                 * @member {im.turms.proto.IQueryUserOnlineStatusesRequest|null|undefined} queryUserOnlineStatusesRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryUserOnlineStatusesRequest = null;

                /**
                 * TurmsRequest updateUserLocationRequest.
                 * @member {im.turms.proto.IUpdateUserLocationRequest|null|undefined} updateUserLocationRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateUserLocationRequest = null;

                /**
                 * TurmsRequest updateUserOnlineStatusRequest.
                 * @member {im.turms.proto.IUpdateUserOnlineStatusRequest|null|undefined} updateUserOnlineStatusRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateUserOnlineStatusRequest = null;

                /**
                 * TurmsRequest updateUserRequest.
                 * @member {im.turms.proto.IUpdateUserRequest|null|undefined} updateUserRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateUserRequest = null;

                /**
                 * TurmsRequest createFriendRequestRequest.
                 * @member {im.turms.proto.ICreateFriendRequestRequest|null|undefined} createFriendRequestRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createFriendRequestRequest = null;

                /**
                 * TurmsRequest createRelationshipGroupRequest.
                 * @member {im.turms.proto.ICreateRelationshipGroupRequest|null|undefined} createRelationshipGroupRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createRelationshipGroupRequest = null;

                /**
                 * TurmsRequest createRelationshipRequest.
                 * @member {im.turms.proto.ICreateRelationshipRequest|null|undefined} createRelationshipRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createRelationshipRequest = null;

                /**
                 * TurmsRequest deleteRelationshipGroupRequest.
                 * @member {im.turms.proto.IDeleteRelationshipGroupRequest|null|undefined} deleteRelationshipGroupRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteRelationshipGroupRequest = null;

                /**
                 * TurmsRequest deleteRelationshipRequest.
                 * @member {im.turms.proto.IDeleteRelationshipRequest|null|undefined} deleteRelationshipRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteRelationshipRequest = null;

                /**
                 * TurmsRequest queryFriendRequestsRequest.
                 * @member {im.turms.proto.IQueryFriendRequestsRequest|null|undefined} queryFriendRequestsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryFriendRequestsRequest = null;

                /**
                 * TurmsRequest queryRelatedUserIdsRequest.
                 * @member {im.turms.proto.IQueryRelatedUserIdsRequest|null|undefined} queryRelatedUserIdsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryRelatedUserIdsRequest = null;

                /**
                 * TurmsRequest queryRelationshipGroupsRequest.
                 * @member {im.turms.proto.IQueryRelationshipGroupsRequest|null|undefined} queryRelationshipGroupsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryRelationshipGroupsRequest = null;

                /**
                 * TurmsRequest queryRelationshipsRequest.
                 * @member {im.turms.proto.IQueryRelationshipsRequest|null|undefined} queryRelationshipsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryRelationshipsRequest = null;

                /**
                 * TurmsRequest updateFriendRequestRequest.
                 * @member {im.turms.proto.IUpdateFriendRequestRequest|null|undefined} updateFriendRequestRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateFriendRequestRequest = null;

                /**
                 * TurmsRequest updateRelationshipGroupRequest.
                 * @member {im.turms.proto.IUpdateRelationshipGroupRequest|null|undefined} updateRelationshipGroupRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateRelationshipGroupRequest = null;

                /**
                 * TurmsRequest updateRelationshipRequest.
                 * @member {im.turms.proto.IUpdateRelationshipRequest|null|undefined} updateRelationshipRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateRelationshipRequest = null;

                /**
                 * TurmsRequest createGroupRequest.
                 * @member {im.turms.proto.ICreateGroupRequest|null|undefined} createGroupRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createGroupRequest = null;

                /**
                 * TurmsRequest deleteGroupRequest.
                 * @member {im.turms.proto.IDeleteGroupRequest|null|undefined} deleteGroupRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteGroupRequest = null;

                /**
                 * TurmsRequest queryGroupRequest.
                 * @member {im.turms.proto.IQueryGroupRequest|null|undefined} queryGroupRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryGroupRequest = null;

                /**
                 * TurmsRequest queryJoinedGroupIdsRequest.
                 * @member {im.turms.proto.IQueryJoinedGroupIdsRequest|null|undefined} queryJoinedGroupIdsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryJoinedGroupIdsRequest = null;

                /**
                 * TurmsRequest queryJoinedGroupInfosRequest.
                 * @member {im.turms.proto.IQueryJoinedGroupInfosRequest|null|undefined} queryJoinedGroupInfosRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryJoinedGroupInfosRequest = null;

                /**
                 * TurmsRequest updateGroupRequest.
                 * @member {im.turms.proto.IUpdateGroupRequest|null|undefined} updateGroupRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateGroupRequest = null;

                /**
                 * TurmsRequest createGroupBlacklistedUserRequest.
                 * @member {im.turms.proto.ICreateGroupBlacklistedUserRequest|null|undefined} createGroupBlacklistedUserRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createGroupBlacklistedUserRequest = null;

                /**
                 * TurmsRequest deleteGroupBlacklistedUserRequest.
                 * @member {im.turms.proto.IDeleteGroupBlacklistedUserRequest|null|undefined} deleteGroupBlacklistedUserRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteGroupBlacklistedUserRequest = null;

                /**
                 * TurmsRequest queryGroupBlacklistedUserIdsRequest.
                 * @member {im.turms.proto.IQueryGroupBlacklistedUserIdsRequest|null|undefined} queryGroupBlacklistedUserIdsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryGroupBlacklistedUserIdsRequest = null;

                /**
                 * TurmsRequest queryGroupBlacklistedUserInfosRequest.
                 * @member {im.turms.proto.IQueryGroupBlacklistedUserInfosRequest|null|undefined} queryGroupBlacklistedUserInfosRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryGroupBlacklistedUserInfosRequest = null;

                /**
                 * TurmsRequest checkGroupJoinQuestionsAnswersRequest.
                 * @member {im.turms.proto.ICheckGroupJoinQuestionsAnswersRequest|null|undefined} checkGroupJoinQuestionsAnswersRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.checkGroupJoinQuestionsAnswersRequest = null;

                /**
                 * TurmsRequest createGroupInvitationRequest.
                 * @member {im.turms.proto.ICreateGroupInvitationRequest|null|undefined} createGroupInvitationRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createGroupInvitationRequest = null;

                /**
                 * TurmsRequest createGroupJoinRequestRequest.
                 * @member {im.turms.proto.ICreateGroupJoinRequestRequest|null|undefined} createGroupJoinRequestRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createGroupJoinRequestRequest = null;

                /**
                 * TurmsRequest createGroupJoinQuestionRequest.
                 * @member {im.turms.proto.ICreateGroupJoinQuestionRequest|null|undefined} createGroupJoinQuestionRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createGroupJoinQuestionRequest = null;

                /**
                 * TurmsRequest deleteGroupInvitationRequest.
                 * @member {im.turms.proto.IDeleteGroupInvitationRequest|null|undefined} deleteGroupInvitationRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteGroupInvitationRequest = null;

                /**
                 * TurmsRequest deleteGroupJoinRequestRequest.
                 * @member {im.turms.proto.IDeleteGroupJoinRequestRequest|null|undefined} deleteGroupJoinRequestRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteGroupJoinRequestRequest = null;

                /**
                 * TurmsRequest deleteGroupJoinQuestionRequest.
                 * @member {im.turms.proto.IDeleteGroupJoinQuestionRequest|null|undefined} deleteGroupJoinQuestionRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteGroupJoinQuestionRequest = null;

                /**
                 * TurmsRequest queryGroupInvitationsRequest.
                 * @member {im.turms.proto.IQueryGroupInvitationsRequest|null|undefined} queryGroupInvitationsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryGroupInvitationsRequest = null;

                /**
                 * TurmsRequest queryGroupJoinRequestsRequest.
                 * @member {im.turms.proto.IQueryGroupJoinRequestsRequest|null|undefined} queryGroupJoinRequestsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryGroupJoinRequestsRequest = null;

                /**
                 * TurmsRequest queryGroupJoinQuestionsRequest.
                 * @member {im.turms.proto.IQueryGroupJoinQuestionsRequest|null|undefined} queryGroupJoinQuestionsRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryGroupJoinQuestionsRequest = null;

                /**
                 * TurmsRequest updateGroupJoinQuestionRequest.
                 * @member {im.turms.proto.IUpdateGroupJoinQuestionRequest|null|undefined} updateGroupJoinQuestionRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateGroupJoinQuestionRequest = null;

                /**
                 * TurmsRequest createGroupMemberRequest.
                 * @member {im.turms.proto.ICreateGroupMemberRequest|null|undefined} createGroupMemberRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.createGroupMemberRequest = null;

                /**
                 * TurmsRequest deleteGroupMemberRequest.
                 * @member {im.turms.proto.IDeleteGroupMemberRequest|null|undefined} deleteGroupMemberRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.deleteGroupMemberRequest = null;

                /**
                 * TurmsRequest queryGroupMembersRequest.
                 * @member {im.turms.proto.IQueryGroupMembersRequest|null|undefined} queryGroupMembersRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.queryGroupMembersRequest = null;

                /**
                 * TurmsRequest updateGroupMemberRequest.
                 * @member {im.turms.proto.IUpdateGroupMemberRequest|null|undefined} updateGroupMemberRequest
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                TurmsRequest.prototype.updateGroupMemberRequest = null;

                // OneOf field names bound to virtual getters and setters
                var $oneOfFields;

                /**
                 * TurmsRequest kind.
                 * @member {"ackRequest"|"deleteResourceRequest"|"querySignedGetUrlRequest"|"querySignedPutUrlRequest"|"createMessageRequest"|"queryMessageStatusesRequest"|"queryMessagesRequest"|"queryPendingMessagesWithTotalRequest"|"updateMessageRequest"|"updateTypingStatusRequest"|"createSessionRequest"|"deleteSessionRequest"|"queryUserProfileRequest"|"queryUserIdsNearbyRequest"|"queryUserInfosNearbyRequest"|"queryUserOnlineStatusesRequest"|"updateUserLocationRequest"|"updateUserOnlineStatusRequest"|"updateUserRequest"|"createFriendRequestRequest"|"createRelationshipGroupRequest"|"createRelationshipRequest"|"deleteRelationshipGroupRequest"|"deleteRelationshipRequest"|"queryFriendRequestsRequest"|"queryRelatedUserIdsRequest"|"queryRelationshipGroupsRequest"|"queryRelationshipsRequest"|"updateFriendRequestRequest"|"updateRelationshipGroupRequest"|"updateRelationshipRequest"|"createGroupRequest"|"deleteGroupRequest"|"queryGroupRequest"|"queryJoinedGroupIdsRequest"|"queryJoinedGroupInfosRequest"|"updateGroupRequest"|"createGroupBlacklistedUserRequest"|"deleteGroupBlacklistedUserRequest"|"queryGroupBlacklistedUserIdsRequest"|"queryGroupBlacklistedUserInfosRequest"|"checkGroupJoinQuestionsAnswersRequest"|"createGroupInvitationRequest"|"createGroupJoinRequestRequest"|"createGroupJoinQuestionRequest"|"deleteGroupInvitationRequest"|"deleteGroupJoinRequestRequest"|"deleteGroupJoinQuestionRequest"|"queryGroupInvitationsRequest"|"queryGroupJoinRequestsRequest"|"queryGroupJoinQuestionsRequest"|"updateGroupJoinQuestionRequest"|"createGroupMemberRequest"|"deleteGroupMemberRequest"|"queryGroupMembersRequest"|"updateGroupMemberRequest"|undefined} kind
                 * @memberof im.turms.proto.TurmsRequest
                 * @instance
                 */
                Object.defineProperty(TurmsRequest.prototype, "kind", {
                    get: $util.oneOfGetter($oneOfFields = ["ackRequest", "deleteResourceRequest", "querySignedGetUrlRequest", "querySignedPutUrlRequest", "createMessageRequest", "queryMessageStatusesRequest", "queryMessagesRequest", "queryPendingMessagesWithTotalRequest", "updateMessageRequest", "updateTypingStatusRequest", "createSessionRequest", "deleteSessionRequest", "queryUserProfileRequest", "queryUserIdsNearbyRequest", "queryUserInfosNearbyRequest", "queryUserOnlineStatusesRequest", "updateUserLocationRequest", "updateUserOnlineStatusRequest", "updateUserRequest", "createFriendRequestRequest", "createRelationshipGroupRequest", "createRelationshipRequest", "deleteRelationshipGroupRequest", "deleteRelationshipRequest", "queryFriendRequestsRequest", "queryRelatedUserIdsRequest", "queryRelationshipGroupsRequest", "queryRelationshipsRequest", "updateFriendRequestRequest", "updateRelationshipGroupRequest", "updateRelationshipRequest", "createGroupRequest", "deleteGroupRequest", "queryGroupRequest", "queryJoinedGroupIdsRequest", "queryJoinedGroupInfosRequest", "updateGroupRequest", "createGroupBlacklistedUserRequest", "deleteGroupBlacklistedUserRequest", "queryGroupBlacklistedUserIdsRequest", "queryGroupBlacklistedUserInfosRequest", "checkGroupJoinQuestionsAnswersRequest", "createGroupInvitationRequest", "createGroupJoinRequestRequest", "createGroupJoinQuestionRequest", "deleteGroupInvitationRequest", "deleteGroupJoinRequestRequest", "deleteGroupJoinQuestionRequest", "queryGroupInvitationsRequest", "queryGroupJoinRequestsRequest", "queryGroupJoinQuestionsRequest", "updateGroupJoinQuestionRequest", "createGroupMemberRequest", "deleteGroupMemberRequest", "queryGroupMembersRequest", "updateGroupMemberRequest"]),
                    set: $util.oneOfSetter($oneOfFields)
                });

                /**
                 * Encodes the specified TurmsRequest message. Does not implicitly {@link im.turms.proto.TurmsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.TurmsRequest
                 * @static
                 * @param {im.turms.proto.ITurmsRequest} message TurmsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                TurmsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.requestId != null && Object.hasOwnProperty.call(message, "requestId"))
                        $root.google.protobuf.Int64Value.encode(message.requestId, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.ackRequest != null && Object.hasOwnProperty.call(message, "ackRequest"))
                        $root.im.turms.proto.AckRequest.encode(message.ackRequest, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.deleteResourceRequest != null && Object.hasOwnProperty.call(message, "deleteResourceRequest"))
                        $root.im.turms.proto.DeleteResourceRequest.encode(message.deleteResourceRequest, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.querySignedGetUrlRequest != null && Object.hasOwnProperty.call(message, "querySignedGetUrlRequest"))
                        $root.im.turms.proto.QuerySignedGetUrlRequest.encode(message.querySignedGetUrlRequest, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    if (message.querySignedPutUrlRequest != null && Object.hasOwnProperty.call(message, "querySignedPutUrlRequest"))
                        $root.im.turms.proto.QuerySignedPutUrlRequest.encode(message.querySignedPutUrlRequest, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.createMessageRequest != null && Object.hasOwnProperty.call(message, "createMessageRequest"))
                        $root.im.turms.proto.CreateMessageRequest.encode(message.createMessageRequest, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    if (message.queryMessageStatusesRequest != null && Object.hasOwnProperty.call(message, "queryMessageStatusesRequest"))
                        $root.im.turms.proto.QueryMessageStatusesRequest.encode(message.queryMessageStatusesRequest, writer.uint32(/* id 7, wireType 2 =*/58).fork()).ldelim();
                    if (message.queryMessagesRequest != null && Object.hasOwnProperty.call(message, "queryMessagesRequest"))
                        $root.im.turms.proto.QueryMessagesRequest.encode(message.queryMessagesRequest, writer.uint32(/* id 8, wireType 2 =*/66).fork()).ldelim();
                    if (message.queryPendingMessagesWithTotalRequest != null && Object.hasOwnProperty.call(message, "queryPendingMessagesWithTotalRequest"))
                        $root.im.turms.proto.QueryPendingMessagesWithTotalRequest.encode(message.queryPendingMessagesWithTotalRequest, writer.uint32(/* id 9, wireType 2 =*/74).fork()).ldelim();
                    if (message.updateMessageRequest != null && Object.hasOwnProperty.call(message, "updateMessageRequest"))
                        $root.im.turms.proto.UpdateMessageRequest.encode(message.updateMessageRequest, writer.uint32(/* id 10, wireType 2 =*/82).fork()).ldelim();
                    if (message.updateTypingStatusRequest != null && Object.hasOwnProperty.call(message, "updateTypingStatusRequest"))
                        $root.im.turms.proto.UpdateTypingStatusRequest.encode(message.updateTypingStatusRequest, writer.uint32(/* id 11, wireType 2 =*/90).fork()).ldelim();
                    if (message.createSessionRequest != null && Object.hasOwnProperty.call(message, "createSessionRequest"))
                        $root.im.turms.proto.CreateSessionRequest.encode(message.createSessionRequest, writer.uint32(/* id 100, wireType 2 =*/802).fork()).ldelim();
                    if (message.deleteSessionRequest != null && Object.hasOwnProperty.call(message, "deleteSessionRequest"))
                        $root.im.turms.proto.DeleteSessionRequest.encode(message.deleteSessionRequest, writer.uint32(/* id 101, wireType 2 =*/810).fork()).ldelim();
                    if (message.queryUserProfileRequest != null && Object.hasOwnProperty.call(message, "queryUserProfileRequest"))
                        $root.im.turms.proto.QueryUserProfileRequest.encode(message.queryUserProfileRequest, writer.uint32(/* id 102, wireType 2 =*/818).fork()).ldelim();
                    if (message.queryUserIdsNearbyRequest != null && Object.hasOwnProperty.call(message, "queryUserIdsNearbyRequest"))
                        $root.im.turms.proto.QueryUserIdsNearbyRequest.encode(message.queryUserIdsNearbyRequest, writer.uint32(/* id 103, wireType 2 =*/826).fork()).ldelim();
                    if (message.queryUserInfosNearbyRequest != null && Object.hasOwnProperty.call(message, "queryUserInfosNearbyRequest"))
                        $root.im.turms.proto.QueryUserInfosNearbyRequest.encode(message.queryUserInfosNearbyRequest, writer.uint32(/* id 104, wireType 2 =*/834).fork()).ldelim();
                    if (message.queryUserOnlineStatusesRequest != null && Object.hasOwnProperty.call(message, "queryUserOnlineStatusesRequest"))
                        $root.im.turms.proto.QueryUserOnlineStatusesRequest.encode(message.queryUserOnlineStatusesRequest, writer.uint32(/* id 105, wireType 2 =*/842).fork()).ldelim();
                    if (message.updateUserLocationRequest != null && Object.hasOwnProperty.call(message, "updateUserLocationRequest"))
                        $root.im.turms.proto.UpdateUserLocationRequest.encode(message.updateUserLocationRequest, writer.uint32(/* id 106, wireType 2 =*/850).fork()).ldelim();
                    if (message.updateUserOnlineStatusRequest != null && Object.hasOwnProperty.call(message, "updateUserOnlineStatusRequest"))
                        $root.im.turms.proto.UpdateUserOnlineStatusRequest.encode(message.updateUserOnlineStatusRequest, writer.uint32(/* id 107, wireType 2 =*/858).fork()).ldelim();
                    if (message.updateUserRequest != null && Object.hasOwnProperty.call(message, "updateUserRequest"))
                        $root.im.turms.proto.UpdateUserRequest.encode(message.updateUserRequest, writer.uint32(/* id 108, wireType 2 =*/866).fork()).ldelim();
                    if (message.createFriendRequestRequest != null && Object.hasOwnProperty.call(message, "createFriendRequestRequest"))
                        $root.im.turms.proto.CreateFriendRequestRequest.encode(message.createFriendRequestRequest, writer.uint32(/* id 200, wireType 2 =*/1602).fork()).ldelim();
                    if (message.createRelationshipGroupRequest != null && Object.hasOwnProperty.call(message, "createRelationshipGroupRequest"))
                        $root.im.turms.proto.CreateRelationshipGroupRequest.encode(message.createRelationshipGroupRequest, writer.uint32(/* id 201, wireType 2 =*/1610).fork()).ldelim();
                    if (message.createRelationshipRequest != null && Object.hasOwnProperty.call(message, "createRelationshipRequest"))
                        $root.im.turms.proto.CreateRelationshipRequest.encode(message.createRelationshipRequest, writer.uint32(/* id 202, wireType 2 =*/1618).fork()).ldelim();
                    if (message.deleteRelationshipGroupRequest != null && Object.hasOwnProperty.call(message, "deleteRelationshipGroupRequest"))
                        $root.im.turms.proto.DeleteRelationshipGroupRequest.encode(message.deleteRelationshipGroupRequest, writer.uint32(/* id 203, wireType 2 =*/1626).fork()).ldelim();
                    if (message.deleteRelationshipRequest != null && Object.hasOwnProperty.call(message, "deleteRelationshipRequest"))
                        $root.im.turms.proto.DeleteRelationshipRequest.encode(message.deleteRelationshipRequest, writer.uint32(/* id 204, wireType 2 =*/1634).fork()).ldelim();
                    if (message.queryFriendRequestsRequest != null && Object.hasOwnProperty.call(message, "queryFriendRequestsRequest"))
                        $root.im.turms.proto.QueryFriendRequestsRequest.encode(message.queryFriendRequestsRequest, writer.uint32(/* id 205, wireType 2 =*/1642).fork()).ldelim();
                    if (message.queryRelatedUserIdsRequest != null && Object.hasOwnProperty.call(message, "queryRelatedUserIdsRequest"))
                        $root.im.turms.proto.QueryRelatedUserIdsRequest.encode(message.queryRelatedUserIdsRequest, writer.uint32(/* id 206, wireType 2 =*/1650).fork()).ldelim();
                    if (message.queryRelationshipGroupsRequest != null && Object.hasOwnProperty.call(message, "queryRelationshipGroupsRequest"))
                        $root.im.turms.proto.QueryRelationshipGroupsRequest.encode(message.queryRelationshipGroupsRequest, writer.uint32(/* id 207, wireType 2 =*/1658).fork()).ldelim();
                    if (message.queryRelationshipsRequest != null && Object.hasOwnProperty.call(message, "queryRelationshipsRequest"))
                        $root.im.turms.proto.QueryRelationshipsRequest.encode(message.queryRelationshipsRequest, writer.uint32(/* id 208, wireType 2 =*/1666).fork()).ldelim();
                    if (message.updateFriendRequestRequest != null && Object.hasOwnProperty.call(message, "updateFriendRequestRequest"))
                        $root.im.turms.proto.UpdateFriendRequestRequest.encode(message.updateFriendRequestRequest, writer.uint32(/* id 209, wireType 2 =*/1674).fork()).ldelim();
                    if (message.updateRelationshipGroupRequest != null && Object.hasOwnProperty.call(message, "updateRelationshipGroupRequest"))
                        $root.im.turms.proto.UpdateRelationshipGroupRequest.encode(message.updateRelationshipGroupRequest, writer.uint32(/* id 210, wireType 2 =*/1682).fork()).ldelim();
                    if (message.updateRelationshipRequest != null && Object.hasOwnProperty.call(message, "updateRelationshipRequest"))
                        $root.im.turms.proto.UpdateRelationshipRequest.encode(message.updateRelationshipRequest, writer.uint32(/* id 211, wireType 2 =*/1690).fork()).ldelim();
                    if (message.createGroupRequest != null && Object.hasOwnProperty.call(message, "createGroupRequest"))
                        $root.im.turms.proto.CreateGroupRequest.encode(message.createGroupRequest, writer.uint32(/* id 300, wireType 2 =*/2402).fork()).ldelim();
                    if (message.deleteGroupRequest != null && Object.hasOwnProperty.call(message, "deleteGroupRequest"))
                        $root.im.turms.proto.DeleteGroupRequest.encode(message.deleteGroupRequest, writer.uint32(/* id 301, wireType 2 =*/2410).fork()).ldelim();
                    if (message.queryGroupRequest != null && Object.hasOwnProperty.call(message, "queryGroupRequest"))
                        $root.im.turms.proto.QueryGroupRequest.encode(message.queryGroupRequest, writer.uint32(/* id 302, wireType 2 =*/2418).fork()).ldelim();
                    if (message.queryJoinedGroupIdsRequest != null && Object.hasOwnProperty.call(message, "queryJoinedGroupIdsRequest"))
                        $root.im.turms.proto.QueryJoinedGroupIdsRequest.encode(message.queryJoinedGroupIdsRequest, writer.uint32(/* id 303, wireType 2 =*/2426).fork()).ldelim();
                    if (message.queryJoinedGroupInfosRequest != null && Object.hasOwnProperty.call(message, "queryJoinedGroupInfosRequest"))
                        $root.im.turms.proto.QueryJoinedGroupInfosRequest.encode(message.queryJoinedGroupInfosRequest, writer.uint32(/* id 304, wireType 2 =*/2434).fork()).ldelim();
                    if (message.updateGroupRequest != null && Object.hasOwnProperty.call(message, "updateGroupRequest"))
                        $root.im.turms.proto.UpdateGroupRequest.encode(message.updateGroupRequest, writer.uint32(/* id 305, wireType 2 =*/2442).fork()).ldelim();
                    if (message.createGroupBlacklistedUserRequest != null && Object.hasOwnProperty.call(message, "createGroupBlacklistedUserRequest"))
                        $root.im.turms.proto.CreateGroupBlacklistedUserRequest.encode(message.createGroupBlacklistedUserRequest, writer.uint32(/* id 400, wireType 2 =*/3202).fork()).ldelim();
                    if (message.deleteGroupBlacklistedUserRequest != null && Object.hasOwnProperty.call(message, "deleteGroupBlacklistedUserRequest"))
                        $root.im.turms.proto.DeleteGroupBlacklistedUserRequest.encode(message.deleteGroupBlacklistedUserRequest, writer.uint32(/* id 401, wireType 2 =*/3210).fork()).ldelim();
                    if (message.queryGroupBlacklistedUserIdsRequest != null && Object.hasOwnProperty.call(message, "queryGroupBlacklistedUserIdsRequest"))
                        $root.im.turms.proto.QueryGroupBlacklistedUserIdsRequest.encode(message.queryGroupBlacklistedUserIdsRequest, writer.uint32(/* id 402, wireType 2 =*/3218).fork()).ldelim();
                    if (message.queryGroupBlacklistedUserInfosRequest != null && Object.hasOwnProperty.call(message, "queryGroupBlacklistedUserInfosRequest"))
                        $root.im.turms.proto.QueryGroupBlacklistedUserInfosRequest.encode(message.queryGroupBlacklistedUserInfosRequest, writer.uint32(/* id 403, wireType 2 =*/3226).fork()).ldelim();
                    if (message.checkGroupJoinQuestionsAnswersRequest != null && Object.hasOwnProperty.call(message, "checkGroupJoinQuestionsAnswersRequest"))
                        $root.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest.encode(message.checkGroupJoinQuestionsAnswersRequest, writer.uint32(/* id 500, wireType 2 =*/4002).fork()).ldelim();
                    if (message.createGroupInvitationRequest != null && Object.hasOwnProperty.call(message, "createGroupInvitationRequest"))
                        $root.im.turms.proto.CreateGroupInvitationRequest.encode(message.createGroupInvitationRequest, writer.uint32(/* id 501, wireType 2 =*/4010).fork()).ldelim();
                    if (message.createGroupJoinRequestRequest != null && Object.hasOwnProperty.call(message, "createGroupJoinRequestRequest"))
                        $root.im.turms.proto.CreateGroupJoinRequestRequest.encode(message.createGroupJoinRequestRequest, writer.uint32(/* id 502, wireType 2 =*/4018).fork()).ldelim();
                    if (message.createGroupJoinQuestionRequest != null && Object.hasOwnProperty.call(message, "createGroupJoinQuestionRequest"))
                        $root.im.turms.proto.CreateGroupJoinQuestionRequest.encode(message.createGroupJoinQuestionRequest, writer.uint32(/* id 503, wireType 2 =*/4026).fork()).ldelim();
                    if (message.deleteGroupInvitationRequest != null && Object.hasOwnProperty.call(message, "deleteGroupInvitationRequest"))
                        $root.im.turms.proto.DeleteGroupInvitationRequest.encode(message.deleteGroupInvitationRequest, writer.uint32(/* id 504, wireType 2 =*/4034).fork()).ldelim();
                    if (message.deleteGroupJoinRequestRequest != null && Object.hasOwnProperty.call(message, "deleteGroupJoinRequestRequest"))
                        $root.im.turms.proto.DeleteGroupJoinRequestRequest.encode(message.deleteGroupJoinRequestRequest, writer.uint32(/* id 505, wireType 2 =*/4042).fork()).ldelim();
                    if (message.deleteGroupJoinQuestionRequest != null && Object.hasOwnProperty.call(message, "deleteGroupJoinQuestionRequest"))
                        $root.im.turms.proto.DeleteGroupJoinQuestionRequest.encode(message.deleteGroupJoinQuestionRequest, writer.uint32(/* id 506, wireType 2 =*/4050).fork()).ldelim();
                    if (message.queryGroupInvitationsRequest != null && Object.hasOwnProperty.call(message, "queryGroupInvitationsRequest"))
                        $root.im.turms.proto.QueryGroupInvitationsRequest.encode(message.queryGroupInvitationsRequest, writer.uint32(/* id 507, wireType 2 =*/4058).fork()).ldelim();
                    if (message.queryGroupJoinRequestsRequest != null && Object.hasOwnProperty.call(message, "queryGroupJoinRequestsRequest"))
                        $root.im.turms.proto.QueryGroupJoinRequestsRequest.encode(message.queryGroupJoinRequestsRequest, writer.uint32(/* id 508, wireType 2 =*/4066).fork()).ldelim();
                    if (message.queryGroupJoinQuestionsRequest != null && Object.hasOwnProperty.call(message, "queryGroupJoinQuestionsRequest"))
                        $root.im.turms.proto.QueryGroupJoinQuestionsRequest.encode(message.queryGroupJoinQuestionsRequest, writer.uint32(/* id 509, wireType 2 =*/4074).fork()).ldelim();
                    if (message.updateGroupJoinQuestionRequest != null && Object.hasOwnProperty.call(message, "updateGroupJoinQuestionRequest"))
                        $root.im.turms.proto.UpdateGroupJoinQuestionRequest.encode(message.updateGroupJoinQuestionRequest, writer.uint32(/* id 510, wireType 2 =*/4082).fork()).ldelim();
                    if (message.createGroupMemberRequest != null && Object.hasOwnProperty.call(message, "createGroupMemberRequest"))
                        $root.im.turms.proto.CreateGroupMemberRequest.encode(message.createGroupMemberRequest, writer.uint32(/* id 600, wireType 2 =*/4802).fork()).ldelim();
                    if (message.deleteGroupMemberRequest != null && Object.hasOwnProperty.call(message, "deleteGroupMemberRequest"))
                        $root.im.turms.proto.DeleteGroupMemberRequest.encode(message.deleteGroupMemberRequest, writer.uint32(/* id 601, wireType 2 =*/4810).fork()).ldelim();
                    if (message.queryGroupMembersRequest != null && Object.hasOwnProperty.call(message, "queryGroupMembersRequest"))
                        $root.im.turms.proto.QueryGroupMembersRequest.encode(message.queryGroupMembersRequest, writer.uint32(/* id 602, wireType 2 =*/4818).fork()).ldelim();
                    if (message.updateGroupMemberRequest != null && Object.hasOwnProperty.call(message, "updateGroupMemberRequest"))
                        $root.im.turms.proto.UpdateGroupMemberRequest.encode(message.updateGroupMemberRequest, writer.uint32(/* id 603, wireType 2 =*/4826).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a TurmsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.TurmsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.TurmsRequest} TurmsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                TurmsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.TurmsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.requestId = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.ackRequest = $root.im.turms.proto.AckRequest.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.deleteResourceRequest = $root.im.turms.proto.DeleteResourceRequest.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.querySignedGetUrlRequest = $root.im.turms.proto.QuerySignedGetUrlRequest.decode(reader, reader.uint32());
                            break;
                        case 5:
                            message.querySignedPutUrlRequest = $root.im.turms.proto.QuerySignedPutUrlRequest.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.createMessageRequest = $root.im.turms.proto.CreateMessageRequest.decode(reader, reader.uint32());
                            break;
                        case 7:
                            message.queryMessageStatusesRequest = $root.im.turms.proto.QueryMessageStatusesRequest.decode(reader, reader.uint32());
                            break;
                        case 8:
                            message.queryMessagesRequest = $root.im.turms.proto.QueryMessagesRequest.decode(reader, reader.uint32());
                            break;
                        case 9:
                            message.queryPendingMessagesWithTotalRequest = $root.im.turms.proto.QueryPendingMessagesWithTotalRequest.decode(reader, reader.uint32());
                            break;
                        case 10:
                            message.updateMessageRequest = $root.im.turms.proto.UpdateMessageRequest.decode(reader, reader.uint32());
                            break;
                        case 11:
                            message.updateTypingStatusRequest = $root.im.turms.proto.UpdateTypingStatusRequest.decode(reader, reader.uint32());
                            break;
                        case 100:
                            message.createSessionRequest = $root.im.turms.proto.CreateSessionRequest.decode(reader, reader.uint32());
                            break;
                        case 101:
                            message.deleteSessionRequest = $root.im.turms.proto.DeleteSessionRequest.decode(reader, reader.uint32());
                            break;
                        case 102:
                            message.queryUserProfileRequest = $root.im.turms.proto.QueryUserProfileRequest.decode(reader, reader.uint32());
                            break;
                        case 103:
                            message.queryUserIdsNearbyRequest = $root.im.turms.proto.QueryUserIdsNearbyRequest.decode(reader, reader.uint32());
                            break;
                        case 104:
                            message.queryUserInfosNearbyRequest = $root.im.turms.proto.QueryUserInfosNearbyRequest.decode(reader, reader.uint32());
                            break;
                        case 105:
                            message.queryUserOnlineStatusesRequest = $root.im.turms.proto.QueryUserOnlineStatusesRequest.decode(reader, reader.uint32());
                            break;
                        case 106:
                            message.updateUserLocationRequest = $root.im.turms.proto.UpdateUserLocationRequest.decode(reader, reader.uint32());
                            break;
                        case 107:
                            message.updateUserOnlineStatusRequest = $root.im.turms.proto.UpdateUserOnlineStatusRequest.decode(reader, reader.uint32());
                            break;
                        case 108:
                            message.updateUserRequest = $root.im.turms.proto.UpdateUserRequest.decode(reader, reader.uint32());
                            break;
                        case 200:
                            message.createFriendRequestRequest = $root.im.turms.proto.CreateFriendRequestRequest.decode(reader, reader.uint32());
                            break;
                        case 201:
                            message.createRelationshipGroupRequest = $root.im.turms.proto.CreateRelationshipGroupRequest.decode(reader, reader.uint32());
                            break;
                        case 202:
                            message.createRelationshipRequest = $root.im.turms.proto.CreateRelationshipRequest.decode(reader, reader.uint32());
                            break;
                        case 203:
                            message.deleteRelationshipGroupRequest = $root.im.turms.proto.DeleteRelationshipGroupRequest.decode(reader, reader.uint32());
                            break;
                        case 204:
                            message.deleteRelationshipRequest = $root.im.turms.proto.DeleteRelationshipRequest.decode(reader, reader.uint32());
                            break;
                        case 205:
                            message.queryFriendRequestsRequest = $root.im.turms.proto.QueryFriendRequestsRequest.decode(reader, reader.uint32());
                            break;
                        case 206:
                            message.queryRelatedUserIdsRequest = $root.im.turms.proto.QueryRelatedUserIdsRequest.decode(reader, reader.uint32());
                            break;
                        case 207:
                            message.queryRelationshipGroupsRequest = $root.im.turms.proto.QueryRelationshipGroupsRequest.decode(reader, reader.uint32());
                            break;
                        case 208:
                            message.queryRelationshipsRequest = $root.im.turms.proto.QueryRelationshipsRequest.decode(reader, reader.uint32());
                            break;
                        case 209:
                            message.updateFriendRequestRequest = $root.im.turms.proto.UpdateFriendRequestRequest.decode(reader, reader.uint32());
                            break;
                        case 210:
                            message.updateRelationshipGroupRequest = $root.im.turms.proto.UpdateRelationshipGroupRequest.decode(reader, reader.uint32());
                            break;
                        case 211:
                            message.updateRelationshipRequest = $root.im.turms.proto.UpdateRelationshipRequest.decode(reader, reader.uint32());
                            break;
                        case 300:
                            message.createGroupRequest = $root.im.turms.proto.CreateGroupRequest.decode(reader, reader.uint32());
                            break;
                        case 301:
                            message.deleteGroupRequest = $root.im.turms.proto.DeleteGroupRequest.decode(reader, reader.uint32());
                            break;
                        case 302:
                            message.queryGroupRequest = $root.im.turms.proto.QueryGroupRequest.decode(reader, reader.uint32());
                            break;
                        case 303:
                            message.queryJoinedGroupIdsRequest = $root.im.turms.proto.QueryJoinedGroupIdsRequest.decode(reader, reader.uint32());
                            break;
                        case 304:
                            message.queryJoinedGroupInfosRequest = $root.im.turms.proto.QueryJoinedGroupInfosRequest.decode(reader, reader.uint32());
                            break;
                        case 305:
                            message.updateGroupRequest = $root.im.turms.proto.UpdateGroupRequest.decode(reader, reader.uint32());
                            break;
                        case 400:
                            message.createGroupBlacklistedUserRequest = $root.im.turms.proto.CreateGroupBlacklistedUserRequest.decode(reader, reader.uint32());
                            break;
                        case 401:
                            message.deleteGroupBlacklistedUserRequest = $root.im.turms.proto.DeleteGroupBlacklistedUserRequest.decode(reader, reader.uint32());
                            break;
                        case 402:
                            message.queryGroupBlacklistedUserIdsRequest = $root.im.turms.proto.QueryGroupBlacklistedUserIdsRequest.decode(reader, reader.uint32());
                            break;
                        case 403:
                            message.queryGroupBlacklistedUserInfosRequest = $root.im.turms.proto.QueryGroupBlacklistedUserInfosRequest.decode(reader, reader.uint32());
                            break;
                        case 500:
                            message.checkGroupJoinQuestionsAnswersRequest = $root.im.turms.proto.CheckGroupJoinQuestionsAnswersRequest.decode(reader, reader.uint32());
                            break;
                        case 501:
                            message.createGroupInvitationRequest = $root.im.turms.proto.CreateGroupInvitationRequest.decode(reader, reader.uint32());
                            break;
                        case 502:
                            message.createGroupJoinRequestRequest = $root.im.turms.proto.CreateGroupJoinRequestRequest.decode(reader, reader.uint32());
                            break;
                        case 503:
                            message.createGroupJoinQuestionRequest = $root.im.turms.proto.CreateGroupJoinQuestionRequest.decode(reader, reader.uint32());
                            break;
                        case 504:
                            message.deleteGroupInvitationRequest = $root.im.turms.proto.DeleteGroupInvitationRequest.decode(reader, reader.uint32());
                            break;
                        case 505:
                            message.deleteGroupJoinRequestRequest = $root.im.turms.proto.DeleteGroupJoinRequestRequest.decode(reader, reader.uint32());
                            break;
                        case 506:
                            message.deleteGroupJoinQuestionRequest = $root.im.turms.proto.DeleteGroupJoinQuestionRequest.decode(reader, reader.uint32());
                            break;
                        case 507:
                            message.queryGroupInvitationsRequest = $root.im.turms.proto.QueryGroupInvitationsRequest.decode(reader, reader.uint32());
                            break;
                        case 508:
                            message.queryGroupJoinRequestsRequest = $root.im.turms.proto.QueryGroupJoinRequestsRequest.decode(reader, reader.uint32());
                            break;
                        case 509:
                            message.queryGroupJoinQuestionsRequest = $root.im.turms.proto.QueryGroupJoinQuestionsRequest.decode(reader, reader.uint32());
                            break;
                        case 510:
                            message.updateGroupJoinQuestionRequest = $root.im.turms.proto.UpdateGroupJoinQuestionRequest.decode(reader, reader.uint32());
                            break;
                        case 600:
                            message.createGroupMemberRequest = $root.im.turms.proto.CreateGroupMemberRequest.decode(reader, reader.uint32());
                            break;
                        case 601:
                            message.deleteGroupMemberRequest = $root.im.turms.proto.DeleteGroupMemberRequest.decode(reader, reader.uint32());
                            break;
                        case 602:
                            message.queryGroupMembersRequest = $root.im.turms.proto.QueryGroupMembersRequest.decode(reader, reader.uint32());
                            break;
                        case 603:
                            message.updateGroupMemberRequest = $root.im.turms.proto.UpdateGroupMemberRequest.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return TurmsRequest;
            })();

            proto.CreateSessionRequest = (function() {

                /**
                 * Properties of a CreateSessionRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateSessionRequest
                 * @property {string|null} [userId] CreateSessionRequest userId
                 * @property {google.protobuf.IStringValue|null} [password] CreateSessionRequest password
                 * @property {im.turms.proto.UserStatus|null} [userStatus] CreateSessionRequest userStatus
                 * @property {im.turms.proto.DeviceType|null} [deviceType] CreateSessionRequest deviceType
                 * @property {google.protobuf.IStringValue|null} [deviceDetails] CreateSessionRequest deviceDetails
                 * @property {im.turms.proto.IUserLocation|null} [location] CreateSessionRequest location
                 */

                /**
                 * Constructs a new CreateSessionRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateSessionRequest.
                 * @implements ICreateSessionRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateSessionRequest=} [properties] Properties to set
                 */
                function CreateSessionRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateSessionRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @instance
                 */
                CreateSessionRequest.prototype.userId = "0";

                /**
                 * CreateSessionRequest password.
                 * @member {google.protobuf.IStringValue|null|undefined} password
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @instance
                 */
                CreateSessionRequest.prototype.password = null;

                /**
                 * CreateSessionRequest userStatus.
                 * @member {im.turms.proto.UserStatus} userStatus
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @instance
                 */
                CreateSessionRequest.prototype.userStatus = 0;

                /**
                 * CreateSessionRequest deviceType.
                 * @member {im.turms.proto.DeviceType} deviceType
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @instance
                 */
                CreateSessionRequest.prototype.deviceType = 0;

                /**
                 * CreateSessionRequest deviceDetails.
                 * @member {google.protobuf.IStringValue|null|undefined} deviceDetails
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @instance
                 */
                CreateSessionRequest.prototype.deviceDetails = null;

                /**
                 * CreateSessionRequest location.
                 * @member {im.turms.proto.IUserLocation|null|undefined} location
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @instance
                 */
                CreateSessionRequest.prototype.location = null;

                /**
                 * Encodes the specified CreateSessionRequest message. Does not implicitly {@link im.turms.proto.CreateSessionRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @static
                 * @param {im.turms.proto.ICreateSessionRequest} message CreateSessionRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateSessionRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.password != null && Object.hasOwnProperty.call(message, "password"))
                        $root.google.protobuf.StringValue.encode(message.password, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.userStatus != null && Object.hasOwnProperty.call(message, "userStatus"))
                        writer.uint32(/* id 3, wireType 0 =*/24).int32(message.userStatus);
                    if (message.deviceType != null && Object.hasOwnProperty.call(message, "deviceType"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.deviceType);
                    if (message.deviceDetails != null && Object.hasOwnProperty.call(message, "deviceDetails"))
                        $root.google.protobuf.StringValue.encode(message.deviceDetails, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
                    if (message.location != null && Object.hasOwnProperty.call(message, "location"))
                        $root.im.turms.proto.UserLocation.encode(message.location, writer.uint32(/* id 6, wireType 2 =*/50).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a CreateSessionRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateSessionRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateSessionRequest} CreateSessionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateSessionRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateSessionRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.password = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.userStatus = reader.int32();
                            break;
                        case 4:
                            message.deviceType = reader.int32();
                            break;
                        case 5:
                            message.deviceDetails = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 6:
                            message.location = $root.im.turms.proto.UserLocation.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateSessionRequest;
            })();

            proto.DeleteSessionRequest = (function() {

                /**
                 * Properties of a DeleteSessionRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteSessionRequest
                 */

                /**
                 * Constructs a new DeleteSessionRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteSessionRequest.
                 * @implements IDeleteSessionRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteSessionRequest=} [properties] Properties to set
                 */
                function DeleteSessionRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * Encodes the specified DeleteSessionRequest message. Does not implicitly {@link im.turms.proto.DeleteSessionRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteSessionRequest
                 * @static
                 * @param {im.turms.proto.IDeleteSessionRequest} message DeleteSessionRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteSessionRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    return writer;
                };

                /**
                 * Decodes a DeleteSessionRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteSessionRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteSessionRequest} DeleteSessionRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteSessionRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteSessionRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteSessionRequest;
            })();

            proto.QueryUserIdsNearbyRequest = (function() {

                /**
                 * Properties of a QueryUserIdsNearbyRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryUserIdsNearbyRequest
                 * @property {number|null} [latitude] QueryUserIdsNearbyRequest latitude
                 * @property {number|null} [longitude] QueryUserIdsNearbyRequest longitude
                 * @property {google.protobuf.IFloatValue|null} [distance] QueryUserIdsNearbyRequest distance
                 * @property {google.protobuf.IInt32Value|null} [maxNumber] QueryUserIdsNearbyRequest maxNumber
                 */

                /**
                 * Constructs a new QueryUserIdsNearbyRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryUserIdsNearbyRequest.
                 * @implements IQueryUserIdsNearbyRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryUserIdsNearbyRequest=} [properties] Properties to set
                 */
                function QueryUserIdsNearbyRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryUserIdsNearbyRequest latitude.
                 * @member {number} latitude
                 * @memberof im.turms.proto.QueryUserIdsNearbyRequest
                 * @instance
                 */
                QueryUserIdsNearbyRequest.prototype.latitude = 0;

                /**
                 * QueryUserIdsNearbyRequest longitude.
                 * @member {number} longitude
                 * @memberof im.turms.proto.QueryUserIdsNearbyRequest
                 * @instance
                 */
                QueryUserIdsNearbyRequest.prototype.longitude = 0;

                /**
                 * QueryUserIdsNearbyRequest distance.
                 * @member {google.protobuf.IFloatValue|null|undefined} distance
                 * @memberof im.turms.proto.QueryUserIdsNearbyRequest
                 * @instance
                 */
                QueryUserIdsNearbyRequest.prototype.distance = null;

                /**
                 * QueryUserIdsNearbyRequest maxNumber.
                 * @member {google.protobuf.IInt32Value|null|undefined} maxNumber
                 * @memberof im.turms.proto.QueryUserIdsNearbyRequest
                 * @instance
                 */
                QueryUserIdsNearbyRequest.prototype.maxNumber = null;

                /**
                 * Encodes the specified QueryUserIdsNearbyRequest message. Does not implicitly {@link im.turms.proto.QueryUserIdsNearbyRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryUserIdsNearbyRequest
                 * @static
                 * @param {im.turms.proto.IQueryUserIdsNearbyRequest} message QueryUserIdsNearbyRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryUserIdsNearbyRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.latitude != null && Object.hasOwnProperty.call(message, "latitude"))
                        writer.uint32(/* id 1, wireType 5 =*/13).float(message.latitude);
                    if (message.longitude != null && Object.hasOwnProperty.call(message, "longitude"))
                        writer.uint32(/* id 2, wireType 5 =*/21).float(message.longitude);
                    if (message.distance != null && Object.hasOwnProperty.call(message, "distance"))
                        $root.google.protobuf.FloatValue.encode(message.distance, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.maxNumber != null && Object.hasOwnProperty.call(message, "maxNumber"))
                        $root.google.protobuf.Int32Value.encode(message.maxNumber, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryUserIdsNearbyRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryUserIdsNearbyRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryUserIdsNearbyRequest} QueryUserIdsNearbyRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryUserIdsNearbyRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryUserIdsNearbyRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.latitude = reader.float();
                            break;
                        case 2:
                            message.longitude = reader.float();
                            break;
                        case 3:
                            message.distance = $root.google.protobuf.FloatValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.maxNumber = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryUserIdsNearbyRequest;
            })();

            proto.QueryUserInfosNearbyRequest = (function() {

                /**
                 * Properties of a QueryUserInfosNearbyRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryUserInfosNearbyRequest
                 * @property {number|null} [latitude] QueryUserInfosNearbyRequest latitude
                 * @property {number|null} [longitude] QueryUserInfosNearbyRequest longitude
                 * @property {google.protobuf.IFloatValue|null} [distance] QueryUserInfosNearbyRequest distance
                 * @property {google.protobuf.IInt32Value|null} [maxNumber] QueryUserInfosNearbyRequest maxNumber
                 */

                /**
                 * Constructs a new QueryUserInfosNearbyRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryUserInfosNearbyRequest.
                 * @implements IQueryUserInfosNearbyRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryUserInfosNearbyRequest=} [properties] Properties to set
                 */
                function QueryUserInfosNearbyRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryUserInfosNearbyRequest latitude.
                 * @member {number} latitude
                 * @memberof im.turms.proto.QueryUserInfosNearbyRequest
                 * @instance
                 */
                QueryUserInfosNearbyRequest.prototype.latitude = 0;

                /**
                 * QueryUserInfosNearbyRequest longitude.
                 * @member {number} longitude
                 * @memberof im.turms.proto.QueryUserInfosNearbyRequest
                 * @instance
                 */
                QueryUserInfosNearbyRequest.prototype.longitude = 0;

                /**
                 * QueryUserInfosNearbyRequest distance.
                 * @member {google.protobuf.IFloatValue|null|undefined} distance
                 * @memberof im.turms.proto.QueryUserInfosNearbyRequest
                 * @instance
                 */
                QueryUserInfosNearbyRequest.prototype.distance = null;

                /**
                 * QueryUserInfosNearbyRequest maxNumber.
                 * @member {google.protobuf.IInt32Value|null|undefined} maxNumber
                 * @memberof im.turms.proto.QueryUserInfosNearbyRequest
                 * @instance
                 */
                QueryUserInfosNearbyRequest.prototype.maxNumber = null;

                /**
                 * Encodes the specified QueryUserInfosNearbyRequest message. Does not implicitly {@link im.turms.proto.QueryUserInfosNearbyRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryUserInfosNearbyRequest
                 * @static
                 * @param {im.turms.proto.IQueryUserInfosNearbyRequest} message QueryUserInfosNearbyRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryUserInfosNearbyRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.latitude != null && Object.hasOwnProperty.call(message, "latitude"))
                        writer.uint32(/* id 1, wireType 5 =*/13).float(message.latitude);
                    if (message.longitude != null && Object.hasOwnProperty.call(message, "longitude"))
                        writer.uint32(/* id 2, wireType 5 =*/21).float(message.longitude);
                    if (message.distance != null && Object.hasOwnProperty.call(message, "distance"))
                        $root.google.protobuf.FloatValue.encode(message.distance, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.maxNumber != null && Object.hasOwnProperty.call(message, "maxNumber"))
                        $root.google.protobuf.Int32Value.encode(message.maxNumber, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryUserInfosNearbyRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryUserInfosNearbyRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryUserInfosNearbyRequest} QueryUserInfosNearbyRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryUserInfosNearbyRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryUserInfosNearbyRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.latitude = reader.float();
                            break;
                        case 2:
                            message.longitude = reader.float();
                            break;
                        case 3:
                            message.distance = $root.google.protobuf.FloatValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.maxNumber = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryUserInfosNearbyRequest;
            })();

            proto.QueryUserOnlineStatusesRequest = (function() {

                /**
                 * Properties of a QueryUserOnlineStatusesRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryUserOnlineStatusesRequest
                 * @property {Array.<string>|null} [userIds] QueryUserOnlineStatusesRequest userIds
                 */

                /**
                 * Constructs a new QueryUserOnlineStatusesRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryUserOnlineStatusesRequest.
                 * @implements IQueryUserOnlineStatusesRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryUserOnlineStatusesRequest=} [properties] Properties to set
                 */
                function QueryUserOnlineStatusesRequest(properties) {
                    this.userIds = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryUserOnlineStatusesRequest userIds.
                 * @member {Array.<string>} userIds
                 * @memberof im.turms.proto.QueryUserOnlineStatusesRequest
                 * @instance
                 */
                QueryUserOnlineStatusesRequest.prototype.userIds = $util.emptyArray;

                /**
                 * Encodes the specified QueryUserOnlineStatusesRequest message. Does not implicitly {@link im.turms.proto.QueryUserOnlineStatusesRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryUserOnlineStatusesRequest
                 * @static
                 * @param {im.turms.proto.IQueryUserOnlineStatusesRequest} message QueryUserOnlineStatusesRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryUserOnlineStatusesRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userIds != null && message.userIds.length) {
                        writer.uint32(/* id 1, wireType 2 =*/10).fork();
                        for (var i = 0; i < message.userIds.length; ++i)
                            writer.int64(message.userIds[i]);
                        writer.ldelim();
                    }
                    return writer;
                };

                /**
                 * Decodes a QueryUserOnlineStatusesRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryUserOnlineStatusesRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryUserOnlineStatusesRequest} QueryUserOnlineStatusesRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryUserOnlineStatusesRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryUserOnlineStatusesRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userIds && message.userIds.length))
                                message.userIds = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.userIds.push(reader.int64().toString());
                            } else
                                message.userIds.push(reader.int64().toString());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryUserOnlineStatusesRequest;
            })();

            proto.QueryUserProfileRequest = (function() {

                /**
                 * Properties of a QueryUserProfileRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryUserProfileRequest
                 * @property {string|null} [userId] QueryUserProfileRequest userId
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryUserProfileRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryUserProfileRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryUserProfileRequest.
                 * @implements IQueryUserProfileRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryUserProfileRequest=} [properties] Properties to set
                 */
                function QueryUserProfileRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryUserProfileRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.QueryUserProfileRequest
                 * @instance
                 */
                QueryUserProfileRequest.prototype.userId = "0";

                /**
                 * QueryUserProfileRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryUserProfileRequest
                 * @instance
                 */
                QueryUserProfileRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryUserProfileRequest message. Does not implicitly {@link im.turms.proto.QueryUserProfileRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryUserProfileRequest
                 * @static
                 * @param {im.turms.proto.IQueryUserProfileRequest} message QueryUserProfileRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryUserProfileRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryUserProfileRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryUserProfileRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryUserProfileRequest} QueryUserProfileRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryUserProfileRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryUserProfileRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryUserProfileRequest;
            })();

            proto.CreateFriendRequestRequest = (function() {

                /**
                 * Properties of a CreateFriendRequestRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateFriendRequestRequest
                 * @property {string|null} [recipientId] CreateFriendRequestRequest recipientId
                 * @property {string|null} [content] CreateFriendRequestRequest content
                 */

                /**
                 * Constructs a new CreateFriendRequestRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateFriendRequestRequest.
                 * @implements ICreateFriendRequestRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateFriendRequestRequest=} [properties] Properties to set
                 */
                function CreateFriendRequestRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateFriendRequestRequest recipientId.
                 * @member {string} recipientId
                 * @memberof im.turms.proto.CreateFriendRequestRequest
                 * @instance
                 */
                CreateFriendRequestRequest.prototype.recipientId = "0";

                /**
                 * CreateFriendRequestRequest content.
                 * @member {string} content
                 * @memberof im.turms.proto.CreateFriendRequestRequest
                 * @instance
                 */
                CreateFriendRequestRequest.prototype.content = "";

                /**
                 * Encodes the specified CreateFriendRequestRequest message. Does not implicitly {@link im.turms.proto.CreateFriendRequestRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateFriendRequestRequest
                 * @static
                 * @param {im.turms.proto.ICreateFriendRequestRequest} message CreateFriendRequestRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateFriendRequestRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.recipientId != null && Object.hasOwnProperty.call(message, "recipientId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.recipientId);
                    if (message.content != null && Object.hasOwnProperty.call(message, "content"))
                        writer.uint32(/* id 2, wireType 2 =*/18).string(message.content);
                    return writer;
                };

                /**
                 * Decodes a CreateFriendRequestRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateFriendRequestRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateFriendRequestRequest} CreateFriendRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateFriendRequestRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateFriendRequestRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.recipientId = reader.int64().toString();
                            break;
                        case 2:
                            message.content = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateFriendRequestRequest;
            })();

            proto.CreateRelationshipGroupRequest = (function() {

                /**
                 * Properties of a CreateRelationshipGroupRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateRelationshipGroupRequest
                 * @property {string|null} [name] CreateRelationshipGroupRequest name
                 */

                /**
                 * Constructs a new CreateRelationshipGroupRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateRelationshipGroupRequest.
                 * @implements ICreateRelationshipGroupRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateRelationshipGroupRequest=} [properties] Properties to set
                 */
                function CreateRelationshipGroupRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateRelationshipGroupRequest name.
                 * @member {string} name
                 * @memberof im.turms.proto.CreateRelationshipGroupRequest
                 * @instance
                 */
                CreateRelationshipGroupRequest.prototype.name = "";

                /**
                 * Encodes the specified CreateRelationshipGroupRequest message. Does not implicitly {@link im.turms.proto.CreateRelationshipGroupRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateRelationshipGroupRequest
                 * @static
                 * @param {im.turms.proto.ICreateRelationshipGroupRequest} message CreateRelationshipGroupRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateRelationshipGroupRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        writer.uint32(/* id 1, wireType 2 =*/10).string(message.name);
                    return writer;
                };

                /**
                 * Decodes a CreateRelationshipGroupRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateRelationshipGroupRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateRelationshipGroupRequest} CreateRelationshipGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateRelationshipGroupRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateRelationshipGroupRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.name = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateRelationshipGroupRequest;
            })();

            proto.CreateRelationshipRequest = (function() {

                /**
                 * Properties of a CreateRelationshipRequest.
                 * @memberof im.turms.proto
                 * @interface ICreateRelationshipRequest
                 * @property {string|null} [userId] CreateRelationshipRequest userId
                 * @property {boolean|null} [blocked] CreateRelationshipRequest blocked
                 * @property {google.protobuf.IInt32Value|null} [groupIndex] CreateRelationshipRequest groupIndex
                 */

                /**
                 * Constructs a new CreateRelationshipRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a CreateRelationshipRequest.
                 * @implements ICreateRelationshipRequest
                 * @constructor
                 * @param {im.turms.proto.ICreateRelationshipRequest=} [properties] Properties to set
                 */
                function CreateRelationshipRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * CreateRelationshipRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.CreateRelationshipRequest
                 * @instance
                 */
                CreateRelationshipRequest.prototype.userId = "0";

                /**
                 * CreateRelationshipRequest blocked.
                 * @member {boolean} blocked
                 * @memberof im.turms.proto.CreateRelationshipRequest
                 * @instance
                 */
                CreateRelationshipRequest.prototype.blocked = false;

                /**
                 * CreateRelationshipRequest groupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} groupIndex
                 * @memberof im.turms.proto.CreateRelationshipRequest
                 * @instance
                 */
                CreateRelationshipRequest.prototype.groupIndex = null;

                /**
                 * Encodes the specified CreateRelationshipRequest message. Does not implicitly {@link im.turms.proto.CreateRelationshipRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.CreateRelationshipRequest
                 * @static
                 * @param {im.turms.proto.ICreateRelationshipRequest} message CreateRelationshipRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                CreateRelationshipRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.blocked != null && Object.hasOwnProperty.call(message, "blocked"))
                        writer.uint32(/* id 2, wireType 0 =*/16).bool(message.blocked);
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.groupIndex, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a CreateRelationshipRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.CreateRelationshipRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.CreateRelationshipRequest} CreateRelationshipRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                CreateRelationshipRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.CreateRelationshipRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.blocked = reader.bool();
                            break;
                        case 3:
                            message.groupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return CreateRelationshipRequest;
            })();

            proto.DeleteRelationshipGroupMemberRequest = (function() {

                /**
                 * Properties of a DeleteRelationshipGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteRelationshipGroupMemberRequest
                 * @property {string|null} [userId] DeleteRelationshipGroupMemberRequest userId
                 * @property {number|null} [groupIndex] DeleteRelationshipGroupMemberRequest groupIndex
                 * @property {google.protobuf.IInt32Value|null} [targetGroupIndex] DeleteRelationshipGroupMemberRequest targetGroupIndex
                 */

                /**
                 * Constructs a new DeleteRelationshipGroupMemberRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteRelationshipGroupMemberRequest.
                 * @implements IDeleteRelationshipGroupMemberRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteRelationshipGroupMemberRequest=} [properties] Properties to set
                 */
                function DeleteRelationshipGroupMemberRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteRelationshipGroupMemberRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.DeleteRelationshipGroupMemberRequest
                 * @instance
                 */
                DeleteRelationshipGroupMemberRequest.prototype.userId = "0";

                /**
                 * DeleteRelationshipGroupMemberRequest groupIndex.
                 * @member {number} groupIndex
                 * @memberof im.turms.proto.DeleteRelationshipGroupMemberRequest
                 * @instance
                 */
                DeleteRelationshipGroupMemberRequest.prototype.groupIndex = 0;

                /**
                 * DeleteRelationshipGroupMemberRequest targetGroupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} targetGroupIndex
                 * @memberof im.turms.proto.DeleteRelationshipGroupMemberRequest
                 * @instance
                 */
                DeleteRelationshipGroupMemberRequest.prototype.targetGroupIndex = null;

                /**
                 * Encodes the specified DeleteRelationshipGroupMemberRequest message. Does not implicitly {@link im.turms.proto.DeleteRelationshipGroupMemberRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteRelationshipGroupMemberRequest
                 * @static
                 * @param {im.turms.proto.IDeleteRelationshipGroupMemberRequest} message DeleteRelationshipGroupMemberRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteRelationshipGroupMemberRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int32(message.groupIndex);
                    if (message.targetGroupIndex != null && Object.hasOwnProperty.call(message, "targetGroupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.targetGroupIndex, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a DeleteRelationshipGroupMemberRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteRelationshipGroupMemberRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteRelationshipGroupMemberRequest} DeleteRelationshipGroupMemberRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteRelationshipGroupMemberRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteRelationshipGroupMemberRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.groupIndex = reader.int32();
                            break;
                        case 3:
                            message.targetGroupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteRelationshipGroupMemberRequest;
            })();

            proto.DeleteRelationshipGroupRequest = (function() {

                /**
                 * Properties of a DeleteRelationshipGroupRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteRelationshipGroupRequest
                 * @property {number|null} [groupIndex] DeleteRelationshipGroupRequest groupIndex
                 * @property {google.protobuf.IInt32Value|null} [targetGroupIndex] DeleteRelationshipGroupRequest targetGroupIndex
                 */

                /**
                 * Constructs a new DeleteRelationshipGroupRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteRelationshipGroupRequest.
                 * @implements IDeleteRelationshipGroupRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteRelationshipGroupRequest=} [properties] Properties to set
                 */
                function DeleteRelationshipGroupRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteRelationshipGroupRequest groupIndex.
                 * @member {number} groupIndex
                 * @memberof im.turms.proto.DeleteRelationshipGroupRequest
                 * @instance
                 */
                DeleteRelationshipGroupRequest.prototype.groupIndex = 0;

                /**
                 * DeleteRelationshipGroupRequest targetGroupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} targetGroupIndex
                 * @memberof im.turms.proto.DeleteRelationshipGroupRequest
                 * @instance
                 */
                DeleteRelationshipGroupRequest.prototype.targetGroupIndex = null;

                /**
                 * Encodes the specified DeleteRelationshipGroupRequest message. Does not implicitly {@link im.turms.proto.DeleteRelationshipGroupRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteRelationshipGroupRequest
                 * @static
                 * @param {im.turms.proto.IDeleteRelationshipGroupRequest} message DeleteRelationshipGroupRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteRelationshipGroupRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.groupIndex);
                    if (message.targetGroupIndex != null && Object.hasOwnProperty.call(message, "targetGroupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.targetGroupIndex, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a DeleteRelationshipGroupRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteRelationshipGroupRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteRelationshipGroupRequest} DeleteRelationshipGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteRelationshipGroupRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteRelationshipGroupRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupIndex = reader.int32();
                            break;
                        case 2:
                            message.targetGroupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteRelationshipGroupRequest;
            })();

            proto.DeleteRelationshipRequest = (function() {

                /**
                 * Properties of a DeleteRelationshipRequest.
                 * @memberof im.turms.proto
                 * @interface IDeleteRelationshipRequest
                 * @property {string|null} [userId] DeleteRelationshipRequest userId
                 * @property {google.protobuf.IInt32Value|null} [groupIndex] DeleteRelationshipRequest groupIndex
                 * @property {google.protobuf.IInt32Value|null} [targetGroupIndex] DeleteRelationshipRequest targetGroupIndex
                 */

                /**
                 * Constructs a new DeleteRelationshipRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a DeleteRelationshipRequest.
                 * @implements IDeleteRelationshipRequest
                 * @constructor
                 * @param {im.turms.proto.IDeleteRelationshipRequest=} [properties] Properties to set
                 */
                function DeleteRelationshipRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * DeleteRelationshipRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.DeleteRelationshipRequest
                 * @instance
                 */
                DeleteRelationshipRequest.prototype.userId = "0";

                /**
                 * DeleteRelationshipRequest groupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} groupIndex
                 * @memberof im.turms.proto.DeleteRelationshipRequest
                 * @instance
                 */
                DeleteRelationshipRequest.prototype.groupIndex = null;

                /**
                 * DeleteRelationshipRequest targetGroupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} targetGroupIndex
                 * @memberof im.turms.proto.DeleteRelationshipRequest
                 * @instance
                 */
                DeleteRelationshipRequest.prototype.targetGroupIndex = null;

                /**
                 * Encodes the specified DeleteRelationshipRequest message. Does not implicitly {@link im.turms.proto.DeleteRelationshipRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.DeleteRelationshipRequest
                 * @static
                 * @param {im.turms.proto.IDeleteRelationshipRequest} message DeleteRelationshipRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                DeleteRelationshipRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.groupIndex, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.targetGroupIndex != null && Object.hasOwnProperty.call(message, "targetGroupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.targetGroupIndex, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a DeleteRelationshipRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.DeleteRelationshipRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.DeleteRelationshipRequest} DeleteRelationshipRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                DeleteRelationshipRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.DeleteRelationshipRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.groupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.targetGroupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return DeleteRelationshipRequest;
            })();

            proto.QueryFriendRequestsRequest = (function() {

                /**
                 * Properties of a QueryFriendRequestsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryFriendRequestsRequest
                 * @property {boolean|null} [areSentByMe] QueryFriendRequestsRequest areSentByMe
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryFriendRequestsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryFriendRequestsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryFriendRequestsRequest.
                 * @implements IQueryFriendRequestsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryFriendRequestsRequest=} [properties] Properties to set
                 */
                function QueryFriendRequestsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryFriendRequestsRequest areSentByMe.
                 * @member {boolean} areSentByMe
                 * @memberof im.turms.proto.QueryFriendRequestsRequest
                 * @instance
                 */
                QueryFriendRequestsRequest.prototype.areSentByMe = false;

                /**
                 * QueryFriendRequestsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryFriendRequestsRequest
                 * @instance
                 */
                QueryFriendRequestsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryFriendRequestsRequest message. Does not implicitly {@link im.turms.proto.QueryFriendRequestsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryFriendRequestsRequest
                 * @static
                 * @param {im.turms.proto.IQueryFriendRequestsRequest} message QueryFriendRequestsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryFriendRequestsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.areSentByMe != null && Object.hasOwnProperty.call(message, "areSentByMe"))
                        writer.uint32(/* id 1, wireType 0 =*/8).bool(message.areSentByMe);
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryFriendRequestsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryFriendRequestsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryFriendRequestsRequest} QueryFriendRequestsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryFriendRequestsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryFriendRequestsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.areSentByMe = reader.bool();
                            break;
                        case 2:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryFriendRequestsRequest;
            })();

            proto.QueryRelatedUserIdsRequest = (function() {

                /**
                 * Properties of a QueryRelatedUserIdsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryRelatedUserIdsRequest
                 * @property {google.protobuf.IBoolValue|null} [blocked] QueryRelatedUserIdsRequest blocked
                 * @property {google.protobuf.IInt32Value|null} [groupIndex] QueryRelatedUserIdsRequest groupIndex
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryRelatedUserIdsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryRelatedUserIdsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryRelatedUserIdsRequest.
                 * @implements IQueryRelatedUserIdsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryRelatedUserIdsRequest=} [properties] Properties to set
                 */
                function QueryRelatedUserIdsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryRelatedUserIdsRequest blocked.
                 * @member {google.protobuf.IBoolValue|null|undefined} blocked
                 * @memberof im.turms.proto.QueryRelatedUserIdsRequest
                 * @instance
                 */
                QueryRelatedUserIdsRequest.prototype.blocked = null;

                /**
                 * QueryRelatedUserIdsRequest groupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} groupIndex
                 * @memberof im.turms.proto.QueryRelatedUserIdsRequest
                 * @instance
                 */
                QueryRelatedUserIdsRequest.prototype.groupIndex = null;

                /**
                 * QueryRelatedUserIdsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryRelatedUserIdsRequest
                 * @instance
                 */
                QueryRelatedUserIdsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryRelatedUserIdsRequest message. Does not implicitly {@link im.turms.proto.QueryRelatedUserIdsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryRelatedUserIdsRequest
                 * @static
                 * @param {im.turms.proto.IQueryRelatedUserIdsRequest} message QueryRelatedUserIdsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryRelatedUserIdsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.blocked != null && Object.hasOwnProperty.call(message, "blocked"))
                        $root.google.protobuf.BoolValue.encode(message.blocked, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.groupIndex, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryRelatedUserIdsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryRelatedUserIdsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryRelatedUserIdsRequest} QueryRelatedUserIdsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryRelatedUserIdsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryRelatedUserIdsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.blocked = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.groupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryRelatedUserIdsRequest;
            })();

            proto.QueryRelationshipGroupsRequest = (function() {

                /**
                 * Properties of a QueryRelationshipGroupsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryRelationshipGroupsRequest
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryRelationshipGroupsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryRelationshipGroupsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryRelationshipGroupsRequest.
                 * @implements IQueryRelationshipGroupsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryRelationshipGroupsRequest=} [properties] Properties to set
                 */
                function QueryRelationshipGroupsRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryRelationshipGroupsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryRelationshipGroupsRequest
                 * @instance
                 */
                QueryRelationshipGroupsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryRelationshipGroupsRequest message. Does not implicitly {@link im.turms.proto.QueryRelationshipGroupsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryRelationshipGroupsRequest
                 * @static
                 * @param {im.turms.proto.IQueryRelationshipGroupsRequest} message QueryRelationshipGroupsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryRelationshipGroupsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryRelationshipGroupsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryRelationshipGroupsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryRelationshipGroupsRequest} QueryRelationshipGroupsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryRelationshipGroupsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryRelationshipGroupsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryRelationshipGroupsRequest;
            })();

            proto.QueryRelationshipsRequest = (function() {

                /**
                 * Properties of a QueryRelationshipsRequest.
                 * @memberof im.turms.proto
                 * @interface IQueryRelationshipsRequest
                 * @property {Array.<string>|null} [userIds] QueryRelationshipsRequest userIds
                 * @property {google.protobuf.IBoolValue|null} [blocked] QueryRelationshipsRequest blocked
                 * @property {google.protobuf.IInt32Value|null} [groupIndex] QueryRelationshipsRequest groupIndex
                 * @property {google.protobuf.IInt64Value|null} [lastUpdatedDate] QueryRelationshipsRequest lastUpdatedDate
                 */

                /**
                 * Constructs a new QueryRelationshipsRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents a QueryRelationshipsRequest.
                 * @implements IQueryRelationshipsRequest
                 * @constructor
                 * @param {im.turms.proto.IQueryRelationshipsRequest=} [properties] Properties to set
                 */
                function QueryRelationshipsRequest(properties) {
                    this.userIds = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * QueryRelationshipsRequest userIds.
                 * @member {Array.<string>} userIds
                 * @memberof im.turms.proto.QueryRelationshipsRequest
                 * @instance
                 */
                QueryRelationshipsRequest.prototype.userIds = $util.emptyArray;

                /**
                 * QueryRelationshipsRequest blocked.
                 * @member {google.protobuf.IBoolValue|null|undefined} blocked
                 * @memberof im.turms.proto.QueryRelationshipsRequest
                 * @instance
                 */
                QueryRelationshipsRequest.prototype.blocked = null;

                /**
                 * QueryRelationshipsRequest groupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} groupIndex
                 * @memberof im.turms.proto.QueryRelationshipsRequest
                 * @instance
                 */
                QueryRelationshipsRequest.prototype.groupIndex = null;

                /**
                 * QueryRelationshipsRequest lastUpdatedDate.
                 * @member {google.protobuf.IInt64Value|null|undefined} lastUpdatedDate
                 * @memberof im.turms.proto.QueryRelationshipsRequest
                 * @instance
                 */
                QueryRelationshipsRequest.prototype.lastUpdatedDate = null;

                /**
                 * Encodes the specified QueryRelationshipsRequest message. Does not implicitly {@link im.turms.proto.QueryRelationshipsRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.QueryRelationshipsRequest
                 * @static
                 * @param {im.turms.proto.IQueryRelationshipsRequest} message QueryRelationshipsRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                QueryRelationshipsRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userIds != null && message.userIds.length) {
                        writer.uint32(/* id 1, wireType 2 =*/10).fork();
                        for (var i = 0; i < message.userIds.length; ++i)
                            writer.int64(message.userIds[i]);
                        writer.ldelim();
                    }
                    if (message.blocked != null && Object.hasOwnProperty.call(message, "blocked"))
                        $root.google.protobuf.BoolValue.encode(message.blocked, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.groupIndex, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.lastUpdatedDate != null && Object.hasOwnProperty.call(message, "lastUpdatedDate"))
                        $root.google.protobuf.Int64Value.encode(message.lastUpdatedDate, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes a QueryRelationshipsRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.QueryRelationshipsRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.QueryRelationshipsRequest} QueryRelationshipsRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                QueryRelationshipsRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.QueryRelationshipsRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            if (!(message.userIds && message.userIds.length))
                                message.userIds = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.userIds.push(reader.int64().toString());
                            } else
                                message.userIds.push(reader.int64().toString());
                            break;
                        case 2:
                            message.blocked = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.groupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.lastUpdatedDate = $root.google.protobuf.Int64Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return QueryRelationshipsRequest;
            })();

            proto.UpdateFriendRequestRequest = (function() {

                /**
                 * Properties of an UpdateFriendRequestRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateFriendRequestRequest
                 * @property {string|null} [requestId] UpdateFriendRequestRequest requestId
                 * @property {im.turms.proto.ResponseAction|null} [responseAction] UpdateFriendRequestRequest responseAction
                 * @property {google.protobuf.IStringValue|null} [reason] UpdateFriendRequestRequest reason
                 */

                /**
                 * Constructs a new UpdateFriendRequestRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateFriendRequestRequest.
                 * @implements IUpdateFriendRequestRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateFriendRequestRequest=} [properties] Properties to set
                 */
                function UpdateFriendRequestRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateFriendRequestRequest requestId.
                 * @member {string} requestId
                 * @memberof im.turms.proto.UpdateFriendRequestRequest
                 * @instance
                 */
                UpdateFriendRequestRequest.prototype.requestId = "0";

                /**
                 * UpdateFriendRequestRequest responseAction.
                 * @member {im.turms.proto.ResponseAction} responseAction
                 * @memberof im.turms.proto.UpdateFriendRequestRequest
                 * @instance
                 */
                UpdateFriendRequestRequest.prototype.responseAction = 0;

                /**
                 * UpdateFriendRequestRequest reason.
                 * @member {google.protobuf.IStringValue|null|undefined} reason
                 * @memberof im.turms.proto.UpdateFriendRequestRequest
                 * @instance
                 */
                UpdateFriendRequestRequest.prototype.reason = null;

                /**
                 * Encodes the specified UpdateFriendRequestRequest message. Does not implicitly {@link im.turms.proto.UpdateFriendRequestRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateFriendRequestRequest
                 * @static
                 * @param {im.turms.proto.IUpdateFriendRequestRequest} message UpdateFriendRequestRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateFriendRequestRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.requestId != null && Object.hasOwnProperty.call(message, "requestId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.requestId);
                    if (message.responseAction != null && Object.hasOwnProperty.call(message, "responseAction"))
                        writer.uint32(/* id 2, wireType 0 =*/16).int32(message.responseAction);
                    if (message.reason != null && Object.hasOwnProperty.call(message, "reason"))
                        $root.google.protobuf.StringValue.encode(message.reason, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an UpdateFriendRequestRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateFriendRequestRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateFriendRequestRequest} UpdateFriendRequestRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateFriendRequestRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateFriendRequestRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.requestId = reader.int64().toString();
                            break;
                        case 2:
                            message.responseAction = reader.int32();
                            break;
                        case 3:
                            message.reason = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateFriendRequestRequest;
            })();

            proto.UpdateRelationshipGroupRequest = (function() {

                /**
                 * Properties of an UpdateRelationshipGroupRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateRelationshipGroupRequest
                 * @property {number|null} [groupIndex] UpdateRelationshipGroupRequest groupIndex
                 * @property {string|null} [newName] UpdateRelationshipGroupRequest newName
                 */

                /**
                 * Constructs a new UpdateRelationshipGroupRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateRelationshipGroupRequest.
                 * @implements IUpdateRelationshipGroupRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateRelationshipGroupRequest=} [properties] Properties to set
                 */
                function UpdateRelationshipGroupRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateRelationshipGroupRequest groupIndex.
                 * @member {number} groupIndex
                 * @memberof im.turms.proto.UpdateRelationshipGroupRequest
                 * @instance
                 */
                UpdateRelationshipGroupRequest.prototype.groupIndex = 0;

                /**
                 * UpdateRelationshipGroupRequest newName.
                 * @member {string} newName
                 * @memberof im.turms.proto.UpdateRelationshipGroupRequest
                 * @instance
                 */
                UpdateRelationshipGroupRequest.prototype.newName = "";

                /**
                 * Encodes the specified UpdateRelationshipGroupRequest message. Does not implicitly {@link im.turms.proto.UpdateRelationshipGroupRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateRelationshipGroupRequest
                 * @static
                 * @param {im.turms.proto.IUpdateRelationshipGroupRequest} message UpdateRelationshipGroupRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateRelationshipGroupRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.groupIndex != null && Object.hasOwnProperty.call(message, "groupIndex"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.groupIndex);
                    if (message.newName != null && Object.hasOwnProperty.call(message, "newName"))
                        writer.uint32(/* id 2, wireType 2 =*/18).string(message.newName);
                    return writer;
                };

                /**
                 * Decodes an UpdateRelationshipGroupRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateRelationshipGroupRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateRelationshipGroupRequest} UpdateRelationshipGroupRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateRelationshipGroupRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateRelationshipGroupRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.groupIndex = reader.int32();
                            break;
                        case 2:
                            message.newName = reader.string();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateRelationshipGroupRequest;
            })();

            proto.UpdateRelationshipRequest = (function() {

                /**
                 * Properties of an UpdateRelationshipRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateRelationshipRequest
                 * @property {string|null} [userId] UpdateRelationshipRequest userId
                 * @property {google.protobuf.IBoolValue|null} [blocked] UpdateRelationshipRequest blocked
                 * @property {google.protobuf.IInt32Value|null} [newGroupIndex] UpdateRelationshipRequest newGroupIndex
                 * @property {google.protobuf.IInt32Value|null} [deleteGroupIndex] UpdateRelationshipRequest deleteGroupIndex
                 */

                /**
                 * Constructs a new UpdateRelationshipRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateRelationshipRequest.
                 * @implements IUpdateRelationshipRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateRelationshipRequest=} [properties] Properties to set
                 */
                function UpdateRelationshipRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateRelationshipRequest userId.
                 * @member {string} userId
                 * @memberof im.turms.proto.UpdateRelationshipRequest
                 * @instance
                 */
                UpdateRelationshipRequest.prototype.userId = "0";

                /**
                 * UpdateRelationshipRequest blocked.
                 * @member {google.protobuf.IBoolValue|null|undefined} blocked
                 * @memberof im.turms.proto.UpdateRelationshipRequest
                 * @instance
                 */
                UpdateRelationshipRequest.prototype.blocked = null;

                /**
                 * UpdateRelationshipRequest newGroupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} newGroupIndex
                 * @memberof im.turms.proto.UpdateRelationshipRequest
                 * @instance
                 */
                UpdateRelationshipRequest.prototype.newGroupIndex = null;

                /**
                 * UpdateRelationshipRequest deleteGroupIndex.
                 * @member {google.protobuf.IInt32Value|null|undefined} deleteGroupIndex
                 * @memberof im.turms.proto.UpdateRelationshipRequest
                 * @instance
                 */
                UpdateRelationshipRequest.prototype.deleteGroupIndex = null;

                /**
                 * Encodes the specified UpdateRelationshipRequest message. Does not implicitly {@link im.turms.proto.UpdateRelationshipRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateRelationshipRequest
                 * @static
                 * @param {im.turms.proto.IUpdateRelationshipRequest} message UpdateRelationshipRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateRelationshipRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int64(message.userId);
                    if (message.blocked != null && Object.hasOwnProperty.call(message, "blocked"))
                        $root.google.protobuf.BoolValue.encode(message.blocked, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.newGroupIndex != null && Object.hasOwnProperty.call(message, "newGroupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.newGroupIndex, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.deleteGroupIndex != null && Object.hasOwnProperty.call(message, "deleteGroupIndex"))
                        $root.google.protobuf.Int32Value.encode(message.deleteGroupIndex, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an UpdateRelationshipRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateRelationshipRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateRelationshipRequest} UpdateRelationshipRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateRelationshipRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateRelationshipRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userId = reader.int64().toString();
                            break;
                        case 2:
                            message.blocked = $root.google.protobuf.BoolValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.newGroupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.deleteGroupIndex = $root.google.protobuf.Int32Value.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateRelationshipRequest;
            })();

            proto.UpdateUserLocationRequest = (function() {

                /**
                 * Properties of an UpdateUserLocationRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateUserLocationRequest
                 * @property {number|null} [latitude] UpdateUserLocationRequest latitude
                 * @property {number|null} [longitude] UpdateUserLocationRequest longitude
                 * @property {google.protobuf.IStringValue|null} [name] UpdateUserLocationRequest name
                 * @property {google.protobuf.IStringValue|null} [address] UpdateUserLocationRequest address
                 */

                /**
                 * Constructs a new UpdateUserLocationRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateUserLocationRequest.
                 * @implements IUpdateUserLocationRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateUserLocationRequest=} [properties] Properties to set
                 */
                function UpdateUserLocationRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateUserLocationRequest latitude.
                 * @member {number} latitude
                 * @memberof im.turms.proto.UpdateUserLocationRequest
                 * @instance
                 */
                UpdateUserLocationRequest.prototype.latitude = 0;

                /**
                 * UpdateUserLocationRequest longitude.
                 * @member {number} longitude
                 * @memberof im.turms.proto.UpdateUserLocationRequest
                 * @instance
                 */
                UpdateUserLocationRequest.prototype.longitude = 0;

                /**
                 * UpdateUserLocationRequest name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.UpdateUserLocationRequest
                 * @instance
                 */
                UpdateUserLocationRequest.prototype.name = null;

                /**
                 * UpdateUserLocationRequest address.
                 * @member {google.protobuf.IStringValue|null|undefined} address
                 * @memberof im.turms.proto.UpdateUserLocationRequest
                 * @instance
                 */
                UpdateUserLocationRequest.prototype.address = null;

                /**
                 * Encodes the specified UpdateUserLocationRequest message. Does not implicitly {@link im.turms.proto.UpdateUserLocationRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateUserLocationRequest
                 * @static
                 * @param {im.turms.proto.IUpdateUserLocationRequest} message UpdateUserLocationRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateUserLocationRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.latitude != null && Object.hasOwnProperty.call(message, "latitude"))
                        writer.uint32(/* id 1, wireType 5 =*/13).float(message.latitude);
                    if (message.longitude != null && Object.hasOwnProperty.call(message, "longitude"))
                        writer.uint32(/* id 2, wireType 5 =*/21).float(message.longitude);
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.address != null && Object.hasOwnProperty.call(message, "address"))
                        $root.google.protobuf.StringValue.encode(message.address, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
                    return writer;
                };

                /**
                 * Decodes an UpdateUserLocationRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateUserLocationRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateUserLocationRequest} UpdateUserLocationRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateUserLocationRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateUserLocationRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.latitude = reader.float();
                            break;
                        case 2:
                            message.longitude = reader.float();
                            break;
                        case 3:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.address = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateUserLocationRequest;
            })();

            proto.UpdateUserOnlineStatusRequest = (function() {

                /**
                 * Properties of an UpdateUserOnlineStatusRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateUserOnlineStatusRequest
                 * @property {im.turms.proto.UserStatus|null} [userStatus] UpdateUserOnlineStatusRequest userStatus
                 * @property {Array.<im.turms.proto.DeviceType>|null} [deviceTypes] UpdateUserOnlineStatusRequest deviceTypes
                 */

                /**
                 * Constructs a new UpdateUserOnlineStatusRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateUserOnlineStatusRequest.
                 * @implements IUpdateUserOnlineStatusRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateUserOnlineStatusRequest=} [properties] Properties to set
                 */
                function UpdateUserOnlineStatusRequest(properties) {
                    this.deviceTypes = [];
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateUserOnlineStatusRequest userStatus.
                 * @member {im.turms.proto.UserStatus} userStatus
                 * @memberof im.turms.proto.UpdateUserOnlineStatusRequest
                 * @instance
                 */
                UpdateUserOnlineStatusRequest.prototype.userStatus = 0;

                /**
                 * UpdateUserOnlineStatusRequest deviceTypes.
                 * @member {Array.<im.turms.proto.DeviceType>} deviceTypes
                 * @memberof im.turms.proto.UpdateUserOnlineStatusRequest
                 * @instance
                 */
                UpdateUserOnlineStatusRequest.prototype.deviceTypes = $util.emptyArray;

                /**
                 * Encodes the specified UpdateUserOnlineStatusRequest message. Does not implicitly {@link im.turms.proto.UpdateUserOnlineStatusRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateUserOnlineStatusRequest
                 * @static
                 * @param {im.turms.proto.IUpdateUserOnlineStatusRequest} message UpdateUserOnlineStatusRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateUserOnlineStatusRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.userStatus != null && Object.hasOwnProperty.call(message, "userStatus"))
                        writer.uint32(/* id 1, wireType 0 =*/8).int32(message.userStatus);
                    if (message.deviceTypes != null && message.deviceTypes.length) {
                        writer.uint32(/* id 2, wireType 2 =*/18).fork();
                        for (var i = 0; i < message.deviceTypes.length; ++i)
                            writer.int32(message.deviceTypes[i]);
                        writer.ldelim();
                    }
                    return writer;
                };

                /**
                 * Decodes an UpdateUserOnlineStatusRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateUserOnlineStatusRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateUserOnlineStatusRequest} UpdateUserOnlineStatusRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateUserOnlineStatusRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateUserOnlineStatusRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.userStatus = reader.int32();
                            break;
                        case 2:
                            if (!(message.deviceTypes && message.deviceTypes.length))
                                message.deviceTypes = [];
                            if ((tag & 7) === 2) {
                                var end2 = reader.uint32() + reader.pos;
                                while (reader.pos < end2)
                                    message.deviceTypes.push(reader.int32());
                            } else
                                message.deviceTypes.push(reader.int32());
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateUserOnlineStatusRequest;
            })();

            proto.UpdateUserRequest = (function() {

                /**
                 * Properties of an UpdateUserRequest.
                 * @memberof im.turms.proto
                 * @interface IUpdateUserRequest
                 * @property {google.protobuf.IStringValue|null} [password] UpdateUserRequest password
                 * @property {google.protobuf.IStringValue|null} [name] UpdateUserRequest name
                 * @property {google.protobuf.IStringValue|null} [intro] UpdateUserRequest intro
                 * @property {im.turms.proto.ProfileAccessStrategy|null} [profileAccessStrategy] UpdateUserRequest profileAccessStrategy
                 */

                /**
                 * Constructs a new UpdateUserRequest.
                 * @memberof im.turms.proto
                 * @classdesc Represents an UpdateUserRequest.
                 * @implements IUpdateUserRequest
                 * @constructor
                 * @param {im.turms.proto.IUpdateUserRequest=} [properties] Properties to set
                 */
                function UpdateUserRequest(properties) {
                    if (properties)
                        for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                            if (properties[keys[i]] != null)
                                this[keys[i]] = properties[keys[i]];
                }

                /**
                 * UpdateUserRequest password.
                 * @member {google.protobuf.IStringValue|null|undefined} password
                 * @memberof im.turms.proto.UpdateUserRequest
                 * @instance
                 */
                UpdateUserRequest.prototype.password = null;

                /**
                 * UpdateUserRequest name.
                 * @member {google.protobuf.IStringValue|null|undefined} name
                 * @memberof im.turms.proto.UpdateUserRequest
                 * @instance
                 */
                UpdateUserRequest.prototype.name = null;

                /**
                 * UpdateUserRequest intro.
                 * @member {google.protobuf.IStringValue|null|undefined} intro
                 * @memberof im.turms.proto.UpdateUserRequest
                 * @instance
                 */
                UpdateUserRequest.prototype.intro = null;

                /**
                 * UpdateUserRequest profileAccessStrategy.
                 * @member {im.turms.proto.ProfileAccessStrategy} profileAccessStrategy
                 * @memberof im.turms.proto.UpdateUserRequest
                 * @instance
                 */
                UpdateUserRequest.prototype.profileAccessStrategy = 0;

                /**
                 * Encodes the specified UpdateUserRequest message. Does not implicitly {@link im.turms.proto.UpdateUserRequest.verify|verify} messages.
                 * @function encode
                 * @memberof im.turms.proto.UpdateUserRequest
                 * @static
                 * @param {im.turms.proto.IUpdateUserRequest} message UpdateUserRequest message or plain object to encode
                 * @param {$protobuf.Writer} [writer] Writer to encode to
                 * @returns {$protobuf.Writer} Writer
                 */
                UpdateUserRequest.encode = function encode(message, writer) {
                    if (!writer)
                        writer = $Writer.create();
                    if (message.password != null && Object.hasOwnProperty.call(message, "password"))
                        $root.google.protobuf.StringValue.encode(message.password, writer.uint32(/* id 1, wireType 2 =*/10).fork()).ldelim();
                    if (message.name != null && Object.hasOwnProperty.call(message, "name"))
                        $root.google.protobuf.StringValue.encode(message.name, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
                    if (message.intro != null && Object.hasOwnProperty.call(message, "intro"))
                        $root.google.protobuf.StringValue.encode(message.intro, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
                    if (message.profileAccessStrategy != null && Object.hasOwnProperty.call(message, "profileAccessStrategy"))
                        writer.uint32(/* id 4, wireType 0 =*/32).int32(message.profileAccessStrategy);
                    return writer;
                };

                /**
                 * Decodes an UpdateUserRequest message from the specified reader or buffer.
                 * @function decode
                 * @memberof im.turms.proto.UpdateUserRequest
                 * @static
                 * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
                 * @param {number} [length] Message length if known beforehand
                 * @returns {im.turms.proto.UpdateUserRequest} UpdateUserRequest
                 * @throws {Error} If the payload is not a reader or valid buffer
                 * @throws {$protobuf.util.ProtocolError} If required fields are missing
                 */
                UpdateUserRequest.decode = function decode(reader, length) {
                    if (!(reader instanceof $Reader))
                        reader = $Reader.create(reader);
                    var end = length === undefined ? reader.len : reader.pos + length, message = new $root.im.turms.proto.UpdateUserRequest();
                    while (reader.pos < end) {
                        var tag = reader.uint32();
                        switch (tag >>> 3) {
                        case 1:
                            message.password = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 2:
                            message.name = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 3:
                            message.intro = $root.google.protobuf.StringValue.decode(reader, reader.uint32());
                            break;
                        case 4:
                            message.profileAccessStrategy = reader.int32();
                            break;
                        default:
                            reader.skipType(tag & 7);
                            break;
                        }
                    }
                    return message;
                };

                return UpdateUserRequest;
            })();

            return proto;
        })();

        return turms;
    })();

    return im;
})();

$root.google = (function() {

    /**
     * Namespace google.
     * @exports google
     * @namespace
     */
    var google = {};

    google.protobuf = (function() {

        /**
         * Namespace protobuf.
         * @memberof google
         * @namespace
         */
        var protobuf = {};

        protobuf.DoubleValue = (function() {

            /**
             * Properties of a DoubleValue.
             * @memberof google.protobuf
             * @interface IDoubleValue
             * @property {number|null} [value] DoubleValue value
             */

            /**
             * Constructs a new DoubleValue.
             * @memberof google.protobuf
             * @classdesc Represents a DoubleValue.
             * @implements IDoubleValue
             * @constructor
             * @param {google.protobuf.IDoubleValue=} [properties] Properties to set
             */
            function DoubleValue(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * DoubleValue value.
             * @member {number} value
             * @memberof google.protobuf.DoubleValue
             * @instance
             */
            DoubleValue.prototype.value = 0;

            /**
             * Encodes the specified DoubleValue message. Does not implicitly {@link google.protobuf.DoubleValue.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.DoubleValue
             * @static
             * @param {google.protobuf.IDoubleValue} message DoubleValue message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            DoubleValue.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 1 =*/9).double(message.value);
                return writer;
            };

            /**
             * Decodes a DoubleValue message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.DoubleValue
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.DoubleValue} DoubleValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            DoubleValue.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.DoubleValue();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.double();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return DoubleValue;
        })();

        protobuf.FloatValue = (function() {

            /**
             * Properties of a FloatValue.
             * @memberof google.protobuf
             * @interface IFloatValue
             * @property {number|null} [value] FloatValue value
             */

            /**
             * Constructs a new FloatValue.
             * @memberof google.protobuf
             * @classdesc Represents a FloatValue.
             * @implements IFloatValue
             * @constructor
             * @param {google.protobuf.IFloatValue=} [properties] Properties to set
             */
            function FloatValue(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * FloatValue value.
             * @member {number} value
             * @memberof google.protobuf.FloatValue
             * @instance
             */
            FloatValue.prototype.value = 0;

            /**
             * Encodes the specified FloatValue message. Does not implicitly {@link google.protobuf.FloatValue.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.FloatValue
             * @static
             * @param {google.protobuf.IFloatValue} message FloatValue message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            FloatValue.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 5 =*/13).float(message.value);
                return writer;
            };

            /**
             * Decodes a FloatValue message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.FloatValue
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.FloatValue} FloatValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            FloatValue.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.FloatValue();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.float();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return FloatValue;
        })();

        protobuf.Int64Value = (function() {

            /**
             * Properties of an Int64Value.
             * @memberof google.protobuf
             * @interface IInt64Value
             * @property {string|null} [value] Int64Value value
             */

            /**
             * Constructs a new Int64Value.
             * @memberof google.protobuf
             * @classdesc Represents an Int64Value.
             * @implements IInt64Value
             * @constructor
             * @param {google.protobuf.IInt64Value=} [properties] Properties to set
             */
            function Int64Value(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * Int64Value value.
             * @member {string} value
             * @memberof google.protobuf.Int64Value
             * @instance
             */
            Int64Value.prototype.value = "0";

            /**
             * Encodes the specified Int64Value message. Does not implicitly {@link google.protobuf.Int64Value.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.Int64Value
             * @static
             * @param {google.protobuf.IInt64Value} message Int64Value message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            Int64Value.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 0 =*/8).int64(message.value);
                return writer;
            };

            /**
             * Decodes an Int64Value message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.Int64Value
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.Int64Value} Int64Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            Int64Value.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.Int64Value();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.int64().toString();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return Int64Value;
        })();

        protobuf.UInt64Value = (function() {

            /**
             * Properties of a UInt64Value.
             * @memberof google.protobuf
             * @interface IUInt64Value
             * @property {string|null} [value] UInt64Value value
             */

            /**
             * Constructs a new UInt64Value.
             * @memberof google.protobuf
             * @classdesc Represents a UInt64Value.
             * @implements IUInt64Value
             * @constructor
             * @param {google.protobuf.IUInt64Value=} [properties] Properties to set
             */
            function UInt64Value(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * UInt64Value value.
             * @member {string} value
             * @memberof google.protobuf.UInt64Value
             * @instance
             */
            UInt64Value.prototype.value = "0";

            /**
             * Encodes the specified UInt64Value message. Does not implicitly {@link google.protobuf.UInt64Value.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.UInt64Value
             * @static
             * @param {google.protobuf.IUInt64Value} message UInt64Value message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            UInt64Value.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 0 =*/8).uint64(message.value);
                return writer;
            };

            /**
             * Decodes a UInt64Value message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.UInt64Value
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.UInt64Value} UInt64Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            UInt64Value.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.UInt64Value();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.uint64().toString();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return UInt64Value;
        })();

        protobuf.Int32Value = (function() {

            /**
             * Properties of an Int32Value.
             * @memberof google.protobuf
             * @interface IInt32Value
             * @property {number|null} [value] Int32Value value
             */

            /**
             * Constructs a new Int32Value.
             * @memberof google.protobuf
             * @classdesc Represents an Int32Value.
             * @implements IInt32Value
             * @constructor
             * @param {google.protobuf.IInt32Value=} [properties] Properties to set
             */
            function Int32Value(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * Int32Value value.
             * @member {number} value
             * @memberof google.protobuf.Int32Value
             * @instance
             */
            Int32Value.prototype.value = 0;

            /**
             * Encodes the specified Int32Value message. Does not implicitly {@link google.protobuf.Int32Value.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.Int32Value
             * @static
             * @param {google.protobuf.IInt32Value} message Int32Value message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            Int32Value.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 0 =*/8).int32(message.value);
                return writer;
            };

            /**
             * Decodes an Int32Value message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.Int32Value
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.Int32Value} Int32Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            Int32Value.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.Int32Value();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.int32();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return Int32Value;
        })();

        protobuf.UInt32Value = (function() {

            /**
             * Properties of a UInt32Value.
             * @memberof google.protobuf
             * @interface IUInt32Value
             * @property {number|null} [value] UInt32Value value
             */

            /**
             * Constructs a new UInt32Value.
             * @memberof google.protobuf
             * @classdesc Represents a UInt32Value.
             * @implements IUInt32Value
             * @constructor
             * @param {google.protobuf.IUInt32Value=} [properties] Properties to set
             */
            function UInt32Value(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * UInt32Value value.
             * @member {number} value
             * @memberof google.protobuf.UInt32Value
             * @instance
             */
            UInt32Value.prototype.value = 0;

            /**
             * Encodes the specified UInt32Value message. Does not implicitly {@link google.protobuf.UInt32Value.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.UInt32Value
             * @static
             * @param {google.protobuf.IUInt32Value} message UInt32Value message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            UInt32Value.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 0 =*/8).uint32(message.value);
                return writer;
            };

            /**
             * Decodes a UInt32Value message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.UInt32Value
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.UInt32Value} UInt32Value
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            UInt32Value.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.UInt32Value();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.uint32();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return UInt32Value;
        })();

        protobuf.BoolValue = (function() {

            /**
             * Properties of a BoolValue.
             * @memberof google.protobuf
             * @interface IBoolValue
             * @property {boolean|null} [value] BoolValue value
             */

            /**
             * Constructs a new BoolValue.
             * @memberof google.protobuf
             * @classdesc Represents a BoolValue.
             * @implements IBoolValue
             * @constructor
             * @param {google.protobuf.IBoolValue=} [properties] Properties to set
             */
            function BoolValue(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * BoolValue value.
             * @member {boolean} value
             * @memberof google.protobuf.BoolValue
             * @instance
             */
            BoolValue.prototype.value = false;

            /**
             * Encodes the specified BoolValue message. Does not implicitly {@link google.protobuf.BoolValue.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.BoolValue
             * @static
             * @param {google.protobuf.IBoolValue} message BoolValue message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            BoolValue.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 0 =*/8).bool(message.value);
                return writer;
            };

            /**
             * Decodes a BoolValue message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.BoolValue
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.BoolValue} BoolValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            BoolValue.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.BoolValue();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.bool();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return BoolValue;
        })();

        protobuf.StringValue = (function() {

            /**
             * Properties of a StringValue.
             * @memberof google.protobuf
             * @interface IStringValue
             * @property {string|null} [value] StringValue value
             */

            /**
             * Constructs a new StringValue.
             * @memberof google.protobuf
             * @classdesc Represents a StringValue.
             * @implements IStringValue
             * @constructor
             * @param {google.protobuf.IStringValue=} [properties] Properties to set
             */
            function StringValue(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * StringValue value.
             * @member {string} value
             * @memberof google.protobuf.StringValue
             * @instance
             */
            StringValue.prototype.value = "";

            /**
             * Encodes the specified StringValue message. Does not implicitly {@link google.protobuf.StringValue.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.StringValue
             * @static
             * @param {google.protobuf.IStringValue} message StringValue message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            StringValue.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 2 =*/10).string(message.value);
                return writer;
            };

            /**
             * Decodes a StringValue message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.StringValue
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.StringValue} StringValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            StringValue.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.StringValue();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.string();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return StringValue;
        })();

        protobuf.BytesValue = (function() {

            /**
             * Properties of a BytesValue.
             * @memberof google.protobuf
             * @interface IBytesValue
             * @property {Uint8Array|null} [value] BytesValue value
             */

            /**
             * Constructs a new BytesValue.
             * @memberof google.protobuf
             * @classdesc Represents a BytesValue.
             * @implements IBytesValue
             * @constructor
             * @param {google.protobuf.IBytesValue=} [properties] Properties to set
             */
            function BytesValue(properties) {
                if (properties)
                    for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                        if (properties[keys[i]] != null)
                            this[keys[i]] = properties[keys[i]];
            }

            /**
             * BytesValue value.
             * @member {Uint8Array} value
             * @memberof google.protobuf.BytesValue
             * @instance
             */
            BytesValue.prototype.value = $util.newBuffer([]);

            /**
             * Encodes the specified BytesValue message. Does not implicitly {@link google.protobuf.BytesValue.verify|verify} messages.
             * @function encode
             * @memberof google.protobuf.BytesValue
             * @static
             * @param {google.protobuf.IBytesValue} message BytesValue message or plain object to encode
             * @param {$protobuf.Writer} [writer] Writer to encode to
             * @returns {$protobuf.Writer} Writer
             */
            BytesValue.encode = function encode(message, writer) {
                if (!writer)
                    writer = $Writer.create();
                if (message.value != null && Object.hasOwnProperty.call(message, "value"))
                    writer.uint32(/* id 1, wireType 2 =*/10).bytes(message.value);
                return writer;
            };

            /**
             * Decodes a BytesValue message from the specified reader or buffer.
             * @function decode
             * @memberof google.protobuf.BytesValue
             * @static
             * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
             * @param {number} [length] Message length if known beforehand
             * @returns {google.protobuf.BytesValue} BytesValue
             * @throws {Error} If the payload is not a reader or valid buffer
             * @throws {$protobuf.util.ProtocolError} If required fields are missing
             */
            BytesValue.decode = function decode(reader, length) {
                if (!(reader instanceof $Reader))
                    reader = $Reader.create(reader);
                var end = length === undefined ? reader.len : reader.pos + length, message = new $root.google.protobuf.BytesValue();
                while (reader.pos < end) {
                    var tag = reader.uint32();
                    switch (tag >>> 3) {
                    case 1:
                        message.value = reader.bytes();
                        break;
                    default:
                        reader.skipType(tag & 7);
                        break;
                    }
                }
                return message;
            };

            return BytesValue;
        })();

        return protobuf;
    })();

    return google;
})();

module.exports = $root;
