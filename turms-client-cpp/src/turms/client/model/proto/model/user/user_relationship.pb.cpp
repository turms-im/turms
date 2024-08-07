// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: model/user/user_relationship.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/model/user/user_relationship.pb.h"

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

inline constexpr UserRelationship::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      custom_attributes_{},
      name_(&::google::protobuf::internal::fixed_address_empty_string,
            ::_pbi::ConstantInitialized()),
      owner_id_{::int64_t{0}},
      related_user_id_{::int64_t{0}},
      block_date_{::int64_t{0}},
      group_index_{::int64_t{0}},
      establishment_date_{::int64_t{0}} {
}

template <typename>
PROTOBUF_CONSTEXPR UserRelationship::UserRelationship(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct UserRelationshipDefaultTypeInternal {
    PROTOBUF_CONSTEXPR UserRelationshipDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~UserRelationshipDefaultTypeInternal() {
    }
    union {
        UserRelationship _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    UserRelationshipDefaultTypeInternal _UserRelationship_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UserRelationship::_Internal {
   public:
    using HasBits = decltype(std::declval<UserRelationship>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_._has_bits_);
};

void UserRelationship::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
UserRelationship::UserRelationship(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UserRelationship)
}
inline PROTOBUF_NDEBUG_INLINE UserRelationship::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::UserRelationship& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_},
      name_(arena, from.name_) {
}

UserRelationship::UserRelationship(::google::protobuf::Arena* arena, const UserRelationship& from)
    : ::google::protobuf::MessageLite(arena) {
    UserRelationship* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    ::memcpy(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, owner_id_),
             reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, owner_id_),
             offsetof(Impl_, establishment_date_) - offsetof(Impl_, owner_id_) +
                 sizeof(Impl_::establishment_date_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UserRelationship)
}
inline PROTOBUF_NDEBUG_INLINE UserRelationship::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      custom_attributes_{visibility, arena},
      name_(arena) {
}

inline void UserRelationship::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, owner_id_),
             0,
             offsetof(Impl_, establishment_date_) - offsetof(Impl_, owner_id_) +
                 sizeof(Impl_::establishment_date_));
}
UserRelationship::~UserRelationship() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.UserRelationship)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void UserRelationship::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.name_.Destroy();
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* UserRelationship::GetClassData() const {
    PROTOBUF_CONSTINIT static const ClassDataLite<42> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.UserRelationship",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<3, 7, 1, 54, 2>
    UserRelationship::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_._has_bits_),
            0,  // no _extensions_
            15,
            56,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950848,  // skipmap
            offsetof(decltype(_table_), field_entries),
            7,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_UserRelationship_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::UserRelationship>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // optional int64 owner_id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 1, 0, PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.owner_id_)}},
            // optional int64 related_user_id = 2;
            {::_pbi::TcParser::FastV64S1,
             {16, 2, 0, PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.related_user_id_)}},
            // optional int64 block_date = 3;
            {::_pbi::TcParser::FastV64S1,
             {24, 3, 0, PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.block_date_)}},
            // optional int64 group_index = 4;
            {::_pbi::TcParser::FastV64S1,
             {32, 4, 0, PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.group_index_)}},
            // optional int64 establishment_date = 5;
            {::_pbi::TcParser::FastV64S1,
             {40, 5, 0, PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.establishment_date_)}},
            // optional string name = 6;
            {::_pbi::TcParser::FastUS1,
             {50, 0, 0, PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.name_)}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122, 63, 0, PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // optional int64 owner_id = 1;
            {PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.owner_id_),
             _Internal::kHasBitsOffset + 1,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 related_user_id = 2;
            {PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.related_user_id_),
             _Internal::kHasBitsOffset + 2,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 block_date = 3;
            {PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.block_date_),
             _Internal::kHasBitsOffset + 3,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 group_index = 4;
            {PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.group_index_),
             _Internal::kHasBitsOffset + 4,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 establishment_date = 5;
            {PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.establishment_date_),
             _Internal::kHasBitsOffset + 5,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional string name = 6;
            {PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.name_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.custom_attributes_),
             -1,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{"\51\0\0\0\0\0\4\0"
          "turms.client.model.proto.UserRelationship"
          "name"}},
};

PROTOBUF_NOINLINE void UserRelationship::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UserRelationship)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.custom_attributes_.Clear();
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000001u) {
        _impl_.name_.ClearNonDefaultToEmpty();
    }
    if (cached_has_bits & 0x0000003eu) {
        ::memset(&_impl_.owner_id_,
                 0,
                 static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.establishment_date_) -
                                       reinterpret_cast<char*>(&_impl_.owner_id_)) +
                     sizeof(_impl_.establishment_date_));
    }
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* UserRelationship::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UserRelationship)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    cached_has_bits = _impl_._has_bits_[0];
    // optional int64 owner_id = 1;
    if (cached_has_bits & 0x00000002u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_owner_id(), target);
    }

    // optional int64 related_user_id = 2;
    if (cached_has_bits & 0x00000004u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<2>(
            stream, this->_internal_related_user_id(), target);
    }

    // optional int64 block_date = 3;
    if (cached_has_bits & 0x00000008u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<3>(
            stream, this->_internal_block_date(), target);
    }

    // optional int64 group_index = 4;
    if (cached_has_bits & 0x00000010u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<4>(
            stream, this->_internal_group_index(), target);
    }

    // optional int64 establishment_date = 5;
    if (cached_has_bits & 0x00000020u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<5>(
            stream, this->_internal_establishment_date(), target);
    }

    // optional string name = 6;
    if (cached_has_bits & 0x00000001u) {
        const std::string& _s = this->_internal_name();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.UserRelationship.name");
        target = stream->WriteStringMaybeAliased(6, _s, target);
    }

    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    for (unsigned i = 0, n = static_cast<unsigned>(this->_internal_custom_attributes_size()); i < n;
         i++) {
        const auto& repfield = this->_internal_custom_attributes().Get(i);
        target = ::google::protobuf::internal::WireFormatLite::InternalWriteMessage(
            15, repfield, repfield.GetCachedSize(), target, stream);
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UserRelationship)
    return target;
}

::size_t UserRelationship::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UserRelationship)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(reinterpret_cast<const void*>(this));
    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    total_size += 1UL * this->_internal_custom_attributes_size();
    for (const auto& msg : this->_internal_custom_attributes()) {
        total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
    }
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x0000003fu) {
        // optional string name = 6;
        if (cached_has_bits & 0x00000001u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this->_internal_name());
        }

        // optional int64 owner_id = 1;
        if (cached_has_bits & 0x00000002u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_owner_id());
        }

        // optional int64 related_user_id = 2;
        if (cached_has_bits & 0x00000004u) {
            total_size +=
                ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_related_user_id());
        }

        // optional int64 block_date = 3;
        if (cached_has_bits & 0x00000008u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_block_date());
        }

        // optional int64 group_index = 4;
        if (cached_has_bits & 0x00000010u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_group_index());
        }

        // optional int64 establishment_date = 5;
        if (cached_has_bits & 0x00000020u) {
            total_size +=
                ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_establishment_date());
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

void UserRelationship::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const UserRelationship*>(&from));
}

void UserRelationship::MergeFrom(const UserRelationship& from) {
    UserRelationship* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UserRelationship)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x0000003fu) {
        if (cached_has_bits & 0x00000001u) {
            _this->_internal_set_name(from._internal_name());
        }
        if (cached_has_bits & 0x00000002u) {
            _this->_impl_.owner_id_ = from._impl_.owner_id_;
        }
        if (cached_has_bits & 0x00000004u) {
            _this->_impl_.related_user_id_ = from._impl_.related_user_id_;
        }
        if (cached_has_bits & 0x00000008u) {
            _this->_impl_.block_date_ = from._impl_.block_date_;
        }
        if (cached_has_bits & 0x00000010u) {
            _this->_impl_.group_index_ = from._impl_.group_index_;
        }
        if (cached_has_bits & 0x00000020u) {
            _this->_impl_.establishment_date_ = from._impl_.establishment_date_;
        }
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UserRelationship::CopyFrom(const UserRelationship& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UserRelationship)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void UserRelationship::InternalSwap(UserRelationship* PROTOBUF_RESTRICT other) {
    using std::swap;
    auto* arena = GetArena();
    ABSL_DCHECK_EQ(arena, other->GetArena());
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.name_, &other->_impl_.name_, arena);
    ::google::protobuf::internal::memswap<
        PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.establishment_date_) +
        sizeof(UserRelationship::_impl_.establishment_date_) -
        PROTOBUF_FIELD_OFFSET(UserRelationship, _impl_.owner_id_)>(
        reinterpret_cast<char*>(&_impl_.owner_id_),
        reinterpret_cast<char*>(&other->_impl_.owner_id_));
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