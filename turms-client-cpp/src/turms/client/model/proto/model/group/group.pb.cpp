// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: model/group/group.proto
// Protobuf C++ Version: 5.27.2

#include "turms/client/model/proto/model/group/group.pb.h"

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

inline constexpr Group::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      user_defined_attributes_{},
      custom_attributes_{},
      name_(&::google::protobuf::internal::fixed_address_empty_string,
            ::_pbi::ConstantInitialized()),
      intro_(&::google::protobuf::internal::fixed_address_empty_string,
             ::_pbi::ConstantInitialized()),
      announcement_(&::google::protobuf::internal::fixed_address_empty_string,
                    ::_pbi::ConstantInitialized()),
      id_{::int64_t{0}},
      type_id_{::int64_t{0}},
      creator_id_{::int64_t{0}},
      owner_id_{::int64_t{0}},
      creation_date_{::int64_t{0}},
      last_updated_date_{::int64_t{0}},
      mute_end_date_{::int64_t{0}},
      active_{false} {
}

template <typename>
PROTOBUF_CONSTEXPR Group::Group(::_pbi::ConstantInitialized)
    : _impl_(::_pbi::ConstantInitialized()) {
}
struct GroupDefaultTypeInternal {
    PROTOBUF_CONSTEXPR GroupDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~GroupDefaultTypeInternal() {
    }
    union {
        Group _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    GroupDefaultTypeInternal _Group_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

// ===================================================================

class Group::_Internal {
   public:
    using HasBits = decltype(std::declval<Group>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset = 8 * PROTOBUF_FIELD_OFFSET(Group, _impl_._has_bits_);
};

void Group::clear_user_defined_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.user_defined_attributes_.Clear();
}
void Group::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
Group::Group(::google::protobuf::Arena* arena)
    : ::google::protobuf::MessageLite(arena) {
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.Group)
}
inline PROTOBUF_NDEBUG_INLINE Group::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::Group& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      user_defined_attributes_{visibility, arena, from.user_defined_attributes_},
      custom_attributes_{visibility, arena, from.custom_attributes_},
      name_(arena, from.name_),
      intro_(arena, from.intro_),
      announcement_(arena, from.announcement_) {
}

Group::Group(::google::protobuf::Arena* arena, const Group& from)
    : ::google::protobuf::MessageLite(arena) {
    Group* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    ::memcpy(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, id_),
             reinterpret_cast<const char*>(&from._impl_) + offsetof(Impl_, id_),
             offsetof(Impl_, active_) - offsetof(Impl_, id_) + sizeof(Impl_::active_));

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.Group)
}
inline PROTOBUF_NDEBUG_INLINE Group::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      user_defined_attributes_{visibility, arena},
      custom_attributes_{visibility, arena},
      name_(arena),
      intro_(arena),
      announcement_(arena) {
}

inline void Group::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    ::memset(reinterpret_cast<char*>(&_impl_) + offsetof(Impl_, id_),
             0,
             offsetof(Impl_, active_) - offsetof(Impl_, id_) + sizeof(Impl_::active_));
}
Group::~Group() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.Group)
    _internal_metadata_.Delete<std::string>();
    SharedDtor();
}
inline void Group::SharedDtor() {
    ABSL_DCHECK(GetArena() == nullptr);
    _impl_.name_.Destroy();
    _impl_.intro_.Destroy();
    _impl_.announcement_.Destroy();
    _impl_.~Impl_();
}

const ::google::protobuf::MessageLite::ClassData* Group::GetClassData() const {
    PROTOBUF_CONSTINIT static const ClassDataLite<31> _data_ = {
        {
            &_table_.header,
            nullptr,  // OnDemandRegisterArenaDtor
            nullptr,  // IsInitialized
            PROTOBUF_FIELD_OFFSET(Group, _impl_._cached_size_),
            true,
        },
        "turms.client.model.proto.Group",
    };

    return _data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<4, 13, 3, 91, 2>
    Group::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(Group, _impl_._has_bits_),
            0,  // no _extensions_
            15,
            120,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294946816,  // skipmap
            offsetof(decltype(_table_), field_entries),
            13,  // num_field_entries
            3,   // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            &_Group_default_instance_._instance,
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<::turms::client::model::proto::Group>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // optional int64 id = 1;
            {::_pbi::TcParser::FastV64S1, {8, 3, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.id_)}},
            // optional int64 type_id = 2;
            {::_pbi::TcParser::FastV64S1,
             {16, 4, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.type_id_)}},
            // optional int64 creator_id = 3;
            {::_pbi::TcParser::FastV64S1,
             {24, 5, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.creator_id_)}},
            // optional int64 owner_id = 4;
            {::_pbi::TcParser::FastV64S1,
             {32, 6, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.owner_id_)}},
            // optional string name = 5;
            {::_pbi::TcParser::FastUS1, {42, 0, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.name_)}},
            // optional string intro = 6;
            {::_pbi::TcParser::FastUS1, {50, 1, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.intro_)}},
            // optional string announcement = 7;
            {::_pbi::TcParser::FastUS1,
             {58, 2, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.announcement_)}},
            // optional int64 creation_date = 8;
            {::_pbi::TcParser::FastV64S1,
             {64, 7, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.creation_date_)}},
            // optional int64 last_updated_date = 9;
            {::_pbi::TcParser::FastV64S1,
             {72, 8, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.last_updated_date_)}},
            // optional int64 mute_end_date = 10;
            {::_pbi::TcParser::FastV64S1,
             {80, 9, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.mute_end_date_)}},
            // optional bool active = 11;
            {::_pbi::TcParser::FastV8S1, {88, 10, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.active_)}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122, 63, 0, PROTOBUF_FIELD_OFFSET(Group, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // optional int64 id = 1;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.id_),
             _Internal::kHasBitsOffset + 3,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 type_id = 2;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.type_id_),
             _Internal::kHasBitsOffset + 4,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 creator_id = 3;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.creator_id_),
             _Internal::kHasBitsOffset + 5,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 owner_id = 4;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.owner_id_),
             _Internal::kHasBitsOffset + 6,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional string name = 5;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.name_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // optional string intro = 6;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.intro_),
             _Internal::kHasBitsOffset + 1,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // optional string announcement = 7;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.announcement_),
             _Internal::kHasBitsOffset + 2,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // optional int64 creation_date = 8;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.creation_date_),
             _Internal::kHasBitsOffset + 7,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 last_updated_date = 9;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.last_updated_date_),
             _Internal::kHasBitsOffset + 8,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional int64 mute_end_date = 10;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.mute_end_date_),
             _Internal::kHasBitsOffset + 9,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional bool active = 11;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.active_),
             _Internal::kHasBitsOffset + 10,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kBool)},
            // map<string, .turms.client.model.proto.Value> user_defined_attributes = 12;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.user_defined_attributes_),
             -1,
             1,
             (0 | ::_fl::kFcRepeated | ::_fl::kMap)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(Group, _impl_.custom_attributes_),
             -1,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
            {::_pbi::TcParser::GetMapAuxInfo<decltype(Group()._impl_.user_defined_attributes_)>(
                1, 0, 0, 9, 11)},
            {::_pbi::TcParser::CreateInArenaStorageCb<::turms::client::model::proto::Value>},
        }},
        {{"\36\0\0\0\0\4\5\14\0\0\0\0\27\0\0\0"
          "turms.client.model.proto.Group"
          "name"
          "intro"
          "announcement"
          "user_defined_attributes"}},
};

PROTOBUF_NOINLINE void Group::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.Group)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    _impl_.user_defined_attributes_.Clear();
    _impl_.custom_attributes_.Clear();
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x00000007u) {
        if (cached_has_bits & 0x00000001u) {
            _impl_.name_.ClearNonDefaultToEmpty();
        }
        if (cached_has_bits & 0x00000002u) {
            _impl_.intro_.ClearNonDefaultToEmpty();
        }
        if (cached_has_bits & 0x00000004u) {
            _impl_.announcement_.ClearNonDefaultToEmpty();
        }
    }
    if (cached_has_bits & 0x000000f8u) {
        ::memset(&_impl_.id_,
                 0,
                 static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.creation_date_) -
                                       reinterpret_cast<char*>(&_impl_.id_)) +
                     sizeof(_impl_.creation_date_));
    }
    if (cached_has_bits & 0x00000700u) {
        ::memset(&_impl_.last_updated_date_,
                 0,
                 static_cast<::size_t>(reinterpret_cast<char*>(&_impl_.active_) -
                                       reinterpret_cast<char*>(&_impl_.last_updated_date_)) +
                     sizeof(_impl_.active_));
    }
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

::uint8_t* Group::_InternalSerialize(::uint8_t* target,
                                     ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.Group)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    cached_has_bits = _impl_._has_bits_[0];
    // optional int64 id = 1;
    if (cached_has_bits & 0x00000008u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this->_internal_id(), target);
    }

    // optional int64 type_id = 2;
    if (cached_has_bits & 0x00000010u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<2>(
            stream, this->_internal_type_id(), target);
    }

    // optional int64 creator_id = 3;
    if (cached_has_bits & 0x00000020u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<3>(
            stream, this->_internal_creator_id(), target);
    }

    // optional int64 owner_id = 4;
    if (cached_has_bits & 0x00000040u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<4>(
            stream, this->_internal_owner_id(), target);
    }

    // optional string name = 5;
    if (cached_has_bits & 0x00000001u) {
        const std::string& _s = this->_internal_name();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.Group.name");
        target = stream->WriteStringMaybeAliased(5, _s, target);
    }

    // optional string intro = 6;
    if (cached_has_bits & 0x00000002u) {
        const std::string& _s = this->_internal_intro();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.Group.intro");
        target = stream->WriteStringMaybeAliased(6, _s, target);
    }

    // optional string announcement = 7;
    if (cached_has_bits & 0x00000004u) {
        const std::string& _s = this->_internal_announcement();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.Group.announcement");
        target = stream->WriteStringMaybeAliased(7, _s, target);
    }

    // optional int64 creation_date = 8;
    if (cached_has_bits & 0x00000080u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<8>(
            stream, this->_internal_creation_date(), target);
    }

    // optional int64 last_updated_date = 9;
    if (cached_has_bits & 0x00000100u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<9>(
            stream, this->_internal_last_updated_date(), target);
    }

    // optional int64 mute_end_date = 10;
    if (cached_has_bits & 0x00000200u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<10>(
            stream, this->_internal_mute_end_date(), target);
    }

    // optional bool active = 11;
    if (cached_has_bits & 0x00000400u) {
        target = stream->EnsureSpace(target);
        target = ::_pbi::WireFormatLite::WriteBoolToArray(11, this->_internal_active(), target);
    }

    // map<string, .turms.client.model.proto.Value> user_defined_attributes = 12;
    if (!_internal_user_defined_attributes().empty()) {
        using MapType = ::google::protobuf::Map<std::string, ::turms::client::model::proto::Value>;
        using WireHelper = _pbi::MapEntryFuncs<std::string,
                                               ::turms::client::model::proto::Value,
                                               _pbi::WireFormatLite::TYPE_STRING,
                                               _pbi::WireFormatLite::TYPE_MESSAGE>;
        const auto& field = _internal_user_defined_attributes();

        if (stream->IsSerializationDeterministic() && field.size() > 1) {
            for (const auto& entry : ::google::protobuf::internal::MapSorterPtr<MapType>(field)) {
                target =
                    WireHelper::InternalSerialize(12, entry.first, entry.second, target, stream);
                ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
                    entry.first.data(),
                    static_cast<int>(entry.first.length()),
                    ::google::protobuf::internal::WireFormatLite::SERIALIZE,
                    "turms.client.model.proto.Group.user_defined_attributes");
            }
        } else {
            for (const auto& entry : field) {
                target =
                    WireHelper::InternalSerialize(12, entry.first, entry.second, target, stream);
                ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
                    entry.first.data(),
                    static_cast<int>(entry.first.length()),
                    ::google::protobuf::internal::WireFormatLite::SERIALIZE,
                    "turms.client.model.proto.Group.user_defined_attributes");
            }
        }
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.Group)
    return target;
}

::size_t Group::ByteSizeLong() const {
    // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.Group)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(reinterpret_cast<const void*>(this));
    // map<string, .turms.client.model.proto.Value> user_defined_attributes = 12;
    total_size +=
        1 * ::google::protobuf::internal::FromIntSize(_internal_user_defined_attributes_size());
    for (const auto& entry : _internal_user_defined_attributes()) {
        total_size +=
            _pbi::MapEntryFuncs<std::string,
                                ::turms::client::model::proto::Value,
                                _pbi::WireFormatLite::TYPE_STRING,
                                _pbi::WireFormatLite::TYPE_MESSAGE>::ByteSizeLong(entry.first,
                                                                                  entry.second);
    }
    // repeated .turms.client.model.proto.Value custom_attributes = 15;
    total_size += 1UL * this->_internal_custom_attributes_size();
    for (const auto& msg : this->_internal_custom_attributes()) {
        total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
    }
    cached_has_bits = _impl_._has_bits_[0];
    if (cached_has_bits & 0x000000ffu) {
        // optional string name = 5;
        if (cached_has_bits & 0x00000001u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this->_internal_name());
        }

        // optional string intro = 6;
        if (cached_has_bits & 0x00000002u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this->_internal_intro());
        }

        // optional string announcement = 7;
        if (cached_has_bits & 0x00000004u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this->_internal_announcement());
        }

        // optional int64 id = 1;
        if (cached_has_bits & 0x00000008u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_id());
        }

        // optional int64 type_id = 2;
        if (cached_has_bits & 0x00000010u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_type_id());
        }

        // optional int64 creator_id = 3;
        if (cached_has_bits & 0x00000020u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_creator_id());
        }

        // optional int64 owner_id = 4;
        if (cached_has_bits & 0x00000040u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_owner_id());
        }

        // optional int64 creation_date = 8;
        if (cached_has_bits & 0x00000080u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_creation_date());
        }
    }
    if (cached_has_bits & 0x00000700u) {
        // optional int64 last_updated_date = 9;
        if (cached_has_bits & 0x00000100u) {
            total_size +=
                ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_last_updated_date());
        }

        // optional int64 mute_end_date = 10;
        if (cached_has_bits & 0x00000200u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this->_internal_mute_end_date());
        }

        // optional bool active = 11;
        if (cached_has_bits & 0x00000400u) {
            total_size += 2;
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

void Group::CheckTypeAndMergeFrom(const ::google::protobuf::MessageLite& from) {
    MergeFrom(*::_pbi::DownCast<const Group*>(&from));
}

void Group::MergeFrom(const Group& from) {
    Group* const _this = this;
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.Group)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_impl_.user_defined_attributes_.MergeFrom(from._impl_.user_defined_attributes_);
    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x000000ffu) {
        if (cached_has_bits & 0x00000001u) {
            _this->_internal_set_name(from._internal_name());
        }
        if (cached_has_bits & 0x00000002u) {
            _this->_internal_set_intro(from._internal_intro());
        }
        if (cached_has_bits & 0x00000004u) {
            _this->_internal_set_announcement(from._internal_announcement());
        }
        if (cached_has_bits & 0x00000008u) {
            _this->_impl_.id_ = from._impl_.id_;
        }
        if (cached_has_bits & 0x00000010u) {
            _this->_impl_.type_id_ = from._impl_.type_id_;
        }
        if (cached_has_bits & 0x00000020u) {
            _this->_impl_.creator_id_ = from._impl_.creator_id_;
        }
        if (cached_has_bits & 0x00000040u) {
            _this->_impl_.owner_id_ = from._impl_.owner_id_;
        }
        if (cached_has_bits & 0x00000080u) {
            _this->_impl_.creation_date_ = from._impl_.creation_date_;
        }
    }
    if (cached_has_bits & 0x00000700u) {
        if (cached_has_bits & 0x00000100u) {
            _this->_impl_.last_updated_date_ = from._impl_.last_updated_date_;
        }
        if (cached_has_bits & 0x00000200u) {
            _this->_impl_.mute_end_date_ = from._impl_.mute_end_date_;
        }
        if (cached_has_bits & 0x00000400u) {
            _this->_impl_.active_ = from._impl_.active_;
        }
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void Group::CopyFrom(const Group& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.Group)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void Group::InternalSwap(Group* PROTOBUF_RESTRICT other) {
    using std::swap;
    auto* arena = GetArena();
    ABSL_DCHECK_EQ(arena, other->GetArena());
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    _impl_.user_defined_attributes_.InternalSwap(&other->_impl_.user_defined_attributes_);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.name_, &other->_impl_.name_, arena);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.intro_, &other->_impl_.intro_, arena);
    ::_pbi::ArenaStringPtr::InternalSwap(
        &_impl_.announcement_, &other->_impl_.announcement_, arena);
    ::google::protobuf::internal::memswap<PROTOBUF_FIELD_OFFSET(Group, _impl_.active_) +
                                          sizeof(Group::_impl_.active_) -
                                          PROTOBUF_FIELD_OFFSET(Group, _impl_.id_)>(
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