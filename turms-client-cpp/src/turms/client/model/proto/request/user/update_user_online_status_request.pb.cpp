// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/user/update_user_online_status_request.proto

#include "turms/client/model/proto/request/user/update_user_online_status_request.pb.h"

#include <algorithm>
#include "google/protobuf/io/coded_stream.h"
#include "google/protobuf/extension_set.h"
#include "google/protobuf/wire_format_lite.h"
#include "google/protobuf/io/zero_copy_stream_impl_lite.h"
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
        template <typename>
PROTOBUF_CONSTEXPR UpdateUserOnlineStatusRequest::UpdateUserOnlineStatusRequest(::_pbi::ConstantInitialized)
    : _impl_{
      /*decltype(_impl_.device_types_)*/ {},
      /*decltype(_impl_._device_types_cached_byte_size_)*/ {0},
      /*decltype(_impl_.user_status_)*/ 0,
      /*decltype(_impl_._cached_size_)*/ {},
    } {}
struct UpdateUserOnlineStatusRequestDefaultTypeInternal {
  PROTOBUF_CONSTEXPR UpdateUserOnlineStatusRequestDefaultTypeInternal() : _instance(::_pbi::ConstantInitialized{}) {}
  ~UpdateUserOnlineStatusRequestDefaultTypeInternal() {}
  union {
    UpdateUserOnlineStatusRequest _instance;
  };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT
    PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 UpdateUserOnlineStatusRequestDefaultTypeInternal _UpdateUserOnlineStatusRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UpdateUserOnlineStatusRequest::_Internal {
 public:
};

UpdateUserOnlineStatusRequest::UpdateUserOnlineStatusRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
  SharedCtor(arena);
  // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UpdateUserOnlineStatusRequest)
}
UpdateUserOnlineStatusRequest::UpdateUserOnlineStatusRequest(const UpdateUserOnlineStatusRequest& from) : ::google::protobuf::MessageLite() {
  UpdateUserOnlineStatusRequest* const _this = this;
  (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_.device_types_){from._internal_device_types()},
      /*decltype(_impl_._device_types_cached_byte_size_)*/ {0},
      decltype(_impl_.user_status_){},
      /*decltype(_impl_._cached_size_)*/ {},
  };
  _internal_metadata_.MergeFrom<std::string>(
      from._internal_metadata_);
  _this->_impl_.user_status_ = from._impl_.user_status_;

  // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UpdateUserOnlineStatusRequest)
}
inline void UpdateUserOnlineStatusRequest::SharedCtor(::_pb::Arena* arena) {
  (void)arena;
  new (&_impl_) Impl_{
      decltype(_impl_.device_types_){arena},
      /*decltype(_impl_._device_types_cached_byte_size_)*/ {0},
      decltype(_impl_.user_status_){0},
      /*decltype(_impl_._cached_size_)*/ {},
  };
}
UpdateUserOnlineStatusRequest::~UpdateUserOnlineStatusRequest() {
  // @@protoc_insertion_point(destructor:turms.client.model.proto.UpdateUserOnlineStatusRequest)
  _internal_metadata_.Delete<std::string>();
  SharedDtor();
}
inline void UpdateUserOnlineStatusRequest::SharedDtor() {
  ABSL_DCHECK(GetArenaForAllocation() == nullptr);
  _internal_mutable_device_types()->~RepeatedField();
}
void UpdateUserOnlineStatusRequest::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

PROTOBUF_NOINLINE void UpdateUserOnlineStatusRequest::Clear() {
// @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UpdateUserOnlineStatusRequest)
  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _internal_mutable_device_types()->Clear();
  _impl_.user_status_ = 0;
  _internal_metadata_.Clear<std::string>();
}

const char* UpdateUserOnlineStatusRequest::_InternalParse(
    const char* ptr, ::_pbi::ParseContext* ctx) {
  ptr = ::_pbi::TcParser::ParseLoop(this, ptr, ctx, &_table_.header);
  return ptr;
}


PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
const ::_pbi::TcParseTable<1, 2, 0, 0, 2> UpdateUserOnlineStatusRequest::_table_ = {
  {
    0,  // no _has_bits_
    0, // no _extensions_
    2, 8,  // max_field_number, fast_idx_mask
    offsetof(decltype(_table_), field_lookup_table),
    4294967292,  // skipmap
    offsetof(decltype(_table_), field_entries),
    2,  // num_field_entries
    0,  // num_aux_entries
    offsetof(decltype(_table_), field_names),  // no aux_entries
    &_UpdateUserOnlineStatusRequest_default_instance_._instance,
    ::_pbi::TcParser::GenericFallbackLite,  // fallback
  }, {{
    // .turms.client.model.proto.UserStatus user_status = 2;
    {::_pbi::TcParser::FastV32S1,
     {16, 63, 0, PROTOBUF_FIELD_OFFSET(UpdateUserOnlineStatusRequest, _impl_.user_status_)}},
    // repeated .turms.client.model.proto.DeviceType device_types = 1;
    {::_pbi::TcParser::FastV32P1,
     {10, 63, 0, PROTOBUF_FIELD_OFFSET(UpdateUserOnlineStatusRequest, _impl_.device_types_)}},
  }}, {{
    65535, 65535
  }}, {{
    // repeated .turms.client.model.proto.DeviceType device_types = 1;
    {PROTOBUF_FIELD_OFFSET(UpdateUserOnlineStatusRequest, _impl_.device_types_), 0, 0,
    (0 | ::_fl::kFcRepeated | ::_fl::kPackedOpenEnum)},
    // .turms.client.model.proto.UserStatus user_status = 2;
    {PROTOBUF_FIELD_OFFSET(UpdateUserOnlineStatusRequest, _impl_.user_status_), 0, 0,
    (0 | ::_fl::kFcSingular | ::_fl::kOpenEnum)},
  }},
  // no aux_entries
  {{
  }},
};

::uint8_t* UpdateUserOnlineStatusRequest::_InternalSerialize(
    ::uint8_t* target,
    ::google::protobuf::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UpdateUserOnlineStatusRequest)
  ::uint32_t cached_has_bits = 0;
  (void)cached_has_bits;

  // repeated .turms.client.model.proto.DeviceType device_types = 1;
  {
    int byte_size = _impl_._device_types_cached_byte_size_.Get();
    if (byte_size > 0) {
      target = stream->WriteEnumPacked(1, _internal_device_types(),
                                       byte_size, target);
    }
  }

  // .turms.client.model.proto.UserStatus user_status = 2;
  if (this->_internal_user_status() != 0) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteEnumToArray(
        2, this->_internal_user_status(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = stream->WriteRaw(
        _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).data(),
        static_cast<int>(_internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size()), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UpdateUserOnlineStatusRequest)
  return target;
}

::size_t UpdateUserOnlineStatusRequest::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UpdateUserOnlineStatusRequest)
  ::size_t total_size = 0;

  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated .turms.client.model.proto.DeviceType device_types = 1;
  {
    std::size_t data_size = 0;
    auto count = static_cast<std::size_t>(this->_internal_device_types_size());

    for (std::size_t i = 0; i < count; ++i) {
      data_size += ::_pbi::WireFormatLite::EnumSize(
          this->_internal_device_types().Get(static_cast<int>(i)));
    }
    total_size += data_size;
    if (data_size > 0) {
      total_size += 1;
      total_size += ::_pbi::WireFormatLite::Int32Size(
          static_cast<int32_t>(data_size));
    }
    _impl_._device_types_cached_byte_size_.Set(::_pbi::ToCachedSize(data_size));
  }
  // .turms.client.model.proto.UserStatus user_status = 2;
  if (this->_internal_user_status() != 0) {
    total_size += 1 +
                  ::_pbi::WireFormatLite::EnumSize(this->_internal_user_status());
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    total_size += _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size();
  }
  int cached_size = ::_pbi::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void UpdateUserOnlineStatusRequest::CheckTypeAndMergeFrom(
    const ::google::protobuf::MessageLite& from) {
  MergeFrom(*::_pbi::DownCast<const UpdateUserOnlineStatusRequest*>(
      &from));
}

void UpdateUserOnlineStatusRequest::MergeFrom(const UpdateUserOnlineStatusRequest& from) {
  UpdateUserOnlineStatusRequest* const _this = this;
  // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UpdateUserOnlineStatusRequest)
  ABSL_DCHECK_NE(&from, _this);
  ::uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  _this->_internal_mutable_device_types()->MergeFrom(from._internal_device_types());
  if (from._internal_user_status() != 0) {
    _this->_internal_set_user_status(from._internal_user_status());
  }
  _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UpdateUserOnlineStatusRequest::CopyFrom(const UpdateUserOnlineStatusRequest& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UpdateUserOnlineStatusRequest)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

PROTOBUF_NOINLINE bool UpdateUserOnlineStatusRequest::IsInitialized() const {
  return true;
}

void UpdateUserOnlineStatusRequest::InternalSwap(UpdateUserOnlineStatusRequest* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  _impl_.device_types_.InternalSwap(&other->_impl_.device_types_);
  swap(_impl_.user_status_, other->_impl_.user_status_);
}

std::string UpdateUserOnlineStatusRequest::GetTypeName() const {
  return "turms.client.model.proto.UpdateUserOnlineStatusRequest";
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
