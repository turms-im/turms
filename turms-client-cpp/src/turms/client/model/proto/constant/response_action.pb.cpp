// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: constant/response_action.proto

#include "turms/client/model/proto/constant/response_action.pb.h"

#include <algorithm>
#include "google/protobuf/io/coded_stream.h"
#include "google/protobuf/extension_set.h"
#include "google/protobuf/wire_format_lite.h"
#include "google/protobuf/generated_message_tctable_impl.h"
// @@protoc_insertion_point(includes)

// Must be included last.
#include "google/protobuf/port_def.inc"
PROTOBUF_PRAGMA_INIT_SEG
namespace _pb = ::google::protobuf;
namespace _pbi = ::google::protobuf::internal;
namespace _fl = ::google::protobuf::internal::field_layout;
namespace turms {
namespace client {
namespace model {
namespace proto {
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
bool ResponseAction_IsValid(int value) {
  switch (value) {
    case 0:
    case 1:
    case 2:
      return true;
    default:
      return false;
  }
}
static ::google::protobuf::internal::ExplicitlyConstructed<std::string>
    ResponseAction_strings[3] = {};

static const char ResponseAction_names[] = {
    "ACCEPT"
    "DECLINE"
    "IGNORE"
};

static const ::google::protobuf::internal::EnumEntry ResponseAction_entries[] =
    {
        {{&ResponseAction_names[0], 6}, 0},
        {{&ResponseAction_names[6], 7}, 1},
        {{&ResponseAction_names[13], 6}, 2},
};

static const int ResponseAction_entries_by_number[] = {
    0,  // 0 -> ACCEPT
    1,  // 1 -> DECLINE
    2,  // 2 -> IGNORE
};

const std::string& ResponseAction_Name(ResponseAction value) {
  static const bool kDummy =
      ::google::protobuf::internal::InitializeEnumStrings(
          ResponseAction_entries, ResponseAction_entries_by_number,
          3, ResponseAction_strings);
  (void)kDummy;

  int idx = ::google::protobuf::internal::LookUpEnumName(
      ResponseAction_entries, ResponseAction_entries_by_number, 3,
      value);
  return idx == -1 ? ::google::protobuf::internal::GetEmptyString()
                   : ResponseAction_strings[idx].get();
}

bool ResponseAction_Parse(absl::string_view name, ResponseAction* value) {
  int int_value;
  bool success = ::google::protobuf::internal::LookUpEnumValue(
      ResponseAction_entries, 3, name, &int_value);
  if (success) {
    *value = static_cast<ResponseAction>(int_value);
  }
  return success;
}
// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace google {
namespace protobuf {
}  // namespace protobuf
}  // namespace google
// @@protoc_insertion_point(global_scope)
#include "google/protobuf/port_undef.inc"
