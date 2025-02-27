// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: request/conference/update_meeting_request.proto
// Protobuf C++ Version: 5.29.1

#include "turms/client/model/proto/request/conference/update_meeting_request.pb.h"

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

inline constexpr UpdateMeetingRequest::Impl_::Impl_(::_pbi::ConstantInitialized) noexcept
    : _cached_size_{0},
      custom_attributes_{},
      name_(&::google::protobuf::internal::fixed_address_empty_string,
            ::_pbi::ConstantInitialized()),
      intro_(&::google::protobuf::internal::fixed_address_empty_string,
             ::_pbi::ConstantInitialized()),
      password_(&::google::protobuf::internal::fixed_address_empty_string,
                ::_pbi::ConstantInitialized()),
      id_{::int64_t{0}} {
}

template <typename>
PROTOBUF_CONSTEXPR UpdateMeetingRequest::UpdateMeetingRequest(::_pbi::ConstantInitialized)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(_class_data_.base()),
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(),
#endif  // PROTOBUF_CUSTOM_VTABLE
      _impl_(::_pbi::ConstantInitialized()) {
}
struct UpdateMeetingRequestDefaultTypeInternal {
    PROTOBUF_CONSTEXPR UpdateMeetingRequestDefaultTypeInternal()
        : _instance(::_pbi::ConstantInitialized{}) {
    }
    ~UpdateMeetingRequestDefaultTypeInternal() {
    }
    union {
        UpdateMeetingRequest _instance;
    };
};

PROTOBUF_ATTRIBUTE_NO_DESTROY PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
    UpdateMeetingRequestDefaultTypeInternal _UpdateMeetingRequest_default_instance_;
}  // namespace proto
}  // namespace model
}  // namespace client
}  // namespace turms
namespace turms {
namespace client {
namespace model {
namespace proto {
// ===================================================================

class UpdateMeetingRequest::_Internal {
   public:
    using HasBits = decltype(std::declval<UpdateMeetingRequest>()._impl_._has_bits_);
    static constexpr ::int32_t kHasBitsOffset =
        8 * PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_._has_bits_);
};

void UpdateMeetingRequest::clear_custom_attributes() {
    ::google::protobuf::internal::TSanWrite(&_impl_);
    _impl_.custom_attributes_.Clear();
}
UpdateMeetingRequest::UpdateMeetingRequest(::google::protobuf::Arena* arena)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(arena, _class_data_.base()) {
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(arena) {
#endif  // PROTOBUF_CUSTOM_VTABLE
    SharedCtor(arena);
    // @@protoc_insertion_point(arena_constructor:turms.client.model.proto.UpdateMeetingRequest)
}
inline PROTOBUF_NDEBUG_INLINE UpdateMeetingRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility,
    ::google::protobuf::Arena* arena,
    const Impl_& from,
    const ::turms::client::model::proto::UpdateMeetingRequest& from_msg)
    : _has_bits_{from._has_bits_},
      _cached_size_{0},
      custom_attributes_{visibility, arena, from.custom_attributes_},
      name_(arena, from.name_),
      intro_(arena, from.intro_),
      password_(arena, from.password_) {
}

UpdateMeetingRequest::UpdateMeetingRequest(::google::protobuf::Arena* arena,
                                           const UpdateMeetingRequest& from)
#if defined(PROTOBUF_CUSTOM_VTABLE)
    : ::google::protobuf::MessageLite(arena, _class_data_.base()) {
#else   // PROTOBUF_CUSTOM_VTABLE
    : ::google::protobuf::MessageLite(arena) {
#endif  // PROTOBUF_CUSTOM_VTABLE
    UpdateMeetingRequest* const _this = this;
    (void)_this;
    _internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
    new (&_impl_) Impl_(internal_visibility(), arena, from._impl_, from);
    _impl_.id_ = from._impl_.id_;

    // @@protoc_insertion_point(copy_constructor:turms.client.model.proto.UpdateMeetingRequest)
}
inline PROTOBUF_NDEBUG_INLINE UpdateMeetingRequest::Impl_::Impl_(
    ::google::protobuf::internal::InternalVisibility visibility, ::google::protobuf::Arena* arena)
    : _cached_size_{0},
      custom_attributes_{visibility, arena},
      name_(arena),
      intro_(arena),
      password_(arena) {
}

inline void UpdateMeetingRequest::SharedCtor(::_pb::Arena* arena) {
    new (&_impl_) Impl_(internal_visibility(), arena);
    _impl_.id_ = {};
}
UpdateMeetingRequest::~UpdateMeetingRequest() {
    // @@protoc_insertion_point(destructor:turms.client.model.proto.UpdateMeetingRequest)
    SharedDtor(*this);
}
inline void UpdateMeetingRequest::SharedDtor(MessageLite& self) {
    UpdateMeetingRequest& this_ = static_cast<UpdateMeetingRequest&>(self);
    this_._internal_metadata_.Delete<std::string>();
    ABSL_DCHECK(this_.GetArena() == nullptr);
    this_._impl_.name_.Destroy();
    this_._impl_.intro_.Destroy();
    this_._impl_.password_.Destroy();
    this_._impl_.~Impl_();
}

inline void* UpdateMeetingRequest::PlacementNew_(const void*,
                                                 void* mem,
                                                 ::google::protobuf::Arena* arena) {
    return ::new (mem) UpdateMeetingRequest(arena);
}
constexpr auto UpdateMeetingRequest::InternalNewImpl_() {
    constexpr auto arena_bits = ::google::protobuf::internal::EncodePlacementArenaOffsets({
        PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.custom_attributes_) +
            decltype(UpdateMeetingRequest::_impl_.custom_attributes_)::InternalGetArenaOffset(
                ::google::protobuf::MessageLite::internal_visibility()),
    });
    if (arena_bits.has_value()) {
        return ::google::protobuf::internal::MessageCreator::CopyInit(
            sizeof(UpdateMeetingRequest), alignof(UpdateMeetingRequest), *arena_bits);
    } else {
        return ::google::protobuf::internal::MessageCreator(&UpdateMeetingRequest::PlacementNew_,
                                                            sizeof(UpdateMeetingRequest),
                                                            alignof(UpdateMeetingRequest));
    }
}
PROTOBUF_CONSTINIT
PROTOBUF_ATTRIBUTE_INIT_PRIORITY1
const ::google::protobuf::internal::ClassDataLite<46> UpdateMeetingRequest::_class_data_ = {
    {
        &_UpdateMeetingRequest_default_instance_._instance,
        &_table_.header,
        nullptr,  // OnDemandRegisterArenaDtor
        nullptr,  // IsInitialized
        &UpdateMeetingRequest::MergeImpl,
        ::google::protobuf::MessageLite::GetNewImpl<UpdateMeetingRequest>(),
#if defined(PROTOBUF_CUSTOM_VTABLE)
        &UpdateMeetingRequest::SharedDtor,
        ::google::protobuf::MessageLite::GetClearImpl<UpdateMeetingRequest>(),
        &UpdateMeetingRequest::ByteSizeLong,
        &UpdateMeetingRequest::_InternalSerialize,
#endif  // PROTOBUF_CUSTOM_VTABLE
        PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_._cached_size_),
        true,
    },
    "turms.client.model.proto.UpdateMeetingRequest",
};
const ::google::protobuf::internal::ClassData* UpdateMeetingRequest::GetClassData() const {
    return _class_data_.base();
}
PROTOBUF_CONSTINIT PROTOBUF_ATTRIBUTE_INIT_PRIORITY1 const ::_pbi::TcParseTable<3, 5, 1, 71, 2>
    UpdateMeetingRequest::_table_ = {
        {
            PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_._has_bits_),
            0,  // no _extensions_
            15,
            56,  // max_field_number, fast_idx_mask
            offsetof(decltype(_table_), field_lookup_table),
            4294950896,  // skipmap
            offsetof(decltype(_table_), field_entries),
            5,  // num_field_entries
            1,  // num_aux_entries
            offsetof(decltype(_table_), aux_entries),
            _class_data_.base(),
            nullptr,                                // post_loop_handler
            ::_pbi::TcParser::GenericFallbackLite,  // fallback
#ifdef PROTOBUF_PREFETCH_PARSE_TABLE
            ::_pbi::TcParser::GetTable<
                ::turms::client::model::proto::UpdateMeetingRequest>(),  // to_prefetch
#endif  // PROTOBUF_PREFETCH_PARSE_TABLE
        },
        {{
            {::_pbi::TcParser::MiniParse, {}},
            // optional int64 id = 1;
            {::_pbi::TcParser::FastV64S1,
             {8, 3, 0, PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.id_)}},
            // optional string name = 2;
            {::_pbi::TcParser::FastUS1,
             {18, 0, 0, PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.name_)}},
            // optional string intro = 3;
            {::_pbi::TcParser::FastUS1,
             {26, 1, 0, PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.intro_)}},
            // optional string password = 4;
            {::_pbi::TcParser::FastUS1,
             {34, 2, 0, PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.password_)}},
            {::_pbi::TcParser::MiniParse, {}},
            {::_pbi::TcParser::MiniParse, {}},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {::_pbi::TcParser::FastMtR1,
             {122, 63, 0, PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.custom_attributes_)}},
        }},
        {{65535, 65535}},
        {{
            // optional int64 id = 1;
            {PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.id_),
             _Internal::kHasBitsOffset + 3,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kInt64)},
            // optional string name = 2;
            {PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.name_),
             _Internal::kHasBitsOffset + 0,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // optional string intro = 3;
            {PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.intro_),
             _Internal::kHasBitsOffset + 1,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // optional string password = 4;
            {PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.password_),
             _Internal::kHasBitsOffset + 2,
             0,
             (0 | ::_fl::kFcOptional | ::_fl::kUtf8String | ::_fl::kRepAString)},
            // repeated .turms.client.model.proto.Value custom_attributes = 15;
            {PROTOBUF_FIELD_OFFSET(UpdateMeetingRequest, _impl_.custom_attributes_),
             -1,
             0,
             (0 | ::_fl::kFcRepeated | ::_fl::kMessage | ::_fl::kTvTable)},
        }},
        {{
            {::_pbi::TcParser::GetTable<::turms::client::model::proto::Value>()},
        }},
        {{"\55\0\4\5\10\0\0\0"
          "turms.client.model.proto.UpdateMeetingRequest"
          "name"
          "intro"
          "password"}},
    };

PROTOBUF_NOINLINE void UpdateMeetingRequest::Clear() {
    // @@protoc_insertion_point(message_clear_start:turms.client.model.proto.UpdateMeetingRequest)
    ::google::protobuf::internal::TSanWrite(&_impl_);
    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

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
            _impl_.password_.ClearNonDefaultToEmpty();
        }
    }
    _impl_.id_ = ::int64_t{0};
    _impl_._has_bits_.Clear();
    _internal_metadata_.Clear<std::string>();
}

#if defined(PROTOBUF_CUSTOM_VTABLE)
::uint8_t* UpdateMeetingRequest::_InternalSerialize(
    const MessageLite& base,
    ::uint8_t* target,
    ::google::protobuf::io::EpsCopyOutputStream* stream) {
    const UpdateMeetingRequest& this_ = static_cast<const UpdateMeetingRequest&>(base);
#else   // PROTOBUF_CUSTOM_VTABLE
::uint8_t* UpdateMeetingRequest::_InternalSerialize(
    ::uint8_t* target, ::google::protobuf::io::EpsCopyOutputStream* stream) const {
    const UpdateMeetingRequest& this_ = *this;
#endif  // PROTOBUF_CUSTOM_VTABLE
        // @@protoc_insertion_point(serialize_to_array_start:turms.client.model.proto.UpdateMeetingRequest)
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    cached_has_bits = this_._impl_._has_bits_[0];
    // optional int64 id = 1;
    if (cached_has_bits & 0x00000008u) {
        target = ::google::protobuf::internal::WireFormatLite::WriteInt64ToArrayWithField<1>(
            stream, this_._internal_id(), target);
    }

    // optional string name = 2;
    if (cached_has_bits & 0x00000001u) {
        const std::string& _s = this_._internal_name();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.UpdateMeetingRequest.name");
        target = stream->WriteStringMaybeAliased(2, _s, target);
    }

    // optional string intro = 3;
    if (cached_has_bits & 0x00000002u) {
        const std::string& _s = this_._internal_intro();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.UpdateMeetingRequest.intro");
        target = stream->WriteStringMaybeAliased(3, _s, target);
    }

    // optional string password = 4;
    if (cached_has_bits & 0x00000004u) {
        const std::string& _s = this_._internal_password();
        ::google::protobuf::internal::WireFormatLite::VerifyUtf8String(
            _s.data(),
            static_cast<int>(_s.length()),
            ::google::protobuf::internal::WireFormatLite::SERIALIZE,
            "turms.client.model.proto.UpdateMeetingRequest.password");
        target = stream->WriteStringMaybeAliased(4, _s, target);
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
    // @@protoc_insertion_point(serialize_to_array_end:turms.client.model.proto.UpdateMeetingRequest)
    return target;
}

#if defined(PROTOBUF_CUSTOM_VTABLE)
::size_t UpdateMeetingRequest::ByteSizeLong(const MessageLite& base) {
    const UpdateMeetingRequest& this_ = static_cast<const UpdateMeetingRequest&>(base);
#else   // PROTOBUF_CUSTOM_VTABLE
::size_t UpdateMeetingRequest::ByteSizeLong() const {
    const UpdateMeetingRequest& this_ = *this;
#endif  // PROTOBUF_CUSTOM_VTABLE
        // @@protoc_insertion_point(message_byte_size_start:turms.client.model.proto.UpdateMeetingRequest)
    ::size_t total_size = 0;

    ::uint32_t cached_has_bits = 0;
    // Prevent compiler warnings about cached_has_bits being unused
    (void)cached_has_bits;

    ::_pbi::Prefetch5LinesFrom7Lines(&this_);
    {
        // repeated .turms.client.model.proto.Value custom_attributes = 15;
        {
            total_size += 1UL * this_._internal_custom_attributes_size();
            for (const auto& msg : this_._internal_custom_attributes()) {
                total_size += ::google::protobuf::internal::WireFormatLite::MessageSize(msg);
            }
        }
    }
    cached_has_bits = this_._impl_._has_bits_[0];
    if (cached_has_bits & 0x0000000fu) {
        // optional string name = 2;
        if (cached_has_bits & 0x00000001u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this_._internal_name());
        }
        // optional string intro = 3;
        if (cached_has_bits & 0x00000002u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this_._internal_intro());
        }
        // optional string password = 4;
        if (cached_has_bits & 0x00000004u) {
            total_size += 1 + ::google::protobuf::internal::WireFormatLite::StringSize(
                                  this_._internal_password());
        }
        // optional int64 id = 1;
        if (cached_has_bits & 0x00000008u) {
            total_size += ::_pbi::WireFormatLite::Int64SizePlusOne(this_._internal_id());
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

void UpdateMeetingRequest::MergeImpl(::google::protobuf::MessageLite& to_msg,
                                     const ::google::protobuf::MessageLite& from_msg) {
    auto* const _this = static_cast<UpdateMeetingRequest*>(&to_msg);
    auto& from = static_cast<const UpdateMeetingRequest&>(from_msg);
    // @@protoc_insertion_point(class_specific_merge_from_start:turms.client.model.proto.UpdateMeetingRequest)
    ABSL_DCHECK_NE(&from, _this);
    ::uint32_t cached_has_bits = 0;
    (void)cached_has_bits;

    _this->_internal_mutable_custom_attributes()->MergeFrom(from._internal_custom_attributes());
    cached_has_bits = from._impl_._has_bits_[0];
    if (cached_has_bits & 0x0000000fu) {
        if (cached_has_bits & 0x00000001u) {
            _this->_internal_set_name(from._internal_name());
        }
        if (cached_has_bits & 0x00000002u) {
            _this->_internal_set_intro(from._internal_intro());
        }
        if (cached_has_bits & 0x00000004u) {
            _this->_internal_set_password(from._internal_password());
        }
        if (cached_has_bits & 0x00000008u) {
            _this->_impl_.id_ = from._impl_.id_;
        }
    }
    _this->_impl_._has_bits_[0] |= cached_has_bits;
    _this->_internal_metadata_.MergeFrom<std::string>(from._internal_metadata_);
}

void UpdateMeetingRequest::CopyFrom(const UpdateMeetingRequest& from) {
    // @@protoc_insertion_point(class_specific_copy_from_start:turms.client.model.proto.UpdateMeetingRequest)
    if (&from == this)
        return;
    Clear();
    MergeFrom(from);
}

void UpdateMeetingRequest::InternalSwap(UpdateMeetingRequest* PROTOBUF_RESTRICT other) {
    using std::swap;
    auto* arena = GetArena();
    ABSL_DCHECK_EQ(arena, other->GetArena());
    _internal_metadata_.InternalSwap(&other->_internal_metadata_);
    swap(_impl_._has_bits_[0], other->_impl_._has_bits_[0]);
    _impl_.custom_attributes_.InternalSwap(&other->_impl_.custom_attributes_);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.name_, &other->_impl_.name_, arena);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.intro_, &other->_impl_.intro_, arena);
    ::_pbi::ArenaStringPtr::InternalSwap(&_impl_.password_, &other->_impl_.password_, arena);
    swap(_impl_.id_, other->_impl_.id_);
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