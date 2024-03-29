// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/group/member/update_group_member_request.proto

#include "turms/client/model/proto/request/group/member/update_group_member_request.pb.h"

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
PROTOBUF_CONSTEXPR UpdateGroupMemberRequest::UpdateGroupMemberRequest(::_pbi::ConstantInitialized)
    : _impl_{
      /*decltype(_impl_._has_bits_)*/ {},
      /*decltype(_impl_._cached_size_)*/ {},
      /*decltype(_impl_.name_)*/ {
          &::_pbi::fixed_address_empty_string,
          ::_pbi::ConstantInitialized{},
      },
      /*decltype(_impl_.group_id_)*/ ::int64_t{0},
      /*decltype(_impl_.member_id_)*/ ::int64_t{0},
      /*decltype(_impl_.mute_end_date_)*/ ::int64_t{0},
      /*decltype(_impl_.role_)*/ 0,
    } {}
struct UpdateGroupMemberRequestDefaultTypeInternal {
  PROTOBUF_CONSTEXPR UpdateGroupMemberRequestDefaultTypeInternal() : _instance(::_pbi::ConstantInitialized{}) {}
  ~UpdateGroupMemberRequestDefaultTypeInternal() {}
  union {
    UpdateGroupMemberRequest _instance;
  };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT
    PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 UpdateGroupMemberRequestDefaultTypeInternal _UpdateGroupMemberRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UpdateGroupMemberRequest::_Internal {
 public:
  using HasBits = decltype(std::declval<UpdateGroupMemberRequest>()._impl_._has_bits_);
  static constexpr ::int32_t kHasBitsOffset =
    8 * PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_._has_bits_);
  static void set_has_name(HasBits* has_bits) {
    (*has_bits)[0] |= 1u;
  }
  static void set_has_role(HasBits* has_bits) {
    (*has_bits)[0] |= 4u;
  }
  static void set_has_mute_end_date(HasBits* has_bits) {
    (*has_bits)[0] |= 2u;
  }
};

UpdateGroupMemberRequest::UpdateGroupMemberRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
  SharedCtor(arena);
  // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UpdateGroupMemberRequest)
}
UpdateGroupMemberRequest::UpdateGroupMemberRequest(const UpdateGroupMemberRequest& from) : ::google::protobuf::MessageLite() {
  UpdateGroupMemberRequest* const _this = this;
  (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_._has_bits_){from._impl_._has_bits_},
      /*decltype(_impl_._cached_size_)*/ {},
      decltype(_impl_.name_){},
      decltype(_impl_.group_id_){},
      decltype(_impl_.member_id_){},
      decltype(_impl_.mute_end_date_){},
      decltype(_impl_.role_){},
  };
  _internal_metadata_.MergeFrom<std::string>(
      from._internal_metadata_);
  _impl_.name_.InitDefault();
  #ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
        _impl_.name_.Set("", GetArenaForAllocation());
  #endif  // PROTOBUF_FORCE_COPY_DEFAULT_STRING
  if ((from._impl_._has_bits_[0] & 0x00000001u) != 0) {
    _this->_impl_.name_.Set(from._internal_name(), _this->GetArenaForAllocation());
  }
  ::memcpy(&_impl_.group_id_, &from._impl_.group_id_,
    static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.role_) -
    reinterpret_cast<char*>(&_impl_.group_id_)) + sizeof(_impl_.role_));

  // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UpdateGroupMemberRequest)
}
inline void UpdateGroupMemberRequest::SharedCtor(::_pb::Arena* arena) {
  (void)arena;
  new (&_impl_) Impl_{
      decltype(_impl_._has_bits_){},
      /*decltype(_impl_._cached_size_)*/ {},
      decltype(_impl_.name_){},
      decltype(_impl_.group_id_){::int64_t{0}},
      decltype(_impl_.member_id_){::int64_t{0}},
      decltype(_impl_.mute_end_date_){::int64_t{0}},
      decltype(_impl_.role_){0},
  };
  _impl_.name_.InitDefault();
  #ifdef PROTOBUF_FORCE_COPY_DEFAULT_STRING
        _impl_.name_.Set("", GetArenaForAllocation());
  #endif  // PROTOBUF_FORCE_COPY_DEFAULT_STRING
}
UpdateGroupMemberRequest::~UpdateGroupMemberRequest() {
  // @@protoc_insertion_point(destructor:turms.client.model.proto.UpdateGroupMemberRequest)
  _internal_metadata_.Delete<std::string>();
  SharedDtor();
}
inline void UpdateGroupMemberRequest::SharedDtor() {
  ABSL_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.name_.Destroy();
}
void UpdateGroupMemberRequest::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

PROTOBUF_NOINLINE void UpdateGroupMemberRequest::Clear() {
// @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UpdateGroupMemberRequest)
  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  cached_has_bits = _impl_._has_bits_[0];
  if (cached_has_bits & 0x00000001u) {
    _impl_.name_.ClearNonDefaultToEmpty();
  }
  ::memset(&_impl_.group_id_, 0, static_cast<::size_t>(
      reinterpret_cast<char*>(&_impl_.member_id_) -
      reinterpret_cast<char*>(&_impl_.group_id_)) + sizeof(_impl_.member_id_));
  if (cached_has_bits & 0x00000006u) {
    ::memset(&_impl_.mute_end_date_, 0, static_cast<::size_t>(
        reinterpret_cast<char*>(&_impl_.role_) -
        reinterpret_cast<char*>(&_impl_.mute_end_date_)) + sizeof(_impl_.role_));
  }
  _impl_._has_bits_.Clear();
  _internal_metadata_.Clear<std::string>();
}

const char* UpdateGroupMemberRequest::_InternalParse(
    const char* ptr, ::_pbi::ParseContext* ctx) {
  ptr = ::_pbi::TcParser::ParseLoop(this, ptr, ctx, &_table_.header);
  return ptr;
}


PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
const ::_pbi::TcParseTable<3, 5, 0, 62, 2> UpdateGroupMemberRequest::_table_ = {
  {
    PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_._has_bits_),
    0, // no _extensions_
    5, 56,  // max_field_number, fast_idx_mask
    offsetof(decltype(_table_), field_lookup_table),
    4294967264,  // skipmap
    offsetof(decltype(_table_), field_entries),
    5,  // num_field_entries
    0,  // num_aux_entries
    offsetof(decltype(_table_), field_names),  // no aux_entries
    &_UpdateGroupMemberRequest_default_instance_._instance,
    ::_pbi::TcParser::GenericFallbackLite,  // fallback
  }, {{
    {::_pbi::TcParser::MiniParse, {}},
    // int64 group_id = 1;
    {::_pbi::TcParser::FastV64S1,
     {8, 63, 0, PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.group_id_)}},
    // int64 member_id = 2;
    {::_pbi::TcParser::FastV64S1,
     {16, 63, 0, PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.member_id_)}},
    // optional string name = 3;
    {::_pbi::TcParser::FastUS1,
     {26, 0, 0, PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.name_)}},
    // optional .turms.client.model.proto.GroupMemberRole role = 4;
    {::_pbi::TcParser::FastV32S1,
     {32, 2, 0, PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.role_)}},
    // optional int64 mute_end_date = 5;
    {::_pbi::TcParser::FastV64S1,
     {40, 1, 0, PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.mute_end_date_)}},
    {::_pbi::TcParser::MiniParse, {}},
    {::_pbi::TcParser::MiniParse, {}},
  }}, {{
    65535, 65535
  }}, {{
    // int64 group_id = 1;
    {PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.group_id_), -1, 0,
    (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
    // int64 member_id = 2;
    {PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.member_id_), -1, 0,
    (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
    // optional string name = 3;
    {PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.name_), _Internal::kHasBitsOffset + 0, 0,
    (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
    // optional .turms.client.model.proto.GroupMemberRole role = 4;
    {PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.role_), _Internal::kHasBitsOffset + 2, 0,
    (0 | ::_fl::kFcOptional | ::_fl::kOpenEnum)},
    // optional int64 mute_end_date = 5;
    {PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.mute_end_date_), _Internal::kHasBitsOffset + 1, 0,
    (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
  }},
  // no aux_entries
  {{
    "\61\0\0\4\0\0\0\0"
    "turms.client.model.proto.UpdateGroupMemberRequest"
    "name"
  }},
};

::uint8_t* UpdateGroupMemberRequest::_InternalSerialize(
    ::uint8_t* target,
    ::google::protobuf::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UpdateGroupMemberRequest)
  ::uint32_t cached_has_bits = 0;
  (void)cached_has_bits;

  // int64 group_id = 1;
  if (this->_internal_group_id() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::
        WriteInt64ToArrayWithField<1>(
            stream, this->_internal_group_id(), target);
  }

  // int64 member_id = 2;
  if (this->_internal_member_id() != 0) {
    target = ::google::protobuf::internal::WireFormatLite::
        WriteInt64ToArrayWithField<2>(
            stream, this->_internal_member_id(), target);
  }

  cached_has_bits = _impl_._has_bits_[0];
  // optional string name = 3;
  if (cached_has_bits & 0x00000001u) {
    const std::string& _s = this->_internal_name();
    ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
        _s.data(), static_cast<int>(_s.length()), ::google::protobuf::internal::WireFormatLite::SERIALIZE, "turms.client.model.proto.UpdateGroupMemberRequest.name");
    target = stream->WriteStringMaybeAliased(3, _s, target);
  }

  // optional .turms.client.model.proto.GroupMemberRole role = 4;
  if (cached_has_bits & 0x00000004u) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteEnumToArray(
        4, this->_internal_role(), target);
  }

  // optional int64 mute_end_date = 5;
  if (cached_has_bits & 0x00000002u) {
    target = ::google::protobuf::internal::WireFormatLite::
        WriteInt64ToArrayWithField<5>(
            stream, this->_internal_mute_end_date(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = stream->WriteRaw(
        _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).data(),
        static_cast<int>(_internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size()), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UpdateGroupMemberRequest)
  return target;
}

::size_t UpdateGroupMemberRequest::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UpdateGroupMemberRequest)
  ::size_t total_size = 0;

  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // optional string name = 3;
  cached_has_bits = _impl_._has_bits_[0];
  if (cached_has_bits & 0x00000001u) {
    total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                    this->_internal_name());
  }

  // int64 group_id = 1;
  if (this->_internal_group_id() != 0) {
    total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(
        this->_internal_group_id());
  }

  // int64 member_id = 2;
  if (this->_internal_member_id() != 0) {
    total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(
        this->_internal_member_id());
  }

  if (cached_has_bits & 0x00000006u) {
    // optional int64 mute_end_date = 5;
    if (cached_has_bits & 0x00000002u) {
      total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(
          this->_internal_mute_end_date());
    }

    // optional .turms.client.model.proto.GroupMemberRole role = 4;
    if (cached_has_bits & 0x00000004u) {
      total_size += 1 +
                    ::_pbi::WireFormatLite::EnumSize(this->_internal_role());
    }

  }
  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    total_size += _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size();
  }
  int cached_size = ::_pbi::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void UpdateGroupMemberRequest::CheckTypeAndMergeFrom(
    const ::google::protobuf::MessageLite& from) {
  MergeFrom(*::_pbi::DownCast<const UpdateGroupMemberRequest*>(
      &from));
}

void UpdateGroupMemberRequest::MergeFrom(const UpdateGroupMemberRequest& from) {
  UpdateGroupMemberRequest* const _this = this;
  // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UpdateGroupMemberRequest)
  ABSL_DCHECK_NE(&from, _this);
  ::uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  if ((from._impl_._has_bits_[0] & 0x00000001u) != 0) {
    _this->_internal_set_name(from._internal_name());
  }
  if (from._internal_group_id() != 0) {
    _this->_internal_set_group_id(from._internal_group_id());
  }
  if (from._internal_member_id() != 0) {
    _this->_internal_set_member_id(from._internal_member_id());
  }
  cached_has_bits = from._impl_._has_bits_[0];
  if (cached_has_bits & 0x00000006u) {
    if (cached_has_bits & 0x00000002u) {
      _this->_impl_.mute_end_date_ = from._impl_.mute_end_date_;
    }
    if (cached_has_bits & 0x00000004u) {
      _this->_impl_.role_ = from._impl_.role_;
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
  }
  _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UpdateGroupMemberRequest::CopyFrom(const UpdateGroupMemberRequest& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UpdateGroupMemberRequest)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

PROTOBUF_NOINLINE bool UpdateGroupMemberRequest::IsInitialized() const {
  return true;
}

void UpdateGroupMemberRequest::InternalSwap(UpdateGroupMemberRequest* other) {
  using std::swap;
  auto* lhs_arena = GetArenaForAllocation();
  auto* rhs_arena = other->GetArenaForAllocation();
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
  ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.name_, lhs_arena,
                                       &other->_impl_.name_, rhs_arena);
  ::google::protobuf::internal::memswap<
      PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.role_)
      + sizeof(UpdateGroupMemberRequest::_impl_.role_)
      - PROTOBUF_FIELD_OFFSET(UpdateGroupMemberRequest, _impl_.group_id_)>(
          reinterpret_cast<char*>(&_impl_.group_id_),
          reinterpret_cast<char*>(&other->_impl_.group_id_));
}

std::string UpdateGroupMemberRequest::GetTypeName() const {
  return "turms.client.model.proto.UpdateGroupMemberRequest";
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
