// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/user/update_user_request.proto

package im.turms.common.model.dto.request.user;

public final class UpdateUserRequestOuterClass {
  private UpdateUserRequestOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_im_turms_proto_UpdateUserRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_im_turms_proto_UpdateUserRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n&request/user/update_user_request.proto" +
      "\022\016im.turms.proto\032\036google/protobuf/wrappe" +
      "rs.proto\032&constant/profile_access_strate" +
      "gy.proto\"\344\001\n\021UpdateUserRequest\022.\n\010passwo" +
      "rd\030\001 \001(\0132\034.google.protobuf.StringValue\022*" +
      "\n\004name\030\002 \001(\0132\034.google.protobuf.StringVal" +
      "ue\022+\n\005intro\030\003 \001(\0132\034.google.protobuf.Stri" +
      "ngValue\022F\n\027profile_access_strategy\030\004 \001(\016" +
      "2%.im.turms.proto.ProfileAccessStrategyB" +
      "-\n&im.turms.common.model.dto.request.use" +
      "rP\001\272\002\000b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
          im.turms.common.constant.ProfileAccessStrategyOuterClass.getDescriptor(),
        });
    internal_static_im_turms_proto_UpdateUserRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_im_turms_proto_UpdateUserRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_im_turms_proto_UpdateUserRequest_descriptor,
        new java.lang.String[] { "Password", "Name", "Intro", "ProfileAccessStrategy", });
    com.google.protobuf.WrappersProto.getDescriptor();
    im.turms.common.constant.ProfileAccessStrategyOuterClass.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
