// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: request/user/relationship/query_related_user_ids_request.proto

#include "turms/client/model/proto/request/user/relationship/query_related_user_ids_request.pb.h"

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
PROTOBUF_CONSTEXPR QueryRelatedUserIdsRequest::QueryRelatedUserIdsRequest(::_pbi::ConstantInitialized)
    : _impl_{
      /*decltype(_impl_._has_bits_)*/ {},
      /*decltype(_impl_._cached_size_)*/ {},
      /*decltype(_impl_.group_indexes_)*/ {},
      /* _impl_._group_indexes_cached_byte_size_ = */ {0},
      /*decltype(_impl_.last_updated_date_)*/ ::int64_t{0},
      /*decltype(_impl_.blocked_)*/ false,
    } {}
struct QueryRelatedUserIdsRequestDefaultTypeInternal {
  PROTOBUF_CONSTEXPR QueryRelatedUserIdsRequestDefaultTypeInternal() : _instance(::_pbi::ConstantInitialized{}) {}
  ~QueryRelatedUserIdsRequestDefaultTypeInternal() {}
  union {
    QueryRelatedUserIdsRequest _instance;
  };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT
    PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 QueryRelatedUserIdsRequestDefaultTypeInternal _QueryRelatedUserIdsRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class QueryRelatedUserIdsRequest::_Internal {
 public:
  using HasBits = decltype(std::declval<QueryRelatedUserIdsRequest>()._impl_._has_bits_);
  static constexpr ::int32_t kHasBitsOffset =
    8 * PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_._has_bits_);
  static void set_has_blocked(HasBits* has_bits) {
    (*has_bits)[0] |= 2u;
  }
  static void set_has_last_updated_date(HasBits* has_bits) {
    (*has_bits)[0] |= 1u;
  }
};

QueryRelatedUserIdsRequest::QueryRelatedUserIdsRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
  SharedCtor(arena);
  // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.QueryRelatedUserIdsRequest)
}
QueryRelatedUserIdsRequest::QueryRelatedUserIdsRequest(const QueryRelatedUserIdsRequest& from) : ::google::protobuf::MessageLite() {
  QueryRelatedUserIdsRequest* const _this = this;
  (void)_this;
  new (&_impl_) Impl_{
      decltype(_impl_._has_bits_){from._impl_._has_bits_},
      /*decltype(_impl_._cached_size_)*/ {},
      decltype(_impl_.group_indexes_){from._impl_.group_indexes_},
      /* _impl_._group_indexes_cached_byte_size_ = */ {0},
      decltype(_impl_.last_updated_date_){},
      decltype(_impl_.blocked_){},
  };
  _internal_metadata_.MergeFrom<std::string>(
      from._internal_metadata_);
  ::memcpy(&_impl_.last_updated_date_, &from._impl_.last_updated_date_,
    static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.blocked_) -
    reinterpret_cast<char*>(&_impl_.last_updated_date_)) + sizeof(_impl_.blocked_));

  // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.QueryRelatedUserIdsRequest)
}
inline void QueryRelatedUserIdsRequest::SharedCtor(::_pb::Arena* arena) {
  (void)arena;
  new (&_impl_) Impl_{
      decltype(_impl_._has_bits_){},
      /*decltype(_impl_._cached_size_)*/ {},
      decltype(_impl_.group_indexes_){arena},
      /* _impl_._group_indexes_cached_byte_size_ = */ {0},
      decltype(_impl_.last_updated_date_){::int64_t{0}},
      decltype(_impl_.blocked_){false},
  };
}
QueryRelatedUserIdsRequest::~QueryRelatedUserIdsRequest() {
  // @@protoc_insertion_point(destructor:turms.client.model.proto.QueryRelatedUserIdsRequest)
  _internal_metadata_.Delete<std::string>();
  SharedDtor();
}
inline void QueryRelatedUserIdsRequest::SharedDtor() {
  ABSL_DCHECK(GetArenaForAllocation() == nullptr);
  _impl_.group_indexes_.~RepeatedField();
}
void QueryRelatedUserIdsRequest::SetCachedSize(int size) const {
  _impl_._cached_size_.Set(size);
}

PROTOBUF_NOINLINE void QueryRelatedUserIdsRequest::Clear() {
// @@protoc_insertion_point(message_clear_start:turms.client.model.proto.QueryRelatedUserIdsRequest)
  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  _internal_mutable_group_indexes()->Clear();
  cached_has_bits = _impl_._has_bits_[0];
  if (cached_has_bits & 0x00000003u) {
    ::memset(&_impl_.last_updated_date_, 0, static_cast<::size_t>(
        reinterpret_cast<char*>(&_impl_.blocked_) -
        reinterpret_cast<char*>(&_impl_.last_updated_date_)) + sizeof(_impl_.blocked_));
  }
  _impl_._has_bits_.Clear();
  _internal_metadata_.Clear<std::string>();
}

const char* QueryRelatedUserIdsRequest::_InternalParse(
    const char* ptr, ::_pbi::ParseContext* ctx) {
  ptr = ::_pbi::TcParser::ParseLoop(this, ptr, ctx, &_table_.header);
  return ptr;
}


PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
const ::_pbi::TcParseTable<2, 3, 0, 0, 2> QueryRelatedUserIdsRequest::_table_ = {
  {
    PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_._has_bits_),
    0, // no _extensions_
    3, 24,  // max_field_number, fast_idx_mask
    offsetof(decltype(_table_), field_lookup_table),
    4294967288,  // skipmap
    offsetof(decltype(_table_), field_entries),
    3,  // num_field_entries
    0,  // num_aux_entries
    offsetof(decltype(_table_), field_names),  // no aux_entries
    &_QueryRelatedUserIdsRequest_default_instance_._instance,
    ::_pbi::TcParser::GenericFallbackLite,  // fallback
  }, {{
    {::_pbi::TcParser::MiniParse, {}},
    // optional bool blocked = 1;
    {::_pbi::TcParser::FastV8S1,
     {8, 1, 0, PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.blocked_)}},
    // repeated int32 group_indexes = 2;
    {::_pbi::TcParser::FastV32P1,
     {18, 63, 0, PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.group_indexes_)}},
    // optional int64 last_updated_date = 3;
    {::_pbi::TcParser::FastV64S1,
     {24, 0, 0, PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.last_updated_date_)}},
  }}, {{
    65535, 65535
  }}, {{
    // optional bool blocked = 1;
    {PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.blocked_), _Internal::kHasBitsOffset + 1, 0,
    (0 | ::_fl::kFcOptional | ::_fl::kBool)},
    // repeated int32 group_indexes = 2;
    {PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.group_indexes_), -1, 0,
    (0 | ::_fl::kFcRepeated | ::_fl::kPackedInt32)},
    // optional int64 last_updated_date = 3;
    {PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.last_updated_date_), _Internal::kHasBitsOffset + 0, 0,
    (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
  }},
  // no aux_entries
  {{
  }},
};

::uint8_t* QueryRelatedUserIdsRequest::_InternalSerialize(
    ::uint8_t* target,
    ::google::protobuf::io::EpsCopyOutputStream* stream) const {
  // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.QueryRelatedUserIdsRequest)
  ::uint32_t cached_has_bits = 0;
  (void)cached_has_bits;

  cached_has_bits = _impl_._has_bits_[0];
  // optional bool blocked = 1;
  if (cached_has_bits & 0x00000002u) {
    target = stream->EnsureSpace(target);
    target = ::_pbi::WireFormatLite::WriteBoolToArray(
        1, this->_internal_blocked(), target);
  }

  // repeated int32 group_indexes = 2;
  {
    int byte_size = _impl_._group_indexes_cached_byte_size_.Get();
    if (byte_size > 0) {
      target = stream->WriteInt32Packed(2, _internal_group_indexes(),
                                                 byte_size, target);
    }
  }

  // optional int64 last_updated_date = 3;
  if (cached_has_bits & 0x00000001u) {
    target = ::google::protobuf::internal::WireFormatLite::
        WriteInt64ToArrayWithField<3>(
            stream, this->_internal_last_updated_date(), target);
  }

  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    target = stream->WriteRaw(
        _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).data(),
        static_cast<int>(_internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size()), target);
  }
  // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.QueryRelatedUserIdsRequest)
  return target;
}

::size_t QueryRelatedUserIdsRequest::ByteSizeLong() const {
// @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.QueryRelatedUserIdsRequest)
  ::size_t total_size = 0;

  ::uint32_t cached_has_bits = 0;
  // Prevent compiler warnings about cached_has_bits being unused
  (void) cached_has_bits;

  // repeated int32 group_indexes = 2;
  {
    std::size_t data_size = ::_pbi::WireFormatLite::Int32Size(
        this->_internal_group_indexes())
    ;
    _impl_._group_indexes_cached_byte_size_.Set(::_pbi::ToCachedSize(data_size));
    std::size_t tag_size = data_size == 0
        ? 0
        : 1 + ::_pbi::WireFormatLite::Int32Size(
                            static_cast<int32_t>(data_size))
    ;
    total_size += tag_size + data_size;
  }
  cached_has_bits = _impl_._has_bits_[0];
  if (cached_has_bits & 0x00000003u) {
    // optional int64 last_updated_date = 3;
    if (cached_has_bits & 0x00000001u) {
      total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(
          this->_internal_last_updated_date());
    }

    // optional bool blocked = 1;
    if (cached_has_bits & 0x00000002u) {
      total_size += 2;
    }

  }
  if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
    total_size += _internal_metadata_.unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString).size();
  }
  int cached_size = ::_pbi::ToCachedSize(total_size);
  SetCachedSize(cached_size);
  return total_size;
}

void QueryRelatedUserIdsRequest::CheckTypeAndMergeFrom(
    const ::google::protobuf::MessageLite& from) {
  MergeFrom(*::_pbi::DownCast<const QueryRelatedUserIdsRequest*>(
      &from));
}

void QueryRelatedUserIdsRequest::MergeFrom(const QueryRelatedUserIdsRequest& from) {
  QueryRelatedUserIdsRequest* const _this = this;
  // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.QueryRelatedUserIdsRequest)
  ABSL_DCHECK_NE(&from, _this);
  ::uint32_t cached_has_bits = 0;
  (void) cached_has_bits;

  _this->_internal_mutable_group_indexes()->MergeFrom(from._internal_group_indexes());
  cached_has_bits = from._impl_._has_bits_[0];
  if (cached_has_bits & 0x00000003u) {
    if (cached_has_bits & 0x00000001u) {
      _this->_impl_.last_updated_date_ = from._impl_.last_updated_date_;
    }
    if (cached_has_bits & 0x00000002u) {
      _this->_impl_.blocked_ = from._impl_.blocked_;
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
  }
  _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void QueryRelatedUserIdsRequest::CopyFrom(const QueryRelatedUserIdsRequest& from) {
// @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.QueryRelatedUserIdsRequest)
  if (&from == this) return;
  Clear();
  MergeFrom(from);
}

PROTOBUF_NOINLINE bool QueryRelatedUserIdsRequest::IsInitialized() const {
  return true;
}

void QueryRelatedUserIdsRequest::InternalSwap(QueryRelatedUserIdsRequest* other) {
  using std::swap;
  _internal_metadata_.InternalSwap(&other->_internal_metadata_);
  swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
  _impl_.group_indexes_.InternalSwap(&other->_impl_.group_indexes_);
  ::google::protobuf::internal::memswap<
      PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.blocked_)
      + sizeof(QueryRelatedUserIdsRequest::_impl_.blocked_)
      - PROTOBUF_FIELD_OFFSET(QueryRelatedUserIdsRequest, _impl_.last_updated_date_)>(
          reinterpret_cast<char*>(&_impl_.last_updated_date_),
          reinterpret_cast<char*>(&other->_impl_.last_updated_date_));
}

std::string QueryRelatedUserIdsRequest::GetTypeName() const {
  return "turms.client.model.proto.QueryRelatedUserIdsRequest";
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
