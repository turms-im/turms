// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/user/user_online_status.proto
// Protobuf C++ Version: 5.26.1

#include "turms/client/model/proto/model/user/user_online_status.pb.h"

#include <algorithm>

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

inline constexpr UserOnlineStatus::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : using_device_types_{},
      _using_device_types_cached_byte_size_{0},
      user_id_{::int64_t{0}},
      user_status_{static_cast<::turms::client::model::proto::UserStatus>(0)},
      _cached_size_{0} {
}

template <typename>
PROTOBUF_CONSTEXPR UserOnlineStatus::UserOnlineStatus(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct UserOnlineStatusDefaultTypeInternal {
    PROTOBUF_CONSTEXPR UserOnlineStatusDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~UserOnlineStatusDefaultTypeInternal() {
    }
    union {
        UserOnlineStatus _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    UserOnlineStatusDefaultTypeInternal _UserOnlineStatus_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UserOnlineStatus::_Internal {
   public:
};

UserOnlineStatus::UserOnlineStatus(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UserOnlineStatus)
}
inline PROTOBUF_NDEBUG_INLINE UserOnlineStatus::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from)
    : using_device_types_{visibility, arena, from.using_device_types_},
      _using_device_types_cached_byte_size_{0},
      _cached_size_{0} {
}

UserOnlineStatus::UserOnlineStatus(::google::protobuf::Arena* arena, const UserOnlineStatus& from)
    : ::google::protobuf::MessageLite(arena) {
    UserOnlineStatus* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_);
    ::memcpy(
        reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, user_id_),
        reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, user_id_),
        offsetof(Impl_, user_status_) - offsetof(Impl_, user_id_) + sizeof(Impl_::user_status_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UserOnlineStatus)
}
inline PROTOBUF_NDEBUG_INLINE UserOnlineStatus::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : using_device_types_{visibility, arena},
      _using_device_types_cached_byte_size_{0},
      _cached_size_{0} {
}

inline void UserOnlineStatus::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(
        reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, user_id_),
        0,
        offsetof(Impl_, user_status_) - offsetof(Impl_, user_id_) + sizeof(Impl_::user_status_));
}
UserOnlineStatus::~UserOnlineStatus() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.UserOnlineStatus)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void UserOnlineStatus::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* UserOnlineStatus::GetClassData() const {
    struct ClassData_ {
        ::google::protobuf::MessageLite::ClassData header;
        char type_name[42];
    };

    PROTOBUF_CONSTINIT static const ClassData_ _data_ = {
        {
            nullptr,  // OnDemandRegisterArenaDtor
            PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.UserOnlineStatus",
    };

    return &_data_.header;
}
PROTOBUF_NOINLINE void UserOnlineStatus::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UserOnlineStatus)
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.using_device_types_.Clear();
    ::memset(&_impl_.user_id_,
             0,
             static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.user_status_) -
                                   reinterpret_cast<char*>(&_impl_.user_id_)) +
                 sizeof(_impl_.user_status_));
    _internal_metadata_.Clear<std::string>();
}

const char* UserOnlineStatus::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
    ptr = ::_pbi::TcParser::ParseLoop(this, ptr, ctx, &_table_.header);
    return ptr;
}

PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<2, 3, 0, 0, 2>
    UserOnlineStatus::_table_ = {
        {
            0,  // no _has_bits_
            0,  // no _extensions_
            3,
            24,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294967288,  // skipmap
            offsetof(decltype(_table_), field_entries),
            3,                                         // num_field_entries
            0,                                         // num_aux_entries
            offsetof(decltype(_table_), field_names),  // no aux_entries
            &_UserOnlineStatus_default_instance_._instance,
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::UserOnlineStatus>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // int64 user_id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 63, 0, PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_.user_id_)}},
            // .turms.client.model.proto.UserStatus user_status = 2;
            {::_pbi::TcParser::FastV32S1,
             {16, 63, 0, PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_.user_status_)}},
            // repeated .turms.client.model.proto.DeviceType using_device_types = 3;
            {::_pbi::TcParser::FastV32P1,
             {26, 63, 0, PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_.using_device_types_)}},
        }},
        {{65535, 65535}},
        {{
            // int64 user_id = 1;
            {PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_.user_id_),
             0,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
            // .turms.client.model.proto.UserStatus user_status = 2;
            {PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_.user_status_),
             0,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kOpenEnum)},
            // repeated .turms.client.model.proto.DeviceType using_device_types = 3;
            {PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_.using_device_types_),
             0,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kPackedOpenEnum)},
        }},
        // no aux_entries
        {{}},
};

::uint8_t* UserOnlineStatus::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UserOnlineStatus)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // int64 user_id = 1;
    if (this->_internal_user_id() != 0) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_user_id(), target);
    }

    // .turms.client.model.proto.UserStatus user_status = 2;
    if (this->_internal_user_status() != 0) {
        target = stream->EnsureSpace(target);
        target = ::_pbi::WireFormatLite::WriteEnumToArray(2, this->_internal_user_status(), target);
    }

    // repeated .turms.client.model.proto.DeviceType using_device_types = 3;
    {
        std::size_t byte_size = _impl_._using_device_types_cached_byte_size_.Get();
        if (byte_size > 0) {
            target = stream->WriteEnumPacked(3, _internal_using_device_types(), byte_size, target);
        }
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UserOnlineStatus)
    return target;
}

::size_t UserOnlineStatus::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UserOnlineStatus)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    // repeated .turms.client.model.proto.DeviceType using_device_types = 3;
    {
        std::size_t data_size = 0;
        auto count = static_cast<std::size_t>(this->_internal_using_device_types_size());

        for (std::size_t i = 0; i < count; ++i) {
            data_size += ::_pbi::WireFormatLite::EnumSize(
                this->_internal_using_device_types().Get(static_cast<int>(i)));
        }
        total_size += data_size;
        if (data_size > 0) {
            total_size += 1;
            total_size += ::_pbi::WireFormatLite::Int32Size(static_cast<int32_t>(data_size));
        }
        _impl_._using_device_types_cached_byte_size_.Set(::_pbi::ToCachedSize(data_size));
    }
    // int64 user_id = 1;
    if (this->_internal_user_id() != 0) {
        total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_user_id());
    }

    // .turms.client.model.proto.UserStatus user_status = 2;
    if (this->_internal_user_status() != 0) {
        total_size += 1 + ::_pbi::WireFormatLite::EnumSize(this->_internal_user_status());
    }

    if (PROTOBUF_PREDICT_FALSE(_internal_metadata_.have_unknown_fields())) {
        total_size += _internal_metadata_
                          .unknown_fields<std::string>(::google::protobuf::internal::GetEmptyString)
                          .size();
    }
    _impl_._cached_size_.Set(::_pbi::ToCachedSize(total_size));
    return total_size;
}

void UserOnlineStatus::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const UserOnlineStatus*>(&from));
}

void UserOnlineStatus::MergeFrom(const UserOnlineStatus& from) {
    UserOnlineStatus* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UserOnlineStatus)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_using_device_types()->MergeFrom(from._internal_using_device_types());
    if (from._internal_user_id() != 0) {
        _this->_impl_.user_id_ = from._impl_.user_id_;
    }
    if (from._internal_user_status() != 0) {
        _this->_impl_.user_status_ = from._impl_.user_status_;
    }
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UserOnlineStatus::CopyFrom(const UserOnlineStatus& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UserOnlineStatus)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

PROTOBUF_NOINLINE bool UserOnlineStatus::IsInitialized() const {
    return true;
}

void UserOnlineStatus::InternalSwap(UserOnlineStatus* PROTOBUF_RESTRICT other) {
    using std::swap;
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    _impl_.using_device_types_.InternalSwap(&other->_impl_.using_device_types_);
    ::google::protobuf::internal::memswap<PROTOBUF_FIELD_OFFSET(UserOnlineStatus,
                                                                _impl_.user_status_) +
                                          sizeof(UserOnlineStatus::_impl_.user_status_) -
                                          PROTOBUF_FIELD_OFFSET(UserOnlineStatus, _impl_.user_id_)>(
        reinterpret_cast<char*>(&_impl_.user_id_),
        reinterpret_cast<char*>(&other->_impl_.user_id_));
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