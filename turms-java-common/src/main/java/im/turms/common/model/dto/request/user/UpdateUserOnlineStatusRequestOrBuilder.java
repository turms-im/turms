// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/user/update_user_online_status_request.proto

package im.turms.common.model.dto.request.user;

public interface UpdateUserOnlineStatusRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:im.turms.proto.UpdateUserOnlineStatusRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.im.turms.proto.UserStatus user_status = 1;</code>
   * @return The enum numeric value on the wire for userStatus.
   */
  int getUserStatusValue();
  /**
   * <code>.im.turms.proto.UserStatus user_status = 1;</code>
   * @return The userStatus.
   */
  im.turms.common.constant.UserStatus getUserStatus();

  /**
   * <code>repeated .im.turms.proto.DeviceType device_types = 2;</code>
   * @return A list containing the deviceTypes.
   */
  java.util.List<im.turms.common.constant.DeviceType> getDeviceTypesList();
  /**
   * <code>repeated .im.turms.proto.DeviceType device_types = 2;</code>
   * @return The count of deviceTypes.
   */
  int getDeviceTypesCount();
  /**
   * <code>repeated .im.turms.proto.DeviceType device_types = 2;</code>
   * @param index The index of the element to return.
   * @return The deviceTypes at the given index.
   */
  im.turms.common.constant.DeviceType getDeviceTypes(int index);
  /**
   * <code>repeated .im.turms.proto.DeviceType device_types = 2;</code>
   * @return A list containing the enum numeric values on the wire for deviceTypes.
   */
  java.util.List<java.lang.Integer>
  getDeviceTypesValueList();
  /**
   * <code>repeated .im.turms.proto.DeviceType device_types = 2;</code>
   * @param index The index of the value to return.
   * @return The enum numeric value on the wire of deviceTypes at the given index.
   */
  int getDeviceTypesValue(int index);
}
