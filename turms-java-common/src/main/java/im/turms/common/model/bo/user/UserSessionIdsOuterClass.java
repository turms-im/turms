// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/user/user_session_ids.proto

package im.turms.common.model.bo.user;

public final class UserSessionIdsOuterClass {
  private UserSessionIdsOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_UserSessionIds_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_UserSessionIds_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!model/user/user_session_ids.proto\022\016im." +
      "turms.proto\032 model/user/user_session_id." +
      "proto\"I\n\016UserSessionIds\0227\n\020user_session_" +
      "ids\030\001 \003(\0132\035.im.turms.proto.UserSessionId" +
      "B$\n\035im.turms.common.model.bo.userP\001\272\002\000b\006" +
      "proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          im.turms.common.model.bo.user.UserSessionIdOuterClass.getDescriptor(),
        });
    internal_static_im_turms_proto_UserSessionIds_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_im_turms_proto_UserSessionIds_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_UserSessionIds_descriptor,
        new java.lang.String[] { "UserSessionIds", });
    im.turms.common.model.bo.user.UserSessionIdOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
