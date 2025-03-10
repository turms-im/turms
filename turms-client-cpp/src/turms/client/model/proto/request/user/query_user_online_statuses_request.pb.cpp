// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/user/query_user_online_statuses_request.proto
// Protobuf C++ Version: 5.29.1

#include "turms/client/model/proto/request/user/query_user_online_statuses_request.pb.h"

#include <algorithm>
#include <type_traits>

#include "google/protobuf/extension_set.h"
#include "google/protobuf/generated_message_tctable_impl.h"
#include "google/protobuf/generated_message_util.h"
#include "google/protobuf/io/coded_stream.h"
#include "google/protobuf/io/zero_copy_stream_impl_lite.h"
#include "google/protobuf/wire_format_lite.h"
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

inline constexpr QueryUserOnlineStatusesRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : user_ids_{},
      _user_ids_cached_byte_size_{0},
      custom_attributes_{},
      _cached_size_{0} {
}

template <typename>
PROTOBUF_CONSTEXPR QueryUserOnlineStatusesRequest::QueryUserOnlineStatusesRequest(
    ::_pbi::ConstantInitialized)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(_class_data_.base()),
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(),
#endif  // PROTOBUF_CUSTOM_VTABLE
      _impl_(::_pbi::ConstantInitialized()) {
}
struct QueryUserOnlineStatusesRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR QueryUserOnlineStatusesRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~QueryUserOnlineStatusesRequestDefaultTypeInternal() {
    }
    union {
        QueryUserOnlineStatusesRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    QueryUserOnlineStatusesRequestDefaultTypeInternal
        _QueryUserOnlineStatusesRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class QueryUserOnlineStatusesRequest::_Internal {
   public:
};

void QueryUserOnlineStatusesRequest::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
QueryUserOnlineStatusesRequest::QueryUserOnlineStatusesRequest(::google::protobuf::Arena* arena)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(arena, _class_data_.base()) {
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(arena) {
#endif  // PROTOBUF_CUSTOM_VTABLE
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.QueryUserOnlineStatusesRequest)
}
inline PROTOBUF_NDEBUG_INLINE QueryUserOnlineStatusesRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::QueryUserOnlineStatusesRequest& from_msg)
    : user_ids_{visibility, arena, from.user_ids_},
      _user_ids_cached_byte_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_},
      _cached_size_{0} {
}

QueryUserOnlineStatusesRequest::QueryUserOnlineStatusesRequest(
    ::google::protobuf::Arena* arena, const QueryUserOnlineStatusesRequest& from)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(arena, _class_data_.base()) {
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(arena) {
#endif  // PROTOBUF_CUSTOM_VTABLE
    QueryUserOnlineStatusesRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.QueryUserOnlineStatusesRequest)
}
inline PROTOBUF_NDEBUG_INLINE QueryUserOnlineStatusesRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : user_ids_{visibility, arena},
      _user_ids_cached_byte_size_{0},
      custom_attributes_{visibility, arena},
      _cached_size_{0} {
}

inline void QueryUserOnlineStatusesRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
}
QueryUserOnlineStatusesRequest::~QueryUserOnlineStatusesRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.QueryUserOnlineStatusesRequest)
    SharedDtor(*this);
}
inline void QueryUserOnlineStatusesRequest::SharedDtor(MessageLite& self) {
    QueryUserOnlineStatusesRequest& this_ = static_cast<QueryUserOnlineStatusesRequest&>(self);
    this_._internal_metadata_.Delete<std::string>();
    ABSL_DCHECK(this_.GetArena() == nullptr);
    this_._impl_.~Impl_();
}

inline void* QueryUserOnlineStatusesRequest::PlacementNew_(const void*,
                                                           void* mem,
                                                           ::google::protobuf::Arena* arena) {
    return ::new (mem) QueryUserOnlineStatusesRequest(arena);
}
constexpr auto QueryUserOnlineStatusesRequest::InternalNewImpl_() {
    constexpr auto arena_bits = ::google::protobuf::internal::EncodePlacementArenaOffsets({
        PROTOBUF_FIELD_OFFSET(QueryUserOnlineStatusesRequest, _impl_.user_ids_) +
            decltype(QueryUserOnlineStatusesRequest::_impl_.user_ids_)::InternalGetArenaOffset(
                ::google::protobuf::MessageLite::internal_visibility()),
        PROTOBUF_FIELD_OFFSET(QueryUserOnlineStatusesRequest, _impl_.custom_attributes_) +
            decltype(QueryUserOnlineStatusesRequest::_impl_.custom_attributes_)::
                InternalGetArenaOffset(::google::protobuf::MessageLite::internal_visibility()),
    });
    if (arena_bits.has_value()) {
        return ::google::protobuf::internal::MessageCreator::ZeroInit(
            sizeof(QueryUserOnlineStatusesRequest),
            alignof(QueryUserOnlineStatusesRequest),
            *arena_bits);
    } else {
        return ::google::protobuf::internal::MessageCreator(
            &QueryUserOnlineStatusesRequest::PlacementNew_,
            sizeof(QueryUserOnlineStatusesRequest),
            alignof(QueryUserOnlineStatusesRequest));
    }
}
PROTOBUF_CONSTINIT
PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
const ::google::protobuf::internal::ClassDataLite<56> QueryUserOnlineStatusesRequest::_class_data_ =
    {
        {
            &_QueryUserOnlineStatusesRequest_default_instance_._instance,
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            &QueryUserOnlineStatusesRequest::MergeImpl,
            ::google::protobuf::MessageLite::GetNewImpl<QueryUserOnlineStatusesRequest>(),
#if defined(PROTOBUF_CUSTOM_VTABLE)
            &QueryUserOnlineStatusesRequest::SharedDtor,
            ::google::protobuf::MessageLite::GetClearImpl<QueryUserOnlineStatusesRequest>(),
            &QueryUserOnlineStatusesRequest::ByteSizeLong,
            &QueryUserOnlineStatusesRequest::_InternalSerialize,
#endif  // PROTOBUF_CUSTOM_VTABLE
            PROTOBUF_FIELD_OFFSET(QueryUserOnlineStatusesRequest, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.QueryUserOnlineStatusesRequest",
    };
const ::google::protobuf::internal::ClassData* QueryUserOnlineStatusesRequest::GetClassData()
    const {
    return _class_data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<2, 2, 1, 0, 2>
    QueryUserOnlineStatusesRequest::_table_ = {
        {
            0,  // no _has_bits_
            0,  // no _extensions_
            15,
            24,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950910,  // skipmap
            offsetof(decltype(_table_), field_entries),
            2,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            _class_data_.base(),
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::QueryUserOnlineStatusesRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // repeated int64 user_ids = 1;
            {::_pbi::TcParser::FastV64P1,
             {10, 63, 0, PROTOBUF_FIELD_OFFSET(QueryUserOnlineStatusesRequest, _impl_.user_ids_)}},
            {::_pbi::TcParser::MiniParse, {}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122,
              63,
              0,
              PROTOBUF_FIELD_OFFSET(QueryUserOnlineStatusesRequest, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // repeated int64 user_ids = 1;
            {PROTOBUF_FIELD_OFFSET(QueryUserOnlineStatusesRequest, _impl_.user_ids_),
             0,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kPackedInt64)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(QueryUserOnlineStatusesRequest, _impl_.custom_attributes_),
             0,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{}},
    };

PROTOBUF_NOINLINE void QueryUserOnlineStatusesRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.QueryUserOnlineStatusesRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.user_ids_.Clear();
    _impl_.custom_attributes_.Clear();
    _internal_metadata_.Clear<std::string>();
}

#if defined(PROTOBUF_CUSTOM_VTABLE)
::uint8_t* QueryUserOnlineStatusesRequest::_InternalSerialize(
    const MessageLite& base,
    ::uint8_t* target,
    ::google::protobuf::io::EpsCopyOutputStream* stream) {
    const QueryUserOnlineStatusesRequest& this_ =
        static_cast<const QueryUserOnlineStatusesRequest&>(base);
#else   // PROTOBUF_CUSTOM_VTABLE
::uint8_t* QueryUserOnlineStatusesRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    const QueryUserOnlineStatusesRequest& this_ = *this;
#endif  // PROTOBUF_CUSTOM_VTABLE
        // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.QueryUserOnlineStatusesRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // repeated int64 user_ids = 1;
    {
        int byte_size = this_._impl_._user_ids_cached_byte_size_.Get();
        if (byte_size > 0) {
            target = stream->WriteInt64Packed(1, this_._internal_user_ids(), byte_size, target);
        }
    }

    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    for (unsigned i = 0, n = static_cast<unsigned>(this_._internal_custom_attributes_size()); i < n;
         i++) {
        const auto& repfield = this_._internal_custom_attributes().Get(i);
        target = ::google::protobuf::internal::WireFormatLite::InternalWriteMessage(
            15, repfield, repfield.GetCachedSize(), target, stream);
    }

    if (PROTOBUF_PREDICT_FALSE(this_._internal_metadata_.have_unknown_fields())) {
        target = stream->WriteRaw(
            this_._internal_metadata_
                .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                .data(),
            static_cast<int>(
                this_._internal_metadata_
                    .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                    .size()),
            target);
    }
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.QueryUserOnlineStatusesRequest)
    return target;
}

#if defined(PROTOBUF_CUSTOM_VTABLE)
::size_t QueryUserOnlineStatusesRequest::ByteSizeLong(const MessageLite& base) {
    const QueryUserOnlineStatusesRequest& this_ =
        static_cast<const QueryUserOnlineStatusesRequest&>(base);
#else   // PROTOBUF_CUSTOM_VTABLE
::size_t QueryUserOnlineStatusesRequest::ByteSizeLong() const {
    const QueryUserOnlineStatusesRequest& this_ = *this;
#endif  // PROTOBUF_CUSTOM_VTABLE
        // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.QueryUserOnlineStatusesRequest)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(&this_);
    {
        // repeated int64 user_ids = 1;
        {
            total_size += ::_pbi::WireFormatLite::Int64SizeWithPackedTagSize(
                this_._internal_user_ids(), 1, this_._impl_._user_ids_cached_byte_size_);
        }
        // repeated .turms.client.model.proto.Value custom_attributes = 15;
        {
            total_size += 1UL * this_._internal_custom_attributes_size();
            for (const auto& msg : this_._internal_custom_attributes()) {
                total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
            }
        }
    }
    if (PROTOBUF_PREDICT_FALSE(this_._internal_metadata_.have_unknown_fields())) {
        total_size += this_._internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    this_._impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void QueryUserOnlineStatusesRequest::MergeImpl(::google::protobuf::MessageLite& to_msg,
                                               const ::google::protobuf::MessageLite& from_msg) {
    auto* const _this = static_cast<QueryUserOnlineStatusesRequest*>(&to_msg);
    auto& from = static_cast<const QueryUserOnlineStatusesRequest&>(from_msg);
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.QueryUserOnlineStatusesRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_user_ids()->MergeFrom(from._internal_user_ids());
    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void QueryUserOnlineStatusesRequest::CopyFrom(const QueryUserOnlineStatusesRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.QueryUserOnlineStatusesRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void QueryUserOnlineStatusesRequest::InternalSwap(
    QueryUserOnlineStatusesRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    _impl_.user_ids_.InternalSwap(&other->_impl_.user_ids_);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
}

// @@protoc_insertion_point(namespace_scope)
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace google {
namespace protobuf {}  // namespace protobuf
}  // namespace google
// @@protoc_insertion_point(global_scope)
#include "google/protobuf/port_undef.inc"