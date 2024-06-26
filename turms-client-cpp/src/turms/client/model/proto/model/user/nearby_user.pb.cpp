// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: model/user/nearby_user.proto
// Protobuf C++ Version: 5.26.1

#include "turms/client/model/proto/model/user/nearby_user.pb.h"

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

inline constexpr NearbyUser::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      info_{nullptr},
      location_{nullptr},
      user_id_{::int64_t{0}},
      device_type_{static_cast<::turms::client::model::proto::DeviceType>(0)},
      distance_{0} {
}

template <typename>
PROTOBUF_CONSTEXPR NearbyUser::NearbyUser(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct NearbyUserDefaultTypeInternal {
    PROTOBUF_CONSTEXPR NearbyUserDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~NearbyUserDefaultTypeInternal() {
    }
    union {
        NearbyUser _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    NearbyUserDefaultTypeInternal _NearbyUser_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class NearbyUser::_Internal {
   public:
    using HasBits = decltype(std::declval<NearbyUser>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_._has_bits_);
};

void NearbyUser::clear_info() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    if (_impl_.info_ != nullptr)
        _impl_.info_->Clear();
    _impl_._has_bits_[0] &= ~0x00000001u;
}
void NearbyUser::clear_location() {
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    if (_impl_.location_ != nullptr)
        _impl_.location_->Clear();
    _impl_._has_bits_[0] &= ~0x00000002u;
}
NearbyUser::NearbyUser(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.NearbyUser)
}
inline PROTOBUF_NDEBUG_INLINE NearbyUser::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from)
    : _has_bits_{from._has_bits_},
      _cached_size_{0} {
}

NearbyUser::NearbyUser(::google::protobuf::Arena* arena, const NearbyUser& from)
    : ::google::protobuf::MessageLite(arena) {
    NearbyUser* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_);
    ::uint32_t cached_has_bits = _impl_._has_bits_[0];
    _impl_.info_ = (cached_has_bits & 0x00000001u)
                       ? ::google::protobuf::MessageLite::CopyConstruct<
                             ::turms::client::model::proto::UserInfo>(arena, *from._impl_.info_)
                       : nullptr;
    _impl_.location_ =
        (cached_has_bits & 0x00000002u)
            ? ::google::protobuf::MessageLite::CopyConstruct<
                  ::turms::client::model::proto::UserLocation>(arena, *from._impl_.location_)
            : nullptr;
    ::memcpy(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, user_id_),
             reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, user_id_),
             offsetof(Impl_, distance_) - offsetof(Impl_, user_id_) + sizeof(Impl_::distance_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.NearbyUser)
}
inline PROTOBUF_NDEBUG_INLINE NearbyUser::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0} {
}

inline void NearbyUser::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, info_),
             0,
             offsetof(Impl_, distance_) - offsetof(Impl_, info_) + sizeof(Impl_::distance_));
}
NearbyUser::~NearbyUser() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.NearbyUser)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void NearbyUser::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    delete _impl_.info_;
    delete _impl_.location_;
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* NearbyUser::GetClassData() const {
    struct ClassData_ {
        ::google::protobuf::MessageLite::ClassData header;
        char type_name[36];
    };

    PROTOBUF_CONSTINIT static const ClassData_ _data_ = {
        {
            nullptr,  // OnDemandRegisterArenaDtor
            PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.NearbyUser",
    };

    return &_data_.header;
}
PROTOBUF_NOINLINE void NearbyUser::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.NearbyUser)
    PROTOBUF_TSAN_WRITE(&_impl_._tsan_detect_race);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        if (cached_has_bits & 0x00000001u) {
            ABSL_DCHECK(_impl_.info_ != nullptr);
            _impl_.info_->Clear();
        }
        if (cached_has_bits & 0x00000002u) {
            ABSL_DCHECK(_impl_.location_ != nullptr);
            _impl_.location_->Clear();
        }
    }
    _impl_.user_id_ = ::int64_t{0};
    if (cached_has_bits & 0x0000000cu) {
        ::memset(&_impl_.device_type_,
                 0,
                 static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.distance_) -
                                       reinterpret_cast<char*>(&_impl_.device_type_)) +
                     sizeof(_impl_.distance_));
    }
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

const char* NearbyUser::_InternalParse(const char* ptr, ::_pbi::ParseContext* ctx) {
    ptr = ::_pbi::TcParser::ParseLoop(this, ptr, ctx, &_table_.header);
    return ptr;
}

PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<3, 5, 2, 0, 2>
    NearbyUser::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_._has_bits_),
            0,  // no _extensions_
            5,
            56,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294967264,  // skipmap
            offsetof(decltype(_table_), field_entries),
            5,  // num_field_entries
            2,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_NearbyUser_default_instance_._instance,
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<::turms::client::model::proto::NearbyUser>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // int64 user_id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 63, 0, PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.user_id_)}},
            // optional .turms.client.model.proto.DeviceType device_type = 2;
            {::_pbi::TcParser::FastV32S1,
             {16, 2, 0, PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.device_type_)}},
            // optional .turms.client.model.proto.UserInfo info = 3;
            {::_pbi::TcParser::FastMtS1,
             {26, 0, 0, PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.info_)}},
            // optional int32 distance = 4;
            {::_pbi::TcParser::FastV32S1,
             {32, 3, 0, PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.distance_)}},
            // optional .turms.client.model.proto.UserLocation location = 5;
            {::_pbi::TcParser::FastMtS1,
             {42, 1, 1, PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.location_)}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
        }},
        {{65535, 65535}},
        {{
            // int64 user_id = 1;
            {PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.user_id_),
             -1,
             0,
             (0 | ::_fl::kFcSingular | ::_fl::kInt64)},
            // optional .turms.client.model.proto.DeviceType device_type = 2;
            {PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.device_type_),
             _Internal::kHasBitsOffset + 2,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kOpenEnum)},
            // optional .turms.client.model.proto.UserInfo info = 3;
            {PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.info_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kMessage | ::_fl::kTvTable)},
            // optional int32 distance = 4;
            {PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.distance_),
             _Internal::kHasBitsOffset + 3,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt32)},
            // optional .turms.client.model.proto.UserLocation location = 5;
            {PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.location_),
             _Internal::kHasBitsOffset + 1,
             1,
             (0 | ::_fl::kFcOptional | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::UserInfo>()},
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::UserLocation>()},
        }},
        {{}},
};

::uint8_t* NearbyUser::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.NearbyUser)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    // int64 user_id = 1;
    if (this->_internal_user_id() != 0) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_user_id(), target);
    }

    cached_has_bits = _impl_._has_bits_[0];
    // optional .turms.client.model.proto.DeviceType device_type = 2;
    if (cached_has_bits & 0x00000004u) {
        target = stream->EnsureSpace(target);
        target = ::_pbi::WireFormatLite::WriteEnumToArray(2, this->_internal_device_type(), target);
    }

    // optional .turms.client.model.proto.UserInfo info = 3;
    if (cached_has_bits & 0x00000001u) {
        target = ::google::protobuf::internal::WireFormatLite::InternalWriteMessage(
            3, *_impl_.info_, _impl_.info_->GetCachedSize(), target, stream);
    }

    // optional int32 distance = 4;
    if (cached_has_bits & 0x00000008u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt32ToArrayWithField<4>(
            stream, this->_internal_distance(), target);
    }

    // optional .turms.client.model.proto.UserLocation location = 5;
    if (cached_has_bits & 0x00000002u) {
        target = ::google::protobuf::internal::WireFormatLite::InternalWriteMessage(
            5, *_impl_.location_, _impl_.location_->GetCachedSize(), target, stream);
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.NearbyUser)
    return target;
}

::size_t NearbyUser::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.NearbyUser)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        // optional .turms.client.model.proto.UserInfo info = 3;
        if (cached_has_bits & 0x00000001u) {
            total_size +=
                1 + ::google::protobuf::internal::WireFormatLite::MessageSize(*_impl_.info_);
        }

        // optional .turms.client.model.proto.UserLocation location = 5;
        if (cached_has_bits & 0x00000002u) {
            total_size +=
                1 + ::google::protobuf::internal::WireFormatLite::MessageSize(*_impl_.location_);
        }
    }
    // int64 user_id = 1;
    if (this->_internal_user_id() != 0) {
        total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_user_id());
    }

    if (cached_has_bits & 0x0000000cu) {
        // optional .turms.client.model.proto.DeviceType device_type = 2;
        if (cached_has_bits & 0x00000004u) {
            total_size += 1 + ::_pbi::WireFormatLite::EnumSize(this->_internal_device_type());
        }

        // optional int32 distance = 4;
        if (cached_has_bits & 0x00000008u) {
            total_size += ::_pbi::WireFormatLite::Int32SizePlusOne(this->_internal_distance());
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

void NearbyUser::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const NearbyUser*>(&from));
}

void NearbyUser::MergeFrom(const NearbyUser& from) {
    NearbyUser* const _this = this;
    ::google::protobuf::Arena* arena = _this->GetArena();
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.NearbyUser)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x00000003u) {
        if (cached_has_bits & 0x00000001u) {
            ABSL_DCHECK(from._impl_.info_ != nullptr);
            if (_this->_impl_.info_ == nullptr) {
                _this->_impl_.info_ = ::google::protobuf::MessageLite::CopyConstruct<
                    ::turms::client::model::proto::UserInfo>(arena, *from._impl_.info_);
            } else {
                _this->_impl_.info_->MergeFrom(*from._impl_.info_);
            }
        }
        if (cached_has_bits & 0x00000002u) {
            ABSL_DCHECK(from._impl_.location_ != nullptr);
            if (_this->_impl_.location_ == nullptr) {
                _this->_impl_.location_ = ::google::protobuf::MessageLite::CopyConstruct<
                    ::turms::client::model::proto::UserLocation>(arena, *from._impl_.location_);
            } else {
                _this->_impl_.location_->MergeFrom(*from._impl_.location_);
            }
        }
    }
    if (from._internal_user_id() != 0) {
        _this->_impl_.user_id_ = from._impl_.user_id_;
    }
    if (cached_has_bits & 0x0000000cu) {
        if (cached_has_bits & 0x00000004u) {
            _this->_impl_.device_type_ = from._impl_.device_type_;
        }
        if (cached_has_bits & 0x00000008u) {
            _this->_impl_.distance_ = from._impl_.distance_;
        }
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void NearbyUser::CopyFrom(const NearbyUser& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.NearbyUser)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

PROTOBUF_NOINLINE bool NearbyUser::IsInitialized() const {
    return true;
}

void NearbyUser::InternalSwap(NearbyUser* PROTOBUF_RESTRICT other) {
    using std::swap;
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    ::google::protobuf::internal::memswap<PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.distance_) +
                                          sizeof(NearbyUser::_impl_.distance_) -
                                          PROTOBUF_FIELD_OFFSET(NearbyUser, _impl_.info_)>(
        reinterpret_cast<char*>(&_impl_.info_), reinterpret_cast<char*>(&other->_impl_.info_));
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