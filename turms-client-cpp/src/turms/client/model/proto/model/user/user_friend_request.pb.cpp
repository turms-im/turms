// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: model/user/user_friend_request.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/model/user/user_friend_request.pb.h"

#include <algorithm>
#include <type_traits>

#include "google/protobuf/extension_set.h"
#include "google/protobuf/generated_message_tctable_impl.h"
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

inline constexpr UserFriendRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      content_(&::google::protobuf::internal::fixed_address_empty_string,
               ::_pbi::ConstantInitialized()),
      reason_(&::google::protobuf::internal::fixed_address_empty_string,
              ::_pbi::ConstantInitialized()),
      id_{::int64_t{0}},
      creation_date_{::int64_t{0}},
      expiration_date_{::int64_t{0}},
      requester_id_{::int64_t{0}},
      recipient_id_{::int64_t{0}},
      request_status_{static_cast<::turms::client::model::proto::RequestStatus>(0)} {
}

template <typename>
PROTOBUF_CONSTEXPR UserFriendRequest::UserFriendRequest(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct UserFriendRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR UserFriendRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~UserFriendRequestDefaultTypeInternal() {
    }
    union {
        UserFriendRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    UserFriendRequestDefaultTypeInternal _UserFriendRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UserFriendRequest::_Internal {
   public:
    using HasBits = decltype(std::declval<UserFriendRequest>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_._has_bits_);
};

UserFriendRequest::UserFriendRequest(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UserFriendRequest)
}
inline PROTOBUF_NDEBUG_INLINE UserFriendRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::UserFriendRequest& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      content_(arena, from.content_),
      reason_(arena, from.reason_) {
}

UserFriendRequest::UserFriendRequest(::google::protobuf::Arena* arena,
                                     const UserFriendRequest& from)
    : ::google::protobuf::MessageLite(arena) {
    UserFriendRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    ::memcpy(
        reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, id_),
        reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, id_),
        offsetof(Impl_, request_status_) - offsetof(Impl_, id_) + sizeof(Impl_::request_status_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UserFriendRequest)
}
inline PROTOBUF_NDEBUG_INLINE UserFriendRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      content_(arena),
      reason_(arena) {
}

inline void UserFriendRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(
        reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, id_),
        0,
        offsetof(Impl_, request_status_) - offsetof(Impl_, id_) + sizeof(Impl_::request_status_));
}
UserFriendRequest::~UserFriendRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.UserFriendRequest)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void UserFriendRequest::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.content_.Destroy();
    _impl_.reason_.Destroy();
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* UserFriendRequest::GetClassData() const {
    PROTOBUF_CONSTINIT static const ClassDataLite<43> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.UserFriendRequest",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<3, 8, 0, 72, 2>
    UserFriendRequest::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_._has_bits_),
            0,  // no _extensions_
            8,
            56,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294967040,  // skipmap
            offsetof(decltype(_table_), field_entries),
            8,                                         // num_field_entries
            0,                                         // num_aux_entries
            offsetof(decltype(_table_), field_names),  // no aux_entries
            &_UserFriendRequest_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::UserFriendRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            // optional int64 recipient_id = 8;
            {::_pbi::TcParser::FastV64S1,
             {64, 6, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.recipient_id_)}},
            // optional int64 id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 2, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.id_)}},
            // optional int64 creation_date = 2;
            {::_pbi::TcParser::FastV64S1,
             {16, 3, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.creation_date_)}},
            // optional string content = 3;
            {::_pbi::TcParser::FastUS1,
             {26, 0, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.content_)}},
            // optional .turms.client.model.proto.RequestStatus request_status = 4;
            {::_pbi::TcParser::FastV32S1,
             {32, 7, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.request_status_)}},
            // optional string reason = 5;
            {::_pbi::TcParser::FastUS1,
             {42, 1, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.reason_)}},
            // optional int64 expiration_date = 6;
            {::_pbi::TcParser::FastV64S1,
             {48, 4, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.expiration_date_)}},
            // optional int64 requester_id = 7;
            {::_pbi::TcParser::FastV64S1,
             {56, 5, 0, PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.requester_id_)}},
        }},
        {{65535, 65535}},
        {{
            // optional int64 id = 1;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.id_),
             _Internal::kHasBitsOffset + 2,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 creation_date = 2;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.creation_date_),
             _Internal::kHasBitsOffset + 3,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional string content = 3;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.content_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // optional .turms.client.model.proto.RequestStatus request_status = 4;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.request_status_),
             _Internal::kHasBitsOffset + 7,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kOpenEnum)},
            // optional string reason = 5;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.reason_),
             _Internal::kHasBitsOffset + 1,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // optional int64 expiration_date = 6;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.expiration_date_),
             _Internal::kHasBitsOffset + 4,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 requester_id = 7;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.requester_id_),
             _Internal::kHasBitsOffset + 5,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 recipient_id = 8;
            {PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.recipient_id_),
             _Internal::kHasBitsOffset + 6,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
        }},
        // no aux_entries
        {{"\52\0\0\7\0\6\0\0\0\0\0\0\0\0\0\0"
          "turms.client.model.proto.UserFriendRequest"
          "content"
          "reason"}},
};

PROTOBUF_NOINLINE void UserFriendRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UserFriendRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        if (cached_has_bits & 0x00000001u) {
            _impl_.content_.ClearNonDefaultToEmpty();
        }
        if (cached_has_bits & 0x00000002u) {
            _impl_.reason_.ClearNonDefaultToEmpty();
        }
    }
    if (cached_has_bits & 0x000000fcu) {
        ::memset(&_impl_.id_,
                 0,
                 static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.request_status_) -
                                       reinterpret_cast<char*>(&_impl_.id_)) +
                     sizeof(_impl_.request_status_));
    }
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* UserFriendRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UserFriendRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    cached_has_bits = _impl_._has_bits_[0];
    // optional int64 id = 1;
    if (cached_has_bits & 0x00000004u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_id(), target);
    }

    // optional int64 creation_date = 2;
    if (cached_has_bits & 0x00000008u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<2>(
            stream, this->_internal_creation_date(), target);
    }

    // optional string content = 3;
    if (cached_has_bits & 0x00000001u) {
        const std::string& _s = this->_internal_content();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.UserFriendRequest.content");
        target = stream->WriteStringMaybeAliased(3, _s, target);
    }

    // optional .turms.client.model.proto.RequestStatus request_status = 4;
    if (cached_has_bits & 0x00000080u) {
        target = stream->EnsureSpace(target);
        target =
            ::_pbi::WireFormatLite::WriteEnumToArray(4, this->_internal_request_status(), target);
    }

    // optional string reason = 5;
    if (cached_has_bits & 0x00000002u) {
        const std::string& _s = this->_internal_reason();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.UserFriendRequest.reason");
        target = stream->WriteStringMaybeAliased(5, _s, target);
    }

    // optional int64 expiration_date = 6;
    if (cached_has_bits & 0x00000010u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<6>(
            stream, this->_internal_expiration_date(), target);
    }

    // optional int64 requester_id = 7;
    if (cached_has_bits & 0x00000020u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<7>(
            stream, this->_internal_requester_id(), target);
    }

    // optional int64 recipient_id = 8;
    if (cached_has_bits & 0x00000040u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<8>(
            stream, this->_internal_recipient_id(), target);
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        target = stream->WriteRaw(
            _internal_metadata_
                .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                .data(),
            static_cast<int>(
                _internal_metadata_
                    .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                    .size()),
            target);
    }
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UserFriendRequest)
    return target;
}

::size_t UserFriendRequest::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UserFriendRequest)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(reinterpret_cast<const void*>(this));
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x000000ffu) {
        // optional string content = 3;
        if (cached_has_bits & 0x00000001u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this->_internal_content());
        }

        // optional string reason = 5;
        if (cached_has_bits & 0x00000002u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this->_internal_reason());
        }

        // optional int64 id = 1;
        if (cached_has_bits & 0x00000004u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_id());
        }

        // optional int64 creation_date = 2;
        if (cached_has_bits & 0x00000008u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_creation_date());
        }

        // optional int64 expiration_date = 6;
        if (cached_has_bits & 0x00000010u) {
            total_size +=
                ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_expiration_date());
        }

        // optional int64 requester_id = 7;
        if (cached_has_bits & 0x00000020u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_requester_id());
        }

        // optional int64 recipient_id = 8;
        if (cached_has_bits & 0x00000040u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_recipient_id());
        }

        // optional .turms.client.model.proto.RequestStatus request_status = 4;
        if (cached_has_bits & 0x00000080u) {
            total_size += 1 + ::_pbi::WireFormatLite::EnumSize(this->_internal_request_status());
        }
    }
    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void UserFriendRequest::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const UserFriendRequest*>(&from));
}

void UserFriendRequest::MergeFrom(const UserFriendRequest& from) {
    UserFriendRequest* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UserFriendRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x000000ffu) {
        if (cached_has_bits & 0x00000001u) {
            _this->_internal_set_content(from._internal_content());
        }
        if (cached_has_bits & 0x00000002u) {
            _this->_internal_set_reason(from._internal_reason());
        }
        if (cached_has_bits & 0x00000004u) {
            _this->_impl_.id_ = from._impl_.id_;
        }
        if (cached_has_bits & 0x00000008u) {
            _this->_impl_.creation_date_ = from._impl_.creation_date_;
        }
        if (cached_has_bits & 0x00000010u) {
            _this->_impl_.expiration_date_ = from._impl_.expiration_date_;
        }
        if (cached_has_bits & 0x00000020u) {
            _this->_impl_.requester_id_ = from._impl_.requester_id_;
        }
        if (cached_has_bits & 0x00000040u) {
            _this->_impl_.recipient_id_ = from._impl_.recipient_id_;
        }
        if (cached_has_bits & 0x00000080u) {
            _this->_impl_.request_status_ = from._impl_.request_status_;
        }
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UserFriendRequest::CopyFrom(const UserFriendRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UserFriendRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void UserFriendRequest::InternalSwap(UserFriendRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    auto* arena = GetArena();
    ABSL_DCHECK_EQ(arena, other->GetArena());
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.content_, &other->_impl_.content_, arena);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.reason_, &other->_impl_.reason_, arena);
    ::google::protobuf::internal::memswap<PROTOBUF_FIELD_OFFSET(UserFriendRequest,
                                                                _impl_.request_status_) +
                                          sizeof(UserFriendRequest::_impl_.request_status_) -
                                          PROTOBUF_FIELD_OFFSET(UserFriendRequest, _impl_.id_)>(
        reinterpret_cast<char*>(&_impl_.id_), reinterpret_cast<char*>(&other->_impl_.id_));
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